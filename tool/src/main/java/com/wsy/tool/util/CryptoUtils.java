/*
 * Copyright (c) 2017 3000.com All Rights Reserved. 
 */
package com.wsy.tool.util;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static java.lang.Integer.toHexString;
import static javax.crypto.Cipher.ENCRYPT_MODE;

/**
 * 关于加密/解密操作方法的工具类。
 *
 * @author wsy
 */
public final class CryptoUtils {

    /**
     * AES 模式。
     */
    private static final String AES_MODE = "AES/CBC/NoPadding";

    /**
     * 字符编码。
     */
    private static final String ENCODING = "UTF-8";

    private CryptoUtils() {
    }

    /**
     * AES 加密。
     *
     * @param key    密钥
     * @param iv     IV
     * @param source 明文
     * @return 密文
     */
    public static String encryptAes(String key, String iv, String source) {
        try {
            Cipher cipher = Cipher.getInstance(AES_MODE);
            int blockSize = cipher.getBlockSize();
            byte[] sourceBytes = source.getBytes(ENCODING);
            int inputLength = sourceBytes.length;
            if (inputLength % blockSize != 0) {
                inputLength = inputLength + (blockSize - (inputLength % blockSize));
            }
            byte[] input = new byte[inputLength];
            System.arraycopy(sourceBytes, 0, input, 0, sourceBytes.length);

            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(ENCODING), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(ENCODING));
            cipher.init(ENCRYPT_MODE, keySpec, ivSpec);
            byte[] bytes = cipher.doFinal(input);
            return Base64.encodeToString(bytes, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取给定字符串的 MD5 消息摘要。
     *
     * @param source 字符串
     * @return 消息摘要
     */
    public static String getMd5Digest(String source) {
        try {
            byte[] bytes = source.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);

            byte[] digest = messageDigest.digest();
            StringBuilder builder = new StringBuilder();
            for (byte item : digest) {
                String hex = toHexString(0xff & item);
                if (hex.length() == 1) {
                    builder.append("0");
                }
                builder.append(hex);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
