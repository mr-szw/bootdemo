package com.dawei.test.bootdemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author Dawei 2018/11/16
 */
//@Mapper 使用这个注解 不需要单独的mapper文件直接使用注解写sql就可以啦

public interface UserInfoMapper {

    Map<String, Integer> selectAllUserMenuMap();




}
