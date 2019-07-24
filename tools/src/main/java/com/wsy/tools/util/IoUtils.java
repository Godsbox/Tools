/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 关于文件 IO 相关操作的工具类。
 *
 * @author wsy
 */
public final class IoUtils {

    /**
     * 私有构造方法。
     */
    private IoUtils() {
    }

    /**
     * 将字符串内容写出到文件中。
     *
     * @param string 字符串内容
     * @param os 文件输出流
     * @return true：成功；false：失败。
     */
    public static boolean writeToFile(String string, OutputStream os) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(os);
            writer.write(string);
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 读取文件内容，并存放在字符串中。
     *
     * @param is 文件读取流
     * @return 内容字符串
     */
    public static String readFromFile(InputStream is) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            try {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            return "";
        }
        return content.toString();
    }
}
