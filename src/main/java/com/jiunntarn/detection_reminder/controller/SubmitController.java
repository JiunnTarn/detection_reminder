package com.jiunntarn.detection_reminder.controller;


import com.jiunntarn.detection_reminder.service.ExcludeService;
import com.jiunntarn.detection_reminder.service.SubmitService;
import com.jiunntarn.detection_reminder.util.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author JiunnTarn
 */
@Controller
@RequestMapping(value = "/submit", method = RequestMethod.GET)
public class SubmitController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index.html")
    public String submit(Model model, @RequestParam("name") String name, @RequestParam("u") String u, @RequestParam("p") String p) throws IOException {
        TimeUtil.printTime();
        System.out.println("\t" + name + " submit.");

        String status, message;

        message = SubmitService.submitService(name, u, p);
        model.addAttribute("msg", message);
        int code = ExcludeService.excludeByName(name);
        switch (code) {
            case 0: {
                status = name + " 提交成功。";
                model.addAttribute("status", status);
                return "done";
            }
            case 1: {
                status = "找不到 " + name + "。";
                model.addAttribute("status", status);
                return "retry";
            }
            case 2: {
                status = name + " 今天已经提交过了。";
                model.addAttribute("status", status);
                return "retry";
            }
            default: {
                status = "未知错误";
                model.addAttribute("status", status);
                return "retry";
            }
        }
    }
}
