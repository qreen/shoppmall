package com.shinkeer.shoppmall.to;

import com.shinkeer.shoppmall.common.aspect.ADD;
import com.shinkeer.shoppmall.common.aspect.EDIT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

public class UserTO{

    @NotBlank(groups = {EDIT.class}, message = "id不能为空")
    private String id;
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "联系人不能为空")
    private String userName;
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "联系方式不能为空")
    private String phone;
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "邮箱不能为空")
    private String email;
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "qq不能为空")
    private String qq;
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "地址不能为空")
    private String address;
    private String[] ids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
