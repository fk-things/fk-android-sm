package com.things.fk.library.code;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * crypto utils
 *
 * @author tic
 */
public class CryptUtil {

    private final static String DES = "DES";

    /**
     * encrypt
     *
     * @param src
     * @param key Multiple of 8
     * @return Data Encrypted
     * @throws Exception
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);

        // Trans DESKeySpec to SecretKey
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secureKey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, secureKey, sr);
        return cipher.doFinal(src);
    }

    /**
     * encrypt
     *
     * @param src
     * @param key   Multiple of 8
     * @param ivKey IvParameter Key
     * @return Data Encrypted
     * @throws Exception
     */
    private static byte[] encrypt(byte[] src, byte[] key, byte[] ivKey) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
        Key desKey = keyFactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(ivKey);
        cipher.init(Cipher.ENCRYPT_MODE, desKey, ips);
        byte[] bOut = cipher.doFinal(src);
        return bOut;
    }

    /**
     * Decrypt
     *
     * @param src
     * @param key Multiple of 8
     * @return Data Encrypted
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);

        // Trans DESKeySpec to SecretKey
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secureKey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, secureKey, sr);
        return cipher.doFinal(src);
    }

    /**
     * Decrypt
     *
     * @param src
     * @param key   Multiple of 8
     * @param ivKey IvParameter Key
     * @return Data Encrypted
     * @throws Exception
     */
    private static byte[] decrypt(byte[] src, byte[] key, byte[] ivKey) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
        Key desKey = keyFactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(ivKey);
        cipher.init(Cipher.DECRYPT_MODE, desKey, ips);
        byte[] bOut = cipher.doFinal(src);
        return bOut;
    }

    /**
     * Zip
     *
     * @param source
     * @return
     * @throws IOException
     */
    public static byte[] deflate(byte[] source) throws IOException {
        Deflater deflater = new Deflater(-1);
        ByteArrayOutputStream stream = null;
        byte[] result;
        try {
            deflater.setInput(source);
            deflater.finish();
            stream = new ByteArrayOutputStream(source.length);
            byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                int compressed = deflater.deflate(buffer);
                stream.write(buffer, 0, compressed);
            }
            stream.close();
            result = stream.toByteArray();
            stream = null;
        } finally {
            deflater.end();
            if (stream != null) {
                stream.close();
            }
        }
        return result;
    }

    /**
     * UnZip
     *
     * @param value
     * @return
     * @throws DataFormatException
     * @throws IOException
     */
    public static byte[] inflate(byte[] value) throws DataFormatException, IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(value.length);
        Inflater decompressor = new Inflater();
        try {
            decompressor.setInput(value);

            final byte[] buf = new byte[1024 * 8];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            decompressor.end();
        }

        return bos.toByteArray();
    }

    public static byte[] decrypt(byte data[], String key) throws Exception {
        if (data == null) {
            return null;
        }
        byte[] bt = decrypt(data, key.getBytes());
        return bt;
    }

}
