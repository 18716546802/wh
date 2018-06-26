var f0302;
var f0303;
var f0304;
var f0305;
var f0306;
var f0307;
var f0308;
var f0309;
var f0310;
var f0311;
var f0312;
var f0313;
var f0314;
var f0315;

$(document).ready(function() {

	
	var companyName =JSON.parse(localStorage.getItem("companyName"));
	$("#companyName").text(companyName);	
    var rowData=JSON.parse(localStorage.getItem("rowData"));

    var f0301=rowData.f0301;
    var f0101=rowData.f0101;
    
    $("#f0302").val(rowData.f0302);
    $("#f0303").val(rowData.f0303);
    $("#f0304").val(rowData.f0304);
    $("#f0305").val(rowData.f0305);
    $("#f0306").val(rowData.f0306);
    $("#f0307").val(rowData.f0307);
    $("#f0308").val(rowData.f0308);
    $("#f0309").val(rowData.f0309);
    $("#f0310").val(rowData.f0310);
    $("#f0311").val(rowData.f0311);
    $("#f0312").val(rowData.f0312);
    $("#f0313").val(rowData.f0313);
    $("#f0314").val(rowData.f0314);
    $("#f0315").val(rowData.f0315);
 
   
	$("#submitEditEquipment").click(function(){
			
		if(checkValue()){
			console.log(f0301);
			console.log(f0101);
			
		     $.ajax({    	 
		    	 type:"get",
		    	 url:"/WH/job/updateEquipment",
		    	 data:{
		    		 f0301:f0301,
		    		 f0101:f0101,
		    		 f0302:f0302,
		    		 f0303:f0303,
		    		 f0304:f0304,
		    		 f0305:f0305,
		    		 f0306:f0306,
		    		 f0307:f0307,
		    		 f0308:f0308,
		    		 f0309:f0309,
		    		 f0310:f0310,
		    		 f0311:f0311,
		    		 f0312:f0312,
		    		 f0313:f0313,
		    		 f0314:f0314,
		    		 f0315:f0315,
		    	 },
		    	 success:function(data){
		    		 if(data.success){
			   	         var companyID=f0101;  			   	    		
			   	    	 localStorage.setItem("companyID",JSON.stringify(companyID));
			   	    	 
			   	    	 showPage("/WH/jsp/companyEquipment.jsp");
			   	    	 // window.location.href="/WH/jsp/companyEquipment.jsp";	
		    		 }else{
		    			alert("修改失败"); 
		    		 }
		    		 
		    	 }
		    	 
		    	 
		     });
			
		}
	

	});

});



function checkValue() {

	f0302 = $("#f0302").val();
	f0303 = $("#f0303").val();
	f0304 = $("#f0304").val();
	f0305 = $("#f0305").val();
	f0306 = $("#f0306").val();
	f0307 = $("#f0307").val();
	f0308 = $("#f0308").val();
	f0309 = $("#f0309").val();
	f0310 = $("#f0310").val();
	f0311 = $("#f0311").val();
	f0312 = $("#f0312").val();
	f0313 = $("#f0313").val();
	f0314 = $("#f0314").val();
	f0315 = $("#f0315").val();
	
	 var clt = f0305;
	 var clt1=[];
	 for(var i=0;i<clt.length;i++)
	{
		 if(clt[i]=='/')
			 clt1+='-';
		 else
			 clt1+=clt[i];
	} 
	 f0305=clt1;

	 
	 var str = f0310;
	 var str1=[];
	 for(var i=0;i<str.length;i++)
	{
		 if(str[i]=='/')
			 str1+='-';
		 else
			 str1+=str[i];
	} 
	 f0310=str1;


	 if(f0302==""){
		 alert("设备名称不能为空！");
		 return false;
	 }
	 if(f0305=="yyyy-mm-dd"){
		 alert("投用日期不能为空！");
		 return false;
	 }
	 if(f0310=="yyyy-mm-dd"){
		 alert("生产日期不能为空！");
		 return false;
	 }
	 if(f0315==""){
		 alert("是否过期不能为空！");
		 return false;
	 }
	 else{
		return true;
	}

}



// 判断时间格式
function checkTimeFormat() {

	var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
	if (!reg.test(f0310) || !reg.test(f0305)) {
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

	if (!RQcheck(f0310) || !RQcheck(f0305)) {
		return false;
	} else {
		return true;
	}
}
