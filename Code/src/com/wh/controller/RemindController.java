package com.wh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.wh.dto.ResponseResult;
import com.wh.model.T06;
import com.wh.model.T08;
import com.wh.model.T09;
import com.wh.model.T10;
import com.wh.model.T50;
import com.wh.service.JobService;
import com.wh.service.LowCardService;
import com.wh.service.TreeService;

@Controller
@RequestMapping("/remind")
public class RemindController implements ServletConfigAware,ServletContextAware{
	
	@Autowired
	JobService jobService;
	@Autowired 
	LowCardService lowService;
	@Autowired 
	TreeService treeService;
	
	
	
	private ServletConfig config;
	private ServletContext context;
	

	
	/**
	 * @功能描述: 获取菜单树
	 * @提交方式: get
	 * @时间:2017年5月31日 上午12:19:39
	 * @方法名: getTree 
	 * @接口地址: 
	 * 		一级菜单树
	 * 		http://120.76.145.236:8090/WH/remind/getTree
	 * @测试数据:
	 * @return
	 * @return ResponseResult<List<T06>>
	 */
	@RequestMapping(value="/getTree", method=RequestMethod.GET,produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T06>> getTree(){
		List<T06> list = treeService.getListT06();
		ResponseResult<List<T06>> rr;
		
		if(list.isEmpty()){
			rr = new ResponseResult<List<T06>>(true, "暂无数据");
		}else{
			rr = new ResponseResult<List<T06>>(false, list);
		}
		return rr;
	}
	
	
	
	/**
	 * 
	 * @功能描述: 添加菜单树
	 * @提交方式: POST
	 * @时间:2017年5月31日 上午12:19:39
	 * @方法名: add1LTree 
	 * @接口地址: 
	 * 		一级菜单树
	 * 		http://120.76.145.236:8090/WH/remind/addTree?f0602=&type=1
	 * 		二级菜单树
	 * 		http://120.76.145.236:8090/WH/remind/addTree?f0603=&f0602=&type=2
	 * @测试数据:
	 * @param f0603 添加二级菜单树时才有的数据，是该二级菜单的上一级的id
	 * @param f0602 地区名字
	 * @param type  表示添加一级菜单还是二级菜单
	 * @return
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/addTree", method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> addTree(String f0603,String f0602,String type){
		ResponseResult<String> rr;
		//添加一级菜单
		if(type.equals("1")){
			T06 t06 = new T06();
			t06.setF0602(f0602);
			t06.setF0603(0);
			t06.setF0604(1);
			if(treeService.insertTree(t06) >= 1){
				rr = new ResponseResult<>(true, "添加成功");
			}else{
				rr = new ResponseResult<>(false, "添加失败");
			}
		}else{
			//添加二级菜单
			T06 t06 = new T06();
			t06.setF0602(f0602);
			t06.setF0603(Integer.parseInt(f0603));
			t06.setF0604(1);
			if(treeService.insertTree(t06) >= 1){
				rr = new ResponseResult<>(true, "添加成功");
			}else{
				rr = new ResponseResult<>(false, "添加失败");
			}
		}
		return rr;
	}

	
	/**
	 * 
	 * @功能描述: 更新菜单树
	 * @提交方式: 
	 * @时间:2017年5月31日 上午12:48:13
	 * @方法名: updateTree 
	 * @接口地址: 
	 * http://120.76.145.236:8090/WH/remind/updateTree?f0601=&f0602=&f0603=
	 * @测试数据:
	 * @param f0601 当前修改的地区的ID
	 * @param f0602 当前修改的地区的名称
	 * @param f0603 父节点ID,如果是一级菜单则该值为0
	 * @return
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/updateTree", method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> updateTree(String f0601,String f0602,String f0603){
		ResponseResult<String> rr;
		T06 t06 = new T06();
		t06.setF0601(Integer.parseInt(f0601));
		t06.setF0602(f0602);
		t06.setF0603(Integer.parseInt(f0603));
		t06.setF0604(1);
		
		if(treeService.updateTree(t06) >= 1){
			rr = new ResponseResult<>(true, "修改成功");
		}else{
			rr = new ResponseResult<>(false, "修改失败");
		}
		return rr;
	} 
	
	/**
	 * @功能描述: 删除菜单树
	 * @提交方式: POST
	 * @时间:2017年5月31日 上午1:09:30
	 * @方法名: deleteTree 
	 * @接口地址:
	 * 		http://120.76.145.236:8090/WH/remind/deleteTree?f0601=
	 * @测试数据:
	 * @param f0601
	 * @return
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/deleteTree", method=RequestMethod.POST,produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> deleteTree(String f0601){
		ResponseResult<String> rr;
		if(treeService.deleteTree(Integer.parseInt(f0601)) >=1){
			rr = new ResponseResult<>(true, "删除成功");
		}else{
			rr = new ResponseResult<>(false, "删除失败");
		}
		return rr;
	}
	
	
	/**
	 * @功能描述: 根据企业ID，和提醒类型查询
	 * @提交方式: 
	 * @时间:2017年5月29日 下午10:08:44
	 * @方法名: getT10ListByComType 
	 * @接口地址:
	 * 	http://120.76.145.236:8090/WH/remind/findRemindByComType?f0101=&f1002=&name=
	 * @测试数据:
	 * @param f0101 企业id
	 * @param f1002 提醒类别
	 * @return
	 * @return ResponseResult<List<T10>>
	 */
	@RequestMapping(value="/findRemindByComType", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T10>> getT10ListByComType(String f0101,String f1002,String name){
		ResponseResult<List<T10>> rr;
		List<T10> list = jobService.selectT10ByComType(Integer.parseInt(f0101), f1002,name);
		
		
		if(list.isEmpty()){
			rr = new ResponseResult<List<T10>>(false, "暂无数据");
		}else{
			rr = new ResponseResult<List<T10>>(true, list);
		}
		
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: TODO
	 * @提交方式: 
	 * @时间:2017年5月29日 下午10:10:13
	 * @方法名: findPurkingPerilByComType 
	 * @接口地址: 
	 * 		http://120.76.145.236:8090/WH/remind/findPurkingPerilByComType?f0101=&f0828=
	 * @测试数据:
	 * @param f0101 企业ID
	 * @param f0828 隐患类别
	 * @return
	 * @return ResponseResult<List<T08>>
	 */
	@RequestMapping(value="/findPurkingPerilByComType", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T08>> findPurkingPerilByComType(String f0101,String f0828){
		ResponseResult<List<T08>> rr;
		List<T08> list = jobService.selectT08ByComType(Integer.parseInt(f0101), f0828);
		if(list.isEmpty()){
			rr = new ResponseResult<List<T08>>(false, "暂无数据");
		}else{
			rr = new ResponseResult<List<T08>>(true, list);
		}
		
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: 根据隐患ID 查看 隐患详情
	 * @提交方式: GET
	 * @时间:2017年5月30日 下午10:40:05
	 * @方法名: findOnePurkingPeril 
	 * @接口地址: http://120.76.145.236:8090/WH/remind/findOnePurkingPeril?f0801=
	 * @测试数据:
	 * @param f0801
	 * @return
	 * @return ResponseResult<T08>
	 */
	@RequestMapping(value="/findOnePurkingPeril", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T08> findOnePurkingPeril(String f0801){
		ResponseResult<T08> rr;
		T08 t08 = jobService.slelectT08(Integer.parseInt(f0801));
		
		if(t08 !=  null){
			rr = new ResponseResult<>(true, t08);
		}else{
			rr = new ResponseResult<>(false, "未获取到数据"); 
		}
		
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: PC端添加今日参数
	 * @提交方式: 
	 * @时间:2017年5月30日 下午10:44:37
	 * @方法名: addTodayP 
	 * @接口地址:http://120.76.145.236:8090/WH/remind/addTodayP?f0201=&f5003=&f5004=&f5005=
	 * @测试数据:
	 * @param f0201  当前登录的员工ID
	 * @param f5003  默认值，万汇业务员
	 * @param f5004 每日参检的专家;政府;万汇人员
	 * @param f5005 这个参数不需要
	 * @return
	 * @return ResponseResult<String>
	 */
	@RequestMapping(value="/addTodayP", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> addTodayP(String f0201,String f5003,String f5004,String f5005){
		ResponseResult<String> rr;
		T50 t = new T50();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		t.setF5002(f0201+"+"+sdf.format(new Date()));
		f5003="万汇业务员";
		t.setF5003(f5003);
		System.err.println(f5004);
		t.setF5004(f5004);
		
		if(jobService.isHaveT50(t.getF5002()) == null){
			t = jobService.insertT50(t);
		}else{
			jobService.deleteT50(t.getF5002());
			t = jobService.insertT50(t);
		}
		
		rr = new ResponseResult<>(true, "添加成功");
		return rr;
	}

	@Override
	public void setServletConfig(ServletConfig arg0) {
		config = arg0;		
	}


	@Override
	public void setServletContext(ServletContext arg0) {
		context = arg0;
	}   
}
