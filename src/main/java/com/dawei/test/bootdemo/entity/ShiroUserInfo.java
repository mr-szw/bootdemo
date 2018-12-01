package com.dawei.test.bootdemo.entity;

import com.dawei.test.bootdemo.realm.AuthInfoRealm;
import org.apache.shiro.SecurityUtils;

/**
 * @author Dawei 2018/11/16
 */
public class ShiroUserInfo {

    /**
     * 去除当前用户的 icon
     */
    public static String getIcon() {
        return ShiroUser().getIcon();
    }

    public static Long getId() {
        return ShiroUser().getId();
    }

    public static String loginName() {
        return ShiroUser().getLoginName();
    }

    public static String nickName() {
        return ShiroUser().getNickName();
    }

    public static AuthInfoRealm.ShiroUser ShiroUser() {
        return (AuthInfoRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }


}
