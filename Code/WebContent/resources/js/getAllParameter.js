
//定义新类

function T50(){
	var f5001;
	var f5002;
	var f5003;//日期
	var f5004;
	var f5005;//业务员
	var f5006;//专家
	var f5007;//政府人员
	var f5008;
};

$(document).ready(function(){	 
	
	
	$.ajax({		
		type:"get",
		url:"/WH/job/getAllTodayParameters",
		success:function(data){		
			if(data.success){
				var data=data.data;
				var grid_data=new Array();
				for(var i=0;i<data.length;i++){
					//150000017 记录有其他用处，不是每日参数
					if(data[i].f5005 == "999"){
						continue;
					}
					
					var dempData=new T50();
					dempData.f5001=data[i].f5001;
					//建立临时项用于分割
					var demp=new Array();				
					demp=data[i].f5002.split("+");
					//处理员工ID和日期
					for(var j=0;j<demp.length;j++){
						if(j==0){
							dempData.f5002=demp[j];
						}else{
							var dempDate=demp[j];
							var year=null,month=null,day=null;					
							year=dempDate[0]+dempDate[1]+dempDate[2]+dempDate[3];
							month=dempDate[4]+dempDate[5];
							day=dempDate[6]+dempDate[7];
							var Date=year+"-"+month+"-"+day;
							dempData.f5003=Date;
						}
					}
					
					dempData.f5004=data[i].f5003;
					demp=data[i].f5004.split(";");
					//处理政府检查人员
					for(var j=0;j<demp.length;j++){
						
						if(j==0){
							dempData.f5005=demp[j];
						}else if(j==1){
							dempData.f5006=demp[j];
						}else{
							dempData.f5007=demp[j];
						}
					}
					dempData.f5008=data[i].f5005;
					grid_data.push(dempData);
				}
				
				showJqgrid(grid_data);
			}else{
				grid_data=[];
				showJqgrid(grid_data);
			}

		}
	});
	  
});


//得到当前数据
function getRowData(){
	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}



//编辑参数
function editParamter(){	
	var rowData=getRowData();
	localStorage.setItem("rowData",JSON.stringify(rowData));  
	
	
	showPage("/WH/jsp/editParameter.jsp");
	
//	window.location.href="/WH/jsp/editParameter.jsp";
}
//添加参数
function addParameter(){
	
	showPage("/WH/jsp/addParameter.jsp");
	
	//window.location.href="/WH/jsp/addParameter.jsp";
}

//显示数据
function showJqgrid(grid_data){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";	
	jQuery(grid_selector).jqGrid({
		data: grid_data ,
		datatype: "local",																														    
		height: 500,
		colNames:['ID','业务员','专家','政府人员','日期','备注'],
		colModel:[						
			{name:'f5001',index:'f5001', width:100,align:'center',hidden:true},
			{name:'f5005',index:'f5005',width:100,align:'center' },
			{name:'f5006',index:'f5006',width:100,align:'center' },	
 			{name:'f5007',index:'f5007',width:100,align:'center' },	
 			{name:'f5003',index:'f5003',width:100,align:'center' },
 			{name:'f5008',index:'f5008',width:100,align:'center',hidden:true},	
 			
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
			var delIcons=$(".icon-trash");
			delIcons.hide();//隐藏删除
	
			styleCheckbox(table);						
			updateActionIcons(table);
			updatePagerIcons(table);
			}, 0);
		},

		caption: "参数设置",		
		autowidth:true,//自动宽

	});

	//增删改查
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
		{ 	//navbar options
			edit: true,
			editicon : 'icon-pencil blue',
			editfunc:editParamter,
			add: true,
			addfunc: addParameter,
			addicon : 'icon-plus-sign purple',
			del: true,
			delicon : 'icon-trash red',
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
