/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

/**
 * 关于字符串操作方法的工具类。
 *
 * @author wsy
 */
public final class StringUtils {

    public static final  String WEB_URL_REGE
            = "(((http|https)://)|((?<!((http|https)://))www\\.))"  // 以http...或www开头
            + ".*?"                                                                   // 中间为任意内容，惰性匹配
            + "(?=(&nbsp;|\\s|　|<br />|$|[<>]|、|\\uff08|[\\u4E00-\\u9FA5]))"; // 空格 换行 、 中文 （ 等结尾

    private StringUtils() {
    }

    /**
     * 判断给定的字符串是否为空。
     *
     * @param string 字符串
     * @return true：为空或null；false：不为空。
     */
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * 获取给定字符串的长度。
     *
     * @param string 字符串
     * @return 字符串长度
     */
    public static int getLength(String string) {
        return isEmpty(string) ? 0 : string.length();
    }

    /**
     * 将字符串转换成整型。
     *
     * @param string 字符串
     * @return 整型
     */
    public static int toInt(String string) {
        return toInt(string, 0);
    }

    /**
     * 将字符串转换成整型。
     *
     * @param string       字符串
     * @param defaultValue 默认值
     * @return 整型
     */
    public static int toInt(String string, int defaultValue) {
        int result = defaultValue;
        if (isEmpty(string)) {
            return result;
        }
        try {
            result = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            // 忽略
        }
        return result;
    }

    /**
     * 将字符串转换成浮点型。
     *
     * @param string 字符串
     * @return 浮点型
     */
    public static double toDouble(String string) {
        return toDouble(string, 0);
    }

    /**
     * 将字符串转换成浮点型。
     *
     * @param string       字符串
     * @param defaultValue 默认值
     * @return 浮点型
     */
    public static double toDouble(String string, double defaultValue) {
        double result = defaultValue;
        if (isEmpty(string)) {
            return result;
        }
        try {
            result = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            // 忽略
        }
        return result;
    }

    /**
     * 清除字符串头尾里的控制字符、空白字符和换行字符。
     *
     * @param str 待清除的字符串
     * @return 清除过的字符串
     */
    public static String trim(String str) {
        if (str == null) {
            return null;
        }
        int start = 0;
        int last = str.length() - 1;
        int end = last;
        while ((start <= end) && isBlankChar(str.charAt(start))) {
            start++;
        }
        while ((end >= start) && isBlankChar(str.charAt(end))) {
            end--;
        }
        if (start == 0 && end == last) {
            return str;
        }
        return str.substring(start, end + 1);
    }

    /**
     * 清除字符串里的控制字符、空白字符和换行字符和空白字符。
     *
     * @param str 待清除的字符串
     * @return 清除过的字符串
     */
    public static String trimAll(String str) {
        if (EmptyUtil.isEmpty(str)) {
            return "";
        }
        str = str.replaceAll(" ", ""); //去掉所有空格，包括首尾、中间
        str = str.replaceAll(" +","");  //去掉所有空格，包括首尾、中间
        str = str.replaceAll("\\s*", ""); //可以替换大部分空白字符， 不限于空格 ；
        return str;
    }

    /**
     * 判断给定的字符是否是空白的。
     *
     * @param c 字符
     * @return true：空白的；false：非空白的。
     */
    public static boolean isBlankChar(char c) {
        return c <= ' ' || c == '\n';
    }

    /**
     * 过滤掉所有 HTML 标签。
     *
     * @param string 字符串
     * @return 过滤后的字符串
     */
    public static String escapeHtml(String string) {
        return isEmpty(string) ? string : string.replaceAll("\\<.*?>","");
    }

    /**
     * 格式化指定的大小为兆字节字符串。
     *
     * @param size 待格式化的大小
     * @return 兆字节字符串
     */
    public static String inMegabyte(long size) {
        double mb = (double) size / (1024 * 1024);
        return FormatUtils.toTwoDecimal(mb);
    }

    /**
     * 获取字节数组长度(一个汉字占2个字节，其他占1个字节)
     *
     * @param s 字符串
     * @return 字节数组长度
     */
    public static int getByteLength(String s) {
        int length = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(i, i + 1);
            if (sub.matches(chinese)) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length;
    }

    /**
     * 替换换行
     * @param content
     * @return
     */
    public static String replaceOtherLine(String content){
        return content.replace("\r\n","  ");
    }
}