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
        String res;
        int cnt = 0;
        DAO dao = new DAO();
        List<String> list = dao.getUndoneStudent(new Settings(ReminderService.getTime()));
        if(list.isEmpty()){
            return "All Done.";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (String i : list) {
                cnt++;
                stringBuilder.append("<br>").append(i);
            }
            res = stringBuilder.toString();
        }
        return res + "<br><br>共" + cnt + "人";
    }
}
