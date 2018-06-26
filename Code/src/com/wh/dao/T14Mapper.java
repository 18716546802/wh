package com.wh.dao;

import com.wh.model.T14;

public interface T14Mapper {
    int deleteByPrimaryKey(Integer f1401);

    int insert(T14 record);

    int insertSelective(T14 record);

    T14 selectByPrimaryKey(Integer f1401);

    int updateByPrimaryKeySelective(T14 record);

    int updateByPrimaryKey(T14 record);
}