package com.shinkeer.shoppmall.service;

import com.shinkeer.shoppmall.base.dto.RT;
import com.shinkeer.shoppmall.base.service.ServiceImpl;
import com.shinkeer.shoppmall.common.exception.SerException;
import com.shinkeer.shoppmall.dao.UserDao;
import com.shinkeer.shoppmall.dto.UserDTO;
import com.shinkeer.shoppmall.entity.User;
import com.shinkeer.shoppmall.to.UserTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService extends ServiceImpl<User, UserDTO> {
    @Autowired
    private UserDao userDao;
//    @Autowired
//    protected EntityManager entityManager;




    public void insertUser(UserTO userTO) throws SerException {
        User user = new User();
        BeanUtils.copyProperties(userTO, user);
        user.setCreateDate(LocalDateTime.now());
        super.save(user);
    }

    public void updateUser(UserTO userTO) throws SerException {
        User user = super.findById(userTO.getId());
        BeanUtils.copyProperties(userTO, user);
        super.update(user);
    }
}
