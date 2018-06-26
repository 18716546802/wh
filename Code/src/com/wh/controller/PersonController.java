package com.wh.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.dto.ResponseResult;
import com.wh.model.T07;
import com.wh.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	
	/**
	 * @功能描述: 查询所有人员资质
	 * @提交方式: GET
	 * @时间:2017年5月23日 上午1:18:54
	 * @方法名: selectAllQualifications 
	 * @接口地址:http://120.76.145.236:8090/WH/person/selectAllQualifications
	 * @测试数据:
	 * @return
	 * @return ResponseResult<List<T07>>
	 */
	@RequestMapping(value="/selectAllQualifications",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T07>> selectAllQualifications() {
		
		List<T07> t07list = personService.selectAllQualifications();
		ResponseResult<List<T07>> rr;
		if (t07list.size()==0) {
			rr = new ResponseResult<List<T07>>(false, "没有查询到");
		}else{
			rr = new ResponseResult<List<T07>>(true, t07list);
		}
		
		return rr;
	}


	/**
	 * @功能描述: 根据企业号ID查询企业的所有人员资质问题
	 * @提交方式: GET
	 * @时间:2017年5月23日 上午1:24:25
	 * @方法名: selectListQualificationsByEnterpriseId 
	 * @接口地址:http://localhost:8080/WH/person/selectListQualificationsByEnterpriseId?f0101=
	 * @测试数据:
	 * @param f0101 企业ID
	 * @return
	 * @return ResponseResult<List<T07>>
	 */
	@RequestMapping(value="/selectListQualificationsByEnterpriseId",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T07>> selectListQualificationsByEnterpriseId(String f0101) {
		
		List<T07> t07list = personService.selectListQualificationsByEnterpriseId(Integer.parseInt(f0101));
		ResponseResult<List<T07>> rr;
		if (t07list.size()==0) {
			rr = new ResponseResult<List<T07>>(false, "没有查询到");
		}else{
			rr = new ResponseResult<List<T07>>(true, t07list);
		}
		
		return rr;
	}
	
	/**
		 * @功能描述: 根据人员资质ID查询用户资质
		 * @提交方式: GET
		 * @时间:2017年5月23日 上午10:33:34
		 * @方法名: selectQualificationsByID 
		 * @接口地址:http://120.76.145.236:8090/WH/person/selectQualificationsByID?f0701=
		 * @本地测试地址:http://localhost:8080/WH/person/selectQualificationsByID?f0701=
		 * @测试数据:
		 * @param f0701
		 * @return
		 * @return ResponseResult<T07>
	 */
	@RequestMapping(value="/selectQualificationsByID",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T07> selectQualificationsByID(String f0701) {
		
		T07 t07 = personService.selectQualificationsByID(Integer.parseInt(f0701));
		ResponseResult<T07> rr;
		if (t07 == null) {
			rr = new ResponseResult<T07>(false, "没有查询到");
		}else{
			rr = new ResponseResult<T07>(true, t07);
		}
		
		return rr;
	}
	
	/**
		 * @功能描述: 根据人员资质ID更改人员资质信息
		 * @提交方式: POST
		 * @时间:2017年5月23日 上午10:52:05
		 * @方法名: updataQualificationsByID 
		 * @接口地址:http://120.76.145.236:8090/WH/person/updataQualificationsByID?f0701=&f0101=&f0702=&f0703=&f0704=&f0705=&f0706=&f0707=&f0708= 
		 * @本地测试地址:http://localhost:8080/WH/person/updataQualificationsByID?f0701=10700000&f0101=101000001&f0704=2017-05-14
		 * @测试数据:
		 * @param f0701  人员资质ID
		 * @param f0101  企业编号：该人员在该企业工作（或是证书在该企业） 
		 * @param f0702  姓名：持证人员的姓名 
		 * @param f0703  证书编号 
		 * @param f0704  有效时间（过期时间，年审时间） 
		 * @param f0705  颁发单位：发证书的单位 
		 * @param f0706  状态：（硬）:有效，过期 
		 * @param f0707  证件名称 
		 * @param f0708  资格证书类型 
		 * @return
		 * @throws ParseException
		 * @return ResponseResult<T07>
	 */
	@RequestMapping(value="/updataQualificationsByID",method=RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T07> updataQualificationsByID(String f0701,String f0101,String f0702,
			String f0703,String f0704,String f0705,String f0706,String f0707,
			String f0708) throws ParseException{
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ResponseResult<T07> rr;
		
		T07 t07 = new T07(Integer.parseInt(f0701), Integer.parseInt(f0101), f0702, f0703, sdf.parse(f0704), f0705, f0706, f0707, f0708);
		
		int l = personService.updataQualificationsByID(t07);
		if (l>=1) {
			rr = new ResponseResult<>(true, "修改成功");
		}else{
			rr = new ResponseResult<>(false, "修改失败");
		}
		
		return rr;
	}
	
	/**
		 * @功能描述: 根据人员资质ID删除人员资质信息
		 * @提交方式: 
		 * @时间:2017年5月23日 上午11:42:39
		 * @方法名: deleteQualificationsByID 
		 * @接口地址:http://120.76.145.236:8090/WH/person/deleteQualificationsByID?f0701=
		 * @本地测试地址:http://localhost:8080/WH/person/deleteQualificationsByID?f0701=
		 * @测试数据:
		 * @param f0701  人员资质ID
		 * @return
		 * @return ResponseResult<String>
	 */
	@RequestMapping(value="deleteQualificationsByID",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> deleteQualificationsByID(String f0701) {
		
		
		ResponseResult<String> rr;
		int l = personService.deleteQualificationsByID(Integer.parseInt(f0701));
		if (l>=1) {
			
			rr = new ResponseResult<>(true, "删除成功");
		}else{
			rr = new ResponseResult<>(false, "删除失败");
		}
		return rr;	
	}
	
	/**
		 * @功能描述: 添加人员资质信息
		 * @提交方式: 
		 * @时间:2017年5月23日 上午11:53:30
		 * @方法名: addpersonQualification 
		 * @接口地址:http://120.76.145.236:8090/WH/person/addpersonQualification?f0101=&f0704=
		 * @本地测试地址:http://localhost:8080/WH/person/addpersonQualification?f0101=&f0704=
		 * @测试数据:
		 * @param f0701  人员资质ID
		 * @param f0101  企业编号：该人员在该企业工作（或是证书在该企业） 
		 * @param f0702  姓名：持证人员的姓名 
		 * @param f0703  证书编号 
		 * @param f0704  有效时间（过期时间，年审时间） 
		 * @param f0705  颁发单位：发证书的单位 
		 * @param f0706  状态：（硬）:有效，过期 
		 * @param f0707  证件名称 
		 * @param f0708  资格证书类型 
		 * @return
		 * @throws ParseException
		 * @return ResponseResult<T07>
	 */
	@RequestMapping(value="addpersonQualification",method=RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T07> addpersonQualification(String f0701,String f0101,String f0702,
			String f0703,String f0704,String f0705,String f0706,String f0707,
			String f0708) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ResponseResult<T07> rr;
		T07 t07 = new T07(null, Integer.parseInt(f0101), f0702, f0703, sdf.parse(f0704), f0705, f0706, f0707, f0708);
		t07 = personService.addpersonQualification(t07);
		if (t07.getF0101()==null) {
			rr = new ResponseResult<T07>(false, "添加失败");
		}else{
			rr = new ResponseResult<T07>(true, t07);
		}
		return rr;
	}
	
}
