package com._520it.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lyhzzz
 * @date 2017/11/7
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){

        return "/login";
    }
}
