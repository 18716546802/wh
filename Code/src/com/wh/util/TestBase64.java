package com.wh.util;

public class TestBase64 {

	public static void main(String[] args) {
		
		String imgStr = Util.GetImageStr();
		System.out.println(imgStr);
		
		Util.GenerateImage(imgStr); 
		
		
		String str = "aa"+'\n'+"cc";
		
		System.out.println(str);
		
		String str2 ="bb" +  "\n" + "dd";
		System.out.println(str2);
 
	}

}
