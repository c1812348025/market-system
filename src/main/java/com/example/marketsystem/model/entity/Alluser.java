package com.example.marketsystem.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Alluser
 * 个人用户实体类
 *
 * @blame Android Team
 */
@Data
public class Alluser  implements Serializable {

    private Integer Id;//id

    private String userName;//名字
    @JSONField(serialize = false)
    private String userPwd;//密码

    private String uesrHandline;//用户个性签名

    private String userPhone;//电话

    private Double userMoney;//金额

    private String userSalt;//盐

    private List<AllRole> roleList;//角色列表

    private List<AllPermission> permissionList;//权限列表

}
