package com.example.marketsystem.model.request;

import lombok.Data;

@Data
public class Registerrequest {
    private Integer id;//id

    private String name;//名字

    private String pwd;//密码

    private String phone;//电话

    private Double money;//金额

    private Integer role;//角色
}
