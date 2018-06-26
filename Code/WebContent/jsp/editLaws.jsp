<%@page import="com.wh.model.T02"%>
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
		<title>修改法律法规文件</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->
		<link href="<%=path%>/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
		<!--ace styles -->
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-skins.min.css" />
 
		<link rel="stylesheet" href="<%=path%>/resources/css/common.css" />
		<style>
			#editUserTable,#hider{
				
				}
			#editUserTable span{
					font-size: 15px;
				}
		</style>
		
</head>
		<%-- <jsp:useBean id="stuSer" class="com.wh.service.UserService"></jsp:useBean> --%>
	<body > 
	<%-- 
	<% int id  = Integer.parseInt(request.getParameter("id"));
		/* T02 userInfo = stuSer */
	%> --%>
	
		<jsp:include page="top.jsp" />
    	<jsp:include page="left.jsp" />		

    	
    	<div class="main-content">
			<div class="title"    >
				<ul class="breadcrumb"   style="height:50px;">
					<li >
						<i class="icon-home home-icon"></i>
							<small style="font-size:22px;line-height:50px;">法律法规</small>
						</li>
					<li class="active" style="font-size:22px;color:#438eb9;">法律法规所有文件</li>

			   </ul><!-- .breadcrumb -->
	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div id="hider" class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
							修改法律法规
							</div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" runat="server"> 
										<table id="editUserTable" class="table table-striped table-bordered table-hover">									
											<tbody>
												
												<tr class="commonStyle">
													<td style="background: white;"><span style="color: red">*</span>文件名称</td>
													<td style="background: white;"><input type="text" style="width: 80%;text-align:center;" id="f0902"></td> 
													
												</tr>
										 
											
												<tr class="commonStyle">
													<td style="background: white;"><span style="color: red">*</span>文件类级别</td>
													<td style="background: white;">
														<select  style="width: 80%;text-align:center;" id="f0905">
															<option value="法律">法律</option>
														    <option value="行政法规">行政法规</option>
															<option value="部门规章">部门规章</option>
															<option value="地方法规和规章">地方法规和规章</option>
															<option value="文件公告">文件公告</option>
															<option value="国家标准">国家标准</option>
															<option value="行业标准">行业标准</option>
														</select>
													</td>
		
												</tr>
											
											</tbody>

									</table>
								
							 		<div style="height: 50px;" >
					 					<button  type="button" class="btn btn-large btn-primary " style="width: 45%;" id="submitFormWhUser">提交</button>
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
	   
		<script src="<%=path%>/resources/js/editLaws.js"></script>
		<script src="<%=path%>/resources/js/top.js"></script>
		<script src="<%=path%>/resources/js/left.js"></script>
	
</body>
</html>