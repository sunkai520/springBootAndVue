package com.sunkai.test.config;

import com.sunkai.test.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 配置拦截器 将拦截器添加到spring中
 * **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**") //拦截所有请求
                .excludePathPatterns("/login","/upload","/images/**","/getBkList","/getBkInfo","/error","/getTagList","/"); //除了login接口之外
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        System.out.println(System.getProperty("user.dir")+"2=-3=-=");
        //配置静态资源访问映射 若不配置要访问已经上传的图片会出现访问不了或者重启服务器才可访问的问题
        String path = System.getProperty("user.dir")+"/images/";
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+path);
    }
}

