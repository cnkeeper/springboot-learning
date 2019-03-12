package com.github.cnkeep.common.util.codec;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 描述: Base64工具类
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/2
 */
public final class Base64Util {
    /** 编解码方式 */
    private final static Charset UTF8_CHARSET = Charset.forName("utf-8");

    /**
     * base64 解码
     * @param str
     * @return
     */
    public static final String decode(String str){
        return decode(str.getBytes(UTF8_CHARSET));
    }

    public static final String decode(byte[] bytes){
        byte[] decodeBytes = Base64.getDecoder().decode(bytes);
        return new String(decodeBytes,UTF8_CHARSET);
    }

    /**
     * base64 编码
     * @param str
     * @return
     */
    public static final String encode(String str){
        return encode(str.getBytes(UTF8_CHARSET));
    }

    public static final String encode(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }
}
