package com.wsy.tool.util;

import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * 关于消息操作方法的工具类
 */
public final class ToastUtils {
    // 防止重复弹toast
    private static long lastToastTime = 0;
    // 允许间隔时间
    private static int differTimeMillis = 1000;

    /**
     * 显示短消息。
     *
     * @param msg 消息内容
     */
    public static void show(String msg) {
        showShort(msg);
    }

    /**
     * 显示短消息。
     *
     * @param resId 消息资源 ID
     */
    public static void show(@StringRes int resId) {
        showShort(resId);
    }

    /**
     * 显示短消息。
     *
     * @param message 消息内容
     */
    public static void showShort(String message) {
        if (StringUtils.isEmpty(message)) {
            return;
        }
        long curTime = System.currentTimeMillis();
        long timeDiffer = curTime - lastToastTime;
        if (0 < timeDiffer && timeDiffer < differTimeMillis) {
            return;
        }
        lastToastTime = curTime;
        Toast.makeText(ContextUtils.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示短消息。
     *
     * @param resId 消息资源 ID
     */
    public static void showShort(@StringRes int resId) {
        if (resId <= 0) {
            return;
        }
        long curTime = System.currentTimeMillis();
        long timeDiffer = curTime - lastToastTime;
        if (0 < timeDiffer && timeDiffer < differTimeMillis) {
            return;
        }
        lastToastTime = curTime;
        Toast.makeText(ContextUtils.getAppContext(), resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示短消息。
     *
     * @param message 消息资源 ID
     * @param args    参数
     */
    public static void showShort(@StringRes int message, Object... args) {
        String text = ResourceUtils.getString(message, args);
        Toast.makeText(ContextUtils.getAppContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示长消息。
     *
     * @param message 消息内容
     */
    public static void showLong(String message) {
        if (StringUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(ContextUtils.getAppContext(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示长消息。
     *
     * @param resId 消息资源 ID
     */
    public static void showLong(@StringRes int resId) {
        if (resId <= 0) {
            return;
        }
        Toast.makeText(ContextUtils.getAppContext(), resId, Toast.LENGTH_LONG).show();
    }
}
