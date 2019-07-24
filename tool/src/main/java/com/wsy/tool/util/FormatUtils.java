/*
 * Copyright (c) 2017 3000.com All Rights Reserved. 
 */
package com.wsy.tool.util;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * 关于格式化操作方法的工具类。
 *
 * @author wsy
 */
public final class FormatUtils {

    private static final DecimalFormat FORMAT_INTEGER = new DecimalFormat("#,###");
    private static final DecimalFormat FORMAT_ONE_DECIMAL = new DecimalFormat("#,##0.#");
    private static final DecimalFormat FORMAT_TOW_DECIMAL = new DecimalFormat("#,###.##");

    private static final DecimalFormat FORMAT_TOW_DECIMAL_MUST = new DecimalFormat("#,###.00");

    /**
     * 将数值转化成保留两个小数位的字符串。
     *
     * @param value 数值
     * @return 浮点数字符串
     */
    public static String toTwoDecimal(double value) {
        return FORMAT_TOW_DECIMAL.format(value);
    }

    /**
     * 将数值转化成保留两个小数位的字符串。(强制 整型也保留2位)
     *
     * @param value 数值
     * @return 浮点数字符串
     */
    public static String toTwoDecimalMust(double value) {
        return FORMAT_TOW_DECIMAL_MUST.format(value);
    }

    /**
     * 将数值转化成保留一个小数位的字符串。
     *
     * @param value 数值
     * @return 浮点数字符串
     */
    public static String toOneDecimal(double value) {
        return FORMAT_ONE_DECIMAL.format(value);
    }

    /**
     * 将整数格式化成以逗号分格的字符串。如：1,000。
     *
     * @param value 整数
     * @return 格式化过的整数
     */
    public static String format(long value) {
        return FORMAT_INTEGER.format(value);
    }

    /**
     * 格式化数字，支持以w为单位的格式。
     *
     * @param value 数字
     * @return 格式化过的数字
     */
    public static String inTenThousand(long value) {
        return inTenThousand(value, "#,###.#");
    }

    /**
     * 格式化数字，支持以w为单位的格式。
     *
     * @param value 数字
     * @param pattern 格式
     * @return 格式化过的数字
     */
    public static String inTenThousand(long value, String pattern) {
        if (value >= 10000 && value < 100000000) {
            return new DecimalFormat(pattern+"万").format(value / 10000.00);
        } else if (value >= 100000000) {
            return new DecimalFormat(pattern+"亿").format(value / 100000000.00);
        }
        return new DecimalFormat("#,###").format(value);
    }

    /**
     * 格式化数字，支持以w为单位的格式。
     *
     * @param value 数字
     * @return 格式化过的数字
     */
    public static String inTenThousandW(long value) {
        if (value >= 10000 && value < 100000000) {
            return new DecimalFormat("#,###.#W").format(value / 10000.0);
        } else if (value >= 100000000) {
            return new DecimalFormat("#,###.#Y").format(value / 100000000.0);
        }
        return new DecimalFormat("#,###").format(value);
    }

    /**
     * 格式化下载速度。
     *
     * @param speed 速度。（单位：kb）
     * @return 格式化后的速度字符串值
     */
    public static String formatSpeed(int speed) {
        float div = (float) (speed / 1024.0);
        return div >= 1.0
                ? toOneDecimal(div) + "m/s"
                : speed + "k/s";
    }

    /**
     * 将给定的值转换成MB为单位。
     *
     * @param value 值
     * @return 格式化后的字符串值
     */
    public static String inMegabyte(long value) {
        double mb = (double) value / (1024 * 1024);
        return toTwoDecimal(mb);
    }

    /**
     * 是否是纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (android.text.TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }
}
