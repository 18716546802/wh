package com.wh.serviceImpl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wh.dao.T03Mapper;
import com.wh.dao.T04Mapper;
import com.wh.dao.T07Mapper;
import com.wh.dao.T08Mapper;
import com.wh.dao.T10Mapper;
import com.wh.dao.T12Mapper;
import com.wh.dao.T50Mapper;
import com.wh.model.T03;
import com.wh.model.T04;
import com.wh.model.T07;
import com.wh.model.T08;
import com.wh.model.T10;
import com.wh.model.T50;
import com.wh.service.TimerTestService;

/**
 * Spring 简单的定时器
 * @author ZXC
 *
 */
@Component
@Service
public class TimerTestServiceImpl implements TimerTestService {
	 
	@Autowired
	T07Mapper t07dao;
	@Autowired
	T03Mapper t03dao;
	@Autowired
	T04Mapper t04dao;
	@Autowired
	T08Mapper t08dao;
	@Autowired
	T12Mapper t12dao;
	@Autowired
	T10Mapper t10dao;
	@Autowired
	T50Mapper t50dao;
	//每天凌晨0点0分执行一次
	
	//每30秒执行一次
	//@Scheduled(cron = "0/30 * * * * ?")
/*	 public void test1() throws ParseException{
        Date date = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String dateStr = sim.format(date); 
        String DateStr1 = "2014-08-21 10:20:16";
        String DateStr2 = "2014-08-27 10:20:16";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime1 = dateFormat.parse(DateStr1);
        Date dateTime2 = dateFormat.parse(DateStr2);
        int i = dateTime1.compareTo(dateTime2); 
        System.out.println(i);
        System.out.println("这是spring定时器1，每五秒执行一次,当前时间：" + dateStr);  
	 }*/
	
	
	//每天的16:04分执行
	//@Scheduled(cron = "0 4 16 * * ?")
	
	@Scheduled(cron= "0 0 0 * * ?")
	public void updateTimeT50(){
		T50 time07 = t50dao.getTime07();
		T50 time08 = t50dao.getTime08();
		T50 time03 = t50dao.getTime03();
		time07.setF5002("0");
		time08.setF5002("0");
		time03.setF5002("0");
		t50dao.updateByPrimaryKeySelective(time07);
		t50dao.updateByPrimaryKeySelective(time08);
		t50dao.updateByPrimaryKeySelective(time03);
	}
	
	//人员资质
	//@Scheduled(cron = "0 0 0 * * ?")
	public  void checkT07(){
		Date date = new Date();
		List<T07> list = t07dao.selectAllQualifications();
		for(T07 t07 : list){
			if(t07.getF0704() != null){
				T10 t10;
				t10 = new T10();
				t10.setF0101(t07.getF0101());
				t10.setF1002("人员资质");
				t10.setF1003(new Date());
				t10.setF1004("未确认");
				t10.setF1007(t07.getF0701());
				t10.setF1008("未处理");
				T10 tt10 = t10dao.findByF1007(t07.getF0701());
				if(judgeDate(t07.getF0704(), date, -120)){
					if(tt10 == null){
						t10.setF1006(t07.getF0702()+"的"+t07.getF0707()+"在4个月内到期");
						t10dao.insertNoId(t10);
					}
				}
				if(judgeDate(t07.getF0704(), date, -90)){
					if(tt10 != null){
						t10.setF1002(tt10.getF1002());
						t10.setF0101(tt10.getF0101());
						t10.setF1003(tt10.getF1003());
						t10.setF1004(tt10.getF1004());
						t10.setF1007(tt10.getF1007());
						t10.setF1008(tt10.getF1008());
					}
					t10dao.deleteByF1007(t07.getF0701());
					t10.setF1006(t07.getF0702()+"的"+t07.getF0707()+"在3个月内到期");
					t10dao.insertNoId(t10);
				}
				if(judgeDate(t07.getF0704(), date, -30)){
					if(tt10 != null){
						t10.setF1002(tt10.getF1002());
						t10.setF0101(tt10.getF0101());
						t10.setF1003(tt10.getF1003());
						t10.setF1004(tt10.getF1004());
						t10.setF1007(tt10.getF1007());
						t10.setF1008(tt10.getF1008());
					}
					t10dao.deleteByF1007(t07.getF0701());
					t10.setF1006(t07.getF0702()+"的"+t07.getF0707()+"在1个月内到期");
					t10dao.insertNoId(t10);
				}
				//和今天比较，查看是否过期
				if(judgeDate(t07.getF0704(), date, 0)){
					if(tt10 != null){
						t10.setF1002(tt10.getF1002());
						t10.setF0101(tt10.getF0101());
						t10.setF1003(tt10.getF1003());
						t10.setF1004(tt10.getF1004());
						t10.setF1007(tt10.getF1007());
						t10.setF1008(tt10.getF1008());
					}
					//表示已经过期了，需将这条记录的是否过期字段修改，并且往
					t10dao.deleteByF1007(t07.getF0701());
					t10.setF1006(t07.getF0702()+"的"+t07.getF0707()+"在今天到期");
					t10dao.insertNoId(t10);
					
				}else{
					if(tt10 != null){
						t10.setF1002(tt10.getF1002());
						t10.setF0101(tt10.getF0101());
						t10.setF1003(tt10.getF1003());
						t10.setF1004(tt10.getF1004());
						t10.setF1007(tt10.getF1007());
						t10.setF1008(tt10.getF1008());
					}
					//表示已经过期了，需将这条记录的是否过期字段修改，并且往
					t10dao.deleteByF1007(t07.getF0701());
					t10.setF1006(t07.getF0702()+"的"+t07.getF0707()+"已过期");
					t10dao.insertNoId(t10);
					t07.setF0706("过期");
					t07dao.updateByPrimaryKey(t07);
				}
			}
		}
	}
	
