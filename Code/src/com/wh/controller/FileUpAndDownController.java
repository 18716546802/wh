package com.wh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.wh.dto.ResponseResult;
import com.wh.service.LowCardService;


@Controller
@RequestMapping("/filecontroller")
public class FileUpAndDownController extends HttpServlet implements ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext servletContext;
	
	@RequestMapping(value="/getImageBase",method=RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> uplaodImage(String param) {
		String imgStr = param;
		System.out.println(imgStr+"\n"+"-----");
		String str ;
		ResponseResult<String> rr;
		if (true) {
			str = "上传图片成功";
			rr = new ResponseResult<>(true, str);
		}else{
			str = "上传图片失败";
			rr = new ResponseResult<>(false, str);
 		}
		return rr;
	}
	@RequestMapping(value="/addEnterpriseInfo",method=RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult<String> uplaodInfo(String f0101,String f0102,HttpServletRequest req,HttpServletResponse resp) {

		ResponseResult<String> rr = null;
		
		
		

		
		
		
		rr = new ResponseResult<>(true, "ccccc");
		return rr;
	}
	@RequestMapping(value="/todownload",method={RequestMethod.GET,RequestMethod.POST})
	public String todownload(HttpServletRequest req) {
		
		req.setAttribute("message", "begin");
		return "result";
	}
	@RequestMapping(value="/downloadzz",method={RequestMethod.GET,RequestMethod.POST})
	public ResponseEntity<byte []> downloadFiles(String f0901,HttpServletRequest req,HttpServletResponse resp) {
		
		
		//下载文件路径
		String path = req.getServletContext().getRealPath("/")+"file/";
		String filename = req.getParameter("files");
		File file = new File(path+filename);
		HttpHeaders headers = new HttpHeaders();
		//下载显示的文件名，解决文件乱码问题
		try {
			String downlaodFilename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			//通知浏览器以attachment（下载方式）打开图片
			headers.setContentDispositionFormData("attachment", downlaodFilename);
			//二进制流数据
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			System.out.println("-11111");
			return new ResponseEntity<byte []>(FileUtils.readFileToByteArray(file), headers,HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
		
	}
	@RequestMapping(value="/downloads",method={RequestMethod.GET,RequestMethod.POST})
	public String downloadFiless(String files,HttpServletRequest req,HttpServletResponse resp) {
		
		//下载文件路径
		String path = req.getServletContext().getRealPath("/")+"file/";
		String filename = req.getParameter("files");
		File file = new File(path+filename);
		HttpHeaders headers = new HttpHeaders();
		
		try {
			OutputStream os = resp.getOutputStream();
			//下载显示的文件名，解决文件乱码问题
			String downlaodFilename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			//通知浏览器以attachment（下载方式）打开图片
			headers.setContentDispositionFormData("attachment", downlaodFilename);
			//二进制流数据
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			System.out.println("-11111");
			new ResponseEntity<byte []>(FileUtils.readFileToByteArray(file), headers,HttpStatus.CREATED);
			req.setAttribute("message", "下载成功");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			req.setAttribute("message","下载失败");
		}
		
		
		return "result";
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		
		this.servletContext = servletContext;
	}

}
