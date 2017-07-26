package com.google.xiao.service;

/**
 * Created by xiaoshuang on 2017/7/26.
 * test
 */

public interface IRedisTest {

    public String getValue(String key);

    public void setValue(String key,String value);
}
