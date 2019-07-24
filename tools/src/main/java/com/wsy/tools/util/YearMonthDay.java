/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * @author wsy
 */
public class YearMonthDay {
    /**
     * 年。
     */
    private int mYear;

    /**
     * 月。
     */
    private int mMonth;

    /**
     * 日。
     */
    private int mDay;

    public YearMonthDay(int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
    }

    /**
     * 获取日期。
     *
     * @return 日期
     */
    public int getDay() {
        return mDay;
    }

    /**
     * 获取月份。
     *
     * @return 月份（1~12）
     */
    public int getMonth() {
        return mMonth;
    }

    /**
     * 获取年份。
     *
     * @return 年份
     */
    public int getYear() {
        return mYear;
    }

    /**
     * 判断年月日是否一样。
     *
     * @param yearMonthDay 另一个年月日
     * @return true：一样；false：不一样。
     */
    public boolean isSame(YearMonthDay yearMonthDay) {
        return getYear() == yearMonthDay.getYear()
                && getMonth() == yearMonthDay.getMonth()
                && getDay() == yearMonthDay.getDay();
    }

    /**
     * 根据指定的时间创建一个年月日。
     *
     * @param time 时间（单位：毫秒）
     * @return 年月日
     */
    public static YearMonthDay newInstance(long time) {
        if(String.valueOf(time).length() < 11){
            time *= 1000;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.get(YEAR);
        int month = calendar.get(MONTH) + 1;
        int day = calendar.get(DAY_OF_MONTH);
        return new YearMonthDay(year, month, day);
    }

    public String getMonthDay() {
        StringBuffer times = new StringBuffer();
        if(getMonth() < 10){
            times.append(0);
        }
        times.append(getMonth()).append("月");
        if(getDay() < 10){
            times.append(0);
        }
        times.append(getDay()).append("日");
        return times.toString();
    }
}
