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
<title>政府列表</title>

		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"  />
		
		
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/assets/css/easyui.css"> 
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/assets/css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/assets/css/demo.css"> 
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/reset.css" />
		
		<link href="<%=path%>/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/jquery-ui-1.10.3.full.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/datepicker.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ui.jqgrid.css" />

		<!-- fonts -->
     
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->

		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-skins.min.css" />
		

		<script src="<%=path%>/resources/assets/js/ace-extra.min.js"></script>
	</head>

	<body>
		
		
		
		<jsp:include page="top.jsp" />
   		 <jsp:include page="left.jsp" />
   		  <div class="main-content"  >
			<div class="title"    style=" "  >
				<ul class="breadcrumb"   style="height:50px;">
					<li >
						<i class="icon-home home-icon"></i>
							<small style="font-size:22px;line-height:50px;">政府列表</small>
						</li>
					<li class="active" style="font-size:22px;color:#438eb9;">政府基本信息</li>

			   </ul>
				<div class="main-content" >	
			      <div class="page-content">	
			   		<div class="row" style="width:100%" >
							<div class="col-xs-12" >
							
								<select name="governmentUserTree1" class="easyui-combotree" data-options="url:'/WH/user/userTree?type=2',method:'get',required:true" style="width:200px;"></select>
								<button id="add" class="btn btn-primary btn-xs">添加用户</button>
								<table id="grid-table"></table>

							
							<div id="grid-pager"></div>
								<script type="text/javascript">
									var $path_base = "/";//this will be used in gritter alerts containing images
								</script>				
							</div>
						</div>
					  </div>
					</div>
				</div>
				</div>	 	
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		

		<!-- basic scripts -->


		<script src="<%=path%>/resources/assets/js/jquery-2.0.3.min.js"></script>




		<!-- <script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='resources/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script> -->
		
		<script src="<%=path%>/resources/assets/js/bootstrap.min.js"></script>
		<script src="<%=path%>/resources/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<script src="<%=path%>/resources/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/i18n/grid.locale-en.js"></script>

		<!-- ace scripts -->

		<script src="<%=path%>/resources/assets/js/ace-elements.min.js"></script>
		<script src="<%=path%>/resources/assets/js/ace.min.js"></script>
		
		
		
		
		<!-- inline scripts related to this page -->

		
		<style>
			#jqgh_grid-table_f0209,#jqgh_grid-table_f0202,#jqgh_grid-table_f0203,#jqgh_grid-table_f0204,#jqgh_grid-table_f0205,
			#jqgh_grid-table_f0206,#jqgh_grid-table_f0207,#jqgh_grid-table_f0208{
				text-align: center;
			}
			table #dept{
				/*display: none;*/
			}
			
			.DataTD #wh_name{
				width: 171.6px;
			}
			
			select {
				width: 85.8px;
			}
			#add_grid-table{
				display:none;
			}
			
		</style>
		<script src="<%=path%>/resources/js/governmentUser.js"></script>
		<script src="<%=path%>/resources/js/governmentUserAdd.js"></script>
		<script src="<%=path%>/resources/js/top.js"></script>
		<script src="<%=path%>/resources/js/left.js"></script>
		
	
</body>
</html>
