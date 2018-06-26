<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- 		<base href="<%=basePath%>"></base> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改企业</title>
		<meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文" />
		<meta name="description" content="站长素材提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

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
		
</head>
		
	<body > 
	
	
		<jsp:include page="top.jsp" />
    	<jsp:include page="left.jsp" />		
    	
    	
    	<div class="main-content">
			<div class="title"    style=" "  >
				<ul class="breadcrumb"   style="height:50px;">
					<li >
						<i class="icon-home home-icon"></i>
							<small id="companyName" style="font-size:22px;line-height:50px;"></small>
						</li>
					<li class="active" style="font-size:22px;color:#438eb9;">修改企业</li>

			   </ul><!-- .breadcrumb -->
	   
				<div class="main-content" >			
					 
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" style="margin-left: -12px;">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header" style="height: 30px;line-height: 30px;font-size: 15px;font-weight: bold;letter-spacing: 5px;">
								修改企业
							</div>
							<div class="row">
								<div class="col-xs-12">
							 		<form  action="" method="post" runat="server" style="height: 500px;"> 
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
														
												<tbody>
												<tr class="commonStyle"  >
													<td style="  width: 25%;background: white; "><span style="color: red">*</span>企业名称</td>
													<td colspan="3" style="background: white;"><input type="text" style="width: 80%;" id="f0102"></td>
												</tr>
												<tr class="commonStyle">
												<td style="background: white;">经济性质</td>	
												<td style="background: white;">					
														<select id="f0104">
															<option value="国有经济">国有经济</option>
															<option value="集体经济">集体经济</option>
															<option value="私营经济">私营经济</option>
															<option value="个体经济">个体经济</option>
															<option value="联营经济">联营经济</option>
															<option value="股份制">股份制</option>
															<option value="外商投资">外商投资</option>
															<option value="港澳台投资">港澳台投资</option>
															<option value="其他经济">其他经济</option>											
														</select>
													</td>						 						<td style="background: white;">行业类别</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0125"></td>
												</tr>
							
											<tr class="commonStyle" >
												<td style="background: white;"><span style="color: red">*</span>成立日期</td>
												<td style="background: white;"><input type="text" value="yyyy-mm-dd" id="f0105"></td>
												<td style="background: white;">法定代表人</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0108"></td>
							
											</tr>
	
											<tr class="commonStyle"  >
												<td style="width: 25%;background: white;"><span style="color: red">*</span>通讯地址</td>
												<td colspan="3" style="background: white;"><input type="text" style="width: 80%;" id="f0107"></td>									
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">安全负责人</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0110"></td>			 
												<td style="background: white;">联系电话</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0111"></td>
											</tr>
											<tr class="commonStyle">
												<td style="background: white;">安全管理人员</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0108"></td> 
												<td style="background: white;">联系电话</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0109"></td>
											</tr>
										 
											<tr class="commonStyle">
												<td style="background: white;">重点监管类别</td>
												<td style="background: white;">						
														<select id="f0118">
															<option value="涉氨">涉氨</option>
															<option value="涉尘">涉尘</option>
															<option value="有限空间">有限空间</option>
															<option value="金属熔炼">金属熔炼</option>
															<option value="人员密集">人员密集</option>
															<option value="急性中毒">急性中毒</option>
															<option value="其他">其他</option>										
														</select>
													</td>												
												<td style="background: white;"><span style="color: red">*</span>上次检查时间</td>
												<td style="background: white;"><input type="text" value="yyyy-mm-dd" id="f0130"></td>
											</tr>
											
											<tr class="commonStyle">
												<td style="background: white;"><span style="color: red">*</span>安全“三同时”</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0121"></td>
												<td style="background: white;"><span style="color: red">*</span>职卫“三同时”</td>
												<td style="background: white;"><input type="text" style="width: 80%;" id="f0122"></td>
											</tr>
											
											<tr class="commonStyle">
												<td style="background: white;"><span style="color: red">*</span>安全标准化</td>
												<td style="background: white;">						
														<select id="f0123">
															<option value="未开展">未开展</option>
															<option value="已编制">已启动</option>
															<option value="已达标">已达标</option>									
														</select>
												</td>
												<td style="background: white;"><span style="color: red">*</span>应急预案</td>
												<td style="background: white;">						
														<select id="f0124">
															<option value="未编制">未编制</option>
															<option value="已编制">已编制</option>
															<option value="未备案">未备案</option>
															<option value="已备案">已备案</option>										
														</select>
												</td>
											</tr>
											
											
											<tr class="commonStyle">
												<td style="width: 25%;background: white;">主要原辅材料</td>
												<td colspan="3" style="background: white;"><input type="text" style="width: 80%;" id="f0126"></td>
											</tr>
										 
											<tr class="commonStyle">
												<td style="width: 25%;background: white;">产品、副产品及中间产物</td>
												<td colspan="3" style="background: white;"><input type="text" style="width: 80%;" id="f0127"></td>
											</tr>
										</tbody>
										
										<tbody>
											<tr class="commonStyle">
												<td style="width: 25%;height: 100px;vertical-align: middle;text-align: center;background: white;">工艺流程简述</td>
												<td colspan="3" style="background: white;height: 100px;">
													<textarea style="width: 100%;height: 100%;" id="f0116">
														
														
													</textarea>
												</td>
											</tr>
											<tr class="commonStyle">
												<td style="width: 25%;height: 100px;vertical-align: middle;text-align: center;background: white;">周边环境情况</td>
												<td colspan="3" style="height: 100px;background: white;">
												 	<textarea style="width: 100%;height: 100%;" id="f0117">
																
													</textarea>

												</td>
										
											</tr>
										</tbody>
									 
			
									</table>
								
							 		<div  >
					 					<button  type="button" class="btn btn-large btn-primary " style="width: 45%;" id="editRegisterCompany">提交</button>
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
			
	</div>
    	
    
	
 	   <script src="<%=path%>/resources/assets/js/jquery-2.0.3.min.js"></script>
		<!-- <![endif]-->
		<script src="<%=path%>/resources/assets/js/bootstrap.min.js"></script>
		<script src="<%=path%>/resources/assets/js/typeahead-bs2.min.js"></script>
		<!-- page specific plugin scripts -->
		<script src="<%=path%>/resources/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="<%=path%>/resources/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
		<!-- ace scripts -->
		<script src="<%=path%>/resources/assets/js/ace-elements.min.js"></script>
		<script src="<%=path%>/resources/assets/js/ace.min.js"></script>		
		<!-- 修改公司-->
		<script src="<%=path%>/resources/js/editRegisterCompany.js"></script>
		<!-- common js -->
		<script src="<%=path%>/resources/js/top.js"></script>
		<script src="<%=path%>/resources/js/left.js"></script>
		
		<script src="https://cdn.bootcss.com/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.zh-CN.min.js"></script>
	    	
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
	$('#f0105').datepicker();
	$('#f0130').datepicker();
	</script>

	
</body>
</html>