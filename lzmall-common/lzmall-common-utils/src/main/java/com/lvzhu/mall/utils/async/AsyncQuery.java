package com.lvzhu.mall.utils.async;

import java.util.List;
import java.util.concurrent.Executor;


/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:06
 * Desc 文件描述
 */
public interface AsyncQuery<T, R> {

    /**
     * 查询
     * @param param 参数
     * @return 返回
     */
    R doQuery(T param);


    /**
     * 查询 带执行器
     * @param param 参数
     * @param executor  执行器
     * @return 返回
     */
    R doQuery(T param, Executor executor);

    /**
     * 聚合结果
     * @param futures futures
     * @return R
     */
    R reduce(List<AsyncQueryFuture> futures);


    /**
     * 聚合线程
     * @param param
     * @return
     */
    List<AsyncQueryFuture> mapFutures(T param);

}
