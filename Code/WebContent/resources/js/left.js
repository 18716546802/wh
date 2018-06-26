
var companyID;//判断是否为公司
var fatherID;
var companyName;//公司名称

 

function getTreeID(e){
	
	 companyID=$(e).find("input").val();
	 companyName=$(e).find("span").text();
	 localStorage.setItem("companyID",JSON.stringify(companyID));
	 localStorage.setItem("companyName",JSON.stringify(companyName));
	 
	 if(companyID>=101000000 && companyID<=101999999){					 
		 	showPage("/WH/jsp/companyDetailInfo.jsp");
			//window.location.href="/WH/jsp/companyDetailInfo2.jsp";
	 }else{
		 
		 showPage("/WH/jsp/companyList.jsp");
	 }	 
	 
};


//ajax 切换页面
function showPage(url){	
	
	try{
		
		$("#alertmod_grid-table").remove();
	}catch(e){
		
		
		
	}
	$.ajax({	
		type:"get",
		url:url,
		success:function(dates){			 
			$("#mainContent").html(dates);
		}
	});
	
}


//是否跳转企业证照页面
function a_CompanyLicence(){
 
	if(companyID>=101000000 && companyID<=101999999){	
	
		showPage("/WH/jsp/companyLicence.jsp");
		//window.location.href="/WH/jsp/companyLicence.jsp";	
	}
	else{
		alert("请您先选择公司");
	}
	
}

//企业信息
function a_CompanyBaseInfo(){
	 
 
	if(companyID>=101000000 && companyID<=101999999){	
		
	 	showPage("/WH/jsp/companyDetailInfo.jsp");
	}else{
		//返回地区ID
		 showPage("/WH/jsp/companyList.jsp");
	}
	 
}

//是否跳转企业设备页面
function a_CompanyEquipment(){
	
	if(companyID>=101000000 && companyID<=101999999){
		
		showPage("/WH/jsp/companyEquipment.jsp");
		
	//	window.location.href="/WH/jsp/companyEquipment.jsp";	
	}
	else{
		alert("请您先选择公司");
	}
}

//是否跳消息提醒
function a_CompanyAdminRemind(){
	
	if(companyID>=101000000 && companyID<=101999999){		 
		
		showPage("/WH/jsp/companyAdminRemind.jsp");
			
		//window.location.href="/WH/jsp/companyAdminRemind.jsp";	
	}
	else{
		alert("请您先选择公司");
	}
}
//是否跳转到人员资质  
function a_CompanyPeople(){

	if(companyID>=101000000 && companyID<=101999999){		 
		
		showPage("/WH/jsp/companyPeople.jsp");
		
		//window.location.href="/WH/jsp/companyPeople.jsp";	
	}
	else{
		alert("请您先选择公司");
	}
	
}
//是否跳到重大危险源
function a_CompanyDanger(){
	
	if(companyID>=101000000 && companyID<=101999999){		 
		
		showPage("/WH/jsp/companyBigDanger.jsp");
		//window.location.href="/WH/jsp/companyBigDanger.jsp";	
	}
	else{
		alert("请您先选择公司");
	}
}
//是否调到隐患管理
function a_CompanyHideDanger(){
	
	if(companyID>=101000000 && companyID<=101999999){		 
		
		showPage("/WH/jsp/companyHideDanger.jsp");
		//window.location.href="/WH/jsp/companyHideDanger.jsp";	
	}
	else{
		alert("请您先选择公司");
	}
}

//今日参数
function a_getAllParameter(){
	
	showPage("/WH/jsp/getAllParameter.jsp");
}

//今日隐患
function a_CompanyTodayHideDanger(){

	 showPage("/WH/jsp/companyTodayHideDanger.jsp");
}



$(document).ready(function(){	

	
	
	$.ajax({
		url:"/WH/user/tree",
		async:true,
		success:function(result){			
			$(".myTree").append(result.tree);
		}
	});
	
	$.ajax({
		url:"/WH/jsp/companyBaseInfo.jsp",
		async:true,
		success:function(dates){					
			$("#mainContent").html(dates);
		}
		
	});
	
});


