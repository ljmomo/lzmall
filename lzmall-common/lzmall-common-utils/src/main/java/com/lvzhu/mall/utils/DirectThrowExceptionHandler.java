package com.lvzhu.mall.utils;

import com.lvzhu.mall.utils.exception.BusinessException;
import com.lvzhu.mall.utils.exception.ExceptionHandler;
import com.lvzhu.mall.utils.exception.FatalException;


public class DirectThrowExceptionHandler implements ExceptionHandler {

    @Override
    public void handle(Throwable throwable) {
        if (throwable instanceof BusinessException) {
            throw ((BusinessException) throwable);
        }

        throw new FatalException(-100, throwable);
    }
}
