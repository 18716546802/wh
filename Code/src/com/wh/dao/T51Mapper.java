package com.wh.dao;

import com.wh.model.T51;

public interface T51Mapper {
    int deleteByPrimaryKey(Integer f5101);

    int insert(T51 record);

    int insertSelective(T51 record);

    T51 selectByPrimaryKey(Integer f5101);

    int updateByPrimaryKeySelective(T51 record);

    int updateByPrimaryKey(T51 record);
}