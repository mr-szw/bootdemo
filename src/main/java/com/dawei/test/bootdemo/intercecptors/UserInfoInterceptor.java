package com.dawei.test.bootdemo.intercecptors;

import com.dawei.test.bootdemo.entity.ShiroUserInfo;
import com.dawei.test.bootdemo.entity.UserInfo;
import com.dawei.test.bootdemo.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dawei 2018/11/16
 */
@Component
public class UserInfoInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoInterceptor.class);

    /**
     * 用户数据 采用懒加载的方式
     */
    @Resource
    @Lazy
    private UserInfoService userInfoService;


    /**
     * 前置的拦截器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("PreHandle____ ");
        if (userInfoService == null) {
            WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            userInfoService = applicationContext.getBean(UserInfoService.class);
        }
        UserInfo userById = userInfoService.findUserById(ShiroUserInfo.getId());
        if (userById != null) {
            request.setAttribute("currentUser", userById);
            return true;
        }
        //不通过
        return false;
    }

    /**
     * 后置拦截器
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info("PostHandle____");
    }

    /**
     * 试图解析之后的
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info("AfterCompletion____");
    }
}
