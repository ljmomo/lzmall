package com.lvzhu.mall.utils.list;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author lvzhu.
 * Time 2021/8/13 下午5:21
 * Desc 文件描述
 */
public class LambdaUtil {

    public static <T, K, U> Map<K, U> toMap(List<T> list, Function<? super T, ? extends K> keyMapper,
                                      Function<? super T, ? extends U> valueMapper) {
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <T, R> List<R> toList(List<T> list, Function<? super T, ? extends R> mapper) {
        return list.stream().map(mapper).filter(Objects::nonNull).distinct().collect(Collectors.toList());
    }

    public static <T, R> Set<R> toSet(List<T> list, Function<? super T, ? extends R> mapper) {
        return list.stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toSet());
    }
}
