package com.jiunntarn.detection_reminder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author JiunnTarn
 */
@MapperScan(basePackages = {"com.jiunntarn.detection_reminder.mappers"})
@EnableScheduling
@SpringBootApplication
public class DetectionReminderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DetectionReminderApplication.class, args);
    }

}
