package com.dawei.test.bootdemo.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dawei 2018/11/14
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * 覆盖默认实现，用sendRedirect直接跳出框架，以免造成js框架重复加载js出错。
     *
     * @see FormAuthenticationFilter#onLoginSuccess(AuthenticationToken, Subject, ServletRequest, ServletResponse)
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + this.getSuccessUrl());
        return true;
    }
}
