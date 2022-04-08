package com.jiunntarn.detection_reminder.controller;


import com.jiunntarn.detection_reminder.service.ExcludeService;
import com.jiunntarn.detection_reminder.util.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
@Controller
@RequestMapping(value = "/exclude_by_name",method = RequestMethod.GET)
public class ExcludeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/index.html")
    public String excludeByName(@RequestParam("name") String name) throws IOException {
        TimeUtil.printTime();
        System.out.println("\t" + name + " commite");
        int code = ExcludeService.excludeByName(name);
        return switch (code) {
            case 0 -> name + " 提交成功。";
            case 1 -> "找不到 " + name + "。";
            case 2 -> name + " 今天已经提交过了。";
            default -> "error";
        };

    }
}
