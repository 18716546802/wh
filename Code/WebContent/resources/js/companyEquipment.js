 

var companyID;//公司ID
var grid_data;//数据源

var lstIDAndName;


//得到当前数据
function getRowData(){
	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}

//添加设备
function addEquipment(){
	
	showPage("/WH/jsp/addEquipment.jsp");
	//window.location.href="/WH/jsp/addEquipment.jsp";	 
}

//修改设备
function editEquipment(){
	
	var rowData=getRowData();	
	var f0302=rowData.f0302;	
	var start=-1,end=-1;		 	
	for(var i=0;i<f0302.length;i++){
		
		if(f0302[i]==">" && start==-1){
			start=i;
		}
		if(f0302[i]=="<" ){
			end=i;	
		}
	}	
	f0302=f0302.substring(start+1,end);
	rowData.f0302=f0302;
	localStorage.setItem("rowData",JSON.stringify(rowData));  
	
	showPage("/WH/jsp/editEquipment.jsp");
//	window.location.href="/WH/jsp/editEquipment.jsp";
	
}
//删除设备
function deleteEquipment(){
	

	
	
	 if(confirm("您确定要删除嘛？")){
		 
		 var rowData=getRowData();
			var f0301=rowData.f0301;
			
			$.ajax({
				type:"get",
				url:"/WH/job/deleteEquipment",
				data:{f0301:f0301},
				success:function(data){	
					if(data.success){
						var companyID=rowData.f0101;
						localStorage.setItem("companyID",JSON.stringify(companyID));
				    	
						showPage("/WH/jsp/companyEquipment.jsp");
						//window.location.href="/WH/jsp/companyEquipment.jsp";
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
        url:"/WH/job/findAllEquipmentByCompany?f0101="+companyID,
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
				
			 companyID=lstIDAndName[i].id;
			 localStorage.setItem("companyID",JSON.stringify(lstIDAndName[i].id));
			 localStorage.setItem("companyName",JSON.stringify(lstIDAndName[i].name));
			 
			 
			 console.log(lstIDAndName[i].id);
			 console.log(lstIDAndName[i].name);
			 
			 showPage("/WH/jsp/companyEquipDocument2.jsp");
			 	
		}
	}
}






function showJqgrid(grid_data){	  
	
	
	lstIDAndName=new Array();
 	
	
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";	
	jQuery(grid_selector).jqGrid({
		data: grid_data ,
		datatype: "local",																														    
		height: 500,
		colNames:['企业编号','ID','设备名称','设备级别','生产厂家', '投用日期','注册证编号','厂内编号','设备类别','规格型号', '生产日期', '使用场所','使用证编号','定检周期', '主要性能参数', '是否过期'],
		colModel:[						
			{name:'f0101',index:'f0101', width:100,align:'center',hidden:true},
			{name:'f0301',index:'f0301',width:100,align:'center',hidden:true},
			{name:'f0302',index:'f0302', width:95,align:'center',formatter:function(cellvalue, options, rowObject){     var demp=new idAndName(); demp.id=rowObject.f0301; demp.name=rowObject.f0302; lstIDAndName.push(demp);        return "<a    onclick=getData("+rowObject.f0301+") style='color:#307ecc' href='#'>"+cellvalue+"</a>";}},
			{name:'f0303',index:'f0303', width:90,align:'center'},
			{name:'f0304',index:'f0304', width:100,align:'center'},
			{name:'f0305',index:'f0305', width:95,align:'center'},
			{name:'f0306',index:'f0306', width:100,align:'center'},
			{name:'f0307',index:'f0307', width:95,align:'center'},
			{name:'f0308',index:'f0308', width:95,align:'center'},
			{name:'f0309',index:'f0309', width:95,align:'center'},
			{name:'f0310',index:'f0310', width:95,align:'center'},
			{name:'f0311',index:'f0311', width:95,align:'center'},
			{name:'f0312',index:'f0312', width:95,align:'center'},
			{name:'f0313',index:'f0313', width:100,align:'center'},
			{name:'f0314',index:'f0314', width:100,align:'center'},
			{name:'f0315',index:'f0315', width:100,align:'center'},		 
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

			editurl: "./test.html",//保存地址
		caption: "特种设备及其安全",		
		autowidth:true,//自动宽

	});

	//增删改查
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
		{ 	//navbar options
			edit: true,
			editicon : 'icon-pencil blue',
			editfunc:editEquipment,
			add: true,
			addicon : 'icon-plus-sign purple',
			addfunc:addEquipment,
			del: true,
			delicon :'icon-trash red',
			delfunc:deleteEquipment,
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