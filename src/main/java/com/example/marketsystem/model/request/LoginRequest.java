package com.example.marketsystem.model.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String phone;
    private String pwd;
    private String name;
}
