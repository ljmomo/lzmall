package com.lvzhu.mall.utils.page;

import com.lvzhu.mall.utils.exception.BizPreconditions;
import com.lvzhu.mall.utils.exception.ErrorType;

import java.util.function.Function;


/**
 * @author lvzhu.
 * Time 2021/5/26 下午4:06
 * Desc 滚动分页查询，适用于统计（query and save 场景勿用）
 */
public class RollingPageDataSource<T> implements PageDataSource<T> {
    private int pageNo = 0, pageSize = 200;
    private Function<PageRequest, Page<T>> resultsSupplier;

    private int currentSize = 0;

    public RollingPageDataSource(Function<PageRequest, Page<T>> results, Integer pageSize) {
        this.resultsSupplier = results;
        this.setPageSize(pageSize);
    }

    private void setPageSize(Integer size) {
        BizPreconditions.checkArgument(size < 4000 && size > 0, ErrorType.PARAM_ILLEGAL, "分页值不合法");
        this.pageSize = size;
    }

    @Override
    public boolean mayHasMore() {
        return pageNo == 0 || currentSize == pageSize;
    }

    @Override
    public Page<T> query() {
        newRound();
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
        pageRequest.setSort(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
        Page<T> page = resultsSupplier.apply(pageRequest);
        currentSize = page.getNumberOfElements();
        return page;
    }

    @Override
    public Integer getCurrentPage() {
        return pageNo;
    }

    @Override
    public Long estimateSize() {
        Page<T> apply = resultsSupplier.apply(new PageRequest(1, 1));
        return apply.getTotal();
    }

    private void newRound() {
        this.pageNo++;
    }
}
