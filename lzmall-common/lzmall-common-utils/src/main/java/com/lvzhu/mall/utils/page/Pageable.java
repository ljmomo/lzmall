package com.lvzhu.mall.utils.page;

public interface Pageable {

    /**
     * 获取页码
     *
     * @return 页码
     */
    int getPageNumber();


    /**
     * 获取页面大小
     *
     * @return 页面大小
     */
    int getPageSize();


    /**
     * 获取偏移量
     *
     * @return 偏移量
     */
    int getOffset();

    /**
     * 获取分页参数
     */
    Sort getSort();

    /**
     * 返回下一个 {@link Pageable}
     */
    Pageable next();

    /**
     * 返回上一个 {@link Pageable} ，或第一个 {@link Pageable} 仅当当前页已经是第一页
     */
    Pageable previousOrFirst();

    /**
     * 返回用于请求第一页的 {@link Pageable}
     */
    Pageable first();

    /**
     * 返回是否有第上一页
     * <p>
     * 仅当当前 {@link Pageable} 已经是第一页时返回 {@literal false}
     */
    boolean hasPrevious();
}
