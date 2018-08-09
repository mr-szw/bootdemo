package com.dawei.test.bootdemo.cache;


import com.dawei.test.bootdemo.pojo.DemoPojo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author by Dawei on 2018/8/10.
 * 本地缓存 caffeine
 * 与应用 同步存活
 */
@Service
public class LocalCacheCaffeine {


    @CachePut(key = "#demoPojo.uId", value = "demoPojo")
    public void save(DemoPojo demoPojo) {

    }

    @Cacheable(key = "#uId", value = "demoPojo")
    public DemoPojo get(Long uId) {
        DemoPojo demoPojo = new DemoPojo();
        return  demoPojo;
    }

    @CacheEvict(key = "#uId", value = "demoPojo")
    public void delete() {

    }

}