	//隐患
	//@Scheduled(cron = "0 5 0 * * ?")
	public void checkT08(){
		Date date = new Date();
		List<T08> list = t08dao.selectT08();
		for(T08 t08 : list){
			if("限期整改".equals(t08.getF0809()) && t08.getF0812() != null){
				T10 t10 = new T10();
				t10.setF0101(t08.getF0101());
				t10.setF1002("隐患整改");
				t10.setF1003(new Date());
				t10.setF1004("未确认");
				t10.setF1007(t08.getF0801());
				t10.setF1008("未处理");
				T10 tt10 = t10dao.findByF1007(t08.getF0801());
				
				if(judgeDate(t08.getF0812(), date, -30)){
					if(tt10 == null){
						t10.setF1006(t08.getF0802()+"在30天后到达限期整改时间");
						t10dao.insertNoId(t10);
					}
				}
				if(judgeDate(t08.getF0812(), date, -20)){
					if(tt10 != null){
						t10.setF1002(tt10.getF1002());
						t10.setF0101(tt10.getF0101());
						t10.setF1003(tt10.getF1003());
						t10.setF1004(tt10.getF1004());
						t10.setF1007(tt10.getF1007());
						t10.setF1008(tt10.getF1008());
					}
					t10dao.deleteByF1007(t08.getF0801());
					t10.setF1006(t08.getF0802()+"在20天后到达限期整改时间");
					t10dao.insertNoId(t10);
				}
				if(judgeDate(t08.getF0812(), date, -10)){
					if(tt10 != null){
						t10.setF1002(tt10.getF1002());
						t10.setF0101(tt10.getF0101());
						t10.setF1003(tt10.getF1003());
						t10.setF1004(tt10.getF1004());
						t10.setF1007(tt10.getF1007());
						t10.setF1008(tt10.getF1008());
					}
					t10dao.deleteByF1007(t08.getF0801());
					t10.setF1006(t08.getF0802()+"在10天后到达限期整改时间");
					t10dao.insertNoId(t10);
				}
				//和今天比较，查看是否过期
				if(judgeDate(t08.getF0812(), date, 0)){
					if(tt10 != null){
						t10.setF1002(tt10.getF1002());
						t10.setF0101(tt10.getF0101());
						t10.setF1003(tt10.getF1003());
						t10.setF1004(tt10.getF1004());
						t10.setF1007(tt10.getF1007());
						t10.setF1008(tt10.getF1008());
					}
					t10dao.deleteByF1007(t08.getF0801());
					t10.setF1006(t08.getF0802()+"已经到达限期整改时间");
					t10dao.insertNoId(t10);
				}else{
					if(tt10 != null){
						t10.setF1002(tt10.getF1002());
						t10.setF0101(tt10.getF0101());
						t10.setF1003(tt10.getF1003());
						t10.setF1004(tt10.getF1004());
						t10.setF1007(tt10.getF1007());
						t10.setF1008(tt10.getF1008());
					}
					t10dao.deleteByF1007(t08.getF0801());
					t10.setF1006(t08.getF0802()+"已经超过限期整改时间");
					t10dao.insertNoId(t10);
				}
			}
		}
	}
 
/*	@Scheduled(cron = "0 5 0 * * ?")
	public void checkT12() {
		
	}*/

