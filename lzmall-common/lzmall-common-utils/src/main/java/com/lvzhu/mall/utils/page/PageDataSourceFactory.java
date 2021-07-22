package com.lvzhu.mall.utils.page;
import java.util.function.Function;


/**
 * @author lvzhu.
 * Time 2021/5/26 下午2:35
 * Desc 文件描述
 */
public class PageDataSourceFactory {

    public static <T> PageDataSource<T> firstPageDataSource(Function<PageRequest, Page<T>> supplier) {
        return new FirstPageDataSource<>(supplier, 200);
    }


    public static <T> PageDataSource<T> templateOfRollingDS(Function<PageRequest, Page<T>> supplier) {
        return new RollingPageDataSource<>(supplier, 200);
    }

}
