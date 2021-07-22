package com.lvzhu.mall.utils.exception;

public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int errorCode;

    public BusinessException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BusinessException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BusinessException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
    }

    public BusinessException(IErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode.getCode();
    }

    /**
     * 获取错误码
     */
    public int getErrorCode() {
        return errorCode;
    }
}
