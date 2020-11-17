package com.example.marketsystem.controller;

import com.example.marketsystem.model.entity.Alluser;
import com.example.marketsystem.model.request.LoginRequest;
import com.example.marketsystem.service.ShiroGetMessageService;
import com.example.marketsystem.utils.JosnData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * UesrVerifcationController
 * 用于登录，注册等
 * @author chuyx
 * @date 2020/11/12 16:32
 */
@RestController
    @RequestMapping("/api/v1/pub/verif")
public class UesrVerifcationController {
    @Autowired
    private ShiroGetMessageService shiroGetMessageService;

    @PostMapping("/login")
    public JosnData login(@RequestBody(required = false) LoginRequest loginRequest){
        Map<String, Object> info = new HashMap<>();
        try{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getName(), loginRequest.getPwd());
            subject.login(token);
            Alluser user= shiroGetMessageService.findAllInfoByName(loginRequest.getName());
            info.put("msg","登陆成功");
            info.put("sessionId",subject.getSession().getId());
            info.put("data",user);
            return JosnData.buildSuccess(info);
        }catch (Exception e){
            e.printStackTrace();
            return JosnData.buildError(-1,"账号名或密码错误");
        }
    }
    @PostMapping("/logout")
    public JosnData logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal()!=null){
            return JosnData.buildSuccess("退出成功");
        }
        return JosnData.buildError(-2,"未检测到账号");
    }
}
