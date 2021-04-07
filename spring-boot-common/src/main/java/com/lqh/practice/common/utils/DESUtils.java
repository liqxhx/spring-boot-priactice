package com.lqh.practice.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * <p> 类描述: DESUtils
 *
 * @author qhlee
 * @version 1.0
 * @date 2021/04/05 08:54
 * @since 2021/04/05 08:54
 */
public class DESUtils {
    /**
     * 指定DES加密解密所用的密钥
     */
    private static Key key;
    private static String KEY_STR = "myKey";
    private static String DEF_CHARSET = "utf8";

    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(new SecureRandom(KEY_STR.getBytes()));

            key = keyGenerator.generateKey();
            keyGenerator = null;


//                    KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
//        keyGenerator.init(56);
//        SecretKey secretKey = keyGenerator.generateKey();
//        byte[] byteKey = secretKey.getEncoded();
//            DESKeySpec desKeySpec = new DESKeySpec(byteKey);
//            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
//            SecretKey convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }


    public static String getEncryptString(String str) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            byte[] strBytes = str.getBytes(DEF_CHARSET);
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // 加密模式
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return base64Encoder.encode(encryptStrBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDecryptString(String str) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] strBytes = base64Decoder.decodeBuffer(str);
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // 解密模式
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptStrBytes = cipher.doFinal(strBytes);
            return new String(decryptStrBytes, DEF_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * main
    */
    public static void main(String[] args) {
        if(args == null || args.length < 1) {
            System.out.println("请输入要加密的字符串，用空格分隔");
        }

        Arrays.stream(args).forEach(str -> System.out.println(str + ": " + getEncryptString(str)));

    }

}
