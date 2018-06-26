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
		
 
		
 
    	
 
		<div class="title"    style="">
				<ul class="breadcrumb"  style="height:50px;">
					<li >
						<i class="icon-home home-icon"></i>
							<small id="companyName" style="font-size:22px;line-height:50px;"></small>
						</li>
					<li class="active" style="font-size:22px;color:#438eb9;">添加安全附件</li>

			   </ul><!-- .breadcrumb -->
	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
								添加安全附件
						   </div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" runat="server" style="height: 500px;"> 
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
												<tbody>
											 		
											 		<tr class="commonStyle"  >
												<td style="  width: 25%;background: white; "><span style="color: red">*</span>安全附件名称</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0402" ></td>
											</tr>
											
											
											<tr class="commonStyle">
 												<td style="background: white;"><span style="color: red">*</span>安全附件检测时间</td>
												<td style="background: white;"><input type="text" value="yyyy-mm-dd" id="f0403"></td>	 		 													 
											</tr>
											
	
											<tr class="commonStyle">
												<td style="background: white;">检测结论</td>
												<td style="background: white;"><input type="text" style="width: 80%;"  id="f0404"></td>	 
											</tr>
											 
											<tr class="commonStyle">
												<td style="background: white;">检测报告编号</td>
												<td style="background: white;"><input type="text" style="width: 80%;"  id="f0405"></td>	 
											</tr>
											 
											<tr class="commonStyle">
												<td style="background: white;">附件检测周期</td>
												<td style="background: white;"><input type="text" style="width: 80%;"  id="f0406"></td>	 
											</tr>
											 
											 <tr class="commonStyle">
												<td style="background: white;"><span style="color: red">*</span>下次检测时间</td>
												<td style="background: white;"><input type="text" value="yyyy-mm-dd" id="f0407"></td>	 		 													 
											</tr>
 										 
			 
											<tr class="commonStyle">
												<td style="background: white;">备是否过期</td>
												<td style="background: white;">
												<select style="width: 80%;padding-left: 38%;" id="f0408">
													  <option value ="未过期">未过期</option>
													  <option value ="过期">过期</option>
												</select>									
												</td>		
											</tr>
 											 
 											
												</tbody>				
			
										</table>
								
							 		<div >
					 					<button  type="button" class="btn btn-large btn-primary " style="width: 45%;" id="submitEquipDocument">提交</button>
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
		<!-- 添加安全文件-->
		<script src="<%=path%>/resources/js/addEquipDocument.js"></script>

	    	
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
	$('#f0403').datepicker();
	$('#f0407').datepicker();
	</script>
 