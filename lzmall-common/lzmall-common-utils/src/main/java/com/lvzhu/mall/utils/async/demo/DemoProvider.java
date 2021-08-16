package com.lvzhu.mall.utils.async.demo;

import java.util.List;

import lombok.extern.slf4j.Slf4j;


/**
 * @author lvzhu.
 * Time 2021/8/16 上午11:05
 * Desc 文件描述
 */

@Slf4j
public class DemoProvider implements CommonProvider{

    @Override
    public List<CommonDTO> find(String name, Integer id) {
        return null;
    }
}
