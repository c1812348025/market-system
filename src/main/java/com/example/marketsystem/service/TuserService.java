package com.example.marketsystem.service;

import com.example.marketsystem.model.request.LoginRequest;
import com.example.marketsystem.model.request.Registerrequest;
import com.example.marketsystem.utils.JosnData;
import org.springframework.stereotype.Service;

@Service
public interface TuserService {

    
//注册
    JosnData register(Registerrequest registerrequest);
//登录
   String phonelogin(LoginRequest loginRequest);

    String userLogin(LoginRequest loginRequest);
}
