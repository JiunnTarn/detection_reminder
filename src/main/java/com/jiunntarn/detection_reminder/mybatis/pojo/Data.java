package com.jiunntarn.detection_reminder.mybatis.pojo;

/**
 * @author JiunnTarn
 */
public class Data {
    private final String name;
    private final String phone;
    private final Integer tail;

    public Data(String name, String phone, Integer tail) {
        this.name = name;
        this.phone = phone;
        this.tail = tail;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", tail=" + tail +
                '}';
    }
}
