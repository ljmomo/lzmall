package com.lvzhu.mall.utils.exception;

public enum ErrorType implements IErrorCode {
    // 10 系统级别异常
    DEFAULT(348010001, "failed"),
    SYSTEM_ERROR(348010002, "系统异常"),
    PARAM_ILLEGAL(348020001, "请求参数不合法"),

    ;


//    NO_LIVE_ASSET(321100099,"当前直播无资产权限观看")
    ;

    private int code;

    private String message;

    ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static ErrorType parse(int code) {
        ErrorType[] errorTypes = ErrorType.values();
        for (ErrorType errorType : errorTypes) {
            if (errorType.getCode() == code) {
                return errorType;
            }
        }
        return null;
    }
}