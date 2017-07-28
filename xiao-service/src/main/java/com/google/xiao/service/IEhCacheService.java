package com.google.xiao.service;

/**
 * Created by xiaoshuang on 2017/7/28.
 */

public interface IEhCacheService {

    public String getTimestamp(String param);

    public String getTimestampNoCache(String param);
}
