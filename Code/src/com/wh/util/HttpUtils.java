package com.wh.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HttpUtils {
	/**
	 *  本功能使用得是高德地图的地理编码WEB API,如该功能不能正常使用了,后期维护者可以在高德地图的开发者平台上申请账号重新使用
	 *  address=北京市朝阳区阜通东大街6号
	 *  output=XML/JSON 设置返回值的格式,可以是XML也可以是JSON
	 *  key=b902204b64e0e62314300605bf757a54 <用户的key>"; 
	 */
	
	private final static String URL = "http://restapi.amap.com/v3/geocode/geo?key=b902204b64e0e62314300605bf757a54&output=JSON";
	
	/**
	 * @功能描述: 解析json 数据，得到该地址的 
	 */
	public static String getLocation(String data){
		JSONObject json = JSON.parseObject(data); 
		if(json.getString("status").equals("1") && json.getInteger("count") >0){
			JSONArray city = json.getJSONArray("geocodes");  
			JSONObject location = JSON.parseObject(city.get(0).toString());
			return location.getString("location");
		}else{
			return "failed";
		}

	}
	
	/**
	 * @功能描述: 通过传入的通讯地址,调用高德地图的服务,获取到该地址的详细信息
	 */
	public static String sendGet(String site){
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = URL+"&address="+site;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
        getLocation(result);
        return result;
	}
	public static void main(String[] args){
		sendGet("重庆科技学院");
	}
}
