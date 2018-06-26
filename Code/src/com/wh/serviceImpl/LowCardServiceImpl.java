package com.wh.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.T03Mapper;
import com.wh.dao.T04Mapper;
import com.wh.dao.T09Mapper;
import com.wh.model.T03;
import com.wh.model.T04;
import com.wh.model.T09;
import com.wh.service.LowCardService;

@Service
public class LowCardServiceImpl implements LowCardService{

	@Autowired
	T09Mapper t09dao;
	@Autowired
	T04Mapper t04dao;
	@Autowired
	T03Mapper t03dao;
	
	@Override
	public List<T09> selectAllT09() {
		// TODO Auto-generated method stub
		return t09dao.selectAllT09();
	}

	@Override
	public T09 selectLowByPrimaryKey(Integer f0901) {
		// TODO Auto-generated method stub
		return t09dao.selectByPrimaryKey(f0901);
	}

	@Override
	public T09 insertT09(T09 t09) {
		
		t09dao.insertSelective(t09);
		return t09;
	}

	@Override
	public int updateT09(T09 t09) {
		// TODO Auto-generated method stub
		return t09dao.updateByPrimaryKeySelective(t09);
	}

	@Override
	public int deletelow(Integer f0901) {
		// TODO Auto-generated method stub
		return t09dao.deleteByPrimaryKey(f0901);
	}

	@Override
	public List<T04> seleteAllCheckCards() {
		// TODO Auto-generated method stub
		return t04dao.seleteAllCheckInfo();
	}

	@Override
	public T04 selectOneCheckInfo(Integer f0401) {
		// TODO Auto-generated method stub
		return t04dao.selectByPrimaryKey(f0401);
	}

	@Override
	public T04 InsertCheckInfo(T04 t04) {
		t04dao.insertSelective(t04);
		return t04;
	}

	@Override
	public int updateCheckInfo(T04 t04) {
		int i = t04dao.updateByPrimaryKeySelective(t04);
		int t03Id = t04dao.selectByPrimaryKey(t04.getF0401()).getF0301();
		if((t04.getF0408() != null || !"".equals(t04.getF0408())) && t04.getF0408() == "未过期"){
			List<T04> list = t04dao.findAllCheckInfoByE(t03Id);
			boolean isBroken = true;
			for(T04 t:list){
				if((t04.getF0408() != null || !"".equals(t04.getF0408())) && t04.getF0408() == "过期"){
					isBroken = false;
					break;
				}
			}
			//
			if(!isBroken){
				T03 t03 = new T03();
				t03.setF0301(t03Id);
				t03.setF0315("未过期");
				t03dao.updateByPrimaryKeySelective(t03);
			}
		}
		if((t04.getF0408() != null || !"".equals(t04.getF0408())) && t04.getF0408() == "过期"){
				T03 t03 = new T03();
				t03.setF0301(t03Id);
				t03.setF0315("过期");
				t03dao.updateByPrimaryKeySelective(t03);
		}

		return i;
	}

	@Override
	public int deleteCheckInfo(Integer f0401) {
		// TODO Auto-generated method stub
		return t04dao.deleteByPrimaryKey(f0401);
	}

	@Override
	public List<T09> selectListLowByEnterpriseId(Integer f0101) {
		// TODO Auto-generated method stub
		return t09dao.selectListLowByEnterpriseId(f0101);
	}

	@Override
	public List<T04> findAllCheckInfoByE(Integer f0301) {
		// TODO Auto-generated method stub
		return t04dao.findAllCheckInfoByE(f0301);
	}

	@Override
	public List<T09> findLowByComAndType(Integer f0101, String f0905) {
		List<T09> list;
		List<T09> list2  = t09dao.findQUANJUFile();
		T09 t = new T09();
		if("所有".equals(f0905)){
			list = t09dao.selectListLowByEnterpriseId(f0101);
			for(T09 tt: list2){
				list.add(tt);
			}
		}
		else{
			t.setF0101(f0101);
			t.setF0905(f0905);
			list = t09dao.findLowByComAndType(t);
			for(T09 tt: list2){
				if(f0905.equals(tt.getF0905())){
					list.add(tt);
				}

			}
		}
		
		return list;
	}




}
