package com.lvzhu.mall.utils.async;

import java.util.function.Supplier;

import lombok.Data;


/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:20
 * Desc 文件描述
 */
@Data
public class AsyncQueryFuture<T> {
    private T result;

    private Supplier<T> query;


    private AsyncQueryOptions options = AsyncQueryOptions.DEFAULT;

    /**
     * 保存被异常处理器处理后抛出的异常（仅异常被抛出时有值）
     */
    private Throwable throwable;

    public AsyncQueryFuture(Supplier<T> query) {
        this.query = query;
    }

    public AsyncQueryFuture(Supplier<T> query, AsyncQueryOptions options) {
        this.options = options;
        this.query = query;
    }
}
