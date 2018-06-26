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
import com.wh.model.T03;
import com.wh.model.T11;
import com.wh.service.JobServiceDs;

/***
 * 该控制层用于处理一部分业务操作，比如重大危险源管理，特种设备管理
 *@author DENG
 *
 */
@Controller
@RequestMapping("/jobDs")
public class JobsControllerDs {
	@Autowired
	private JobServiceDs jobServiceds;	
	
	/**
	 * 查询出所有的特种设备
	 * 访问地址为：http://120.76.145.236:8090/WH/jobDs/selectDsT03
	 * @return
	 */
	@RequestMapping(value="/selectDsT03", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T03>> selectT03(){
		List<T03> t = jobServiceds.selectT03();
		
		return new ResponseResult<List<T03>>(true, t);
	}
	
	
	/**
	 * 访问地址为：http://120.76.145.236:8090/WH/jobDs/insertDsT03
	 * 该方法用于手机端和PC端的添加新特种设备,
	 * 手机端需提交的参数见UI设计页
	 * @param f0101
	 * @param f0301
	 * @param f0302
	 * @param f0303
	 * @param f0304
	 * @param f0305
	 * @param f0306
	 * @param f0307
	 * @param f0308
	 * @param f0309
	 * @param f0310
	 * @param f0311
	 * @param f0312
	 * @param f0313
	 * @param f0314
	 * @param f0315
	 * @return
	 */
	@RequestMapping(value="/insertDsT03", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T03> insertT03(String f0101,String f0301,String f0302,String f0303,String f0304,String f0305,String f0306,String f0307,String f0308,String f0309,String f0310,String f0311,
			String f0312,String f0313,String f0314,String f0315) throws NumberFormatException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		T03 t = new T03(Integer.parseInt(f0101), Integer.parseInt(f0301), f0302, f0303, f0304, sdf.parse(f0305),f0306,
				f0307, f0308, f0309,  sdf.parse(f0310), f0311, f0312,  f0313,f0314,f0315);
		ResponseResult<T03> rr;
		t = jobServiceds.insertT03(t);
		rr = new ResponseResult<>(true, t);
		
		return rr;
	}
	
	/**
	 * 访问地址为：http://120.76.145.236:8090/WH/jobDs/updateDsT03
	 * 该方法用于手机端或者PC端的修改特种设备.
	 * @param f0101
	 * @param f0301
	 * @param f0302
	 * @param f0303
	 * @param f0304
	 * @param f0305
	 * @param f0306
	 * @param f0307
	 * @param f0308
	 * @param f0309
	 * @param f0310
	 * @param f0311
	 * @param f0312
	 * @param f0313
	 * @param f0314
	 * @param f0315
	 * @return
	 */
	@RequestMapping(value="/updateDsT03", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T03> updateT03(String f0101,String f0301,String f0302,String f0303,String f0304,String f0305,String f0306,String f0307,String f0308,String f0309,String f0310,String f0311,
			String f0312,String f0313,String f0314,String f0315) throws NumberFormatException, ParseException{
		ResponseResult<T03> rr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		T03 t = new T03(Integer.parseInt(f0101), Integer.parseInt(f0301), f0302, f0303, f0304, sdf.parse(f0305),f0306,
				f0307, f0308, f0309,  sdf.parse(f0310), f0311, f0312,  f0313,f0314,f0315);
		int i = jobServiceds.updateT03(t);
		
		if(i>=1){
			rr = new ResponseResult<T03>(true, t);
		}else{
			rr = new ResponseResult<T03>(false,"更新失败" );
		}
		
		return rr;
	}
	
	/**
	 * 访问地址为：http://120.76.145.236:8090/WH/jobDs/deleteDsT03
	 * 删除特种设备，根据输入的ID来确定删除哪一个特种设备的信息.
	 * @return
	 */
	@RequestMapping(value="/deleteDsT03", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T03>> deleteT03(Integer f0101){
		int t = jobServiceds.deleteT03(f0101);
		String l=String.valueOf(t);
		return new ResponseResult<List<T03>>(true, l);
	}
	
	
	/**
	 * 
	 * 访问地址为：http://120.76.145.236:8090/WH/jobDs/selectDsT11
	 * 查询所有的重大危险源
	 * @return
	 */
	@RequestMapping(value="/selectDsT11", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T11>> selectT11(){
		List<T11> t = jobServiceds.selectT11() ;
		
		return new ResponseResult<List<T11>>(true, t);
	}
	
	
	/**
	 * 访问地址为：http://120.76.145.236:8090/WH/jobDs/insertDsT11
	 * 该方法用于手机端,PC端的添加新的重大危险源,
	 * 手机端需提交的参数见UI设计页
	 * @param f0101
	 * @param f1101
	 * @param f1102
	 * @param f1103
	 * @param f1104
	 * @param f1105
	 * @param f1106
	 * @param f1107
	 * @param f1108
	 * @param f1109
	 * @param f1110
	 * @param f1112
	 * @param f1113
	 * @param f1114
	 * @param f1115
	 * @param f1116
	 * @param f1117
	 * @return
	 */
	@RequestMapping(value="/insertDsT11", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T11> insertT11(String f0101, String f1101, String f1102,String f1103, String f1104,String f1105,String f1106,
			String f1107,String f1108,String f1109,String f1110,String f1112,String f1113,String f1114,String f1115,String f1116,
			String f1117)throws NumberFormatException,ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		T11 t = new T11(Integer.parseInt(f0101),Integer.parseInt(f1101),f1102,f1103,sdf.parse(f1104),f1105,f1106,f1107,
				f1108,f1109,f1110,f1112,f1113,f1114,f1115,f1116,f1117);
		ResponseResult<T11> rr;
		jobServiceds.insertT11(t);
		rr = new ResponseResult<>(true, t);
		return rr;
	}
	
	/**
	 * 访问地址为：http://120.76.145.236:8090/WH/jobDs/updateDsT11
	 * 该方法用于手机端或者PC端的修改重大危险源.
	 * @param f0101
	 * @param f1101
	 * @param f1102
	 * @param f1103
	 * @param f1104
	 * @param f1105
	 * @param f1106
	 * @param f1107
	 * @param f1108
	 * @param f1109
	 * @param f1110
	 * @param f1112
	 * @param f1113
	 * @param f1114
	 * @param f1115
	 * @param f1116
	 * @param f1117
	 * @return
	 */
	@RequestMapping(value="/updateDsT11", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T11> updateT11(String f0101,String f1101,String f1102,String f1103,String f1104,String f1105,String f1106,String f1107,String f1108,String f1109,String f1110,
			String f1112,String f1113,String f1114,String f1115,String f1116,String f1117) throws NumberFormatException, ParseException{
		ResponseResult<T11> rr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		T11 t = new T11(Integer.parseInt(f0101), Integer.parseInt(f1101), f1102, f1103, sdf.parse(f1104), f1105,f1106,
				f1107, f1108, f1109,  f1110, f1112,  f1113,f1114,f1115,f1116,f1117);
		int i = jobServiceds.updateT11(t);
		
		if(i>=1){
			rr = new ResponseResult<T11>(true, t);
		}else{
			rr = new ResponseResult<T11>(false,"更新失败" );
		}
		
		return rr;
	}
}
