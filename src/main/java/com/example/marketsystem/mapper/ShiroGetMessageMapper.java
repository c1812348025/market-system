package com.example.marketsystem.mapper;

import com.example.marketsystem.model.entity.AllPermission;
import com.example.marketsystem.model.entity.AllRole;
import com.example.marketsystem.model.entity.Alluser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShiroGetMessageMapper
 * shiro获取相关数据接口
 * @author chuyx
 * @date 2020/11/11 16:13
 */
@Repository
public interface ShiroGetMessageMapper {
    //根据用户名密码查询
    /*@Select("select * from all_user where uesr_name=#{username} and user_pwd = #{pwd}")*/
    Alluser findbyName(@Param("username") String name);

    //根据用户查角色
    List<AllRole> findRoleByUserId(@Param("userid") int userId);

    //根据用户id获取所有权限
    List<AllPermission> findPermissionByUserId(@Param("userid") int userid);

}
