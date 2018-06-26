
var f0101;
var f0502;
var f0503;
var f0504;
var f0505;

$(document).ready(function(){
    f0101 = JSON.parse(localStorage.getItem("companyID"));  	
    var companyName =JSON.parse(localStorage.getItem("companyName")); 
	
    $("#companyName").text(companyName);
    
    console.log(companyName);
	
	$("#submitAddLicence").click(function(){
		
		if(checkValue()){

			$.ajax({
				type:"post",
				url:"/WH/Qualification/addQinfo",
				data:{
					f0101:f0101,
					f0502:f0502,
					f0503:f0503,
					f0504:f0504,
					f0505:f0505
				},
				success:function(data){	
					if(data.success){
						localStorage.setItem("companyID",JSON.stringify(f0101));
		   	    								
						showPage("/WH/jsp/companyLicence.jsp");
						
						//window.location.href="/WH/jsp/companyLicence.jsp";
					}else{
						alert("添加失败");
					}
					 
				}
				
				
			});

		}
		
	});
	
	
});

//判断是否为空

function checkValue(){
	
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




