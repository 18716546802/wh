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
	 
    	 
		<div class="title"   >
				<ul class="breadcrumb"  style="height:50px;">
					<li >
						<i class="icon-home home-icon"></i>
							<small style="font-size:22px;line-height:50px;" id="companyName"></small>
						</li>
					<li class="active" style="font-size:22px;color:#438eb9;">添加隐患</li>

			   </ul><!-- .breadcrumb -->
	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
								添加隐患
							</div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" runat="server" style="height: 500px;"> 
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
										 
										
									<tbody>
																		
											<tr class="commonStyle"  >
												<td style="  width: 25%;background: white;">参检人员</td>
												<td style="background: white;width: 25%;"><input id="f0803" type="text" style="width: 80%;"></td>	
												<td style="  width: 25%;background: white;">整改情况</td>
												<td style="background: white;">
													 <select style="width: 80%;padding-left: 30%;" id="f0804">
													 	 <option value ="未整改">未整改</option>
													  	 <option value ="已整改">已整改</option>
													  	 <option value ="已确认">已确认</option>
													 </select>													
												</td>	
											</tr>
											
											
											<tr class="commonStyle">
												<td style="background: white;">企业确认人员</td>
												<td style="background: white;"><input id="f0805" type="text" style="width: 80%"></td>	 
						                        <td style="background: white;">复查人员</td>
												<td style="background: white;"><input id="f0806" type="text" style="width: 80%"></td>	 
											</tr>
											
							   
	
											<tr class="commonStyle">
												<td style="background: white;">企业整改人员</td>
												<td style="background: white;"><input id="f0808" type="text" style="width: 80%" ></td>
												<td style="  width: 25%;background: white; "><span style="color: red">*</span>整改类型</td>
												<td style="background: white;">
													 <select style="width: 80%;padding-left: 27%;" id="f0809">
													 	 <option value ="不限时间">不限时间</option>
													  	 <option value ="限期整改">限期整改</option>

													 </select>													
												</td>
												
											</tr>

											<tr class="commonStyle">
												<td style="background: white;"><span style="color: red">*</span>限期天数</td>
												<td style="background: white;"><input id="f0810" type="text" style="width: 80%";></td>	 
						                        <td style="background: white;"><span style="color: red">*</span>隐患发现时间</td>
												<td style="background: white;"><input type="text" value="yyyy-mm-dd" id="f0811"></td>	 
											</tr>
										 	
										 	<tr class="commonStyle">
												<td style="background: white;"><span style="color: red">*</span>隐患整改截止日期</td>
												<td style="background: white;"><input type="text" value="yyyy-mm-dd" id="f0812"></td>	 
						                        <td style="background: white;">是否重大危险源</td>
												<td style="background: white;">
													 <select style="width: 80%;padding-left: 35%;" id="f0819">
													 	 <option value ="是">是</option>
													  	 <option value ="否">否</option>
													 </select>	
												</td>	 
											</tr>
											
											<tr class="commonStyle">	 
						                        <td style="background: white;" ><span style="color: red">*</span>隐患类别</td>
												<td style="background: white;"  colspan="3">
													 <select style="width: 80%;padding-left: 32%;" id="f0820">
													 	 <option value ="安全管理">安全管理</option>
													  	 <option value ="总图运输">总图运输</option>
													  	 <option value ="生产工艺">生产工艺</option>
													  	 <option value ="工艺设备">工艺设备</option>
													  	 <option value ="特种设备">特种设备</option>
													  	 <option value ="安全设施">安全设施</option>
													  	 <option value ="电气">&nbsp;&nbsp;&nbsp;&nbsp;电气</option>
													  	 <option value ="消防">&nbsp;&nbsp;&nbsp;&nbsp;消防</option>
													  	 <option value ="其他">&nbsp;&nbsp;&nbsp;&nbsp;其他</option>
													 </select>	
												
												</td>	 
											</tr>
			  									
										
											 
																										
										</tbody>
										
										<tbody>
											
											<tr class="commonStyle">
						                        <td style="background: white;height: 100px;vertical-align: middle;text-align: center;">复查结果</td>											 
												<td colspan="3" style="background: white;width: 80%;">
													<textarea style="width: 100%;height: 100px;" id="f0807">
														
														
													</textarea>
												</td>								 	 
											</tr>
											
										
										
											<tr class="commonStyle">
						                        <td style="background: white;height: 100px;vertical-align: middle;text-align: center;"><span style="color: red">*</span>隐患描述</td>											 
												<td colspan="3" style="background: white;width: 80%;">
													<textarea style="width: 100%;height: 100px;" id="f0802">
														
														
													</textarea>
												</td>								 	 
											</tr>
											
											<tr class="commonStyle">
												<td style="background: white;height: 200px;vertical-align: middle;text-align: center;"id="addPic">												
												 
														<button  type="button" class="btn btn-primary " >点击上传隐患图片</button>
													 
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
											
											
											
			  																							
										</tbody>

										
									 
			
									</table>
								
								 		<div style="height: 100px;" >
						 					<button  type="button" class="btn btn-large btn-primary " style="width: 45%;" id="submitAddHideDanger">提交</button>
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
 	    <!-- Plupload js-->
 	    <script src="<%=path%>/resources/plupload/plupload.full.min.js"></script>
		<!-- 添加隐患-->
		<script src="<%=path%>/resources/js/addHideDanger.js"></script>
	 
	 
	  <script type="text/javascript">
	$.fn.datepicker.dates['en'] = {
	    days: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
	    daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
	    daysMin: ["日", "一", "二", "三", "四", "五", "六"],
	    months: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
	    monthsShort: [" 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", " 10 ", " 11 ", " 12 "],
	    today: "今天",
	    clear: "清空",
	    format: "yyyy-mm-dd",
	    titleFormat: "yyyy MM", /* Leverages same syntax as 'format' */
	    weekStart: 0
	};
	$('#f0812').datepicker();
	$('#f0811').datepicker();
	</script>
 