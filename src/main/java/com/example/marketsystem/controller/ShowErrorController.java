package com.example.marketsystem.controller;

import com.example.marketsystem.utils.JosnData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ShowErrorController
 * 返回状态码等
 * @author chuyx
 * @date 2020/11/12 16:11
 */
@RestController
@RequestMapping("/pub")
public class ShowErrorController {
    /**
     * 需要登陆的
     */
    @RequestMapping("/needLogin")
    public JosnData needLogin(){
        return JosnData.buildError(-2,"温馨提示:您还未登录！请用对应账号登录");
    }

    @RequestMapping("/noPermission")
    public JosnData noPermission(){
        return JosnData.buildError(-3,"温馨提示:您不具有访问权限，需要请联系管理员赋权");
    }
}
