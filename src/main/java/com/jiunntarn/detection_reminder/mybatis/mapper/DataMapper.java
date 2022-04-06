package com.jiunntarn.detection_reminder.mybatis.mapper;

import com.jiunntarn.detection_reminder.mybatis.pojo.Data;

import java.util.List;

/**
 * @author JiunnTarn
 */
public interface DataMapper {
    public List<Data> getDataByTail(Integer tail);
}
