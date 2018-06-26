package com.wh.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.T50Mapper;
import com.wh.model.T50;
import com.wh.service.PurkingPerilService;

@Service
public class PurkingPerilServiceImpl implements PurkingPerilService {

	@Autowired
	T50Mapper T50Dao;
	
	@Override
	public T50 findPid() {
		return T50Dao.selectF5005("999");
	}

	@Override
	public int updateT50(T50 t50) {
		return T50Dao.updateByPrimaryKeySelective(t50);
	}

}
