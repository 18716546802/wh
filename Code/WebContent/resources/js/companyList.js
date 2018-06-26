

var grid_data;//数据源
var companyName;//公司名
var companyID;//公司ID 



function idAndName(){   //ID 和 名字
	
	var id;
	var name;
};

var lstIDAndName;


//得到当前数据
function getRowData(){
	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}

//添加公司
function addCompany(){	
	
	localStorage.setItem("companyID",JSON.stringify(companyID));
		
	$("#alertmod_grid-table").remove();
	showPage("/WH/jsp/registerCompany.jsp");
  
}
//修改公司
function editCompany(){
	

	var rowData =getRowData();
	
	var f0102=rowData.f0102;
	
	var start=-1,end=-1;
	
	 	
	for(var i=0;i<f0102.length;i++){
		
		if(f0102[i]==">" && start==-1){
			start=i;
		}
		if(f0102[i]=="<" ){
			end=i;	
		}
	}
	
	f0102=f0102.substring(start+1,end);
 
	rowData.f0102=f0102;
	
	//$("#alertmod_grid-table").remove();
	localStorage.setItem("rowData",JSON.stringify(rowData));  
	showPage("/WH/jsp/editCompany.jsp");
	//window.location.href="/WH/jsp/editCompany.jsp";
}
// 删除公司
function deleteCompany() {

	if (confirm("您确定要删除嘛？")) {

		var rowData = getRowData();
		var f0601 = rowData.f0601;

		$.ajax({
			type : "get",
			url : "/WH/job/deleteEnterprise",
			data : {
				f0101 : rowData.f0101
			},
			success : function(data) {
				
				if (data.success) {
					localStorage.setItem("companyID", JSON.stringify(f0601));
					
					showPage("/WH/jsp/companyList.jsp");
					
					//window.location.href = "/WH/jsp/companyList.jsp";
				} else {
					alert("删除失败");
				}
			}
		});

	} else {

	}

}

 

$(document).ready(function(){
	
	companyName =JSON.parse(localStorage.getItem("companyName")); 
    companyID = JSON.parse(localStorage.getItem("companyID"));  
	$("#companyName").text(companyName);
	
	$.ajax({
       	type:"GET",
        url:"/WH/job/findEnterpriseByArea?f0601="+companyID,
        success:function(data){   
        
        	if(data.success){     		     		
        		grid_data=data.data;	
        		showJqgrid(grid_data);
        	}
        	else{
        		grid_data=[];
        		showJqgrid(grid_data);
        	}      	
         }
   });
 
});


function getData(id){
	

	for(var i=0;i<lstIDAndName.length;i++){
		
		if(id==lstIDAndName[i].id){	
			
			 localStorage.setItem("companyID",JSON.stringify(lstIDAndName[i].id));
			 localStorage.setItem("companyName",JSON.stringify(lstIDAndName[i].name));
			 companyID=lstIDAndName[i].id;	 	
			 showPage("/WH/jsp/companyDetailInfo.jsp");
			 
		}
	}
}


