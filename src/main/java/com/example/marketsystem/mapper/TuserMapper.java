package com.example.marketsystem.mapper;

import com.example.marketsystem.model.entity.Alluser;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface TuserMapper {
    /**
     * 电话登录接口
     * @param
     * @param
     * @param map
     * @return
     */
    Alluser phonelogin(HashMap map);
    /**
     * 账号密码登录
     * @param map
     * @return
     */
    Alluser userLogin(HashMap map);
    /**
     * 注册接口
     * @param map
     */
    void InserTuser(HashMap map);

    /**
     * 效验账号占用
     * @return
     */
    int verificationTuser(HashMap map);


}
