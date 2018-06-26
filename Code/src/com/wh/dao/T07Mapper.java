package com.wh.dao;

import java.util.List;

import com.wh.model.T07;

public interface T07Mapper {
    int deleteByPrimaryKey(Integer f0701);

    int insert(T07 record);

    int insertSelective(T07 record);

    T07 selectByPrimaryKey(Integer f0701);

    int updateByPrimaryKeySelective(T07 record);

    int updateByPrimaryKey(T07 record);

	List<T07> selectAllQualifications();

	List<T07> selectListQualificationsByEnterpriseId(Integer f0101);

}