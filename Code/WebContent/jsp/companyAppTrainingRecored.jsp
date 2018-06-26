<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" 
			href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" 
			integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" 
			crossorigin="anonymous">
			<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TrainingRecored</title>
</head>
<body>
	<div class="container">
			<div>
						<table class="table table-bordered">
			<tr>
					<th class="text-center">姓名</th>
					<th class="text-center">证书名称</th>
					<th class="text-center">证书编号</th>
					<th class="text-center">有效时间</th>
					<th class="text-center">颁发单位</th>
					<th class="text-center">状态</th>
			</tr>
			
			<c:forEach var="item" items="${items}">
			<tr>
					<td class="text-center">${item.f0702 }</td>
					<td class="text-center">${item.f0707 }</td>
					<td class="text-center">${item.f0703 }</td>
					<td class="text-center">
					<fmt:formatDate value="${item.f0704 }" type="date" pattern="yyyy/MM/dd"/>
					</td>
					<td class="text-center">${item.f0705 }</td>
					<td class="text-center">${item.f0706 }</td>		
			</tr>			
			
			</c:forEach>
			
			

	</table>
			</div>
	</div>
</body>
</html>