package com.lvzhu.mall.utils.page;

import java.util.Objects;

import lombok.Data;

@Data
public class PageRequest extends AbstractPageRequest {

    private static final long serialVersionUID = -4541509938956089562L;

    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;

    private Sort sort;

    /**
     * 是否开启count，默认为开启
     */
    private boolean countEnabled = true;

    /**
     * 使用默认的页码和页面大小构造 {@link PageRequest} 实例。
     */
    public PageRequest() {
        this(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    }

    /**
     * 根据页码和页面大小构造 {@link PageRequest} 实例。页码从1开始，即 {@code pageNumber} 为1时返回第一页数据。
     *
     * @param pageNumber 页码
     * @param pageSize   页面大小
     */
    public PageRequest(int pageNumber, int pageSize) {
        this(pageNumber, pageSize, null);
    }

    /**
     * 根据页码、页面大小和排序构造 {@link PageRequest} 实例。页码从1开始，即 {@code pageNumber} 为1时返回第一页数据。
     *
     * @param pageNumber 页码
     * @param pageSize   页面大小
     * @param direction  排序方向
     * @param properties 排序属性
     */
    public PageRequest(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        this(pageNumber, pageSize, new Sort(direction, properties));
    }

    /**
     * 根据页码、页面大小和排序构造 {@link PageRequest} 实例。页码从1开始，即 {@code pageNumber} 为1时返回第一页数据。
     *
     * @param pageNumber 页码
     * @param pageSize   页面大小
     * @param sort       排序
     */
    public PageRequest(int pageNumber, int pageSize, Sort sort) {
        super(pageNumber, pageSize);
        this.sort = sort;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getSort()
     */
    @Override
    public Sort getSort() {
        return sort;
    }

    /**
     * 设置排序
     */
    public void setSort(Sort sort) {
        this.sort = sort;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#next()
     */
    @Override
    public Pageable next() {
        return new PageRequest(getPageNumber() + 1, getPageSize(), getSort());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.AbstractPageRequest#previous()
     */
    @Override
    public PageRequest previous() {
        return getPageNumber() == 0 ? this
                : new PageRequest(getPageNumber() - 1, getPageSize(), getSort());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#first()
     */
    @Override
    public Pageable first() {
        return new PageRequest(0, getPageSize(), getSort());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageRequest)) {
            return false;
        }

        PageRequest that = (PageRequest) obj;

        boolean sortEqual = Objects.equals(this.sort, that.sort);

        return super.equals(that) && sortEqual;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 31 * super.hashCode() + (null == sort ? 0 : sort.hashCode());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String
                .format("Page request [number: %d, size %d, sort: %s]", getPageNumber(), getPageSize(),
                        sort == null ? null : sort.toString());
    }
}
