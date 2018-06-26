<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>


<%! 
//用于转换数据的格式
		String timePatter = "yyyy/MM/dd";
		public String getTimeByDate(Date date){
			if(date != null){
				SimpleDateFormat sdf = new SimpleDateFormat(timePatter);
				return sdf.format(date);
			}else{
				return "暂无";
			}

		}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" 
			href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" 
			integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" 
			crossorigin="anonymous">
			<meta name="viewport" content="width=device-width, initial-scale=1">
<title>companyAPPFirstPage</title>
</head>
<body >
	<div class="container">
			<div>
				<table class="table table-bordered">
				<!-- 1 -->
					<tr><th class="text-center"  colspan="4">企业基本信息</th></tr>
				<!-- 2 -->
					<tr>
						<td><strong>企业名称</strong></td>
						<td id="companyName" class="text-center"  colspan="3">${data.cName}</td>
						
					</tr>
				<!-- 3 -->
					<tr>
						<td><strong>经济性质</strong></td>
						<td id="ecnomicAttr">${data.cEClass}</td>
						
						<td><strong>行业类别</strong></td>
						<td id="majorClass" class="text-center">${data.cMClass}</td>
					</tr>
				<!-- 4 -->
					<tr>
							<td><strong>成立日期</strong></td>
							<td id="biuldDate">
							<c:if test="${not empty data.buildTime}">
								${data.buildTime }
							</c:if>
							<c:if test="${empty data.buildTime}">暂无</c:if>
							</td>
							
							<td><strong>法人代表</strong></td>
							<td id="legalRepre" class="text-center">${data.legalPerson }</td>
					</tr>
					
				<!-- 5 -->
					<tr>
							<td><strong>通讯地址</strong></td>
							<td id="address" colspan="3">${data.address }</td>
					</tr>
				<!-- 6 -->
				<tr>
							<td><strong>安全负责人</strong></td>
							<td id="RPName" class="text-center">${data.securityRP }</td>
						
							<td><strong>安全管理人</strong></td>
							<td id="MPName" class="text-center">${data.securityMP }</td>							
				</tr>		
				
				<!-- 7 -->
				<tr>
							<td colspan="4" class="text-center"><strong>证照资质</strong></td>
				</tr>
				<tr>
						<td class="text-center"><strong>证照名称</strong></td>
						<td class="text-center"><strong>有效期至</strong></td>
						<td class="text-center" colspan="2"><strong>证照编号</strong></td>
				</tr>
				
				<!-- 8 -->
				<c:forEach var="cItem"  items="${data.cLicenses}"> 
				<tr>
						<td id="licenseName" class="text-center">${cItem.f0502 }</td>
						<td id="periodTime" class="text-center">
						<fmt:formatDate value="${cItem.f0504 }" type="date" pattern="yyyy/MM/dd"/>
						</td>
						<td id="licenseNum" colspan="2" class="text-center">${cItem.f0503 }</td>
				</tr>
				
				</c:forEach>
				
				<!-- 9 -->

				
				<!-- 10 -->
				<tr>
						<td colspan="4" class="text-center"><strong>人员资质</strong></td>
				</tr>
				<!-- 人员资质表头-->
				<tr>
						<td class="text-center"><strong>证照名称</strong></td>
						<td class="text-center"><strong>姓名</strong></td>
						<td class="text-center"><strong>证照编号</strong></td>
						<td class="text-center"><strong>有效期</strong></td>
				</tr>
		
				
				<c:forEach var="pItem" items="${data.pLicenses }">
				<tr>
						<td id="employeeLicense" class="text-center">${pItem.f0707 }</td>
						<td id="employeeName" class="text-center">${pItem.f0702 }</td>
						<td id="employeeLicenseNum" class="text-center">${pItem.f0703 }</td>
						<td id="employeeLicensePeriod" class="text-center">
						<fmt:formatDate value="${pItem.f0704 }" type="date" pattern="yyyy/MM/dd"/>
						</td>				
				</tr>				
					
				</c:forEach>
				
				

				
				<!-- 13 -->
				<tr>
						<td class="text-center" colspan="4"><strong>特种设备</strong></td>
				</tr>
				<!-- 14 -->
				<tr>
						<td class="text-center"><strong>设备级别</strong></td>
						<td class="text-center"><strong>规格型号</strong></td>
						<td  colspan="2" class="text-center"><strong>设备名称</strong></td>
				</tr>
				
				<c:forEach var="seq" items="${data.sequs}">
				<tr>
						<td class="text-center">${seq.f0303}</td>
						<td class="text-center">${seq.f0309 }</td>
						<td  colspan="2" class="text-center">${seq.f0302}</td>
				</tr>				
				</c:forEach>
				

				</table>
			</div>
	</div>
	

	
	
</body>
</html>