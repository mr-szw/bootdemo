package com.dawei.test.bootdemo.config;

import com.dawei.test.bootdemo.intercecptors.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author by Dawei on 2018/7/18
 */
@Configuration
public class MyMvcConfig  implements WebMvcConfigurer {

    /* 添加自定义的拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/*/**");
    }

}
