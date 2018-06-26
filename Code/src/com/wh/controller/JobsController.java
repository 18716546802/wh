package com.wh.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.wh.dao.T10Mapper;
import com.wh.dto.ResponseResult;
import com.wh.model.T01;
import com.wh.model.T03;
import com.wh.model.T08;
import com.wh.model.T10;
import com.wh.model.T50;
import com.wh.service.JobService;
import com.wh.service.PurkingPerilService;
import com.wh.util.AllParam;
import com.wh.util.FileUploadUtil;
import com.wh.util.HttpUtils;
import com.wh.util.Plupload;
import com.wh.util.Utils;

/***
 * 该控制层用于处理一部分业务操作，比如今日参数，隐患管理
 * @author ZXC
 *
 */
@Controller
@RequestMapping("/job")
public class JobsController implements ServletContextAware{
	@Autowired
	private JobService jobService;
	@Autowired
	private PurkingPerilService pService;
	@Autowired
	private T10Mapper t10dao;
	
	private ServletContext servletContext;		
	
	
	
	@RequestMapping(value="/findEnterpriseType", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T01>> findEnterpriseType(String f0118){
		ResponseResult<List<T01>> rr;
		List<T01> list;
		if(null == f0118 || "".equals(f0118)){
			list = jobService.findAllEnterprise();
		}else{
			list = jobService.findEnterpriseType(f0118);
		}
		
		if(list.isEmpty()){
			rr = new ResponseResult<List<T01>>(false, "该类别下暂无数据");
		}else{
			rr = new ResponseResult<List<T01>>(true, list);
		}
			
		
		return rr;
	}
	
	
	/**
	 * @功能描述: 该接口会查询出所有企业的信息并且封装成一个List返回到客户端 ，不需要参数
	 * @提交方式: get
	 * @时间:2017年5月5日 下午8:27:54
	 * @方法名: findAllEnterprise 
	 * @接口地址: http://120.76.145.236:8090/WH/job/findAllEnterprise
	 * @测试数据: f0101:01000002, f0601:106000002, f0102:"阿里巴巴", f0103:"企业用户", f0104:"私营企业", f0105:2012-12-11, f0106:"杭州", f0107:"杭州", f0108:"马云", f0109:"13456789321", f0110:"马云", f0111:"13301334455", f0112:"二级企业", f0113:"黄", f0114:333.56, f0115:442.05, f0116:"企业工艺流程简介", f0117:"企业周边环境简介", f0118 :"普通企业", f0119:"图片地址", f0120:"企业大门照片",f0121:"已启动" ,f0122:"已达标", f0123:"已达标", f0124:"已编制", f0125:"互联网", f0126:"主要原辅材料", f0127:"产品、副产品及中间产物", f0128:"普通企业", f0129:"阿里"
	 * @return
	 * @return ResponseResult<List<T01>>
	 * @throws ParseException 
	 */
	@RequestMapping(value="/findAllEnterprise", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T01>> findAllEnterprise() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//T01 t1 = new T01(101000001, 106000010, "腾讯公司", "企业用户", "私营企业", sdf.parse("1999-11-11"), "广东深圳", "深圳", "马化腾", "13301335544", "张小龙", "13301334455", "一级企业", "蓝", 234.56, 453.05, "企业工艺流程简介", "企业周边环境简介", "普通企业", "图片地址", "企业大门照片","已启动" ,"已达标", "已达标", "已编制", "互联网", "主要原辅材料", "产品、副产品及中间产物", "普通企业", "腾讯");
		//T01 t2 = new T01(101000002, 106000002, "阿里巴巴", "企业用户", "私营企业",sdf.parse("2012-12-11"), "杭州", "杭州", "马云", "13456789321", "马云", "13301334455", "二级企业", "黄", 333.56, 442.05, "企业工艺流程简介", "企业周边环境简介", "普通企业", "图片地址", "企业大门照片","已启动" ,"已达标", "已达标", "已编制", "互联网", "主要原辅材料", "产品、副产品及中间产物", "普通企业", "阿里");
		List<T01> list = jobService.findAllEnterprise();
		/*
			list.add(t1);
			list.add(t2);
		*/
		ResponseResult<List<T01>> rr = new ResponseResult<List<T01>>(true, list);
		return rr;
	}
	
	/***
	 * @功能描述: 该接口用于手机端，电脑端对企业进行模糊搜索，用户需传入企业的名称（可以是简写可以是全称）
	 * 			name后跟的值可以是公司全称也可以是公司简称
	  * @提交方式: get
	 * @时间:2017年5月5日 下午10:24:49
	 * @方法名: findEnterprisebyName 
	 * @接口地址: http://120.76.145.236:8090/WH/job/findEnterprisebyName?name=阿里
	 * @测试数据:	 f0101:01000002, f0601:106000002, f0102:"阿里巴巴", f0103:"企业用户", f0104:"私营企业", f0105:2012-12-11, f0106:"杭州", f0107:"杭州", f0108:"马云", f0109:"13456789321", f0110:"马云", f0111:"13301334455", f0112:"二级企业", f0113:"黄", f0114:333.56, f0115:442.05, f0116:"企业工艺流程简介", f0117:"企业周边环境简介", f0118 :"普通企业", f0119:"图片地址", f0120:"企业大门照片",f0121:"已启动" ,f0122:"已达标", f0123:"已达标", f0124:"已编制", f0125:"互联网", f0126:"主要原辅材料", f0127:"产品、副产品及中间产物", f0128:"普通企业", f0129:"阿里"
	 * @param name
	 * @return ResponseResult<List<T01>>
	 * @throws ParseException 
	 */
	@RequestMapping(value="/findEnterprisebyName", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T01>> findEnterprisebyName(String name) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<T01> list = jobService.findEnterprisebyName(name);
		//T01 t2 = new T01(101000002, 106000002, "阿里巴巴", "企业用户", "私营企业",sdf.parse("2012-12-11"), "杭州", "杭州", "马云", "13456789321", "马云", "13301334455", "二级企业", "黄", 333.56, 442.05, "企业工艺流程简介", "企业周边环境简介", "普通企业", "图片地址", "企业大门照片","已启动" ,"已达标", "已达标", "已编制", "互联网", "主要原辅材料", "产品、副产品及中间产物", "普通企业", "阿里");
		//list.add(t2);
		ResponseResult<List<T01>> rr = new ResponseResult<List<T01>>(true, list);
		return rr;
	}

	
	/**
	 * 
	 * @功能描述: 根据前台传入的ID,返回相应的企业信息,是JSON对象
	 * @提交方式: GET
	 * @时间:2017年5月19日 下午11:24:12
	 * @方法名: findEnterprisebyId 
	 * @接口地址:http://120.76.145.236:8090/WH/job/findEnterprisebyId?f0101=101000007
	 * @测试数据:
	 * @param f0101
	 * @return
	 * @return ResponseResult<T01>
	 */
	@RequestMapping(value="/findEnterprisebyId", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T01> findEnterprisebyId(String f0101){
		ResponseResult<T01> rr;
		T01 t01 = jobService.findT01(Integer.parseInt(f0101));
		System.out.println("---------");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(t01 == null){
			System.err.println(sdf.format(t01.getF0130()));
			rr = new ResponseResult<>(false, "不存在该企业");
		}else{
			rr = new ResponseResult<>(true, t01);
		}
		
		return rr;
	}
	
	
	/**
	 * @功能描述: 该方法可以用于手机端及PC端对企业信息的修改，至少需传入企业ID,接口地址里的参数可继续添加，参数名就是param 后的值
	 * @时间:2017年5月5日 下午10:44:34
	 * @方法名: 修改企业信息，这个功能接口可以用于万汇公司在PC端后台对企业信息修改，必须要传入一个企业ID号，以及要修改的参数
	 * @接口地址: http:120.76.145.236:8090/WH/job/updateEnterprise?f0101=101000003&f0105=2013-01-11&f0102=学软&f0601=10600010&f0114=45.23&f0115=33.22
	 * @测试数据: 
	 * @param f0101
	 * @param f0601
	 * @param f0102
	 * @param f0103 
	 * @param f0104
	 * @param f0105
	 * @param f0106
	 * @param f0107
	 * @param f0108
	 * @param f0109
	 * @param f0110
	 * @param f0111
	 * @param f0112
	 * @param f0113
	 * @param f0114
	 * @param f0115
	 * @param f0116
	 * @param f0117
	 * @param f0118
	 * @param f0119
	 * @param f0120
	 * @param f0121
	 * @param f0122
	 * @param f0123
	 * @param f0124
	 * @param f0125
	 * @param f0126
	 * @param f0127
	 * @param f0128
	 * @param f0129
	 * @param f0130 
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/updateEnterprise", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> updateEnterprise(String f0101, String f0601, String f0102, String f0103, String f0104, String f0105, String f0106,
			String f0107, String f0108, String f0109, String f0110, String f0111, String f0112, String f0113,
			String f0114, String f0115, String f0116, String f0117, String f0118, String f0119, String f0120,
			String f0121, String f0122, String f0123, String f0124, String f0125, String f0126, String f0127,
			String f0128, String f0129,String f0130) throws NumberFormatException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		T01 t = new T01(Integer.parseInt(f0101), Integer.parseInt(f0601), f0102, f0103, f0104, sdf.parse(f0105),f0106,
				f0107, f0108, f0109,  f0110, f0111, f0112,  f0113,
				null, null, f0116, f0117, f0118, f0119, f0120,
				f0121, f0122, f0123, f0124, f0125, f0126, f0127,f0128,f0129);
		t.setF0130(sdf.parse(f0130));
		int num = jobService.updateByPrimaryKeySelective(t);
		ResponseResult<String> rr;
		if(num >= 1){
			
			rr = new ResponseResult<>(true, new String("修改成功"));
		}else{
			rr = new ResponseResult<>(false, new String("修改失败"));
		}
		
		return rr;
	}
	
	/**
	 * @功能描述: 添加新的企业，这个功能接口是用于PC端或者手机端添加一个新的企业，PC端尽量上传所有数据，手机端的参数需要上传企业的名称,照片,以及地理位置
	 * @时间:2017年5月6日 上午11:38:14
	 * @提交方式 POST
	 * @方法名: addEnterprise 
	 * @接口地址: http:120.76.145.236:8090/WH/job/addEnterprise?f0601=10600018&f0102=网易游戏&f0103=企业用户&f0104=股份制&f0105=1996-02-23&f0106=杭州&f0107=杭州&f0108=丁磊&f0109=13308092345&f0114=22.33&f0115=33.22
	 * @测试数据:
	 * @param f0601
	 * @param f0102
	 * @param f0103
	 * @param f0104
	 * @param f0105
	 * @param f0106
	 * @param f0107
	 * @param f0108
	 * @param f0109
	 * @param f0110
	 * @param f0111
	 * @param f0112
	 * @param f0113
	 * @param f0114
	 * @param f0115
	 * @param f0116
	 * @param f0117
	 * @param f0118
	 * @param f0119
	 * @param f0120
	 * @param f0121
	 * @param f0122
	 * @param f0123
	 * @param f0124
	 * @param f0125
	 * @param f0126
	 * @param f0127
	 * @param f0128
	 * @param f0129
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @return ResponseResult<T01>
	 */
	@RequestMapping(value="/addEnterprise", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T01> addEnterprise(String data,String f0601, String f0102, String f0103, String f0104, String f0105, String f0106,
			String f0107, String f0108, String f0109, String f0110, String f0111, String f0112, String f0113,
			String f0114, String f0115, String f0116, String f0117, String f0118, String f0119, String f0120,
			String f0121, String f0122, String f0123, String f0124, String f0125, String f0126, String f0127,
			String f0128, String f0129) throws NumberFormatException, ParseException{
		ResponseResult<T01> rr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//安卓端不会提交F0105字段，所以用这个f0105来判断是否是安卓端上传的数据
		if (f0105 == null) {
			
			try {
				if (f0114==null || f0115==null) {
					f0114=null;
					f0115=null;
				}
				f0119 = Utils.GenerateImage(f0119, servletContext);
				System.err.println(f0119);
				System.out.println(f0119);
				T01 t = new T01(null,null, f0102, f0103, f0104, null,f0106,
						f0107, f0108, f0109,  f0110, f0111, f0112,  f0113,
						Double.parseDouble(f0114), Double.parseDouble(f0115), f0116, f0117, f0118, f0119, f0120,
						f0121, f0122, f0123, f0124, f0125, f0126, f0127,f0128,f0129);
				System.out.println(jobService.getNoF0601());
				if (t.getF0601()==null || t.getF0601().equals("")) {
					t.setF0601(jobService.getNoF0601());
				}
				
				t = jobService.insertT01(t);
				t.setF0130(new Date());
				System.err.println(t.getF0119());
				rr = new ResponseResult<T01>(true, t);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				rr = new ResponseResult<T01>(false, "上传企业错误");
			}
		}else{
			
			
			
			//PC端
/*			T01 t = new T01(null,Integer.parseInt(f0601), f0102, f0103, f0104, sdf.parse(f0105),f0106,
					f0107, f0108, f0109,  f0110, f0111, f0112,  f0113,
					Double.parseDouble(f0114), Double.parseDouble(f0115), f0116, f0117, f0118, f0119, f0120,
					f0121, f0122, f0123, f0124, f0125, f0126, f0127,f0128,f0129);
			 t.setF0130(new Date());*/
			 
			T01 t = new T01(null,Integer.parseInt(f0601), f0102, f0103, f0104, sdf.parse(f0105),f0106,
					f0107, f0108, f0109,  f0110, f0111, f0112,  f0113,
					null, null, f0116, f0117, f0118, f0119, f0120,
					f0121, f0122, f0123, f0124, f0125, f0126, f0127,f0128,f0129);
			 t.setF0130(new Date());
/*			//获取地理位置坐标
			String result = HttpUtils.sendGet(f0107);
			String location = HttpUtils.getLocation(result);
			
			if(location.equals("failed")){
				return new ResponseResult<>(false, "上传失败,请输入更完整的通讯地址");
			}else{
				Double d1 = Double.parseDouble(location.split(",")[0]);
				Double d2 = Double.parseDouble(location.split(",")[1]);
				t.setF0114(d1);
				t.setF0115(d2);
			}*/
			
			
			t = jobService.insertT01(t);
			if(t.getF0121().equals("未开展")){
				T10 t10 = new T10();
				t10.setF0101(t.getF0101());
				t10.setF1002("隐患整改");
				t10.setF1003(new Date());
				t10.setF1004("未确认");
				t10.setF1007(0121);
				t10.setF1008("未处理");
				t10.setF1006("您公司的安全三同时状态为 ‘未开展’ ，请尽快完善");
				t10dao.insertNoId(t10);
			}
			if(t.getF0122().equals("未开展")){
				T10 t10 = new T10();
				t10.setF0101(t.getF0101());
				t10.setF1002("隐患整改");
				t10.setF1003(new Date());
				t10.setF1004("未确认");
				t10.setF1007(0122);
				t10.setF1008("未处理");
				t10.setF1006("您公司的职位三同时状态为 ‘未开展’ ，请尽快完善");
				t10dao.insertNoId(t10);
			}
			if(t.getF0123().equals("未开展")){
				T10 t10 = new T10();
				t10.setF0101(t.getF0101());
				t10.setF1002("隐患整改");
				t10.setF1003(new Date());
				t10.setF1004("未确认");
				t10.setF1007(0123);
				t10.setF1008("未处理");
				t10.setF1006("您公司的安全标准化状态为 ‘未开展’ ，请尽快完善");
				t10dao.insertNoId(t10);
			}
			if(t.getF0124().contains("未编制")) {
				System.out.println("未编制");
				T10 t10 = new T10();
				t10.setF0101(t.getF0101());
				t10.setF1002("隐患整改");
				t10.setF1003(new Date());
				t10.setF1004("未确认");
				t10.setF1007(0124);
				t10.setF1008("未处理");
				t10.setF1006("您公司的应急预案还未编制 ，请尽快完善");
				t10dao.insertNoId(t10);
			}
			rr = new ResponseResult<T01>(true, t);
		}

		 
		 return rr;
	}
	
	/**
	 * 
	 * @功能描述: 根据ID 删除企业
	 * @提交方式: GET
	 * @时间:2017年5月27日 下午11:46:33
	 * @方法名: deleteEnterprise 
	 * @接口地址: 
	 * 		本地接口：http://localhost:8080/WH/job/deleteEnterprise?f0101=
	 * 		服务器接口：http://120.76.145.236:8090/WH/job/deleteEnterprise?f0101=
	 * @测试数据:
	 * @param f0101 企业id
	 * @return
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/deleteEnterprise",method=RequestMethod.GET, produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> deleteEnterprise(String f0101){
		ResponseResult<String> rr;
		if(jobService.deleteT01(Integer.parseInt(f0101)) >= 1){
			rr = new ResponseResult<>(true, "删除成功");
		}else{
			rr = new ResponseResult<>(false, "删除失败");
		}
		return rr;
	}
	/**
	 * @功能描述: 	该方法用于查询该业务员今日参数是否设置
	 * 			查询方式，构造条件：格式：员工ID号+yyyyMMdd,在T50常数表中,查询是否有这条记录;
	 * 			如果有，则返回该条记录，人员解析：张三，李四；王五；赵六； 分别表示：专家，政府；和万汇人员。
	 * 			如果没有则返回flase,手机端应该就让用户提交今日参数;
	 * @时间:2017年5月6日 上午11:51:28
	 * @方法名: getTodayParameters 
	 * 
	 * @接口地址: http:120.76.145.236:8090/WH/job/getTodayParameters?f0201=102000001
	 * @测试数据: 
	 * @param f0201
	 * @return
	 * @return ResponseResult<T50>
	 */
	@RequestMapping(value="/getTodayParameters",method=RequestMethod.GET, produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T50> getTodayParameters(String f0201){
		ResponseResult<T50> rr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String id = f0201+"+"+sdf.format(new Date());
		T50 t = jobService.isHaveT50(id);
		//T50 t = new T50(Integer.parseInt("10500001"), "102000001+20141223", "万汇业务员", "张三，李四；王五；赵六", "");
		if(t == null){
			rr = new ResponseResult<T50>(false, "未设置今日参数");
		}else{
			rr = new ResponseResult<T50>(true, t);
		}
		 return  rr;
	}
	 
	
	
	/**
	 * 
	 * @功能描述: 获取所有的工作参数，以JSON数组形式返回 不需要参数
	 * @时间:2017年5月12日 下午9:36:20
	 * @方法名: getAllTodayParameters 
	 * @接口地址: http:120.76.145.236:8090/WH/job/getAllTodayParameters
	 * @测试数据:
	 * @return
	 * @return ResponseResult<List<T50>>
	 */
	@RequestMapping(value="/getAllTodayParameters", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T50>> getAllTodayParameters(){
		ResponseResult<List<T50>> rr;
		List<T50> t = jobService.findAllTodayParameters();
		if(t.isEmpty()){
			rr = new ResponseResult<>(false, "");
		}else{
			rr = new ResponseResult<List<T50>>(true, t);
		}
		
		return rr;
	}
	
	/**
	 * @功能描述: 该方法用于业务员在PC端，或者手机端提交他们今日的工作参数，（万汇人员，政府人员，专家）
	 * @
	 * @时间:2017年5月6日 下午12:32:55
	 * @方法名: addTodayParameters 
	 * @接口地址: http:120.76.145.236:8090/WH/job/addTodayParameters?f0201=102000001&f5003=万汇业务员&f5004=张三,李四;王五;赵六
	 * @测试数据: 
	 * @param f5002
	 * @param f5003
	 * @param f5004
	 * @param f5005
	 * @return
	 * @return ResponseResult<T50>
	 */
	@RequestMapping(value="/addTodayParameters", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T50> addTodayParameters(String param,String f0201,String f5003,String f5004,String f5005){
		if ( param !=null || !("".equals(param)) || !"null".equals(param) ) {
			f5004=param;
			ResponseResult<T50> rr;
			T50 t = new T50();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			t.setF5002(f0201+"+"+sdf.format(new Date()));
			f5003="万汇业务员";
			t.setF5003(f5003);
			t.setF5004(f5004);
			t.setF5005(f5005);
			
			if(jobService.isHaveT50(t.getF5002()) == null){
				t = jobService.insertT50(t);
			}else{
				jobService.deleteT50(t.getF5002());
				t = jobService.insertT50(t);
			}
			
			rr = new ResponseResult<T50>(true, t);
			return rr;
		}
		ResponseResult<T50> rr;
		T50 t = new T50();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		t.setF5002(f0201+"+"+sdf.format(new Date()));
		
		
		f5003="万汇业务员";
		t.setF5003(f5003);
		t.setF5004(f5004);
		t.setF5005(f5005);
		
		
		if(jobService.isHaveT50(t.getF5002()) == null){
			t = jobService.insertT50(t);
		}else{
			jobService.deleteT50(t.getF5002());
			t = jobService.insertT50(t);
		}
		rr = new ResponseResult<T50>(true, t);
		return rr;
	}
	
	
	
	
	/**
	 * 
	 * @功能描述: 每日参数的修改接口
	 * @提交方式: POST
	 * @时间:2017年5月23日 上午12:25:00
	 * @方法名: updateTodayParameters 
	 * @接口地址: http:120.76.145.236:8090/WH/job/addTodayParameters
	 * @测试数据:
	 * @param param 手机端传入的参数集合
	 * @param f5001  每日参数ID ,必要的一个参数
	 * @param f5003   类别为：万汇业务员 ,固定，不变的一个参数,不让其修改
	 * @param zj   专家人员名称  
	 * @param zf   政府人员名称
	 * @param wh   万汇业务员名称 
	 * @param f5005   备注     该值可有可无
	 * @return
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/updateTodayParameters", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> updateTodayParameters(String param,String f5001,String zj,String zf,String wh,String f5003,String f5005){
		ResponseResult<String> rr;
		T50 t = new T50();
		t.setF5001(Integer.parseInt(f5001));
		t.setF5003(f5003);
		t.setF5004(zj+";"+zf+";"+wh);
		t.setF5005(f5005);
		
		if(jobService.updateT50(t) >= 1){
			rr = new ResponseResult<>(true, "修改成功");
		}else{
			rr = new ResponseResult<>(false, "修改失败");
		}
		return rr;
	} 
	
	
	/**
	 * 
	 * @功能描述: 根据id 删除隐患
	 * @提交方式: 
	 * @时间:2017年5月30日 下午12:41:11
	 * @方法名: deletePurkingPeril 
	 * @接口地址:http:120.76.145.236:8090/WH/job/deletePurkingPeril?f0801=
	 * @测试数据:
	 * @param f0801
	 * @return
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/deletePurkingPeril", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> deletePurkingPeril(String f0801){
		ResponseResult<String> rr;
		if(jobService.deleteT08(Integer.parseInt(f0801)) >=1){
			rr = new ResponseResult<>(true, "删除成功");
		}else{
			rr = new ResponseResult<>(false, "删除失败");
		}
		return rr;
	}
	
	/**
	 * @功能描述: 修改隐患，员工端提交复查记录可以调用该接口，需要传入一个复查的结果，以及该隐患的ID
	 * 			企业端提交整改记录可以调用该接口，需要传入该隐患的Id以及修改后的照片
	 * @时间:2017年5月6日 下午12:20:42
	 * @方法名: updatePurkingPeril 
	 * @接口地址: http:120.76.145.236:8090/WH/job/updatePurkingPeril
	 * @测试数据:
	 * @param f0101  隐患所属企业ID
	 * @param f0801  隐患ID
	 * @param f0802  隐患描述
	 * @param f0803  参检人员:采用如下格式 张万汇，李万汇；王专家，赵专家；钱政府，黄政府；其中，第一部分表示万汇工作人员，第二部分表 示专家，第三部分表示政府人员
	 * @param f0804  整改情况
	 * @param f0805  企业确认人员
	 * @param f0806  复查人员
	 * @param f0807  复查结果
	 * @param f0808  企业整改人员
	 * @param f0809  整改类型（硬）：不限时间（默认），限期整改
	 * @param f0810  限期天数：如果是限期整改隐患，那就是限期天数，5，10，15，30天等
	 * @param f0811  隐患发现时间
	 * @param f0812  隐患必须整改完的最后天数
	 * @param f0813  隐患图片的URL1
	 * @param f0814  隐患图片的URL2
	 * @param f0815  隐患图片的URL3
	 * @param f0816  整改后的隐患图片的URL1
	 * @param f0817  整改后的隐患图片的URL2
	 * @param f0818  整改后的隐患图片的URL3
	 * @param f0819  是否重大危险源（硬）：是、否
	 * @param f0820  隐患类别：提的是隐患所属专业（安全管理、总图运输、生产工艺、工艺设备、特种设备、安全设施、电气、消防、其他）
	 * @param f1101 ID:如果该隐患属于重大危险源（即：F0819值为是），则要填，否则置为空
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @return ResponseResult<T08>
	 */
	@RequestMapping(value="/updatePurkingPeril", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T08> updatePurkingPeril(Plupload plupload,HttpServletRequest req,HttpServletResponse res,String f0101,String f0801,String f0802,String f0803,String f0804,String f0805,String f0806,String f0807,String f0808,String f0809,String f0810,
			String f0811,String f0812,String f0813,String f0814,String f0815,String f0816,String f0817,String f0818,String f0819,String f0820,String f1101,String imgCount) throws NumberFormatException, ParseException{
		ResponseResult rr;
		System.err.println("imgCount"+imgCount);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (f0810==null || f0811==null || f0812==null ) {
			try {
				//return new ResponseResult<T08>(true, "success");
				System.out.println(f0101);
				System.out.println(f0801);
				System.out.println(f0804);
				T08 t = new T08(Integer.parseInt(f0801), Integer.parseInt(f0101),f0802,f0803,f0804,f0805,f0806,
						f0807, f0808, f0809, null,null, null,f0813,f0814,
						f0815, f0816, f0817, f0818, f0819, f0820, null) ;
				int i = jobService.updateT08(t);
				
				if(i>=1){
					rr = new ResponseResult<T08>(true, t);
				}else{
					rr = new ResponseResult<T08>(false,"更新失败" );
				}
				
			} catch (Exception e) {
				rr = new ResponseResult<T08>(false, "修改失败");
			}
			return rr;
			
		}else{
			if(Integer.parseInt(imgCount) == 0){
				T08 t = new T08(Integer.parseInt(f0801), Integer.parseInt(f0101),f0802,f0803,f0804,f0805,f0806,
						f0807, f0808, f0809, Integer.parseInt(f0810),sdf.parse(f0811), sdf.parse(f0812),f0813,f0814,
						f0815, f0816, f0817, f0818, f0819, f0820, null) ;
				int i = jobService.updateT08(t);
				
				if(i>=1){
					rr = new ResponseResult<T08>(true, t);
				}else{
					rr = new ResponseResult<T08>(false,"更新失败" );
				}
			}else{
				//设置上传参数
				plupload.setRequest(req); 
				//文件保存的绝对路径
				String path = plupload.getRequest().getSession().getServletContext().getRealPath("/") + "images/";
				//相对路径
				String url = plupload.getRequest().getSession().getServletContext().getContextPath() + "/images/";
				//创建UUID为文件命名
				String uuid = UUID.randomUUID().toString();
				uuid = uuid.replace("-","");
				File dir = new File(path);
				
				//获取记录值
				T50 t50 = pService.findPid();
				//f0813,f0814,f0815 新添加的图片
				//f0816,f0817,f0818 隐患修改的后的图片
				//t50.getF5002();	 如果是第一条记录则该值为1，第一条记录插入后将保存刚刚插入的那条记录的ID，并在最后一条记录插入时，将该值赋值为空
				//t50.getF5003();   如果是第一条记录则该值为1，第二条记录则该值为2，是第三条记录则该值为3。在最后一条记录插入后，该值赋值为1
				if(t50.getF5002().equals("1")){
					T08 t08 = new T08(Integer.parseInt(f0801), Integer.parseInt(f0101),f0802,f0803,
							f0804,f0805,f0806,f0807, f0808, f0809, 
							Integer.parseInt(f0810),sdf.parse(f0811), 
							sdf.parse(f0812),null,null,null, null, 
							null, null, f0819, f0820, null);
					
					//第一条记录
					try {
						FileUploadUtil.upload1(plupload, dir, uuid);
						t08.setF0813("http://"+AllParam.ServerUrl+url+uuid+"."+plupload.getName().split("\\.")[1]);
						
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(jobService.updateT08(t08) >= 1){
						//当前图片记录条数和总记录条数相等了就说明是最后一条
						if(!t50.getF5003().equals(imgCount)){
							t50.setF5002(t08.getF0801()+"");
							t50.setF5003("2");
							pService.updateT50(t50);
						}
						rr = new ResponseResult<>(true, "添加成功");
					}else{
						rr = new ResponseResult<>(false, "添加失败");
					}				
				}else if(Integer.parseInt(t50.getF5003()) <= Integer.parseInt(imgCount) && Integer.parseInt(t50.getF5003()) == 2){
					T08 t08 = new T08();
					t08.setF0801(Integer.parseInt(t50.getF5002()));
					//第二张图片
					try {
						FileUploadUtil.upload1(plupload, dir, uuid);
						t08.setF0814(url+uuid+"."+plupload.getName().split("\\.")[1]);
						
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(jobService.updateT08(t08) >= 1){
						
						if(!t50.getF5003().equals(imgCount)){
							t50.setF5003("3");
						}else{
							//表示现在是最后一张图片
							t50.setF5002("1");
							t50.setF5003("1");
						}
						pService.updateT50(t50);
						rr = new ResponseResult<>(true, "添加成功");
					}else{
						rr = new ResponseResult<>(false, "添加失败");
					}				
				}else{
					//
					T08 t08 = new T08();
					t08.setF0801(Integer.parseInt(t50.getF5002()));
					try {
						FileUploadUtil.upload1(plupload, dir, uuid);
						t08.setF0815(url+uuid+"."+plupload.getName().split("\\.")[1]);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(jobService.updateT08(t08) >= 1){
						//第三张就是最后一张图片
						t50.setF5002("1");
						t50.setF5003("1");
						pService.updateT50(t50);
						rr = new ResponseResult<>(true, "添加成功");
					}else{
						rr = new ResponseResult<>(false, "添加失败");
					}					
				}
			}			
			return rr;
		}

		
	}
	
	/**
	 * @功能描述: 查询某个公司下的所有隐患，该接口可以用于PC端和手机端查询,如果id不为空，就表示是查询该公司下的所有隐患，否则查询全部隐患
	 * @时间:2017年5月6日 下午12:40:46
	 * @方法名: findAllPurkingPeril
	 * @接口地址: http:120.76.145.236:8090/WH/job/findAllPurkingPeril?id=101000002
	 * 			http:120.76.145.236:8090/WH/job/findAllPurkingPeril?id=101000007
	 * 			http:120.76.145.236:8090/WH/job/findAllPurkingPeril
	 * @测试数据: 
	 * @param id 表示的某个企业的id
	 * @return
	 * @return ResponseResult<List<T08>>
	 */
	@RequestMapping(value="/findAllPurkingPeril", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T08>> findAllPurkingPeril(String id){
		List<T08> t;
		ResponseResult<List<T08>> rr;
		try {
			if(id != null || "".equals(id)){
				t = jobService.selectT08(Integer.parseInt(id));
				if(t.isEmpty()){
					rr = new ResponseResult<List<T08>>(false, "未查询到该企业的隐患情况");
					return rr;
				}
				rr = new ResponseResult<List<T08>>(true, t);
			}else{
				t = jobService.selectT08();
				rr = new ResponseResult<List<T08>>(true, t);
			}
		} catch (Exception e) {
			System.err.println("error is:"+e.getMessage());
			rr = new ResponseResult<List<T08>>(false, "查询失败");
		}

		
		return rr;
	}
	
	/**
	 * 
		 * @功能描述: 查询某个企业未完结的隐患信息（）（未整改和已整改的）新隐患管理
		 * @提交方式: GET,POST
		 * @时间:2017年7月18日 下午11:01:16
		 * @方法名: selectNewPurkingPeril 
		 * @接口地址:http://120.76.145.236:8090/WH/job/findNewPurkingPeril?f0101=101000001
		 * @本地测试地址:
		 * @测试数据:
		 * @param f0101
		 * @return
		 * @return ResponseResult<List<T08>>
	 */
	@RequestMapping(value="/findNewPurkingPeril",method={RequestMethod.GET,RequestMethod.POST},
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T08>> selectNewPurkingPeril(String f0101)
	{
		ResponseResult<List<T08>> rr = null;
		List<T08> t;
		try {
			if(f0101 != null || "".equals(f0101)){
				t = jobService.seletNewPurkingPeril(Integer.parseInt(f0101));
				if(t.isEmpty()){
					rr = new ResponseResult<List<T08>>(false, "未查询到该企业的未整改和未确认隐患情况");
					return rr;
				}
				rr = new ResponseResult<List<T08>>(true, t);
			}
		} catch (Exception e) {
			System.err.println("error is:"+e.getMessage());
			rr = new ResponseResult<List<T08>>(false, "查询失败");
		}
		return rr;
	}
	/**
	 * 
		 * @功能描述: 查询某个公司已完结的隐患信息（已确认的），隐患管理
		 * @提交方式: GET,POST
		 * @时间:2017年7月18日 下午11:03:42
		 * @方法名: selectSurePurkingPeril 
		 * @接口地址:http://120.76.145.236:8090/WH/job/selectSurePurkingPeril?f0101=101000001
		 * @本地测试地址:
		 * @测试数据:
		 * @param f0101
		 * @return
		 * @return ResponseResult<List<T08>>
	 */
	@RequestMapping(value="/selectSurePurkingPeril",method={RequestMethod.GET,RequestMethod.POST},
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T08>> selectSurePurkingPeril(String f0101)
	{
		ResponseResult<List<T08>> rr=null;
		List<T08> t;
		try {
			if(f0101 != null || "".equals(f0101)){
				t = jobService.selectSurePurkingPeril(Integer.parseInt(f0101));
				if(t.isEmpty()){
					rr = new ResponseResult<List<T08>>(false, "未查询到该企业的未整改和未确认隐患情况");
					return rr;
				}
				rr = new ResponseResult<List<T08>>(true, t);
			}
			
		} catch (Exception e) {
			rr = new ResponseResult<List<T08>>(false, "未查询到");
		}
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: 该接口可以用于PC端,手机端提交某企业的隐患情况 ，添加隐患
	 * @时间:2017年5月12日 下午11:05:53
	 * @方法名: addPurkingPeril 
	 * @接口地址: http:120.76.145.236:8090/WH/job/addPurkingPeril?f0101=
	 * @测试数据:
	 * @param f0101  隐患所属企业ID
	 * @param f0801  隐患ID
	 * @param f0802  隐患描述
	 * @param f0803  参检人员:采用如下格式 张万汇，李万汇；王专家，赵专家；钱政府，黄政府；其中，第一部分表示万汇工作人员，第二部分表 示专家，第三部分表示政府人员
	 * @param f0804  整改情况
	 * @param f0805  企业确认人员
	 * @param f0806  复查人员
	 * @param f0807  复查结果
	 * @param f0808  企业整改人员
	 * @param f0809  整改类型（硬）：不限时间（默认），限期整改
	 * @param f0810  限期天数：如果是限期整改隐患，那就是限期天数，5，10，15，30天等
	 * @param f0811  隐患发现时间
	 * @param f0812  隐患必须整改完的最后天数
	 * @param f0813  隐患图片的URL1
	 * @param f0814  隐患图片的URL2
	 * @param f0815  隐患图片的URL3
	 * @param f0816  整改后的隐患图片的URL1
	 * @param f0817  整改后的隐患图片的URL2
	 * @param f0818  整改后的隐患图片的URL3
	 * @param f0819  是否重大危险源（硬）：是、否
	 * @param f0820  隐患类别：提的是隐患所属专业（安全管理、总图运输、生产工艺、工艺设备、特种设备、安全设施、电气、消防、其他）
	 * @param f1101 ID:如果该隐患属于重大危险源（即：F0819值为是），则要填，否则置为空
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/addPurkingPeril", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> addPurkingPeril(Plupload plupload,HttpServletRequest req,HttpServletResponse res,String f0101,String f0801,String f0802,String f0803,String f0804,String f0805,String f0806,String f0807,String f0808,String f0809,String f0810,
			String f0811,String f0812,String f0201,String f0813,String f0814,String f0815,String f0816,String f0817,String f0818,String f0819,String f0820,String f1101,String imgCount) throws NumberFormatException, ParseException{
		ResponseResult<String> rr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		T50 tt50 = jobService.isHaveT50(f0201+"+"+sdf.format(new Date()));
		if (f0811==null || f0812==null || f0811.equals("") || f0812.equals("")) {
			try {
				f0811 = Utils.NowTime();
				f0812 = Utils.getfinalday(f0811,f0810);
				if(tt50 != null){
					f0803 = tt50.getF5004();
				}
				//f0810="30";
				f0804="未整改";
				f1101=null;
							
				T08 t = new T08(null, Integer.parseInt(f0101),f0802,f0803,f0804,f0805,f0806,
						f0807, f0808, f0809, Integer.parseInt(f0810),sdf.parse(f0811), sdf.parse(f0812),f0813,f0814,
						f0815, f0816, f0817, f0818, f0819, f0820, null);
				t.setF0813(Utils.GenerateImage(f0813, servletContext));
				t.setF0814(Utils.GenerateImage(f0814, servletContext));
				t.setF0815(Utils.GenerateImage(f0815, servletContext));
				System.err.println("------------------");
				int n = jobService.addT08(t);
				if( n>= 1){
					rr = new ResponseResult<>(true, "添加成功");
				}else{
					rr = new ResponseResult<>(false, "添加失败");
				}
			} catch (Exception e) {
				rr = new ResponseResult<>(false, "添加失败");
				System.err.println(e.getMessage());
			}

			return rr;
		}else{
			if(Integer.parseInt(imgCount) == 0){
				T08 t = new T08(null, Integer.parseInt(f0101),f0802,f0803,f0804,f0805,f0806,
						f0807, f0808, f0809, Integer.parseInt(f0810),sdf.parse(f0811), sdf.parse(f0812),f0813,f0814,
						f0815, f0816, f0817, f0818, f0819, f0820, null) ;
				if(tt50 != null){
					t.setF0803(tt50.getF5004());
				}
				if(req.getParameter("f1101") == null || "null".equals(req.getParameter("f1101"))){
					System.err.println(req.getParameter("f1101"));
				}else{
					t.setF1101(Integer.parseInt(req.getParameter("f1101")));
				}
				t.setF0802(f0802);
				if(jobService.addT08(t) >= 1){
					rr = new ResponseResult<>(true, "添加成功");
				}else{
					rr = new ResponseResult<>(false, "添加失败");
				}
				return rr;
			}else{
				//设置上传参数
				plupload.setRequest(req); 
				//文件保存的绝对路径
				String path = plupload.getRequest().getSession().getServletContext().getRealPath("/") + "images/";
				//相对路径
				String url = plupload.getRequest().getSession().getServletContext().getContextPath() + "/images/";
				//创建UUID为文件命名
				String uuid = UUID.randomUUID().toString();
				uuid = uuid.replace("-","");
				File dir = new File(path);
				//f0813,f0814,f0815 新添加的图片
				//f0816,f0817,f0818 隐患修改的后的图片
				
				//获取记录值
				
				T50 t50 = pService.findPid();

				//t50.getF5002();	 如果是第一条记录则该值为1，第一条记录插入后将保存刚刚插入的那条记录的ID，并在最后一条记录插入时，将该值赋值为空
				//t50.getF5003();   如果是第一条记录则该值为1，第二条记录则该值为2，是第三条记录则该值为3。在最后一条记录插入后，该值赋值为1
				if(t50.getF5002().equals("1")){
					T08 t08 = new T08(null, Integer.parseInt(f0101),f0802,f0803,
							f0804,f0805,f0806,f0807, f0808, f0809, 
							Integer.parseInt(f0810),sdf.parse(f0811), 
							sdf.parse(f0812),null,null,null, null, 
							null, null, f0819, f0820, null);
					if(tt50 != null){
						t08.setF0803(tt50.getF5004());
					}
					//第一条记录
					try {
						FileUploadUtil.upload1(plupload, dir, uuid);
						t08.setF0813("http://"+AllParam.ServerUrl+url+uuid+"."+plupload.getName().split("\\.")[1]);
						
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(jobService.addT08(t08) >= 1){
						//当前图片记录条数和总记录条数相等了就说明是最后一条
						if(!t50.getF5003().equals(imgCount)){
							t50.setF5002(t08.getF0801()+"");
							t50.setF5003("2");
							pService.updateT50(t50);
						}
						
						rr = new ResponseResult<>(true, "添加成功");
					}else{
						rr = new ResponseResult<>(false, "添加失败");
					}				
				}else if(Integer.parseInt(t50.getF5003()) <= Integer.parseInt(imgCount) && Integer.parseInt(t50.getF5003()) == 2){
					T08 t08 = new T08();
					t08.setF0801(Integer.parseInt(t50.getF5002()));
					//第二张图片
					try {
						FileUploadUtil.upload1(plupload, dir, uuid);
						t08.setF0814("http://"+AllParam.ServerUrl+url+uuid+"."+plupload.getName().split("\\.")[1]);
						
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(jobService.updateT08(t08) >= 1){
						
						if(!t50.getF5003().equals(imgCount)){
							t50.setF5003("3");
						}else{
							//表示现在是最后一张图片
							t50.setF5002("1");
							t50.setF5003("1");
						}
						pService.updateT50(t50);
						rr = new ResponseResult<>(true, "添加成功");
					}else{
						rr = new ResponseResult<>(false, "添加失败");
					}				
				}else{
					//最后一张图片
					T08 t08 = new T08();
					t08.setF0801(Integer.parseInt(t50.getF5002()));
					try {
						FileUploadUtil.upload1(plupload, dir, uuid);
						t08.setF0815("http://"+AllParam.ServerUrl+url+uuid+"."+plupload.getName().split("\\.")[1]);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(jobService.updateT08(t08) >= 1){
						//第三张就是最后一张图片
						t50.setF5002("1");
						t50.setF5003("1");
						pService.updateT50(t50);
						rr = new ResponseResult<>(true, "添加成功");
					}else{
						rr = new ResponseResult<>(false, "添加失败");
					}					
				}
				}
			}
		return rr;
	}
	
	
	/***
	 * @功能描述: PC端使用的接口，获取每日提交上来的新隐患
	 * @提交方式: GET
	 * @时间:2017年5月25日 下午10:05:46
	 * @方法名: findTodayPurkingPeril 
	 * @接口地址: 120.76.145.236:8090/WH/job/findTodayPurkingPeril
	 * @测试数据:
	 * @return
	 * @return ResponseResult<List<T08>>
	 */
	@RequestMapping(value="/findTodayPurkingPeril", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T08>> findTodayPurkingPeril(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ResponseResult<List<T08>> rr;
		//获取到当天的隐患列表
		
		List<T08> list = jobService.selectTodayT08(sdf.format(new Date()));
		if(list.isEmpty()){
			rr = new ResponseResult<List<T08>>(false, "未查询到今天提交的隐患");
		}else{
			rr = new ResponseResult<List<T08>>(true, list);
		}
		
		return rr;
	}
	
	/***
	 * 
	 * @功能描述: 这个接口用于查询某个地区下的企业列表，将会返回一个包含了该地区下的企业的JSON数组
	 * @时间:2017年5月11日 上午12:47:06
	 * @方法名: findEnterpriseByArea 
	 * @接口地址: http:120.76.145.236:8090/WH/job/findEnterpriseByArea?f0601=10600011
	 * @测试数据: {"success":true,"data":[{"f0101":101000001,"f0601":10600011,"f0102":"腾讯公司","f0103":"企业用户","f0104":"私营企业","f0105":942076800000,"f0106":"广东深圳","f0107":"深圳","f0108":"马化腾","f0109":"13301335544","f0110":"张小龙","f0111":"13301334455","f0112":"一级企业","f0113":"蓝","f0114":234.56,"f0115":453.05,"f0116":"企业工艺流程简介","f0117":"企业周边环境简介","f0118":"普通企业","f0119":"图片地址","f0120":"企业大门照片","f0121":"已启动","f0122":"已达标","f0123":"已达标","f0124":"已编制","f0125":"互联网","f0126":"主要原辅材料","f0127":"产品、副产品及中间产物","f0128":"普通企业","f0129":"腾讯","t08":null}],"error":null}
	 * @param f0601
	 * @return
	 * @return ResponseResult<List<T01>>
	 */
	@RequestMapping(value="/findEnterpriseByArea", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T01>> findEnterpriseByArea(String f0601){
		ResponseResult<List<T01>> rr ;
		
		List<T01> list = jobService.findEnterpriseByArea(Integer.parseInt(f0601));
		if(list.isEmpty()){
			rr = new ResponseResult<List<T01>>(false, "该地区下暂时没有企业");
		}else{
			rr = new ResponseResult<List<T01>>(true, list);
		}
		return rr;
	}
	
	//特种设备处理
	
	/**
	 * 
	 * @功能描述: 该接口会返回出所有企业的特种设备信息的JSON数组
	 * @时间:2017年5月11日 下午10:24:04
	 * @方法名: findAllEquipment 
	 * @接口地址: http:120.76.145.236:8090/WH/job/findAllEquipment
	 * @测试数据: 暂无
	 * @return
	 * @return PcResult<T03,Void>
	 */
	@RequestMapping(value="/findAllEquipment", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T03>> findAllEquipment(){
		ResponseResult<List<T03>> rr;
		List<T03> list = jobService.findAllEquipment();
		if(list.isEmpty()){
			rr = new ResponseResult<>(false, "暂无特种设备");
		}else{
			rr = new ResponseResult<List<T03>>(true, list);
		}
		return rr;
	}
	 
	/**
	 * 
	 * @功能描述: 该接口可以根据前台传入的企业ID返回该企业的特种设备的JSON数组
	 * @时间:2017年5月11日 下午10:35:30
	 * @方法名: findAllEquipmentByCompany 
	 * @接口地址: http:120.76.145.236:8090/WH/job/findAllEquipmentByCompany?f0101=101000001
	 * @测试数据: 暂无
	 * @param f0101
	 * @return
	 * @return PcResult<List<T03>,Void>
	 */
	@RequestMapping(value="/findAllEquipmentByCompany", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T03>> findAllEquipmentByCompany(String f0101){
		ResponseResult<List<T03>> rr;
		List<T03> list = jobService.findAllEquipmentByCompany(Integer.parseInt(f0101));
		
		if(list.isEmpty()){
			rr = new ResponseResult<>(false, "暂无特种设备");;
		}else{
			rr = new ResponseResult<List<T03>>(true, list);
		}
		
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: 修改特种设备信息,根据传入的ID修改相应的特种设备信息
	 * @时间:2017年5月11日 下午10:53:11
	 * @方法名: updateEquipment 
	 * @接口地址: http:120.76.145.236:8090/WH/job/updateEquipment
	 * @测试数据:
	 * @param f0301 特种设备ID
	 * @param f0101 企业ID
	 * @param f0302 设备名称
	 * @param f0303 设备级别
	 * @param f0304 生产厂家
	 * @param f0305 投用日期
	 * @param f0306 注册证编号
	 * @param f0307 厂内编号
	 * @param f0308 设备类别
	 * @param f0309 规格型号
	 * @param f0310 生产日期
	 * @param f0311 使用场所
	 * @param f0312 使用证编号
	 * @param f0313 定检周期
	 * @param f0314 主要性能参数
	 * @param f0315 设备是否过期（硬）：未过期（Default）,过期（过期的检测是，Ｔ０４表中的安全附件，有未检测的记录）
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @return PcResult<String,Void>
	 */
	@RequestMapping(value="/updateEquipment", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> updateEquipment(String f0301, String f0101, String f0302, String f0303, String f0304, String f0305, String f0306,
			String f0307, String f0308, String f0309, String f0310, String f0311, String f0312, String f0313,
			String f0314, String f0315) throws NumberFormatException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		T03 t03 = new T03(Integer.parseInt(f0301), Integer.parseInt(f0101), f0302, f0303, f0304, sdf.parse(f0305), f0306, f0307, f0308, f0309, sdf.parse(f0310), f0311, f0312, f0313, f0314, f0315);
		ResponseResult<String> rr;
		if(jobService.updateEquipment(t03) >= 1 ){
			rr = new ResponseResult<>(true, "修改成功");
		}else{
			rr = new ResponseResult<>(false, "修改失败");
		}
		
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: 为某个企业添加新的特种设备，需要全字段参数，返回给用户端一个判定值，以及一条提示语句（添加成功/添加失败）
	 * @时间:2017年5月12日 上午12:06:54
	 * @方法名: addEquipment 
	 * @接口地址:http:120.76.145.236:8090/WH/job/addEquipment?f0101=101000001&f0302=灭火器&f0303=一级&f0304=重庆科技学院&f0305=2016-12-11&f0306=asda1111&f0307=9207&f0308=1&f0309=1&f0310=1999-11-11&f0311=车间&f0312=123&f0313=30天&f0314=无&f0315=否
	 * @测试数据:
	 * @param f0301 特种设备ID
	 * @param f0101 企业ID
	 * @param f0302 设备名称
	 * @param f0303 设备级别
	 * @param f0304 生产厂家
	 * @param f0305 投用日期
	 * @param f0306 注册证编号
	 * @param f0307 厂内编号
	 * @param f0308 设备类别
	 * @param f0309 规格型号
	 * @param f0310 生产日期
	 * @param f0311 使用场所
	 * @param f0312 使用证编号
	 * @param f0313 定检周期
	 * @param f0314 主要性能参数
	 * @param f0315 设备是否过期（硬）：未过期（Default）,过期（过期的检测是，Ｔ０４表中的安全附件，有未检测的记录）
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @return PcResult<String,Void>
	 */
	@RequestMapping(value="/addEquipment", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> addEquipment(String f0301, String f0101, String f0302, String f0303, String f0304, String f0305, String f0306,
			String f0307, String f0308, String f0309, String f0310, String f0311, String f0312, String f0313,
			String f0314, String f0315) throws NumberFormatException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		T03 t03 = new T03(null, Integer.parseInt(f0101), f0302, f0303, f0304, sdf.parse(f0305), f0306, f0307, f0308, f0309, sdf.parse(f0310), f0311, f0312, f0313, f0314, f0315);
		ResponseResult<String> rr;
		if(jobService.addEquipment(t03) >= 1 ){
			rr = new ResponseResult<>(true, "添加成功");
		}else{
			rr = new ResponseResult<>(false, "添加失败");
		}
		
		return rr;
	}
	
	/**
	 * @功能描述: 该接口可以根据前台传入的特种设备ID,删除相应的特种设备
	 * @时间:2017年5月11日 下午10:35:30
	 * @方法名: findAllEquipmentByCompany 
	 * @接口地址: http:120.76.145.236:8090/WH/job/deleteEquipment?f0301=
	 * @测试数据: 暂无
	 * @param f0101
	 * @return
	 * @return PcResult<List<T03>,Void>
	 */
	@RequestMapping(value="/deleteEquipment", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> deleteEquipment(String f0301){
		ResponseResult<String> rr;
		if(jobService.deleteEquipment(Integer.parseInt(f0301)) >= 1 ){
			rr = new ResponseResult<>(true, "删除成功");
		}else{
			rr = new ResponseResult<>(false, "删除失败");
		}
		
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: 根据企业ID查询该企业的信息
	 * @提交方式: GET
	 * @时间:2017年5月27日 上午11:32:11
	 * @方法名: findEquipmentInfo 
	 * @接口地址:http:120.76.145.236:8090/WH/job/findEquipmentInfo?f0101=101000001
	 * @测试数据:
	 * @param f0101  企业ID
	 * @return
	 * @return ResponseResult<T01>
	 */
	@RequestMapping(value="/findEquipmentInfo", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T01> findEquipmentInfo(String f0101){
		ResponseResult<T01> rr;
		T01 t01 = jobService.findEnterpriseById(Integer.parseInt(f0101));
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(t01!= null){
			rr = new ResponseResult<>(true, t01);
		}else{
			rr = new ResponseResult<>(false,"未查询到该企业");
		}
		
		return rr;
	}
	
	@RequestMapping(value="/changeLoac", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T01> changeLoaction(String f0101,String f0601){
		ResponseResult<T01> rr;
		
		T01 t01 = new T01();
		try{
			t01.setF0101(Integer.parseInt(f0101));
		}catch(Exception e){
			rr = new ResponseResult<>(false, "修改失败，请重试");
		}
		try{
			t01.setF0601(Integer.parseInt(f0601));
		}catch(Exception e){
			rr = new ResponseResult<>(false, "修改失败，请重试");
		}
		
		if(jobService.updateT01(t01) > 0){
			rr = new ResponseResult<>(true, "修改成功");
		}else{
			rr = new ResponseResult<>(false, "修改失败，请重试");
		}

		return rr;
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		
		this.servletContext = servletContext;
	}
	
}