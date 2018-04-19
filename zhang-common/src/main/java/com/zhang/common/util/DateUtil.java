package com.zhang.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Edison
 * @ClassName:
 * @Desc:日期工具类
 * @date 2017/10/16
 * @history
 */
public class DateUtil {

    private static Log log = LogFactory.getLog(DateUtil.class);
    private static ThreadLocal<SimpleDateFormat> localSdf = new ThreadLocal<SimpleDateFormat>();

    /**
     * 日期格式 yyyy-MM-dd
     */
    public static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 时间戳格式 yyyy-MM-dd HH:mm:ss
     */
    public static String DATE_TIME_PATTERN = DATE_PATTERN + " HH:mm:ss";

    /**
     * 日期格式 yyyyMMdd
     */
    public static String DATE_CUSTOM = "yyyyMMdd";

    /**
     * 时间格式 HHmmss
     */
    public static String TIME_CUSTOM = "HHmmss";

    /**
     * 日期时间格式 yyyyMMddHHmmss
     */
    public static String DATE_TIME_CUSTOM = "yyyyMMddHHmmss";

    /**
     * 返回日期字符串 yyyy-MM-dd
     * @param date
     * @return
     */
    public static final String getDate(Date date){
        SimpleDateFormat df = localSdf.get();
        String returnValue = "";
        if (date != null){
            if (df == null){
                df = new SimpleDateFormat();
                localSdf.set(df);
            }
            df.applyPattern(DATE_PATTERN);
            returnValue = df.format(date);
        }
        return returnValue;
    }

    /**
     * 返回时间部分 HH:mm:ss
     *
     * @param datetime
     * @return
     */
    public static final String getTimePart(Date datetime) {
        return DateToString(datetime, "HH:mm:ss");
    }

    /**
     * 字符串转日期
     * @param strDate
     * @param mask
     * @return
     */
    public static final Date StringToDate(String strDate, String mask){
        SimpleDateFormat df = localSdf.get();
        Date date = null;
        if (strDate != null && !"".equals(strDate)) {
            if (df == null) {
                df = new SimpleDateFormat();
                localSdf.set(df);
            }
        }
        try {
            df.applyPattern(mask);
            date = df.parse(strDate);
        } catch (ParseException pe) {
            log.error("ParseException: " + pe);
        }
        return date;
    }

    /**
     * 日期转字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String DateToString(Date date, String pattern){
        SimpleDateFormat df = localSdf.get();
        if (df == null){
            df = new SimpleDateFormat();
            localSdf.set(df);
        }
        df.applyPattern(pattern);
        return df.format(date);
    }

    /**
     * @desc 获取yyyyMMdd
     */
    public static String getdatestr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_CUSTOM);
        String str = sdf.format(date);
        return str;
    }

    /**
     * @desc 获取HHmmss
     */
    public static String gettimestr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_CUSTOM);
        String str = sdf.format(date);
        return str;
    }

    /**
     * @desc 获取yyyyMMddHHmmss
     */
    public static String getdatetimestr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_CUSTOM);
        String str = sdf.format(date);
        return str;
    }

}
