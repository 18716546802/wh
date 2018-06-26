package com.wh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.dto.ResponseResult;
import com.wh.model.T01;
import com.wh.model.T03;
import com.wh.model.T07;
import com.wh.model.T08;
import com.wh.model.T10;
import com.wh.model.T11;
import com.wh.service.DangerService;
import com.wh.service.JobService;
import com.wh.service.PersonService;

@Controller
@RequestMapping("/government")
public class GovernmentAppController {
	@Autowired
	private JobService jobService;
	@Autowired
	private DangerService dangerService;
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/getBigDanger")
	public String getBigDanger(HttpServletRequest req,String f0601){
		req.getSession().setAttribute("F0601", f0601);
		return "zfBigDanger";
	}
	/**
	 * @功能描述: 安卓政府端显示企业重大危险源的页面
	 */
	
	@RequestMapping(value="/bigdanger", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public List<T11> getBigDanger(String f0601,String f0102,HttpServletRequest req,HttpServletResponse res){
		
		List<T11> t11List = new ArrayList<>();
		//如果f0102没有值
		if(null == f0102 || "".equals(f0102)){
			//先获取到该地区下的所有企业
			List<T01> list = jobService.findEnterpriseByArea(Integer.parseInt(f0601));
			for(T01 t : list){
				List<T11> listT = dangerService.findAllDangerByCom(t.getF0101());
				for(T11 t11:listT){
					t11List.add(t11);
				}
			}
		}else{
			//获取到和这个名称有关的企业列表
			List<T01> list = jobService.findEnterprisebyName(f0102);
			//做循环查询
			for(int i=0;i<list.size();i++){
				if(f0601.equals(list.get(i).getF0601()+"")){
					List<T11> ti1List = dangerService.findAllDangerByCom(list.get(i).getF0101());
					for(T11 t11 : ti1List){
						t11List.add(t11);
					}
				}
			}
			
		}
		
		return t11List;
	}

	
	
	
	@RequestMapping("/gethiddenDanger")
	public String getHiddenDanger(HttpServletRequest req,String f0601){
		req.getSession().setAttribute("F0601", f0601);
		return "zfHiddenDanger";
	}

	/**
	 * @功能描述: 安卓政府端显示企业安全隐患的页面
	 */
	@RequestMapping(value="/hiddenDanger", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public List<T08> getHiddenDanger(String f0601,String f0102,String type){
	List<T08> t08List = new ArrayList<>();	
		System.out.println("f0601"+"-----"+f0601);
		System.out.println("f0102"+"-----"+f0102);
		System.out.println("type"+"-----"+type);
		if((null == f0102 || "".equals(f0102)) && (null == type || "".equals(type))){
			//获取到这个地区下的所有企业
			List<T01> list = jobService.findEnterpriseByArea(Integer.parseInt(f0601));
			for(T01 t:list){
				List<T08> t08L = jobService.selectT08(t.getF0101());
				for(T08 t08:t08L){
					t08List.add(t08);
				}
			}
		}
		else if((null == f0102 || "".equals(f0102)) && (null != type || !"".equals(type))){
			//只传入了类型
			//List<T08> t08L = jobService.findT08ByType(type);
			//获取到这个地区下的所有企业
			List<T01> list = jobService.findEnterpriseByArea(Integer.parseInt(f0601));
			for(T01 t:list){
				List<T08> t08L = jobService.selectT08(t.getF0101());
				for(T08 t08:t08L){
					if(t08.getF0820() != null){
						if(t08.getF0820().equals(type)){
							t08List.add(t08);
						}
					}
				}
			}
		}else if((null != f0102 || !"".equals(f0102)) && (null == type || "".equals(type))){
			//只传入了公司名称
			//获取到和这个名称有关的企业列表
			List<T01> list = jobService.findEnterprisebyName(f0102);
			
			//做循环查询
			for(int i=0;i<list.size();i++){
				if(f0601.equals(list.get(i).getF0601()+"")){
					List<T08> to8List = jobService.selectT08(list.get(i).getF0101());
					for(T08 t08 : to8List){
						t08List.add(t08);
					}
				}
			}
		}
		else{
			//获取到和这个名称有关的企业列表
			List<T01> list = jobService.findEnterprisebyName(f0102);
			//获取到该类型下的所有隐患
			List<T08> to8List = jobService.findT08ByType(type);
			
			for(int i=0;i<list.size();i++){
				if(f0601.equals(list.get(i).getF0601()+"")){
					for(int j=0;j<to8List.size();j++){
						if(to8List.get(j).getF0101() - list.get(i).getF0101() == 0){
							t08List.add(to8List.get(j));
						}
					}
				}
			}
		}
		
		return t08List;
	}

	/**
	 * @功能描述: 安卓政府端显示人员资质的页面
	 */
	@RequestMapping("/getPersonalQualifical")
	public String getPersonalQualifical(HttpServletRequest req,String f0601){
		req.getSession().setAttribute("F0601", f0601);
		return "zfPersonalQualifications";
	}

	@RequestMapping(value="/personQualification", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public List<T07> getpersonQualification(String f0601,String f0102){
		List<T07> t07List = new ArrayList<>();
		
		if( null == f0102 || "".equals(f0102) ){
			//List<T01> list = jobService.findEnterprisebyName(f0102);
			List<T01> list = jobService.findEnterpriseByArea(Integer.parseInt(f0601));
			List<T07> list07 = personService.selectAllQualifications();
			for(T01 t01:list){
				for(T07 t07:list07){
					if(t07.getF0101() - t01.getF0101() == 0){
						t07List.add(t07);
					}
				}
			}
		}else{
			List<T01> list = jobService.findEnterprisebyName(f0102);
			
			for(int i=0;i<list.size();i++){
				if(f0601.equals(list.get(i).getF0601()+"")){
					List<T07> li = personService.selectListQualificationsByEnterpriseId(list.get(i).getF0101());
					for(T07 t07:li){
						t07List.add(t07);
					}
				}
			}
		}
		return t07List;
	}
	
	/**
	 * @功能描述: 安卓政府端显示特种设备
	 */
	@RequestMapping("/getSpecialEquipment")
	public String getSpecialEquipment(HttpServletRequest req,String f0601){
		req.getSession().setAttribute("F0601", f0601);
		return "zfSpecialEquipment";
	}

	@RequestMapping(value="/SpecialEquipment", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public List<T03> getSpecialEquipment(String f0601,String f0102){
		
		
		List<T03> t03List = new ArrayList<>();
		if(null == f0102 || "".equals(f0102)){
			List<T01> list = jobService.findEnterpriseByArea(Integer.parseInt(f0601));
			List<T03> list03 = jobService.findAllEquipment();
			
			for(T01 t01:list){
				for(T03 t03:list03){
					if(t03.getF0101() - t01.getF0101() == 0){
						t03List.add(t03);
					}
				}
			}
		}else{
			List<T01> list = jobService.findEnterprisebyName(f0102);
			
			for(int i=0;i<list.size();i++){
				if(f0601.equals(list.get(i).getF0601()+"")){
					List<T03> li = jobService.findAllEquipmentByCompany(list.get(i).getF0101());
					for(T03 t03:li){
						t03List.add(t03);
					}
				}
			}
		}
		
		
		
		return t03List;
	}

	
/*	public ResponseResult<List<T10>> getRemind(String f0601,String f0101,String f0201,String type){
		
	}*/
	 
}
