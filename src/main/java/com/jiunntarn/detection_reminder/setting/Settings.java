package com.jiunntarn.detection_reminder.setting;

import java.util.Date;
import java.util.List;

/**
 * @author JiunnTarn
 */
public class Settings {
    private Who who;
    private List<Integer> tail;

    public List<Integer> getTail() {
        return tail;
    }

    public Who getWho() {
        return who;
    }

    public Settings(Time dayPart) {
        Date date = new Date();
        //%tj表示一年中的第几天
        int dayFromStart = Integer.parseInt(String.format("%tj", date));
        if (dayPart == Time.AFTERNOON) {
            dayFromStart++;
        }
        switch (dayFromStart % 3) {
            case 0 -> {
                tail = List.of(1, 2, 3, 4);
                who = Who.XYX;
            }
            case 1 -> {
                tail = List.of(5, 6, 7);
                who = Who.ZJJ;
            }
            case 2 -> {
                tail = List.of(8, 9, 0);
                who = Who.ZJJ;
            }
            default -> {
            }
        }
    }
}


