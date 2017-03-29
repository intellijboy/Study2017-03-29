package com.ylz.com.ylz.code.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by liuburu on 2017/3/28.
 */
public class ChineseUtil {

    public static String randGenerate() {


        int hightPos, lowPos; // 定义高低位
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
        lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
        byte[] b = new byte[2];
        b[0] = (new Integer(hightPos).byteValue());
        b[1] = (new Integer(lowPos).byteValue());
        String result  = "";
        try {
            result = new String(b, "GBk");//转成中文
        } catch (UnsupportedEncodingException e) {
            System.out.println("中文转换异常");
            e.printStackTrace();
        }
        return result;
    }
}
