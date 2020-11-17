package com.example.marketsystem.controller;

import com.example.marketsystem.model.request.LoginRequest;
import com.example.marketsystem.model.request.Registerrequest;
import com.example.marketsystem.service.TuserService;
import com.example.marketsystem.utils.JosnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/pri/tuser")
public class TuserController {
    @Autowired
    private TuserService tuserservice;

    /**
     * 电话登录接口
     * @param loginRequest
     * @return
     */
    @PostMapping("/Phonelogin")
    public JosnData Phonelogin(@RequestBody(required = false) LoginRequest loginRequest)throws ExecutionException {
        String token = tuserservice.phonelogin(loginRequest);
        return token ==null ?JosnData.buildError(-1,"登录失败！手机号或密码错误"): JosnData.buildSuccess(token);
    }
    /**
     * 电话登录接口
     * @param loginRequest
     * @return
     */
    @PostMapping("/Userlogin")
    public JosnData Userlogin(@RequestBody(required = false) LoginRequest loginRequest)throws ExecutionException {
        String token = tuserservice.userLogin(loginRequest);
        return token ==null ?JosnData.buildError(-1,"登录失败！账号或密码错误"): JosnData.buildSuccess(token);
    }

    /**
     * 注册接口
     * @param registerrequest
     * @return
     */
    @RequestMapping("register")
    public JosnData getTuser(@RequestBody Registerrequest registerrequest){
        JosnData josnData = tuserservice.register(registerrequest);
        return josnData ;
    }

}
