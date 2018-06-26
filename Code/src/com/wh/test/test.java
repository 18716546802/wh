package com.wh.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wh.service.UserService;

public class test {
	@Test
	public void test1(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
		UserService user = (UserService)ac.getBean("userService");		
		System.out.println(user.getUserById(1).getF0202());
	}
	public static String getStrTime(String cc_time) {  
		String re_StrTime = null;  

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");  
		// 例如：cc_time=1291778220  
		long lcc_time = Long.valueOf(cc_time);  
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));  

		return re_StrTime;  
	}
	public static void main(String[] args) {
		
//		String timestrap = new Date().getDay()+"";
//		
//		
//		//String ss = getStrTime(timestrap);
//		//System.out.println(ss);
//		System.out.println(timestrap);
		Calendar now = Calendar.getInstance();
		System.out.println(now.getTime());
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataNowStr = sdf.format(d);
		System.out.println(dataNowStr);
	}
}
