package com.mymall.service;

import com.mymall.commons.ServerResponse;
import com.mymall.pojo.User;

/**
 * @author LoveYou
 */
public interface IUserService {
    /**
     * login 登录功能
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(String username, String password);

    /**
     * register 注册功能
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * checkValid 校验用户名、邮箱的有效性
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValid(String str,String type);

    ServerResponse selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse checkAdminRole(User user);
}
