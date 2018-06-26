//页面一加载先获取所有隐患
function danger(){
	var name;  //名称
	var level; //级别
	var company;  //企业
	var isBackup; //是否备案
}
dangerList = new Array();
$.ajax({
   type: "GET",
   url: "/WH/government/bigdanger",
   data: {f0601:$("#f0601").val()},
   dataType: "json",
   success: function(data){	    
	    //初始化数据
	    for(var i=0;i<data.length;i++){
	    	var d = new danger();
	    	d.name = data[i].f1102;
	    	d.company = data[i].t01.f0102;
	    	if(data[i].f1116 == null || data[i].f1116 == ""){
	    		d.level = "暂未评出等级";
	    	}else{
	    		d.level = data[i].f1116;
	    	}
	    	if(data[i].f1116 == null || data[i].f1116 == ""){
	    		d.isBackup = "否";
	    	}else{
	    		d.isBackup = "是";
	    	}
	    	dangerList.push(d);
	    }
	    init();
   }
});

$("#search").bind('click',function(){
	dangerList = [];
	var name = $("#f0102").val();
	$.ajax({
		   type: "GET",
		   url: "/WH/government/bigdanger",
		   data: {
			   f0102:name,
			   f0601:$("#f0601").val()
			   },
		   dataType: "json",
		   async:true,
		   success: function(data){
	    
			    //初始化数据
			    for(var i=0;i<data.length;i++){
			    	var d = new danger();
			    	d.name = data[i].f1102;
			    	d.company = data[i].t01.f0102;
			    	
			    	if(data[i].f1116 == null || data[i].f1116 == ""){
			    		d.level = "暂未评出等级";
			    	}else{
			    		d.level = data[i].f1116;
			    	}
			    	if(data[i].f1116 == null || data[i].f1116 == ""){
			    		d.isBackup = "否";
			    	}else{
			    		d.isBackup = "是";
			    	}
			    	dangerList.push(d);
			    }
			    
			    
			    init();
		   }
		});
})


//分页功能初始化
function init(){
	var nums = 10; //每页出现的数量
    
	var initPagination = function() {
		var num_entries = dangerList.length;
		// 创建分页
		$("#page").pagination(num_entries, {
			num_edge_entries: 1, //边缘页数
			num_display_entries: 5, //主体页数
			callback: pageselectCallback,
			items_per_page:nums //每页显示 nums 项
		});
	 }();

	function pageselectCallback(page_index, jq){
		var new_content = "";
		var i = page_index*nums;
		var j = i+nums;
		
		for(i;i < j && i<dangerList.length;i++){
			new_content += '<tr><td>' + dangerList[i].name +"</td>" +"<td>"+dangerList[i].level+"</td>" + "<td>"+dangerList[i].company+"</td>" +"<td>"+dangerList[i].isBackup+"</td>" +'</tr>';
		}
		
		$("#content").empty().append(new_content); //装载对应分页的内容
		return false;
	}
}