var f0101;
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
var f0118;
var f0126;
var f0127;
var f0125;
var f0601;
var f0112;



$(document).ready(function(){
	var rowData = JSON.parse(localStorage.getItem("rowData"));
	console.log(rowData);
	f0101 = rowData.f0101;
	
	f0601 = rowData.f0601;
	var str = rowData.f0102;
	var str1;
	var str2;
	var sum="";
	
	$("#f0102").val(rowData.f0102);
	$("#f0103").val(rowData.f0103);
	$("#f0104").val(rowData.f0104);
	$("#f0105").val(rowData.f0105);
	$("#f0106").val(rowData.f0106);
	$("#f0107").val(rowData.f0107);
	$("#f0108").val(rowData.f0108);
	$("#f0109").val(rowData.f0109);
	$("#f0110").val(rowData.f0110);
	$("#f0111").val(rowData.f0111);
	$("#f0116").val(rowData.f0116);
	$("#f0117").val(rowData.f0117);
	$("#f0118").val(rowData.f0118);
	$("#f0121").val(rowData.f0121);
	$("#f0122").val(rowData.f0122);
	$("#f0123").val(rowData.f0123);
	$("#f0124").val(rowData.f0124);
	$("#f0125").val(rowData.f0125);
	$("#f0126").val(rowData.f0126);
	$("#f0127").val(rowData.f0127);
	$("#f0128").val(rowData.f0128);
	$("#f0112").val(rowData.f0112);
	$("#f0130").val(rowData.f0130);
	
	
$("#submitEidtCompany").click(function(){
		if(judgeSubmit()){
			$.ajax({
				type:"POST",
				url:"/WH/job/updateEnterprise",
				data:{
					 f0101:f0101,
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
			 		 f0118:f0118,
			 		 f0130:f0130,
				},
				success:function(data){
						
						alert("修改成功");					
						showPage("/WH/jsp/companyList.jsp");
						//window.location.href="/WH/jsp/companyBaseInfo.jsp";	
				},	
				error:function(e){
						
					
						alert("修改成功");
						showPage("/WH/jsp/companyList.jsp");
				}
			})
		}
	})

function judgeSubmit(){
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
	 f0118=$("#f0118").val();
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
	//判断时间格式
	function checkTimeFormat(){

	   var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
	   if(!reg.test(f0105) || !reg.test(f0130)){	   
	       return false;
	   }else{
	       return true;
	   }
	}

	//判断时间大小
	function RQcheck(RQ) {
	  var date = RQ;
	  var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	  if (result == null)
	      return false;
	  var d = new Date(result[1], result[3] - 1, result[4]);
	  return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4]);
	}
	function checkTimeCorrect() {
	  var ret = true;
	   
	  if (!RQcheck(f0105) || !RQcheck(f0130))  {
	  return false;
	  }else{
	   return true;
	  }
	}

})