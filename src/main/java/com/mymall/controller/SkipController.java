package com.mymall.controller;

import com.mymall.pojo.User;
import com.mymall.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author lxy
 */
@Controller
public class SkipController {
    @Autowired
    private TestService testService;
    @RequestMapping("/t")
    public String test(){
        System.out.println("表现层执行了");
        List<User> users = testService.test();
        for (User user : users) {
            System.out.println(user);
        }
        return "portal/view/user-login";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "portal/view/user-login";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "portal/view/user-register";
    }

    @RequestMapping("/index")
    public String index(){
        return "portal/view/index";
    }
    @RequestMapping("/forgotPassword")
    public String forgotPassword(){
        return "portal/view/user-pass-reset";
    }
}
