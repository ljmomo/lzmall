package com.lvzhu.mall.utils.async;

import com.lvzhu.mall.utils.AsyncExecutorOptions;

import java.util.List;
import java.util.concurrent.Executor;


/**
 * @author lvzhu.
 * Time 2021/8/13 下午6:09
 * Desc 文件描述
 */
public abstract  class AbstractAsyncQuery<T,R> implements AsyncQuery<T,R> {
    @Override
    public R doQuery(T param) {
        List<AsyncQueryFuture> queryFutures = mapFutures(param);
        AsyncQueryExecutor.executeQuery(queryFutures);
        return reduce(queryFutures);
    }

    @Override
    public R doQuery(T param, Executor executor) {
        List<AsyncQueryFuture> queryFutures = mapFutures(param);
        AsyncQueryExecutor.executeQuery(queryFutures, AsyncExecutorOptions.DEFAULT, executor);
        return reduce(queryFutures);
    }
}
