function equipment(){
	var name;	//名称	
	var result; //检测结论
	var date; //检测日期
	var company;//所属企业
}

dangerList = new Array();

$.ajax({
   type: "GET",
   url: "/WH/government/SpecialEquipment",
   data: {f0601:$("#f0601").val()},
   dataType: "json",
   success: function(data){
	    
	    //初始化数据
	    for(var i=0;i<data.length;i++){
	    	var d = new equipment();
	    	if(data[i].t04.length == 0){
		    	d.name = data[i].f0302;
		    	d.result = "暂无";
		    	d.date = "暂无";
		    	d.company = data[i].t01.f0102;
		    	dangerList.push(d);
	    	}else{
	    		for(j=0;j<data[i].t04.length;j++){
			    	d.name = data[i].t04[j].f0402;
			    	d.result = data[i].t04[j].f0404;
			    	d.date = data[i].t04[j].f0403;
			    	d.company = data[i].t01.f0102;
			    	dangerList.push(d);
	    		}
	    	}
	    }
	    init();
   }
});

$("#search").bind('click',function(){
	dangerList = [];
	console.log(111);
	var name = $("#f0102").val();
	$.ajax({
		   type: "GET",
		   url: "/WH/government/SpecialEquipment",
		   data: {
			   f0102:name,
			   f0601:$("#f0601").val()
		   },
		   dataType: "json",
		   async:true,
		   success: function(data){
			   	if(data.length == 0){
			   		alert("未查询到相关数据");
			   	}
			    //初始化数据
			    for(var i=0;i<data.length;i++){
			    	var d = new equipment();
			    	if(data[i].t04.length == 0){
				    	d.name = data[i].f0302;
				    	d.result = "暂无";
				    	d.date = "暂无";
				    	d.company = data[i].t01.f0102;
				    	dangerList.push(d);
			    	}else{
			    		for(j=0;j<data[i].t04.length;j++){
					    	d.name = data[i].t04[j].f0402;
					    	d.result = data[i].t04[j].f0404;
					    	d.date = data[i].t04[j].f0403;
					    	d.company = data[i].t01.f0102;
					    	dangerList.push(d);
			    		}
			    	}
			    }
			    
			    
			    init();
		   }
		});
})


//分页功能初始化
function init(){
	var nums = 7; //每页出现的数量
    
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
			new_content += '<tr><td>' + dangerList[i].name +"</td>" +"<td>"+dangerList[i].company +"</td>"+"<td>"+dangerList[i].date +"</td>" + "<td>"+dangerList[i].result+"</td>"+'</tr>';
		}
		
		$("#content").empty().append(new_content); //装载对应分页的内容
		return false;
	}
}

//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate(dateStr) {
    var date = new Date();
    var seperator1 = "-";
    
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    
    
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    if(currentdate > string2date(dateStr)){
    	return false;
    }else{
    	return true;
    }
}

function string2date(str){
	  return new Date(Date.parse(str.replace(/-/g,  "/")));
}