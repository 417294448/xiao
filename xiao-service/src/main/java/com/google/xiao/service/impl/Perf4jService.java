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
    @Override
    public void getPerfLog() throws InterruptedException {
        StopWatch watch = new Slf4JStopWatch("No class info");
        log.info("this is start");
        System.out.print("this is the content!");
        Thread.sleep(200L);
        log.info("this is end");

        printResults(100000,watch);
    }

    private void printResults(int count, StopWatch watch) {
        log.info("Test {} took {}ms (avg. {} ns/log)", new Object[]{
            watch.getTag(),
            watch.getElapsedTime(),
            watch.getElapsedTime() * 1000 * 1000 / count});
    }
}
