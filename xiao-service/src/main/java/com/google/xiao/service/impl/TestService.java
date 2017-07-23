package com.google.xiao.service.impl;

import com.google.xiao.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/23.
 */
@Service
public class TestService implements ITestService{
    @Override
    public String getHello() {
        return "hello";
    }
}
