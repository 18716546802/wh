package com.wh.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.wh.dto.CompanyHomedto;
import com.wh.dto.ResponseResult;
import com.wh.model.T01;
import com.wh.model.T07;
import com.wh.model.T08;
import com.wh.service.JobService;
import com.wh.service.PersonService;
import com.wh.service.QualificationService;
import com.wh.util.Utils;
/**
 * 企业端手机相关的页面
 */
/**
 * 企业端主页
 */
@Controller
@RequestMapping("/companyApp")
public class CompanyAppController implements ServletContextAware{
 
		@Autowired
		private JobService jobService;
		@Autowired
		private QualificationService qService;
		@Autowired
		private PersonService pService;
		
		
		private ServletContext servletContext;
		/**
		 * 查询企业基本信息
		 * @param id
		 * @param model
		 * @return
		 */
		@RequestMapping("/home")
		public String getHomeInfo(String id,Model model)
		{
			CompanyHomedto chdto = new CompanyHomedto();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			//获取企业基本信息，并将相关信息存储到model中
			T01 t01 = jobService.findT01(Integer.parseInt(id));
			chdto.setcName(t01.getF0102());
			chdto.setcEClass(t01.getF0104());
			chdto.setcMClass(t01.getF0125());
			if( t01.getF0105() == null ){
				chdto.setBuildTime("暂无");
			}else{
				chdto.setBuildTime(sdf.format(t01.getF0105()));
			}
			chdto.setLegalPerson(t01.getF0108());
			chdto.setAddress(t01.getF0107());
			chdto.setSecurityRP(t01.getF0110());
			chdto.setSecurityMP(t01.getF0108());
			chdto.setcLicenses(qService.findAllEnterpriseQ(Integer.parseInt(id)));
			chdto.setpLicenses(pService.selectListQualificationsByEnterpriseId(Integer.parseInt(id)));
			chdto.setSequs(jobService.findAllEquipmentByCompany(Integer.parseInt(id)));
			model.addAttribute("data", chdto);
			//跳转页面
			return "companyAppHomePage";
		}
		/**
		 * 企业人员培训记录页面
		 * @param id
		 * @param model
		 * @return
		 */
		@RequestMapping("/recored")
		public String getRecored(String id,Model model){
			List<T07> lists = null;
			lists = pService.selectListQualificationsByEnterpriseId(Integer.parseInt(id));
			model.addAttribute("items", lists);
			model.addAttribute("f0101", id);
			return "companyAppTrainingRecored";
		}
		
		@RequestMapping("/riskPage")
		public ModelAndView riskpage(String id,@RequestParam(required=false) String danName,ModelAndView mav){
			
			if(danName == null || danName.equals("所有隐患")){
				List<T08> lists = jobService.selectT08(Integer.parseInt(id));
				mav.addObject("items", lists);
				mav.addObject("f0101", id);
			}else{
				mav.addObject("f0101", id);
				System.err.println(danName);
			}
			mav.setViewName("companyAppDanManagePage");
			return mav;
		}
		
		
		/**
		 * 
		 * @功能描述: 企业端提交隐患整改的接口
		 * @提交方式: 
		 * @时间:2017年6月18日 上午1:47:41
		 * @方法名: updatePurkingPeril 
		 * @接口地址: http://120.76.145.236:8090/WH/companyApp/updatePurkingPeril?f0801=&f0804=&f0805=&f0808=&f0816
		 * @测试数据:
		 * @param f0801
		 * @param f0804
		 * @param f0805
		 * @param f0808
		 * @param f0816
		 * @return
		 * @return ResponseResult<String>
		 */
		@RequestMapping(value="/updatePurkingPeril", method=RequestMethod.POST,
				produces ="application/json;charset=UTF-8")
		@ResponseBody
		public ResponseResult<String> updatePurkingPeril(String f0801,String f0804,String f0805,String f0808,String f0816,String f0817,String f0818){
			ResponseResult<String> rr;
			T08 t08  = jobService.slelectT08(Integer.parseInt(f0801));
			t08.setF0801(Integer.parseInt(f0801));
			String pre = t08.getF0805();
			
			if(null == pre || "".equals(pre) || "null".equals(pre)){
				t08.setF0805(f0805);
			}else{
				if(!pre.contains(f0805)){
					pre += ",";
					pre += f0805;
					t08.setF0805(pre);
				}
			}
			t08.setF0804(f0804);
			t08.setF0808(f0808);
			
			t08.setF0816(Utils.GenerateImage(f0816, servletContext));
			t08.setF0817(Utils.GenerateImage(f0817, servletContext));
			t08.setF0818(Utils.GenerateImage(f0818, servletContext));
			if(jobService.updateT08(t08) >0){
				rr = new ResponseResult<>(true, "提交成功");
			}else{
				rr = new ResponseResult<>(false, "提交失败");
			}
			return  rr;
			
		}
		/**
		 * 
		 * @功能描述: 安卓员工端提交手机经纬度
		 * @提交方式: 
		 * @时间:2017年7月15日 下午5:59:51
		 * @方法名: updateELocation 
		 * @接口地址:
		 * @测试数据:
		 * @param f0101
		 * @param f0114
		 * @param f0115
		 * @return
		 * @return ResponseResult<String>
		 */
		@RequestMapping(value="/updateELocation", method=RequestMethod.GET,
				produces ="application/json;charset=UTF-8")
		@ResponseBody
		public ResponseResult<String> updateELocation(String f0101, String f0114, String f0115){
			T01 t = new T01();
			t.setF0114(Double.parseDouble(f0114));
			t.setF0101(Integer.parseInt(f0101));
			t.setF0115(Double.parseDouble(f0115));
			ResponseResult<String> rr;
			if(jobService.updateByPrimaryKeySelective(t) >= 1){
				
				rr = new ResponseResult<>(true, new String("修改成功"));
			}else{
				rr = new ResponseResult<>(false, new String("修改失败"));
			}
			return rr;
		}
			
		@Override
		public void setServletContext(ServletContext arg0) {
			// TODO Auto-generated method stub
			servletContext = arg0;
		}
}
