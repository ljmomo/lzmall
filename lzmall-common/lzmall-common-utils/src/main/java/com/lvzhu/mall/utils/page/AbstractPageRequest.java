package com.lvzhu.mall.utils.page;

import java.io.Serializable;


public abstract class AbstractPageRequest implements Pageable, Serializable {

    private int pageNumber;
    private int pageSize;

    public AbstractPageRequest(int pageNumber, int pageSize) {

        if (pageNumber < 1) {
            throw new IllegalArgumentException("Page index must not be less than one!");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageSize()
     */
    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageNumber()
     */
    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getOffset()
     */
    @Override
    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#hasPrevious()
     */
    @Override
    public boolean hasPrevious() {
        return pageNumber > 1;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#previousOrFirst()
     */
    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#next()
     */
    @Override
    public abstract Pageable next();

    /**
     * Returns the {@link Pageable} requesting the previous {@link Page}.
     */
    public abstract Pageable previous();

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#first()
     */
    @Override
    public abstract Pageable first();

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + pageNumber;
        result = prime * result + pageSize;

        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AbstractPageRequest other = (AbstractPageRequest) obj;
        return this.pageNumber == other.pageNumber && this.pageSize == other.pageSize;
    }
}
