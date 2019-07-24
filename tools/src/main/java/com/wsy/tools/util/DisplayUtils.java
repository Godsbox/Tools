/*
 * Copyright (c) 2017 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import java.lang.reflect.Method;

import static com.wsy.tools.util.ContextUtils.getAppContext;


/**
 * 关于显示器操作方法的工具类。
 *
 * @author wsy
 */
public class DisplayUtils {

    static {
        //Timber.d("display density:%s, width:%s, height:%s", getDensity(), getDisplayWidth(), getDisplayHeight());
    }

    /**
     * 获取屏幕显示器宽度。（单位：像素）
     *
     * @return 显示器宽度
     */
    public static int getDisplayWidth() {
        DisplayMetrics displayMetrics = getAppContext().getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕显示器高度。（单位：像素）
     *
     * @return 显示器高度
     */
    public static int getDisplayHeight() {
        DisplayMetrics displayMetrics = getAppContext().getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * 获取屏幕密度。
     *
     * @return 屏幕密度
     */
    private static float getScaledDensity() {
        return getAppContext().getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * 获取屏幕密度。
     *
     * @return 屏幕密度
     */
    public static float getDensity() {
        return getAppContext().getResources().getDisplayMetrics().density;
    }

    /**
     * 密度像素转像素。
     *
     * @param dp 密度像素
     * @return 像素
     */
    public static int dp2px(float dp) {
        float density = getDensity();
        if (dp < 0) {
            return (int) (dp * density - 0.5f);
        } else {
            return (int) (dp * density + 0.5f);
        }
    }

    /**
     * 密度像素转像素。
     *
     * @param dp 密度像素
     * @return 像素
     */
    public static float dp2px_float(float dp) {
        float density = getDensity();
        if (dp < 0) {
            return dp * density - 0.5f;
        } else {
            return dp * density + 0.5f;
        }
    }

    /**
     * sp转换成px
     */
    public static float sp2px(int spValue) {
        float fontScale = getScaledDensity();
        return spValue * fontScale + 0.5f;
    }

    /**
     * 将px值转换为sp值
     */
    public static int px2sp(int pxValue) {
        final float fontScale = getScaledDensity();
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将px值转换为dip或dp值
     */
    public static int px2dip(int pxValue) {
        return (int) (pxValue / getDensity() + 0.5f);
    }

    /**
     * 退出应用
     * 适合sdk7以上的版本
     */
    public static void exitApp(Activity activity) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(startMain);
        System.exit(0);
    }

    @Deprecated
    public static void setStatusBarSmart(View view) {
        ViewUtils.setPaddingSmart(view);
    }

    @Deprecated
    public static int getStatusBarHeight() {
        return DeviceUtils.getStatusBarHeight();
    }

    /**
     * 判断是否存在虚拟按键
     */
    public static boolean checkDeviceHasNavigationBar() {
        boolean hasNavigationBar = false;
        Resources rs = getAppContext().getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }

    /**
     * 设置屏幕的背景透明度
     *
     * @param alpha 透明度 0.0-1.0
     */
    public static void backgroundAlpha(Activity activity, float alpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = alpha;
        activity.getWindow().setAttributes(lp);
    }

}
