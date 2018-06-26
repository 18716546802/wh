var f0601;
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
var f0125;
var f0112;
var rowData;



$(document).ready(function(){
		
	
	var  companyID = JSON.parse(localStorage.getItem("companyID"));  

	 $.ajax({
			
			type:"get",
			url:"/WH/job/findEquipmentInfo",
			data:{f0101:companyID},
			success:function(data){		
				
				console.log(data);
				
				if(data.success){
					rowData=data.data;
					showData(rowData);	
					
				}else{
									
				}
			}	
		});

});


function showData(rowData){

	
	 if(rowData.f0102!=null)
	 $("#f0102").text(rowData.f0102);
	 else
	 $("#f0102").text("无");
	 
	 if(rowData.f0104!=null)
	 $("#f0104").text(rowData.f0104);
	 else
	 $("#f0104").text("无");		 
	 
	 if(rowData.f0104!=null)
	 $("#f0103").text(rowData.f0103);
	 else
	 $("#f0103").text("无");
	 
	 if(rowData.f0105!=null)
	 $("#f0105").text(rowData.f0105);//成立日期
	 else
	 $("#f0105").text("无");	 
	 
	 if(rowData.f0108!=null)
	 $("#f0108").text(rowData.f0108);
	 else
	 $("#f0108").text("无");
	 
	 
	 if(rowData.f0107!=null)
	 $("#f0107").text(rowData.f0107);
	 else
	 $("#f0107").text("无");
	 
	 if(rowData.f0110!=null)
	 $("#f0110").text(rowData.f0110);
	 else
	 $("#f0110").text("无");
	 
	 if(rowData.f0111!=null)
	 $("#f0111").text(rowData.f0111);
	 else
	 $("#f0111").text("无");
	 
	 if(rowData.f0108!=null)
     $("#f0108one").text(rowData.f0108);
	 else
	 $("#f0108one").text("无");		
	 
	 if(rowData.f0109!=null)
	 $("#f0109").text(rowData.f0109);
	 else
	 $("#f0109").text("无");
	 
	 if(rowData.f0128!=null)
	 $("#f0128").text(rowData.f0128);
	 else
	  $("#f0128").text("无");
	 
	 if(rowData.f0130!=null)
	 $("#f0130").text(rowData.f0130);//上次检查时间
	 else
	 $("#f0130").text("无");//上次检查时间
		
	 
	 if(rowData.f0121!=null)
	 $("#f0121").text(rowData.f0121);
	 else
	 $("#f0121").text("无");
	 
	
	 
	 if(rowData.f0122!=null)	 
	 $("#f0122").text(rowData.f0122);
	 else
	 $("#f0122").text("无");
	 
	 
	 
	 
	 
	 if(rowData.f0123!=null)
	 $("#f0123").text(rowData.f0123);
	 else
	 $("#f0123").text("无");
	 
	 
	 if(rowData.f0124!=null)
	 $("#f0124").text(rowData.f0124);
	 else
	 $("#f0124").text("无");
	 
	 if(rowData.f0116!=null)
	 $("#f0116").text(rowData.f0116);
	 else
	 $("#f0116").text("无");		
	 
	 
	 if(rowData.f0117!=null)
	 $("#f0117").text(rowData.f0117);
	 else
	 $("#f0117").text("无");
	
	 
	 
	 if(rowData.f0126!=null)
	 $("#f0126").text(rowData.f0126);
	 else
	 $("#f0126").text("无");
	 
	 if(rowData.f0127!=null){
		 
		 $("#f0127").text(rowData.f0127);

	 }else{
		 
		 $("#f0127").text("无");
	 }
	 
	 if(rowData.f0125!=null){
		 $("#f0125").text(rowData.f0125);
	 }
	 if(rowData.f0112!=null){
		 $("#f0112").text(rowData.f0112);
	 }
	 
	
}
