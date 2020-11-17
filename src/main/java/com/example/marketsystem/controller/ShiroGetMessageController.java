package com.example.marketsystem.controller;

import com.example.marketsystem.model.entity.Alluser;
import com.example.marketsystem.service.ShiroGetMessageService;
import com.example.marketsystem.utils.JosnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ShiroGetMessageController
 * shiro获取权限角色
 * @author chuyx
 * @date 2020/11/12 10:59
 */
@RestController
@RequestMapping("/pub/shiro")
public class ShiroGetMessageController {

    @Autowired
    private ShiroGetMessageService shiroGetMessageService;

    @RequestMapping("/findAllInfoByNameAndPwd")
    public JosnData findAllInfoByNameAndPwd(String name ,String pwd) {
        Alluser user = shiroGetMessageService.findAllInfoByName(name);
        if (user == null) {
            return JosnData.buildError(-1, "用户详细信息获取出错！");
        }
        return JosnData.buildSuccess(user);
    }
    @RequestMapping("/findUserByNameAndPwd")
    public JosnData findUserByNameAndPwd(String name,String pwd){
        Alluser user = shiroGetMessageService.findUserByName(name);
        if (user == null) {
            return JosnData.buildError(-1, "用户信息获取出错！");
        }
        return JosnData.buildSuccess(user);
    }
}

