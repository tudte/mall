package com.mymall.dao;

import com.mymall.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author LoveYou
 */
public interface UserMapper {
    /**
     * deleteByPrimaryKey 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert 增加user
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * insertSelective 增加user null不写sql
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * selectByPrimaryKey 根据id查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective 更新用户信息 null不写sql
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * updateByPrimaryKey 更新用户信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * checkUsername 检查用户名是否存在
     * @param username
     * @return
     */
    int checkUsername(String username);

    /**
     * 检查邮箱是否已被注册
     * @param email
     * @return
     */
    int checkEmail(String email);

    /**
     * selectLogin 查询用户名密码是否正确
     * @param username
     * @param password
     * @return
     */
    User selectLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询问题
     * @param username
     * @return
     */
    String selectQuestion(String username);

    /**
     * 查询用户是否输入正确
     * @param username
     * @param question
     * @param answer
     * @return
     */
    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);

    /**
     * 忘记密码的修改密码
     * @param username
     * @param password
     * @return
     */
    int updateByUsername(@Param("username")String username,@Param("password")String password);

    /**
     * 检查用户密码
     * @param password
     * @param id
     * @return
     */
    int checkPassword(@Param("password")String password,@Param("id")Integer id);

    /**
     * 检查邮箱是否被注册
     * @param email
     * @param id
     * @return
     */
    int checkEmailByUserId(@Param("email")String email,@Param("id")Integer id);
}