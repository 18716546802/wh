<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>  

    	
  
		<div class="title"  >
				<ul class="breadcrumb"  style="height:50px;">
					<li >
						<i class="icon-home home-icon"></i>
							<small style="font-size:22px;line-height:50px;">企业详情</small>
					</li>
		
			   </ul><!-- .breadcrumb -->

	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
									企业详情
							</div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" runat="server" style="height: 500px;"> 
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
														
												<tbody>
											<tr class="commonStyle">
												<td style="background: white;width: 25%;">企业名称</td>
												<td style="background: white;width: 25%;" id="f0102"></td>
												<td style="background: white;width: 25%;">企业安全等级</td>
												<td style="background: white;width: 25%;" id="f0112"></td>
											</tr>
												<tr class="commonStyle">
													<td style="background: white;width: 25%;">经济性质</td>
												<td style="background: white;width: 25%;"  id="f0104"></td>
						 						<td style="background: white;width: 25%;width: 25%;">行业类别</td>
												<td style="background: white;width: 25%;" id="f0125"></td>
												</tr>
							
											<tr class="commonStyle" >
												<td style="background: white;width: 25%;">成立日期</td>
												<td style="background: white;width: 25%;"  id="f0105"></td>
												<td style="background: white;width: 25%;">法定代表人</td>
												<td style="background: white;width: 25%;" id="f0108one"></td>
							
											</tr>
	
											<tr class="commonStyle"  >
												<td style="width: 25%;background: white;">通讯地址</td>
												<td colspan="3" style="background: white;" id="f0107"> </td>									
											</tr>
											<tr class="commonStyle">
												<td style="background: white;width: 25%;">安全负责人</td>
												<td style="background: white;width: 25%;" id="f0110"> </td>			 
												<td style="background: white;width: 25%;">联系电话</td>
												<td style="background: white;width: 25%;" id="f0111"></td>
											</tr>
											<tr class="commonStyle">
												<td style="background: white;width: 25%;">安全管理人员</td>
												<td style="background: white;width: 25%;" id="f0108"></td> 
												<td style="background: white;width: 25%;">联系电话</td>
												<td style="background: white;width: 25%;" id="f0109"> </td>
											</tr>
										 
											<tr class="commonStyle">
												<td style="background: white;width: 25%;">重点监管类别</td>
												<td style="background: white;width: 25%;" id="f0128"> </td>											 
												<td style="background: white;width: 25%;">上次检查时间</td>
												<td style="background: white;width: 25%;" id="f0130"> </td>
											</tr>
											
											<tr class="commonStyle">
												<td style="background: white;width: 25%;">安全“三同时”</td>
												<td style="background: white;width: 25%;" id="f0121"></td>
												<td style="background: white;width: 25%;">职卫“三同时”</td>
												<td style="background: white;width: 25%;" id="f0122"> </td>
											</tr>
											
											<tr class="commonStyle">
												<td style="background: white;width: 25%;">安全标准化</td>
												<td style="background: white;width: 25%;" id="f0123"></td>
												<td style="background: white;width: 25%;">应急预案</td>
												<td style="background: white;width: 25%;" id="f0124"></td>
											</tr>
											
											
											<tr class="commonStyle">
												<td style="width: 25%;background: white;">主要原辅材料</td>
												<td colspan="3" style="background: white;" id="f0126"> </td>
											</tr>
										 
											<tr class="commonStyle">
												<td style="width: 25%;background: white;">产品、副产品及中间产物</td>
												<td colspan="3" style="background: white;" id="f0127"> </td>
											</tr>
										</tbody>
										
										<tbody>
											<tr class="commonStyle">
												<td style="width: 25%;height: 100px;vertical-align: middle;text-align: center;background: white;">工艺流程简述</td>
												<td colspan="3" style="background: white;height: 100px;"id="f0116">
													 
												</td>
											</tr>
											<tr class="commonStyle">
												<td style="width: 25%;height: 100px;vertical-align: middle;text-align: center;background: white;">周边环境情况</td>
												<td colspan="3" style="height: 100px;background: white;" id="f0117">
												 	 

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
			
			
		<!-- 公司详情-->
		<script src="<%=path%>/resources/js/companyDetailInfo2.js"></script>

