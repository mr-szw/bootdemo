package com.dawei.test.bootdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author by Dawei on 2018/7/18.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /*
        添加自定义拦截器

    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    /*
        addViewControllers可以方便的实现一个请求直接映射成视图，而无需书写controller
        registry.addViewController("请求路径").setViewName("请求页面文件路径")
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // registry.addViewController("/index").setViewName("/webapp/views/indexPage");

    }


}
