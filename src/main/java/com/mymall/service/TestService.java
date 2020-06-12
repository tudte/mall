package com.mymall.service;

import com.mymall.dao.UserDao;
import com.mymall.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private UserDao userDao;

    public List<User> test(){
        System.out.println("业务层执行了");
        return userDao.text();
    }
}
