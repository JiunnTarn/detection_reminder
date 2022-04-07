package com.jiunntarn.detection_reminder.controller;


import com.jiunntarn.detection_reminder.service.ExcludeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
@RestController
public class ExcludeController {

    @GetMapping("/exclude_by_name")
    public String excludeByName(@RequestParam("name") String name) throws IOException {
        int code = ExcludeService.excludeByName(name);
        switch (code) {
            case 0:
                return name + " 提交成功。";
            case 1:
                return "找不到" + name + "。";
            case 2:
                return name + " 今天已经提交过了。";
            default:
                return "error";
        }

    }
}
