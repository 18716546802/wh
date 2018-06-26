

$("#changeLocation").bind('click',function(){
	var f0101 = $("input[name='companyUserTree']").val();
	var f0601 = $("input[name='governmentUserTree']").val();
	
	if(f0101 == ''){
		alert("请选择你要修改地区的企业");
	}
	
	if(f0601 == ''){
		alert("请选择该企业要切换到的地区");
	}
	
	if(f0101>=101000000 && f0101 <= 101999999){
		$.post("/WH/job/changeLoac",{
			f0101:f0101,
			f0601:f0601},
			function(data){
				alert(data.error);
				 window.location.reload();
			});
	}else{
		alert("请选择正确的企业");
	}
});