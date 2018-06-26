package com.wh.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.T01Mapper;
import com.wh.dao.T03Mapper;
import com.wh.dao.T04Mapper;
import com.wh.dao.T06Mapper;
import com.wh.dao.T08Mapper;
import com.wh.dao.T10Mapper;
import com.wh.dao.T50Mapper;
import com.wh.model.T01;
import com.wh.model.T03;
import com.wh.model.T04;
import com.wh.model.T08;
import com.wh.model.T10;
import com.wh.model.T50;
import com.wh.service.JobService;

/***
 * 业务服务实现层，该层用于实现业务层的各种逻辑
 * 
 * @author ZXC
 *
 */
@Service
public class JobServiceImpl implements JobService{
	@Autowired
	T50Mapper  t50Dao;
	@Autowired
	T08Mapper  t08Dao;
	@Autowired
	T01Mapper  t01Dao;
	@Autowired
	T03Mapper  t03Dao;
	@Autowired
	T06Mapper  t06Dao;
	@Autowired
	T04Mapper  t04Dao;
	@Autowired
	T10Mapper  t10Dao;
	
	
	public T50 isHaveT50(Integer id) {
		// TODO Auto-generated method stub
		return t50Dao.selectByPrimaryKey(id);
	}

	/***
	 * 该方法调用Dao层的insert方法，向数据库中写入数据
	 * @param T50类
	 */
	public T50 insertT50(T50 t) {
		t50Dao.insertSelective(t);
		return t;
	}

	/**
	 * 该方法用于更新隐患
	 * updateByPrimaryKeySelective(t) 该方法会返回一个操作成功的行数
	 */
	@Override
	public int updateT08(T08 t) {
		if( (t.getF0804() != null || !"".equals(t.getF0804())) && (t.getF0804() == "已确认") ){
			T10 t10 = new T10();
			t10.setF1007(t.getF0801());
			t10.setF1008("已处理");
			t10.setF1009(new Date());
			t10Dao.updateByF1007(t10);
		}
		return t08Dao.updateByPrimaryKeySelective(t);
	}

	/**
	 * 根据传入的Id
	 * 删除T08中的记录
	 */
	@Override
	public int deleteT08(int id) {
		// TODO Auto-generated method stub
		return t08Dao.deleteByPrimaryKey(id);
	}


	@Override
	public T08 slelectT08(int f0801) {
		return t08Dao.selectByPrimaryKey(f0801);
	}
	
	/**
	 * 该方法需要返回出所有的未完成的T08对象，未实现，
	 */
	@Override
	public List<T08> selectT08() {
		// TODO Auto-generated method stub
		return t08Dao.selectT08();
	}

	@Override
	public List<T08> selectT08(int id) {
		// TODO Auto-generated method stub
		return t08Dao.selectT08Byf0101(id);
	}

	@Override
	public T01 insertT01(T01 t) {
		t01Dao.insertSelective(t);
		return t;
	}

	@Override
	public int updateT01(T01 t) {
		return t01Dao.updateByPrimaryKeySelective(t);
	}

	@Override
	public List<T01> findEnterpriseByArea(int f0601) {
		List<T01> list = t01Dao.findEnterpriseByArea(f0601);
		return list;
	}

	@Override
	public List<T01> findAllEnterprise() {
		// TODO Auto-generated method stub
		return t01Dao.findAllEnterprise();
	}

	@Override
	public List<T03> findAllEquipment() {
		List<T03> list = t03Dao.selectAllT03();
		for(T03 t:list){
			t.setT04(t04Dao.findAllCheckInfoByE(t.getF0301()));
		}
 		return list;
	}

	@Override
	public List<T03> findAllEquipmentByCompany(int f0101) {
		List<T03> list = t03Dao.selectAllT03ByCompany(f0101);
				
		for(int i=0;i<list.size();i++){
			List<T04> l = t04Dao.findAllCheckInfoByE(list.get(i).getF0301());
			list.get(i).setT04(l);
		}
		
		return list;
	}

