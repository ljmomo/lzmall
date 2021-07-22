package com.lvzhu.mall.utils.page;

import java.util.Objects;


/**
 * @author lvzhu.
 * Time 2021/7/22 上午10:37
 * Desc 文件描述
 */
public class DemoPage {

    private PageDataSource<String> getDataSource( Object query){
        return PageDataSourceFactory.firstPageDataSource(page -> findPage(query));
    }


    private Page<String> findPage(Object query){
        return  new Page<>();
    }


    private  void getData(Object params){
        PageDataSource<String> dataSource = getDataSource(params);
        while (dataSource.mayHasMore()){
            Page<String> query = dataSource.query();
            query.getContent().stream().filter(Objects::nonNull).forEach(a->{
            });
        }
    }

}
