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
					<li class="active" style="font-size:22px;color:#438eb9;">修改特种设备</li>

			   </ul><!-- .breadcrumb -->
	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
								修改特种设备
							</div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" runat="server" style="height: 500px;"> 
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
														
										<tbody>
											 <tr class="commonStyle"  >
												<td style="  width: 25%;background: white;" ><span style="color: red">*</span>设备名称</td>
												<td style="background: white;"><input type="text" style="width: 80%;"  id="f0302"></td>
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">设备级别</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0303"></td>

											</tr>
											<tr class="commonStyle">
												<td style="background: white;">生产厂家</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0304" ></td>
											</tr>
											<tr class="commonStyle">
												<td style="background: white;"><span style="color: red">*</span>投用日期</td>
												<td style="background: white;"><input type="text" value="yyyy-mm-dd" id="f0305"></td>	 
												 	 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">注册证编号</td>
												<td style="background: white;"><input type="text" style="width: 80%;"    id="f0306"></td>	 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">厂内编号</td>
												<td style="background: white;"><input type="text" style="width: 80%;"  id="f0307"></td>	 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">设备类别</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0308"></td>	 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">规格型号</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0309"></td>	 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;"><span style="color: red">*</span>生产日期</td>
												<td style="background: white;"><input type="text" value="yyyy-mm-dd" id="f0310"></td>	 		 													 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">使用场所</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0311" ></td>	 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">使用证编号</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0312" ></td>	 
											</tr>
 											
 											<tr class="commonStyle">
												<td style="background: white;">定检周期</td>
												<td style="background: white;"><input type="text" style="width: 80%;"  id="f0313"></td>	 
											</tr>
											
											<tr class="commonStyle">
												<td style="background: white;">主要性能参数</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0314"></td>	 
											</tr>
 											
 											<tr class="commonStyle">
												<td style="background: white;" ><span style="color: red">*</span>是否过期</td>
												<td style="background: white;">
												<select style="width: 80%;padding-left: 35%;" id="f0315">
													  <option value ="未过期">未过期</option>
													  <option value ="过期">过期</option>
												</select>									
												</td>		 
											</tr>
 
										</tbody>
										
									 
			
									</table>
								
							 		<div  >
					 					<button  type="button" class="btn btn-large btn-primary " style="width: 45%;" id="submitEditEquipment">提交</button>
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
	
		<!-- 添加证照-->
		<script src="<%=path%>/resources/js/editEquipment.js"></script>
 
 
	  <script type="text/javascript">
	$.fn.datepicker.dates['en'] = {
	    days: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
	    daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
	    daysMin: ["日", "一", "二", "三", "四", "五", "六"],
	    months: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
	    monthsShort: [" 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", " 10 ", " 11 ", " 12 "],
	    today: "今天",
	    clear: "清空",
	    format: "yyyy/mm/dd",
	    titleFormat: "yyyy MM", /* Leverages same syntax as 'format' */
	    weekStart: 0
	};
	$('#f0310').datepicker();
	$('#f0305').datepicker();
	</script>
	    	
 