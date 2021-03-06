/**
 * @author lgq
 * @date 2018/4/15
 **/
package com.shinkeer.shoppmall.base.service;


import com.shinkeer.shoppmall.base.dto.BaseDTO;
import com.shinkeer.shoppmall.base.entity.BaseEntity;
import com.shinkeer.shoppmall.common.exception.SerException;

import java.util.List;

/**
 * 基础业务接口
 *
 * @param <BE>
 * @param <BD>
 */
public interface Ser<BE extends BaseEntity, BD extends BaseDTO> {
    /**
     * 查询所有数据
     *
     * @return
     * @throws SerException
     */
    default List<BE> findAll() throws SerException {
        return null;
    }

    /**
     * 查询分页数据
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<BE> findByPage(BD dto) throws SerException {
        return null;
    }

    /**
     * 查询数据量
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long count(BD dto) throws SerException {
        return null;
    }

    /**
     * 查询第一个对象
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default BE findOne(BD dto) throws SerException {
        return null;
    }

    /**
     * 根据条件询对象列表
     * 是否分页
     *
     * @param dto
     * @param page 分页
     * @return
     * @throws SerException
     */
    default List<BE> findByRTS(BD dto, Boolean page) throws SerException {
        return null;
    }

    /**
     * 根据条件询对象列表
     * 默认不分页排序
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<BE> findByRTS(BD dto) throws SerException {
        return null;
    }


    /**
     * 通过id查询某个对象
     *
     * @param id
     * @return
     * @throws SerException
     */
    default BE findById(String id) throws SerException {
        return null;
    }


    /**
     * 保存对象列表
     *
     * @param entities
     * @throws SerException
     */
    default void save(BE... entities) throws SerException {

    }

    /**
     * 通过id删除对象
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 通过id批量删除对象
     *
     * @param ids
     * @throws SerException
     */
    default void remove(String[] ids) throws SerException {

    }


    /**
     * 删除对象列表
     *
     * @param entities
     * @throws SerException
     */
    default void remove(BE... entities) throws SerException {

    }


    /**
     * 更新对象
     *
     * @param entities
     * @throws SerException
     */
    default void update(BE... entities) throws SerException {

    }

    /**
     * 通过id查询对象是否存在
     *
     * @param id
     * @return
     * @throws SerException
     */
    default Boolean exists(String id) throws SerException {
        return null;
    }

    /**
     * sql查询
     *
     * @param sql    sql语句
     * @param clazz  查询结果映射类
     * @param fields 查询的字段
     * @param
     * @return
     * @throws SerException
     */
    default <T> List<T> findBySql(String sql, Class clazz, String[] fields) throws SerException {
        return null;
    }

    /**
     * sql原生查询,执行结果需要自己解析
     *
     * @param sql sql语句
     * @return
     * @throws SerException
     */
    default List<Object> findBySql(String sql) throws SerException {
        return null;
    }


    /**
     * 执行sql语句
     *
     * @param sql
     * @throws SerException
     */
    default void executeSql(String sql) throws SerException {
    }

    /**
     * 获取表名
     *
     * @param clazz
     * @return
     */
    default String getTableName(Class clazz) throws SerException {
        return null;
    }
}
