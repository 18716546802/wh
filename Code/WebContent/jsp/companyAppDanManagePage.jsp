<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" 
			href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" 
			integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" 
			crossorigin="anonymous">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>RiskManage</title>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">整改提交</h4>
      </div>
      
      <div class="modal-body center">
				<form action="#" method="post" enctype="multipart/form-data"> 
						<div class="form-group">
									<label style="margin: 5px">整改描述：</label>
       								<textarea id="describle" rows="3"  class="form-control" name="describle"></textarea>
						</div>
						
						<div class="form-group">
						       		<label>图片：</label>
       								<input type="file" name="image">
						</div>
						
<!--        					<div class="text-center"> -->
<!--        								<button class="btn btn-info">提交</button> -->
<!--        					</div>		 -->


       					 <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						        <button type="submit" class="btn btn-info submitInfo">提交</button>
     					 </div>			
				</form>
      </div>
    </div>
  </div>
</div>

	<div class="container">
		<div>
					<select class="form-control" id="selid">
						<option></option>
						<option>所有隐患</option>
						<option>安全管理</option>
						<option>总图运输</option>
						<option>生产工艺</option>
						<option>工艺设备</option>
						<option>特种设备</option>
						<option>安全设施</option>
						<option>电气</option>
						<option>消防</option>
						<option>其他</option>
					</select>
					
					<table class="table table-bordered"  style="margin-top: 20px">
						<tr>
								<th class="text-center">隐患情况</th>
								<th class="text-center" >发现时间</th>
								<th class="text-center">期限</th>
								<th class="text-center">操作</th>
						</tr>
						
						
						<c:forEach var="item" items="${items }">
								<tr>
										<td class="text-center">${item.f0802}</td>
										<td class="text-center">
											<fmt:formatDate value="${item.f0811}" type="date" pattern="yyyy/MM/dd"/>
										</td>
										<td class="text-center">
											${item.f0810}
										</td>
										<td class="text-center">
										<span style="display: none;">${item.f0801 }</span>
										<button class="btn btn-info btnSubmit"  data-toggle="modal" data-target="#myModal">提交整改</button></td></td>
								</tr>						
						</c:forEach>
						
					</table>
			
		</div>
	
	</div>
	<span id="enterId"  style="display: none;">${f0101}</span>
	
</body>
<script type="text/javascript">
		var serverName="http://localhost:8080";
		var enterId = $("#enterId").text();
		var id;
	$(".btnSubmit").bind("click",function(){
		id = $(this).prev().text();
	});
	
	$("#selid").change(function(){
		var item = $("#selid option:selected");
		window.location.href=serverName +"/WH/companyApp/riskPage?id="+enterId+"&danName="+item.text();
	});
	
</script>
</html>