

function T06(){
	var f0601;
	var f0602;
	var f0603;
	var f0604;
	var id;
}


//得到当前数据
function getRowData(){
	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID	
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}

//修改
var updateRegion = function(){
	var rowData =getRowData();
	localStorage.setItem("rowData",JSON.stringify(rowData));  
	window.location.href="/WH/jsp/editRegion.jsp";
};

//添加
var addRegion = function(){
	window.location.href="/WH/jsp/addRegion.jsp";
};

//修改
var deleteRegion = function(){
		//删除数据
			var rowData =getRowData();
			var f0601=rowData.f0601;	
			$.ajax({
				type:"POST",
				url:"/WH/remind/deleteTree",
				data:{f0601:f0601},
				success:function(data){
					if(data.success){
				    	window.location.href="/WH/jsp/regionManagement.jsp";
					}else{
						alert("删除失败");
					} 
				}	
			});		
	}

$(document).ready(function(){
	
	

	//从后台获得数据
	 $.ajax({
		       	type:"GET",
		        url:"/WH/remind/getTree",//接口路径
		        success:function(data){
		        	var grid_data = new Array();
		        	
		        	var t06List = data.data;
		        	
		        	for(var i=0;i<t06List.length;i++){
		        		var t06 = new T06();
		        		t06.f0601 = t06List[i].f0601;
		        		t06.f0602 = t06List[i].f0602;
		        		
		        		if(t06List[i].f0603 == 0){
		        			t06.f0603 = "无";
		        			t06.f0604 = "一级节点";
		        		}else{
		        			t06.f0604 = "二级节点";
			        		for(var j=0;j<t06List.length;j++){
			        			
			        			if(t06List[i].f0603 == t06List[j].f0601){
			        				t06.f0603 = t06List[j].f0602;
			        				t06.id = t06List[j].f0601;
			        				break;
			        			}
			        		}
		        		}
		        		grid_data.push(t06);
		        	}
		        	showdata(grid_data);
		        	

 
	         } 
	   });
	 
		
function showdata(grid_data){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	jQuery(grid_selector).jqGrid({
		data: grid_data,
		datatype: "local",
		height: 250,
		colNames:['ID','区县/乡镇名称', '上级地址','节点等级','Pid'],
		colModel:[
			{name:'f0601', index:'f0601', width:'12%', align:"center", hidden:true, key:true},
			{name:'f0602', index:'f0602', width:'12%', align:"center"},
			{name:'f0603', index:'f0603', width:'12%', align:"center"},
			{name:'f0604', index:'f0604', width:'12%', align:"center"},
			{name:'PID',    index:'PID',  width:'12%', align:"center", hidden:true},
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


		caption: "地区管理",				
		autowidth: true//autowidth  是否自动适应宽度

	});
	
	//增删改查
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
			{ 	//navbar options 表格左下角的各个功能按钮
		edit: true,
		editicon : 'icon-pencil blue',
		editfunc:updateRegion,
		add: true,
		addicon : 'icon-plus-sign purple',
		addfunc:addRegion,
		del: true,
		delicon : 'icon-trash red',
		delfunc:deleteRegion,
		search: true,
		searchicon : 'icon-search orange',
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
			
			/*multipleGroup:true,
			showQuery: true
			*/
		}
	)
	
	
}
		
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
				 
});