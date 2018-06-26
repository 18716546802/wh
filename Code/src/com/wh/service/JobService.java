package com.wh.service;

import java.util.List;

import com.wh.model.T01;
import com.wh.model.T03;
import com.wh.model.T08;
import com.wh.model.T10;
import com.wh.model.T50;


public interface JobService {
	public T01 insertT01(T01 t);
	public int updateT01(T01 t);
	public T01 findT01(Integer f0101);
	public int deleteT01(Integer f0101);
	public List<T01> findEnterpriseType(String f0118);
	
	public int getNoF0601();
	public T50 isHaveT50(String id);
	public T50 insertT50(T50 t);
	public int updateT50(T50 t);
	public int deleteT50(String id);
	public List<T50> findAllTodayParameters();
	
	
	public int addT08(T08 t);
	public int updateT08(T08 t);
	public T08 slelectT08(int f0801);
	public List<T08> selectT08();
	public List<T08> selectT08(int id);
	public int deleteT08(int id);
	public List<T08> selectTodayT08(String date);
	public List<T08> selectT08ByComType(Integer f0101,String f0828);
	public List<T08> findT08ByType(String f0828);
	public List<T01> findEnterpriseByArea(int f0601);
	public List<T01> findAllEnterprise();
	public T01 findEnterpriseById(int f0101);
	public List<T01> findEnterprisebyName(String name);
	
	public List<T03> findAllEquipment();
	public List<T03> findAllEquipmentByCompany(int f0101);
	
	public int updateEquipment(T03 t03);
	public int deleteEquipment(int f0301);
	public int addEquipment(T03 t03);
	public int updateByPrimaryKeySelective(T01 t);
	
	public List<T10> selectT10ByComType(Integer f0101,String f1002,String name);
	public int updateT10(T10 t10);
	public List<T08> seletNewPurkingPeril(Integer f0101);
	public List<T08> selectSurePurkingPeril(Integer f0101);
}
