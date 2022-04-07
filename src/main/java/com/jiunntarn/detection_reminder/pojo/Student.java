package com.jiunntarn.detection_reminder.pojo;

/**
 * @author JiunnTarn
 */
public class Student {
    private final String name;
    private final String phone;
    private final Integer tail;

    public Student(String name, String phone, Integer tail) {
        this.name = name;
        this.phone = phone;
        this.tail = tail;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", tail=" + tail +
                '}';
    }

    public String getName() {
        return name;
    }
}
