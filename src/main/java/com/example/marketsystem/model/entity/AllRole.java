package com.example.marketsystem.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * AllRole
 * 角色实体
 * @author chuyx
 * @date 2020/11/11 15:55
 */
@Data
public class AllRole implements Serializable {
    //角色id
    private Integer roleId;
    //角色名称
    private String roleName;
    //角色标识
    private String roleTage;
    //角色描述
    private String roleDiscription;
    //权限列表
    private List<AllPermission> permissionList;
}
