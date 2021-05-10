package com.tulun.dao;

import com.tulun.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public User getUserById(Integer id);

    public  User getUserByNameAndPwd(@Param("name") String name,@Param("pwd") String passwd);//多参数传递，绑定到注解上
}
