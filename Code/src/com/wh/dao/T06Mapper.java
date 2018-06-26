package com.wh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wh.model.T06;

public interface T06Mapper {
    int deleteByPrimaryKey(Integer f0601);

    int insert(T06 record);

    int insertSelective(T06 record);

    T06 selectByPrimaryKey(Integer f0601);
    int deleteT06By1LTree(@Param("f0601")Integer f0601);
    int updateByPrimaryKeySelective(T06 record);

    int updateByPrimaryKey(T06 record);
    int getF0601();
    List<T06> selectMenuTree();
}