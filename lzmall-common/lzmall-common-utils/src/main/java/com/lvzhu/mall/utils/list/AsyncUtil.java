package com.lvzhu.mall.utils.list;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:21
 * Desc 文件描述
 */
public class AsyncUtil {
    private static final ThreadPoolExecutor commonPool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadFactoryBuilder().setNameFormat("common-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());
    public static void run(Runnable... runnable) {
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (Runnable run : runnable) {
            futureList.add(CompletableFuture.runAsync(run, commonPool));
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
    }
}
