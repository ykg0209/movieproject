/**
 * User: 杨康广
 * Date: 2020/12/3
 * Time: 14:38
 * Class RandomUtil
 */

package com.ykg.util;

import java.util.Random;

public class RandomUtil {

    /**
     * 通过时间毫秒值获取随机数
     * @return
     */
    public static int getRandomInt(){
        long time = System.currentTimeMillis();
        String s = (time + "").substring(7);
        Random r = new Random();
        int i = r.nextInt(100);
        s += i;
        return Integer.parseInt(s);
    }
}
