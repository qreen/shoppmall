package com.shinkeer.shoppmall.service;

import com.shinkeer.shoppmall.base.dto.RT;
import com.shinkeer.shoppmall.base.service.ServiceImpl;
import com.shinkeer.shoppmall.common.exception.SerException;
import com.shinkeer.shoppmall.dao.LoginUserDao;
import com.shinkeer.shoppmall.dao.UserDao;
import com.shinkeer.shoppmall.dto.LoginUserDTO;
import com.shinkeer.shoppmall.dto.UserDTO;
import com.shinkeer.shoppmall.entity.LoginUser;
import com.shinkeer.shoppmall.entity.User;
import com.shinkeer.shoppmall.to.LoginUserTO;
import com.shinkeer.shoppmall.to.UserTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginUserService extends ServiceImpl<LoginUser, LoginUserDTO> {
    @Autowired
    private LoginUserDao loginUserDao;

    //登录
    public int login(LoginUserDTO loginUserDTO) throws SerException{
        LoginUser loginUser = new LoginUser();
        loginUserDTO.addRT(RT.eq("loginName",loginUserDTO.getLoginName()));
        List<LoginUser> loginUsers = super.findByRTS(loginUserDTO);
        if(loginUsers.size()>0){
            loginUser = loginUsers.get(0);
            if(loginUser.getLoginPassword().equals(loginUserDTO.getLoginPassword())){
                return 1;
            }else{
                return 2;
            }
        }
        return 2;
    }



    public void registration(LoginUserTO loginUserTO) throws SerException {
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(loginUserTO, loginUser);
        loginUser.setCreateDate(LocalDateTime.now());
        super.save(loginUser);
    }

    public void updateUser(LoginUserTO loginUserTO) throws SerException {
        LoginUser loginUser = super.findById(loginUserTO.getId());
        BeanUtils.copyProperties(loginUserTO, loginUser);
        super.update(loginUser);
    }
}