	@Override
	public int updateEquipment(T03 t03) {
		return t03Dao.updateByPrimaryKeySelective(t03);
	}

	@Override
	public int deleteEquipment(int f0301) {
		return t03Dao.deleteByPrimaryKey(f0301);
	}

	@Override
	public int addEquipment(T03 t03) {
		return t03Dao.insertSelective(t03);
	}

	@Override
	public List<T50> findAllTodayParameters() {
		return t50Dao.selectAllT50();
	}

	@Override
	public int addT08(T08 t) {
		return t08Dao.insertSelective(t);
	}

	@Override
	public List<T01> findEnterprisebyName(String name) {
		// TODO Auto-generated method stub
		return t01Dao.findEnterprisebyName("%"+name+"%");
	}

	@Override
	public int updateByPrimaryKeySelective(T01 t) {
		if(t01Dao.updateByPrimaryKeySelective(t) > 0){
			if(t.getF0121() == null){
				return 1;
			}
			if(!t.getF0121().equals("未开展")){
				T10 t10 = t10Dao.findByF0101AndF1007(t.getF0101(), 0121);
				if(t10 == null){
					return 1;
				}
				if(t10.getF1008().equals("未处理")){
					t10.setF1008("已处理");
					t10Dao.updateByPrimaryKeySelective(t10);
				}
			}else{
				T10 t10 = t10Dao.findByF0101AndF1007(t.getF0101(), 0121);
				if(t10 !=null){
					t10Dao.deleteByF0101AndF1007(t.getF0101(), 0121);
				}
				T10 tt = new T10();
				tt.setF0101(t.getF0101());
				tt.setF1002("隐患整改");
				tt.setF1003(new Date());
				tt.setF1004("未确认");
				tt.setF1007(0121);
				tt.setF1008("未处理");
				tt.setF1006("您公司的安全三同时状态为 ‘未开展’ ，请尽快完善");
				t10Dao.insertNoId(tt);
			}
			
			if(!t.getF0122().equals("未开展")){
				T10 t10 = t10Dao.findByF0101AndF1007(t.getF0101(), 0122);
				if(t10.getF1008().equals("未处理")){
					t10.setF1008("已处理");
					t10Dao.updateByPrimaryKeySelective(t10);
				}
			}else{
				T10 t10 = t10Dao.findByF0101AndF1007(t.getF0101(), 0122);
				if(t10 !=null){
					t10Dao.deleteByF0101AndF1007(t.getF0101(), 0122);
				}
				T10 tt = new T10();
				tt.setF0101(t.getF0101());
				tt.setF1002("隐患整改");
				tt.setF1003(new Date());
				tt.setF1004("未确认");
				tt.setF1007(0122);
				tt.setF1008("未处理");
				tt.setF1006("您公司的职位三同时状态为 ‘未开展’ ，请尽快完善");
				t10Dao.insertNoId(tt);
			}
			
			if(!t.getF0123().equals("未开展")){
				T10 t10 = t10Dao.findByF0101AndF1007(t.getF0101(), 0123);
				if(t10.getF1008().equals("未处理")){
					t10.setF1008("已处理");
					t10Dao.updateByPrimaryKeySelective(t10);
				}
			}else{
				T10 t10 = t10Dao.findByF0101AndF1007(t.getF0101(), 0123);
				if(t10 !=null){
					t10Dao.deleteByF0101AndF1007(t.getF0101(), 0123);
				}
				T10 tt = new T10();
				tt.setF0101(t.getF0101());
				tt.setF1002("隐患整改");
				tt.setF1003(new Date());
				tt.setF1004("未确认");
				tt.setF1007(0123);
				tt.setF1008("未处理");
				tt.setF1006("您公司的安全标准化状态为 ‘未开展’ ，请尽快完善");
				t10Dao.insertNoId(tt);
			}
			if(!t.getF0124().equals("未编制")){
				T10 t10 = t10Dao.findByF0101AndF1007(t.getF0101(), 0124);
				if(t10.getF1008().equals("未处理")){
					t10.setF1008("已处理");
					t10Dao.updateByPrimaryKeySelective(t10);
				}
			}else{
				T10 t10 = t10Dao.findByF0101AndF1007(t.getF0101(), 0124);
				if(t10 !=null){
					t10Dao.deleteByF0101AndF1007(t.getF0101(), 0124);
				}
				T10 tt = new T10();
				tt.setF0101(t.getF0101());
				tt.setF1002("隐患整改");
				tt.setF1003(new Date());
				tt.setF1004("未确认");
				tt.setF1007(0124);
				tt.setF1008("未处理");
				tt.setF1006("您公司的应急预案还未编制 ，请尽快完善");
				t10Dao.insertNoId(tt);
			}
			
			return 1;
		}else{
			return -1;
		}
		
	}

