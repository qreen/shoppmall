/**
 * jpa 高级查询实现
 *
 * @author lgq
 * @date 2018/4/15
 **/
package com.shinkeer.shoppmall.base.rep;


import com.shinkeer.shoppmall.base.dto.BaseDTO;
import com.shinkeer.shoppmall.base.dto.Restrict;
import com.shinkeer.shoppmall.base.dto.RestrictType;
import com.shinkeer.shoppmall.base.entity.BaseEntity;
import com.shinkeer.shoppmall.common.exception.RepException;
import com.shinkeer.shoppmall.common.types.RepExceptionType;
import com.shinkeer.shoppmall.common.utils.ClazzTypeUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.*;
import java.lang.reflect.Method;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JPA 高级查询
 *
 * @param <BE> 实体
 * @param <BD> 数据传输实体
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JpaSpec<BE extends BaseEntity, BD extends BaseDTO> implements Specification<BE> {
    private BD dto;

    public JpaSpec(BD dto) {
        this.dto = dto;
    }

    @Override
    public Specification and(Specification other) {
        return null;
    }

    @Override
    public Specification or(Specification other) {
        return null;
    }

    private static final Pattern PATTERN = Pattern.compile("\\[[a-zA-Z0-9]+\\]");

    @Nullable
    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        try {

            Predicate predicate = initPredicates(dto, root, criteriaBuilder);
            if(null!=predicate){
                return query.where(predicate).getRestriction();
            }else {
                return query.getRestriction();
            }


        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 连表查询支持单属性（model）查询，set，list集合
     *
     * @param dto
     * @param root
     * @param cb
     * @return
     * @throws RepException
     */
    private Predicate initPredicates(BD dto, Root<BE> root, CriteriaBuilder cb) throws RepException {
        List<Predicate> preList = new ArrayList<>(0); //条件列表
        List<Restrict> restricts = null;
        if (null != dto) {
            restricts = dto.getRestricts();//避免条件列表为空
        }
        List<Predicate> or_preList = new ArrayList<>(); //or 条件列表
        Class clazz = null;
        Join<BE, Object> join = null;
        if (null != restricts && restricts.size() > 0) {
            try {
                for (Restrict model : restricts) {
                    Boolean isOrPre = false; //是否为or查询
                    Predicate predicate = null;
                    RestrictType type = model.getRestrictType();//查询条件类型
                    String field = model.getField(); //字段
                    if (null != model.getValue()) {
                        if (model.getValue() instanceof Boolean) {
                            model.setValue(Boolean.TRUE == model.getValue() ? "1" : "0");
                        }
                        clazz = ClazzTypeUtil.switchType(model.getValue()); //得到数据类型

                    } else {
                        if (type.equals(RestrictType.ISNULL) || //该查询为value可为空
                                type.equals(RestrictType.ISNOTNULL)) {
                            clazz = String.class;
                        } else {
                            throw new RepException(type.name() + "查询:列【" + field + "】值不能为空");
                        }

                    }
                    String[] fields = model.getField().split("\\.");
                    join = handlerJoinTable(root, fields);  //是否有连接查询
                    Method method = handlerMethod(cb, model);//获得反射调用方法
                    Boolean existJoin = (null != join);
                    if (existJoin) {
                        field = fields[fields.length - 1]; //有连接查询取最后的分割字段
                    }
                    switch (type) {
                        case LIKE:
                            if (existJoin) {
                                predicate = cb.like(join.get(field).as(clazz), "%" + model.getValue() + "%");
                            } else {
                                predicate = cb.like(root.get(field).as(clazz), "%" + model.getValue() + "%");
                            }
                            break;
                        case ISNULL:
                            if (existJoin) {
                                predicate = cb.isNull(join.get(field).as(clazz));
                            } else {
                                predicate = cb.isNull(root.get(field).as(clazz));
                            }
                            break;
                        case ISNOTNULL:
                            if (existJoin) {
                                predicate = cb.isNotNull(join.get(field).as(clazz));
                            } else {
                                predicate = cb.isNotNull(root.get(field).as(clazz));
                            }
                            break;
                        case NOTIN:
                            Object[] vals = ClazzTypeUtil.convertValuesByType(model.getValue());
                            CriteriaBuilder.In in = null;
                            if (existJoin) {
                                in = cb.in(join.get(field));
                            } else {
                                in = cb.in(root.get(field));
                            }
                            for (int i = 0; i < vals.length; i++) {
                                in.value(vals[i]);
                            }
                            predicate = cb.not(in);
                            break;
                        case OR:
                            isOrPre = true;
                            if (existJoin) {
                                predicate = cb.or(cb.equal(join.get(field).as(clazz), model.getValue()));
                            } else {
                                predicate = cb.or(cb.equal(root.get(field).as(clazz), model.getValue()));
                            }
                            break;
                        case IN: {
                            Object[] values = ClazzTypeUtil.convertValuesByType(model.getValue());
                            if (existJoin) {
                                predicate = join.get(field).as(clazz).in(values);
                            } else {
                                predicate = root.get(field).as(clazz).in(values);
                            }
                            break;
                        }

                        default:
                            Object[] values = ClazzTypeUtil.convertValuesByType(model.getValue());
                            if (existJoin) {
                                predicate = (Predicate) method.invoke(cb, ArrayUtils.add(values, 0, join.get(field).as(clazz)));
                            } else {
                                predicate = (Predicate) method.invoke(cb, ArrayUtils.add(values, 0, root.get(field).as(clazz)));

                            }

                    }
                    if (null != predicate) { //处理or
                        if (!isOrPre) {
                            preList.add(predicate);
                        } else {
                            or_preList.add(predicate);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                exceptionHandler(e);

            }

            Predicate[] predicates = new Predicate[preList.size()];
            Predicate predicate = cb.and(preList.toArray(predicates));
            if (or_preList.size() > 0) { //处理 or 查询
                or_preList.add(0, predicate);
                Predicate[] or_pres = new Predicate[or_preList.size()];
                predicate = cb.or(or_preList.toArray(or_pres));
            }
            return predicate;
        } else {
            return null;
        }

    }

    private Method handlerMethod(CriteriaBuilder cb, Restrict restrict) {
        Method[] methods = cb.getClass().getDeclaredMethods();
        Method method = null;
        String name = RestrictType.getRestrict(restrict.getRestrictType());
        for (Method m : methods) {
            Class<?>[] types = m.getParameterTypes();
            if (m.getName().equals(name) &&
                    types[types.length - 1] != Expression.class) {
                method = m;
                break;
            }
        }
        return method;
    }


    /**
     * 左连接查询
     *
     * @param root
     * @return
     */
    private Join<BE, Object> handlerJoinTable(Root<BE> root, String[] fields) {
        int fields_length = fields.length - 1; //忽略最后的属性查询字段 如user.userinfo.email 只取user.userinfo
        Join<BE, Object> join = null;
        if (fields_length >= 1) {  //存在连接查询
            for (int i = 0; i < fields_length; i++) {
                String entityName = fields[i];
                boolean isList = StringUtils.lastIndexOf(entityName,"List")>-1;
                boolean isSet = StringUtils.lastIndexOf(entityName,"Set")>-1;
                if (i == 0) {
                    if (isSet) {
                        join = root.joinSet(entityName, JoinType.LEFT);
                    } else if (isList) {
                        join = root.joinList(entityName, JoinType.LEFT);
                    } else {
                        join = root.join(entityName, JoinType.LEFT);
                    }
                } else {
                    if(isList || isSet){
                        if (isList) {
                            join = join.joinList(entityName, JoinType.LEFT);
                        } else if (isSet) {
                            join = join.joinSet(entityName, JoinType.LEFT);
                        }
                    }else {
                        join = join.join(entityName, JoinType.LEFT);
                    }
                }

            }

            return join;
        }
        return null;
    }

    /**
     * 查询异常处理
     *
     * @param e
     * @return
     */
    public static RepException exceptionHandler(Exception e) throws RepException {
        String msg = "";
        RepExceptionType type = RepExceptionType.UNDEFINE;

        if (e instanceof IllegalArgumentException) {
            Matcher matcher = PATTERN.matcher(e.getMessage());
            boolean isFind = matcher.find();
            if (isFind) { //字段不匹配
                msg = StringUtils.substring(matcher.group(), 1, -1);
                type = RepExceptionType.NOT_FIND_FIELD;
            } else if (e.getMessage().indexOf("wrong number of arguments") != -1) { //其他错误
                msg = "wrong number of arguments";
                type = RepExceptionType.ERROR_ARGUMENTS;
            } else if (e instanceof NumberFormatException) {
                msg = e.getMessage();
                type = RepExceptionType.ERROR_NUMBER_FORMAT;
            }
        } else {
            if (e instanceof DateTimeParseException) {
                msg = e.getMessage();
                type = RepExceptionType.ERROR_PARSE_DATE;
            } else {
                msg = e.getMessage();
                type = RepExceptionType.UNDEFINE;
            }

        }
        throw new RepException(type, msg);
    }

    /**
     * 分页及排序
     *
     * @param dto
     * @return
     */
    public PageRequest getPageRequest(BD dto) {
        PageRequest pageRequest;
        Sort sort = null;
        List<String> _sorts = dto.getSorts();
        if (_sorts != null && _sorts.size() > 0) {
            String field;
            String order = null;
            for (String sorts : _sorts) {
                String[] _sort = sorts.split("=");
                if (_sort.length > 1) {
                    order = _sort[1];
                }
                field = _sort[0];
                Sort.Direction dct = null;
                if (null != order && order.equalsIgnoreCase("asc")) {
                    dct = Sort.Direction.ASC;
                } else {
                    dct = Sort.Direction.DESC;
                }
                if (null == sort) {
                    sort = new Sort(dct, field);
                } else {
                    sort = sort.and(new Sort(dct, field));
                }
            }
            pageRequest = PageRequest.of(dto.getPage(), dto.getLimit(), sort); //分页带排序
        } else {
            pageRequest = PageRequest.of(dto.getPage(), dto.getLimit()); //分页不带排序
        }
        return pageRequest;
    }

}