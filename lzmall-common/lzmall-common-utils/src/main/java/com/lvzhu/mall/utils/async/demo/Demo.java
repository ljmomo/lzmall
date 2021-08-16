package com.lvzhu.mall.utils.async.demo;

import java.util.List;


/**
 * @author lvzhu.
 * Time 2021/8/16 上午11:16
 * Desc 文件描述
 */
public class Demo {

    private DemoProviderManager providerManager;


    private List<CommonDTO> findDemo(String name,Integer userId){
        final List<CommonProvider> providers = providerManager.getProviders();
          return   new CommonAsyncQuery(providers).doQuery(CommonQueyParam.builder().name(name).userId(userId).build());
    }


}
