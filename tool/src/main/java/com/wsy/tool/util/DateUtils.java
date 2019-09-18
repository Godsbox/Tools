package com.wsy.tool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期Util类 
 *
 * @author wsy
 */
public class DateUtils
{
    private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";

    private static String datePattern = "yyyy-MM-dd";

    /**
     * 获得默认的 date pattern 
     */
    public static String getDatePattern()
    {
        return defaultDatePattern;
    }

    /**
     * 获得默认的 date pattern你
     */
    public static String getDatePatternSimple()
    {
        return datePattern;
    }

    /**
     * 返回预设Format的当前日期字符串 
     */
    public static String getToday()
    {
        Date today = new Date();
        return format(today);
    }

    /**
     * 使用预设Format格式化Date成字符串 
     */
    public static String format(Date date)
    {
        return date == null ? " " : format(date, getDatePattern());
    }

    /**
     * 使用参数Format格式化Date成字符串 
     */
    public static String format(Date date, String pattern)
    {
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 使用预设格式将字符串转为Date
     */
    public static Date parse(String strDate) throws ParseException
    {
        return StringUtils.isEmpty(strDate) ? null : parse(strDate,
                getDatePattern());
    }

    /**
     * 使用预设格式将字符串转为long
     */
    public static long parseTolong(String strDate)
    {
        Date date = null;
        try {
            date = StringUtils.isEmpty(strDate) ? null : parse(strDate,
                    getDatePattern());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return date == null ? 0 : date.getTime();
        }
    }

    /**
     * 时间戳转日期
     * */
    public static String parseLong(long timestamp, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(timestamp));
    }

    /**
     * 使用参数Format将字符串转为Date 
     */
    public static Date parse(String strDate, String pattern)
            throws ParseException
    {
        return StringUtils.isEmpty(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
    }

    /**
     * 在日期上增加数个整月 
     */
    public static Date addMonth(Date date, int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    public static String getLastDayOfMonth(String year, String month)
    {
        Calendar cal = Calendar.getInstance();
        // 年  
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        // 月，因为Calendar里的月是从0开始，所以要-1  
        // cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);  
        // 日，设为一号  
        cal.set(Calendar.DATE, 1);
        // 月份加一，得到下个月的一号  
        cal.add(Calendar.MONTH, 1);
        // 下一个月减一为本月最后一天  
        cal.add(Calendar.DATE, -1);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号  
    }

    public static Date getDate(String year, String month, String day)
            throws ParseException
    {
        String result = year + "- "
                + (month.length() == 1 ? ("0 " + month) : month) + "- "
                + (day.length() == 1 ? ("0 " + day) : day);
        return parse(result);
    }

    /**
     * 获取当月天数*/
    public static int getCurrentMonthLastDay()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天
        calendar.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当月当天*/
    public static int getCurrentDay()
    {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当月当天*/
    public static int getCurrentMonth()
    {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 系统获取，默认1=周日，2周一....
     * */
    public static int getWeekDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String getWeekDay(int weekDay) {
        int curWeekDay = DateUtils.getWeekDay();
        if (curWeekDay == 1) {
            curWeekDay = 7;
        }
        else {
            curWeekDay = curWeekDay - 1;
        }
        switch (weekDay) {
            case 1:
                return isToDay(curWeekDay, weekDay) + "一";
            case 2:
                return isToDay(curWeekDay, weekDay) + "二";
            case 3:
                return isToDay(curWeekDay, weekDay) + "三";
            case 4:
                return isToDay(curWeekDay, weekDay) + "四";
            case 5:
                return isToDay(curWeekDay, weekDay) + "五";
            case 6:
                return isToDay(curWeekDay, weekDay) + "六";
            case 7:
                return isToDay(curWeekDay, weekDay) + "日";
            default:
                return "";
        }
    }

    private static String isToDay(int curDay, int weekDay) {
        if (curDay == weekDay) {
            return "周";
        }
        return "";
    }

}  