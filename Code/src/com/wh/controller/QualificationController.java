package com.wh.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wh.dto.ResponseResult;
import com.wh.model.T05;
import com.wh.service.QualificationService;

/**
 * 
 * @author ZXC
 *	该控制层提供企业资质以及员工资质的各种操作接口
 */
@RequestMapping("/Qualification")
@Controller
public class QualificationController {
	
	@Autowired
	QualificationService qService;

	/**
	 * @功能描述: 通过用户端提供的企业id来查询该企业下的所有企业资质，并且给他返回一个JSON对象数组
	 * @提交方式: GET
	 * @时间:2017年5月23日 下午10:39:48
	 * @方法名: findAllEnterpriseQById 
	 * @接口地址:http://120.76.145.236:8090/WH/Qualification/findAllEnterpriseQById?f0101=101000001
	 * @测试数据:
	 * @param f0101 企业ID
	 * @return
	 * @return ResponseResult<List<T05>>
	 */
	@RequestMapping(value="/findAllEnterpriseQById", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T05>> findAllEnterpriseQById(String f0101){
		ResponseResult<List<T05>> rr;
		List<T05> list = qService.findAllEnterpriseQ(Integer.parseInt(f0101));
		
		if(list.isEmpty()){
			rr = new ResponseResult<>(false, "未查询到任何信息");
		}
		else{
			rr = new ResponseResult<List<T05>>(true, list);
		}
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: 查询所有企业的企业资质，并且给他返回一个JSON对象数组
	 * @提交方式: 
	 * @时间:2017年5月23日 下午10:41:30
	 * @方法名: findAllEnterpriseQ 
	 * @接口地址: http://120.76.145.236:8090/WH/Qualification/findAllEnterpriseQ
	 * @测试数据:
	 * @return ResponseResult<List<T05>>
	 */
	@RequestMapping(value="/findAllEnterpriseQ", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T05>> findAllEnterpriseQ(){
		ResponseResult<List<T05>> rr;
		List<T05> list = qService.findAllEnterpriseQ();
		
		if(list.isEmpty()){
			rr = new ResponseResult<>(false, "未查询到任何信息");
		}
		else{
			rr = new ResponseResult<List<T05>>(true, list);
		}
		return rr;
	}
	
	/**
	 * @功能描述: 企业资质更新接口,返回一个json对象，json.success 是一个bool值，表示更新成功或者失败,json.error 更新失败的提示信息，json.data 更新成功的提示信息
	 * @提交方式: GET
	 * @时间:2017年5月19日 下午9:38:20
	 * @方法名: updateInfo 
	 * @接口地址: 手机端：http://120.76.145.236:8090/WH/Qualification/updateQInfo?param=
	 * 			PC端：http://120.76.145.236:8090/WH/Qualification/updateQInfo?f0501=10500002&f0502=税务登记证ss&f0503=23213asdas4343&f0504=2019-11-18&f0505=否
	 * @测试数据:
	 * @param param 手机端传入的json对象参数
	 * @param f0501  企业资质id
	 * @param f0502 证照名称
	 * @param f0503 证照编号
	 * @param f0504 有效日期
	 * @param f0505 证照是否有效:(硬）有效、过期、无效
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @return ResponseResult<String> 
	 */
	@RequestMapping(value="/updateQInfo", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> updateQInfo(String param,String f0501,String f0502,String f0503,String f0504,String f0505) throws NumberFormatException, ParseException{
		ResponseResult<String> rr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		T05 t ;
		if(param == null || "".equals(param)){
			t = new T05(Integer.parseInt(f0501), null, f0502, f0503, sdf.parse(f0504), f0505);
		}else{
			t = (T05)JSON.parse(param);
		}
		if(qService.updateQinfo(t) >= 1){
			rr = new ResponseResult<>(true, "更新成功");
		}else{
			rr = new ResponseResult<>(false, "更新失败");
		}
		return rr;
	}

	/**
	 * 
	 * @功能描述: 企业证照的删除接口,返回一个json对象，json.success 是一个bool值，表示删除成功或者失败,json.error 删除失败的提示信息，json.data 删除成功的提示信息
	 * @提交方式: GET
	 * @时间:2017年5月19日 下午10:11:56
	 * @方法名: deleteQInfo 
	 * @接口地址:手机端：http://120.76.145.236:8090/WH/Qualification/deleteQInfo?param=
	 * 			PC端：http://120.76.145.236:8090/WH/Qualification/deleteQInfo?f0501=10500002
	 * @测试数据:
	 * @param param 手机端传入的json对象参数
	 * @param f0501  企业证照id
	 * @return
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/deleteQInfo", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> deleteQInfo(String param,String f0501){
		int id;
		ResponseResult<String> rr;
		if(param == null || "".equals(param)){
			id = Integer.parseInt(f0501);
		}else{
			T05 t = (T05)JSON.parse(param);
			id = t.getF0501();
		}
		if(qService.deleteQinfo(id) >= 1){
			rr = new ResponseResult<>(true, "更新成功");
		}else{
			rr = new ResponseResult<>(false, "更新失败");
		}
		
		return rr;
	}

	/**
	 * @功能描述: 添加企业证照的接口,返回一个json对象，json.success 是一个bool值，表示添加证照的状态，true表示成功,false表示失败,json.error 更新失败的提示信息，json.data 更新成功的提示信息
	 * @提交方式: post
	 * @时间:2017年5月19日 下午10:15:03
	 * @方法名: addQinfo 
	 * @接口地址: 手机端：http://120.76.145.236:8090/WH/Qualification/addQinfo?param=
	 * 			PC端：http://120.76.145.236:8090/WH/Qualification/addQinfo?f0101=101000002&f0502=税务登记证ss&f0503=23213asdas4343&f0504=2019-11-18&f0505=否
	 * @测试数据:
	 * @param param 手机端传入的json对象参数
	 * @param f0101 所属企业ID
	 * @param f0501  企业资质id(如果是添加新的证照就不需要这个字段)
	 * @param f0502 证照名称
	 * @param f0503 证照编号
	 * @param f0504 有效日期
	 * @param f0505 证照是否有效:(硬）有效、过期、无效
	 * @return
	 * @return ResponseResult<String>
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/addQinfo", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> addQinfo(String param,String f0101,String f0501,String f0502,String f0503,String f0504,String f0505) throws NumberFormatException, ParseException{
		ResponseResult<String> rr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		T05 t;
		if(param == null || "".equals(param)){
			t = new T05(null, Integer.parseInt(f0101), f0502, f0503, sdf.parse(f0504), f0505);
		}else{
			t = (T05)JSON.parse(param);
		}

		if(qService.addQinfo(t).getF0501() != null){
			rr = new ResponseResult<>(true, "添加成功");
		}else{
			rr = new ResponseResult<>(false, "添加失败");
		}
		
		return rr;
	}

}
