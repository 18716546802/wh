var f0601;
var f0602;
var f0603;
var f0604;
var pid;
var upNode;

$(document).ready(function(){
	var rowData = JSON.parse(localStorage.getItem("rowData"));
	f0601 = rowData.f0601;
	pid = rowData.id;

	if(rowData.f0604 == "二级节点"){
		document.getElementById("file_type2").checked ="checked";		
	}else if(rowData.f0604 == "一级节点"){
		rowData.f0603 = 0;
		document.getElementById("file_type1").checked = "checked";
	}
	
	
	
	$(":input[name='editRegionTree']").val(pid);
	$("#f0602").val(rowData.f0602);
	$("#f0603").val(rowData.f0603);

	
	
$("#submitFormEditRegion").click(function(){
	if(judgeSubmit()){
		$.ajax({
			type:"POST",
			url:"/WH/remind/updateTree",
			data:{
				f0601:f0601,
				f0602:f0602,
				f0603:upNode
			},
			success:function(data){
					window.location.href="/WH/jsp/regionManagement.jsp";	
			},	
			error:function(e){
					alert("修改失败");
			}
		})
	}
})
function judgeSubmit(){
	
	
	if(rowData.f0603 == 0){
		 upNode = 0;
	}else{
		 upNode = $(":input[name='editRegionTree']").val();
	}

	f0602=$("#f0602").val();
	f0603=$("#upNode").val();
	

	 
	
	if(f0602=="" || f0603=="" ){		
		alert("地区名字不能为空！");
		return false;
	}else{
		return true;	
		}
	}
})