/**
 * 用户查询数据传输
 *
 * @author lgq
 * @date 2018/4/15
 **/
package com.shinkeer.shoppmall.dto;

import com.shinkeer.shoppmall.base.dto.BaseDTO;

public class UserDTO extends BaseDTO {
    /**
     * 联系人
     */
    private String userName;
    /**
     * 排序列名
     */
    private String sort;
    /**
     * 排序命令（desc asc）
     */
    private String sortOrder;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
