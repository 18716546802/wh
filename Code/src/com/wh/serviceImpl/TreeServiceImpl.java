package com.wh.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.T06Mapper;
import com.wh.model.T06;
import com.wh.service.TreeService;

@Service
public class TreeServiceImpl implements TreeService {

	@Autowired
	T06Mapper t06Dao;
	
	@Override
	public int insertTree(T06 t06) {
		
		return t06Dao.insertSelective(t06);
	}

	@Override
	public int updateTree(T06 t06) {

		return t06Dao.updateByPrimaryKeySelective(t06);
	}

	@Override
	public int deleteTree(Integer f0601) {
		t06Dao.deleteT06By1LTree(f0601);
		//deleteT06By1LTree 这个方法是当f0601这个菜单为一级菜单时用用于删除其下的子菜单
		return t06Dao.deleteByPrimaryKey(f0601);
	}

	@Override
	public List<T06> getListT06() {
		// TODO Auto-generated method stub
		return t06Dao.selectMenuTree();
	}

}
