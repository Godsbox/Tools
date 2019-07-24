/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.WIFI_SERVICE;

/**
 * 关于上下文操作方法的工具类。
 *
 * @author wsy
 */
public final class ContextUtils {
    /**
     * 错误消息：应用上下文为null。
     */
    static final String MSG_APP_CONTEXT_IS_NULL = "请先在 Application#onCreate() 方法里调用 setAppContext(this) 方法";

    /**
     * 错误消息：上下文参数不能为null。
     */
    static final String MSG_CONTEXT_IS_NULL = "context 参数不能为 null。";

    /**
     * 应用上下文。
     */
    @SuppressLint("StaticFieldLeak")
    private static Context appContext;

    /**
     * 设置应用上下文。
     *
     * @param context 上下文
     */
    public static void setAppContext(Context context) {
        if(context == null){
            return;
        }
        appContext = context.getApplicationContext();
    }

    /**
     * 获取应用上下文。
     *
     * @return 应用上下文
     */
    public static Context getAppContext() {
        if (appContext == null) {
            throw new IllegalStateException(MSG_APP_CONTEXT_IS_NULL);
        }
        return appContext;
    }

    /**
     * 获取系统服务。利用泛型机制省去手动转换成目标类型。
     *
     * @param name 系统服务名称
     * @param <T>  系统服务的类型
     * @return 指名的系统服务
     */
    @SuppressWarnings("unchecked")
    public static <T> T getSystemService(String name) {
        return (T) getAppContext().getSystemService(name);
    }

    /**
     * 获取连接管理者。
     *
     * @return 连接管理者
     */
    public static ConnectivityManager getConnectivityManager() {
        return getSystemService(CONNECTIVITY_SERVICE);
    }

    /**
     * 获取WIFI连接管理者。
     *
     * @return 连接管理者
     */
    @SuppressLint("WifiManagerLeak")
    public static WifiManager getWifiConnectivityManager() {
        return getSystemService(WIFI_SERVICE);
    }

    /**
     * 将上下文转换成 Activity。
     *
     * @param context 上下文
     * @return Activity
     */
    public static Activity toActivity(Context context) {
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (!(context instanceof Activity)) {
            throw new IllegalStateException("Expected an activity context, got " + context.getClass().getSimpleName());
        }
        return (Activity) context;
    }
}
