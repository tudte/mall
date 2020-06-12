package com.mymall.controller;

import com.mymall.commons.Conts;
import com.mymall.commons.ServerResponse;
import com.mymall.pojo.User;
import com.mymall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 *用户模块
 * @author LoveYou
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Conts.CURRENT_USER,response.getData());
        }
        return response;
    }

    /**
     * 退出功能
     * @param session
     * @return
     */
    @RequestMapping(value = "loginOut.do" ,method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> loginOut(HttpSession session){
        session.removeAttribute(Conts.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 注册功能
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user){
        /*System.out.println(user);*/
        return iUserService.register(user);
        /*return ServerResponse.createBySuccessMessage("注册成功");*/
    }

    /**
     * 通过用户名查询找回密码问题
     * @return
     */
    @RequestMapping(value = "selectQuestion.do" ,method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse selectQuestion(String username){
        return iUserService.selectQuestion(username);
    }

}
