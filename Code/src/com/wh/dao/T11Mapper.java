package com.wh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wh.model.T11;

public interface T11Mapper {
    int deleteByPrimaryKey(Integer f1101);

    int insert(T11 record);

    int insertSelective(T11 record);

    T11 selectByPrimaryKey(Integer f1101);

    int updateByPrimaryKeySelective(T11 record);

    int updateByPrimaryKey(T11 record);

	List<T11> selectDangerlist();
	List<T11> selectAllDangerByCom(@Param("f0101")Integer f0101);
	T11 insertT11(T11 t11);


	List<T11> selectListDangByEnterpriseId(Integer f0101);
	
	
}