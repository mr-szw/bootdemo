package com.dawei.test.bootdemo.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author by Dawei on 2018/8/10.
 * 分布式缓存Redsi
 */
@Repository
public class DistributedCache {


    @Resource
    private RedisTemplate redisTemplate;

    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }
    public boolean isExists(String key) {
        return redisTemplate.hasKey(key);
    }

    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
       redisTemplate.delete(key);
    }
}
