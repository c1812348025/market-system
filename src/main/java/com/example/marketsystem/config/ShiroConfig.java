package com.example.marketsystem.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /***
     * shiro 配置
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){


        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        //需要登录的接口，如果访问某个接口，需要登录却没登录，则调用此接口(如果不是前后端分离，则跳转页面)
        shiroFilterFactoryBean.setLoginUrl("/pub/needLogin");

        //登录成功，跳转url，如果前后端分离，则没这个调用
        shiroFilterFactoryBean.setSuccessUrl("/");

        //没有权限，未授权就会调用此方法， 先验证登录-》再验证是否有权限
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/noPermission");

        //设置自定义filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("roleOrFilter",new CustomRolesOrAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //拦截器路径，坑一，部分路径无法进行拦截，时有时无；因为同学使用的是hashmap, 无序的，应该改为LinkedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //退出过滤器
        filterChainDefinitionMap.put("/logout","logout");

        //匿名可以访问，也是就游客模式
        filterChainDefinitionMap.put("/api/v1/pub/**","anon");

        //登录用户才可以访问
        filterChainDefinitionMap.put("/api/v1/user/**","authc");

        //管理员角色才可以访问
        filterChainDefinitionMap.put("/api/v1/admin/**","roleOrFilter[admin,root]");

        //有编辑权限才可以访问
       // filterChainDefinitionMap.put("/video/update","perms[video_update]");


        //坑二: 过滤链是顺序执行，从上而下，一般讲/** 放到最下面

        //authc : url定义必须通过认证才可以访问
        //anon  : url可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //如果不是前后端分离，则不必设置下面的sessionManager
        securityManager.setSessionManager(sessionManager());

        //使用自定义cachmanager
        securityManager.setCacheManager(cacheManager());

        //设置realm（推荐放到最后，不然某些情况会不生效）
        securityManager.setRealm(customRealm());

        return securityManager;
    }


    /**
     * 自定义realm
     * @return
     */
    @Bean
    public ConstRealm customRealm(){
        ConstRealm constomRealm = new ConstRealm();

        constomRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return constomRealm;
    }


    /**
     * 密码加解密规则
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        //设置散列算法：这里使用的MD5算法
        credentialsMatcher.setHashAlgorithmName("md5");

        //散列次数，好比散列2次，相当于md5(md5(xxxx))
        credentialsMatcher.setHashIterations(2);

        return credentialsMatcher;
    }



    //自定义sessionManager
    @Bean
    public SessionManager sessionManager(){

        ConstomSessionManager customSessionManager = new ConstomSessionManager();
        //sessionmanager 实现session持久化
        customSessionManager.setSessionDAO(redisSessionDAO());
        return customSessionManager;
    }

    /**
     * redisMagger 配置
     */
    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        redisManager.setTimeout(60 * 60 * 1000);
        return redisManager;
    }
    /**
     * 配置cachmanager
     * 设置redis具体的cach实现类
     */
    public RedisCacheManager cacheManager(){
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(getRedisManager());
        return cacheManager;
    }
    /**
     *自定义session持久化
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        //设置自定义session
        redisSessionDAO.setSessionIdGenerator(new ConstomSessionIdGenerator());
    return redisSessionDAO;
    }

    /***
     * shiro 管理生命周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 加入注解的使用，不加入这个AOP注解不生效(shiro的注解 例如 @RequiresGuest)
     * api controller 层
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 用来扫描上下文寻找所有的Advistor(通知器),
     * 将符合条件的Advisor应用到切入点的Bean中，
     * 需要在LifecycleBeanPostProcessor创建后才可以创建
     *
     * @DependsOn 依赖于，当spring存在对应对象时，才会加载该类
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

}
