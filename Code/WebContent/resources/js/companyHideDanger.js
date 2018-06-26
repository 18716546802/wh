 

var companyID;//公司ID
var grid_data;//数据源


//得到当前数据
function getRowData(){

	var id = $("#grid-table").jqGrid("getGridParam", "selrow");      //获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); //当前行数据
	return rowData;
}

//添加隐患
function addHideDanger(){

	
	showPage("/WH/jsp/addHideDanger.jsp");
	
	//window.location.href="/WH/jsp/addHideDanger.jsp";
	
}

//修改隐患
function editHideDanger(){
	
	var rowData=getRowData();
	  	  
	
    if(rowData.f0813!=""){
    	//去除a
    	rowData.f0813=getWord(rowData.f0813);
    	//去除span
    	rowData.f0813=getWord(rowData.f0813);
    }	
    
    if(rowData.f0802!=""){
    	
    	rowData.f0802=getWord(rowData.f0802);
    }
    
    console.log(rowData.f0813);
     
	localStorage.setItem("rowData",JSON.stringify(rowData));
	localStorage.setItem("todayHideDanger",JSON.stringify(-1));
	
	showPage("/WH/jsp/editHideDanger.jsp")
	
	
	//window.location.href="/WH/jsp/editHideDanger.jsp";
}
//删除隐患
function deleteHideDanger(){
		
	 if(confirm("您确定要删除嘛？")){

			var rowData=getRowData();
			
			console.log(rowData);

			$.ajax({
				type:"post",
				url:"/WH/job/deletePurkingPeril",
				data:{f0801:rowData.f0801},
				success:function(data){	
					if(data.success){
						var companyID=rowData.f0101;
						localStorage.setItem("companyID",JSON.stringify(companyID));
						
						
						showPage("/WH/jsp/companyHideDanger.jsp");
						//window.location.href="/WH/jsp/companyHideDanger.jsp";
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
        url:"/WH/job/findAllPurkingPeril?id="+companyID,
        success:function(data){   
        	if(data.success){    
        		
        		grid_data=data.data;
        		showJqgrid(grid_data);
        	}else{
        		grid_data=[];
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
		colNames:['企业编号','ID','隐患描述','参检人员','整改情况','企业确认人员','复查人员','复查结果','企业整改人员','整改类型','限期天数','隐患发现时间','最后整改时间','隐患图片','隐患图片','隐患图片','整改后的隐患图片','整改后的隐患图片','整改后的隐患图片','是否重大危险源','隐患类别','ID'],
		colModel:[						
			{name:'f0101',index:'f0101', width:200,align:'center',hidden:true},
			{name:'f0801',index:'f0801', width:200,align:'center',hidden:true},
			{name:'f0802',index:'f0802', width:150,align:'center',formatter:function(cellvalue, options, rowObject){ return "<a  style='color:#307ecc'   onclick=getData("+rowObject.f0801+")   href='#'>"+cellvalue+"</a>";}},
			{name:'f0803',index:'f0803', width:100,align:'center'},
			{name:'f0804',index:'f0804', width:100,align:'center'},
			{name:'f0805',index:'f0805', width:200,align:'center',hidden:true},
			{name:'f0806',index:'f0806', width:200,align:'center',hidden:true},
			{name:'f0807',index:'f0807', width:100,align:'center',hidden:true},
			{name:'f0808',index:'f0808', width:200,align:'center',hidden:true},
			{name:'f0809',index:'f0809', width:100,align:'center'},
			{name:'f0810',index:'f0810', width:200,align:'center',hidden:true},
			{name:'f0811',index:'f0811', width:100,align:'center',hidden:true},
			{name:'f0812',index:'f0812', width:100,align:'center'},
			{name:'f0813',index:'f0813', width:100,align:'center',formatter:function(cellvalue, options, rowObject){   if(rowObject.f0813!=null)return "<a  target='_blank' style='color:#307ecc' href='"+rowObject.f0813+"'>"+"查看图片"+"<span style='display:none'>"+rowObject.f0813+"</span>"+"</a>";else return "";}},
			{name:'f0814',index:'f0814', width:100,align:'center',hidden:true},
			{name:'f0815',index:'f0815', width:100,align:'center',hidden:true},		
			{name:'f0816',index:'f0816', width:100,align:'center',formatter:function(cellvalue, options, rowObject){   if(rowObject.f0816!=null)return "<a  target='_blank' style='color:#307ecc' href='"+rowObject.f0816+"'>"+"查看图片"+"<span style='display:none'>"+rowObject.f0816+"</span>"+"</a>"; else return ""; }},
			{name:'f0817',index:'f0817', width:100,align:'center',hidden:true},
			{name:'f0818',index:'f0818', width:100,align:'center',hidden:true},
			{name:'f0819',index:'f0819', width:100,align:'center',hidden:true},
			{name:'f0820',index:'f0820', width:100,align:'center',hidden:true},
			{name:'f1101',index:'f1101', width:100,align:'center',hidden:true},
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