package com.lvzhu.mall.utils.exception;
/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:21
 * Desc 文件描述
 */
public class FatalException extends BusinessException {

    public FatalException(int errorCode) {
        super(errorCode);
    }

    public FatalException(int errorCode, String message) {
        super(errorCode, message);
    }

    public FatalException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public FatalException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public FatalException(IErrorCode errorCode) {
        super(errorCode);
    }

    public FatalException(IErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
