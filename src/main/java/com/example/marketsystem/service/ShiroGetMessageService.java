package com.example.marketsystem.service;

import com.example.marketsystem.model.entity.Alluser;
import org.springframework.stereotype.Service;

/**
 * ShiroGetMessageService
 * shiro 权限 角色
 * @author chuyx
 * @date 2020/11/12 11:02
 */
@Service
public interface ShiroGetMessageService {
    /**
     * 获取用户全部信息
     * @param name
     * @return
     */
    Alluser findAllInfoByName(String name);

    /**
     * 获取用户简单信息
     * @param name
     * @return
     */
    Alluser findUserByName(String name);

}
