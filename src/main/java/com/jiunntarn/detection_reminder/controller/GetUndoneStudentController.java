package com.jiunntarn.detection_reminder.controller;

import com.jiunntarn.detection_reminder.service.GetUndoneStudentService;
import com.jiunntarn.detection_reminder.util.TimeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
@RestController
public class GetUndoneStudentController {
    @GetMapping("/get_undone_student")
    public static String getUndoneStudent() throws IOException {
        TimeUtil.printTime();
        System.out.println("\tGet undone student");

        return "未完成名单：<br>" + GetUndoneStudentService.getUndoneStudent();
    }
}
