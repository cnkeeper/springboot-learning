package com.github.cnkeep.common.util.codec;

import com.github.cnkeep.common.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

/**
 * 描述: AES加密工具, 此工具一般与Base64Util搭配使用
 *
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date ${DATE}
 */
public class AesUtil {

    private static final String KEY_ALGORITHM = "AES";
    // Algorithm/Mode/Padding
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    /** 默认编码格式utf-8*/
    private static final Charset DEFAULT_CHARSET_UTF8 = Charset.forName("utf-8");
    /**
     * 128 bit IV, don't change this value unless you're sure of what you're doing.
     */
    private static final IvParameterSpec IV = new IvParameterSpec("q7gMNtbWoxJvyiHi".getBytes(DEFAULT_CHARSET_UTF8));

    /**
     * Encrypt the string
     *
     * @param text      data to be encrypted
     * @param secretKey secretKey , 16 bit of numbers and letters
     */
    public static byte[] encrypt(byte[] text, String secretKey) throws Exception {
        Assert.notNull(text, "text can't be null");
        Assert.notNull(secretKey, "secretKey can't be null");
        Assert.isTrue(secretKey.length() == 16, "secretKey length must be 16 characters");

        SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(DEFAULT_CHARSET_UTF8), KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, IV);

        return cipher.doFinal(text);
    }

    /**
     * Decrypt the string
     *
     * @param content   data to decrypt
     * @param secretKey secretKey , 16 bit of numbers and letters
     */
    public static byte[] decrypt(byte[] content, String secretKey) throws Exception {
        Assert.notNull(content, "content can't be null");
        Assert.notNull(secretKey, "secretKey can't be null");
        Assert.isTrue(secretKey.length() == 16, "secretKey length must be 16 characters");

        SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(DEFAULT_CHARSET_UTF8), KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, IV);

        return cipher.doFinal(content);
    }

}