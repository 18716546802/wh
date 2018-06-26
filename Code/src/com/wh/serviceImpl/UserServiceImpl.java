package com.wh.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.T01Mapper;
import com.wh.dao.T02Mapper;
import com.wh.dao.T06Mapper;
import com.wh.dao.T50Mapper;
import com.wh.model.T01;
import com.wh.model.T02;
import com.wh.model.T06;
import com.wh.model.T50;
import com.wh.service.TimerTestService;
import com.wh.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	T02Mapper t02Dao;
	@Autowired
	T01Mapper t01Dao;
	@Autowired
	T06Mapper t06Dao;
	@Autowired
	T50Mapper t50Dao;
	@Autowired
	TimerTestService timeService;
	@Override
	public T02 getUserById(int id) {
		return t02Dao.selectByPrimaryKey(id);
	}

	@Override
	public T02 userLogin(String userName, String password) {
		
		T02 t = t02Dao.userLogin(userName, password);
		if(t != null){
			//F0209字段为不为0 表示这个用户是企业用户或者政府用户
			if(t.getF0209() != 0){
				T01 t01 = t01Dao.selectByPrimaryKey(t.getF0209());
				T06 t06 = t06Dao.selectByPrimaryKey(t.getF0209());
				t.setT01(t01);
				t.setT06(t06);
			}
		}
		T50 time07 = t50Dao.getTime07();
		T50 time08 = t50Dao.getTime08();
		T50 time03 = t50Dao.getTime03();
		
		if(time07.getF5002().equals("0")){
			timeService.checkT07();
			time07.setF5002("1");
			t50Dao.updateByPrimaryKeySelective(time07);
		}
		if(time08.getF5002().equals("0")){
			timeService.checkT08();
			time08.setF5002("1");
			t50Dao.updateByPrimaryKeySelective(time08);
		}
		if(time03.getF5002().equals("0")){
			timeService.checkT03();
			time03.setF5002("1");
			t50Dao.updateByPrimaryKeySelective(time03);
		}
		
		return t;
	}

	@Override
	public T02 addUser(T02 t02) {
		T02 t = t02Dao.userLogin(t02.getF0202(), t02.getF0203());
		if(t == null){
			t02Dao.insertSelective(t02);
			return t02;
		}
		else return null;
	}

	@Override
	public int updateUserInfo(T02 t02) {
		return t02Dao.updateByPrimaryKeySelective(t02);
	}

	@Override
	public List<T06> selectMenuTree() {
		return t06Dao.selectMenuTree();
	}

	@Override
	public int deleteUser(T02 t02) {
		return t02Dao.deleteByPrimaryKey(t02.getF0201());
	}

	@Override
	public List<T02> findEnterpriseUsers(String sql) {
		List<T02> list = t02Dao.getT02(sql);
		for(int i=0;i<list.size();i++){
			//F0209字段为不为0 表示这个用户是企业用户或者政府用户
			if(list.get(i).getF0209() != 0){
				T01 t01 = t01Dao.selectByPrimaryKey(list.get(i).getF0209());
				T06 t06 = t06Dao.selectByPrimaryKey(list.get(i).getF0209());
				list.get(i).setT01(t01);
				list.get(i).setT06(t06);
			}
		}
		return list;
	}

}
