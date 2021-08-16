package com.lvzhu.mall.utils.async.demo;

import java.util.List;


/**
 * @author lvzhu.
 * Time 2021/8/16 上午11:03
 * Desc 文件描述
 */
public interface CommonProvider {

    List<CommonDTO> find(String name, Integer id);

}
