package com.dawei.test.bootdemo.service;

import com.dawei.test.bootdemo.entity.UserInfo;

import java.util.Map;

/**
 * @author Dawei 2018/11/14
 */

public interface UserInfoService {


    default UserInfo findUserByLoginName(String name) {return new UserInfo();}

    default UserInfo findUserById(Long id) {return new UserInfo();}

    default Integer saveUser(UserInfo userInfo) {return 0;}

    default Integer updateUser(UserInfo userInfo) {return 0;}

    //void saveUserRoles(Long id,Set<Role> roleSet);
/*
    void dropUserRolesByUserId(Long id);

    int userCount(String param);

    void deleteUser(UserInfo user);

    Map selectUserMenuCount();*/











}
