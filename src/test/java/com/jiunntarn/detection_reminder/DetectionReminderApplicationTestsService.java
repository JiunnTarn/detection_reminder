package com.jiunntarn.detection_reminder;

import com.jiunntarn.detection_reminder.service.ReminderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DetectionReminderApplicationTestsService {

    @Test
    void reminder() throws Exception {
        ReminderService.reminder();
    }

}
