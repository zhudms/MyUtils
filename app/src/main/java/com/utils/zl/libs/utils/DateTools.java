package com.utils.zl.libs.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class DateTools {

    public static String getDate(String format) {
        SimpleDateFormat fm = new SimpleDateFormat(format);
        return fm.format(new Date());
    }

    public static String getDate() {
        return getDate("HH:mm:ss");
    }

    public static String getCurrentDay() {
        return getDate("20yy:MM:dd");
    }

    public static String getCrashSaveDay() {
        return getDate("20yy-MM-dd-mm");
    }

    public static String getCoverDate() {
        return getDate("20yy:MM:dd HH:mm:ss");
    }

    public static String getLineDate() {
        return getDate("20yy-MM-dd HH-mm-ss");
    }


    public static String getOldDate(int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -count);    //得到前一天
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("20yy:MM:dd");
        return df.format(date);
    }

}
