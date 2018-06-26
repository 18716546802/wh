package com.wh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wh.model.T50;

public interface T50Mapper {
    int deleteByPrimaryKey(Integer f5001);

    int insert(T50 record);
    int deleteT50(@Param("f5002")String id);
    int insertSelective(T50 record);

    T50 selectByPrimaryKey(Integer f5001);

    int updateByPrimaryKeySelective(T50 record);
    T50 selectF5005(@Param("f5005")String f5005);
    int updateByPrimaryKey(T50 record);
    
    List<T50> selectAllT50();
    T50 selectT50ByToday(@Param("f5002")String f5002);
    T50 getTips1();
    T50 getTips2();
    T50 getTips3();
    
    T50 getTime07();
    T50 getTime08();
    T50 getTime03();
    //void updateTime(@Param("f5004")String f5004);
}