package com.lvzhu.mall.utils.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import lombok.Data;


public class Sort implements Iterable<Sort.Order>, Serializable {

    private static final long serialVersionUID = 2583583164063267653L;

    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private List<Order> orders;

    public Sort() {
    }

    public Sort(Order... orders) {
        this(Arrays.asList(orders));
    }

    public Sort(List<Order> orders) {

        if (null == orders || orders.isEmpty()) {
            throw new IllegalArgumentException(
                    "You have to provide at least one sort property to sort by!");
        }

        this.orders = orders;
    }

    /**
     * 使用默认排序 {@link Direction#ASC} 创建一个 {@link Sort}
     *
     * @param properties 排序属性
     */
    public Sort(String... properties) {
        this(DEFAULT_DIRECTION, properties);
    }

    /**
     * 创建一个 {@link Sort} 实例
     *
     * @param direction  排序方向
     * @param properties 排序属性
     */
    public Sort(Direction direction, String... properties) {
        this(direction, properties == null ? new ArrayList<>() : Arrays.asList(properties));
    }


    /**
     * 创建排序方向
     */
    public Sort(Direction direction, List<String> properties) {

        if (properties == null || properties.isEmpty()) {
            throw new IllegalArgumentException("You have to provide at least one property to sort by!");
        }

        this.orders = new ArrayList<>(properties.size());

        for (String property : properties) {
            this.orders.add(new Order(direction, property));
        }
    }

    /**
     * Returns a new {@link Sort} consisting of the {@link Order}s of the current {@link Sort}
     * combined with the given ones.
     *
     * @param sort can be {@literal null}.
     */
    public Sort and(Sort sort) {

        if (sort == null) {
            return this;
        }

        ArrayList<Order> these = new ArrayList<Order>(this.orders);

        for (Order order : sort) {
            these.add(order);
        }

        return new Sort(these);
    }

    /**
     * Returns the order registered for the given property.
     */
    public Order getOrderFor(String property) {

        for (Order order : this) {
            if (order.getProperty().equals(property)) {
                return order;
            }
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Order> iterator() {
        return this.orders.iterator();
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

        if (!(obj instanceof Sort)) {
            return false;
        }

        Sort that = (Sort) obj;

        return this.orders.equals(that.orders);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result + this.orders.hashCode();
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Sort{" +
                "orders=" + this.orders +
                '}';
    }

    /**
     * 排序方向
     */
    public enum Direction {

        ASC, DESC;

        public boolean isAscending() {
            return this.equals(ASC);
        }

        public boolean isDescending() {
            return this.equals(DESC);
        }

        public static Direction fromString(String value) {
            try {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format(
                        "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).",
                        value), e);
            }
        }

        public static Direction fromStringOrNull(String value) {

            try {
                return fromString(value);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    /**
     * 如何处理 {@code null} 值的排序策略
     */
    public enum NullHandling {

        /**
         * 让底层数据库自行决定如何处理
         */
        NATIVE,

        /**
         * {@code null} 在前
         */
        NULLS_FIRST,

        /**
         * {@code null} 在后
         */
        NULLS_LAST;
    }

    @Data
    public static class Order implements Serializable {

        private static final long serialVersionUID = 4688849215001940570L;
        private Direction direction;
        private String property;
        private NullHandling nullHandling;

        public Order() {
        }

        public Order(Direction direction, String property) {
            this(direction, property, null);
        }

        public Order(Direction direction, String property, NullHandling nullHandling) {
            if (property == null || property.length() == 0) {
                throw new IllegalArgumentException("Property must not null or empty!");
            }

            this.direction = direction == null ? DEFAULT_DIRECTION : direction;
            this.property = property;
            this.nullHandling = nullHandling == null ? NullHandling.NATIVE : nullHandling;
        }

        public Order(String property) {
            this(DEFAULT_DIRECTION, property);
        }

        public boolean isAscending() {
            return this.direction.isAscending();
        }

        public boolean isDescending() {
            return this.direction.isDescending();
        }

        public Order with(Direction direction) {
            return new Order(direction, this.property, this.nullHandling);
        }

        public Order withProperty(String property) {
            return new Order(this.direction, property, this.nullHandling);
        }

        public Sort withProperties(String... properties) {
            return new Sort(this.direction, properties);
        }

        public Order with(NullHandling nullHandling) {
            return new Order(this.direction, this.property, nullHandling);
        }

        public Order nullsFirst() {
            return this.with(NullHandling.NULLS_FIRST);
        }

        public Order nullsLast() {
            return this.with(NullHandling.NULLS_LAST);
        }

        public Order nullsNative() {
            return this.with(NullHandling.NATIVE);
        }
    }
}
