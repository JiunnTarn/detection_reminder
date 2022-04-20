package com.jiunntarn.detection_reminder.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JiunnTarn
 */
public class TimeUtil {
    public static void printTime() {
        Date nowTime = new Date();
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print(sdFormatter.format(nowTime));
    }

    public static String todayFormat() {
        Date todayFormat = new Date();
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return sdFormatter.format(todayFormat);
    }

    public static long todayMilliseconds() {
        Date now = new Date();
        Date todayMilliseconds = new Date(now.getYear(), now.getMonth(), now.getDate());
        return todayMilliseconds.getTime();
    }

    public static long nowMilliseconds() {
        Date now = new Date();
        return now.getTime();
    }
}
