package com.dawei.test.bootdemo.service.impl;

import com.dawei.test.bootdemo.entity.UserInfo;
import com.dawei.test.bootdemo.mapper.UserInfoMapper;
import com.dawei.test.bootdemo.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Dawei 2018/11/14
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfo userInfo;

    {
        userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setLocked(0);
        userInfo.setSalt("3fb62b5aeede1bbf");
        userInfo.setLoginName("jk");
        userInfo.setPassword("810339f5426fe2dcaf92c5cac718acc6471a034b");
        userInfo.setIcon("2");
        userInfo.setNickName("jk");
    }

    @Resource
    private UserInfoMapper userInfoMapper;


    @Override
    public UserInfo findUserByLoginName(String LoginName) {

        return userInfo;
    }


    @Override
    public UserInfo findUserById(Long id) {
        return userInfo;
    }


    @Override
    public Map<String, Integer> selectAllUserMenuMap() {
        return userInfoMapper.selectAllUserMenuMap();
    }
}
