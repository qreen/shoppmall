package com.shinkeer.shoppmall.to;

import com.shinkeer.shoppmall.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class LoginUserTO{

    public interface  RegistrationTest{}

    private String id;
    @NotBlank(groups = {LoginUserTO.RegistrationTest.class}, message = "用户名不能为空")
    private String loginName;
    @NotBlank(groups = {LoginUserTO.RegistrationTest.class}, message = "密码不能为空")
    private String loginPassword;
    private String loginPhone;
    @NotBlank(groups = {LoginUserTO.RegistrationTest.class}, message = "邮箱不能为空")
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
