package com.jiunntarn.detection_reminder.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static void printTime() {
        Date nowTime = new Date();
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print(sdFormatter.format(nowTime));
    }
}
