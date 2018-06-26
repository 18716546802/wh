package com.wh.service;

import java.util.List;

import com.wh.model.T07;

public interface PersonService {

	public List<T07> selectAllQualifications();

	public List<T07> selectListQualificationsByEnterpriseId(Integer f0101);

	public T07 selectQualificationsByID(Integer f0701);

	public int updataQualificationsByID(T07 t07);

	public int deleteQualificationsByID(Integer f0701);

	public T07 addpersonQualification(T07 t07);

}
