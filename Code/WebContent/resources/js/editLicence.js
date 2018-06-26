
//数据库数据
var f0101;
var f0501;
var f0502;
var f0503;
var f0504;
var f0505;


$(document).ready(function(){
 
   var companyName =JSON.parse(localStorage.getItem("companyName"));
   $("#companyName").text(companyName);	
   var rowData=JSON.parse(localStorage.getItem("rowData"));

   
   f0501 =rowData.f0501;
   f0101=rowData.f0101;

   $("#f0502").val(rowData.f0502);
   $("#f0503").val(rowData.f0503);
   $("#f0504").val(rowData.f0504);
   $("#f0505").val(rowData.f0505);
   //$("#f0505").attr("value", rowData.f0505); 
   
   
   
   
   $("#submitEditLicence").click(function(){
	   	
	   if(judgeSubmit()){
		   
		   $.ajax({		   
				type:"GET",
				url:"/WH/Qualification/updateQInfo",
				data:{
					f0501:f0501,
					f0502:f0502,
					f0503:f0503,
					f0504:f0504,
					f0505:f0505
				},
		   	    success:function(data){
		   	    	if(data.success){
		   	    		var companyID=f0101;  			   	    		
		   	    		localStorage.setItem("companyID",JSON.stringify(companyID));	   	    	
		   	    		showPage("/WH/jsp/companyLicence.jsp");
		   	    		//	window.location.href="/WH/jsp/companyLicence.jsp";	
		   	    	}else{
		   	    		alert("修改失败");
		   	    	}
		   	    }
			   
		   });
	   }
	
   });
   	
});


//判断提交数据是否为空
function judgeSubmit(){

	f0502=$("#f0502").val();
	f0503=$("#f0503").val();
	f0504=$("#f0504").val();
	f0505=$("#f0505").val();
	 var clt = f0504;
	 var clt1=[];
	 for(var i=0;i<clt.length;i++)
	{
		 if(clt[i]=='/')
			 clt1+='-';
		 else
			 clt1+=clt[i];
	} 
	 f0504=clt1;
	 
	 if(f0502==""){
		 alert("证照名称不能为空！");
		 return false;
	 }
	 if(f0504=="yyyy-mm-dd"){
		 alert("有效日期不能为空！");
		 return false;
	 }
	 if(f0505==""){
		 alert("证照是否有效不能为空！");
		 return false;
	 }
	
	else{
		return true;
	}
}





 
