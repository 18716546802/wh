<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>  
		
		
		<!-- Basi Style -->
		<link href="<%=path%>/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/font-awesome.min.css" />			
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/jquery-ui-1.10.3.full.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/datepicker.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ui.jqgrid.css" />
	
		<!-- ace styles -->
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/css/common.css" />
		
		
		
 
			<div class="title"  >
				<ul class="breadcrumb" style="height:50px;"> 
					<li >
						<i class="icon-home home-icon"></i>
							<small style="font-size:22px;line-height:50px;">重大危险源</small>
						</li>
					<li class="active" id="companyName" style="font-size:22px;color:#438eb9;" ></li>


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
		
		
		
 		

		<!-- page specific plugin scripts -->
		<script src="<%=path%>/resources/assets/js/jquery-2.0.3.min.js"></script>		
		<!-- page specific plugin scripts -->		
		<script src="<%=path%>/resources/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/i18n/grid.locale-en.js"></script>		
		<!-- 企业重大危险源 -->
		<script src="<%=path%>/resources/js/companyBigDanger.js"></script>

