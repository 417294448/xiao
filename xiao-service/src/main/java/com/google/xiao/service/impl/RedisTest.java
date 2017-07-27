package com.google.xiao.service.impl;

import com.google.xiao.service.IRedisTest;
import com.google.xiao.service.redis.RedisService;
import com.google.xiao.service.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoshuang on 2017/7/26.
 */
@Service
public class RedisTest implements IRedisTest{
    @Autowired
    RedisUtils redisUtil;

    @Autowired
    RedisService redisService;

    @Override
    public String getValue(String key) {
        return redisService.get(key);
    }

    @Override
    public void setValue(String key, String value) {
         redisService.set(key,value);
    }

    @Override
    public String getValue1(String key) {
        return redisUtil.get(key);
    }

    @Override
    public void setValue1(String key, String value) {
        redisUtil.set(key,value);
    }

}
