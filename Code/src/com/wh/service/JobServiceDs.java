package com.wh.service;

import java.util.List;

import com.wh.model.T03;
import com.wh.model.T11;

public interface JobServiceDs {

	public List<T03> selectT03();
	public T03 insertT03(T03 t);
	public int updateT03(T03 t);
	int deleteT03(int id);
	public List<T11> selectT11();
	public T11 insertT11(T11 t);
	public int updateT11(T11 t);
}
