package com.wh.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.T05Mapper;
import com.wh.model.T05;
import com.wh.service.QualificationService;

@Service
public class QualificationServiceImpl implements QualificationService {

	@Autowired
	T05Mapper t05Dao;
	
	@Override
	public List<T05> findAllEnterpriseQ(Integer f0101) {
		// TODO Auto-generated method stub
		return t05Dao.selectT05Byf0101(f0101);
	}

	@Override
	public List<T05> findAllEnterpriseQ() {
		// TODO Auto-generated method stub
		return t05Dao.selectT05();
	}

	@Override
	public int updateQinfo(T05 t) {
		// TODO Auto-generated method stub
		return t05Dao.updateByPrimaryKeySelective(t);
	}

	@Override
	public int deleteQinfo(Integer f0501) {
		// TODO Auto-generated method stub
		return t05Dao.deleteByPrimaryKey(f0501);
	}

	@Override
	public T05 addQinfo(T05 t) {
		t05Dao.insertSelective(t);
		System.out.println("----------t.f0501"+t.getF0501());
		return t;
	}

}
