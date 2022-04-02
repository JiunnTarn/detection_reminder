package com.jiunntarn.mybatis.test;

import com.JiunnTarn.mybatis.mapper.DataMapper;
import com.JiunnTarn.mybatis.pojo.Data;
import com.JiunnTarn.mybatis.setting.Settings;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    @Test
    public void testMyBatis() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DataMapper mapper = sqlSession.getMapper(DataMapper.class);
        List<Integer> tailList = Settings.getTail();
        for (Integer i : tailList) {
            List<Data> result = mapper.getDataByTail(i);
            for (Data data : result) {
                System.out.println(data.getPhone());
            }
        }
    }
}
