/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tool.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 关于时间日期相关操作方法的工具类。
 *
 * @author wsy
 */
public final class TimeUtils {


    /**
     * 判断现在是否是白天时间。（即：6点到18点）
     *
     * @return true：白天时间；false：晚上时间。
     */
    public static boolean isDayTime() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        return 6 <= hour && hour < 18;
    }

    /**
     * 判断两个时间戳是否是在同一天。
     *
     * @param firstTime  第一个时间戳（单位：毫秒）
     * @param secondTime 第二个时间戳（单位：毫秒）
     * @return true:是；false:否
     */
    public static boolean isSameDay(long firstTime, long secondTime) {
        YearMonthDay first = YearMonthDay.newInstance(firstTime);
        YearMonthDay second = YearMonthDay.newInstance(secondTime);
        return first.isSame(second);
    }

    /**
     * 是否是今天。
     *
     * @return true:是；false:否
     */
    public static boolean isToDay(long time) {
        if(time < 10000){
            return false;
        }
        YearMonthDay first = YearMonthDay.newInstance(time);
        YearMonthDay second = YearMonthDay.newInstance(System.currentTimeMillis());
        return first.isSame(second);
    }

    /**
     * 分钟数转换为时/分*/
    public static String getTimeFromMinute(int minute) {
        if (minute < 60) {
            return minute+"分钟";
        }
        else {
            int hour = minute / 60;
            int min = minute % 60;
            return hour+"小时"+min+"分钟";
        }
    }

    /**
     * 分钟数转换后获取其中的分钟数*/
    public static int getMinFromMinute(int minute) {
        if (minute < 60) {
            return minute;
        }
        else {
            return minute % 60;
        }
    }

    /**
     * 根据秒数转化为时分秒   00:00:00
     */
    public static String getHourTime(long second) {
        if (second < 10) {
            return "00:00:0" + second;
        }
        if (second < 60) {
            return "00:00:" + second;
        }
        if (second < 3600) {
            long minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "00:0" + minute + ":0" + second;
                }
                return "00:0" + minute + ":" + second;
            }
            if (second < 10) {
                return "00:" + minute + ":0" + second;
            }
            return "00:" + minute + ":" + second;
        }
        long hour = second / 3600;
        long minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + ":0" + minute + ":0" + second;
                }
                return "0" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "0" + hour + ":" + minute + ":0" + second;
            }
            return "0" + hour + ":" + minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":0" + minute + ":0" + second;
            }
            return hour + ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + ":" + minute + ":0" + second;
        }
        return hour + ":" + minute + ":" + second;
    }

    /**
     * 分钟数转换为小时
     * 忽略分钟数
     * */
    public static String getHourFromMinute(int minute) {
        if (minute < 60) {
            return 0+"小时";
        }
        else {
            int hour = minute / 60;
            return hour+"小时";
        }
    }

    /**
     * 分钟数转换为时/分
     * "时、分"字体减小，返回html
     * */
    public static String getTimeFromMinuteForHtml(int minute) {
        if (minute < 60) {
            return minute+"<font><small>"+"分钟"+"</big></small>";
        }
        else {
            int hour = minute / 60;
            int min = minute % 60;
            return hour+"<font><small>"+"小时"+"</big></small>"+min+"<font><small>"+"分钟"+"</big></small>";
        }
    }

    /**
     * 获取时间戳  秒。
     */
    public static long getSystemCurrentTimeSecond() {
        return System.currentTimeMillis()/1000;
    }

    /**
     * 获取当前时间
     * @param timeMillis 时间戳 毫秒（若要获取当前时间，可以传0）
     * @param format 格式(yyyy年MM月dd日 HH:mm:ss)
     * */
    public static String getCurrentTimeFormat(long timeMillis, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        if (timeMillis == 0) {
            timeMillis = System.currentTimeMillis();
        }
        Date curDate = new Date(timeMillis);//获取当前时间
        return formatter.format(curDate);
    }
}
