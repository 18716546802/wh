package com.wh.dao;

import java.util.List;

import com.wh.model.T03;

public interface T03Mapper {
    int deleteByPrimaryKey(Integer f0301);

    int insert(T03 record);

    int insertSelective(T03 record);

    T03 selectByPrimaryKey(Integer f0301);

    int updateByPrimaryKeySelective(T03 record);

    int updateByPrimaryKey(T03 record);
    
    List<T03> selectAllT03();
    List<T03> selectAllT03ByCompany(Integer f0101);
}