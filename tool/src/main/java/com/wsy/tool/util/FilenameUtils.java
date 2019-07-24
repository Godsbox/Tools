/*
 * Copyright (c) 2017 3000.com All Rights Reserved.
 */
package com.wsy.tool.util;

import java.io.File;
import java.util.UUID;

/**
 * 关于文件名操作方法的工具类。
 *
 * @author wsy
 */
public final class FilenameUtils {
    /**
     * 扩展名分隔符
     */
    public static final char SEPARATOR_EXTENSION = '.';

    /**
     * Unix 路径分隔符
     */
    public static final char SEPARATOR_UNIX = '/';

    /**
     * Windows 路径分隔符
     */
    public static final char SEPARATOR_WINDOWS = '\\';

    /**
     * 系统文件路径分隔符
     */
    public static final char SEPARATOR_SYSTEM = File.separatorChar;

    /**
     * 扩展名：GIF
     */
    public static final String EXTENSION_GIF = "gif";

    /**
     * 扩展名：PNG
     */
    public static final String EXTENSION_PNG = "png";

    /**
     * 扩展名：JPG
     */
    public static final String EXTENSION_JPG = "jpg";

    /**
     * 扩展名：JPEG
     */
    public static final String EXTENSION_JPEG = "jpeg";

    /**
     * 私有构造器。
     */
    private FilenameUtils() {
    }

    /**
     * 获取文件名，不含文件路径。
     *
     * @param filename 文件名
     * @return 文件名
     */
    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfLastSeparator(filename);
        return filename.substring(index + 1);
    }

    /**
     * 获取文件名, 不含文件路径, 不含后缀。
     *
     * @param filename 文件名
     * @return 文件名
     */
    public static String getNameNoExtension(String filename){
        if (filename == null) {
            return null;
        }
        int index = indexOfLastSeparator(filename);
        return removeFileType(filename.substring(index + 1));
    }

    /**
     * 删除后缀
     * @param filePath
     * @return
     */
    public static String removeFileType(String filePath){
        int dotPos = filePath.lastIndexOf('.');
        if (0 <= dotPos) {
            return filePath.substring(0, dotPos);
        }
        return filePath;
    }

    /**
     * 获取最后一个目录名
     * @param path
     * @return
     */
    public static String getLastDir(String path){
        if (path == null) {
            return null;
        }
        String name = path.substring(0,indexOfLastSeparator(path));
        return getName(name);
    }

    /**
     * 获取文件目录，不含文件名。
     *
     * @param path 文件名
     * @return 文件目录
     */
    public static String getDir(String path) {
        if (path == null) {
            return null;
        }
        int index = indexOfLastSeparator(path);
        return path.substring(0,index+1);
    }

    /**
     * 获取文件名的扩展名。
     *
     * @param filename 文件名
     * @return 文件的扩展名。如果没有扩展名返回空字符串；如果文件名是 {@code null}，则返回  {@code null}。
     */
    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfExtension(filename);
        if (index == -1) {
            return "";
        }
        return filename.substring(index + 1);
    }

    /**
     * 返回文件扩展名的位置。
     *
     * @param filename 文件名
     * @return 扩展名的位置
     */
    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        }
        int extensionPos = filename.lastIndexOf(SEPARATOR_EXTENSION);
        int lastSeparator = indexOfLastSeparator(filename);
        return lastSeparator > extensionPos ? -1 : extensionPos;
    }

    /**
     * 返回最后一个目录分隔符的位置。
     *
     * @param filename 文件名
     * @return 最后一个目录分隔符的位置
     */
    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        return filename.lastIndexOf(SEPARATOR_UNIX);
    }

    /**
     * 判断指定的文件名是否是 Gif 文件。
     *
     * @param filename 文件名
     * @return true：是 Gif 文件；false：不是 Gif 文件
     */
    public static boolean isGif(String filename) {
        String extension = getExtension(filename);
        return isGifExtension(extension);
    }

    /**
     * 根据指定的扩展名判断是否是 GIF 扩展名。
     *
     * @param extension 扩展名
     * @return true：是 GIF 扩展名；false：不是 GIF 扩展名
     */
    public static boolean isGifExtension(String extension) {
        return EXTENSION_GIF.equalsIgnoreCase(extension);
    }

    /**
     * 判断指定的文件名是否是 PNG 文件。
     *
     * @param filename 文件名
     * @return true：是 PNG 文件；false：不是 PNG 文件
     */
    public static boolean isPng(String filename) {
        String extension = getExtension(filename);
        return isPngExtension(extension);
    }

    /**
     * 根据指定的扩展名判断是否是 PNG 扩展名。
     *
     * @param extension 扩展名
     * @return true：是 PNG 扩展名；false：不是 PNG 扩展名
     */
    public static boolean isPngExtension(String extension) {
        return EXTENSION_PNG.equalsIgnoreCase(extension);
    }

    /**
     * 判断指定的文件名是否是 JPG/JPEG 文件。
     *
     * @param filename 文件名
     * @return true：是 JPG/JPEG 文件；false：不是 JPG/JPEG 文件
     */
    public static boolean isJpgOrJpeg(String filename) {
        String extension = getExtension(filename);
        return isJpgOrJpegExtension(extension);
    }

    /**
     * 根据指定的扩展名判断是否是 JPG/JPEG 扩展名。
     *
     * @param extension 扩展名
     * @return true：是 JPG/JPEG 扩展名；false：不是 JPG/JPEG 扩展名
     */
    public static boolean isJpgOrJpegExtension(String extension) {
        return EXTENSION_JPG.equalsIgnoreCase(extension)
                || EXTENSION_JPEG.equalsIgnoreCase(extension);
    }

    /**
     * 生成新的 UUID 文件路径。
     *
     * @param extension 文件扩展名
     * @return 文件路径
     */
    public static String newRandomFilepath(String extension) {
        return newRandomFilepath(null, extension);
    }

    /**
     * 生成新的 UUID 文件路径。
     *
     * @param dir       目录路径
     * @param extension 文件扩展名
     * @return 文件路径
     */
    public static String newRandomFilepath(String dir, String extension) {
        String uuid = UUID.randomUUID().toString();
        if (dir == null) {
            dir = "";
        }
        return dir + SEPARATOR_UNIX + uuid + SEPARATOR_EXTENSION + extension;
    }

    /**
     * 根据给定的目录和文件名（含扩展名）生成新的文件路径。
     *
     * @param dir      目录路径
     * @param filename 文件名
     * @return 文件路径
     */
    public static String newFilepath(String dir, String filename) {
        return dir + SEPARATOR_UNIX + filename;
    }

    /**
     * 根据给定的目录和文件名（不含扩展名）生成新的文件路径。
     *
     * @param dir       目录路径
     * @param filename  文件名
     * @param extension 扩展名
     * @return 文件路径
     */
    public static String newFilepath(String dir, String filename, String extension) {
        return dir + SEPARATOR_UNIX + filename + SEPARATOR_EXTENSION + extension;
    }
}
