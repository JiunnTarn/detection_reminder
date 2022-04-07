package com.jiunntarn.detection_reminder.service;

import com.jiunntarn.detection_reminder.Dao.DAO;
import com.jiunntarn.detection_reminder.setting.Settings;
import com.jiunntarn.detection_reminder.setting.Time;
import com.jiunntarn.detection_reminder.setting.Who;
import com.jiunntarn.detection_reminder.util.SignUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.jiunntarn.detection_reminder.util.TimeUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author JiunnTarn
 */
public class ReminderService {

    public static String reminder() throws Exception {
        TimeUtil.printTime();
        System.out.println("\tRan reminder");

        DAO dao = new DAO();

        Settings settings = new Settings(getTime());
        String who = settings.getWho() == Who.ZJJ ? "朱嘉俊" : "徐宇翔";
        String textContent = "";
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=99d3f226f6770bc23f77ac9794ffed569ae4886192865bf5def4a2e5c5de58e2" + SignUtil.sign());
        OapiRobotSendRequest request = new OapiRobotSendRequest();

        List<String> atList = dao.getAtList(settings);
        if(!atList.isEmpty()) {
            request.setMsgtype("markdown");
            OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
            if (getTime() == Time.MORNING) {
                markdown.setTitle("今天这些同学需要做核酸");
                textContent = "### 今天这些同学需要做核酸\n" + "### 确认好了跟" + who + "说下[天使]\n\n" + "> " + dao.getAtString(settings) + "\n";
            } else if (getTime() == Time.AFTERNOON) {
                markdown.setTitle("明天这些同学需要做核酸");
                textContent = "### 明天这些同学需要做核酸\n\n" + "> " + dao.getAtString(settings) + "\n";
            }
            markdown.setText(textContent);
            request.setMarkdown(markdown);

            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setAtMobiles(atList);
            request.setAt(at);

            System.out.println();
            System.out.print("at: ");
            System.out.println(request.getAt());

            return request.getAt();

//        OapiRobotSendResponse response = client.execute(request);
//        if (response.isSuccess()) {
//            System.out.println("Done");
//        } else {
//            System.out.println("Error");
//        }
        } else {
            return "Already Done.";
        }
    }

    public static Time getTime() {
        Calendar cal=Calendar.getInstance();
        int h=cal.get(Calendar.HOUR_OF_DAY);
        if (h < 17) {
            return Time.MORNING;
        } else if (h >= 17) {
            return Time.AFTERNOON;
        }
        return null;
    }

}
