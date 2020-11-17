package com.example.marketsystem.interceptor;

import com.example.marketsystem.utils.JWTUtils;
import com.example.marketsystem.utils.JosnData;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;

import io.jsonwebtoken.Claims;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 进controller层之前的方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
        String accesToken = request.getHeader("token");
        if(accesToken == null){
            accesToken= request.getParameter("token");
        }
        if(StringUtils.isNotBlank(accesToken)){
            String atten="chuyx.class";
            if (accesToken.indexOf(atten)==-1&&accesToken.indexOf("l-p1")==-1){
                sendJosnMassage(response, JosnData.buildError("登陆过期！重新登陆"));
                return false;
            }
            return true;
        }
        }catch (Exception e){}
        sendJosnMassage(response, JosnData.buildError("登陆过期！重新登陆"));
        return false;
    }

    /**
     * 相应Josn数据给前端
     * @param response
     * @param obj
     */
    public static void sendJosnMassage(HttpServletResponse response , Object obj){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/josn; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();
        }catch (Exception  e){
            e.printStackTrace();
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
