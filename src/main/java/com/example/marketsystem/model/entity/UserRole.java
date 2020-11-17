package com.example.marketsystem.model.entity;

import lombok.Data;

/**
 * UserRole
 * 用户角色对应实体
 * @author chuyx
 * @date 2020/11/11 16:02
 */
@Data
public class UserRole {
    //角色用户对应id
    private Integer userRoleId;
    //用户id
    private Integer userId;
    //角色标识
    private String roleTage;
}
