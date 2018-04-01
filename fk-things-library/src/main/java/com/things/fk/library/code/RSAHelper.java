package com.things.fk.library.code;

/**
 * Created by wulb
 * com.lwtx.base.rsautil
 * 功能描述:
 * 2016/6/16 17:26
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSAHelper {

    //创建密钥对生成器，指定加密和解密算法为RSA
    public String[] Skey_RSA(int keylen) {//输入密钥长度
        String[] output = new String[5]; //用来存储密钥的e n d p q
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(keylen); //指定密钥的长度，初始化密钥对生成器
            KeyPair kp = kpg.generateKeyPair(); //生成密钥对
            RSAPublicKey puk = (RSAPublicKey) kp.getPublic();
            RSAPrivateCrtKey prk = (RSAPrivateCrtKey) kp.getPrivate();
            BigInteger e = puk.getPublicExponent();
            BigInteger n = puk.getModulus();
            BigInteger d = prk.getPrivateExponent();
            BigInteger p = prk.getPrimeP();
            BigInteger q = prk.getPrimeQ();
            output[0] = e.toString();
            output[1] = n.toString();
            output[2] = d.toString();
            output[3] = p.toString();
            output[4] = q.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSAHelper.class.getName())
                  .log(Level.SEVERE, null, ex);
        }
        return output;
    }

    //加密 在RSA公钥中包含有两个整数信息：e和n。对于明文数字m,计算密文的公式是m的e次方再与n求模。
    public String Enc_RSA(String mingwen, String eStr, String nStr) {
        String miwen = new String();
        try {
            BigInteger e = new BigInteger(eStr);
            BigInteger n = new BigInteger(nStr);
            byte[] ptext = mingwen.getBytes("UTF8"); //获取明文的大整数
            BigInteger m = new BigInteger(ptext);
            BigInteger c = m.modPow(e, n);
            miwen = c.toString();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RSAHelper.class.getName())
                  .log(Level.SEVERE, null, ex);
        }
        return miwen;
    }

    //解密
    public String Dec_RSA(String miwen, String dStr, String nStr) {
        StringBuffer mingwen = new StringBuffer();
        BigInteger d = new BigInteger(dStr);//获取私钥的参数d,n
        BigInteger n = new BigInteger(nStr);
        BigInteger c = new BigInteger(miwen);
        BigInteger m = c.modPow(d, n);//解密明文
        byte[] mt = m.toByteArray();//计算明文对应的字符串并输出
        for (int i = 0; i < mt.length; i++) {
            mingwen.append((char) mt[i]);
        }
        return mingwen.toString();
    }
}