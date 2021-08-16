package com.lvzhu.mall.utils.async.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author lvzhu.
 * Time 2021/8/16 上午10:55
 * Desc 文件描述
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonQueyParam {

    private String name;

    private Integer userId;
}

