package com.github.cnkeep.web.util.codec;

import com.github.cnkeep.common.test.util.codec.Base64Util;
import org.junit.Assert;
import org.junit.Test;

import java.text.MessageFormat;

/**
 * 描述： 测试Base64Util
 * @author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date ${DATE}
 */
public class Base64UtilTest {


    /**
     * 测试字母数字的Base64编解码
     */
    @Test
    public void testLetter(){
        String originStr = "abcd1234";
        System.out.println(MessageFormat.format("原文：{0}",originStr));

        //base64编码
        String encodeByBase64 = Base64Util.encode(originStr);
        System.out.println(MessageFormat.format("Base64编码：{0}",encodeByBase64));

        //base64解码
        String decodeStr = Base64Util.decode(encodeByBase64);
        System.out.println(MessageFormat.format("Base64解码：{0}",decodeStr));
        Assert.assertTrue(originStr.equals(decodeStr));
    }

    /**
     * 测试中文的Base64编解码
     */
    @Test
    public void testChinese(){
        String originStr = "你好,00";
        System.out.println(MessageFormat.format("原文：{0}",originStr));

        //base64编码
        String encodeByBase64 = Base64Util.encode(originStr);
        System.out.println(MessageFormat.format("Base64编码：{0}",encodeByBase64));

        //base64解码
        String decodeStr = Base64Util.decode(encodeByBase64);
        System.out.println(MessageFormat.format("Base64解码：{0}",decodeStr));

        Assert.assertTrue(originStr.equals(decodeStr));
    }
}