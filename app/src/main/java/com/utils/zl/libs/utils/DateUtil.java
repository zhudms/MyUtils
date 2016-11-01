package com.utils.zl.libs.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by TANG on 2016/3/5.
 */
public class DateUtil {

    private static final String TAG = "DateUtil:";

    private static final String MONTH = "月";
    private static final String DAY = "日";
    private static final String DATA_FORMAT = "20yy-MM-dd";
    public static final String FORMAT_WITHHOUR = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_WITHDAY = "yyyy-MM-dd";
    public static final String FORMAT_WITHSECOND = "yyyy-MM-dd HH:mm:ss";//H 24小时制
    private static final String TIME_FORAMT = "HH:mm";

    private static final int ONE_SECOND = 1000;
    private static final int ONE_MIN = ONE_SECOND * 60;
    private static final int ONE_HOUR = ONE_MIN * 60;
    public static final int ONE_DAY = ONE_HOUR * 24;


    public static String getDate(String format) {
        SimpleDateFormat fm = new SimpleDateFormat(format);
        return fm.format(new Date());
    }

    /**
     * 今天显示时间 ，早于今年的显示年 月，日
     * (当时间为空时，与下面的方法返回值不同)
     *
     * @param time
     * @param dataFormat
     * @return
     */
    public static String getDateToShow(String time, String dataFormat) {
        if (TextUtils.isEmpty(time)) {
            return "未知";
        }


        Calendar currCalendar = Calendar.getInstance();
        currCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currCalendar.set(Calendar.MINUTE, 0);


        Date mData = getDateByString(time, dataFormat);
        Calendar tagCalendar = Calendar.getInstance();
        tagCalendar.setTime(mData);

        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(mData);

        int yearTag = tempCalendar.get(Calendar.YEAR);
        int yearCurr = currCalendar.get(Calendar.YEAR);

        int monthTag = tempCalendar.get(Calendar.MONTH) + 1;
        int monthCurr = currCalendar.get(Calendar.MONTH) + 1;

        int dayTag = tempCalendar.get(Calendar.DAY_OF_MONTH);
        int dayCurr = currCalendar.get(Calendar.DAY_OF_MONTH);


        if (yearTag != yearCurr) {
            return new StringBuffer().append(yearTag).append("年").append(monthTag).append(MONTH).append(dayTag).append(DAY).toString();
        }
        else if (monthTag != monthCurr) {
            if ((currCalendar.getTimeInMillis() - tempCalendar.getTimeInMillis()) / ONE_DAY == 0) {//24小时之内且不是今天(这里逻辑绝对有问题，有时间细看)

                return "昨天";
            }
            return new StringBuffer().append(monthTag).append(MONTH).append(dayTag).append(DAY).toString();
        }
        else {
            //是今天
            if (dayTag == dayCurr) {
                return new StringBuffer().append(String.format(VALUES.DATA_FORMATER, tagCalendar.get(Calendar.HOUR_OF_DAY))).
                        append(VALUES.HOUR_LINKER).append(String.format(VALUES.DATA_FORMATER, tagCalendar.get(Calendar.MINUTE))).toString();
            }
            else if ((currCalendar.getTimeInMillis() - tempCalendar.getTimeInMillis()) / ONE_DAY == 0) {//24小时之内且不是今天
                return "昨天";
            }

        }


        return new StringBuffer().append(monthTag).append(MONTH).append(dayTag).append(DAY).toString();
    }

    /**
     * 今天显示时间 ，早于今年的显示年 月，日
     *
     * @param time
     * @param dataFormat
     * @return
     */
    public static String getReadDateToShow(String time, String dataFormat) {//
        if (TextUtils.isEmpty(time)) {
            return "未读";
        }


        Calendar currCalendar = Calendar.getInstance();
        currCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currCalendar.set(Calendar.MINUTE, 0);


        Date mData = getDateByString(time, dataFormat);
        Calendar tagCalendar = Calendar.getInstance();
        tagCalendar.setTime(mData);

        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(mData);


        int yearTag = tempCalendar.get(Calendar.YEAR);
        int yearCurr = currCalendar.get(Calendar.YEAR);

        int monthTag = tempCalendar.get(Calendar.MONTH) + 1;
        int monthCurr = currCalendar.get(Calendar.MONTH) + 1;

        int dayTag = tempCalendar.get(Calendar.DAY_OF_MONTH);
        int dayCurr = currCalendar.get(Calendar.DAY_OF_MONTH);


        if (yearTag != yearCurr) {
            return new StringBuffer().append(yearTag).append("年").append(monthTag).append(MONTH).append(dayTag).append(DAY).toString();
        }
        else if (monthTag != monthCurr) {
            if ((currCalendar.getTimeInMillis() - tempCalendar.getTimeInMillis()) / ONE_DAY == 0) {//24小时之内且不是今天(这里逻辑绝对有问题，有时间细看)

                return "昨天";
            }
            return new StringBuffer().append(monthTag).append(MONTH).append(dayTag).append(DAY).toString();
        }
        else {
            //是今天
            if (dayTag == dayCurr) {
                return new StringBuffer().append(String.format(VALUES.DATA_FORMATER, tagCalendar.get(Calendar.HOUR_OF_DAY))).
                        append(VALUES.HOUR_LINKER).append(String.format(VALUES.DATA_FORMATER, tagCalendar.get(Calendar.MINUTE))).toString();
            }
            else if ((currCalendar.getTimeInMillis() - tempCalendar.getTimeInMillis()) / ONE_DAY == 0) {//24小时之内且不是今天
                return "昨天";
            }

        }


        return new StringBuffer().append(monthTag).append(MONTH).append(dayTag).append(DAY).toString();
    }

    /**
     * 按照规则格式化时间
     *
     * @param
     * @return
     * @time 2016/10/28
     * @auth TANG
     */
    public static Date getDateByString(String time, String dataFormat) {

        SimpleDateFormat df = new SimpleDateFormat(dataFormat);
        Date date = null;
        try {
            date = df.parse(time);
        } catch (Exception ex) {
            LogUtil.d("asd", TAG + ex.toString());
        }
        return date;

    }


    public static String getCurrentDateFormated(String format) {
        return getDate(DATA_FORMAT);
    }


    /**
     * 需要格式化成yy-MM-dd 格式
     * 格式化错误会抛异常
     *
     * @param time
     * @param tag
     * @return time 是否比tag 更晚
     */
    public static boolean isAfter(String time, String tag, String dateFormat) {
        boolean isAfter = false;
        try {

            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            Date date = format.parse(time);
            Date dateTag = format.parse(tag);
            isAfter = date.after(dateTag);
        } catch (Exception e) {
            e.printStackTrace();
            //ParseException
        }
        return isAfter;
    }


    /**
     * 传入值是否比当前时间晚
     *
     * @param time
     * @param dateFormat
     * @return
     * @throws ParseException
     */
    public static boolean isAfterCurrent(String time, String dateFormat) {
        boolean isAfter = false;
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            Date date = format.parse(time);
            Date dateCurrent = new Date(System.currentTimeMillis());
            isAfter = date.after(dateCurrent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAfter;
    }


    /**
     * 当前时间是否比传入时间早
     *
     * @param time
     * @return
     */
    public static boolean isBefore(long time) {

        Date date = new Date(System.currentTimeMillis());
        return date.before(new Date(time));
    }
}
