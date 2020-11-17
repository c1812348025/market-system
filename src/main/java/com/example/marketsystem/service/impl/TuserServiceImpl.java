package com.example.marketsystem.service.impl;

import com.example.marketsystem.model.entity.Alluser;
import com.example.marketsystem.mapper.TuserMapper;
import com.example.marketsystem.model.request.LoginRequest;
import com.example.marketsystem.model.request.Registerrequest;
import com.example.marketsystem.service.TuserService;
import com.example.marketsystem.utils.BaseCache;
import com.example.marketsystem.utils.CommonUtils;
import com.example.marketsystem.utils.JWTUtils;
import com.example.marketsystem.utils.JosnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service("tuserservice")
@Transactional(rollbackFor=Exception.class)
public class TuserServiceImpl implements TuserService {
    @Autowired
    private TuserMapper tuserMapper;

    @Autowired
    private BaseCache baseCache;
    /**
     * 电话登录接口
     * @param
     * @param
     * @return
     */
    @Override
    public String phonelogin(LoginRequest loginRequest) {
        //信息提取
        String name = loginRequest.getName();
        String pwd = loginRequest.getPwd();
        String phone = loginRequest.getPhone();
        HashMap map = new HashMap();
        map.put("pwd",CommonUtils.MD5(pwd));
        map.put("phone",phone);
        Alluser tuser=tuserMapper.phonelogin(map);
        if(tuser==null) {
            return null;
        }else{
            String token = JWTUtils.geneJosnWebToken(tuser);
            return token;
        }
    }

    @Override
    public String userLogin(LoginRequest loginRequest) {
        //信息提取
        String name = loginRequest.getName();
        String pwd = loginRequest.getPwd();
        String phone = loginRequest.getPhone();
        HashMap map = new HashMap();
        map.put("name",name);
        map.put("pwd",CommonUtils.MD5(pwd));
        Alluser tuser=tuserMapper.userLogin(map);
        if(tuser==null) {
            return null;
        }else{
            String token = JWTUtils.geneJosnWebToken(tuser);
            return token;
        }
    }

    /**
     * 注册接口
     * @param registerrequest
     * @return
     */
    @Override
    public JosnData register(Registerrequest registerrequest) {
        //注册前先判断是否存在手机号和相同的账号
        String phone = registerrequest.getPhone();
        String name = registerrequest.getName();
        String pwd= registerrequest.getPwd();
        HashMap map = new HashMap();
        map.put("phone",phone);
        //手机号验证
        int a = tuserMapper.verificationTuser(map);
        if(a!=0){
            return JosnData.buildError(-1,"手机号已被注册！");
        }else {
            map.put("name",name);
            //账号验证
            a = tuserMapper.verificationTuser(map);
            if(a!=0){
                return JosnData.buildError(-1,"账号已被占用！");
            }else {
                    map.put("pwd", CommonUtils.MD5(pwd));
                    try {
                        tuserMapper.InserTuser(map);
                        return JosnData.buildError("注册成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                        return JosnData.buildError(-1, "注册失败");
                    }
                  }
             }
    }
}
