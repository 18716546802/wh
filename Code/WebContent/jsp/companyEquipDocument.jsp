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
<title>特种设备安全附件</title>

		<link href="<%=path%>/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
		
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/font-awesome.min.css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<link rel="stylesheet" href="<%=path%>/resources/assets/css/jquery-ui-1.10.3.full.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/datepicker.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ui.jqgrid.css" />

		<!-- fonts -->

		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/css/common.css" />
		
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
							<small  style="font-size:22px;line-height:50px;">特种设备安全文件</small>
						</li>
					<li class="active" id="companyName" style="font-size:22px;color:#438eb9;"></li>


			   </ul><!-- .breadcrumb -->
	   
				<div class="main-content"   >			
					<div class="page-content">			
						<div class="row" style="width:100%"  id="jqgrid">
							<div class="col-xs-12" >
								<table id="grid-table"  ></table>

								<div id="grid-pager"></div>

								<script type="text/javascript" >
									var $path_base = "/";//this will be used in gritter alerts containing images
								</script>

							</div>						
						</div>
						 
					 
					</div>
			</div> 			 			
		</div>

		<script src="<%=path%>/resources/assets/js/jquery-2.0.3.min.js"></script>
		<script src="<%=path%>/resources/assets/js/bootstrap.min.js"></script>
		<script src="<%=path%>/resources/assets/js/typeahead-bs2.min.js"></script>
		<!-- page specific plugin scripts -->
		<script src="<%=path%>/resources/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
		<!-- ace scripts -->
		<script src="<%=path%>/resources/assets/js/ace-elements.min.js"></script>
		<script src="<%=path%>/resources/assets/js/ace.min.js"></script>
		
		
		<script>
				var f0301=${param.f0301};
				
		</script>
		
	
		<!--设备文件 -->
 		<script src="<%=path%>/resources/js/companyEquipDocument.js"></script>
		<!-- common js -->
		<script src="<%=path%>/resources/js/top.js"></script>
		<script src="<%=path%>/resources/js/left.js"></script>
 
	
</body>
</html>