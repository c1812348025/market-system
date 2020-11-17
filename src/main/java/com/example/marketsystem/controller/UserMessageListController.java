package com.example.marketsystem.controller;

import com.example.marketsystem.utils.JosnData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * UserMessageListController
 * 返回用户信息集合(需要登陆)
 * @author chuyx
 * @date 2020/11/12 16:55
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserMessageListController {
    @RequestMapping("/userTheOrder")
    public JosnData userTheOrder(){
        Map<String, String> map = new HashMap<>();
        map.put("msg","成功了！");
        map.put("data","ddddd");
        return JosnData.buildSuccess(map);
    }
}
