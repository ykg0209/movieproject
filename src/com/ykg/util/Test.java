/**
 * User: 杨
 * Date: 2020/12/2
 * Time: 20:30
 * Class Test
 */

package com.ykg.util;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
//        boolean a = true;
//        boolean b = false;
//        System.out.println(a&&b);

        Date date = ScannerUtil.readDate("yyyy-MM-dd");
        System.out.println(date);
    }
}
