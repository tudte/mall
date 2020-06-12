package com.mymall.service.impl;

import com.mymall.commons.Conts;
import com.mymall.commons.ServerResponse;
import com.mymall.dao.UserMapper;
import com.mymall.pojo.User;
import com.mymall.service.IUserService;
import com.mymall.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LoveYou
 */
@Service("iUserService")
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<User> login(String username, String password) {
        //检查用户名是否存在
        int resultCount = userMapper.checkUsername(username);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //密码MD5
        String MD5password = MD5Util.MD5EncodeUtf8(password);
        System.out.println(MD5password);
        //检查用户名密码是否正确
        User user = userMapper.selectLogin(username, MD5password);
        if(user==null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        //设置用户的密码为空
        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createBySuccess("登录成功",user);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> register(User user) {
        //检查用户名是否存在
        int resultName = userMapper.checkUsername(user.getUsername());
        //不存在，返回错误信息
        if(resultName>0){
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        //检查邮箱是否已被注册
        int resultEmail = userMapper.checkEmail(user.getEmail());
        //已被注册，返回错误信息
        if(resultEmail>0){
            return ServerResponse.createByErrorMessage("邮箱已注册");
        }
        user.setRole(Conts.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        //存入用户数据
        int insert = userMapper.insert(user);
        //存入用户数据失败
        if(insert==0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        //判断是否位空字符
        if(!StringUtils.isBlank(type)){
            //校验用户名
            if(Conts.USERNAME.equals(type)){
                int resultName = userMapper.checkUsername(str);
                //返回错误信息
                if(resultName>0){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            //校验邮箱
            if(Conts.EMAIL.equals(type)){
                int resultEmail = userMapper.checkEmail(str);
                //返回错误信息
                if(resultEmail>0){
                    return ServerResponse.createByErrorMessage("邮箱已注册");
                }
            }

        }
        return ServerResponse.createBySuccessMessage("有效");
    }

    @Override
    public ServerResponse selectQuestion(String username) {
        //校验用户名是否存在
        ServerResponse<String> checkValid = checkValid(username, Conts.USERNAME);

        //通过用户名查询问题
        String question = userMapper.selectQuestion(username);
        if(StringUtils.isNotEmpty(question)){
            //不为空
            ServerResponse.createBySuccessMessage(question);
        }
        return ServerResponse.createByErrorMessage("没有设置找回密码问题");
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        return null;
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        return null;
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        return null;
    }

    @Override
    public ServerResponse<User> updateInformation(User user) {
        return null;
    }

    @Override
    public ServerResponse<User> getInformation(Integer userId) {
        return null;
    }

    @Override
    public ServerResponse checkAdminRole(User user) {
        return null;
    }
}
