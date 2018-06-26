var grid_data;



//得到当前数据
function getRowData(){
	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}


// 删除
var deleteLaws = function() {

	if (confirm("您确定要删除嘛？")) {

		var rowData = getRowData();
		var f0901 = rowData.f0901;
		$.ajax({
			type : "get",
			url : "/WH/lowcard/deleteLowInfo",
			data : {
				f0901 : f0901
			},
			success : function(data) {
				if (data.success) {
					window.location.href = "/WH/jsp/lawsAndRegulations.jsp";
				} else {
					alert("删除失败");
				}
			}
		});

	} else {

	}

}

// 添加
var addLaws = function(){
	window.location.href="/WH/jsp/addLaws.jsp";
}

//修改
var editLaws = function(){
	var rowData =getRowData();
	localStorage.setItem("rowData",JSON.stringify(rowData));  
	window.location.href="/WH/jsp/editLaws.jsp";
}

$(document).ready(function(){

	$.ajax({
       	type:"GET",
        url:"/WH/lowcard/findAllLows",
        success:function(data){   
        	
        		console.log(data.data);
 
        		grid_data=data.data;	  
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";	
				jQuery(grid_selector).jqGrid({
					data: grid_data ,
					datatype: "local",																														    
					height: 400,
					colNames:['ID','企业编号','文件名称','文件GUID','文件类型', '文件类级别','上传人员','文件访问级别','所属企业'],
					colModel:[						
						{name:'f0901', index:'f0901', width:100, align:'center',hidden:true},
						{name:'f0101', index:'f0101', width:100, align:'center',hidden:true},
						{name:'f0902', index:'f0902', width:95,  align:'left', formatter:function (cellvalue,options, rowObject){return "<a href='../lowcard/download?f0901="+rowObject.f0901+"'>"+cellvalue+'</a>';}},
						{name:'f0903', index:'f0903', width:95,  align:'center',hidden:true},
						{name:'f0904', index:'f0904', width:140, align:'center'},
						{name:'f0905', index:'f0905', width:95,  align:'center'},
						{name:'f0906', index:'f0906', width:100, align:'center'},
						{name:'f0907', index:'f0907', width:95,  align:'center'},
						{name:'t01.f0102', index:'t01.f0102', width:95,  align:'center'},
					],		
					viewrecords:true,
					rowNum:10,
					rowList:[10,20,30],
					pager : pager_selector,
					altRows: true,
					//toppager: true,						
					multiselect: true, 	/*定义是否可以多选*/
					//multikey: "ctrlKey",
			        multiboxonly: true, 	/*只有当multiselect = true.起作用，当multiboxonly 为ture时只有选择checkbox才会起作用*/				
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							var icons = $(".ui-icon-refresh");//隐藏刷新
							icons.hide();//隐藏刷新
							styleCheckbox(table);
							updateActionIcons(table);
							updatePagerIcons(table);
							//enableTooltips(table);
						}, 0);
					},
			
		
					caption: "企业详细证照",				
					autowidth: true//autowidth  是否自动适应宽度
			
				});

				//增删改查
				jQuery(grid_selector).jqGrid('navGrid',pager_selector,
					{ 	//navbar options
						edit: true,
						editicon : 'icon-pencil blue',
						editfunc:editLaws,
						add: true,
						addicon : 'icon-plus-sign purple',
						addfunc:addLaws,
						del: true,
						delicon : 'icon-trash red',
						delfunc:deleteLaws,
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
						},
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
			
				function enableTooltips(table) {
					$('.navtable .ui-pg-button').tooltip({container:'body'});
					$(table).find('.ui-pg-div').tooltip({container:'body'});
				}    
        	}
        	 
       
   });
	
	
});