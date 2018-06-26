
var username;//账号
var password;//密码
var confrimCheckCode;//验证验证码
var checkCode;//验证码


$(document).ready(function(){
	
	$("#resultLogin").hide();		
	//生成验证码
	createCode();
	//登录
	$("#btnLogin").click(function(){	
		username=$("#username").val();
		password=$("#password").val();	
	    confrimCheckCode=$("#confrimCheckCode").val().toUpperCase();
		//txt不能为空 账号密码不能小于5
		if(username==""){		
			
			if(username==""){
				$("#txtErro").html('登录失败,账号为空');
			}
			$("#resultLogin").show();			
			return;
		}
		else if(password==""){	
			$("#txtErro").html('登录失败,密码为空');
			$("#resultLogin").show();			
			return;		
		}
		else if(confrimCheckCode=="" || confrimCheckCode!=checkCode){
			if(confrimCheckCode==""){
				$("#txtErro").html('登陆失败，验证码为空');
			}
			else{
				createCode();
				$("#txtErro").html('登陆失败，验证码错误');
				 
			}
			$("#resultLogin").show();			
			return;	
		}		
		//请求
		$.ajax({
            type:"get",
            url:"user/login",
            data:{username:username,password:password},
            dataType:"text",
            beforeSend:function(){
             },
            success:function(data){           	
                //判断输入是否成功，成功则跳转          	
            		var data = JSON.parse(data);
            		if(data.success){
            			$("#resultLogin").show();   
  
            			var userName=data.data.f0204;   		
            			localStorage.setItem("userName",JSON.stringify(userName));  
            			var f0201=data.data.f0201;
            			localStorage.setItem("userID",JSON.stringify(f0201));
            			
            		//	console.log(f0201);
            			window.location.href="././jsp/home.jsp";

            		}
            		else{
            			createCode();
            			$("#txtErro").html('登录失败,账号密码不存在');
            			$("#resultLogin").show(); 
            	 
            		}
            }
        });		
	});
	
});


//生成验证码
function createCode(){
	
	$("#checkCode").val("");
	 code = "";   
     var codeLength = 4;//验证码的长度  
	 var code;
     var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
     'S','T','U','V','W','X','Y','Z');//随机数  
     for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）  
        code += random[index];//根据索引取得随机数加到code上  
    }  
    checkCode=code;
	$("#checkCode").val(code);
}
