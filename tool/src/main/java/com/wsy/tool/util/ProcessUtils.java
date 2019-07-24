/*
 * Copyright (c) 2017 3000.com All Rights Reserved. 
 */
package com.wsy.tool.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;

import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;
import static com.wsy.tool.util.ContextUtils.getAppContext;

/**
 * 关于进程操作方法的工具类。
 *
 * @author wsy
 */
public class ProcessUtils {

    /**
     * 判断当前进程是否是应用进程。
     *
     * @param appName 应用名称
     * @return true：是；false：否。
     */
    public static boolean isAppProcess(String appName) {
        RunningAppProcessInfo info = getRunningProcess(appName);
        return info != null && Process.myPid() == info.pid;
    }

    /**
     * 获取指定名称的进程运行信息。
     *
     * @param processName 进程名称
     * @return 进程运行信息。
     */
    public static RunningAppProcessInfo getRunningProcess(String processName) {
        ActivityManager am = (ActivityManager) getAppContext().getSystemService(ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> apps = am.getRunningAppProcesses();
        for (RunningAppProcessInfo info : apps) {
            if (info.processName.equals(processName)) {
                return info;
            }
        }
        return null;
    }

    /**
     * 判断 用户是否安装QQ客户端
     */
    public static boolean isQQClientAvailable() {
        final PackageManager packageManager = getAppContext().getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equalsIgnoreCase("com.tencent.qqlite") || pn.equalsIgnoreCase("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 域名是否可用
     * 默认只ping 10秒
     */
    public static boolean isAvailableForUrlAddress(String address){
        java.lang.Process process;
        int status1 = 1;
        try {
            process = Runtime.getRuntime().exec("ping -c 1 -w 10 " + address);
            status1 = process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (status1 == 0) {
            //域名能ping通，有效
            return true;
        } else {
            //域名ping不通
            return false;
        }
    }

    /**
     * 判断某个应用是否已安装。
     *
     * @param app     应用包名
     * @return true：已安装；false：未安装。
     */
    public static boolean isAppInstalled(String app) {
        final PackageManager packageManager = getAppContext().getPackageManager();
        if(packageManager == null){
            return false;
        }
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            String name = packages.get(i).packageName;
            if (name.equals(app)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 打开第三方app
     * @param appName 包名
     * */
    public static void startOtherApp(Context context, String appName) {
        final PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(appName);
        if (intent != null) {
            context.startActivity(intent);
        }
    }

}
