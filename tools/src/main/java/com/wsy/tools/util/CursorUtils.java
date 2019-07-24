/*
 * Copyright (c) 2017 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

import android.database.Cursor;

/**
 * 关于数据游标操作方法的工具类。
 *
 * @author wsy
 */
public final class CursorUtils {

    /**
     * 私有构造器。
     */
    private CursorUtils() {
    }

    /**
     * 获取给定字段的字符串值。
     *
     * @param cursor 游标
     * @param column 字段名称
     * @return 字段值
     */
    public static String getString(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndex(column));
    }

    /**
     * 获取给定字段的整数值。
     *
     * @param cursor 游标
     * @param column 字段名称
     * @return 字段值
     */
    public static int getInt(Cursor cursor, String column) {
        return cursor.getInt(cursor.getColumnIndex(column));
    }

    /**
     * 获取给定字段的长整数值。
     *
     * @param cursor 游标
     * @param column 字段名称
     * @return 字段值
     */
    public static long getLong(Cursor cursor, String column) {
        return cursor.getLong(cursor.getColumnIndex(column));
    }

    /**
     * 获取给定字段的布尔值。
     *
     * @param cursor 游标
     * @param column 字段名称
     * @return 字段值
     */
    public static boolean getBoolean(Cursor cursor, String column) {
        return getInt(cursor, column) > 0;
    }
}
