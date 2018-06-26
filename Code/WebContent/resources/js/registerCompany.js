var f0601;
var f0102;
var f0104;
var f0103;
var f0105;//成立日期
var f0108;
var f0107;
var f0110;
var f0111;
var f0108;
var f0109;
var f0128;
var f0130;//上次检查时间
var f0121;
var f0122;
var f0123;
var f0124;
var f0116;
var f0117;
var f0126;
var f0127;
var f0125;
var f0112;



$(document).ready(function() {
	
	
	f0601=JSON.parse(localStorage.getItem("companyID"));
	var companyName = JSON.parse(localStorage.getItem("companyName"));
	$("#companyName").text(companyName);
	
 
	
	
	$("#submitAddCompany").click(function(){
		
		 if(checkValue()){			 	 
			 $.ajax({
				 type:"post",
				 url:"/WH/job/addEnterprise",
				 data:{
					 f0601:f0601,
					 f0102:f0102,
					 f0104:f0104,
					 f0103:f0103,
					 f0105:f0105,
					 f0108:f0108,
					 f0107:f0107,
					 f0110:f0110,
					 f0111:f0111,
					 f0108:f0108,
					 f0109:f0109,
					 f0128:f0128,
					 f0121:f0121,
					 f0122:f0122,
					 f0123:f0123,
					 f0124:f0124,
					 f0116:f0116,
					 f0117:f0117,
					 f0126:f0126,
					 f0127:f0127,
					 f0125:f0125,
					 f0112:f0112,
				  },
			 	  success:function(data){
			 		  
			 		  if(data.success){						 			 
						localStorage.setItem("companyID",JSON.stringify(f0601));
						showPage("/WH/jsp/companyList.jsp");
						//window.location.href="/WH/jsp/companyList.jsp";
			   	    	
			 		  }else{
			 			  alert(data.error);
			 		  }
			 	  }
			 });
		 }
		
	});
	
	
});


function checkValue(){
	
	 f0102=$("#f0102").val();
	 f0104=$("#f0104").val();
	 f0103=$("#f0103").val();
	 f0105=$("#f0105").val();//成立日期
	 f0108=$("#f0108").val();
	 f0107=$("#f0107").val();
	 f0110=$("#f0110").val();
     f0111=$("#f0111").val();
     f0108=$("#f0108").val(); 
	 f0109=$("#f0109").val();
	 f0128=$("#f0128").val().join(', ');
	 f0130=$("#f0130").val();//上次检查时间	 
	 f0121=$("#f0121").val();
	 f0122=$("#f0122").val();
	 f0123=$("#f0123").val();
	 f0124=$("#f0124").val().join(', ');
	 f0116=$("#f0116").val();
	 f0117=$("#f0117").val();
	 f0126=$("#f0126").val();
	 f0127=$("#f0127").val();
	 f0125=$("#f0125").val();
	 f0112=$("#f0112").val();
	 

	 var clt = f0105;
	 var clt1=[];
	 for(var i=0;i<clt.length;i++)
	{
		 if(clt[i]=='/')
			 clt1+='-';
		 else
			 clt1+=clt[i];
	} 
	 f0105=clt1;

	 
	 var str = f0130;
	 var str1=[];
	 for(var i=0;i<str.length;i++)
	{
		 if(str[i]=='/')
			 str1+='-';
		 else
			 str1+=str[i];
	} 
	 f0130=str1;

	 if(f0102==""){
		 alert("企业名称不能为空！");
		 return false;
	 }
	 if(f0121==""){
		 alert("安全'三同时'不能为空！");
		 return false;
	 }
	 if(f0122==""){
		 alert("职位'三同时'不能为空！");
		 return false;
	 }
	 if(f0123==""){
		 alert("安全标准化不能为空！");
		 return false;
	 }
	 if(f0124==""){
		 alert("应急预案不能为空");
		 return false;
	 }
	 if(f0105=="yyyy-mm-dd"){
		 alert("成立日期不能为空！");
		 return false;
	 }
	 if(f0107==""){
		 alert("通讯地址不能为空！");
		 return false;
	 }
	 if(f0130=="yyyy-mm-dd"){
		 alert("上次检查日期不能为空！");
		 return false;
	 }
	 
	 if(f0112=="请选择安全等级"){
		 alert("请填写安全等级");
		 return false;
	 }else{
		 return true;
	 }
 
}

