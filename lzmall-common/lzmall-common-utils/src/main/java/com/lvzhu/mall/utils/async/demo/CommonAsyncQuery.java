package com.lvzhu.mall.utils.async.demo;

import com.lvzhu.mall.utils.async.AbstractAsyncQuery;
import com.lvzhu.mall.utils.async.AsyncQueryFuture;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author lvzhu.
 * Time 2021/8/16 上午10:55
 * Desc 文件描述
 */
public class CommonAsyncQuery extends AbstractAsyncQuery<CommonQueyParam, List<CommonDTO>> {

    private List<CommonProvider> providers;


    public CommonAsyncQuery(List<CommonProvider> providers) {
        this.providers = providers;
    }


    /**
     * 聚合结果
     *
     * @param futures futures
     * @return R
     */
    @Override
    public List<CommonDTO> reduce(List<AsyncQueryFuture> futures) {
        return futures.stream().map(f-> (List<CommonDTO>)f.getResult()).filter(CollectionUtils::isNotEmpty).flatMap(Collection::stream).collect(Collectors.toList());
    }


    /**
     * 聚合线程
     */
    @Override
    public List<AsyncQueryFuture> mapFutures(CommonQueyParam param) {
        List<AsyncQueryFuture> futures = new ArrayList<>();
        for(CommonProvider provider: providers){
            futures.add(new AsyncQueryFuture(() -> provider.find(param.getName(), param.getUserId())));
        }
        return futures;
    }
}
