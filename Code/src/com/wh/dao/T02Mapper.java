package com.wh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wh.model.T02;

public interface T02Mapper {
    int deleteByPrimaryKey(Integer f0201);

    int insert(T02 record);

    int insertSelective(T02 record);

    T02 selectByPrimaryKey(Integer f0201);

    int updateByPrimaryKeySelective(T02 record);

    int updateByPrimaryKey(T02 record);
    
    List<T02> getT02(@Param("f0205")String sql);
    
    T02 userLogin(String userName, String password);
}