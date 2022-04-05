import com.JiunnTarn.mybatis.mapper.DataMapper;
import com.JiunnTarn.mybatis.pojo.Data;
import com.JiunnTarn.mybatis.setting.Time;
import com.JiunnTarn.mybatis.setting.Settings;
import com.JiunnTarn.mybatis.setting.Who;
import com.JiunnTarn.sign.Sign;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
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

        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        if (getTime() == Time.MORNING) {
            textContent = "今天这些同学需要做核酸，确认好了跟" + who + "说下[天使]";
        } else if (getTime() == Time.AFTERNOON) {
            textContent = "明天这些同学需要做核酸";
        }
        text.setContent(textContent);
        request.setText(text);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(getAtList(settings));
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

    public static List<String> getAtList(Settings settings) throws IOException {
        List<String> res = new ArrayList<>();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DataMapper mapper = sqlSession.getMapper(DataMapper.class);
        List<Integer> tailList = settings.getTail();
        assert tailList != null;
        for (Integer i : tailList) {
            List<Data> result = mapper.getDataByTail(i);
            for (Data data : result) {
                res.add(data.getPhone());
            }
        }
        return res;
    }

}
