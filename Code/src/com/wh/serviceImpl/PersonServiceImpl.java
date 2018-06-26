package com.wh.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.T07Mapper;
import com.wh.dao.T10Mapper;
import com.wh.model.T07;
import com.wh.model.T10;
import com.wh.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	T07Mapper t07dao;
	@Autowired
	T10Mapper t10dao;
	
	@Override
	public List<T07> selectAllQualifications() {
		// TODO Auto-generated method stub
		return t07dao.selectAllQualifications();
	}

	@Override
	public List<T07> selectListQualificationsByEnterpriseId(Integer f0101) {
		// TODO Auto-generated method stub
		return t07dao.selectListQualificationsByEnterpriseId(f0101);
	}

	@Override
	public T07 selectQualificationsByID(Integer f0701) {
		// TODO Auto-generated method stub
		return t07dao.selectByPrimaryKey(f0701);
	}

	/* (non-Javadoc)
	 * @see com.wh.service.PersonService#updataQualificationsByID(com.wh.model.T07)
	 */
	@Override
	public int updataQualificationsByID(T07 t07) {
		// TODO Auto-generated method stub
		if( (null != t07.getF0706() || !"".equals(t07.getF0706())) && "有效".equals(t07.getF0706()) ){
			T10 t10 = new T10();
			t10.setF1007(t07.getF0701());
			t10.setF1008("已处理");
			t10.setF1009(new Date());
			t10dao.updateByF1007(t10);
		}
		return t07dao.updateByPrimaryKeySelective(t07);
	}

	@Override
	public int deleteQualificationsByID(Integer f0701) {
		// TODO Auto-generated method stub
		return t07dao.deleteByPrimaryKey(f0701);
	}

	@Override
	public T07 addpersonQualification(T07 t07) {
		t07dao.insertSelective(t07);
		return t07;
	}


}
