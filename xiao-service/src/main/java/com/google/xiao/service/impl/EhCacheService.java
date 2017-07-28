package com.google.xiao.service.impl;

import com.google.xiao.service.IEhCacheService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoshuang on 2017/7/28.
 */
@Service
public class EhCacheService implements IEhCacheService{

    /*
        @Cacheable  支持condition设置条件；key指定key的生成规则
        @CacheEvict 与@Cacheable功能相反，@CacheEvict表明所修饰的方法是用来删除失效或无用的缓存数据。

        @CachePut
        @Caching
        @CacheConfig
    */

    @Cacheable(value="myCache",key="#param")
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }

    @CacheEvict(value="myCache",key="#param")
    public String getTimestampNoCache(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }
}
