package com.jiunntarn.detection_reminder.controller;

import com.jiunntarn.detection_reminder.service.ReminderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JiunnTarn
 */
@RestController
public class ReminderController {

    @Scheduled(cron = "0 20 7,10,11,22 * * ?")
    @GetMapping("/reminder")
    public static String reminderController() throws Exception {
        return ReminderService.reminder();
    }
}
