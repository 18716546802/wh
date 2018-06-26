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
import com.wh.model.T10;
import com.wh.model.T11;
import com.wh.service.DangerService;

@Controller
@RequestMapping("/danger")
public class DangerController {

	
	@Autowired
	private DangerService dangerService;
	/**
	 * 查询所有危险源信息
	 * 
	 * @return
	 * @throws ParseException
	 * 
	 * 
	 * 测试：http://120.76.145.236:8090/WH/danger/selectDangerlist (对于参数，写的为必填，其他的为选填) 
	 * 
	 */
	@RequestMapping(value="/selectDangerlist", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T11>> selectDangers() throws ParseException{

		List<T11> t11list = dangerService.selectDangerlist();
		ResponseResult<List<T11>> rr;
		if (t11list==null) {
			rr = new ResponseResult<List<T11>>(false,"没有查询到");
		}else{
			rr = new ResponseResult<List<T11>>(true,t11list);
		}
		return rr;
	}
	
	/**
	 * 查询一个危险源信息
	 * 
	 * @param f1101 危险源ID
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://120.76.145.236:8090/WH/danger/selectByPrimaryKey?f1101= (对于参数，写的为必填，其他的为选填) 
	 */
	@RequestMapping(value="/selectByPrimaryKey", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T11> selectDanger(String f1101) throws ParseException{

		T11 t11 = dangerService.selectByPrimaryKey(Integer.parseInt(f1101));
		ResponseResult<T11> rr;
		if (t11==null) {
			rr = new ResponseResult<T11>(false,"没有查询到");
		} else {
			rr = new ResponseResult<T11>(true,t11);
		}
		
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: 根据企业id查询当前企业下的危险源,会返回一个json对象，如果成功json.success的值为true,并且返回该企业下的所有危险信息;反之为false,并且会有查询失败的信息
	 * @提交方式: 
	 * @时间:2017年5月22日 下午12:25:01
	 * @方法名: findAllDangerCom 
	 * @接口地址:http://120.76.145.236:8090/WH/danger/findAllDangerCom?f0101=101000001
	 * @测试数据:
	 * @param f0101
	 * @return
	 * @return ResponseResult<List<T11>>
	 */
	@RequestMapping(value="/findAllDangerCom", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T11>> findAllDangerCom(String f0101){
		List<T11> t11list = dangerService.findAllDangerByCom(Integer.parseInt(f0101));
		ResponseResult<List<T11>> rr;
		if (t11list==null) {
			rr = new ResponseResult<List<T11>>(false,"没有查询到");
		}else{
			rr = new ResponseResult<List<T11>>(true,t11list);
		}
		return rr;
	}

	/**
	 * 
	 * 根据危险源ID修改危险源信息
	 * 
	 * @param f1101   危险源ID
	 * @param f0101   企业编号
	 * @param f1102   重大危险源名称 
	 * @param f1103   重大危险源所在地址 
	 * @param f1104   重大危险源投用时间 
	 * @param f1105   重大危险源编号 
	 * @param f1106   规模：单元内主要装置，设施及生产（储存）规模
	 * @param f1107   是否位于化工（工业）园区（硬）：是，否 
	 * @param f1108   工业园区名称 
	 * @param f1109   危险源与周边重点防护目标最近距离靖况描述（m)  
	 * @param f1110   厂区边界外500m范围内人数估算值 
	 * @param f1112   主要监控措施 
	 * @param f1113   近三年内危险化学品事故情况
	 * @param f1114   备案编号 
	 * @param f1115   负责人 
	 * @param f1116   由高到低分别为一、二、三、四级 
	 * @param f1117   是否在政府已备案（硬），已备案，未备案（default）
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/danger/updateDanger?f1101=&f0101=&f1104=  (对于参数，写的为必填，其他的为选填) 
	 */
	@RequestMapping(value="/updateDanger", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> updateDanger(String f1101,String f0101,String f1102,String f1103,String f1104,
			String f1105,String f1106,String f1107,String f1108,String f1109,String f1110,String f1112,String f1113,
			String f1114,String f1115,String f1116,String f1117) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		T11 t11 = new T11(Integer.parseInt(f1101), Integer.parseInt(f0101), f1102, f1103,sdf.parse(f1104), f1105, f1106, f1107, f1108, f1109, f1110, f1112, f1113, f1114, f1115, f1116, f1117);
		int l = dangerService.updateByPrimaryKeySelective(t11);
		ResponseResult<String> rr;
		if (l>=1) {
			rr = new ResponseResult<>(true,"修改成功");
		}else {
			rr = new ResponseResult<>(false,"修改失败");
		}
		
		return rr;
	}
	

	/**
	 * 添加危险源信息
	 * 
	 * @param f1101   危险源ID
	 * @param f0101   企业编号
	 * @param f1102   重大危险源名称 
	 * @param f1103   重大危险源所在地址 
	 * @param f1104   重大危险源投用时间 
	 * @param f1105   重大危险源编号 
	 * @param f1106   规模：单元内主要装置，设施及生产（储存）规模
	 * @param f1107   是否位于化工（工业）园区（硬）：是，否 
	 * @param f1108   工业园区名称 
	 * @param f1109   危险源与周边重点防护目标最近距离靖况描述（m)  
	 * @param f1110   厂区边界外500m范围内人数估算值 
	 * @param f1112   主要监控措施 
	 * @param f1113   近三年内危险化学品事故情况
	 * @param f1114   备案编号 
	 * @param f1115   负责人 
	 * @param f1116   由高到低分别为一、二、三、四级 
	 * @param f1117   是否在政府已备案（硬），已备案，未备案（default）
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/danger/addDanger (对于参数，写的为必填，其他的为选填)
	 */
	@RequestMapping(value="/addDanger", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T11> addADanger(String f1101,String f0101,String f1102,String f1103,String f1104,
			String f1105,String f1106,String f1107,String f1108,String f1109,String f1110,String f1112,String f1113,
			String f1114,String f1115,String f1116,String f1117) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		T11 t11 = new T11(null, Integer.parseInt(f0101), f1102, f1103,sdf.parse(f1104), f1105, f1106, f1107, f1108, f1109, f1110, f1112, f1113, f1114, f1115, f1116, f1117);
		
		ResponseResult<T11> rr;
		t11 = dangerService.insertT11(t11);
		if (t11.getF1101() == null) {
			rr = new ResponseResult<T11>(false,"添加失败");
		} else {
			rr = new ResponseResult<T11>(true,"添加成功");
		}
			
		return rr;
	}

	/**
	 * 删除危险源信息
	 * 
	 * @param f1101    危险源ID
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/danger/deleteDanger?f1101=101000006  (对于参数，写的为必填，其他的为选填)
	 */
	@RequestMapping(value="/deleteDanger", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> deleteADanger(String f1101) throws ParseException{
		
		
		int l = dangerService.deleteByPrimaryKey(Integer.parseInt(f1101));
		ResponseResult<String> rr;
		if (l>=1) {
			rr = new ResponseResult<>(true,"删除成功");
		}else {
			rr = new ResponseResult<>(false,"删除失败");
		}
		return rr;
	}
	
	
	
	/**
	 * 查询所有提醒业务信息
	 * 
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/danger/selectAllRemind    (对于参数，写的为必填，其他的为选填)
	 */
	@RequestMapping(value="/selectAllRemind", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T10>> selectAllRemind() throws ParseException{

		List<T10> t10list = dangerService.selectAllRemind();
		ResponseResult<List<T10>> rr;
		if (t10list==null) {
			rr = new ResponseResult<List<T10>>(false,"没有查询到");
		} else {
			rr = new ResponseResult<List<T10>>(true,t10list);
		}
		return rr;
	}
	
	/**
	 * 查询一个提醒业务信息
	 * 
	 * @param f1001  提醒业务ID
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/danger/selectRemindByPrimaryKey?f1001=110000003   (对于参数，写的为必填，其他的为选填)
	 */
	@RequestMapping(value="/selectRemindByPrimaryKey", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T10> selectARemind(String f1001) throws ParseException{

		T10 t10 = dangerService.selectRemindByPrimaryKey(Integer.parseInt(f1001));
		ResponseResult<T10> rr;
		if (t10==null) {
			rr= new ResponseResult<T10>(false,"没有查询到");
		}else{
			rr = new ResponseResult<T10>(true,t10);
		}
		
	
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: 根据企业id查询当前企业下的提醒管理业务,会返回一个json对象，如果成功json.success的值为true,并且返回该企业下的所有管理信息
	 * @提交方式: GET
	 * @时间:2017年5月22日 下午12:06:24
	 * @方法名: findAllRemindByCom 
	 * @接口地址:http://120.76.145.236:8090/WH/danger/findAllRemindCom?f0101=101000001
	 * @测试数据:
	 * @param f0101
	 * @return
	 * @return ResponseResult<List<T10>>
	 */
	@RequestMapping(value="/findAllRemindCom", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T10>> findAllRemindCom(String f0101){
		ResponseResult<List<T10>> rr;
		List<T10> list = dangerService.findAllRemindByCom(Integer.parseInt(f0101));
		if(list.isEmpty()){
			rr= new ResponseResult<>(false,"没有查询到");
		}else{
			rr= new ResponseResult<>(true,list);
		}
		
		return rr;
	}
	
	
	/**
	 * 
	 * @功能描述: 修改提醒业务信息   ,返回一个json对象，json.success 是一个bool值，表示更新成功或者失败,json.error 更新失败的提示信息，json.data 更新成功的提示信息
	 * @提交方式: GET
	 * @时间:2017年5月20日 下午11:59:18
	 * @方法名: updateRemind 
	 * @接口地址:http://120.76.145.236:8090/WH/danger/updateRemind?f1001=110000003&f0101=101000001&f1002=设备检测&f1003=2015-10-10&f1004=已确认&f1005=张三;李四&f1006=电机设备损坏了&f1007=54551&f1008=未处理&f1009=2015-06-10&f1010=2015-06-10
	 * @测试数据:
	 * @param param 手机端传入的json数据
	 * @param f1001   提醒信息ID
	 * @param f0101   企业编号：给该企业的提醒 
	 * @param f1002   类型（硬）：提醒类型：设备检测、企业资质、人员资质、重点时段、隐患整改 
	 * @param f1003   提醒发出时间，是指的系统自动计算出的提醒时间 
	 * @param f1004   是否确认（硬）：已确认、未确认：当企业有任何人已经得到该信息，都表示已经确认。 
	 * @param f1005   企业确认人员：多人用分号隔开。当用户登陆系统时，如果该用户还示确认，把该人员名字加上。
	 * @param f1006   提醒内容，把相关的提醒组成字符文本，放入这个字段。 
	 * @param f1007   该提醒关联的业务编号；这个编号根据提醒内容的不同，关联不同的表的主键F0X01 
	 * @param f1008   该提醒是事已经处理（硬）：未处理、已处理：这里当对应的提醒已经处理，就自动把该状态改为：已理处 
	 * @param f1009   提醒的业务处理时间 
	 * @param f1010   最早得到该提醒的时间，即该企业最早得到该提醒的那个的时间。 
	 * @return
	 * @throws ParseException
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/updateRemind", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> updateRemind(String param,String f1001,String f0101,String f1002,String f1003,String f1004,
			String f1005,String f1006,String f1007,String f1008,String f1009,String f1010) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		T10 t10 = new T10(Integer.parseInt(f1001), Integer.parseInt(f0101), f1002, sdf.parse(f1003), f1004, f1005, f1006,Integer.parseInt(f1007), f1008, sdf.parse(f1009), sdf.parse(f1010));
		//T11 t11 = new T11(Integer.parseInt(f1001), Integer.parseInt(f0101), f1102, f1103,sdf.parse(f1104), f1105, f1106, f1107, f1108, f1109, f1110, f1112, f1113, f1114, f1115, f1116, f1117);
		int l = dangerService.updateRemindByPrimaryKeySelective(t10);
		ResponseResult<String> rr;
		if (l>=1) {
			rr = new ResponseResult<>(true,"修改成功");
		}else {
			rr = new ResponseResult<>(false,"修改失败");
		}
		
		return rr;
	}
	

	/**
	 * 
	 * @功能描述: 添加提醒业务信息
	 * @提交方式: GET
	 * @时间:2017年5月21日 上午12:17:48
	 * @方法名: addRemind 
	 * @接口地址:http://120.76.145.236:8090/WH/danger/addRemind?f0101=&f1003=&f1007=&f1009=1&f1010=
	 * @测试数据:
	 * @param param 手机端穿过来的JSON数据
	 * @param f1001   提醒信息ID
	 * @param f0101   企业编号：给该企业的提醒 
	 * @param f1002   类型（硬）：提醒类型：设备检测、企业资质、人员资质、重点时段、隐患整改 
	 * @param f1003   提醒发出时间，是指的系统自动计算出的提醒时间 
	 * @param f1004   是否确认（硬）：已确认、未确认：当企业有任何人已经得到该信息，都表示已经确认。 
	 * @param f1005   企业确认人员：多人用分号隔开。当用户登陆系统时，如果该用户还示确认，把该人员名字加上。
	 * @param f1006   提醒内容，把相关的提醒组成字符文本，放入这个字段。 
	 * @param f1007   该提醒关联的业务编号；这个编号根据提醒内容的不同，关联不同的表的主键F0X01 
	 * @param f1008   该提醒是事已经处理（硬）：未处理、已处理：这里当对应的提醒已经处理，就自动把该状态改为：已理处 
	 * @param f1009   提醒的业务处理时间 
	 * @param f1010   最早得到该提醒的时间，即该企业最早得到该提醒的那个的时间。 
	 * @return
	 * @throws ParseException
	 * @return ResponseResult<T10>
	 */
	@RequestMapping(value="/addRemind", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T10> addRemind(String f1001,String f0101,String f1002,String f1003,String f1004,
			String f1005,String f1006,String f1007,String f1008,String f1009,String f1010) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		T10 t10 = new T10(null, Integer.parseInt(f0101), f1002, sdf.parse(f1003), f1004, f1005, f1006, Integer.parseInt(f1007), f1008, sdf.parse(f1009), sdf.parse(f1010));
		t10 = dangerService.insertT10(t10);
		ResponseResult<T10> rr;
		if (t10.getF1001() == null) {
			rr = new ResponseResult<T10>(false,"添加失败");
		} else {
			rr = new ResponseResult<T10>(true,t10);
		}
		return rr;
	}
	
	/**
	 * 删除提醒业务信息
	 * 
	 * @param f1001   提醒信息ID
	 * @return
	 * @throws ParseException
	 * 
	 * 本地测试：http://localhost:8080/WH/danger/deleteRemindById?f1001=
	 */
	@RequestMapping(value="/deleteRemindById", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> deleteRemind(String f1001) throws ParseException{
		
		
		int l = dangerService.deleteT10ByPrimaryKey(Integer.parseInt(f1001));
		ResponseResult<String> rr;
		if (l>=1) {
			rr = new ResponseResult<>(true,"删除成功");
		}else {
			rr = new ResponseResult<>(false,"删除失败");
		}
		return rr;
	}
	
	
}
