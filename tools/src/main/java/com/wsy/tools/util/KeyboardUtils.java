/*
 * Copyright (c) 2017 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT;
import static com.wsy.tools.util.ContextUtils.getSystemService;

/**
 * 关于软键盘操作方法的工具类。
 *
 * @author wsy
 */
public final class KeyboardUtils {

    /**
     * 私有构造器。
     */
    private KeyboardUtils() {
    }

    /**
     * 显示软键盘。
     *
     * @param view 需要软键盘的视图
     */
    public static void showSoftInput(View view) {
        if (view == null) {
            return;
        }

        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        InputMethodManager imm = getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, SHOW_IMPLICIT);
    }

    /**
     * 显示软键盘
     *
     * @param view  需要软键盘的视图
     * @param delay 延迟
     */
    public static void showSoftInputDelayed(final View view, int delay) {
        if (view == null) {
            return;
        }
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                showSoftInput(view);
            }
        }, delay);
    }

    /**
     * 延迟显示软键盘。
     *
     * @param view 需要软键盘的视图
     */
    public static void showSoftInputDelayed(final View view) {
        showSoftInputDelayed(view, 250);
    }

    /**
     * 隐藏软键盘。
     *
     * @param view 不再需要软键盘的视图
     */
    public static void hideSoftInput(View view) {
        if (view == null) {
            return;
        }

        InputMethodManager imm = getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        view.clearFocus();
    }

    /**
     * 隐藏软键盘。
     *
     * @param view 窗口里的某个视图，用于辨别哪个窗口
     */
    public static void hideSoftInputFromWindow(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager imm = getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 判断软键盘是否已激活。
     *
     * @return true：已激活；false：未激活。
     */
    public static boolean isActive() {
        InputMethodManager imm = getSystemService(INPUT_METHOD_SERVICE);
        return imm.isActive();
    }

    /**
     * 修复 INPUT_METHOD_MANAGER__SERVED_VIEW 引起的内存泄漏。
     *
     * @param context 上下文
     */
    public static void fixMemoryLeak(Context context) {
        if (context == null) {
            return;
        }

        InputMethodManager manager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (manager == null) {
            return;
        }

        String[] fieldNames = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Class<?> clazz = manager.getClass();
        try {
            for (String fieldName : fieldNames) {
                Field field = clazz.getDeclaredField(fieldName);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                Object object = field.get(manager);
                if (object != null && object instanceof View) {
                    View view = (View) object;
                    if (view.getContext() == context) {
                        field.set(manager, null);
                        continue;
                    }
                    break;
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
