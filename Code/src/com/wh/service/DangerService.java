package com.wh.service;

import java.util.List;

import com.wh.model.T10;
import com.wh.model.T11;

public interface DangerService {

	
	//查询重大危险源
	public List<T11> selectDangerlist();

	public int updateByPrimaryKeySelective(T11 t11);

	public int insertSelective(T11 t11);

	public int deleteByPrimaryKey(Integer f1101);

	public T11 selectByPrimaryKey(Integer f1101);
	public List<T11 > findAllDangerByCom(Integer f0101);

	public List<T10> selectAllRemind();
	public List<T10> findAllRemindByCom(Integer f0101);

	public T10 selectRemindByPrimaryKey(Integer f1001);

	public int updateRemindByPrimaryKeySelective(T10 t10);

	public int insertSelective(T10 t10);

	public int deleteT10ByPrimaryKey(Integer f1001);

	public int insert(T11 t11);

	public T11 insertT11(T11 t11);

	public T10 insertT10(T10 t10);

	public List<T10> selectListRemindnByEnterprise(Integer f0101);

	public List<T11> selectListDangByEnterprise(int parseInt);


	public List<T11> selectListDangByEnterpriseId(Integer f0101);

}
