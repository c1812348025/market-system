package com.example.marketsystem.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.marketsystem.interceptor.CorsInterceptor;
import com.example.marketsystem.interceptor.LoginInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }

    /**
     * 序列化
     * @return
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.valueOf(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"));
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        fastJsonHttpMessageConverter.setFeatures(SerializerFeature.QuoteFieldNames, SerializerFeature.WriteMapNullValue);
        return fastJsonHttpMessageConverter;
    }
    /**
     * 拦截器配置
     * 需要权限访问 api/v1/pri/
     *不需要权限访问 api/v1/pub/
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截全部路径，跨域需要放在最上层 跨域拦截器
         */
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**")
        //拦截器优先级，越低越优先
        .order(1);

        /**
         * 登录拦截器
         */
        //拦截路径
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/*/*/*/*/**")
                //不拦截哪些
              .excludePathPatterns("/api/v1/pri/tuser/**","/api/v1/pri/tuser/verif/**","/api/v1/pub/*/**","/pub/shiro/*/**")
        .order(2);


    }
}
