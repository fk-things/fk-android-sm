package com.things.fk.library.code;


import com.things.fk.library.code.cipher.Base64Cipher;

import java.security.MessageDigest;

public class Coder {

    private static final String KEY_SHA = "SHA";
    private static final String KEY_MD5 = "MD5";

    /**
     * BASE64解密
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return new Base64Cipher().decrypt(key.getBytes());
    }

    /**
     * BASE64加密
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return new String(new Base64Cipher().encrypt(key),"utf-8");
    }

    /**
     * MD5加密
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);

        return md5.digest();

    }

    /**
     * SHA加密
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }
}