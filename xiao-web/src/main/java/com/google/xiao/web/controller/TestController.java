/*
 * Copyright 2015 edaijia.cn All right reserved. 
 *
 */
package com.google.xiao.web.controller;

import com.google.gson.Gson;
import com.google.xiao.domain.TestModel;
import com.google.xiao.service.IPerf4jService;
import com.google.xiao.service.IRedisTest;
import com.google.xiao.service.IRetryService;
import com.google.xiao.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
}
