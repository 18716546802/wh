package com.wh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wh.model.T09;

public interface T09Mapper {
    int deleteByPrimaryKey(Integer f0901);

    int insert(T09 record);

    int insertSelective(T09 record);

    T09 selectByPrimaryKey(Integer f0901);

    int updateByPrimaryKeySelective(T09 record);

    int updateByPrimaryKey(T09 record);

	List<T09> selectAllT09();

	List<T09> selectListLowByEnterpriseId(Integer f0101);
	List<T09> findLowByComAndType(T09 record);
	List<T09> findLowByType(T09 record);
	
	List<T09> findQUANJUFile();
}