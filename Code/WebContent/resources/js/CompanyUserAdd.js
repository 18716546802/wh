
$(document).ready(function(){
 	$("#add").click(function(){	
 		var ipt = 	$(":input[name='companyUserTree1']").val();//获取企业id
 		console.log(ipt);
 		if(ipt==='')
		{
			alert("请先选择企业！");
		}
		else{
			
			if(ipt >=101000000 && ipt<=101999999){
				$("#add_grid-table").click();
				localStorage.setItem("ipt",ipt);
				window.location.href="/WH/jsp/addCompany.jsp";
			}else{
				alert("请选择正确的企业");
			}
		}
	});	
})