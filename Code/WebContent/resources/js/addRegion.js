
var f0601;
var f0602;
var f0603;
var type;


$(document).ready(function(){
	$("#submitFormAddRegion").click(function(){
		
		if(checkValue()){

			$.ajax({
				type:"POST",
				url:"/WH/remind/addTree",
				data:{
					f0601:f0601,
					f0602:f0602,
					f0603:$(":input[name='regionTree']").val(),
					type:$("input[name='file_type']:checked").val()
				},
				success:function(data){	
					if(data.success){
		   	    		window.location.href="/WH/jsp/regionManagement.jsp";
					}else{
						alert("添加失败");
					}
					 
				}
				
				
			});

		}
		
	});
		
});
function checkValue(){
	
	f0602=$("#f0602").val();
	f0603=$("#f0603").val();
	type=$("#type").val();

	if(f0602==""|| f0603==""||type=="" ){
		alert("地区名字不能为空");
		return false;
	}else{
		return true;
		}
}

