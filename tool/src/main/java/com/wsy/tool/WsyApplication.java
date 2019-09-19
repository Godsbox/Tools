/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tool;

import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArraySet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.wsy.tool.util.OsUtils.asOfMarshmallow;

/**
 * 应用
 * @author wsy
 * @time 2019/9/19 15:16
 */
public class WsyApplication extends MultiDexApplication {

    /**
     * 单例。
     */
    private static WsyApplication INSTANCE;

    /**
     * 标识启动权限是否均已授权。
     */
    private static boolean launchPermissionsGranted;

    /**
     * 启动需要的权限
     */
    public static Set<String> LAUNCH_PERMISSIONS = new ArraySet<>();

    /**
     * 被拒绝的权限。
     */
    private static Set<String> deniedLaunchPermissions;

    /**
     * 分割 Dex 支持
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        beforeCreate();
        INSTANCE = this;
        checkLaunchPermissions();
        closeAndroidPWarningDialog();
    }

    public void beforeCreate(){
        LAUNCH_PERMISSIONS.add(READ_PHONE_STATE);
        LAUNCH_PERMISSIONS.add(WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 关闭在android P手机上调试时弹出的警告弹窗
     */
    protected void closeAndroidPWarningDialog() {
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= 28) {
            try {
                Class clz = Class.forName("android.content.pm.PackageParser$Package");
                Constructor declaredConstructor = clz.getDeclaredConstructor(String.class);
                declaredConstructor.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Class cls = Class.forName("android.app.ActivityThread");
                Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
                declaredMethod.setAccessible(true);
                Object activityThread = declaredMethod.invoke(null);
                Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
                mHiddenApiWarningShown.setAccessible(true);
                mHiddenApiWarningShown.setBoolean(activityThread, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取应用单例。
     *
     * @return 应用单例
     */
    public static WsyApplication getInstance() {
        return INSTANCE;
    }


    /**
     * 检查启动权限是否均已授权。
     *
     * @return true：均已授权；false：存在未授权。
     */
    protected static boolean checkLaunchPermissions() {
        // 安卓 6.0 以下不需要授权运行时权限。
        if (!asOfMarshmallow()) {
            launchPermissionsGranted = true;
            return true;
        }
        // 检查运行时权限是否均已被授权
        boolean allGranted = true;
        deniedLaunchPermissions = new ArraySet<>(LAUNCH_PERMISSIONS.size());
        for (String permission : LAUNCH_PERMISSIONS) {
            int flag = ContextCompat.checkSelfPermission(getInstance(), permission);
            if (flag != PERMISSION_GRANTED) {
                allGranted = false;
                deniedLaunchPermissions.add(permission);
            }
        }
        launchPermissionsGranted = allGranted;
        return launchPermissionsGranted;
    }

    public static void removeDeniedLaunchPermission(String permission) {
        deniedLaunchPermissions.remove(permission);
    }

    public static String[] getDeniedLaunchPermissions() {
        return deniedLaunchPermissions.toArray(new String[deniedLaunchPermissions.size()]);
    }

}