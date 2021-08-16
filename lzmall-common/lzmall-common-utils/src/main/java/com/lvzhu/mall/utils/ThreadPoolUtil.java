package com.lvzhu.mall.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:06
 * Desc 文件描述
 */
public class ThreadPoolUtil {

    public static ThreadPoolExecutor createPool(int coreSize, int maxSize, ThreadRejectPolicyType rejectType){
       return createPool(coreSize, maxSize, "lvzhu-common-pool-%d",rejectType);
    }

    public static ThreadPoolExecutor createPool(int coreSize, int maxSize, String threadNameFormat, ThreadRejectPolicyType rejectType){
        ThreadPoolExecutor threadPoolExecutor = null;

        switch (rejectType){
            case ABORT:
                threadPoolExecutor =  new FrameworkThreadPoolExecutor(coreSize, maxSize, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<>(1),
                        new ThreadFactoryBuilder().setNameFormat(threadNameFormat).build(),
                        new ThreadPoolExecutor.AbortPolicy());
                break;
            case CALLER_RUNS:
                threadPoolExecutor =  new FrameworkThreadPoolExecutor(coreSize, maxSize, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<>(1),
                        new ThreadFactoryBuilder().setNameFormat(threadNameFormat).build(),
                        new ThreadPoolExecutor.CallerRunsPolicy());
                break;
            default:
                throw new IllegalArgumentException("当前拒绝策略不支持");
        }
        return threadPoolExecutor;
    }


    public static ThreadPoolExecutor createPool(int coreSize, int maxSize, BlockingQueue<Runnable> workQueue, String threadNameFormat, ThreadRejectPolicyType rejectType){
        return new FrameworkThreadPoolExecutor(coreSize, maxSize, 0L, TimeUnit.MILLISECONDS,
                workQueue,
                new ThreadFactoryBuilder().setNameFormat(threadNameFormat).build(),
                getRejectedExecutionHandlerByType(rejectType));
    }

    private static RejectedExecutionHandler getRejectedExecutionHandlerByType(ThreadRejectPolicyType rejectType){
        RejectedExecutionHandler rejectedExecutionHandler;
        switch (rejectType){
            case ABORT:
                rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
                break;
            case CALLER_RUNS:
                rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
                break;
            default:
                throw new IllegalArgumentException("当前拒绝策略不支持");
        }
        return rejectedExecutionHandler;
    }

}
