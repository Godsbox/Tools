/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

import static android.os.Looper.getMainLooper;
import static android.os.Looper.myLooper;

/**
 * 关于 Looper 操作方法的工具类。
 *
 * @author wsy
 */
public final class LooperUtils {
    /**
     * 私有构造方法。
     */
    private LooperUtils() {
    }

    /**
     * 判断是否在主线程里运行。
     *
     * @return true：是；false：否。
     */
    public static boolean inMainThread() {
        return myLooper() == getMainLooper();
    }
}
