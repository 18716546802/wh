package com.wh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wh.model.T04;

public interface T04Mapper {
    int deleteByPrimaryKey(Integer f0401);

    int insert(T04 record);

    int insertSelective(T04 record);

    T04 selectByPrimaryKey(Integer f0401);

    int updateByPrimaryKeySelective(T04 record);

    int updateByPrimaryKey(T04 record);

	List<T04> seleteAllCheckInfo();
	List<T04> findAllCheckInfoByE(@Param("f0301")Integer f0301);
}