package com.jiunntarn.detection_reminder.service;

import com.jiunntarn.detection_reminder.Dao.DAO;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
public class ExcludeService {
    //返回码说明
    //0:排除成功
    //1:找不到此人
    //2:排除列表中已存在此人
    public static int excludeByName(String name) throws IOException {
        DAO dao = new DAO();
        if(!dao.isStudentExistedInStudentList(name)) {
            return 1;
        } else if(dao.isStudentExistedInExcludeList(name)) {
            return 2;
        } else {
            dao.insertExclude(name);
            return 0;
        }

    }
}
