package com.shinkeer.shoppmall.entity;

import com.shinkeer.shoppmall.base.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User extends BaseEntity{

    @Column(name = "id", nullable = false, length = 36, updatable = false, insertable = false)
    private String id;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系人'")
    private String userName;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String phone;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String email;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT 'qq'")
    private String qq;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String address;

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
}
