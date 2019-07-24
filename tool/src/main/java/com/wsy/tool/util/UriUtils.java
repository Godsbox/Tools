/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tool.util;

import static android.content.ContentResolver.SCHEME_ANDROID_RESOURCE;
import static com.wsy.tool.util.ContextUtils.getAppContext;

/**
 * 关于URI操作方法的工具类。
 *
 * @author wsy
 */
public final class UriUtils {

    private UriUtils() {
    }

    /**
     * 将资源 ID 转换成 URI 字符串。
     *
     * @param resourceId 资源 ID
     * @return 资源 URI 字符串
     */
    public static String getUriString(int resourceId)
    {
        return new StringBuilder(SCHEME_ANDROID_RESOURCE)
                .append("://")
                .append(getAppContext().getPackageName())
                .append("/")
                .append(resourceId)
                .toString();
    }
}
