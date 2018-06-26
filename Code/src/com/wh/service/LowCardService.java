package com.wh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.wh.model.T04;
import com.wh.model.T09;


public interface LowCardService {

	List <T09> selectAllT09();

	T09 selectLowByPrimaryKey(Integer f0901);

	T09 insertT09(T09 t09);

	int updateT09(T09 t09);

	int deletelow(Integer f0901);

	List <T04> seleteAllCheckCards();
	List<T04> findAllCheckInfoByE(Integer f0301);
	
	T04 selectOneCheckInfo(Integer f0401);

	T04 InsertCheckInfo(T04 t04);

	int updateCheckInfo(T04 t04);

	int deleteCheckInfo(Integer f0401);

	List<T09> selectListLowByEnterpriseId(Integer f0101);
	List<T09> findLowByComAndType(Integer f0101, String f0905);
}
