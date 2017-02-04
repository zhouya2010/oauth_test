package com.sidu.controller;

import com.sidu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dell on 2017/1/21.
 */
@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("hello")
    public String test(){
        return "hello word";
    }


    @RequestMapping(value = "/login"    )
    public String showLoginForm(@RequestParam("username") String username, @RequestParam("password") String password) {

        userService.findByUsername(username);
        return "error";

//        Subject subject = SecurityUtils.getSubject() ;
//        UsernamePasswordToken token = new UsernamePasswordToken(username,password) ;
//        try {
//            subject.login(token);
//            return "admin" ;
//        }catch (Exception e){
//            //这里将异常打印关闭是因为如果登录失败的话会自动抛异常
//            return "error" ;
//        }
    }

}
