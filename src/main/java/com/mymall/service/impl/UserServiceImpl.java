package com.mymall.service.impl;

import com.mymall.commons.Const;
import com.mymall.commons.ServerResponse;
import com.mymall.commons.TokenCache;
import com.mymall.dao.UserMapper;
import com.mymall.pojo.User;
import com.mymall.service.IUserService;
import com.mymall.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author LoveYou
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {
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
        ServerResponse<String> resultName = checkValid(user.getUsername(), Const.USERNAME);
        if(!resultName.isSuccess()){
            return resultName;
        }
        //检查邮箱是否已被注册
        ServerResponse<String> resultEmail = checkValid(user.getEmail(), Const.EMAIL);
       if(!resultEmail.isSuccess()){
           return resultEmail;
       }
        user.setRole(Const.Role.ROLE_CUSTOMER);
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

    /**
     * 校验用户名或邮箱是否存在
     * @param str
     * @param type
     * @return
     */
    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        //判断是否位空字符
        if(StringUtils.isNotBlank(type)){
            //校验用户名
            if(Const.USERNAME.equals(type)){
                int resultName = userMapper.checkUsername(str);
                //返回错误信息
                if(resultName>0){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            //校验邮箱
            if(Const.EMAIL.equals(type)){
                int resultEmail = userMapper.checkEmail(str);
                //返回错误信息
                if(resultEmail>0){
                    return ServerResponse.createByErrorMessage("邮箱已注册");
                }
            }

        }else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验通过");
    }

    /**
     * 根据用户名查询找回密码问题
     * @param username
     * @return
     */
    @Override
    public ServerResponse selectQuestion(String username) {
        //校验用户名是否存在
        ServerResponse<String> checkValid = checkValid(username, Const.USERNAME);
        //用户信息不存在则返回信息
        if(checkValid.isSuccess()){
            return ServerResponse.createByErrorMessage("用户名输入有误");
        }
        //通过用户名查询问题
        String question = userMapper.selectQuestion(username);
        //判断是否为空
        if(StringUtils.isNotBlank(question)){
            //不为空
            return ServerResponse.createBySuccessMessage(question);
        }
        return ServerResponse.createByErrorMessage("没有设置找回密码问题");
    }

    /**
     * 校验问题答案并存储token
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        //校验用户名是否存在
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if(resultCount>0){
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题答案错误");
    }

    /**
     * 通过忘记密码修改密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @Override
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        //检查token
        if(StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("需要传递token");
        }
        ServerResponse<String> resultName = checkValid(username, Const.USERNAME);
        //用户不存在
        if(resultName.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        //获取Token
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if(StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("token错误");
        }
        //获取的token与缓存中的token判断
        if(StringUtils.equals(token,forgetToken)){
            //MD5加密
            String MD5password=MD5Util.MD5EncodeUtf8(passwordNew);
            int result = userMapper.updateByUsername(username, MD5password);
            if(result>0){
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        }else {
            return ServerResponse.createByErrorMessage("token无效或者过期");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    /**
     * 登录状态修改密码
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        //防止横向越权
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if(resultCount==0){
            //没有数据
            return ServerResponse.createByErrorMessage("密码输入有误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount>0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    /**
     * 更新个人信息
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> updateInformation(User user) {
        //检查邮箱是否有相同的
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if(resultCount>0){
            return ServerResponse.createByErrorMessage("邮箱已被注册");
        }
        //创建更新媒介
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if(updateCount > 0){
            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }

    /**
     * 获取个人信息
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<User> getInformation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);

    }

    /**
     * 判断是否是管理员
     * @param user
     * @return
     */
    @Override
    public ServerResponse checkAdminRole(User user) {
        if(user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
