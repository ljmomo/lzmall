package com.lvzhu.mall.utils.list;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:21
 * Desc 文件描述
 */
public class ListUtil {
    public static String toDbIn(List<String> data){
        return data.stream().map(e -> {
            return "'" + e + "'";
        }).collect(Collectors.joining(","));
    }
}
