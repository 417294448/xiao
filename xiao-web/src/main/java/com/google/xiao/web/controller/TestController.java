/*
 * Copyright 2015 edaijia.cn All right reserved. 
 *
 */
package com.google.xiao.web.controller;

import com.google.gson.Gson;
import com.google.xiao.domain.TestModel;
import com.google.xiao.service.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;


/**
 * 首页广告位管理。
 * 
 * @author 广告位管理 2015年12月18日 上午10:38:22
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {


    @Autowired
    private ITestService testService;

    @Autowired
    private IRetryService retryService;

    @Autowired
    private IPerf4jService perf4jService;

    @Autowired
    private IRedisTest redisTest;

    @Autowired
    private IEhCacheService ehCacheService;

    @Autowired
    private EhCacheCacheManager cacheManager;


    /**
     * test lombok
     *
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public String getTest() {

        TestModel testModel = new TestModel();
        testModel.setAddress("shanghang");
        testModel.setName("xiao");
        return  new Gson().toJson(testModel);
    }

    /**
     * test service bean scan
     * @return
     */
    @RequestMapping(value = "/getHello", method = RequestMethod.GET)
    @ResponseBody
    public String getHello() {

        return testService.getHello();
    }

    /**
     * test retry
     * @return
     */
    @RequestMapping(value = "/getRetryHello", method = RequestMethod.GET)
    @ResponseBody
    public String getRetryHello() {

         retryService.retryPrintHello();
        return "hello" ;
    }

    /**
     * test retry
     * @return
     */
    @RequestMapping(value = "/getTestPerf4j", method = RequestMethod.GET)
    @ResponseBody
    public String getTestPerf4j() throws InterruptedException {
        log.info("log in controller start");
        perf4jService.getPerfLog();
        log.info("log in controller end");
        return "perf4j" ;
    }

    @RequestMapping(value = "/getRedisValue", method = RequestMethod.GET)
    @ResponseBody
    public String getRedisValue(){
        redisTest.setValue("mytest", "1111");
        return redisTest.getValue("mytest");
    }

    @RequestMapping(value = "/getRedisValue1", method = RequestMethod.GET)
    @ResponseBody
    public String getRedisValue1(){
        redisTest.setValue1("mytest", "1111");
        return redisTest.getValue1("mytest");
    }

    @RequestMapping(value = "/getEhcacheValue", method = RequestMethod.GET)
    @ResponseBody
    public String getEhcacheValue(){

        return ehCacheService.getTimestamp("1");
    }

    @RequestMapping(value = "/getTimestampNoCache", method = RequestMethod.GET)
    @ResponseBody
    public String getTimestampNoCache(){
        return ehCacheService.getTimestampNoCache("1");
    }

    @RequestMapping(value = "/getEhcacheValue1", method = RequestMethod.GET)
    @ResponseBody
    public String getEhcacheValue1(){
        cacheManager.getCache("myCache").put("111", "1a1a1a");
        return cacheManager.getCache("myCache").get("111").toString();
    }

}
