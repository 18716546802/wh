//页面一加载先获取所有隐患
function danger(){
	var name;  //名称
	var level; //级别
	var company;  //企业
	var isBackup; //是否备案
	var type; //类别
	var limtDay; //期限天数
	var describe; //隐患描述
	var isExpired;//是否过期
	var findDate; //发现时间
}
dangerList = new Array();


$.ajax({
   type: "GET",
   url: "/WH/government/hiddenDanger",
   data: {
	   f0601:$("#f0601").val()
	   },
   dataType: "json",
   success: function(data){
    
	    //初始化数据
	    for(var i=0;i<data.length;i++){
	    	var d = new danger();
	    	//隐患描述
	    	d.describe = data[i].f0802;
	    	//所属企业
	    	d.company = data[i].t01.f0102;
	    	//是否过期
	    	if(getNowFormatDate(data[i].f0812)){
	    		d.isExpired = "否";
	    	}else{
	    		d.isExpired = "是";
	    	}
	    	//类别
	    	d.type = data[i].f0820;
	    	//过期时间
	    	d.limtDay = data[i].f0812;
	    	
	    	dangerList.push(d);
	    }
	    init();
   }
});

$("#search").bind('click',function(){
	dangerList = [];
	console.log(111);
	var name = $("#f0102").val();
	var t = $("#companyType").val();
	var id = $("#f0601").val();
	$.ajax({
		   type: "GET",
		   url: "/WH/government/hiddenDanger",
		   data: {
			   f0102:name,
			   type:t,
			   f0601:id
		   },
		   dataType: "json",
		   async:true,
		   success: function(data){
			   	if(data.length == 0){
			   		alert("未查询到相关数据");
			   	}
			    //初始化数据
			    for(var i=0;i<data.length;i++){
			    	var d = new danger();
			    	//隐患描述
			    	d.describe = data[i].f0802;
			    	//所属企业
			    	d.company = data[i].t01.f0102;
			    	//是否过期
			    	if(getNowFormatDate(data[i].f0812)){
			    		d.isExpired = "否";
			    	}else{
			    		d.isExpired = "是";
			    	}
			    	//类别
			    	d.type = data[i].f0820;
			    	//过期时间
			    	d.limtDay = data[i].f0812;
			    	
			    	dangerList.push(d);
			    }
			    
			    
			    init();
		   }
		});
})


//分页功能初始化
function init(){
	var nums = 5; //每页出现的数量
    
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
		/*
		 *               <tr>
				<td>重庆科技雪域</td>
				<td>电气</td>
				<td>2017-05-05</td>
				<td>30</td>
				<td>电线过多，裸露线头</td>
				<td>否</td>
              </tr>
		 * 
		 * 
		 * 
		 * */
		for(i;i < j && i<dangerList.length;i++){
			new_content += '<tr><td>' + dangerList[i].company +"</td>" +"<td>"+dangerList[i].type+"</td>" + "<td>"+dangerList[i].limtDay+"</td>" +"<td>"+dangerList[i].describe+"</td>" +"<td>"+dangerList[i].isExpired+"</td>" +'</tr>';
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