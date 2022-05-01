package com.jiunntarn.detection_reminder.controller;

import com.jiunntarn.detection_reminder.util.OkHttpUtil;
import com.jiunntarn.detection_reminder.util.TimeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
@RestController
public class ClearCookieJarController {
    @GetMapping("/clear_cookie_jar")
    public static String clearCookieJar() throws IOException {
        TimeUtil.printTime();
        System.out.println("\tClear cookie jar.");

        OkHttpUtil.clearCookieJar();
        return "Done.";
    }
}
