 

var companyID;//公司ID

var T08;

function t08(){
	
	var f0101;
	var f0102;
	var f0801;
	var f0802;
	var f080301;
	var f080302;
	var f080303;
	var f0811;
	var f0813;
	
};

//得到数据
function getRowData(){
	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}

 
 

//添加隐患
function addHideDanger(){

	
	 alert("操作失败,不能添加今日隐患");
	
}

//修改隐患
function editHideDanger(){
	
	var rowData=getRowData();
	var f0801=rowData.f0801;
	
	$.ajax({
		url:"/WH/remind/findOnePurkingPeril",
		type:"get",
		data:{
			f0801:f0801,
		},
		success:function(data){				
			localStorage.setItem("todayHideDanger",JSON.stringify(1));
			localStorage.setItem("companyID",JSON.stringify(rowData.f0101));
			localStorage.setItem("companyName",JSON.stringify(rowData.f0102));
			localStorage.setItem("rowData",JSON.stringify(data.data));
			
			
			showPage("/WH/jsp/editHideDanger.jsp");
			//window.location.href="/WH/jsp/editHideDanger.jsp";
		}
		
	});
}
//删除隐患
function deleteHideDanger(){
	
	 alert("操作失败,不能删除今日隐患");
}



$(document).ready(function(){
	
	var companyName=JSON.parse(localStorage.getItem("companyName"));  
	companyID = JSON.parse(localStorage.getItem("companyID"));  
	
	$("#companyName").text(companyName);

	
	$.ajax({
       	type:"GET",
        url:"/WH/job/findTodayPurkingPeril",
        success:function(data){   

        	console.log(data);
        	
        	if(data.success){
        		       		
        		T08=data.data;
            	var dempData=data.data;
            	var grid_data=new Array();
            	
            	for(var i=0;i<dempData.length;i++){           		
            		var demp=new t08();       		
            		demp.f0101=dempData[i].t01.f0101;
            		demp.f0102=dempData[i].t01.f0102;
            		demp.f0801=dempData[i].f0801;
            		demp.f0802=dempData[i].f0802;
            		if(dempData[i].f0803 == null || dempData[i].f0803 == ""){
            			demp.f080301="";
            			demp.f080302="";
            			demp.f080303="";
            		}else{
                   		var str=dempData[i].f0803.split(";");
                		if(str.length==1){   			
                			demp.f080301=str[0];
                			demp.f080302="";
                			demp.f080303="";
             
                		}else if(str.length==2){
                			demp.f080301=str[0];
                			demp.f080302=str[1];
                			demp.f080303="";
                		}else{
                			demp.f080301=str[0];
                			demp.f080302=str[1];
                			demp.f080303=str[2];
                		}
            		}
            		if(dempData[i].f0813==null){
            			demp.f0813="";
            		}else{    			
            			demp.f0813=dempData[i].f0813;	
            		}      		 
            		demp.f0811=dempData[i].f0811;	 
            		grid_data.push(demp);
            	}	
            	
            	console.log(grid_data);
            	
             
            	showJqgrid(grid_data);
            	
            	          	
        	}else{  
        		
        		 
        		
        		var grid_data=[];
        		showJqgrid(grid_data);
        		
        	}

        	       		
        }
        
    	
         
   });
	
});


function getWord(str){
	
    var start=-1,end=-1;
	
	for(var i=0;i<str.length;i++){

        if(str[i]==">" && start==-1){
            start=i;
        }
        if(str[i]=="<" ){
            end=i;
        }
   }

	str=str.substring(start+1,end);
	
	return str;

}


function getData(id){
	
	 
	localStorage.setItem("companyID",JSON.stringify(id));
	 
	companyID=id;
	showPage("/WH/jsp/companyHideDangerDetail.jsp");
			 		 
}




function showJqgrid(grid_data){	  
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";	
	jQuery(grid_selector).jqGrid({
		data: grid_data ,
		datatype: "local",															
											    
		height: 500,
		colNames:['ID','企业名称','T08ID','隐患描述','万汇人员','专家','政府人员','日期','图片'],
		colModel:[						
			{name:'f0101',index:'f0101', width:200,align:'center',hidden:true},
			{name:'f0102',index:'f0102', width:150,align:'center'},
			{name:'f0801',index:'f0801', width:200,align:'center',hidden:true},
			{name:'f0802',index:'f0802', width:150,align:'center',formatter:function(cellvalue, options, rowObject){ return "<a  style='color:#307ecc'   onclick=getData("+rowObject.f0801+")   href='#'>"+cellvalue+"</a>";}},
			{name:'f080301',index:'f080301', width:100,align:'center'},
			{name:'f080302',index:'f080302', width:100,align:'center'},
			{name:'f080303',index:'f080303', width:100,align:'center'},
			{name:'f0811',index:'f0811', width:100,align:'center'},
			{name:'f0813',index:'f0813', width:100,align:'center',formatter:function(cellvalue, options, rowObject){   if(rowObject.f0813!=null && rowObject.f0813!="")return "<a  target='_blank' style='color:#307ecc' href='"+rowObject.f0813+"'>"+"查看图片"+"<span style='display:none'>"+rowObject.f0813+"</span>"+"</a>";else return "";}},	 
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
		caption: "隐患管理",		
		autowidth:true,//自动宽
	});

	//增删改查
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
		{ 	//navbar options
			edit: true,
			editicon : 'icon-pencil blue',
			editfunc:editHideDanger,
			add: true,
			addicon : 'icon-plus-sign purple',
			addfunc:addHideDanger,
			del: true,
			delicon :'icon-trash red',
			delfunc:deleteHideDanger,
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