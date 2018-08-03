package com.shinkeer.shoppmall.action;

import com.alibaba.fastjson.JSON;
import com.shinkeer.shoppmall.base.dto.RT;
import com.shinkeer.shoppmall.common.exception.ActException;
import com.shinkeer.shoppmall.common.exception.SerException;
import com.shinkeer.shoppmall.common.result.ActResult;
import com.shinkeer.shoppmall.common.result.Result;
import com.shinkeer.shoppmall.dto.LoginUserDTO;
import com.shinkeer.shoppmall.entity.LoginUser;
import com.shinkeer.shoppmall.service.LoginUserService;
import com.shinkeer.shoppmall.to.LoginUserTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginUserAction {
    @Autowired
    LoginUserService loginUserService;

    @ResponseBody
    @RequestMapping(value = "/loginUser/findList",method = RequestMethod.GET)
    public String findUsers(LoginUserDTO loginUserDTO) throws ActException{
        Map<String,Object> maps = new HashMap<>();
        try {
            if(StringUtils.isNotBlank(loginUserDTO.getLoginName())){
                loginUserDTO.addRT(RT.eq("loginName", loginUserDTO.getLoginName()));
            }
            if(StringUtils.isNotBlank(loginUserDTO.getSort())&&StringUtils.isNotBlank(loginUserDTO.getSortOrder())){
                loginUserDTO.addSort(loginUserDTO.getSort()+"="+loginUserDTO.getSortOrder());
            }
            maps.put("total",loginUserService.count(loginUserDTO));
            maps.put("rows", loginUserService.findByPage(loginUserDTO));
              return JSON.toJSONString(maps);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/loginUser/findById",method = RequestMethod.GET)
    public Result findById(@RequestParam String id) throws ActException {
        try {
            LoginUser loginUser = loginUserService.findById(id);
            return new ActResult(loginUser);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/loginUser/registration",method = RequestMethod.POST)
    public Result saveUser(@Validated(LoginUserTO.RegistrationTest.class)LoginUserTO loginUserTO, BindingResult rs)throws ActException{
        try {
            loginUserService.registration(loginUserTO);
            return new ActResult("success");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }

    }
    @ResponseBody
    @RequestMapping(value = "/loginUser/update",method = RequestMethod.POST)
    public Result updateUser(@Validated(LoginUserTO.RegistrationTest.class)LoginUserTO loginUserTO,BindingResult rs)throws ActException{
        try {
            loginUserService.updateUser(loginUserTO);
            return new ActResult("success");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }

    }

//    /**
//     * 批量删除
//     * @param ids
//     * @return
//     * @throws ActException
//     */
//    @ResponseBody
//    @RequestMapping(value = "/user/deleteUsers",method = RequestMethod.POST)
//    public Result deleteUser(String[] ids)throws ActException{
//        try {
//            userService.remove(ids);
//            return new ActResult("success");
//        }catch (SerException e){
//            throw new ActException(e.getMessage());
//        }
//    }
}
