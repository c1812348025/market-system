package com.example.marketsystem.config;

import com.example.marketsystem.model.entity.AllPermission;
import com.example.marketsystem.model.entity.AllRole;
import com.example.marketsystem.model.entity.Alluser;
import com.example.marketsystem.service.ShiroGetMessageService;
import com.example.marketsystem.utils.BaseCache;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * ConstomRealm
 * shiro 权限控制的自定义Realm方法
 *
 * @author chuyx
 * @date 2020/11/10 17:53
 * @blame Android Team
 */
public class ConstRealm extends AuthorizingRealm {
    @Autowired
    private ShiroGetMessageService shiroGetMessageService;

    @Autowired
    private BaseCache baseCache;
    /**
     * 加密密钥
     */
    private static final String SECRET="wyclass.class";
    /**
     * 权限的时候会进入该方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Alluser  newuser = (Alluser) principalCollection.getPrimaryPrincipal();
        Alluser user= shiroGetMessageService.findAllInfoByName(newuser.getUserName());
        List<AllRole> roles = user.getRoleList();
        List<AllPermission> permissions = user.getPermissionList();
        List<String> role=new ArrayList<>();
        List<String> permission=new ArrayList<>();
        for (AllRole a: roles){
            if (a!=null){
                role.add(a.getRoleTage());
            }
        }
        for (AllPermission b: permissions){
            if (b!=null){
                permission.add(b.getPerTag());
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(role);
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /***
     * 登录会进入该方法 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从数据中获取uesrname pwd
        String username  = (String) token.getPrincipal();
        Alluser user= shiroGetMessageService.findUserByName(username);
        String pwd = user.getUserPwd();
        if (pwd==null){
            return null;
        }
        String salt = user.getUserSalt();
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        SimpleAuthenticationInfo info =  new SimpleAuthenticationInfo(user,user.getUserPwd(),credentialsSalt,this.getClass().getName());
        return info;
    }
}
