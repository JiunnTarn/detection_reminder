package com.jiunntarn.detection_reminder.controller;

import com.jiunntarn.detection_reminder.service.ReminderService;
import com.jiunntarn.detection_reminder.util.TimeUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JiunnTarn
 */
@RestController
public class ReminderController {

    @Scheduled(cron = "0 20 7,12,13,14,22 * * ?")
    @GetMapping("/reminder")
    public static String reminderController() throws Exception {
        TimeUtil.printTime();
        System.out.print("\tRun reminder : ");

        return ReminderService.reminder();
    }
}
