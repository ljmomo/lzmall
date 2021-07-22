package com.lvzhu.mall.utils.page;

/**
 * @author lvzhu.
 * Time 2021/5/26 下午2:45
 * Desc 文件描述
 */
public interface PageDataSource<Result> {
    boolean mayHasMore();

    Page<Result> query();

    Integer getCurrentPage();

    Long estimateSize();
}
