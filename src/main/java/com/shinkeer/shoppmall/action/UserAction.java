package com.shinkeer.shoppmall.action;

import com.alibaba.fastjson.JSON;
import com.shinkeer.shoppmall.base.dto.RT;
import com.shinkeer.shoppmall.bo.UserBO;
import com.shinkeer.shoppmall.common.aspect.ADD;
import com.shinkeer.shoppmall.common.aspect.EDIT;
import com.shinkeer.shoppmall.common.exception.ActException;
import com.shinkeer.shoppmall.common.exception.SerException;
import com.shinkeer.shoppmall.common.result.ActResult;
import com.shinkeer.shoppmall.common.result.Result;
import com.shinkeer.shoppmall.dto.UserDTO;
import com.shinkeer.shoppmall.entity.User;
import com.shinkeer.shoppmall.service.UserService;
import com.shinkeer.shoppmall.to.UserTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserAction {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/findUsers",method = RequestMethod.GET)
    public String findUsers(UserDTO userDTO) throws ActException{
        Map<String,Object> maps = new HashMap<>();
        try {
            // 可以不用判断，自动会识别
            if(StringUtils.isNotBlank(userDTO.getUserName())){
                userDTO.addRT(RT.eq("userName", userDTO.getUserName()));
            }
            if(StringUtils.isNotBlank(userDTO.getSort())&&StringUtils.isNotBlank(userDTO.getSortOrder())){
                userDTO.addSort(userDTO.getSort()+"="+userDTO.getSortOrder());
            }
            maps.put("total",userService.count(userDTO));
            maps.put("rows", userService.findByPage(userDTO));
              return JSON.toJSONString(maps);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }

    }
    @ResponseBody
    @RequestMapping(value = "/user/findOne2",method = RequestMethod.GET)
    public User findOne2(UserDTO userDTO) throws ActException {
        try {
            User user = new User();
            List<User> user_list = userService.findAll();
            if(user_list.size()>0){
                user = user_list.get(0);
            }
            return user;
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/user/findById",method = RequestMethod.GET)
    public UserBO findById(@RequestParam String id) throws ActException {
        try {
            User user = userService.findById(id);
            UserBO userBO = new UserBO();
            BeanUtils.copyProperties(user,userBO);
            return userBO;
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user/saveUser",method = RequestMethod.POST)
    public Result saveUser(@Validated(ADD.class)UserTO userTO,BindingResult rs)throws ActException{

        try {
            userService.insertUser(userTO);
            return new ActResult("success");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }

    }
    @ResponseBody
    @RequestMapping(value = "/user/updateUser",method = RequestMethod.POST)
    public Result updateUser(@Validated(EDIT.class) UserTO userTO,BindingResult rs)throws ActException{
        try {
            userService.updateUser(userTO);
            return new ActResult("success");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws ActException
     */
    @ResponseBody
    @RequestMapping(value = "/user/deleteUsers",method = RequestMethod.POST)
    public Result deleteUser(String[] ids)throws ActException{
        try {
            userService.remove(ids);
            return new ActResult("success");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }
}
