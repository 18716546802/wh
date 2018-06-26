var f0301;
var f0401;
var f0402;
var f0403;
var f0404;
var f0405;
var f0406;
var f0407;
var f0408;





$(document).ready(function() {

	var companyName = JSON.parse(localStorage.getItem("companyName"));
	$("#companyName").text(companyName);
	f0301=JSON.parse(localStorage.getItem("f0301"));
	
	$("#submitEquipDocument").click(function(){
		if(checkValue()){
			$.ajax({			
				type:"POST",
				url:"/WH/lowcard/addCheckInfo",
				data:{
					f0301:f0301,
					f0402:f0402,
					f0403:f0403,
					f0404:f0404,
					f0405:f0405,
					f0406:f0406,
					f0407:f0407,
					f0408:f0408,
				},
				success:function(data){
					if(data.success){
						localStorage.setItem("f0301",JSON.stringify(f0301));
						localStorage.setItem("companyName",JSON.stringify(companyName));
						showPage("/WH/jsp/companyEquipDocument2.jsp");
						//window.location.href="/WH/jsp/companyEquipDocument.jsp?f0301="+f0301;				 
					}else{
						
						alert("添加失败");
					}				
				}
			});
		}
	});

});

function checkValue() {
		
	 f0402=$("#f0402").val();
	 f0403=$("#f0403").val();
	 f0404=$("#f0404").val();
	 f0405=$("#f0405").val();
	 f0406=$("#f0406").val();
	 f0407=$("#f0407").val();
	 f0408=$("#f0408").val();
	 
	 var clt = f0403;
	 var clt1=[];
	 for(var i=0;i<clt.length;i++)
	{
		 if(clt[i]=='/')
			 clt1+='-';
		 else
			 clt1+=clt[i];
	} 
	 f0403=clt1;

	 
	 var str = f0407;
	 var str1=[];
	 for(var i=0;i<str.length;i++)
	{
		 if(str[i]=='/')
			 str1+='-';
		 else
			 str1+=str[i];
	} 
	 f0407=str1;
	 
	 if(f0402==""){
		 alert("安全附件名称不能为空！");
		 return false;
	 }
	 if(f0403=="yyyy-mm-dd"){
		 alert("安全附件检测时间不能为空！");
		 return false;
	 }
	 if(f0407=="yyyy-mm-dd"){
		 alert("下次检测时间不能为空！");
		 return false;
	 }
	 
	 else{
		 
		 return true;
	 }
 
}


//判断时间格式
function checkTimeFormat(){

   var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
   if(!reg.test(f0403) || !reg.test(f0407)){	   
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
   
  if (!RQcheck(f0403) || !RQcheck(f0407))  {
  return false;
  }else{
   return true;
  }
}

