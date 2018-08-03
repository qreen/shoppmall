package com.shinkeer.shoppmall.dto;

import com.shinkeer.shoppmall.base.dto.BaseDTO;

import javax.validation.constraints.NotBlank;

public class LoginUserDTO extends BaseDTO {

    private interface  loginTest{}

    private String loginName;
    private String loginPassword;
    private String loginPhone;
    private String loginEmail;
    /**
     * 排序列名
     */
    private String sort;
    /**
     * 排序命令（desc asc）
     */
    private String sortOrder;


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
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
