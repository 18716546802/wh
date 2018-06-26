package com.wh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wh.model.T10;

public interface T10Mapper {
    int deleteByPrimaryKey(Integer f1001);
    int deleteByF1007(Integer f1007);
    int deleteByF0101AndF1007(Integer f0101,Integer f1007);
    int insert(T10 record);
    int insertNoId(T10 record);
    int insertSelective(T10 t10);

    int updateByF1007(T10 t10);
    
    int updateByPrimaryKeySelective(T10 t10);

    int updateByPrimaryKey(T10 record);
    T10 findByF0101AndF1007(Integer f0101,Integer f1007);
    T10 findByF1007(Integer f1007);
	List<T10> selectAllRemind();
	List<T10> selectAllRemindByCom(@Param("f0101")Integer f0101);
	List<T10> selectAllRemindByComType(T10 record);
	T10 selectRemindByPrimaryKey(Integer f1001);

	List<T10> selectListRemindnByEnterprise(Integer f0101);
}