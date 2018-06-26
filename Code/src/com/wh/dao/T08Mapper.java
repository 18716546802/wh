package com.wh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wh.model.T08;

public interface T08Mapper {
    int deleteByPrimaryKey(Integer f0801);

    int insert(T08 record);

    int insertSelective(T08 record);

    T08 selectByPrimaryKey(Integer f0801);

    int updateByPrimaryKeySelective(T08 record);
    List<T08> selectT08();
    List<T08> selectT08Byf0101(Integer f0101);
    int updateByPrimaryKey(T08 record);
    List<T08> selectT08Byf0101Type(T08 record);
    List<T08> selectT08ByType(T08 record);
    List<T08> selectTodayPurkingPeril(@Param("f0811")String date);

	List<T08> seletNewPurkingPeril(Integer f0101);

	List<T08> selectSurePurkingPeril(Integer f0101);

}