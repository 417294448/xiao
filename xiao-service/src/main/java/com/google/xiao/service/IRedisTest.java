package com.google.xiao.service;

/**
 * Created by xiaoshuang on 2017/7/26.
 * test
 */

public interface IRedisTest {

    public String getValue(String key);

    public void setValue(String key,String value);

    public String getValue1(String key);

    public void setValue1(String key,String value);
}
