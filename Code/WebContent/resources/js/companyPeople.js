

var grid_data;//数据源
var companyName;//公司名
var companyID;//公司ID 


//得到当前数据
function getRowData(){
	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}

//添加人员
function addPeople(){	
	
	
	showPage("/WH/jsp/addPeople.jsp");
	//window.location.href="/WH/jsp/addPeople.jsp";
	 
}
//修改人员
function editPeople(){
	var rowData =getRowData();
	localStorage.setItem("rowData",JSON.stringify(rowData));  
	
	showPage("/WH/jsp/editPeople.jsp");
	//window.location.href="/WH/jsp/editPeople.jsp";
}
//删除人员
function delePeople(){
	
	 if(confirm("您确定要删除嘛？")){
		 	
		 var rowData =getRowData();
			var f0701=rowData.f0701;	

			$.ajax({
				type:"get",
				url:"/WH/person/deleteQualificationsByID",
				data:{f0701:f0701},
				success:function(data){
					if(data.success){
						
						showPage("/WH/jsp/companyPeople.jsp");						
				    	//window.location.href="/WH/jsp/companyPeople.jsp";
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
    
    localStorage.setItem("companyID",JSON.stringify(companyID));
    localStorage.setItem("companyName",JSON.stringify(companyName));
    
	$("#companyName").text(companyName);
	
	$.ajax({
       	type:"GET",
        url:"/WH/person/selectListQualificationsByEnterpriseId?f0101="+companyID,
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
		colNames:['ID','企业编号','姓名','证书编号','有效时间','颁发单位','状态','证件名称','资格证书类型'],
		colModel:[						
			{name:'f0701',index:'f5001', width:100,align:'center',hidden:true},
			{name:'f0101',index:'f0101',width:100,align:'center' ,hidden:true},
			{name:'f0702',index:'f0702',width:100,align:'center' },	
 			{name:'f0703',index:'f0703',width:100,align:'center' },	
 			{name:'f0704',index:'f0704',width:100,align:'center' },	
 			{name:'f0705',index:'f0705',width:100,align:'center' },	
 			{name:'f0706',index:'f0706',width:100,align:'center' },	
 			{name:'f0707',index:'f0707',width:100,align:'center' },	
 			{name:'f0708',index:'f0708',width:100,align:'center' },	
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

		caption: "人员资质",		
		autowidth:true,//自动宽

	});

	//增删改查
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
		{ 	//navbar options
			edit: true,
			editicon : 'icon-pencil blue',
			editfunc:editPeople,
			add: true,
			addfunc: addPeople,
			addicon : 'icon-plus-sign purple',
			del: true,
			delicon : 'icon-trash red',
			delfunc:delePeople,
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

 
