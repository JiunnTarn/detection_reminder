package com.jiunntarn.detection_reminder.service;

import com.jiunntarn.detection_reminder.Dao.DAO;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
public class EmptyExcludeService {
    public static void emptyExclude() throws IOException {
        DAO dao = new DAO();
        dao.emptyExclude();
    }

}
