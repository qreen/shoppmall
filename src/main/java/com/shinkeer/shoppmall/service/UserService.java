package com.shinkeer.shoppmall.service;

import com.shinkeer.shoppmall.dao.UserDao;
import com.shinkeer.shoppmall.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    protected EntityManager entityManager;

    public List<User> findUsers(int limit, int offset){
        String sql = "select * from user limit "+limit+","+offset;
        Query nativeQuery = entityManager.createNativeQuery(sql);
        return nativeQuery.getResultList();
    }

    /**
     * 数据库类型转换
     *
     * @param obj
     * @return
     */
    private Object convertDataType(String type, Object obj) {
        if (null != obj) {
            String val = obj.toString();
            switch (type) {
                case "int":
                    obj = Integer.parseInt(val);
                    break;
                case "Float":
                    obj = Float.parseFloat(val);
                    break;
                case "Double":
                    obj = Double.parseDouble(val);
                    break;
                case "Long":
                    obj = Long.parseLong(val);
                    break;
                case "BigDecimal":
                    obj = Double.parseDouble(val);
                    break;
                case "Boolean":
                    obj = Boolean.parseBoolean(val);
                    break;
                case "Integer":
                    obj = Integer.parseInt(val);
                    break;
                case "LocalDateTime":
                    obj = LocalDateTime.parse(StringUtils.substring(val, 0, val.length() - 2), DATE_TIME);
                    break;
                case "LocalTime":
                    obj = LocalDateTime.parse(val, TIME);
                    break;
                case "LocalDate":
                    obj = LocalDate.parse(val, DATE);
                    break;
                default:
                    obj = String.valueOf(obj);
                    break;

            }
        }
        return obj;
    }

    public <T> List<T> findBySql(String sql, Class clazz, String[] fields) throws SerException {
        List<Field> all_fields = new ArrayList<>(); //源类属性列表
        Class temp_clazz = clazz;
        while (null != temp_clazz) { //数据源类所有属性（包括父类）
            all_fields.addAll(Arrays.asList(temp_clazz.getDeclaredFields()));
            temp_clazz = temp_clazz.getSuperclass();
            if (Object.class.equals(temp_clazz) || null == temp_clazz) {
                break;
            }
        }
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object> resultList = nativeQuery.getResultList();
        List<T> list = new ArrayList<>(resultList.size());

        //解析查询结果
        try {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] arr_obj;
                if (fields.length > 1) {
                    arr_obj = (Object[]) resultList.get(i);
                } else {
                    arr_obj = new Object[]{resultList.get(i)};
                }
                Object obj = clazz.newInstance();
                for (int j = 0; j < fields.length; j++) {
                    for (Field field : all_fields) {
                        if (field.getName().equals(fields[j])) {
                            field.setAccessible(true);
                            if (!field.getType().isEnum()) { //忽略枚举类型
                                field.set(obj, convertDataType(field.getType().getSimpleName(), arr_obj[j]));
                            } else {
                                Field[] enumFields = field.getType().getFields();
                                for (int k = 0; k < enumFields.length; k++) {
                                    Integer val = null;
                                    if (null != arr_obj[j]) {
                                        val = Integer.parseInt(arr_obj[j].toString());
                                    }
                                    if (null != val && val == k) {
                                        String name = enumFields[k].getName();
                                        field.set(obj, field.getType().getField(name).get(name));
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                list.add((T) obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw repExceptionHandler(new RepException(e.getMessage()));
        }

        return list;
    }


    public User findOne2(){
       List<User> users = userDao.findAll();
       User user = new User();
       if(users.size()>0){
           user = users.get(0);
       }
       return user;
    }
    public String insertUser(User user){
        String msg = "";
        try {
            userDao.save(user);
            msg = "保存成功";
        }catch (Exception e){
            e.printStackTrace();
            msg = "保存失败";
        }
        return msg;
    }
    public String updateUser(User user){
        String msg = "";
        try {
            userDao.saveAndFlush(user);
            msg = "更新成功";
        }catch (Exception e){
            e.printStackTrace();
            msg = "更新失败";
        }
        return msg;
    }
    public String deleteUser(String id){
        String msg = "";
        try {
            userDao.deleteById(id);
            msg = "删除成功";
        }catch (Exception e){
            e.printStackTrace();
            msg = "删除失败";
        }
        return msg;
    }
}
