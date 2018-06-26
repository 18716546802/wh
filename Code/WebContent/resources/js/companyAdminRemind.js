

var grid_data;//数据源
var companyName;//公司名
var companyID;//公司ID 


//得到当前数据
function getRowData(){
	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}

//添加证照
function addLicence(){	
 
}
//修改证照
function editLicence(){
 
}
//删除数据
function deleLicence(){
 	
}

 

$(document).ready(function(){
	
	companyName =JSON.parse(localStorage.getItem("companyName")); 
    companyID = JSON.parse(localStorage.getItem("companyID"));  
	$("#companyName").text(companyName);
	console.log(companyID);
	$.ajax({
       	type:"GET",
        url:"/WH/danger/findAllRemindCom",
        data:{f0101:companyID},
        success:function(data){
        	console.log(data);
        	if(data.success){     		
        		console.log(data.data);
        		grid_data=data.data;	
        		showJqgrid(grid_data);
        	}
        	else{
        		console.log("no");
        		grid_data=[];
        		showJqgrid(grid_data);
        	}      	
         }
   });
 
});


//显示数据
function showJqgrid(grid_data){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";	
	jQuery(grid_selector).jqGrid({
		data: grid_data ,
		datatype: "local",																														    
		height: 500,
	//	colNames:['企业名称','提醒内容','ID','企业编号','类型', '提醒发出时间','是否确认','确认人员','关联业务编号','事物处理','提醒处理时间','最早得到提醒时间'],
		colNames:['提醒类型','提醒内容','企业编号','ID', '提醒发出时间','是否确认','确认人员','关联业务编号','事物处理','提醒处理时间','最早得到提醒时间'],
		colModel:[						
//			{name:'f0102',index:'f0102', width:100,align:'center'},
			{name:'f1002',index:'f1002', width:80,align:'center'},
			{name:'f1006',index:'f1006',width:150,align:'center'},	
			{name:'f1001',index:'f1001', width:95,align:'center',hidden:true},
			{name:'f0101',index:'f0101', width:95,align:'center',hidden:true}, 
			{name:'f1003',index:'f1003', width:95,align:'center'},
			{name:'f1004',index:'f1004', width:100,align:'center'},
			{name:'f1005',index:'f1005', width:95,align:'center'},		
			{name:'f1007',index:'f1007', width:95,align:'center'},
			{name:'f1008',index:'f1008', width:100,align:'center'},
			{name:'f1009',index:'f1009', width:95,align:'center'},		
			{name:'f1010',index:'f1010', width:80,align:'center'},
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

		caption: "提醒管理",		
		autowidth:true,//自动宽

	});

	//增删改查
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
		{ 	//navbar options
			edit: true,
			editicon : 'icon-pencil blue',
			editfunc:editLicence,
			add: true,
			addfunc: addLicence,
			addicon : 'icon-plus-sign purple',
			del: true,
			delicon : 'icon-trash red',
			delfunc:deleLicence,
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

 
