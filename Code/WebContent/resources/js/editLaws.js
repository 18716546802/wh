var f0101;
var f0901;
var f0902;
var f0903;
var f0904;
var f0905;
var f0906;
var f0907;

$(document).ready(function(){
	var rowData = JSON.parse(localStorage.getItem("rowData"));
	f0901 = rowData.f0901;
	f0101 = rowData.f0101;
	var str = rowData.f0902;
	var str1;
	var str2;
	var sum="";

	for(var i=1;i<str.length;i++){
		if(str[i] ==">"){
			str1=i;
			break;
		}
	}
	for(var i=str1;i<str.length;i++){
		if(str[i] =="<"){
			str2=i;
			break;
		}
	}	
	for(var i=str1+1;i<str2;i++){
		if(str[i]!="<"){
			sum+=str[i];
		}
	}

	
	$("#f0903").val(rowData.f0903);
	$("#f0904").val(rowData.f0904);
	$("#f0905").val(rowData.f0905);
	$("#f0906").val(rowData.f0906);
	$("#f0907").val(rowData.f0907);
	$("#f0902").val(sum);
	
$("#submitFormWhUser").click(function(){
	if(judgeSubmit()){
		$.ajax({
			type:"POST",
			url:"/WH/lowcard/updatelowInfo",
			data:{
				f0101:f0101,
				f0902:f0902,
				f0903:f0903,
				f0904:f0904,
				f0905:f0905,
				f0906:f0906,
				f0907:f0907,
				f0901:f0901
			},
			success:function(data){
					window.location.href="/WH/jsp/lawsAndRegulations.jsp";	
			},	
			error:function(e){
					alert("修改失败");
			}
		})
	}
})
function judgeSubmit(){

	f0902=$("#f0902").val();
	f0904=$("#f0904").val();
	f0905=$("#f0905").val();
	f0906=$("#f0906").val();
	f0907=$("#f0907").val();

 
	
	if(f0902=="" || f0905==""){		
		alert("请您完善修改信息");
		return false;
	}else{
		return true;	
		}
	}
})