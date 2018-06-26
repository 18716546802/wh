<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>  

		
		<!-- basic styles -->
		<link href="<%=path%>/resources/assets/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet">
		
		
		<link href="<%=path%>/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
		<!--ace styles -->
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=path%>/resources/assets/css/ace-skins.min.css" />
 
		<link href="<%=path%>/resources/css/registerCommon.css"  rel="stylesheet"/>
		<link rel="stylesheet" href="<%=path%>/resources/css/common.css" />
		
    	

			<div class="title"    style=" "  >
				<ul class="breadcrumb"   style="height:50px;">
					<li >
						<i class="icon-home home-icon"></i>
						<small style="font-size:22px;line-height:50px;" >添加参数</small>
					</li>
			

			   </ul><!-- .breadcrumb -->
	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
								添加参数
							</div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" runat="server" style="height: 500px;"> 
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
										 <tbody>
											<tr class="commonStyle"  >
												<td style="  width: 25%;background: white;"><span style="color: red">*</span>业务员</td>
												<td style="background: white;"><input type="text" style="width: 80%;" placeholder="如果有多人请用 ',' 隔开"  id="f5005"></td>
											</tr>

											<tr class="commonStyle">
												<td style="background: white;"><span style="color: red">*</span>专家</td>
												<td style="background: white;"><input type="text" style="width: 80%;" placeholder="如果有多人请用 ',' 隔开" id="f5006" ></td>	 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;"><span style="color: red">*</span>政府人员</td>
												<td style="background: white;"><input type="text" style="width: 80%;" placeholder="如果有多人请用 ',' 隔开" id="f5007" ></td>	 
											</tr>	 
							
										</tbody>
		
									</table>
								
							 		<div style="height: 100px;padding-top:50px;" >
					 					<button  type="button" class="btn btn-large btn-primary " style="width: 45%;" id="submitAddParameter">提交</button>
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
			

    	
    
		
		<script src="<%=path%>/resources/assets/js/jquery-2.0.3.min.js"></script>
		<!-- <![endif]-->
		<script src="<%=path%>/resources/assets/js/bootstrap.min.js"></script>
		<!-- page specific plugin scripts -->
		<script src="<%=path%>/resources/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
		<!-- ace scripts -->		
		<script src="https://cdn.bootcss.com/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.zh-CN.min.js"></script>		 	 			
		<!-- 添加参数-->
		<script src="<%=path%>/resources/js/addParameter.js"></script>


	    	

