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
		<title>修改地区</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->
		<link href="<%=path%>/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
		<!--ace styles -->
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-skins.min.css" />
		
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/assets/css/easyui.css"/>
 		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/assets/css/icon.css"/>
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/assets/css/demo.css"/>
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/reset.css" />
		
 
		<link rel="stylesheet" href="<%=path%>/resources/css/common.css" />
		<style>
		
			#addCompanyTable span{
					font-size: 15px;
				}
		</style>
		
</head>
		
	<body > 
	
	
		<jsp:include page="top.jsp" />
    	<jsp:include page="left.jsp" />		
    	
    	
    	<div class="main-content">
			<div class="title"    >
				<ul class="breadcrumb"   style="height:50px;">
					<li >
						<i class="icon-home home-icon"></i>
							<small style="font-size:22px;line-height:50px;">地区管理</small>
						</li>
					<li class="active" style="font-size:22px;color:#438eb9;">修改地区</li>

			   </ul>
	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div id="hider" class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
							修改地区
							</div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" >							 		 
										<table id="addCompanyTable" class="table table-striped table-bordered table-hover">									
											<tbody style="background: 100%;">
										 
												<tr class="commonStyle">
													<td style="background: white;width: 25%;">选择类型</td>
													<td style="background: white;" id="f0604">
														<input type="radio" style="width: 10%;" name="file_type" id="file_type1" value="1" disabled="disabled">一级节点
														<input type="radio" style="width:10%;" name="file_type" id="file_type2" value="2" disabled="disabled" checked>二级节点
													</td>											 
												</tr>
												
												
												
												<tr class="commonStyle" id="selectRegion">
													<td style="background: white;width: 25%;">请选择上级地区</td>
													<td style="background: white;" id="f0603">
													<select name="editRegionTree" class="easyui-combotree" data-options="url:'/WH/user/userTree?type=2',method:'GET',required:true" style="width:80%;"></select>													
													</td>													
												</tr>
												
												<tr class="commonStyle">
												<td style="background: white;"><span style="color:red">*</span>地区名字</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0602" ></td>

											</tr>
												
											</tbody>

									</table>
								
							 		<div style="height: 50px; display: block;text-align: center;margin: auto;" >
					 					<button  type="button" class="btn btn-large btn-primary " style="width: 45%;" id="submitFormEditRegion" >提交</button>
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
		<script src="<%=path%>/resources/assets/js/plupload.full.min.js"></script>
		
		<script src="<%=path%>/resources/assets/js/bootstrap.min.js"></script>
		<script src="<%=path%>/resources/assets/js/typeahead-bs2.min.js"></script>
		
		<script src="<%=path%>/resources/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
		<!-- ace scripts -->
		<script src="<%=path%>/resources/assets/js/ace-elements.min.js"></script>
		<script src="<%=path%>/resources/assets/js/ace.min.js"></script>		
			<script src="<%=path%>/resources/js/top.js"></script>
		<script src="<%=path%>/resources/js/left.js"></script>
		<script src="<%=path%>/resources/js/editRegion.js"></script>
	<script type="text/javascript">
		$(function(){
			 showCont();
			 $("input[name=file_type]").change(function(){
			  showCont();
			 });
			});
			function showCont(){
			 switch($("input[name=file_type]:checked").attr("id")){
			  case "file_type1":
				  $("#selectRegion").hide();
			 	  break;
			  case "file_type2":
			  	 $("#selectRegion").show();
			  	 break;
			  default:
			   break;
			 }
			}
	</script>
	
</body>
</html>