package com.wh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import com.wh.model.T01;

public interface T01Mapper {
    int deleteByPrimaryKey(Integer f0101);

    int insert(T01 record);

    int insertSelective(T01 record);

    T01 selectByPrimaryKey(Integer f0101);
    
    int updateT01(T01 t);

    int updateByPrimaryKeySelective(T01 record);

    int updateByPrimaryKey(T01 record);
    
    List<T01> findEnterpriseByArea(int f0601);
    
    List<T01> findAllEnterprise();
    
    List<T01> finaEnterpriseType(@Param("f0118") String f0118);
    
    List<T01> findEnterprisebyName(@Param("f0102")String name);
}