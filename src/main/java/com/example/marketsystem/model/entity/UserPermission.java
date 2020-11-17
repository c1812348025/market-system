package com.example.marketsystem.model.entity;

import lombok.Data;

/**
 * UserPermission
 * 用户权限对应实体
 * @author chuyx
 * @date 2020/11/11 16:08
 */
@Data
public class UserPermission {
    //用户权限对应id
    private Integer userPerId;
    //用户id
    private Integer userId;
    //权限标识
    private String perTage;

}
