var f0101;
var f1101;
var f1102;
var f1103;
var f1104;//投用时间
var f1105;
var f1106;
var f1107;
var f1108;
var f1109;
var f1110;
var f1112;
var f1113;
var f1114;
var f1115;
var f1116;
var f1117;
$(document).ready(function() {

	var companyName =JSON.parse(localStorage.getItem("companyName"));
	$("#companyName").text(companyName);	
    var rowData=JSON.parse(localStorage.getItem("rowData"));

    var f1101=rowData.f1101;
    var f0101=rowData.f0101;
    
    $("#f1102").val(rowData.f1102);
    $("#f1103").val(rowData.f1103);
    $("#f1104").val(rowData.f1104);
    $("#f1105").val(rowData.f1105);
    $("#f1106").val(rowData.f1106);
    $("#f1107").val(rowData.f1107);
    $("#f1108").val(rowData.f1108);
    $("#f1109").val(rowData.f1109);
    $("#f1110").val(rowData.f1110);
    
    $("#f1112").val(rowData.f1112);
    $("#f1113").val(rowData.f1113);
    $("#f1114").val(rowData.f1114);
    $("#f1115").val(rowData.f1115);
    $("#f1116").val(rowData.f1116);
    $("#f1117").val(rowData.f1117);
 
    
    

	$("#submitAddDanger").click(function() {

		if(checkValue()){
	        
			 $.ajax({
				 type:"post",
				 url:"/WH/danger/updateDanger",
				 data:{
					f0101:f0101,
					f1101:f1101,
					f1102:f1102,
					f1103:f1103,
					f1104:f1104,
					f1105:f1105,
					f1106:f1106,
					f1107:f1107,
					f1108:f1108,
					f1109:f1109,
					f1110:f1110,
					f1112:f1112,
					f1113:f1113,
					f1114:f1114,
					f1115:f1115,
					f1116:f1116,
					f1117:f1117,		
				 },
				 success:function(data){
					 if(data.success){
						//localStorage.setItem("companyID",JSON.stringify(f0101));
			   	    	
						showPage("/WH/jsp/companyBigDanger.jsp");
						//window.location.href="/WH/jsp/companyBigDanger.jsp";				 
					 }else{
						alert("修改失败");
					 }
				 }
			 });
	
		}
	});

});

function checkValue() {
	
	f1101=$("#f1101").val();
	f1102=$("#f1102").val();
	f1103=$("#f1103").val();
	f1104=$("#f1104").val();
	f1105=$("#f1105").val();
	f1106=$("#f1106").val();
	f1107=$("#f1107").val();
	f1108=$("#f1108").val();
	f1109=$("#f1109").val();
	f1110=$("#f1110").val();
	f1111=$("#f1111").val();
	f1112=$("#f1112").val();
	f1113=$("#f1113").val();
	f1114=$("#f1114").val();
	f1115=$("#f1115").val();
	f1116=$("#f1116").val();
	f1117=$("#f1117").val();
	
	 var str = f1104;
	 var str1=[];
	 for(var i=0;i<str.length;i++)
	{
		 if(str[i]=='/')
			 str1+='-';
		 else
			 str1+=str[i];
	} 
	 f1104=str1;

	 if(f1102==""){
		 alert("重大危险源名称不能为空！");
		 return false;
	 }
	 if(f1104=="yyyy-mm-dd"){
		 alert("重大危险源投用时间不能为空！");
		 return false;
	 }	
	 if(f1116==""){
		 alert("等级不能为空！");
		 return false;
	 }

	else{

		return true;
	}

 
}


// 判断时间格式
function checkTimeFormat() {

	var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
	var dempTime = $("#f1104").val();

	if (!reg.test(dempTime)) {
		return false;
	} else {
		return true;
	}

}

// 判断时间大小
function RQcheck(RQ) {
	var date = RQ;
	var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (result == null)
		return false;
	var d = new Date(result[1], result[3] - 1, result[4]);
	return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d
			.getDate() == result[4]);

}
function checkTimeCorrect() {
	var ret = true;
	var dempTime = $("#f1104").val();
	if (!RQcheck(dempTime)) {
		return false;
	} else {
		return true;
	}
}

