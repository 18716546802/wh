package com.wh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Case;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class Utils extends HttpServlet{
		

	   //图片转化成base64字符串  
	   public static String GetImageStr()  
	   {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
	       String imgFile = "d://1.jpeg";//待处理的图片  
	       InputStream in = null;  
	       byte[] data = null;  
	       //读取图片字节数组  
	       try   
	       {  
	           in = new FileInputStream(imgFile);          
	           data = new byte[in.available()];  
	           in.read(data);  
	           in.close();  
	       }   
	       catch (IOException e)   
	       {  
	           e.printStackTrace();  
	       }  
	       //对字节数组Base64编码  
	       BASE64Encoder encoder = new BASE64Encoder();  
	       return encoder.encode(data);//返回Base64编码过的字节数组字符串  
	   }  
	     
	   //base64字符串转化成图片  
	   public static String GenerateImage(String imgStr,ServletContext servletContext)  
	   {   //对字节数组字符串进行Base64解码并生成图片  
	       
		   if (imgStr == null) //图像数据为空  
	           return null;  
	       BASE64Decoder decoder = new BASE64Decoder();  
	       try   
	       {  

	           //Base64解码  
	           byte[] b = decoder.decodeBuffer(imgStr);  
	           for(int i=0;i<b.length;++i)  
	           {  
	               if(b[i]<0)  
	               {//调整异常数据  
	                   b[i]+=256;  
	               }  
	           }  
	           //生成jpeg图片  
	           
	           String path = servletContext.getRealPath("/")+"images\\";
	           File file = new File(path);
	           if (!file.exists()) {
	        	   file.mkdir();
	           }
	   			String uuid = UUID.randomUUID().toString();
	   			uuid = uuid.replace("-","");
	           String timestrap = uuid+".jpg";
	           String imgFilePath = path+ timestrap;//新生成的图片  
	           //
	        
	           OutputStream out = new FileOutputStream(imgFilePath);   
	           out.write(b);  
	           out.flush();  
	           out.close();  
	           imgFilePath = "http://"+AllParam.ServerUrl+"/WH/images/"+timestrap; 
	           return imgFilePath;  
	       }   
	       catch (Exception e)   
	       {  
	           return null;  
	       }  
	   }
	public static String uploadfile(String f0903,ServletContext servletContext) {

		String pathname = servletContext.getRealPath("/")+"Files\\";
		File file = new File(pathname);
		if (!file.exists()) {
			file.mkdir();
		}
		
		
		//初始化对象
		
		
		System.out.println(pathname);
		
		return null;
	}

	public static String uploadfiles(CommonsMultipartFile file,ServletContext servletContext) {
		
		
		System.out.println("filename:"+file.getOriginalFilename());
		try{
			//获取输出流
			String path = servletContext.getRealPath("/")+"Files\\";
			File files= new File(path);
			if (!files.exists()) {
				files.mkdir();
			}
			System.out.println(path+file.getOriginalFilename());
			
			//OutputStream os = new FileOutputStream(path+file.getOriginalFilename());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public static String NowTime() {
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowdatastr = sdf.format(d);
		return nowdatastr;
	}
	public static Date addDate(Date d,long day) throws ParseException {

		  long time = d.getTime();
		  day = day*24*60*60*1000;
		  time+=day;
		  return new Date(time);
	}
	public static String getfinalday(String f0811, String f0810) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		int st = Integer.parseInt(f0810);
		try {
			Date d = sdf.parse(f0811);
			dt = addDate(d, st);
			return sdf.format(dt);
		} catch (ParseException e) {
			
			return null;
		}
		
		
 	}

	public static void main(String[] args) {
		String str = getfinalday("2017-05-25","60");
		System.out.println(str);
		
	}
	public static String returnFileslaod(HttpServletRequest req,HttpServletResponse res,ServletContext servletContext) {
		//设置上传文件保存的路径
		String filePath = servletContext.getRealPath("/")+"Files\\";
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
	
		SmartUpload su = new SmartUpload();
		//初始化对象
		try {
			su.initialize((ServletConfig) servletContext, req, res);
			try {
				su.upload();
				for(int i=0;i<su.getFiles().getCount();i++)
				{
					com.jspsmart.upload.File tempfile = su.getFiles().getFile(i);
					System.out.println("--------------");
					System.out.println("表单当中name的值："+tempfile.getFieldName());
					System.out.println("上传文件名："+tempfile.getFileName());
					System.out.println("上传文件大小："+tempfile.getSize());
					System.out.println("上传文件的拓展名："+tempfile.getFileExt());
					System.out.println("上传文件的全名 ："+tempfile.getFilePathName());
					System.out.println("--------------");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unused")
	public static ResponseEntity<byte []> downloadfile(String filenameIdname,String filename,HttpServletRequest req,HttpServletResponse resp) {
		
		//下载文件路径
		String path = req.getServletContext().getRealPath("/")+"file/";
		File file = new File(path+filenameIdname);
		HttpHeaders headers = new HttpHeaders();
		
		try {
			//下载显示的文件名，解决文件乱码问题
			String downlaodFilename = URLEncoder.encode(filename,"utf-8"); //new String(filename.getBytes("UTF-8"), "iso-8859-1");
			
			//通知浏览器以attachment（下载方式）打开图片
			headers.setContentDispositionFormData("attachment", downlaodFilename);
			//二进制流数据
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			resp.setHeader("Content-Disposition", "attachment;fileName="
					+ downlaodFilename);
			return new ResponseEntity<byte []>(FileUtils.readFileToByteArray(file), headers,HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}


}
