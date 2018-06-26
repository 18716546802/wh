package com.wh.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.T03Mapper;
import com.wh.dao.T11Mapper;
import com.wh.model.T03;
import com.wh.model.T11;
import com.wh.service.JobServiceDs;

@Service
public class JobServiceImplDs implements JobServiceDs{
	
	@Autowired
	T03Mapper  t03Dao;
	@Autowired
	T11Mapper  t11Dao;

	/**
	 * 该方法需要返回出所有的未完成的T03对象，未实现，
	 */
	@Override
	public List<T03> selectT03() {
		// TODO Auto-generated method stub
		return new ArrayList<T03>();
	}

	/***
	 * 该方法调用Dao层的insert方法，向数据库中写入数据
	 * @param T03类
	 */
	@Override
	public T03 insertT03(T03 t) {
		// TODO Auto-generated method stub
		t03Dao.insert(t);
		return t;
	}

	/**
	 * 该方法用于更新特种设备
	 * updateByPrimaryKeySelective(t) 该方法会返回一个操作成功的行数
	 */
	@Override
	public int updateT03(T03 t) {
		// TODO Auto-generated method stub
		return t03Dao.updateByPrimaryKeySelective(t);
	}
	
	/**
	 * 根据传入的Id
	 * 删除T03中的记录
	 */
	@Override
	public int deleteT03(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 该方法需要返回出所有的未完成的T11对象，未实现，
	 */
	@Override
	public List<T11> selectT11() {
		// TODO Auto-generated method stub
		return new ArrayList<T11>();
	}
	
	/***
	 * 该方法调用Dao层的insert方法，向数据库中写入数据
	 * @param T11类
	 */
	@Override
	public T11 insertT11(T11 t) {
		// TODO Auto-generated method stub
		t11Dao.insert(t);
		return t;
	}

	/**
	 * 该方法用于更新特种设备
	 * updateByPrimaryKeySelective(t) 该方法会返回一个操作成功的行数
	 */
	@Override
	public int updateT11(T11 t) {
		// TODO Auto-generated method stub
		return t11Dao.updateByPrimaryKeySelective(t);
	}
	
}
