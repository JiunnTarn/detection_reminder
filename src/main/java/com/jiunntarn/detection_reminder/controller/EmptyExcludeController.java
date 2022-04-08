package com.jiunntarn.detection_reminder.controller;

import com.jiunntarn.detection_reminder.service.EmptyExcludeService;
import com.jiunntarn.detection_reminder.util.TimeUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
@RestController
public class EmptyExcludeController {

    @GetMapping("/empty_exclude")
    @Scheduled(cron = "0 0 1 * * ?")
    public static String emptyExcludeController() throws IOException {
        TimeUtil.printTime();
        System.out.println("\tempty exclude list");

        EmptyExcludeService.emptyExclude();
        return "Done";
    }
}
