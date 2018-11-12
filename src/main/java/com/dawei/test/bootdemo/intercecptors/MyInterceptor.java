package com.dawei.test.bootdemo.intercecptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by Dawei on 2018/7/18.
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    /* 方法将在请求处理之前进行调用 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("prdHandle …… ……");
        return true;
    }


    /*
        在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行
        这个方法的主要作用是用于进行资源清理工作的
    */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info("afterCompletion …… ……");
    }


    /* 在当前请求进行处理之后，也就是Controller 方法调用之后执行
    *   preHandle 方法的返回值为true 时才能被调用\
    */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info("postHandle …… ……");
    }


}
