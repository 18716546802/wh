package com.wh.service;

import java.util.List;

import com.wh.model.T06;

public interface TreeService {
	int insertTree(T06 t06);
	int updateTree(T06 t06);
	int deleteTree(Integer f0601);
	List<T06> getListT06();
}