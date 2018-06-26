
var f0202;
var f0203;
var f0204;
var f0205;
var f0206;
var f0207;
var f0208;
var f0209;


$(document).ready(function(){ 
	$("#submitFormAddCompanyUser").click(function(){
		
		if(checkValue()){
			$.ajax({
				type:"POST",
				url:"/WH/user/addUser",
				data:{
					
					f0202:f0202,
					f0203:f0203,
					f0204:f0204,
					f0205:f0205,
					f0206:f0206,
					f0207:f0207,
					f0208:f0208,
					f0209:localStorage.getItem("ipt")
				},
				success:function(data){	
					if(data.success){
						window.location.href="/WH/jsp/company.jsp";
					}else{
						alert("用户名和密码有重复，请使用其他用户名或密码");
					}
				},
				error:function(e){
						alert("添加失败！")
						
				}
				
				
			});

		}
		
	});
});
function checkValue(){
	
	f0202=$("#f0202").val();
	f0203=$("#f0203").val();
	f0204=$("#f0204").val();
	f0205=$("#f0205").val();
	f0206=$("#f0206").val();
	f0207=$("#f0207").val();
	f0208=$("#f0208").val();
	f0209=$("#f0209").val();
	
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

