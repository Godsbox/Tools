package com.wsy.tools.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2019/1/10 0010.
 */

public class ChannelUtil {

    /**
     * 获取metaData
     */
    private static String getMetaData(Context context, String key) {
        if (context == null) {
            return "member";
        }
        try {
            ApplicationInfo appInfo =
                    context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "member";
        }
    }

    /**
     * 获取渠道标识.
     */
    public static String getChannel(Context context) {
        return getMetaData(context, "UMENG_CHANNEL");
    }
}
