package com.wh.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.T10Mapper;
import com.wh.dao.T11Mapper;
import com.wh.model.T10;
import com.wh.model.T11;
import com.wh.service.DangerService;

@Service
public class DangerServiceImpl implements DangerService{

	@Autowired
	T11Mapper t11dao;
	@Autowired
	T10Mapper t10dao;
	
	@Override
	public List<T11> selectDangerlist() {
		// TODO Auto-generated method stub
		return t11dao.selectDangerlist();
	}

	@Override
	public int updateByPrimaryKeySelective(T11 t11) {
		// TODO Auto-generated method stub
		return t11dao.updateByPrimaryKeySelective(t11);
	}

	@Override
	public int insertSelective(T11 t11) {
		// TODO Auto-generated method stub
		return t11dao.insertSelective(t11);
	}

	@Override
	public int deleteByPrimaryKey(Integer f1101) {
		// TODO Auto-generated method stub
		return t11dao.deleteByPrimaryKey(f1101);
	}

	@Override
	public T11 selectByPrimaryKey(Integer f1101) {
		// TODO Auto-generated method stub
		return t11dao.selectByPrimaryKey(f1101);
	}

	
	@Override
	public List<T10> selectAllRemind() {
		// TODO Auto-generated method stub
		return t10dao.selectAllRemind();
	}

	@Override
	public T10 selectRemindByPrimaryKey(Integer f1001) {
		// TODO Auto-generated method stub
		return t10dao.selectRemindByPrimaryKey(f1001);
	}

	@Override
	public int updateRemindByPrimaryKeySelective(T10 t10) {
		// TODO Auto-generated method stub
		return t10dao.updateByPrimaryKeySelective(t10);
	}

	@Override
	public int insertSelective(T10 t10) {
		// TODO Auto-generated method stub
		return t10dao.insertSelective(t10);
	}

	@Override
	public int deleteT10ByPrimaryKey(Integer f1001) {
		// TODO Auto-generated method stub
		return t10dao.deleteByPrimaryKey(f1001);
	}

	@Override
	public int insert(T11 t11) {
		// TODO Auto-generated method stub
		return t11dao.insert(t11);
	}

	@Override
	public T11 insertT11(T11 t11) {
		
		t11dao.insertSelective(t11);
		
		return t11;
	}

	@Override
	public T10 insertT10(T10 t10) {
		
		t10dao.insertSelective(t10);
		return t10;
	}

	@Override
	public List<T10> findAllRemindByCom(Integer f0101) {
		// TODO Auto-generated method stub
		return t10dao.selectAllRemindByCom(f0101);
	}

	@Override
	public List<T11> findAllDangerByCom(Integer f0101) {
		// TODO Auto-generated method stub
		return t11dao.selectAllDangerByCom(f0101);
	}

	@Override
	public List<T10> selectListRemindnByEnterprise(Integer f0101) {
		// TODO Auto-generated method stub
		return t10dao.selectListRemindnByEnterprise(f0101);
	}

	@Override
	public List<T11> selectListDangByEnterprise(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<T11> selectListDangByEnterpriseId(Integer f0101) {
		// TODO Auto-generated method stub
		return t11dao.selectListDangByEnterpriseId(f0101);
	}










	
}
