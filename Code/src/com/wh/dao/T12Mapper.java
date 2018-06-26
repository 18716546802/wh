package com.wh.dao;

import java.util.List;

import com.wh.model.T12;

public interface T12Mapper {
    int deleteByPrimaryKey(Integer f1201);

    int insert(T12 record);

    int insertSelective(T12 record);

    T12 selectByPrimaryKey(Integer f1201);

    int updateByPrimaryKeySelective(T12 record);

    int updateByPrimaryKeyWithBLOBs(T12 record);

    int updateByPrimaryKey(T12 record);
    
    List<T12> selectT12();
}