package com.tulun.controller;

import com.tulun.bean.Result;
import com.tulun.bean.User;
import com.tulun.config.Config;
import com.tulun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody //该注解是响应对象(以json的格式 可 返回不同类型的对象)
    public User testUser () {
         User user = userService.getUserById(1);
        return user;
    }


    /**
     * 登录操作，返回jsp页面
     */
    @RequestMapping("/login")
    public String login(){
        return "user/login";
    }

    /**
     * 验证登录信息
     */
    @RequestMapping("/checklogin")
    @ResponseBody
    public Result checklogin(User user, HttpSession session){
//        写一步验证一步
        System.out.println("登录："+user);
      //从数据库里找
        boolean checkLogin = userService.checkLogin(user.getName(), user.getPasswd());
        Result result = new Result("fail", "用户名或密码错误");
        if (checkLogin) {
            result = new Result("success","成功");
            //登录成功 记录 登录的缓存信息
            session.setAttribute(Config.LoginKey, user);
//            System.out.println("checklogin内部:"+result);
        }
        return result;
    }
}
