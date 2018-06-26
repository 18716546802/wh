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
							<small  style="font-size:22px;line-height:50px;" id="companyName"></small>
						</li>
					<li class="active" style="font-size:22px;color:#438eb9;">添加人员</li>

			   </ul><!-- .breadcrumb -->
	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
								添加人员
							</div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" runat="server" style="height: 500px;"> 
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
										 
										
										<tbody>
											<tr class="commonStyle"  >
												<td style="  width: 25%;background: white; "><span style="color: red">*</span>姓名</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0702" ></td>
											</tr>

											<tr class="commonStyle">
												<td style="background: white;">证书编号</td>
												<td style="background: white;"><input type="text" style="width: 80%;"  id="f0703"></td>	 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;"><span style="color: red">*</span>有效时间</td>
												<td style="background: white;"><input type="text" value="yyyy-mm-dd" id="f0704"/></td>	 		 													 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">颁发单位</td>
												<td style="background: white;"><input type="text" style="width: 80%;"  id="f0705"></td>	 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">状态</td>
												<td style="background: white;">
												<select style="width: 80%;padding-left: 38%;" id="f0706">
													  <option value ="有效">有效</option>
													  <option value ="过期">过期</option>
												</select>									
												</td>		 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">证件名称</td>
												<td style="background: white;"><input type="text" style="width: 80%;"  id="f0707"></td>	 
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">资格证书类型</td>
												<td style="background: white;"><input type="text" style="width: 80%;"  id="f0708"></td>	 
											</tr>
										 
							
										</tbody>
										
									 
			
									</table>
								
							 		<div style="height: 100px;padding-top:50px;" >
					 					<button  type="button" class="btn btn-large btn-primary " style="width: 45%;" id="submitAddPeople">提交</button>
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
		<script src="<%=path%>/resources/js/addPeople.js"></script>
     	
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
	$('#f0704').datepicker();
	</script>
	    	
 