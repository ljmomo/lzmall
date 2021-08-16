package com.lvzhu.mall.utils.async.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author lvzhu.
 * Time 2021/8/16 上午11:12
 * Desc 文件描述
 */
public class DemoProviderManager {


    private List<CommonProvider> providers = new ArrayList<>();

    /**
     * 获取所有的活动提供者
     *
     * @return
     */
    public List<CommonProvider> getProviders() {
        return Collections.unmodifiableList(providers);
    }

    /**
     * 注册活动提供器
     *
     * @param provider
     */
    public void register(CommonProvider provider) {
        providers.add(provider);
    }
}
