package com.jiunntarn.detection_reminder.service;

import com.jiunntarn.detection_reminder.Dao.DAO;
import com.jiunntarn.detection_reminder.setting.Settings;

import java.io.IOException;
import java.util.List;

/**
 * @author JiunnTarn
 */
public class GetUndoneStudentService {
    public static String getUndoneStudent() throws IOException {
        String res = "";
        DAO dao = new DAO();
        List<String> list = dao.getUndoneStudent(new Settings(ReminderService.getTime()));
        if(list.isEmpty()){
            return "All Done.";
        } else {
            for (String i : list) {
                res = res + "\n" + i;
            }
        }
        return res;
    }
}
