package com.JiunnTarn.mybatis.setting;

import java.util.List;

/**
 * @author JiunnTarn
 */
public class Settings {
    static Who who = Who.ZJJ;

    public static List<Integer> getTail() {
        if (who == Who.ZJJ) {
            return List.of(4, 5, 6, 7, 8);
        } else if (who == Who.XYX) {
            return List.of(9, 0, 1, 2, 3);
        }
        return null;
    }

    public static Who getWho() {
        return who;
    }
}

