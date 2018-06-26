<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>  

		
		
	
		<!-- basic styles -->
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
							<small id="companyName" style="font-size:22px;line-height:50px;"></small>
						</li>
					<li class="active" style="font-size:22px;color:#438eb9;">隐患详情</li>

			   </ul><!-- .breadcrumb -->
	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
								隐患详情
							</div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" runat="server" style="height: 500px;"> 
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
										 
										<tbody>
																		
											<tr class="commonStyle"  >
												<td style="  width: 25%;background: white;" >参检人员</td>
												<td style="background: white;width: 25%;" id="f0803"></td>	
												<td style="  width: 25%;background: white;">整改情况</td>
												<td style="background: white;" id="f0804">
													  												
												</td>	
											</tr>
											
											
											<tr class="commonStyle">
												<td style="background: white;">企业确认人员</td>
												<td style="background: white;" id="f0805"> </td>	 
						                        <td style="background: white;">复查人员</td>
												<td style="background: white;" id="f0806"> </td>	 
											</tr>
											
							   
	
											<tr class="commonStyle">
												<td style="background: white;">企业整改人员</td>
												<td style="background: white;" id="f0808"> </td>
												<td style="  width: 25%;background: white; ">整改类型</td>
												<td style="background: white;" id="f0809">
				 												
												</td>
												
											</tr>

											<tr class="commonStyle">
												<td style="background: white;">限期天数</td>
												<td style="background: white;" id="f0810"></td>	 
						                        <td style="background: white;">隐患发现时间</td>
												<td style="background: white;" id="f0811"> </td>	 
											</tr>
										 	
										 	<tr class="commonStyle">
												<td style="background: white;">隐患整改截止日期</td>
												<td style="background: white;" id="f0812"> </td>	 
						                        <td style="background: white;">是否重大危险源</td>
												<td style="background: white;" id="f0819">
										 
												</td>	 
											</tr>
											
											<tr class="commonStyle">	 
						                        <td style="background: white;" >隐患类别</td>
												<td style="background: white;"  colspan="3" id="f0820">
													  
												</td>	 
											</tr>
			  									
										
											 
																										
										</tbody>
										
										<tbody>
											
											<tr class="commonStyle">
						                        <td style="background: white;height: 100px;vertical-align: middle;text-align: center;">复查结果</td>											 
												<td colspan="3" style="background: white;width: 80%;" id="f0807">
													 
												</td>								 	 
											</tr>
											
										
										
											<tr class="commonStyle">
						                        <td style="background: white;height: 100px;vertical-align: middle;text-align: center;">隐患描述</td>											 
												<td colspan="3" style="background: white;width: 80%;" id="f0802">
													 
												</td>								 	 
											</tr>
											
											<tr class="commonStyle">
												<td style="background: white;height: 200px;vertical-align: middle;text-align: center;" >												
												 
													隐患图片
													 
												</td>
												<td style="background: white;" >
													<img id="upPicOne"  style="width: 100%;height: 200px;"/>
												</td>	 
						                        <td style="background: white;"> 
						                        	<img id="upPicTwo"  style="width: 100%;height: 200px;"/>
						                        </td>
												<td style="background: white;"> 
													<img id="upPicThree" style="width: 100%;height: 200px;"/>
												</td>	 
											</tr>
											
											<tr class="commonStyle">
												<td style="background: white;height: 200px;vertical-align: middle;text-align: center;"id="addPic">												
												 
													整改后的隐患图片
													 
												</td>
												<td style="background: white;" >
													<img id="upPicFour"  style="width: 100%;height: 200px;"/>
												</td>	 
						                        <td style="background: white;"> 
						                        	<img id="upPicFive"  style="width: 100%;height: 200px;"/>
						                        </td>
												<td style="background: white;"> 
													<img id="upPicSix" style="width: 100%;height: 200px;"/>
												</td>	 
											</tr>
											
											
			  																							
										</tbody>

										
									 
			
									</table>

																												 
								 	</form> 
								</div>
		 
							</div>

					</div>
	 	
				</div>
			 
			</div>
									 
				</div>
			</div> 			 			
			

    	
    
	
 	     
 
	
		<!-- 隐患详情-->
		<script src="<%=path%>/resources/js/companyHideDangerDetail.js"></script>
	 
	    	

 