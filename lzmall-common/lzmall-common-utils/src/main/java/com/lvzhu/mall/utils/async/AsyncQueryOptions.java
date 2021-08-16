package com.lvzhu.mall.utils.async;

import com.lvzhu.mall.utils.exception.ExceptionHandler;
import com.lvzhu.mall.utils.exception.NullExceptionResultMapper;
import com.lvzhu.mall.utils.exception.SimpleLogExceptionHandler;

import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:21
 * Desc 文件描述
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsyncQueryOptions {
    public static final AsyncQueryOptions DEFAULT = AsyncQueryOptions.builder().build();


    /**
     * 异常处理器
     */
    @Builder.Default
    private ExceptionHandler exceptionHandler = new SimpleLogExceptionHandler();

    /**
     * 异常时返回的转换函数
     */
    @Builder.Default
    private Function<Throwable, Object> exceptionMapper = new NullExceptionResultMapper();
}
