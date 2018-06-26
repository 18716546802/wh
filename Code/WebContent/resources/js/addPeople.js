
var f0101;
var f0702;
var f0703;
var f0704;
var f0705;
var f0706;
var f0707;
var f0708;

$(document).ready(function(){
   
	f0101 = JSON.parse(localStorage.getItem("companyID"));  	
    var companyName =JSON.parse(localStorage.getItem("companyName")); 
    localStorage.setItem("companyName",JSON.stringify(companyName));
	
    $("#companyName").text(companyName);
    
	
	$("#submitAddPeople").click(function(){
		
		if(checkValue()){
			
			$.ajax({
				type:"POST",
				url:"/WH/person/addpersonQualification",
				data:{
					f0101:f0101,
					f0702:f0702,
					f0703:f0703,
					f0704:f0704,
					f0705:f0705,
					f0706:f0706,
					f0707:f0707,
					f0708:f0708,
				},
				success:function(data){
					if(data.success){
						localStorage.setItem("companyID",JSON.stringify(f0101));
		   	    		
						showPage("/WH/jsp/companyPeople.jsp");
						//window.location.href="/WH/jsp/companyPeople.jsp";						
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
	
	
	f0702=$("#f0702").val();
	f0703=$("#f0703").val();
	f0704=$("#f0704").val();
	f0705=$("#f0705").val();
	f0706=$("#f0706").val();
	f0707=$("#f0707").val();
	f0708=$("#f0708").val();
	
	
	 var str = f0704;
	 var str1=[];
	 for(var i=0;i<str.length;i++)
	{
		 if(str[i]=='/')
			 str1+='-';
		 else
			 str1+=str[i];
	} 
	 f0704=str1;
	 
	 if(f0702==""){
		 alert("姓名不能为空！");
		 return false;
	 }
	 if(f0704=="yyyy-mm-dd"){
		 alert("有效时间不能为空！");
		 return false;
	 }
	 else{
		
		return true;
	}
	
	
	
}

//判断时间格式
function checkTimeFormat(){

     var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
     var dempTime=$("#f0704").val();

     if(!reg.test(dempTime)){
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
    var dempTime=$("#f0704").val();
    if (!RQcheck(dempTime)) {
    return false;
    }else{
     return true;
    }
}


