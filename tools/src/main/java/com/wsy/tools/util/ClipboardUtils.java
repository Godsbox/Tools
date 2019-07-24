/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.net.Uri;
import static android.content.ClipData.newPlainText;
import static android.content.ClipData.newRawUri;
import static android.content.Context.CLIPBOARD_SERVICE;
import static com.wsy.tools.util.ContextUtils.getSystemService;

/**
 * 关于剪贴板操作方法的工具类。
 *
 * @author wsy
 */
public final class ClipboardUtils {

    /**
     * 私有构造器。
     */
    private ClipboardUtils() {
    }

    /**
     * 复制链接。
     *
     * @param label 标签
     * @param url   链接
     */
    public static void copyUrl(String label, String url) {
        if(EmptyUtil.isEmpty(label)){
            return;
        }
        ClipboardManager manager = getSystemService(CLIPBOARD_SERVICE);
        ClipData data = newRawUri(label, Uri.parse(url));
        manager.setPrimaryClip(data);
    }

    /**
     * 复制字符串内容。
     *
     * @param label   标签
     * @param content 内容
     */
    public static void copyString(String label, String content) {
        if(EmptyUtil.isEmpty(label)){
            return;
        }
        ClipboardManager manager = getSystemService(CLIPBOARD_SERVICE);
        ClipData data = newPlainText(label, content);
        manager.setPrimaryClip(data);
    }
}
