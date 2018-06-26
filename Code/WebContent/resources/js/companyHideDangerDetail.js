
var rowData;


$(document).ready(function(){
	
	
	var companyName=JSON.parse(localStorage.getItem("companyName"));  
	
	var f0801=JSON.parse(localStorage.getItem("companyID"));
	
	$("#companyName").text(companyName);
	
	$.ajax({
		url:"/WH/remind/findOnePurkingPeril",
		type:"get",
		data:{
			f0801:f0801,
		},
		success:function(data){
			rowData=data.data;
			showData(rowData);
		}
	});
	
	
});


function showData(rowData){
	
	console.log(rowData);
	
	 console.log(rowData.f0802);
	 $("#f0802").text(rowData.f0802);
	 $("#f0803").text(rowData.f0803);
	 $("#f0804").text(rowData.f0804);
	 $("#f0805").text(rowData.f0805);
	 $("#f0806").text(rowData.f0806);
	 $("#f0807").text(rowData.f0807);
	 $("#f0808").text(rowData.f0808);
	 $("#f0809").text(rowData.f0809);
	 $("#f0810").text(rowData.f0810);
	 $("#f0811").text(rowData.f0811);
	 $("#f0812").text(rowData.f0812);
	 $("#f0819").text(rowData.f0819);
	 $("#f0820").text(rowData.f0820);
	 
	 console.log("1");
 
 
	 
	 if(rowData.f0813!="")
	 $("#upPicOne").attr('src',rowData.f0813);
	 if(rowData.f0814!="")
	 $("#upPicTwo").attr('src',rowData.f0814);
	 if(rowData.f0815!="")
	 $("#upPicThree").attr('src',rowData.f0815);
	 
	 if(rowData.f0816!="")
	 $("#upPicFour").attr('src',rowData.f0816);
	 if(rowData.f0817!="")
	 $("#upPicFive").attr('src',rowData.f0817);
	 if(rowData.f0818!="")
	 $("#upPicSix").attr('src',rowData.f0818);
	
}