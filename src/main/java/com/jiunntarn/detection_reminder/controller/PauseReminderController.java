package com.jiunntarn.detection_reminder.controller;

import com.jiunntarn.detection_reminder.service.GetUndoneStudentService;
import com.jiunntarn.detection_reminder.util.TimeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PauseReminderController {
    @GetMapping("/pause_reminder")
    public static String getUndoneStudent() throws IOException {
        TimeUtil.printTime();
        //这时候还没改变值
        if (EmptyExcludeController.pauseReminder) {
            System.out.println("\tunpause reminder");
        } else {
            System.out.println("\tpause reminder");
        }

        EmptyExcludeController.pauseReminder = !EmptyExcludeController.pauseReminder;

        return "当前状态：" + (EmptyExcludeController.pauseReminder ? "今天暂停" : "不暂停");
    }
}
