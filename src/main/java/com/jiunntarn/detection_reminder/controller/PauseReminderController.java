package com.jiunntarn.detection_reminder.controller;

import com.jiunntarn.detection_reminder.service.EmptyExcludeService;
import com.jiunntarn.detection_reminder.service.GetUndoneStudentService;
import com.jiunntarn.detection_reminder.util.TimeUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
@RestController
public class PauseReminderController {
    public static boolean pauseReminder;

    @GetMapping("/pause_reminder")
    public static String pauseReminder() {
        TimeUtil.printTime();
        //这时候还没改变值
        if (PauseReminderController.pauseReminder) {
            System.out.println("\tunpause reminder.");
        } else {
            System.out.println("\tpause reminder.");
        }

        pauseReminder = !pauseReminder;

        return "当前状态：" + (PauseReminderController.pauseReminder ? "今天暂停" : "不暂停");
    }

    @Scheduled(cron = "0 0 19 * * ?")
    public static String unpauseReminder() {
        TimeUtil.printTime();
        System.out.println("\tunpause reminder.");

        pauseReminder = false;
        return "Done";
    }

}
