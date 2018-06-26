package com.wh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;

import com.wh.dto.ResponseResult;
import com.wh.model.T04;
import com.wh.model.T09;
import com.wh.service.LowCardService;
import com.wh.service.UserService;
import com.wh.util.FileUploadUtil;
import com.wh.util.Plupload;
import com.wh.util.Utils;

@Controller
@RequestMapping("/lowcard")
public class LowAndCheckCardController implements ServletConfigAware{
	
	private ServletConfig servletConfig;
	@Autowired
	private LowCardService lowCardService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 查询所有法律法规信息
	 * 
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/lowcard/findAllLows
	 */
	@RequestMapping(value="/findAllLows", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
 	public ResponseResult<List<T09>> selectALLlows() throws ParseException{

		List<T09> t09list = lowCardService.selectAllT09();
		ResponseResult<List<T09>> rr = new ResponseResult<List<T09>>(true,t09list);
		return rr;
	}
	/**
	 * 根据ID查询单个法律法规信息
	 * 
	 * @param f0901   法律法规文件ID
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/lowcard/findLowByPrimaryKey?f0901=
	 */
	@RequestMapping(value="/findLowByPrimaryKey", method=RequestMethod.GET,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T09> selectOneLow(String f0901) throws ParseException{

		T09 t09 = lowCardService.selectLowByPrimaryKey(Integer.parseInt(f0901));
		ResponseResult<T09> rr = new ResponseResult<T09>(true,t09);
		return rr;
	}
	/**
	 * 添加法律文件信息
	 * @param f0901   法律法规文件ID
	 * @param f0101   企业编号:如果F0907值为全局文件，则该值为空，表示该文件是所有企业必须访问的，如果F0907为企业文件，则该字段填写该文件所属的企业的ID，表示该文件是该企业的。
	 * @param f0902   文件名称 
	 * @param f0903   文件GUID，文件保存在系统Files文件夹下，全部名称为GUID命名
	 * @param f0904   文件类型：这里填写文件的后缀，如word文档，就填写 docx或doc 
	 * @param f0905   文件类级别（硬）：法律、行政法规、部门规章、地方法规和规章、文件公告、国家标准、行业标准 
	 * @param f0906   上传人员：这里指的是上传该文件的工作人员 
	 * @param f0907   文件访问级别（硬）：全局文件、企业文件;这里全局文件指是的，所有企业都可必须访问的文件；企业文件：指的是该文件只属于某个企业访问 
	 * @return
	 * @throws ParseException
	 * 测试：http://localhost:8080/WH/lowcard/addLow?f0101=
	 */
	@RequestMapping(value="/addLow", method=RequestMethod.POST,
			produces ="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T09> addOneLow(Plupload plupload,HttpServletRequest req,HttpServletResponse res,String f0901,String f0101,String f0902,String f0903,String f0904,
			String f0905,String f0906,String f0907) throws ParseException{
		ResponseResult<T09> rr;
		//设置上传参数
		plupload.setRequest(req); 
		//文件保存的绝对路径
		String path = plupload.getRequest().getSession().getServletContext().getRealPath("/") + "file/";
		//相对路径
		String url = plupload.getRequest().getSession().getServletContext().getContextPath() + "/file/";
		//创建UUID为文件命名
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-","");
		File dir = new File(path);
		T09 t09 = new T09();
		t09.setF0906(userService.getUserById(Integer.parseInt(req.getParameter("f0906"))).getF0204());
		t09.setF0902(plupload.getName());
		System.out.println(path);
		
		//设置文件类型
		t09.setF0904(plupload.getName().split("\\.")[1]);
		t09.setF0905(plupload.getRequest().getParameter("f0905"));
		t09.setF0907(plupload.getRequest().getParameter("f0907"));
		//设置文件路径
		t09.setF0903(uuid+"."+plupload.getName().split("\\.")[1]);
		if(plupload.getRequest().getParameter("f0907").equals("企业文件")){
			t09.setF0101(Integer.parseInt(plupload.getRequest().getParameter("f0101")));
		}
		
		t09 = lowCardService.insertT09(t09);
		try {
			//上传文件
			FileUploadUtil.upload1(plupload,dir,uuid);
	        //判断文件是否上传成功（被分成块的文件是否全部上传完成）  
	        if (FileUploadUtil.isUploadFinish(plupload)) {  
	        	System.out.println("文件名称==="+plupload.getName());
	        }
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		if(t09.getF0901() != null){
			return  new ResponseResult<T09>(true,"添加成功");
		}else{
			return  new ResponseResult<T09>(false, "添加失败");
		}
		
	}
	
	/**
	 * 根据法律法规文件ID修改法律法规文件信息
	 * @param f0901   法律法规文件ID
	 * @param f0101   企业编号:如果F0907值为全局文件，则该值为空，表示该文件是所有企业必须访问的，如果F0907为企业文件，则该字段填写该文件所属的企业的ID，表示该文件是该企业的。
	 * @param f0902   文件名称 
	 * @param f0903   文件GUID，文件保存在系统Files文件夹下，全部名称为GUID命名
	 * @param f0904   文件类型：这里填写文件的后缀，如word文档，就填写 docx或doc 
	 * @param f0905   文件类级别（硬）：法律、行政法规、部门规章、地方法规和规章、文件公告、国家标准、行业标准 
	 * @param f0906   上传人员：这里指的是上传该文件的工作人员 
	 * @param f0907   文件访问级别（硬）：全局文件、企业文件;这里全局文件指是的，所有企业都可必须访问的文件；企业文件：指的是该文件只属于某个企业访问 
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/lowcard/updatelowInfo?f0901=10900002&f0101=101000001&f0902=安德的游戏
	 */
	@RequestMapping(value="/updatelowInfo",method=RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> updateLow(String f0901,String f0101,String f0902,String f0903,String f0904,
			String f0905,String f0906,String f0907) throws ParseException{
		ResponseResult<String> rr;
		T09 t09 = new T09(Integer.parseInt(f0901),Integer.parseInt(f0101), f0902, f0903, f0904, f0905, f0906, f0907);
		int l = lowCardService.updateT09(t09);
		System.out.println(l+":-------");
		if (l>=1) {
			rr=new ResponseResult<>(true,"修改成功");
		}else {
			rr=new ResponseResult<>(false,"修改失败");
		}
		return rr;
	}
	/**
	 * 根据法律法规文件ID删除法律文件
	 * 
	 * @param f0901   法律法规文件ID
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/lowcard/deleteLowInfo?f0901=10900002
	 */
	@RequestMapping(value="/deleteLowInfo",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> deleteLow(String f0901)  throws ParseException{
		
		ResponseResult<String> rr;
		int l = lowCardService.deletelow(Integer.parseInt(f0901));
		if (l>=1) {
			rr = new  ResponseResult<>(true,"删除成功");
		}else{
			rr = new ResponseResult<>(false, "删除失败");
		}
		return rr;
	}
	
	/**
	 * 
	 * @功能描述: 查询某个特种设备的所有安全附件
	 * @提交方式: GET
	 * @时间:2017年5月25日 下午10:25:20
	 * @方法名: findAllCheckInfoByE 
	 * @接口地址:
	 * 		本地：
	 * 		http://localhost:8080/WH/lowcard/findAllCheckInfoByE?f0301=103000001
	 * 		服务器：
	 * 		http://120.76.145.236:8090/WH/lowcard/findAllCheckInfoByE?f0301=103000001
	 * @测试数据:
	 * @param f0301
	 * @return
	 * @return ResponseResult<List<T04>>
	 */
	@RequestMapping(value="/findAllCheckInfoByE",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T04>> findAllCheckInfoByE(String f0301){
		List<T04> list = new ArrayList<>();
		ResponseResult<List<T04>> rr;
		list = lowCardService.findAllCheckInfoByE(Integer.parseInt(f0301));
		if(list.isEmpty()){
			rr = new ResponseResult<List<T04>>(false, "未查询到数据");
		}else{
			rr = new ResponseResult<List<T04>>(true, list);
		}
		
		return rr;
	}
	
	/**
	 * 查询所有特种设备及安全附件检测记录信息
	 * 
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/lowcard/selectAllCheckInfo
	 */
	@RequestMapping(value="/selectAllCheckInfo",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	private ResponseResult<List<T04>> selectAllCheckCard() throws ParseException{
		
		ResponseResult<List<T04>> rr;
		
		List<T04> t04list = lowCardService.seleteAllCheckCards();
		
		rr=new ResponseResult<List<T04>>(true,t04list);
		
		return rr;
	}
	/**
	 * 根据特种设备记录ID查询单个特种这杯检测信息
	 * @param f0401
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/lowcard/selectCheckInfo?f0401=10400003
	 */
	@RequestMapping(value="/selectCheckInfo",method=RequestMethod.GET, 
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T04> selectOneCheckInfo(String f0401) throws ParseException{
		
		ResponseResult<T04> rr;
		
		T04 t04 = lowCardService.selectOneCheckInfo(Integer.parseInt(f0401));
		
		rr = new ResponseResult<>(true, t04);
		
		return rr;
	}
	/**
	 *  添加特种设备及安全附件检测信息
	 * 
	 * @param f0401     特种设备及安全附件检测记录卡ID
	 * @param f0301  ID:表示该检测的某个特种设备的检测信息 
	 * @param f0402     安全附件名称 
	 * @param f0403     安全附件检测时间 
	 * @param f0404     检测结论 
	 * @param f0405     检测报告编号 
	 * @param f0406     附件检测周期（默认值和F0313相同）
	 * @param f0407     下次检测时间 
	 * @param f0408     备是否过期（硬）：未过期（Default）,过期 
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/lowcard/addCheckInfo?f0301=103000000&f0403=2015-04-05&f0407=2015-04-06
	 */
	@RequestMapping(value="/addCheckInfo",method=RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T04> addCheckInfo(String f0401,String f0301,String f0402,String f0403,
			String f0404,String f0405,String f0406,String f0407,String f0408) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ResponseResult<T04> rr;
		System.out.println(sdf.parse(f0403));
		T04 t04 = new T04(null, Integer.parseInt(f0301), f0402, sdf.parse(f0403), f0404, f0405, f0406, sdf.parse(f0407), f0408);
		
		t04 = lowCardService.InsertCheckInfo(t04);
		System.out.println(t04.getF0403());
		rr = new ResponseResult<T04> (true,t04); 
		return rr;
	}
	
	/**
	 * 根据特种设备及安全附件检测ID修改特种设备及安全附件检测信息
	 * 
	 * @param f0401     特种设备及安全附件检测记录卡ID
	 * @param f0301   ID:表示该检测的某个特种设备的检测信息 
	 * @param f0402     安全附件名称 
	 * @param f0403     安全附件检测时间 
	 * @param f0404     检测结论 
	 * @param f0405     检测报告编号 
	 * @param f0406     附件检测周期（默认值和F0313相同）
	 * @param f0407     下次检测时间 
	 * @param f0408     备是否过期（硬）：未过期（Default）,过期 
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/lowcard/updateCheckInfo?f0401=10400007&f0301=103000000&f0403=2016-10-01&f0407=2016-10-02
	 */
	@RequestMapping(value="/updateCheckInfo",method=RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> updateCheckInfo(String f0401,String f0301,String f0402,String f0403,
			String f0404,String f0405,String f0406,String f0407,String f0408) throws ParseException{
		ResponseResult<String> rr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 		T04 t04 = new T04(Integer.parseInt(f0401),Integer.parseInt(f0301), f0402, sdf.parse(f0403), f0404, f0405, f0406,sdf.parse(f0407), f0408);
		
		int l = lowCardService.updateCheckInfo(t04);
		if (l>=1) {
			rr = new ResponseResult<>(true, "修改成功");
		}else{
			rr = new ResponseResult<>(false, "添加失败");
		}
		return rr;
	}
	
	/**
	 * 根据特种设备及安全附件检测ID删除本条信息
	 * 
	 * @param f0401     特种设备及安全附件检测记录卡ID
	 * @return
	 * @throws ParseException
	 * 
	 * 测试：http://localhost:8080/WH/lowcard/deleteCheckInfoByPrimaryKey?f0401=10400014
	 */
	@RequestMapping(value="/deleteCheckInfoByPrimaryKey",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> deleteCheckInfo(String f0401) throws ParseException{
		ResponseResult<String> rr;
		
		int l = lowCardService.deleteCheckInfo(Integer.parseInt(f0401));
		if (l>=1) {
			rr = new ResponseResult<>(true, "删除成功");
		}else{
			rr = new ResponseResult<>(false, "删除失败");
		}
		return rr;
	}
	/**
	 * 
	 * @功能描述:根据企业ID查询企业法律法规
	 * @提交方式: GET
	 * @时间:2017年5月22日 下午3:33:50
	 * @方法名: selectListLowByEnterpriseId 
	 * @接口地址:http://localhost:8080/WH/lowcard/selectListLowByEnterpriseId?f0101=101000001
	 * @测试数据:
	 * @param f0101 企业号ID
	 * @return
	 * @return ResponseResult<List<T09>>
	 */
	@RequestMapping(value="/selectListLowByEnterpriseId",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T09>> selectListLowByEnterpriseId(String f0101) {
		
		List<T09> t09list = lowCardService.selectListLowByEnterpriseId(Integer.parseInt(f0101));
		
		ResponseResult<List<T09>> rr;

		System.out.println(t09list);
		if (t09list.size()==0) {
			rr = new ResponseResult<List<T09>>(false, "没有查询到");
		}else{
			rr = new ResponseResult<List<T09>>(true, t09list);
		}
		
		return rr;
	}
	
	/**
	 * @功能描述: 根据某个企业下该类别查询法律法规  或者 查询某个类别下的法律法规
	 * @提交方式: 
	 * @时间:2017年6月12日 下午6:13:49
	 * @方法名: findLowByTorC 
	 * @接口地址: http://120.76.145.236:8090/WH/lowcard/findLowByTorC
	 * @测试数据:
	 * @param f0101
	 * @param f0905
	 * @return
	 * @return ResponseResult<List<T09>>
	 */
	@RequestMapping(value="/findLowByTorC",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<List<T09>> findLowByTorC(String f0101,String f0905){
		ResponseResult<List<T09>> rr;
		List<T09> list = lowCardService.findLowByComAndType(Integer.parseInt(f0101), f0905);
		
		if(list.isEmpty()){
			rr = new ResponseResult<List<T09>>(false, "暂无数据");
		}else{
			rr = new ResponseResult<List<T09>>(true, list);
		}
		
		return rr;
		
	}
	
	@RequestMapping(value="/findLowById",method=RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<T09> findLowById(String f0901){
		ResponseResult<T09> rr;
		T09 t09 = lowCardService.selectLowByPrimaryKey(Integer.parseInt(f0901));
		if(t09 == null){
			rr = new ResponseResult<T09>(false, "暂无数据");
		}else{
			rr = new ResponseResult<T09>(true, t09);
		}
		return rr;
	}
	
	
	/**
	 * 
		 * @功能描述: 下载法律法规文件
		 * @提交方式: 
		 * @时间:2017年5月31日 下午9:51:41
		 * @方法名: downloadFiles 
		 * @接口地址: http://localhost:8080/WH/lowcard/download?f0901=
		 * @本地测试地址: http://120.76.145.236:8090/WH/lowcard/download?f0901=
		 * @测试数据:
		 * @param f0901 文件id
		 * @return
		 * @return ResponseEntity<byte[]>
	 */
	@RequestMapping(value="/download",method={RequestMethod.GET,RequestMethod.POST})
	public void downloadFiles(String f0901,HttpServletRequest req,HttpServletResponse resp) {
		
/*		T09 t09 = lowCardService.selectLowByPrimaryKey(Integer.parseInt(f0901));
		String filenameIdname = t09.getF0903();
		String filename=t09.getF0902();
		ResponseEntity<byte []> rr;
		resp.reset();
		try {
			//传入文件UUID形成的文件名filenameIdname，再加入本件本身的名字filename
			rr=Utils.downloadfile(filenameIdname,filename,req,resp);
			return rr;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	*/
		
		T09 t09 = lowCardService.selectLowByPrimaryKey(Integer.parseInt(f0901));

        
        resp.setContentType("application/octet-stream");
        try {
			resp.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(t09.getF0902(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
			req.getRequestDispatcher("/file/" + t09.getF0903()).forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/downloadFile",method={RequestMethod.GET,RequestMethod.POST})
	@Override
	public void setServletConfig(ServletConfig arg0) {
		servletConfig = arg0;		
	}
	
	
	
}
