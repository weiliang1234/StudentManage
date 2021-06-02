package com.booy.ssm.exam.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:wl
 * @Date:2021/5/14 16:06
 * @projectName:ssmdemo
 */
public class DataUtils {

    /**
     * 将字符串转为时间格式
     * @param date
     * @return
     */
    public static Date SimpleDateFormat(String date) {
        Date date1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    /**
     * 将Date类型转为字符串类型
     * @param date
     * @return
     */
    public static String format(Date date) {
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
        String date1 = dateformat.format(date);
        return date1;
    }
}
