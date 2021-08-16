package com.lvzhu.mall.utils.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:26
 * Desc 文件描述
 */
public class SimpleLogExceptionHandler implements ExceptionHandler{
    private final Logger logger;

    public SimpleLogExceptionHandler() {
        logger = LoggerFactory.getLogger(SimpleLogExceptionHandler.class);
    }

    public SimpleLogExceptionHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void handle(Throwable throwable) {
        if (throwable instanceof BusinessException) {
            BusinessException exception = (BusinessException) throwable;
            if (exception instanceof FatalException) {
                logger.error("Code: " + exception.getErrorCode(), exception);
            } else {
                logger.warn("Code: " + exception.getErrorCode(), exception);
            }
        } else {
            logger.error("", throwable);
        }
    }
}
