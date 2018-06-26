package com.wh.dao;

import java.util.List;

import com.wh.model.T05;

public interface T05Mapper {
    int deleteByPrimaryKey(Integer f0501);

    int insert(T05 record);

    int insertSelective(T05 record);

    T05 selectByPrimaryKey(Integer f0501);

    int updateByPrimaryKeySelective(T05 record);

    int updateByPrimaryKey(T05 record);
    List<T05> selectT05Byf0101(Integer f0101);
    List<T05> selectT05();
}