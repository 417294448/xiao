package com.google.xiao.service.impl;

import com.google.xiao.service.IPerf4jService;
import lombok.extern.slf4j.Slf4j;
import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;
import org.perf4j.aop.Profiled;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoshuang on 2017/7/24.
 */
@Service
@Slf4j
public class Perf4jService  implements IPerf4jService{

    @Profiled
    @Override
    public void getPerfLog() throws InterruptedException {

        log.info("this is start");
        for (int i = 0; i < 10; i++) {
            StopWatch watch = new Slf4JStopWatch("the " + i);
            Thread.sleep((long) (Math.random() * 10L));
            watch.lap("first block");
            Thread.sleep((long) (Math.random() * 20L));
            watch.stop("second block");
        }
        log.info("this is end");
    }
}
