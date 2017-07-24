package com.google.xiao.service.impl;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import com.google.xiao.service.IRetryService;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/23.
 * 重试机制
 */
@Service
public class RetryService  implements IRetryService{

    public static final ThreadLocal localCount = new ThreadLocal();

    @Override
    public void retryPrintHello() {
        localCount.set(1L);
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfException()
                .retryIfResult(Predicates.equalTo(false))
                .withWaitStrategy(WaitStrategies.incrementingWait(1, TimeUnit.SECONDS,2,TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .build();
        try {
            retryer.call(buildTask());
        } catch (Exception e) {
            System.err.println("still failed after retry." + e.getCause().toString());
        }
    }

    private static long getCount() throws Exception {
        Long count = (Long) localCount.get();
        count += 1;
        System.out.println("hello" + count);
        localCount.set(count);
        return count;
    }
    private static Callable<Boolean> buildTask() {
        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                long tempCount = getCount();
                if (tempCount < 5) {
                    return false;
                }
                return true;
            }
        };
    }
}
