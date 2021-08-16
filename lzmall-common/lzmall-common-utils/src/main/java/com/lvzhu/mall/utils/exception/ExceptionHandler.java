package com.lvzhu.mall.utils.exception;

/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:21
 * Desc 文件描述
 */
@FunctionalInterface
public interface ExceptionHandler {

    /**
     * handle
     * @param throwable throwable
     */
    void handle(Throwable throwable);
}
