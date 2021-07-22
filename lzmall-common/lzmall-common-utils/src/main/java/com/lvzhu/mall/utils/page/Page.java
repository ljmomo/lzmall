package com.lvzhu.mall.utils.page;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Data;


@Data
public class Page<T> implements Iterable<T>, Serializable {

    private List<T> content = Collections.emptyList();

    private Pageable pageable;

    private long total = 0L;

    public Page() {
    }

    public Page(List<T> content, Pageable pageable, long total) {
        this.content = content == null ? Collections.emptyList() : content;
        this.pageable = pageable;
        this.total = total;
    }

    public int getTotalPages() {
        if (pageable == null) {
            return 1;
        }

        return ((int) Math.ceil((double) total / (double) pageable.getPageSize()));
    }

    public int getNumberOfElements() {
        return content.size();
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    public <S> Page<S> map(Function<T, S> mapper) {
        List<S> content = this.content.stream().map(mapper).collect(Collectors.toList());
        return new Page<>(content, pageable, total);
    }
}
