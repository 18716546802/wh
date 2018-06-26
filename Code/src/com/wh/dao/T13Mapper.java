package com.wh.dao;

import com.wh.model.T13;

public interface T13Mapper {
    int deleteByPrimaryKey(Integer f1301);

    int insert(T13 record);

    int insertSelective(T13 record);

    T13 selectByPrimaryKey(Integer f1301);

    int updateByPrimaryKeySelective(T13 record);

    int updateByPrimaryKey(T13 record);
}