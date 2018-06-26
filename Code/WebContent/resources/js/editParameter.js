

var f5001;
var f5003;
var f5006;
var f5007;
var f5005;




$(document).ready(function(){
	
	 var rowData=JSON.parse(localStorage.getItem("rowData"));
	 console.log(rowData);
	 f5001=rowData.f5001;
	 f5003="万汇业务员";
	 $("#f5005").val(rowData.f5005);
	 $("#f5006").val(rowData.f5006);
	 $("#f5007").val(rowData.f5007);
	 
 
	 $("#submitEditParameter").click(function(){
		 if(checkValue()){
			 $.ajax({		
				 type:"post",
				 url:"/WH/job/updateTodayParameters",
				 data:{
					 f5001:f5001,
					 f5003:f5003,
					 zj:f5005,
					 zf:f5006,
					 wh:f5007,
					 f5005:null
				 },
			 	 success:function(data){
			 		 if(data.success){
			 			 
			 			 showPage("/WH/jsp/getAllParameter.jsp");
			 			 
			 			 //window.location.href="/WH/jsp/getAllParameter.jsp";
			 		 }else{
			 			 alert("修改失败");
			 		 }
			 	 }
				 
			 });
		 }
	 });
	 
})


function checkValue(){
	
	f5005=$("#f5005").val();
	f5006=$("#f5006").val();
	f5007=$("#f5007").val();
	
	if(f5005==""){
		alert("业务员不能为空！");
		return false;
	}
	if(f5006==""){
		alert("专家不能为空！");
		return false;
	}
	if(f5007==""){
		alert("政府人员不能为空！");
		return false;
	}else{

		return true;
	}	
	
}