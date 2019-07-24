/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tool.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 关于集合操作方法的工具类。
 * @author wsy
 */
public final class CollectionUtils {

    /**
     * 私有构造方法。
     */
    private CollectionUtils() {
    }

    /**
     * 判断给定的集合是否无内容。
     *
     * @param collection 集合
     * @param <E>        元素类型
     * @return true：无内容；false：有内容。
     */
    public static <E> boolean isEmpty(Collection<E> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断给定的映射是否为空。
     *
     * @param map 映射表
     * @param <K> 键类型
     * @param <V> 值类型
     * @return true：空；false：非空。
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.size() == 0;
    }

    /**
     * 确保给定的列表不是 null，如果是 null 则将返回 {@link Collections#EMPTY_LIST} 空列表。
     *
     * @param list 列表
     * @param <T>  元素类型
     * @return 列表或空列表。
     */
    public static <T> List<T> ensureNonNull(List<T> list) {
        return list != null ? list : Collections.<T>emptyList();
    }

    /**
     * 获取集合大小。
     *
     * @param collection 集合
     * @param <E>        元素类型
     * @return 集合大小
     */
    public static <E> int getSize(Collection<E> collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * 获取列表指定位置上的元素。
     *
     * @param list     列表
     * @param position 元素位置
     * @param <E>      元素类型
     * @return 元素
     */
    public static <E> E getItem(List<E> list, int position) {
        if (isEmpty(list)) {
            return null;
        }
        if (position < 0 || position >= list.size()) {
            return null;
        }
        return list.get(position);
    }

    /**
     * 获取列表的第一个元素。
     *
     * @param list 列表
     * @param <T>  元素类型
     * @return 第一个元素，或者是 <code>null</code>。
     */
    public static <T> T getFirst(List<T> list) {
        return isEmpty(list) ? null : list.get(0);
    }

    /**
     * 获取列表的最后一个元素。
     *
     * @param list 列表
     * @param <T>  元素类型
     * @return 最后一个元素，或者是 <code>null</code>。
     */
    public static <T> T getLast(List<T> list) {
        return isEmpty(list) ? null : list.get(list.size() - 1);
    }

    /**
     * 删除最后一项。
     *
     * @param list 列表
     * @param <T>  项类型
     * @return 被删除项，无最后一项则返回 {@code null}。
     */
    public static <T> T removeLast(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        return remove(list, list.size() - 1);
    }

    /**
     * 删除给定位置的列表元素。
     *
     * @param list 列表
     * @param position 元素位置
     * @param <T> 元素类型
     * @return 已删除的元素
     */
    public static <T> T remove(List<T> list, int position) {
        if (position < 0 || position >= list.size()) {
            return null;
        }
        return list.remove(position);
    }

    /**
     * 添加数据项到列表的指定位置。
     *
     * @param list 列表
     * @param item 数据项
     * @param <T>  数据项类型
     */
    public static <T> void add(List<T> list, T item) {
        if (list == null) {
            return;
        }
        list.add(item);
    }

    /**
     * 添加数据项到列表的指定位置。
     *
     * @param list     列表
     * @param position 位置
     * @param item     数据项
     * @param <T>      数据项类型
     */
    public static <T> void add(List<T> list, int position, T item) {
        if (list == null) {
            return;
        }
        if (position < 0 || position > list.size()) {
            return;
        }
        list.add(position, item);
    }

    /**
     * 替换列表指定位置的数据项。
     *
     * @param list     列表
     * @param position 位置
     * @param item     数据项
     * @param <T>      数据项类型
     */
    public static <T> void replace(List<T> list, int position, T item) {
        if (list == null) {
            return;
        }
        if (position < 0 || position >= list.size()) {
            return;
        }
        list.set(position, item);
    }
}
