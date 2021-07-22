package com.lvzhu.mall.utils.page;

import com.lvzhu.mall.utils.exception.BizPreconditions;
import com.lvzhu.mall.utils.exception.ErrorType;

import java.util.function.Function;


/**
 * @author lvzhu.
 * Time 2021/5/26 下午4:06
 * Desc 永远只查询第一页的数据源，适用于分页 query And Update 的场景
 */
public class FirstPageDataSource<T> implements PageDataSource<T> {

    private int pageSize = 200;
    private boolean firstQuery = true;
    private int currentSize = 0;
    private Function<PageRequest, Page<T>> resultsSupplier;


    public FirstPageDataSource(Function<PageRequest, Page<T>> results, Integer pageSize) {
        this.resultsSupplier = results;
        this.setPageSize(pageSize);
    }

    private void setPageSize(Integer size) {
        BizPreconditions.checkArgument(size < 4000 && size > 0, ErrorType.PARAM_ILLEGAL, "分页值不合法");
        this.pageSize = size;
    }

    @Override
    public boolean mayHasMore() {
        return firstQuery || currentSize == pageSize;
    }


    @Override
    public Page<T> query() {
        PageRequest pageRequest = new PageRequest(1, pageSize);
        pageRequest.setSort(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
        Page<T> page = resultsSupplier.apply(pageRequest);
        currentSize = page.getNumberOfElements();
        firstQuery = false;
        return page;
    }


    @Override
    public Integer getCurrentPage() {
        return 1;
    }


    @Override
    public Long estimateSize() {
        Page<T> apply = resultsSupplier.apply(new PageRequest(1, 1));
        return apply.getTotal();
    }
}
