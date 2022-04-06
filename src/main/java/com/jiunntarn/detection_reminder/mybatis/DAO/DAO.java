package com.jiunntarn.detection_reminder.mybatis.DAO;

import com.jiunntarn.detection_reminder.mybatis.mapper.DataMapper;
import com.jiunntarn.detection_reminder.mybatis.pojo.Data;
import com.jiunntarn.detection_reminder.mybatis.setting.Settings;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DAO {
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

    public static String getAtString(Settings settings) throws IOException {
        List<String> dataList = getAtList(settings);
        StringBuilder res = new StringBuilder();
        for (String i : dataList) {
            res.append("@").append(i).append(" ");
        }
        return res.toString();
    }
}
