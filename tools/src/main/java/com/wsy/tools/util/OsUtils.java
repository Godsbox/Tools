/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.KITKAT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static android.os.Build.VERSION_CODES.M;

/**
 * 关于操作系统操作方法的工具类。
 *
 * @author wsy
 */
public final class OsUtils {

    /**
     * 判断当前操作系统代码是否大于或等于 Honeycomb。
     *
     * @return true：是，false：不是
     */
    public static boolean asOfKitKat() {
        return SDK_INT >= KITKAT;
    }

    /**
     * 判断当前操作系统代码是否大于或等于 Marshmallow。
     *
     * @return true：是，false：不是
     */
    public static boolean asOfMarshmallow() {
        return SDK_INT >= M;
    }

    /**
     * 判断当前操作系统代码是否大于或等于 Lollipop。
     *
     * @return true：是，false：不是
     */
    public static boolean asOfLollipop() {
        return SDK_INT >= LOLLIPOP;
    }

    /**
     * 判断当前操作系统是否是 Lollipop。
     *
     * @return true：是；false：否。
     */
    public static boolean isLollipop() {
        return SDK_INT == LOLLIPOP;
    }
}
