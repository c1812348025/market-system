package com.example.marketsystem.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * AllPermission
 * 权限实体
 * @author chuyx
 * @date 2020/11/11 15:58
 */
@Data
public class AllPermission implements Serializable {
    //权限id
    private Integer perId;
    //权限名称
    private String perName;
    //权限标识
    private String perTag;
    //权限路径sss
    private String perUrl;
    //上级菜单存tag
    private String perSuperior;
    //菜单级别
    private String perRank;

}
