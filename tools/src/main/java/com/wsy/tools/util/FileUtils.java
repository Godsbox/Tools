/*
 * Copyright (c) 2017 3000.com All Rights Reserved. 
 */
package com.wsy.tools.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 文件系统工具类
 *
 * @author wsy
 */
public class FileUtils {

    /**
     * 计算文件夹的大小，采用递归文件夹方式。（单位：字节）
     *
     * @param directory 文件夹
     * @return 文件夹的大小
     */
    public static long sizeOfDirectory(File directory) {
        long size = 0L;

        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            return size;
        }

        final File[] files = directory.listFiles();
        if (files == null) // 权限受限制时为空
        {
            return size;
        }

        for (final File file : files) {
            try {
                if (!isSymlink(file)) {
                    size += sizeOf(file);
                    if (size < 0) {
                        break;
                    }
                }
            } catch (IOException ioe) {
                // Ignore exceptions caught when asking if a File is a symlink.
            }
        }

        return size;
    }

    /**
     * 计算文件的大小。（单位：字节）
     *
     * @param file 文件
     * @return 文件的大小
     */
    public static long sizeOf(File file) {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }

        if (!file.exists()) {
            return 0L;
        }

        if (file.isDirectory()) {
            return sizeOfDirectory(file);
        }
        return file.length();
    }

    /**
     * 判断指定的文件是否是符合链接。
     *
     * @param file 待判断的文件
     * @return true：是；false：不是。
     */
    public static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }

        File fileInCanonicalDir = null;
        if (file.getParent() == null) {
            fileInCanonicalDir = file;
        } else {
            File canonicalDir = file.getParentFile().getCanonicalFile();
            fileInCanonicalDir = new File(canonicalDir, file.getName());
        }

        return !fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile());
    }

    /**
     * 判断给定的文件路径对应的文件是否存在。
     *
     * @param filepath 文件路径
     * @return true：存在；false：不存在。
     */
    public static boolean exists(String filepath) {
        if (filepath == null || filepath.trim().length() <= 0) {
            return false;
        }
        try {
            File f = new File(filepath);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 修改文件名
     *
     * @param oldName
     * @param newName
     * @return
     */
    public static boolean renameFile(String oldName, String newName) {
        File file = new File(oldName);
        File newFile = new File(newName);
        file.renameTo(newFile);
        return newFile.exists();
    }

    /**
     * 获取文件MD5
     * @param file
     * @return  使用commons-codec 来获取MD5
     */
    public static String getMD5(File file) {
        String md5 = "";
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            md5 = new String(Hex.encodeHex(DigestUtils.md5(in)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return md5;
    }

    /**
     * 获取文件MD5
     * @param file
     * @return 会出现缺少一个0的问题
     */
    public static String getMD5Three(File file) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[4096];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi.toString(16);
    }

    /**
     * 获取文件MD5
     * @param file 会出现缺少一个0的问题
     * @return
     */
    public static String getMd5ByFile(File file){
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 获取网络文件大小
     * @param path
     * @return
     * @throws IOException
     */
    public static int getNetFileLength(String path){
        int length=0;
        URL url;
        try {
            url = new URL(path);
            HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
            //根据响应获取文件大小
            length = urlCon.getContentLength();
            urlCon.disconnect();
        } catch (MalformedURLException e) {
            return 0;
        } catch (IOException e) {
            return 0;
        }
        return length;
    }
}
