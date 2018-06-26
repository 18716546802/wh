<%@page import="org.aspectj.weaver.ast.Var"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- 		<base href="<%=basePath%>"></base> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加政府用户</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->
		<link href="<%=path%>/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
		<!--ace styles -->
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-skins.min.css" />
 
<%-- 		<link rel="stylesheet" href="<%=path%>/resources/css/common.css" /> --%>
		<style>
			#addGovernmentTable,#hider{
					
				}
			#addGovernmentTable span{
					font-size: 15px;
				}
		</style>
		
</head>
		
	<body > 
	
		<jsp:include page="top.jsp" />
    	<jsp:include page="left.jsp" />		
    	
    	
    	<div class="main-content">
			<div class="title"    >
				<ul class="breadcrumb" style="height:50px;">
					<li >
						<i class="icon-home home-icon"></i>
							<small style="font-size:22px;line-height:50px;">政府列表</small>
						</li>
					<li class="active" style="font-size:22px;color:#438eb9;">添加政府用户</li>

			   </ul>
	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div id="hider" class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
							添加政府用户
							</div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" runat="server"> 
										<table id="addGovernmentTable" class="table table-striped table-bordered table-hover">									
											<tbody>
												
												<tr class="commonStyle">
													<td style="background: white;"><span style="color: red">*</span>登陆账号</td>
													<td style="background: white;"><input type="text" style="width: 80%;text-align:center;" id="f0202"></td> 
													
												</tr>
										 
												<tr class="commonStyle">
													<td style="background: white;"><span style="color: red">*</span>用户登陆密码</td>
													<td style="background: white;"><input type="text" style="width: 80%;text-align:center;" id="f0203"></td>											 
													
												</tr>
											
												<tr class="commonStyle">
													<td style="background: white;"><span style="color: red">*</span>用户真实姓名</td>
													<td style="background: white;"><input type="text" style="width: 80%;text-align:center;" id="f0204"></td>
													
												</tr>
												
												<tr class="commonStyle">
													<td style="background: white;">用户类型</td>
													<td style="background: white;"><input type="text" style="width: 80%;text-align:center;" id="f0205" value="政府" readonly="readonly"></td>
													
												</tr>
											
												<tr class="commonStyle">
													<td style="background: white;"><span style="color: red">*</span>是否是管理员</td>
													<td style="background: white;">
														<select id="f0206">
															<option value="请选择是否为管理员">请选择是否为管理员</option>
															<option value="是">是</option>
															<option value="否">否</option>
														</select>
													</td>
												</tr>
												
											
												<tr class="commonStyle">
													<td style="width: 25%;background: white;">联系电话1</td>
													<td colspan="3" style="background: white;"><input type="text" style="width: 80%;text-align:center;" id="f0207"></td>
												</tr>
											 
												<tr class="commonStyle">
													<td style="width: 25%;background: white;">联系电话2</td>
													<td colspan="3" style="background: white;"><input type="text" style="width: 80%;text-align:center;" id="f0208"></td>
												</tr>
											</tbody>

									</table>
								
							 		<div style="height: 50px;" >
					 					<button  type="button" class="btn btn-large btn-primary " style="width: 45%;" id="submitFormAddGovernmentUser">提交</button>
    									<button  type="reset" class="btn btn-large " style="width: 45%;margin-left: 9.5%;">重置</button>							
									</div>
							 	</form> 
							</div>
		 
						</div>

					</div>
	 
				</div>
			 
			</div>
									 
				</div>
			</div> 			 			
			
	</div>
    	
    
	
 	   <script src="<%=path%>/resources/assets/js/jquery-2.0.3.min.js"></script>
		<!-- <![endif]-->
		<script src="<%=path%>/resources/assets/js/bootstrap.min.js"></script>
		<script src="<%=path%>/resources/assets/js/typeahead-bs2.min.js"></script>
		<!-- page specific plugin scripts -->
		<script src="<%=path%>/resources/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
		<!-- ace scripts -->
		<script src="<%=path%>/resources/assets/js/ace-elements.min.js"></script>
		<script src="<%=path%>/resources/assets/js/ace.min.js"></script>		
		<!-- common js -->
		<script src="<%=path%>/resources/js/addGovernmentUser.js"></script>
		<script src="<%=path%>/resources/js/top.js"></script>
		<script src="<%=path%>/resources/js/left.js"></script>
	   

	
</body>
</html>