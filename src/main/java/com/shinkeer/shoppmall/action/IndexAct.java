package com.shinkeer.shoppmall.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAct {

    @GetMapping("/users")
    public String users(){
        return "users";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/merchandise")
    public String merchandise(){
        return "merchandise";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/text")
    public String text(){
        return "text";
    }
}
