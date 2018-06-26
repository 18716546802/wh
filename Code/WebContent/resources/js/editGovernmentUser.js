var f0201;
var f0202;
var f0203;
var f0204;
var f0205;
var f0206;
var f0207;
var f0208;


$(document).ready(function(){
	var rowData = JSON.parse(localStorage.getItem("rowData"));
	

	f0201 = rowData.f0201;
	f0202 = rowData.f0202;
	
	$("#f0203").val(rowData.f0203);
	$("#f0204").val(rowData.f0204);
	$("#f0205").val(rowData.f0205);
	$("#f0206").val(rowData.f0206);
	$("#f0207").val(rowData.f0207);
	$("#f0208").val(rowData.f0208);

	if(rowData.f0206 == "是"){
		$("#f0206").prop("checked",true);
	}else{
		$("#f0206").prop("checked",false);
	}
	
	
	
$("#submitFormGovernmentUser").click(function(){
	if(judgeSubmit()){
		$.ajax({
			type:"POST",
			url:"/WH/user/updateUserInfo",
			data:{
				f0201:f0201,
				f0202:f0202,
				f0203:f0203,
				f0204:f0204,
				f0205:f0205,
				f0206:f0206,
				f0207:f0207,
				f0208:f0208

			},
			success:function(data){
					window.location.href="/WH/jsp/government.jsp";	
			},	
			error:function(e){
					alert("修改失败");
			}
		})
	}
})
function judgeSubmit(){
	
	f0202=$("#f0202").val();
	f0203=$("#f0203").val();
	f0204=$("#f0204").val();
	f0205=$("#f0205").val();
	f0206=$("#f0206").val();
	f0207=$("#f0207").val();
	f0208=$("#f0208").val();

	
	if(f0202==""){
		alert("登陆账号不能为空！");
		return false;
	}
	if(f0203==""){
		alert("用户登录密码不能为空！");
		return false;
	}
	if(f0204==""){
		alert("用户真实姓名不能为空！");
		return false;
	}
	if(f0206=="请选择是否为管理员"){
		alert("清选择是否为管理员！");
		return false;
	}
	else{
		return true;
		}
	}
})