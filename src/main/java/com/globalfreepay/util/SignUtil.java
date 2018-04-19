package com.globalfreepay.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil {
    private static String token = "james7whm";

    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] paramArr = new String[]{token,timestamp,nonce};
        //token,timestamp,nonce字典排序
        Arrays.sort(paramArr);
        //排序后结果拼接字符串
        String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
        String ciphertext = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha1");
            //对content进行sha1 加密
            byte[] digest = messageDigest.digest(content.getBytes());
            ciphertext = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ciphertext!=null?ciphertext.equals(signature.toUpperCase()):false;
    }
    /**
     * 字节转换十六进制字符串
     * @param ib
     * @return
     */
    private static String byteToHexStr(byte ib){
        char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] ob = new char[2];
        ob[0] = HEX_DIGITS[(ib >>> 4) & 0X0F];
        ob[1] = HEX_DIGITS[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    /**
     * 将字节数组转化成字符串
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray){
        String strDigest = "";
        for (int i = 0; i <byteArray.length ; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
}
