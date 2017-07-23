/*
 * Copyright 2015 edaijia.cn All right reserved. 
 *
 */
package com.google.xiao.web.controller;

import com.google.gson.Gson;
import com.google.xiao.domain.TestModel;
import com.google.xiao.service.ITestService;
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
public class TestController {


    @Autowired
    private ITestService testService;

    /**
     * 获取所有有效的banner信息.
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

    @RequestMapping(value = "/getHello", method = RequestMethod.GET)
    @ResponseBody
    public String getHello() {

        return testService.getHello();
    }

}
