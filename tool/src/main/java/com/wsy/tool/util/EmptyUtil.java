package com.wsy.tool.util;

import java.util.List;
import java.util.Map;

/**
 * 空参数判断
 *
 * @author wsy
 */
public class EmptyUtil {

    public static boolean isEmpty(String message){
        return message == null || message.length() == 0;
    }

    public static boolean isEmpty(CharSequence chars){
        return chars == null || chars.length() == 0;
    }

    public static boolean isEmpty(List list){
        return list == null || list.size() == 0;
    }

    public static boolean isEmpty(Map map){
        return map == null || map.isEmpty();
    }
}