package com.tulun.service;

import com.tulun.bean.User;
import com.tulun.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;//将 userMapper注入到当前service层

    public User getUserById (Integer id) {
        if (id < 0)
            return new User();
        User user = userMapper.getUserById(id);
        return user;
    }

    /**
     登录验证
       */
     public boolean checkLogin (String name,String pwd) {
         User user = userMapper.getUserByNameAndPwd(name,pwd);//从数据库里找有无user
         if (user != null) {
             return true;
         } else {
             return false;
         }
     }
 }
