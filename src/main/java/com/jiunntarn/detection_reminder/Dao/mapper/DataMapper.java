package com.jiunntarn.detection_reminder.Dao.mapper;

import com.jiunntarn.detection_reminder.pojo.Student;

import java.util.List;

/**
 * @author JiunnTarn
 */
public interface DataMapper {
    List<Student> getStudentByTail(Integer tail);
    Student getStudentByName(String name);
    List<Student> getStudentInExcludeByName(String name);
    List<Student> getExcludeList();
    void emptyExclude();
    void insertExclude(Student student);
}
