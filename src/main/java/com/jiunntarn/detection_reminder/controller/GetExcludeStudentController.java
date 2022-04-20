package com.jiunntarn.detection_reminder.controller;

import com.jiunntarn.detection_reminder.service.GetExcludeStudentService;

import com.jiunntarn.detection_reminder.util.TimeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
@RestController
public class GetExcludeStudentController {
    @GetMapping("/get_exclude_student")
    public static String getExcludeStudent() throws IOException {
        TimeUtil.printTime();
        System.out.println("\tGet exclude student.");

        return "已完成名单：<br>" + GetExcludeStudentService.getExcludeStudent();
    }
}