	//特种设备
	//@Scheduled(cron = "0 7 0 * * ?")
	public void checkT03() {
		Date date = new Date();
		//查出所有未过期的特种设备
		List<T03> list = t03dao.selectAllT03();
		for(T03 t03 : list){
			int t03Id = t03.getF0301();
			List<T04> listT04 = t04dao.findAllCheckInfoByE(t03Id);
			for(T04 t04 : listT04){
				if(t04.getF0407() != null){
					T10 t10 = new T10();
					t10.setF0101(t03.getF0101());
					t10.setF1002("设备检测");
					t10.setF1004("未确认");
					t10.setF1007(t04.getF0401());
					t10.setF1003(new Date());
					t10.setF1008("未处理");
					T10 tt10 = t10dao.findByF1007(t04.getF0401());
					if(judgeDate(t04.getF0407() , date, -30)){
						if(tt10 == null){
							t10.setF1006(t03.getF0302()+"设备的附件"+t04.getF0402()+"将在30天后进行设备检测");
							t10dao.insertNoId(t10);
						}
					}
					if(judgeDate(t04.getF0407() , date, -20)){
						if(tt10 != null){
							t10.setF1002(tt10.getF1002());
							t10.setF0101(tt10.getF0101());
							t10.setF1003(tt10.getF1003());
							t10.setF1004(tt10.getF1004());
							t10.setF1007(tt10.getF1007());
							t10.setF1008(tt10.getF1008());
						}
						t10dao.deleteByF1007(t04.getF0401());
						t10.setF1006(t03.getF0302()+"设备的附件"+t04.getF0402()+"将在20天后进行设备检测");
						t10dao.insertNoId(t10);
					}
					if(judgeDate(t04.getF0407() , date, -10)){
						if(tt10 != null){
							t10.setF1002(tt10.getF1002());
							t10.setF0101(tt10.getF0101());
							t10.setF1003(tt10.getF1003());
							t10.setF1004(tt10.getF1004());
							t10.setF1007(tt10.getF1007());
							t10.setF1008(tt10.getF1008());
						}
						t10dao.deleteByF1007(t04.getF0401());
						t10.setF1006(t03.getF0302()+"设备的附件"+t04.getF0402()+"将在10天后进行设备检测");
						t10dao.insertNoId(t10);
					}
					//和今天比较，查看是否过期
					if(judgeDate(t04.getF0407(), date, 0)){
						if(tt10 != null){
							t10.setF1002(tt10.getF1002());
							t10.setF0101(tt10.getF0101());
							t10.setF1003(tt10.getF1003());
							t10.setF1004(tt10.getF1004());
							t10.setF1007(tt10.getF1007());
							t10.setF1008(tt10.getF1008());
						}
						t10dao.deleteByF1007(t04.getF0401());
						t10.setF1006(t03.getF0302()+"设备的附件"+t04.getF0402()+"将在今天进行检测");
						t10dao.insertNoId(t10);
					}else{
						if(tt10 != null){
							t10.setF1002(tt10.getF1002());
							t10.setF0101(tt10.getF0101());
							t10.setF1003(tt10.getF1003());
							t10.setF1004(tt10.getF1004());
							t10.setF1007(tt10.getF1007());
							t10.setF1008(tt10.getF1008());
						}
						t10dao.deleteByF1007(t04.getF0401());
						t10.setF1006(t03.getF0302()+"设备的附件"+t04.getF0402()+"已过期");
						t10dao.insertNoId(t10);
					}
					
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @功能描述: 判断一个时间是否超过了另一个时间
	 * @提交方式: 
	 * @时间:2017年6月11日 下午2:55:29
	 * @方法名: judgeDate 
	 * @param d1 过期时间
	 * @param d2 当前时间
	 * @param days 提前天数  如果为0表示是与当前时间比较
	 * @return
	 * @return boolean
	 */
	private boolean judgeDate(Date d1,Date d2,Integer days){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = sdf.format(d1);
		if(days == 0){
			int i = d2.compareTo(d1);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}else{
			Calendar cld;
			//获取一个日历
			cld = Calendar.getInstance();
			cld.setTime(d1);
			cld.add(Calendar.DATE, days);
			int i = d2.compareTo(cld.getTime());
 			if(i>0){
				return true;
			}else{
				return false;
			}
		}
	}


	@Override
	public void checkT12() {
		// TODO Auto-generated method stub
		
	}

}