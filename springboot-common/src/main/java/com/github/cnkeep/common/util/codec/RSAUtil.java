package com.github.cnkeep.common.util.codec;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA工具
 *
 * @Author LeiLi.Zhang
 * @Date 2019/2/20
 * @Version 1.0.0
 */
public class RSAUtil {
    public static final String ENCRYPTIONAL_GORITHM = "RSA";

    /**
     * 根据RSA公钥串返回PublicKey
     * @param base64PublicKey
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String base64PublicKey) throws Exception {
        // 1. 先通过Base64解码
        byte[] publicKeyBytes = Base64.getDecoder().decode(base64PublicKey.getBytes());

        // 2. 解码后的数据封装成PublicKey
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        return publicKey;
    }

    /**
     * 根据RSA私钥串返回PrivateKey
     * @param base64PrivateKey
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String base64PrivateKey) throws Exception {
        // 1. 先通过Base64解码
        byte[] privateKeyBytes = Base64.getDecoder().decode(base64PrivateKey);

        // 2. 解码后的数据封装成PrivateKey
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    public static byte[] encryptByPublicKeyWithBase64(String base64PublicKey, String data) throws Exception {
        // 1. 获取公钥
        PublicKey publicKey = getPublicKey(base64PublicKey);

        // 2. 执行加密
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = encryptCipher.doFinal(data.getBytes());

        // 3. Base64编码
        byte[] encodedBase64Bytes = Base64.getEncoder().encode(encryptedBytes);

        return encodedBase64Bytes;
    }

    public static byte[] encryptByPublicKey(String base64PublicKey, String data) throws Exception {
        // 1. 获取公钥
        PublicKey publicKey = getPublicKey(base64PublicKey);

        // 2. 执行加密
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = encryptCipher.doFinal(data.getBytes());

        return encryptedBytes;
    }

    public static byte[] encryptByPrivateKey(String base64PrivateKey, String data) throws Exception {
        // 1. 获取公钥
        PrivateKey privateKey = getPrivateKey(base64PrivateKey);

        // 2. 执行加密
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptedBytes = encryptCipher.doFinal(data.getBytes());

        return encryptedBytes;
    }

    public static byte[] encryptByPrivateKeyWithBase64(String base64PrivateKey, String data) throws Exception {
        // 1. 获取公钥
        PrivateKey privateKey = getPrivateKey(base64PrivateKey);

        // 2. 执行加密
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptedBytes = encryptCipher.doFinal(data.getBytes());

        // 3. Base64编码
        byte[] encodedBase64Bytes = Base64.getEncoder().encode(encryptedBytes);

        return encodedBase64Bytes;
    }

    /**
     * 用公钥解密
     * @param base64PublicKey
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(String base64PublicKey, String encryptedData) throws Exception {
        // 1. 获取公钥
        PublicKey publicKey = getPublicKey(base64PublicKey);

        // 2. Base64解码
        byte[] decodeBase64Bytes = Base64.getDecoder().decode(encryptedData.getBytes());

        // 3. 解密
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedBytes = decryptCipher.doFinal(decodeBase64Bytes);

        return decryptedBytes;
    }

    /**
     * 用私钥解密
     * @param base64PrivateKey
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String base64PrivateKey, String encryptedData) throws Exception {
        // 1. 获取公钥
        PrivateKey privateKey = getPrivateKey(base64PrivateKey);

        // 2. Base64解码
        byte[] decodeBase64Bytes = Base64.getDecoder().decode(encryptedData.getBytes());

        // 3. 解密
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = decryptCipher.doFinal(decodeBase64Bytes);

        return decryptedBytes;
    }

    /**
     * 用私钥解密
     * @param base64PrivateKey
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKeyWithHex(String base64PrivateKey, String encryptedData) throws Exception {
        // 1. 获取公钥
        PrivateKey privateKey = getPrivateKey(base64PrivateKey);

        // 2. Base64解码
        byte[] decodeBase64Bytes =HexUtil.hexStr2Bytes(encryptedData);

        // 3. 解密
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = decryptCipher.doFinal(decodeBase64Bytes);

        return decryptedBytes;
    }

}
