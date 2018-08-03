package com.shinkeer.shoppmall.entity;

import com.shinkeer.shoppmall.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "loginuser")
public class LoginUser extends BaseEntity{

    @Column(name = "id", nullable = false, length = 36, updatable = false, insertable = false)
    private String id;
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '用户名'")
    private String loginName;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '密码'")
    private String loginPassword;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String loginPhone;
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String loginEmail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
