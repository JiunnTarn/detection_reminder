package com.jiunntarn.detection_reminder.controller;


import com.jiunntarn.detection_reminder.service.ExcludeService;
import com.jiunntarn.detection_reminder.service.SubmitService;
import com.jiunntarn.detection_reminder.util.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
@Controller
@RequestMapping(value = "/submit",method = RequestMethod.GET)
public class SubmitController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/index.html")
    public String submit(@RequestParam("name") String name, @RequestParam("u") String u, @RequestParam("p") String p) throws IOException {
        TimeUtil.printTime();
        System.out.println("\t" + name + " submit.");

        String message = "";

        message = SubmitService.submitService(name,u,p);
        int code = ExcludeService.excludeByName(name);
        switch (code) {
            case 0 -> message += "<br>" + name + " 提交成功。";
            case 1 -> message += "<br>" +"找不到 " + name + "。";
            case 2 -> message += "<br>" + name + " 今天已经提交过了。";
            default -> message += "<br>" + "error";
        }
        return message;
    }
}
