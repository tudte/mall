package com.mymall.dao;

import com.mymall.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from mmall_user")
    List<User> text();
}
