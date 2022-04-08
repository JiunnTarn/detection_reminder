package com.jiunntarn.detection_reminder.Dao;

import com.jiunntarn.detection_reminder.pojo.Student;
import com.jiunntarn.detection_reminder.Dao.mapper.DataMapper;
import com.jiunntarn.detection_reminder.setting.Settings;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    InputStream is;
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    DataMapper mapper;

    public DAO() throws IOException {
        is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(DataMapper.class);
    }

    public void emptyExclude() {
        mapper.emptyExclude();
    }

    public boolean isStudentExistedInExcludeList(String name) {
        List<Student> list = mapper.getStudentInExcludeByName(name);
        return !list.isEmpty();
    }

    public boolean isStudentExistedInStudentList(String name) {
        Student student = mapper.getStudentByName(name);
        return student != null;
    }

    public void insertExclude(String name) {
        Student student = mapper.getStudentByName(name);
        mapper.insertExclude(student);
    }

    public List<String> getExcludeStudent() {
        List<String> exclude = new ArrayList<>();
        List<Student> excludeList = mapper.getExcludeList();

        for (Student student : excludeList) {
            exclude.add(student.getName());
        }

        return exclude;
    }

    public List<String> getUndoneStudent(Settings settings) {
        List<Integer> tailList = settings.getTail();

        List<String> res = new ArrayList<>();
        List<String> exclude = new ArrayList<>();

        List<Student> excludeList = mapper.getExcludeList();

        for (Integer i : tailList) {
            List<Student> result = mapper.getStudentByTail(i);
            for (Student student : result) {
                res.add(student.getName());
            }
        }
        for (Student student : excludeList) {
            exclude.add(student.getName());
        }

        res.removeAll(exclude);

        return res;
    }

    public List<String> getAtList(Settings settings) {
        List<Integer> tailList = settings.getTail();

        List<String> res = new ArrayList<>();
        List<String> exclude = new ArrayList<>();

        List<Student> excludeList = mapper.getExcludeList();

        for (Integer i : tailList) {
            List<Student> result = mapper.getStudentByTail(i);
            for (Student student : result) {
                res.add(student.getPhone());
            }
        }
        for (Student student : excludeList) {
            exclude.add(student.getPhone());
        }

        res.removeAll(exclude);

        return res;
    }

    public String getAtString(Settings settings) {
        List<String> dataList = getAtList(settings);
        StringBuilder res = new StringBuilder();
        for (String i : dataList) {
            res.append("@").append(i).append(" ");
        }
        return res.toString();
    }
}
