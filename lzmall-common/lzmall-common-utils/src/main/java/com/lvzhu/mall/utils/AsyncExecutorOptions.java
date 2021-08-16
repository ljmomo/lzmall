package com.lvzhu.mall.utils;

import com.lvzhu.mall.utils.exception.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsyncExecutorOptions {
    public static final AsyncExecutorOptions DEFAULT = AsyncExecutorOptions.builder().build();

    @Builder.Default
    private long timeout = 2500;

    @Builder.Default
    private ExceptionHandler exceptionHandler = new DirectThrowExceptionHandler();

}