

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
	
	
	showPage("/WH/jsp/addLicence.jsp");
	
	//window.location.href="/WH/jsp/addLicence.jsp";
}
//修改证照
function editLicence(){
	var rowData =getRowData();
	localStorage.setItem("rowData",JSON.stringify(rowData));  
	
	showPage("/WH/jsp/editLicence.jsp");
	//window.location.href="/WH/jsp/editLicence.jsp";
}
//删除数据
function deleLicence(){

	
	 if(confirm("您确定要删除嘛？")){

		 var rowData =getRowData();
			var f0501=rowData.f0501;	
			$.ajax({
				type:"get",
				url:"/WH/Qualification/deleteQInfo",
				data:{f0501:f0501},
				success:function(data){
					if(data.success){
						var companyID=rowData.f0101;
						localStorage.setItem("companyID",JSON.stringify(companyID));
				    	
						showPage("/WH/jsp/companyLicence.jsp");
						//window.location.href="/WH/jsp/companyLicence.jsp";
					}else{
						alert("删除失败");
					} 
				}	
			});		 
		 
     }else{
 	 

     }
	
}

 

$(document).ready(function(){
	
	companyName =JSON.parse(localStorage.getItem("companyName")); 
    companyID = JSON.parse(localStorage.getItem("companyID"));  
    
   //console.log(companyID);
    
	$("#companyName").text(companyName);
	
	$.ajax({
       	type:"GET",
        url:"/WH/Qualification/findAllEnterpriseQById?f0101="+companyID,
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


//显示数据
function showJqgrid(grid_data){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";	
	jQuery(grid_selector).jqGrid({
		data: grid_data ,
		datatype: "local",																														    
		height: 500,
		colNames:['ID','企业编号','证照名称', '证照编号', '有效日期','证照是否有效'],
		colModel:[						
			{name:'f0101',index:'f0101', width:100,align:'center',hidden:true},
			{name:'f0501',index:'f0501',width:100,align:'center',hidden:true},
			{name:'f0502',index:'f0502', width:100,align:'center'},
			{name:'f0503',index:'f0503', width:100,align:'center'},
			{name:'f0504',index:'f0504', width:100,align:'center'},
			{name:'f0505',index:'f0505', width:100,align:'center'}, 	
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

		caption: "企业详细证照",		
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

 
