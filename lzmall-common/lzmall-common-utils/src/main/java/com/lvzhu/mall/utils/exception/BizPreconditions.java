package com.lvzhu.mall.utils.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;


public class BizPreconditions {

    /**
     * 检查参数非null
     *
     * @throws BusinessException 业务异常
     */
    public static <T> void checkNotNull(T reference, ErrorType ErrorType, String errorMessage) throws BusinessException {
        if (reference == null) {
            throw new BusinessException(ErrorType.getCode(), errorMessage);
        }
    }

    /**
     * 检查参数非null
     *
     * @throws BusinessException 业务异常
     */
    public static <T> void checkNotNull(T reference, ErrorType ErrorType) throws BusinessException {
        if (reference == null) {
            throw new BusinessException(ErrorType.getCode(), ErrorType.getMessage());
        }
    }

    /**
     * 检查字符串非空
     *
     * @throws BusinessException 业务异常
     */
    public static void checkNotBlank(String reference, ErrorType ErrorType, String errorMessage) throws BusinessException {
        if (StringUtils.isBlank(reference)) {
            throw new BusinessException(ErrorType.getCode(), errorMessage);
        }
    }

    /**
     * 检查字符串非空
     *
     * @throws BusinessException 业务异常
     */
    public static void checkNotBlank(String reference, ErrorType ErrorType) throws BusinessException {
        if (StringUtils.isBlank(reference)) {
            throw new BusinessException(ErrorType.getCode(), ErrorType.getMessage());
        }
    }

    /**
     * 检查Long格式合法
     *
     * @throws BusinessException 业务异常
     */
    public static void checkLongLegal(Long reference, ErrorType ErrorType, String errorMessage) throws BusinessException {
        if (null == reference || reference <= 0) {
            throw new BusinessException(ErrorType.getCode(), errorMessage);
        }
    }

    /**
     * 检查Long格式合法
     *
     * @throws BusinessException 业务异常
     */
    public static void checkLongLegal(Long reference, ErrorType ErrorType) throws BusinessException {
        if (null == reference || reference <= 0) {
            throw new BusinessException(ErrorType.getCode(), ErrorType.getMessage());
        }
    }

    /**
     * 检查表达式
     *
     * @throws BusinessException 业务异常
     */
    public static void checkArgument(boolean expression, ErrorType ErrorType) throws BusinessException {
        if (!expression) {
            throw new BusinessException(ErrorType.getCode(), ErrorType.getMessage());
        }
    }

    /**
     * 检查表达式
     */
    public static void checkArgument(boolean expression, String errorMessage) {
        if (!expression) {
            throw new BusinessException(-100, errorMessage);
        }
    }

    /**
     * 检查表达式
     *
     * @throws BusinessException 业务异常
     */
    public static void checkArgument(boolean expression, ErrorType ErrorType, String errorMessage) throws BusinessException {
        if (!expression) {
            throw new BusinessException(ErrorType.getCode(), errorMessage);
        }
    }

    /**
     * 检查是否相等
     *
     * @throws BusinessException
     */
    public static void checkEquals(Object a, Object b, ErrorType ErrorType, String msg) throws BusinessException {
        if (!Objects.equals(a, b)) {
            throw new BusinessException(ErrorType.getCode(), msg);
        }
    }

    /**
     * 检查是否相等
     *
     * @throws BusinessException
     */
    public static void checkEquals(Object a, Object b, ErrorType ErrorType) throws BusinessException {
        if (!Objects.equals(a, b)) {
            throw new BusinessException(ErrorType);
        }
    }
}
