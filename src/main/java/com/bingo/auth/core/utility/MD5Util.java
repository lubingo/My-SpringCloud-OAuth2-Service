package com.bingo.auth.core.utility;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/6 15:12
 * @since
 */
public class MD5Util {


    /**
     * MD5
     * @param str 内容
     * @param charset 编码方式
     * @return
     */
    private static byte[]  MD5(String str ,String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md =  MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[]  result =md.digest() ;
        return  result;
    }

    /**
     * Base64
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String base64(byte[] str )   {
        String encode = Base64.encode(str );
        return encode ;
    }



    private static String encode(String  password , String charset ){
        try {
            return base64(MD5(password,charset)) ;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String str = "123456";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str.getBytes("utf-8"));
        byte[] bytes = md5.digest(); // 获取字节数

// 字节数组转换为十六进制数字显示，其中 `%032x` 是数字显示格式，`%x`表示使用十六进制显示数字，`032`表示使用该十六进制的位数为32个，数字小则开头自动补零
        System.out.println(String.format("%032x", new BigInteger(1, bytes))); // new BigInteger 的第一个参数是表示用大于0


// update 可以分开使用，效果一样
        md5 = null;
        md5 = MessageDigest.getInstance("MD5");
        md5.update("Hello,".getBytes("utf-8"));
        md5.update("world".getBytes("utf-8"));
        byte[] bytes2 = md5.digest();
        System.out.println(String.format("%032x", new BigInteger(bytes)).equals(String.format("%032x", new BigInteger(bytes2))));


        System.out.println(base64(MD5(str,"utf-8")));
    }
}
