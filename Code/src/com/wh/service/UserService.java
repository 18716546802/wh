package com.wh.service;

import java.util.List;

import com.wh.model.T02;
import com.wh.model.T06;

public interface UserService {
	public T02 getUserById(int id);
	public T02 userLogin(String userName,String password);
	public T02 addUser(T02 t02);
	public int updateUserInfo(T02 t02);
	public List<T06> selectMenuTree();
	public int deleteUser(T02 t02);
	
	public List<T02> findEnterpriseUsers(String sql);
	
}
