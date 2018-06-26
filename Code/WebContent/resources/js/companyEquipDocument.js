 

var companyID;//公司ID
var grid_data;//数据源


//得到当前数据
function getRowData(){
	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}

//添加文件
function addDocument(){
	
	 
	
	window.location.href="/WH/jsp/addEquipDocument.jsp";
}

//编辑文件
function editDocument(){
	
	var rowData=getRowData();
	localStorage.setItem("rowData",JSON.stringify(rowData));
	
	window.location.href="/WH/jsp/editEquipDocument.jsp";
		
}
//删除文件
function deleteDocument(){
	
	var rowData=getRowData();
	var f0401=rowData.f0401;
	var f0301=rowData.f0301;
	
	 if(confirm("您确定要删除嘛？")){
		 
		 	$.ajax({
				type:"get",
				url:"/WH/lowcard/deleteCheckInfoByPrimaryKey",
				data:{f0401:f0401},
				success:function(data){	
					if(data.success){
						localStorage.setItem("f0301",JSON.stringify(f0301));
			   	    	window.location.href="/WH/jsp/companyEquipDocument.jsp?f0301="+f0301;					 
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
	
	console.log(f0301);
	localStorage.setItem("f0301",JSON.stringify(f0301));
	localStorage.setItem("companyName",JSON.stringify(companyName));
	
	$("#companyName").text(companyName);

	
	$.ajax({
       	type:"GET",
        url:"/WH/lowcard/findAllCheckInfoByE?f0301="+f0301,
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
		height: 400,
		colNames:['ID','ID','安全附件名称','安全附件检测时间', '检测结论', '检测报告编号','附件检测周期','下次检测时间','是否过期'],
		colModel:[			
				{name:'f0301',index:'f0301', width:100,align:'center',editable: true,hidden:true},
				{name:'f0401',index:'f0401', width:100,align:'center',editable: true,hidden:true},
				{name:'f0402',index:'f0402', width:100,align:'center',editable: true },
				{name:'f0403',index:'f0403', width:100,align:'center',editable: true },
				{name:'f0404',index:'f0404', width:100,align:'center',editable: true },
				{name:'f0405',index:'f0405', width:100,align:'center',editable: true },
				{name:'f0406',index:'f0406', width:100,align:'center',editable: true},
				{name:'f0407',index:'f0407', width:100,align:'center',editable: true},
				{name:'f0408',index:'f0408', width:100,align:'center',editable: true},

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
			enableTooltips(table);
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
			editfunc:editDocument,
			add: true,
			addicon : 'icon-plus-sign purple',
			addfunc:addDocument,
			del: true,
			delicon :'icon-trash red',
			delfunc:deleteDocument,
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

	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({container:'body'});
		$(table).find('.ui-pg-div').tooltip({container:'body'});
	}    
	
}