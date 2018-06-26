package com.wh.service;

import java.util.List;

import com.wh.model.T05;

public interface QualificationService {
	public List<T05> findAllEnterpriseQ(Integer f0101);
	public List<T05> findAllEnterpriseQ();
	public int updateQinfo(T05 t);
	public int deleteQinfo(Integer f0501);
	public T05 addQinfo(T05 t);
}
