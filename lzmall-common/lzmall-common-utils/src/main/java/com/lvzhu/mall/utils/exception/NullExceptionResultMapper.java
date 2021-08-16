package com.lvzhu.mall.utils.exception;

import java.util.function.Function;

/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:21
 * Desc 文件描述
 */
public class NullExceptionResultMapper implements Function<Throwable, Object> {

    @Override
    public Object apply(Throwable throwable) {
        return null;
    }
}