//显示数据
function showJqgrid(grid_data){
	
	
	lstIDAndName=new Array();
	
	
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";	
	jQuery(grid_selector).jqGrid({
		data: grid_data ,
		datatype: "local",																														    
		height: 500,
		colNames:['企业编号','ID','企业名称','部门类别','经济性质', '企业成立日期', '注册地址','通讯地址','企业负责人','企业负责人电话','安全负责人','安全负责人电话', '企业安全等级', '企业当前评估风险等级','企业地理位置经度','企业地理位置纬度','企业工艺流程简介','企业周边环境简介', '企业重点监管类别','企业照片','企业略缩图','安全三同时','职位三同时','安全标准化', '应急预案', '行业类别','主要原辅材料','产品、副产品及中间产物','企业重点监管类别','企业名称简称','上次检查时间'],
		colModel:[			
			{name:'f0101',index:'f0101', width:100,align:'center',editable: true,hidden:true},
			{name:'f0601',index:'f0601', width:100,align:'center',editable: true,hidden:true},	
			{name:'f0102',index:'f0102', width:100,align:'center',editable: true,formatter:function(cellvalue, options, rowObject){  var demp=new idAndName();demp.id=rowObject.f0101;demp.name=rowObject.f0102; lstIDAndName.push(demp); return "<a  onclick=getData("+rowObject.f0101+")  style='color:#307ecc'  href='#'>"+cellvalue+"</a>";}},
			{name:'f0103',index:'f0103', width:100,align:'center',editable: true,hidden:true},
			{name:'f0104',index:'f0104', width:100,align:'center',editable: true,hidden:true},
			{name:'f0105',index:'f0105', width:100,align:'center',editable: true,hidden:true},
			{name:'f0106',index:'f0106', width:100,align:'center',editable: true,hidden:true},
			{name:'f0107',index:'f0107', width:200,align:'center',editable: true},
			{name:'f0108',index:'f0108', width:100,align:'center',editable: true},
			{name:'f0109',index:'f0109', width:100,align:'center',editable: true},
			{name:'f0110',index:'f0110', width:100,align:'center',editable: true},
			{name:'f0111',index:'f0111', width:100,align:'center',editable: true},
			{name:'f0112',index:'f0112', width:100,align:'center',editable: true,hidden:true},
			{name:'f0113',index:'f0113', width:100,align:'center',editable: true,hidden:true},
			{name:'f0114',index:'f0114', width:100,align:'center',editable: true,hidden:true},
			{name:'f0115',index:'f0115', width:100,align:'center',editable: true,hidden:true},
			{name:'f0116',index:'f0116', width:100,align:'center',editable: true,hidden:true},
			{name:'f0117',index:'f0117', width:100,align:'center',editable: true,hidden:true},
			{name:'f0118',index:'f0118', width:100,align:'center',editable: true,hidden:true},
			{name:'f0119',index:'f0119', width:100,align:'center',editable: true,hidden:true},
			{name:'f0120',index:'f0120', width:100,align:'center',editable: true,hidden:true},
			{name:'f0121',index:'f0121', width:100,align:'center',editable: true,hidden:true},
			{name:'f0122',index:'f0122', width:100,align:'center',editable: true,hidden:true},
			{name:'f0123',index:'f0123', width:100,align:'center',editable: true,hidden:true},
			{name:'f0124',index:'f0124', width:100,align:'center',editable: true,hidden:true},
			{name:'f0125',index:'f0125', width:100,align:'center',editable: true},
			{name:'f0126',index:'f0126', width:100,align:'center',editable: true,hidden:true},
			{name:'f0127',index:'f0127', width:100,align:'center',editable: true,hidden:true},
			{name:'f0128',index:'f0128', width:100,align:'center',editable: true,hidden:true},
			{name:'f0129',index:'f0129', width:100,align:'center',editable: true,hidden:true},
			{name:'f0130',index:'f0130', width:100,align:'center',editable: true}, 
		],	
		viewrecords : true,
		rowNum:10,
//		rowList:[5,10],
		pager : pager_selector,
		altRows: true,
		//toppager: true,			
		multiselect: true,
		//multikey: "ctrlKey",
        multiboxonly: true,		
		loadComplete : function() {
			var table = this;
			setTimeout(function(){					 
		    var icons = $(".ui-icon-refresh");//隐藏刷新
			icons.hide();
			styleCheckbox(table);						
			updateActionIcons(table);
			updatePagerIcons(table);
			}, 0);
		},

		caption: "公司列表",		
		autowidth:true,//自动宽

	});

	//增删改查
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
		{ 	//navbar options
			edit: true,
			editicon : 'icon-pencil blue',
			editfunc:editCompany,
			add: true,
			addfunc: addCompany,
			addicon : 'icon-plus-sign purple',
			del: true,
			delicon : 'icon-trash red',
			delfunc:deleteCompany,
			search: true,
			searchicon : 'icon-search orange'
		},

		{
			//search form
			recreateForm: true,
			afterShowSearch: function(e){
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
				style_search_form(form);
			},
			afterRedraw: function(){
				style_search_filters($(this));
			}
			,
			multipleSearch: true,
			/**
			multipleGroup:true,
			showQuery: true
			*/
		}
	)
	
	function style_search_filters(form) {

		form.find('.delete-rule').val('X');
		form.find('.add-rule').addClass('btn btn-xs btn-primary');
		form.find('.add-group').addClass('btn btn-xs btn-success');
		form.find('.delete-group').addClass('btn btn-xs btn-danger');									 
	}
	function style_search_form(form) {
		
		var dialog = form.closest('.ui-jqdialog');
		var buttons = dialog.find('.EditTable')
		buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
		buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
		buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
		 
	}
	

	function styleCheckbox(table) {
	 
	}

	//unlike navButtons icons, action icons in rows seem to be hard-coded
	//you can change them like this in here if you want
	function updateActionIcons(table) {
		/**
		var replacement = 
		{
			'ui-icon-pencil' : 'icon-pencil blue',
			'ui-icon-trash' : 'icon-trash red',
			'ui-icon-disk' : 'icon-ok green',
			'ui-icon-cancel' : 'icon-remove red'
		};
		$(table).find('.ui-pg-div span.ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
		*/
	}
	
	//replace icons with FontAwesome icons like above
	function updatePagerIcons(table) {
		var replacement = 
		{
			'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
			'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
			'ui-icon-seek-next' : 'icon-angle-right bigger-140',
			'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
	}	
}

 

