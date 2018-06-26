 

var companyID;//公司ID
var grid_data;//数据源


//得到当前数据
function getRowData(){
	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}

//添加危险
function addDanger(){
	
	
	showPage("/WH/jsp/addDanger.jsp");
	//window.location.href="/WH/jsp/addDanger.jsp";	

}

//修改危险
function editDanger(){
	
	var rowData=getRowData();
	localStorage.setItem("rowData",JSON.stringify(rowData));
	
	showPage("/WH/jsp/eidtDanger.jsp");
	
	//window.location.href="/WH/jsp/eidtDanger.jsp";
	
}
//删除设备
function deleteDanger(){
	
	var rowData=getRowData();
	var f1101=rowData.f1101;
	
	if(confirm("您确定要删除嘛？")){

		$.ajax({
			type:"get",
			url:"/WH/danger/deleteDanger",
			data:{f1101:f1101},
			success:function(data){	
				if(data.success){
					var companyID=rowData.f0101;
					localStorage.setItem("companyID",JSON.stringify(companyID));
			    	
					showPage("/WH/jsp/companyBigDanger.jsp");
					//window.location.href="/WH/jsp/companyBigDanger.jsp";
				}else{
					alert("删除失败");
				} 
			}
			
		});
		 	 
     }else{
 	 

     }	
	
}



$(document).ready(function(){
	
	var companyName=JSON.parse(localStorage.getItem("companyName"));  
	companyID = JSON.parse(localStorage.getItem("companyID"));  
	
	$("#companyName").text(companyName);

	
	$.ajax({
       	type:"GET",
        url:"/WH/danger/findAllDangerCom?f0101="+companyID,
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


function showJqgrid(grid_data){	  
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";	
	jQuery(grid_selector).jqGrid({
		data: grid_data ,
		datatype: "local",																														    
		height: 500,
		colNames:['企业编号','ID','名称','地址','投用时间','编号','规模','是否位于化工园区','工业园区名称','周边重点防护目标描述','厂区边界外500m范围内人数估算值','主要监控措施','三年内危险化学品事故情况','备案编号','负责人','等级','是否在政府备案',],
		colModel:[						
			{name:'f0101',index:'f0101', width:100,align:'center',hidden:true},
			{name:'f1101',index:'f1101',width:100,align:'center',hidden:true },
			{name:'f1102',index:'f1102',width:70,align:'center' },	
 			{name:'f1103',index:'f1103',width:110,align:'center' },	
 			{name:'f1104',index:'f1104',width:100,align:'center' },	
 			{name:'f1105',index:'f1105',width:100,align:'center' },	
 			{name:'f1106',index:'f1106',width:100,align:'center',hidden:true },	
 			{name:'f1107',index:'f1107',width:100,align:'center' },	
 			{name:'f1108',index:'f1108',width:100,align:'center' },
 			{name:'f1109',index:'f1109',width:140,align:'center',hidden:true },
			{name:'f1110',index:'f1110',width:100,align:'center' ,hidden:true},	
 			{name:'f1112',index:'f1112',width:100,align:'center',hidden:true },	
 			{name:'f1113',index:'f1113',width:100,align:'center' ,hidden:true},	
 			{name:'f1114',index:'f1114',width:100,align:'center' },	
 			{name:'f1115',index:'f1115',width:100,align:'center' },	
 			{name:'f1116',index:'f1116',width:100,align:'center' },
 			{name:'f1117',index:'f1117',width:100,align:'center' },
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
		caption: "重大危险源",		
		autowidth:true,//自动宽
	});

	//增删改查
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
		{ 	//navbar options
			edit: true,
			editicon : 'icon-pencil blue',
			editfunc:editDanger,
			add: true,
			addicon : 'icon-plus-sign purple',
			addfunc:addDanger,
			del: true,
			delicon :'icon-trash red',
			delfunc:deleteDanger,
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