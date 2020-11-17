package com.example.marketsystem.service.impl;

import com.example.marketsystem.config.CacheKeyManger;
import com.example.marketsystem.mapper.ShiroGetMessageMapper;
import com.example.marketsystem.model.entity.AllPermission;
import com.example.marketsystem.model.entity.AllRole;
import com.example.marketsystem.model.entity.Alluser;
import com.example.marketsystem.service.ShiroGetMessageService;
import com.example.marketsystem.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ShiroGetMessageServiceImpl
 * shiro 权限 角色
 * @author chuyx
 * @date 2020/11/12 11:03
 */
@Service("shiroGetMessageService")
@Transactional(rollbackFor=Exception.class)
public class ShiroGetMessageServiceImpl implements ShiroGetMessageService {

    @Autowired
    private ShiroGetMessageMapper shiroGetMessageMapper;
    @Autowired
    private BaseCache baseCache;

    /**
     * 获取用户所有信息
     * @param name
     * @return
     */
    @Override
    public Alluser findAllInfoByName(String name) {
        try{
            Object BaseCache = baseCache.getTenMinuteCache().get(CacheKeyManger.getAll_userinfo_key,()->{
                Alluser user1 = shiroGetMessageMapper.findbyName(name);
                return user1;
            });
            Alluser user = null;
            if (BaseCache instanceof Alluser) {
                 user = (Alluser) BaseCache;
            }
        if (user!=null) {
            List<AllRole> roles = shiroGetMessageMapper.findRoleByUserId(user.getId());
            List<AllPermission> permissions = shiroGetMessageMapper.findPermissionByUserId(user.getId());
            user.setRoleList(roles);
            user.setPermissionList(permissions);
        }
        return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户简单信息
     * @param name
     * @return
     */
    @Override
    public Alluser findUserByName(String name) {
        try{
            Object BaseCache = baseCache.getTenMinuteCache().get(CacheKeyManger.getonly_user_key,()->{
                Alluser user1 = shiroGetMessageMapper.findbyName(name);
                return user1;
            });
            Alluser user = null;
            if (BaseCache instanceof Alluser) {
                user = (Alluser) BaseCache;
            }
        return user;
        }catch (Exception e){

        }
        return null;
    }
}
