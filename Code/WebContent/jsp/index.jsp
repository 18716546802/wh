<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <base href="<%=basePath%>"></base> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" href="<%=path %>/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=path %>/resources/css/login.css">
	

<title>登录页面</title>
</head>
<body>

		<!--背景图片-->
		<div class="picBackground">
			<img style="position:fixed;"src="<%=path %>/resources/img/bac.jpg" height="100%" width="100%" />
		</div>

		<div class="container">

			<!--标题-->
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h1 class="panel-title">万汇安全</h1>
				</div>
			</div>
			<!--登陆表单 -->
			<form class="bs-example bs-example-form" method="get" role="form" >
				<div class="input-group">
					<span class="input-group-addon"> <span class="glyphicon   glyphicon-user"></span></span>
					<input type="text" class="form-control" placeholder="账号" class="textbox" style="width: 80%;"  id="username">
				</div>
				<label style="padding-top: 10px;"></label>
				
				<div class="input-group">
					<span class="input-group-addon "><span class="glyphicon glyphicon-lock"></span></span>
					<input  type="password" class="form-control" placeholder="密码" class="textbox" style="width: 80%;" id="password">
				</div>
				<label style="padding-top: 10px;"></label>
				<div class="input-group">
					<span class="input-group-addon "><span class="glyphicon glyphicon-pencil"></span></span>
					<input type="text" class="form-control" placeholder="验证码" class="textbox" style="width: 30%;" id="confrimCheckCode">
					<input type="text"  disabled="disabled" class="form-control" class="textbox" style="width: 40%;margin-left: 10%;letter-spacing:8px;text-align: center;font-weight: bold; " id="checkCode">
				</div>
				<div  style="padding-top: 20px;">		
					<button type="button" class="btn btn-primary" data-toggle="button" style="width: 83%;height: 45px;font-size: 20px;" id="btnLogin"> 
						登录
					</button>
				</div>
							
			</form>		
			<!--登录失败-->
			<div class="panel panel-danger" style="margin-top: 10px;" id="resultLogin">
				<div class="panel-heading" >
     				 <h3 class="panel-title" style="height: 15px;line-height: 15px;" ><div id="txtErro"></div></h3>
    			</div>	
			</div>
		
		</div>
		
		<script src="<%=path %>/resources/js/jquery-3.0.0.min.js"></script>
	
		<script src="<%=path %>/resources/js/login.js"></script>
		
	
</body>
</html>