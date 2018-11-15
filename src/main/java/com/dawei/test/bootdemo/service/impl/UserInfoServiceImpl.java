package com.dawei.test.bootdemo.service.impl;

import com.dawei.test.bootdemo.entity.UserInfo;
import com.dawei.test.bootdemo.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author Dawei 2018/11/14
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public UserInfo findUserByLoginName(String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setLocked(0);
        userInfo.setSalt("3fb62b5aeede1bbf");
        userInfo.setLoginName("jk");
        userInfo.setPassword("810339f5426fe2dcaf92c5cac718acc6471a034b");
        userInfo.setIcon("2");
        userInfo.setNickName("jk");
        return userInfo;
    }
}
