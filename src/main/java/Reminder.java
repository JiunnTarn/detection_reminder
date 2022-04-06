import com.JiunnTarn.mybatis.DAO.DAO;

import com.JiunnTarn.mybatis.setting.Time;
import com.JiunnTarn.mybatis.setting.Settings;
import com.JiunnTarn.mybatis.setting.Who;
import com.JiunnTarn.sign.Sign;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;

import java.util.*;

/**
 * @author JiunnTarn
 */
public class Reminder {
    public static void main(String[] args) throws Exception {
        Settings settings = new Settings(getTime());
        String who = settings.getWho() == Who.ZJJ ? "朱嘉俊" : "徐宇翔";
        String textContent = "";
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=99d3f226f6770bc23f77ac9794ffed569ae4886192865bf5def4a2e5c5de58e2" + Sign.sign());
        OapiRobotSendRequest request = new OapiRobotSendRequest();

        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        if (getTime() == Time.MORNING) {
            markdown.setTitle("你今天需要做核酸哦！");
            textContent = "### 今天这些同学需要做核酸\n" +
                    "### 确认好了跟" + who + "说下[天使]\n\n" +
                    "> " + DAO.getAtString(settings) + "\n";
        } else if (getTime() == Time.AFTERNOON) {
            markdown.setTitle("明天记得做核酸哦！");
            textContent = "### 明天这些同学需要做核酸\n\n" +
                    "> " + DAO.getAtString(settings) + "\n";
        }
        markdown.setText(textContent);
        request.setMarkdown(markdown);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(DAO.getAtList(settings));
        request.setAt(at);

        OapiRobotSendResponse response = client.execute(request);
        if (response.isSuccess()) {
            System.out.println();
            System.out.println("Done");
        } else {
            System.out.println();
            System.out.println("Error");
        }
    }

    public static Time getTime() {
        GregorianCalendar ca = new GregorianCalendar();
        ca.setTime(new Date());
        if (ca.get(GregorianCalendar.AM_PM) == Calendar.AM) {
            return Time.MORNING;
        } else if (ca.get(GregorianCalendar.AM_PM) == Calendar.PM) {
            return Time.AFTERNOON;
        }
        return null;
    }

}
