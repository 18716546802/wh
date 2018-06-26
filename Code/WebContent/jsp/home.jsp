<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理页面</title>
		<!-- basic styles -->
		<link href="<%=path%>/resources/assets/css/bootstrap.min.css" rel="stylesheet" />	
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/font-awesome.min.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/css/common.css" />
	
		

</head>
<body>
 		
 		 <jsp:include page="top.jsp" />
   		 <jsp:include page="left.jsp" />
   		 
   		 <div class="main-content" id="mainContent">
			 
		 </div>
		
 		 
 		<!-- ace settings handler -->
		<script src="<%=path%>/resources/assets/js/ace-extra.min.js"></script>	
		<script src="<%=path%>/resources/assets/js/jquery-2.0.3.min.js"></script>
		<script src="<%=path%>/resources/assets/js/bootstrap.min.js"></script>
		<script src="<%=path%>/resources/assets/js/typeahead-bs2.min.js"></script>
		<!-- ace scripts -->
		<script src="<%=path%>/resources/assets/js/ace-elements.min.js"></script>
		<script src="<%=path%>/resources/assets/js/ace.min.js"></script>
	 	<!-- common js -->
		<script src="<%=path%>/resources/js/top.js"></script>
		<script src="<%=path%>/resources/js/left.js"></script>
 

 
	
</body>
</html>