package com.shinkeer.shoppmall.action;

import com.alibaba.fastjson.JSON;
import com.shinkeer.shoppmall.entity.User;
import com.shinkeer.shoppmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserAction {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/findUsers",method = RequestMethod.GET)
    public String findUsers(){
        List<User> users = userService.findUsers(0,5);
        Map<String,Object> maps = new HashMap<>();
        maps.put("total",users.size());
        maps.put("rows",users);
        return JSON.toJSONString(maps);
    }
    @ResponseBody
    @RequestMapping(value = "/user/findOne2",method = RequestMethod.GET)
    public User findOne2(){
        return userService.findOne2();
    }
    @ResponseBody
    @RequestMapping(value = "/user/saveUser",method = RequestMethod.POST)
    public String saveUser(User user){
        return userService.insertUser(user);
    }
    @ResponseBody
    @RequestMapping(value = "/user/updateUser",method = RequestMethod.POST)
    public String updateUser(User user){
        return userService.updateUser(user);
    }
    @ResponseBody
    @RequestMapping(value = "/user/deleteUser",method = RequestMethod.POST)
    public String deleteUser(@RequestParam String id){
        return userService.deleteUser(id);
    }
}
