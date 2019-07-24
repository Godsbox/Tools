/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tool.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import static com.wsy.tool.util.ContextUtils.getAppContext;


/**
 * 关于资源操作方法的工具类。
 *
 * @author wsy
 */
public final class ResourceUtils {

    private ResourceUtils() {}

    /**
     * 获取字符串资源值。
     *
     * @param res 资源 ID
     * @return 字符串资源值
     */
    public static String getString(@StringRes int res) {
        if (res == 0) {
            return "";
        }
        return getAppContext().getString(res);
    }

    /**
     * 获取字符串资源值。
     *
     * @param res 资源 ID
     * @param args 格式化参数
     * @return 字符串资源值
     */
    public static String getString(@StringRes int res, Object... args) {
        return getAppContext().getString(res, args);
    }

    /**
     * 获取颜色资源值。
     *
     * @param color 资源 ID
     * @return 颜色资源值
     */
    public static int getColor(@ColorRes int color) {
        return ContextCompat.getColor(getAppContext(), color);
    }

    /**
     * 获取颜色状态列表。
     *
     * @param color 颜色资源 ID
     * @return 颜色状态列表
     */
    public static ColorStateList getColorStateList(@ColorRes int color) {
        return ContextCompat.getColorStateList(getAppContext(), color);
    }

    /**
     * 获取字符串数组资源。
     *
     * @param array 字符串数组资源 ID
     * @return 字符串数组资源
     */
    public static String[] getStringArray(int array) {
        return getAppContext().getResources().getStringArray(array);
    }

    /**
     * 获取尺寸资源值。
     *
     * @param dimen 尺寸资源 ID
     * @return 尺寸资源值
     */
    public static int getDimensionPixelSize(int dimen) {
        return getAppContext().getResources().getDimensionPixelSize(dimen);
    }

    /**
     * 获取图片资源值。
     *
     * @param drawable 图片资源 ID
     */
    public static Drawable getDrawable(@DrawableRes int drawable) {
        return getAppContext().getResources().getDrawable(drawable);
    }
}
