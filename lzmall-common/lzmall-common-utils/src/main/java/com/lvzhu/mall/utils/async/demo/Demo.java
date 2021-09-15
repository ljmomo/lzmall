package com.lvzhu.mall.utils.async.demo;

import com.google.common.collect.Lists;

import com.lvzhu.mall.utils.async.AsyncQueryExecutor;
import com.lvzhu.mall.utils.async.AsyncQueryFuture;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author lvzhu.
 * Time 2021/8/16 上午11:16
 * Desc 文件描述
 */
public class Demo {

    private DemoProviderManager providerManager;


    /**
     * 一次要查询多个数据
     * @param name
     * @param userId
     * @return
     */
    private List<CommonDTO> findDemo(String name, Integer userId) {
        final List<CommonProvider> providers = providerManager.getProviders();
        return new CommonAsyncQuery(providers).doQuery(CommonQueyParam.builder().name(name).userId(userId).build());
    }


    private List<DemoDTO> demo2(List<String> queryList) {
        List<DemoDTO> resultList = new ArrayList<>();
        List<AsyncQueryFuture> futures = queryList.stream().map(query -> getFuture(query)).collect(Collectors.toList());
        AsyncQueryExecutor.executeQuery(futures);
        futures.stream().forEach(asyncQueryFuture -> {
            DemoDTO demoDTO = Optional.ofNullable(asyncQueryFuture.getResult()).map(a -> {
                DemoDTO batchDTO = (DemoDTO) a;
                return batchDTO;
            }).orElse(null);
            if (Objects.nonNull(demoDTO)) {
                resultList.add(demoDTO);
            }
        });
        return resultList;
    }


    private AsyncQueryFuture<DemoDTO> getFuture(String query) {
        return new AsyncQueryFuture<>(() -> doFind(query));
    }


    private DemoDTO doFind(String query) {

        return new DemoDTO(query);
    }
}
