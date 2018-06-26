<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>特种设备</title>
  <meta name="keywords" content="table">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="<%=path%>/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="<%=path%>/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" type="text/css" href="<%=path%>/assets/css/pagination.css">
  <link rel="stylesheet" href="<%=path%>/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="<%=path%>/assets/css/admin.css">
  <link rel="stylesheet" href="<%=path%>/assets/css/app.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->


<div class="am-cf admin-main myMian">
  <!-- sidebar start -->

  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
	  <div class="am-g am-margin-top">
	  	<form class="am-form am-form-horizontal">
		  <div class="am-form-group">
		    <div class="am-u-sm-8">
		      <input type="text" id="f0102"  placeholder="请输入企业名称" />
		      <input type="hidden" id="f0601" name="f0601" value="${F0601 }" />
		    </div>
		   	<div class="am-u-sm-4">
		      <button type="button" id="search" class="am-btn am-btn-block am-btn-secondary">搜索</button>
		    </div>
		  </div>
		</form>
	  </div>
      <div class="am-g">
        <div class="am-u-sm-12">
            <table class="am-table am-table-bordered am-table-radius am-table-striped am-table-hover table-main">
              <thead>
              <tr>
                <th class="table-id">名称</th>
                <th class="table-id">所属企业</th>
                <th class="table-id">检测日期</th>
                <th class="table-id">检测结论</th>
              </tr>
              </thead>
              <tbody id="content">

              </tbody>
            </table>
            <div id="page" class="am-center pagination"></div>
        </div>

      </div>
    </div>

  </div>


<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="<%=path%>/assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<!--<![endif]-->

<script src="<%=path%>/assets/js/amazeui.min.js"></script>
<script type="text/javascript" src="<%=path%>/assets/js/jquery.pagination.js"></script>
<script src="<%=path%>/assets/js/zfSpecialEquipment.js"></script>
</body>
</html>
