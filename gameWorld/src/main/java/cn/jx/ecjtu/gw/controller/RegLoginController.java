package cn.jx.ecjtu.gw.controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.Naming;

@RestController
@RequestMapping(value = "/regLogin")
public class RegLoginController {

    @GetMapping(value = "/test")
    public String Test(){
        return "Hello Vue";
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public Boolean login(String username,String password){

        return false;
    }
}