	@Override
	public T50 isHaveT50(String id) {
		// TODO Auto-generated method stub
		return t50Dao.selectT50ByToday(id);
	}

	@Override
	public T01 findT01(Integer f0101) {
		// TODO Auto-generated method stub
		return t01Dao.selectByPrimaryKey(f0101);
	}

	@Override
	public int updateT50(T50 t) {
		return t50Dao.updateByPrimaryKeySelective(t);
	}

	@Override
	public List<T08> selectTodayT08(String date) {
		return t08Dao.selectTodayPurkingPeril(date);
	}

	@Override
	public int getNoF0601() {
		return t06Dao.getF0601();
	}

	@Override
	public T01 findEnterpriseById(int f0101) {
		// TODO Auto-generated method stub
		return t01Dao.selectByPrimaryKey(f0101);
	}

	@Override
	public int deleteT01(Integer f0101) {
		// TODO Auto-generated method stub
		return t01Dao.deleteByPrimaryKey(f0101);
	}

	@Override
	public List<T08> selectT08ByComType(Integer f0101, String f0820) {
		// TODO Auto-generated method stub
		T08 t08 = new T08();
		t08.setF0101(f0101);
		t08.setF0820(f0820);
		
		return t08Dao.selectT08Byf0101Type(t08);
	}

	@Override
	public List<T10> selectT10ByComType(Integer f0101, String f1002,String name) {
		List<T10> list = new ArrayList<>();
		
		if("所有".equals(f1002)){
			list = t10Dao.selectAllRemindByCom(f0101);
		}else{
			T10 t10 = new T10();
			t10.setF0101(f0101);
			t10.setF1002(f1002);
			list = t10Dao.selectAllRemindByComType(t10);
		}
		
		if(!list.isEmpty()){
			for(T10 t10 : list){
				String preName = t10.getF1005();
				if( preName == null || "null".equals(preName) || "".equals(preName)){
					t10.setF1005(name);
				}else{
					if(!preName.contains(name)){
						preName += ","+name;
						t10.setF1005(preName);
					}
				}
				
				if(t10.getF1010() == null){
					t10.setF1010(new Date());
				}
				t10Dao.updateByPrimaryKey(t10);
			}
		}
		return list;
	}

	@Override
	public List<T08> findT08ByType(String f0828) {
		T08 t08 = new T08();
		t08.setF0820(f0828);
		return t08Dao.selectT08ByType(t08);
	}

	@Override
	public List<T01> findEnterpriseType(String f0118) {
		// TODO Auto-generated method stub
		return t01Dao.finaEnterpriseType(f0118);
	}

	@Override
	public int updateT10(T10 t10) {
		// TODO Auto-generated method stub
		return t10Dao.updateByPrimaryKeySelective(t10);
	}

	@Override
	public int deleteT50(String id) {
		return t50Dao.deleteT50(id);
	}

	@Override
	public List<T08> seletNewPurkingPeril(Integer f0101) {
		// TODO Auto-generated method stub
		return t08Dao.seletNewPurkingPeril(f0101);
	}

	@Override
	public List<T08> selectSurePurkingPeril(Integer f0101) {
		// TODO Auto-generated method stub
		return t08Dao.selectSurePurkingPeril(f0101);
	}
	

	

}
