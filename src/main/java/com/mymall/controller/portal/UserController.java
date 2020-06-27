package com.mymall.controller.portal;

import com.mymall.commons.Const;
import com.mymall.commons.ResponseCode;
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
            session.setAttribute(Const.CURRENT_USER,response.getData());
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
        session.removeAttribute(Const.CURRENT_USER);
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
     * 获取当前用户的信息
     * @param session
     * @return
     */
    @RequestMapping(value = "getInfo.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user!=null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录");
    }

    /**
     * 通过用户名查询找回密码问题
     * @return
     */
    @RequestMapping(value = "forgetQuestion.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetQuestion(String username){
        return iUserService.selectQuestion(username);
    }

    /**
     * 校验回答是否正确
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "checkAnswer.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username, question, answer);
    }

    /**
     * 通过忘记密码修改密码
     * @param username
     * @param password
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "forgetResetPassword.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username,String password,String forgetToken){
        return iUserService.forgetResetPassword(username, password, forgetToken);
    }

    /**
     * 登录状态修改密码
     * @param session
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "resetPassword.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }

    /**
     * 更新个人信息
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "updateInformation.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(HttpSession session,User user){
        //查询是否处于登录状态下
        User sessionUser = (User) session.getAttribute(Const.CURRENT_USER);
        if(sessionUser==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        //用户名和id不允许改变
        user.setId(sessionUser.getId());
        user.setUsername(sessionUser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if(response.isSuccess()){
            response.getData().setUsername(sessionUser.getUsername());
            //存储到session域对象中
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
    @RequestMapping(value = "getInformation.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInformation(HttpSession session){
        //获取session中的信息
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status=10");
        }
        return iUserService.getInformation(currentUser.getId());
    }

}
