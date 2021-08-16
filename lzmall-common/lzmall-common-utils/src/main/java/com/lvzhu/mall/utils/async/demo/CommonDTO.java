package com.lvzhu.mall.utils.async.demo;

import java.io.Serializable;


/**
 * @author lvzhu.
 * Time 2021/8/16 上午10:58
 * Desc 文件描述
 */
public class CommonDTO<T extends Serializable> implements Serializable{

    /**
     * 类型
     */
    private String type;

    /**
     * 数据
     */
    private T data;
}
