package com.dawei.test.bootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.dawei.test.bootdemo.constants.ConstantsConfig;
import com.dawei.test.bootdemo.utils.RestResponse;
import com.dawei.test.bootdemo.utils.VerifyCodeUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author by Dawei on 2018/11/14.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     *  空地址请求
     */
    @GetMapping(value = "/")
    public String index() {
        logger.info("根路径 請求-------");
        Subject subject = SecurityUtils.getSubject();
        /* 认证过 则进去首页 否则跳登陆 */
        return subject.isAuthenticated() ? "redirect:index" : "login";
    }

    /**
     * 登陆校验
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        logger.info("来源路径>>>>>>> {}", requestURI);
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            if(requestURI.contains("index")) {
                requestURI = "index";
            }
            return "redirect:" + requestURI;
        }else {
            return "login";
        }
    }

    /**
     * 登陆的主函数
     */
    @PostMapping("/login/main")
    @ResponseBody
    public RestResponse loginMain(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String code = request.getParameter("code");
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return RestResponse.failure("用户名或者密码不能为空");
        }
        if(StringUtils.isBlank(rememberMe)){
            return RestResponse.failure("记住我不能为空");
        }
        if(StringUtils.isBlank(code)){
            return  RestResponse.failure("验证码不能为空");
        }

        HttpSession session = request.getSession();
        if(session == null){
            return RestResponse.failure("session超时");
        }
        String trueCode =  (String)session.getAttribute(ConstantsConfig.VALIDATE_CODE);
        if(StringUtils.isBlank(trueCode)){
            return RestResponse.failure("验证码超时");
        }
        if(StringUtils.isBlank(code) || !trueCode.toLowerCase().equals(code.toLowerCase())){
            return RestResponse.failure("验证码错误");
        }
        String errorMsg = null;
        Map<String,Object> resultMap = Maps.newHashMap();
            /*就是代表当前的用户。*/
            Subject userSubject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,password,Boolean.valueOf(rememberMe));
            try {
                userSubject.login(token);
                /* 登陆 认证成功 */
                if (userSubject.isAuthenticated()) {
                    resultMap.put("url","index");
                }
            }catch (IncorrectCredentialsException e) {
                errorMsg = "登录密码错误.";
            } catch (ExcessiveAttemptsException e) {
                errorMsg = "登录失败次数过多";
            } catch (LockedAccountException e) {
                errorMsg = "帐号已被锁定.";
            } catch (DisabledAccountException e) {
                errorMsg = "帐号已被禁用.";
            } catch (ExpiredCredentialsException e) {
                errorMsg = "帐号已过期.";
            } catch (UnknownAccountException e) {
                errorMsg = "帐号不存在";
            } catch (UnauthorizedException e) {
                errorMsg = "您没有得到相应的授权！";
            }
        if(StringUtils.isBlank(errorMsg)){
            return RestResponse.success("登录成功").setData(resultMap);
        }else{
            return RestResponse.failure(errorMsg);
        }
    }


    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     */
    @GetMapping("/genCaptcha")
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(ConstantsConfig.VALIDATE_CODE, verifyCode);
        logger.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249,205,173), null, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    /**
     * 登陆成功转到首页
     */
    @GetMapping(value = "/index")
    public String toIndex() {
        return "index";
    }

    /**
     * 登出
     */
    @GetMapping("systemLogout")
    public String logOut(){
        logger.info("登出系统————");
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }
}

