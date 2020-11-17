package com.example.marketsystem.model.entity;

import lombok.Data;

/**
 * RolePermission
 * 角色权限对应实体
 * @author chuyx
 * @date 2020/11/11 16:06
 */
@Data
public class RolePermission {
    //角色权限对应id
    private Integer rolePerId;
    //角色标识
    private String roleTage;
    //权限标识
    private String perTage;

}
