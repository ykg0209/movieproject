/**
 * User: 杨
 * Date: 2020/12/2
 * Time: 15:45
 * Class ScannerUtil
 */

package com.ykg.util;

import com.ykg.model.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ScannerUtil {
    private static Scanner sc = new Scanner(System.in);

    /**
     * 选择Y/N
     * @return
     */
    public static char readYN(){
        char c = ' ';
        while (true){
            String s = readKeyBoard(1,false).toUpperCase();
            c = s.charAt(0);
            if ('Y' != c && 'N' != c ){
                System.out.print("输入有误，请重新输入（Y/N）：");
                continue;
            }
            return c;
        }
    }

    /**
     * 读入一个整数
     * @param limit
     * @return
     */
    public static int readInt(int limit) {
        int i = 0;
        while (true) {
            String s = readKeyBoard(limit, false);
            try {
                i = Integer.parseInt(s);
                if (i < 0){
                    System.out.print("请输入正确的序号（INDEX）:");
                    continue;
                }
            } catch (Exception e) {
                System.out.print("请输入正确的序号（INDEX）:");
                continue;
            }
            break;
        }
        return i;
    }

    /**
     * 在规定范围内选择序号
     * @param limit 序号的最大值
     * @return
     */
    public static int readCount(int limit){
        //假设现在影片数目在1000以内
        int i = 0;
        while (true){
           try {
               String s = readKeyBoard(3, false);
               i = Integer.parseInt(s);
               if (limit < i){
                   System.out.print("请输入正确的序号（INDEX）：");
                   continue;
               }
           }catch (Exception e){
               System.out.print("请输入正确的序号（INDEX）：");
               continue;
           }
           break;
        }
        return i;
    }

    /**
     * 读入一个指定日期类型
     *
     * @param pattern 指定的日期类型
     * @return
     */
    public static Date readDate(String pattern) {
        Date date = null;
        while (true) {
            String date_str = readKeyBoard(pattern.length(), false);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try {
                date = sdf.parse(date_str);
            } catch (ParseException e) {
                System.out.print("时间格式输入有误，请重新输入：");
                continue;
            }
            break;
        }
        return date;
    }

    /**
     * 读入一个指定日期类型（有默认值）
     * @param pattern
     * @param defaultDate
     * @return
     */
    public static Date readDate(String pattern,Date defaultDate) {
        Date date1 = null;
        while (true) {
            String date_str = readKeyBoard(pattern.length(), true);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            if (date_str.length() ==0){
                date1 = defaultDate;
                break;
            }
            try {
                date1 = sdf.parse(date_str);
            } catch (ParseException e) {
                System.out.print("时间格式输入有误，请重新输入：");
                continue;
            }
            break;
        }
        return date1;
    }

    /**
     * 从控制台读入一个字符串
     *
     * @param limit 指定最大长度
     * @return 字符串
     */
    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }

    /**
     * (有默认值)从控制台读入一个字符串
     * @param limit
     * @param defaultMovieParam 默认值
     * @return
     */
    public static String readString(int limit, String defaultMovieParam){
        String s = readKeyBoard(limit, true);

        return s.length() == 0 ?defaultMovieParam:s;
    }
    /**
     * 从控制台输入选择的值
     *
     * @param size 菜单最大序号
     * @return char
     */
    public static char readMenuSelect(int size) {
        char c = ' ';
        while (true) {
            String s = readKeyBoard(1, false);
            c = s.charAt(0);
            boolean r = true;
            for (int i = 1; i <= size; i++) {
                r = (c != (i + '0'));
                // 当输入字符c是其中一个正确数字不再比较
                if (!r) {
                    break;
                }
            }
            if (r) {
                System.out.print("选择有误，请重新输入：");
                continue;
            }
            break;
        }
        return c;

    }


    /**
     * 从控制台指定要求获取输入的一行数据
     *
     * @param limit       输入的字符最大数量
     * @param blankReturn 是否无数人直接回车进行提交 true：可不输入
     * @return String
     */
    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            line = line.trim();   //去除前后空格
            //判断是否为空，以及为空是否需要重新输入
            if (line.length() == 0) {
                if (blankReturn) {
                    return line;
                } else {
                    continue;
                }
            }
            // 判断长度是个满足要求
            if (line.length() > limit) {
                System.out.print("你输入的长度(不能大于" + limit + ")有误。请重新输入:");
                continue;
            }
            break;
        }
        return line;
    }


}
