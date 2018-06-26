
var f5003;//固定
var f5004;//合并的人
var f5005;
var f5006;
var f5007;


$(document).ready(function(){

		
	var f0201=JSON.parse(localStorage.getItem("userID"));
	f5003="万汇业务员";
    $("#submitAddParameter").click(function(){
    	if(checkValue()){
    		
      		
    		
    		$.ajax({
    			type:"post",
    			url:"/WH/remind/addTodayP",
    			data:{
    				f0201:f0201,
    				f5003:f5003,
    				f5004:f5004,
    			},
    			success:function(data){
    				if(data.success){
    					
    					showPage("/WH/jsp/getAllParameter.jsp");
    					//window.location.href="/WH/jsp/getAllParameter.jsp";
    				}else{
    					alert("添加失败");
    				}
    			}
    		});  	 	
    	}
    	
    });
});


function checkValue(){
	
	f5005=$("#f5005").val();
	f5006=$("#f5006").val();
	f5007=$("#f5007").val();
	
	if(f5005==""){
		alert("业务员不能为空！");
		return false;
	}
	if(f5006==""){
		alert("专家不能为空！");
		return false;
	}
	if(f5007==""){
		alert("政府人员不能为空！");
		return false;
	}
	else{
		
		f5004=f5005+";"+f5006+";"+f5007;

		return true;
	}
	
	
}