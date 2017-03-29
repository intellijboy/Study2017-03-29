package com.ylz.com.ylz.code.util;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by liuburu on 2017/3/28.
 */
public class AuthCodeUtilTest {
    @Test
    public void getAuthCode() throws Exception {
//        System.out.println(AuthCodeUtil.getAuthCode());
//        System.out.println(getText1());
//        char a = '人';
//        System.out.println((int)a);
//        System.out.println((char)20154);
        for(int i=0;i<100;i++){
            System.out.println(getRandomChar());
        }

    }
    @Test
    public void create() throws Exception {
        for(int i=0;i<100;i++){
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            str = new String(b, "GBk");//转成中文
            System.err.println(str);
        }

    }

    //在0x4e00---0x9fa5之间产生一个随机的字符
    //u4e00~u9fff
    public static char getRandomChar()
    {
        return (char)(0x4e00+(int)(Math.random()*(0x9fa5-0x4e00+1)));
    }

    public static String getText1() {
        int length = 4;
        String finalWord = "", firstWord = "";
        int tempInt = 0;
        String[] array = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "b", "c", "d", "e", "f","g","h","i","j","k","l","m","n",
                "o","p","q","r","s","t","u","v","w","x","y","z" };

        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            switch (rand.nextInt(array.length)) {
                case 1:
                    tempInt = rand.nextInt(26) + 65;
                    firstWord = String.valueOf((char) tempInt);
                    break;
                case 2:
                    int r1,
                            r2,
                            r3,
                            r4;
                    String strH,
                            strL;// high&low
                    r1 = rand.nextInt(3) + 11; // 前闭后开[11,14)
                    if (r1 == 13) {
                        r2 = rand.nextInt(7);
                    } else {
                        r2 = rand.nextInt(16);
                    }

                    r3 = rand.nextInt(6) + 10;
                    if (r3 == 10) {
                        r4 = rand.nextInt(15) + 1;
                    } else if (r3 == 15) {
                        r4 = rand.nextInt(15);
                    } else {
                        r4 = rand.nextInt(16);
                    }

                    strH = array[r1] + array[r2];
                    strL = array[r3] + array[r4];

                    byte[] bytes = new byte[2];
                    bytes[0] = (byte) (Integer.parseInt(strH, 16));
                    bytes[1] = (byte) (Integer.parseInt(strL, 16));

                    firstWord = new String(bytes);
                    break;
                default:
                    tempInt = rand.nextInt(10) + 48;
                    firstWord = String.valueOf((char) tempInt);
                    break;
            }
            finalWord += firstWord;
        }
        return finalWord;
    }

}