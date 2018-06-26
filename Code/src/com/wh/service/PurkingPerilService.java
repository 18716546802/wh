package com.wh.service;

import com.wh.model.T50;

public interface PurkingPerilService {
	//查询T50表中是否有记录
	T50 findPid(); 
	//修改T50表中用于标识该记录的值
	int updateT50(T50 t50);
}
