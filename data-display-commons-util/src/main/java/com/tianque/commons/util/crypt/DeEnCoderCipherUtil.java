/**
 * @author : 孙留平
 * @since : 2019年5月8日 上午10:04:58
 * @see:
 */
package com.tianque.commons.util.crypt;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.testng.util.Strings;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author : Administrator
 * @since : 2019年5月8日 上午10:04:58
 * @see :
 */
public class DeEnCoderCipherUtil {
    private final static String CIPHER_MODE = "DES";
    public static String DEFAULT_DES_KEY = "区块链是分布式数据存储、点对点传输、共识机制、加密算法等计算机技术的新型应用模式。";

    /**
     * @see :
     */
    private DeEnCoderCipherUtil() {
    }

    /**
     * 
     * @see :
     * @param :
     * @return : String
     * @param originalContent
     * @param key
     * @return
     */
    public static String encrypt(String originalContent, String key) {
        if (Strings.isNullOrEmpty(originalContent)
                || Strings.isNullOrEmpty(key)) {
            return null;
        }

        byte[] byteContent = encrypt(originalContent.getBytes(),
                key.getBytes());

        return new BASE64Encoder().encode(byteContent);
    }

    /**
     * 
     * @see :
     * @param :
     * @return : String
     * @param originalContent
     * @param key
     * @return
     */
    public static String decrypt(String cipherText, String key) {
        if (Strings.isNullOrEmpty(cipherText) || Strings.isNullOrEmpty(key)) {
            return null;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bufCipherText;
        try {
            bufCipherText = decoder.decodeBuffer(cipherText);
            byte[] contentByte = decrypt(bufCipherText, key.getBytes());
            return new String(contentByte);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 字节加密
     * 
     * @see :
     * @param :
     * @return : byte[]
     * @param originalContent
     *            明文
     * @param key
     *            加密密钥的byte数组
     * @return 密文的byte数组
     */
    private static byte[] encrypt(byte[] originalContent, byte[] key) {
        // 步骤1 生成可信任的随机数源
        SecureRandom secureRandom = new SecureRandom();

        // 2 基于密钥创建DesKeySpec对象
        DESKeySpec desKeySpec;
        try {
            desKeySpec = new DESKeySpec(key);
            // 3.创建密钥工厂，将DESKeySpec转换成SecretKey对象保存对称密钥
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance(CIPHER_MODE);
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

            // 4cipher对象实际完成的加密操作，指定支持的加密和解密算法

            Cipher cipher = Cipher.getInstance(CIPHER_MODE);

            // 5用密钥初始化cipher encrypt_mode表示加密
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);

            // 返回密文
            return cipher.doFinal(originalContent);
        } catch (InvalidKeyException | NoSuchAlgorithmException
                | NoSuchPaddingException | InvalidKeySpecException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();

            return null;
        }
    }

    /**
     * 
     * @see :
     * @param :
     * @return : String
     * @param originalContent
     * @param key
     * @return
     */
    public static byte[] decrypt(byte[] cipherTextByte, byte[] key) {

        // 步骤1 生成可信任的随机数源
        SecureRandom secureRandom = new SecureRandom();

        // 2 基于密钥创建DesKeySpec对象
        DESKeySpec desKeySpec;
        try {
            desKeySpec = new DESKeySpec(key);
            // 3.创建密钥工厂，将DESKeySpec转换成SecretKey对象保存对称密钥
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance(CIPHER_MODE);
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

            // 4cipher对象实际完成的加密操作，指定支持的加密和解密算法

            Cipher cipher = Cipher.getInstance(CIPHER_MODE);

            // 5用密钥初始化cipher encrypt_mode表示加密
            cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);

            // 返回密文
            return cipher.doFinal(cipherTextByte);
        } catch (InvalidKeyException | NoSuchAlgorithmException
                | NoSuchPaddingException | InvalidKeySpecException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
