package com.lvzhu.mall.utils;


/**
 * @author lvzhu.
 * Time 2021/8/13 下午6:06
 * Desc 文件描述
 */
public enum ThreadRejectPolicyType {

    /**
     * 用调用线程继续执行
     */
    CALLER_RUNS,

    /**
     * 直接丢弃, 抛出异常
     */
    ABORT
}
