package com.example.marketsystem.controller;

import com.example.marketsystem.utils.JosnData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminDataMessageController
 * 管理员数据访问接口
 * @author chuyx
 * @date 2020/11/13 15:10
 */
@RestController
@RequestMapping("/api/v1/admin")
public class AdminDataMessageController {
    @RequestMapping("/admin")
    public JosnData test(){
     return JosnData.buildSuccess("成功");
    }
}
