
$(document).ready(function(){
 	$("#add").click(function(){	
 		var ipt = 	$(":input[name='governmentUserTree1']").val();//获取企业id
 		//console.log(iptl);
		if(ipt==='')
		{
			alert("请先选择地区！");
		}
		else{
			$("#add_grid-table").click();
			//document.getElementById('f0209').value=document.getElementById('_easyui_textbox_input1').value
			localStorage.setItem("ipt",ipt);
			window.location.href="/WH/jsp/addGovernment.jsp";
			
		}
	});	
})