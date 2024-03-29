/*
 * Copyright (c) 2017 3000.com All Rights Reserved.
 */
package com.wsy.tool.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;

import com.gyf.barlibrary.OSUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.content.Context.TELEPHONY_SERVICE;
import static android.provider.Settings.Secure.ANDROID_ID;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;
import static com.wsy.tool.util.ContextUtils.getAppContext;
import static com.wsy.tool.util.ContextUtils.getSystemService;

/**
 * 关于设备操作方法的工具类。
 *
 * @author wsy
 */
public final class DeviceUtils {

    private final String TAG = getClass().getSimpleName();

    private String deviceId;
    /**
     * 私有构造器。
     */
    private DeviceUtils() {
    }

    /**
     * 获取唯一设备 ID。
     *
     * @return 唯一设备 ID  该方法现在处于测试实验阶段。
     */
    public static String getUniquePsuedoId() {
        // If all else fails, if the user does have lower than API 9 (lower
        // than Gingerbread), has reset their device or 'Secure.ANDROID_ID'
        // returns 'null', then simply the ID returned will be solely based
        // off their Android device information. This is where the collisions
        // can happen.
        // Thanks http://www.pocketmagic.net/?p=1662!
        // Try not to use DISPLAY, HOST or ID - these items could change.
        // If there are collisions, there will be overlapping data
        String most = "35"
                + (Build.BOARD.length() % 10)
                + (Build.BRAND.length() % 10)
                + (Build.CPU_ABI.length() % 10)
                + (Build.DEVICE.length() % 10)
                + (Build.MANUFACTURER.length() % 10)
                + (Build.MODEL.length() % 10)
                + (Build.PRODUCT.length() % 10);

        // Thanks to @Roman SL!
        // http://stackoverflow.com/a/4789483/950427
        // Only devices with API >= 9 have android.os.Build.SERIAL
        // http://developer.android.com/reference/android/os/Build.html#SERIAL
        // If a user upgrades software or roots their device, there will be a duplicate entry
        String serial = null;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
        } catch (Exception exception) {
            serial = "serial";
        }
        String deviceId = getAndroidDeviceId();
        String least = deviceId + serial;

        // Thanks @Joe!
        // http://stackoverflow.com/a/2853253/950427
        // Finally, combine the values we have found by using the UUID class to create a unique identifier
        String psuedoId = new UUID(most.hashCode(), least.hashCode()).toString();
        //Timber.d("build:%s\nserial:%s\ndevice_id:%s\npsuedoId:%s\n", most, serial, deviceId, psuedoId);
        return psuedoId;
    }

    /**
     * 获取设备 ID。
     *
     * @return 设备 ID
     */
    public static String getAndroidDeviceId() {
        int flag = ContextCompat.checkSelfPermission(getAppContext(), READ_PHONE_STATE);
        if (flag == PERMISSION_GRANTED) {
            TelephonyManager manager = getSystemService(TELEPHONY_SERVICE);
            String deviceId = manager.getDeviceId();
            return deviceId == null ? "device_id" : deviceId;
        } else {
            return "device_id";
        }
    }

    /**
     * 获取小写 手机品牌
     *
     * @return
     */
    private static String getLowBrand() {
        if (EmptyUtil.isEmpty(Build.BRAND)) {
            return "unKnow";
        }
        return Build.BRAND.toLowerCase();
    }

    /**
     * 获取小写 手机型号
     *
     * @return
     */
    private static String getLowTrimModel() {
        if (EmptyUtil.isEmpty(Build.MODEL)) {
            return "unKnow";
        }
        return StringUtils.trimAll(Build.MODEL.toLowerCase());
    }

    /**
     * 是否是相同型号的手机
     *
     * @return
     */
    public static boolean isSamePhoneType(String target) {
        if (!EmptyUtil.isEmpty(target)) {
            target = StringUtils.trimAll(target).toLowerCase();
            if (target.contains(getLowBrand()) && target.contains(getLowTrimModel())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取 Android ID。
     *
     * @return Android ID
     */
    public static String getAndroidId() {
        return Settings.Secure.getString(getAppContext().getContentResolver(), ANDROID_ID);
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight() {
        int result = 24;
        int resId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = Resources.getSystem().getDimensionPixelSize(resId);
        } else {
            result = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    result, Resources.getSystem().getDisplayMetrics());
        }
        return result;
    }

    /**
     * 是否是状态栏特殊机型
     */
    public static boolean isStatusBarSpecialModel() {
        String model = Build.MODEL;
        if (!TextUtils.isEmpty(model)) {
            if (model.contains("A33") || model.equals("OPPO R7")) {
                return true;
            }
            //三星
            if (model.toUpperCase().contains("N9008S")) {
                return true;
            }
            //将所有android 4.4/5.0/5.1的手机标记为特殊机型,除魅族/小米/华为外
            return !OSUtils.isFlymeOS()
                    && !OSUtils.isMIUI()
                    && !OSUtils.isEMUI3_x()
                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                    && Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
        }
        return false;
    }

    public String getDeviceId() {
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        deviceId = getAndroidDeviceId();
        String KEY_DEVICE_ID = "ANDROID_DEVICE_ID";
        if (!TextUtils.isEmpty(deviceId)) {
            SPUtils.putString(KEY_DEVICE_ID, deviceId);
            return deviceId;
        }
        deviceId = SPUtils.getString(KEY_DEVICE_ID);
        if (!TextUtils.isEmpty(deviceId)) {
            String id = getDeviceIdFromExternalStorage();
            if (TextUtils.isEmpty(id) || !deviceId.equals(id)) {
                save2ExternalStorage(deviceId);
            }
            return deviceId;
        } else {
            deviceId = getDeviceIdFromExternalStorage();
            if (!TextUtils.isEmpty(deviceId)) {
                SPUtils.putString(KEY_DEVICE_ID, deviceId);
                return deviceId;
            }
        }
        deviceId = getUUID();
        if (!TextUtils.isEmpty(deviceId)) {
            //新生成的id要保存起来
            save2ExternalStorage(deviceId);
            SPUtils.putString(KEY_DEVICE_ID, deviceId);
        }
        return deviceId;
    }

    /**
     * 从外部存储的公共目录上读取
     */
    private String getDeviceIdFromExternalStorage() {
        String filePath = getDeviceIdFilePath();
        File file = new File(filePath);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e1) {

                }
            }
            return sb.toString();
        }
    }

    /**
     * 获取外部存在的完整文件路径
     */
    private String getDeviceIdFilePath() {
        String dirPath;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + File.separator;
        } else {
            dirPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "Documents" + File.separator;
        }
        File file = new File(dirPath + ".DEVICE_ID.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "设备id文件路径:" + file.getPath());
        return file.getPath();
    }

    private void save2ExternalStorage(String content) {
        String filePath = getDeviceIdFilePath();
        BufferedWriter writer = null;
        try {
            File f = new File(filePath);
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f, false), "UTF-8");
            writer = new BufferedWriter(write);
            writer.write(content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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

    private String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
