package com.JiunnTarn.mybatis.mapper;

import com.JiunnTarn.mybatis.pojo.Data;

import java.util.List;

/**
 * @author JiunnTarn
 */
public interface DataMapper {
    public List<Data> getDataByTail(Integer tail);
}
