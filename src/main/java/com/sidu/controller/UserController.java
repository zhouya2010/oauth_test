package com.sidu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2017/1/21.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("hello")
    public String test(){
        return "hello word";
    }

}
