package com.jiunntarn.detection_reminder.controller;

import com.jiunntarn.detection_reminder.service.GetUndoneStudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GetUndoneStudentController {
    @GetMapping("/get_undone_student")
    public static String getUndoneStudent() throws IOException {
        return GetUndoneStudentService.getUndoneStudent();
    }
}
