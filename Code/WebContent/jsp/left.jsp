<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>  
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/assets/css/easyui.css"> 
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/assets/css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/assets/css/demo.css"> 
		<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/reset.css" />
		<div class="main-container" id="main-container">
			<div class="main-container-inner">
				
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text myMenu"></span>
				</a>

				<div class="sidebar" id="sidebar">
 
					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success">
								<i class="icon-signal"></i>
							</button>

							<button class="btn btn-info">
								<i class="icon-pencil"></i>
							</button>

							<button class="btn btn-warning">
								<i class="icon-group"></i>
							</button>

							<button class="btn btn-danger">
								<i class="icon-cogs"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->
						
					<!--第一个道航-->	
					<ul class="nav nav-list ">	
						
						<!-- 地区管理 -->
						<li>
							<a  href="#" class="dropdown-toggle">
								<i class="icon-road"></i>
								<span class="menu-text myMenu">地区管理 </span>
								<b class="arrow icon-angle-down"></b>							 
							</a>
							<ul class="submenu" >
								<li>
									<a href="#" onclick="d_regionManagement()">
										<i class="icon-double-angle-right"></i>
										所有地区
									</a>
								</li>
   
								<li>
									<a data-toggle="modal" href="#example">
										<i class="icon-double-angle-right"></i>
										修改企业地区
									</a>
								</li>	
							</ul>	
						</li>	
																					
						<!--用户管理-->	
						<li>
							<a  href="#" class="dropdown-toggle">
								<i class="icon-user"></i>
								<span class="menu-text myMenu">账户管理 </span>
								<b class="arrow icon-angle-down"></b>							 
							</a>
							<ul class="submenu" >
								<li>
									<a href="#" onclick="d_User()">
										<i class="icon-double-angle-right"></i>
										万汇用户
									</a>
								</li>
   
								<li>
									<a href="#" onclick="d_company()">
										<i class="icon-double-angle-right"></i>
										企业用户
									</a>
								</li>

								<li>
									<a href="#" onclick="d_government()">
										<i class="icon-double-angle-right"></i>
										政府用户
									</a>
								</li>	
							</ul>	
						</li>
						
						<!--法律法规-->
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-book"></i>
								<span class="menu-text myMenu">法律法规 </span>
								<b class="arrow icon-angle-down"></b>	
							</a>
							<ul class="submenu" >
								<li>
									<a href="#" onclick="d_lawsAndRegulations()">
										<i class="icon-double-angle-right"></i>
										法律法规
									</a>
								</li>
							</ul>
						</li>
						
						<!--企业列表-->				
						<li class="myTree">
							<a  href="#" class="dropdown-toggle">
								<i class="icon-list-alt"></i>
								<span class="menu-text myMenu">单位列表 </span>
								<b class="arrow icon-angle-down"></b>							 
							</a>
						</li>
						
					</ul>
				
				</div>
				 		
	    </div>
		
		</div>	
		<!--第二个导航-->	   
	   	<div class="main-container" id="main-container"  >
			<div class="sidebar" id="sidebar"  >	
					<ul class="nav nav-list " >	
					<!--	<li style="z-index: 10; border-right: 1px solid #ccc;border-bottom: none;">-->
						<li>	
							<a  href="#" onclick="a_CompanyBaseInfo()">
								<i class="icon-leaf"></i>
								<span class="menu-text myMenu" >企业基本信息 </span>
							</a>
						</li>
						<li>
							<a  href="#" onclick="a_CompanyLicence()" >
								<i class="icon-leaf"></i>
								<span class="menu-text myMenu">企业证照 </span>
							</a>
						</li>
						<li>
							<a  href="#" onclick="a_CompanyEquipment()">
								<i class="icon-leaf"> </i>
								<span class="menu-text myMenu">特种设备及其安全 </span>
							</a>
						</li>
						<li>
							<a  href="#"  onclick="a_CompanyPeople()">
								<i class="icon-leaf" > </i>
								<span class="menu-text myMenu">人员资质 </span>
							</a>
						</li>
						<li>
							<a  href="#"  onclick="a_CompanyDanger()">
								<i class="icon-leaf"> </i>
								<span class="menu-text myMenu">重大危险源 </span>
							</a>
						</li>
						<li>
							<a  href="#"  onclick="a_CompanyTodayHideDanger()" >
								<i class="icon-leaf" > </i>
								<span class="menu-text myMenu">未整改隐患 </span>
							</a>
						</li>
						<li>
							<a  href="#" onclick="a_CompanyHideDanger()">
								<i class="icon-leaf"> </i>
								<span class="menu-text myMenu">整改隐患 </span>
							</a>
						</li>
						<li>
							<a  href="#"  onclick="a_CompanyAdminRemind()">
								<i class="icon-leaf"> </i>
								<span class="menu-text myMenu">提醒管理 </span>
							</a>
						</li>
						<li>
							<a  href="#" onclick="a_getAllParameter()">
								<i class="icon-leaf"> </i>
								<span class="menu-text myMenu">工作参数设置 </span>
							</a>
						</li>	
						</ul>	
			</div>
	   </div>
	   
	   
	   
	   
	   
	   <!-- 太乱 --> 
		<div id="example" class="modal hide fade in" style="display: none;width:420px;background-color:#fff;height:250px;left:35%;top:20%;overflow-y:auto;">
		     <div class="modal-header">
		         <a class="close" data-dismiss="modal">×</a>
		         <h4>修改企业所属地区</h4>
		     </div>
		     <div class="modal-body">
		     请选择要切换地区的企业：
		     <select name="companyUserTree" class="easyui-combotree" data-options="url:'/WH/user/userTree?type=1',method:'GET',required:true" style="width:230px;"></select>
		     <br/>
		     <br/>请选择该企业切换的地区：
		<select name="governmentUserTree" class="easyui-combotree" data-options="url:'/WH/user/userTree?type=2',method:'get',required:true" style="width:230px;"></select>
		     <br/>
		     <br/>
		         <br/>
		     <button id="changeLocation"  type="button" class="btn btn-info btn-block btn-sm">确认切换</button>
		     </div>
		</div> 
<script src="<%=path%>/resources/assets/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/assets/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/changeLocation.js"></script>