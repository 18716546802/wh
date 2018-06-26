var f0201;
var f0802;
var f0803;
var f0804;
var f0805;
var f0806;
var f0807;
var f0808;
var f0809;
var f0810;
var f0811;
var f0812;
var f0101;
var f0819;
var f0820;
var count;


$(document).ready(function() {

	
	f0809=$("#f0809").val();
	
		
	if(f0809=="不限时间"){
		$("#f0810").val("时间为不限时间");
		$("#f0812").val("时间为不限时间");
		$("#f0810").attr("disabled",true);
		$("#f0812").attr("disabled",true);
	}				
	
	count = 0;
	f0101 = JSON.parse(localStorage.getItem("companyID"));
	var companyName = JSON.parse(localStorage.getItem("companyName"));
	$("#companyName").text(companyName);

	$("#submitAddHideDanger").click(function() {

		if (check()) {
			
			console.log(f0811);
			
			if(count==0){	
				
				$.ajax({			
					type:"post",
					url:"/WH/job/addPurkingPeril",
					data:{
						f0101:f0101,
						f0802:f0802,
						f0803:f0803,
						f0804:f0804,
						f0805:f0805,
						f0806:f0806,
						f0807:f0807,
						f0808:f0808,
						f0809:f0809,
						f0810:f0810,
						f0811:f0811,
						f0812:f0812,
						f0819:f0819,
						f0820:f0820,
						imgCount:count
					},
					success:function(){	
						localStorage.setItem("companyID",JSON.stringify(f0101));
					   	
						showPage("/WH/jsp/companyHideDanger.jsp");
						
						//window.location.href="/WH/jsp/companyHideDanger.jsp";
					}
									
				});	
				
			}else{
				
				addHideDanger();
				
			}
			 
		}

	});

});

//整改类型

$("#f0809").change(function(){
		
	f0809=$("#f0809").val();
	if(f0809=="不限时间"){
		$("#f0810").val("时间为不限时间");
		$("#f0812").val("时间为不限时间");
		$("#f0810").attr("disabled",true);
		$("#f0812").attr("disabled",true);
	}else{
		$("#f0810").val("");
		$("#f0812").val("");
		$("#f0810").attr("disabled",false);
		$("#f0812").attr("disabled",false);	
	}
	
});


function checkValue() {
	
	 f0802=$("#f0802").val();
	 f0803=$("#f0803").val();
	 f0804=$("#f0804").val();
	 f0805=$("#f0805").val();
	 f0806=$("#f0806").val();
	 f0807=$("#f0807").val();
	 f0808=$("#f0808").val();
	 f0809=$("#f0809").val();
	 f0810=$("#f0810").val();
	 f0811=$("#f0811").val();
	 f0812=$("#f0812").val();
	 f0819=$("#f0819").val();
	 f0820=$("#f0820").val();
	  
	 if(f0809=="不限时间"){
		 
		 f0812="9999-12-30";
		 f0810=9999999;
	 }
	
	
	 if(parseInt(f0810)!=f0810){
		 
		 alert("添加失败,限制天数必须为整数");
		 return false;
	 }
	 
	 
	if (f0803==""||f0804==""||f0805==""||f0806==""||f0808==""||
	f0809==""||f0810==""||f0811==""||f0812==""||f0819=="") {
		alert("添加失败,请您完善信息");
		return false;
	}  else {
		
		
		
		return true;
	}

}

// 建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}

var uploader = new plupload.Uploader({
	browse_button : 'addPic', // 触发文件选择对话框的按钮，为那个元素id
	url : '/WH/job/addPurkingPeril', // 服务器端的上传页面地址
	flash_swf_url : 'js/Moxie.swf', // swf文件，当需要使用swf方式进行上传时需要配置该参数
	silverlight_xap_url : 'js/Moxie.xap', // silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
	multipart : true,
	filters : {
		mime_types : [ // 只允许上传图片和zip文件
		{
			title : "img",
			extensions : "jpg,gif,png,jpeg"
		} ],
		max_file_size : '9640kb', // 最大只能上传9640kb的文件
		prevent_duplicates : true
	// 不允许选取重复文件
	}
});

// 在实例对象上调用init()方法进行初始化
uploader.init();
//上传完成后执行的方法
uploader.bind('FileUploaded',function(uploader, file, res){
	
	
	localStorage.setItem("companyID",JSON.stringify(f0101));
	
	showPage("/WH/jsp/companyHideDanger.jsp");
 //window.location.href="/WH/jsp/companyHideDanger.jsp";
	
});
// 绑定各种事件，并在事件监听函数中做你想做的事
uploader.bind('FilesAdded', function(uploader, files) {

	if (files.length > 3) {
		alert("上传图片不能超过3张");
		return;
	}
	count = files.length;
	for (var i = 0; i < files.length; i++) {

		var str = getObjectURL(files[i].getNative());
		if (i == 0) {
			$("#upPicOne").attr("src", str);

		} else if (i == 1) {
			$("#upPicTwo").attr("src", str);

		} else {
			$("#upPicThree").attr("src", str);
		}
	}

});
function addDate(date,days){
	 var dateTemp = date.split("-");  
	 	var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式    
	    var millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);  
	    var rDate = new Date(millSeconds);  
	    var year = rDate.getFullYear();  
	    var month = rDate.getMonth() + 1;  
	    if (month < 10) month = "0" + month;  
	    var date = rDate.getDate();  
	    if (date < 10) date = "0" + date;  
	    return (year + "-" + month + "-" + date); 
	
}



function isLeapYear(year) {  
	return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);  
}

//先输入了f0811的情况
$("#f0810").blur(function(){
	var reg = new RegExp("^[0-9]*$");
	if(reg.test($("#f0810").val())){
		if($("#f0809").val() == "限期整改" && $("#f0811").val() != ''){
			$("#f0812").val(addDate($("#f0811").val(),$("#f0810").val()));
		}
	}else{
		alert("期限天数只能为整数");
	}
});
//先输入了f0810
$("#f0811").blur(function(){
	if($("#f0809").val() == "限期整改" && $("#f0810").val() != ''){
		$("#f0812").val(addDate($("#f0811").val(),$("#f0810").val()));
	}
});
function check(){
	

	if($("#f0809").val() == "限期整改" ){
		if($("#f0810").val() == ''){
			alert("请填写限期天数");
			$("#f0810").focus();
			return false;
		}

	}
	if($("#f0811").val() == 'yyyy-mm-dd'){
		alert("请填写发现日期");
		return false;
	}
	if($("#f0820").val() == ""){
		alert("请填写隐患类别");
		return false;
	}
	if($.trim($("#f0802").val()) == ""){
		alert("请填写隐患描述");
		$("#f0802").focus();
		return false;
	}
	
	
	 f0802=$("#f0802").val();
	 f0803=$("#f0803").val();
	 f0804=$("#f0804").val();
	 f0805=$("#f0805").val();
	 f0806=$("#f0806").val();
	 f0807=$("#f0807").val();
	 f0808=$("#f0808").val();
	 f0809=$("#f0809").val();
	 f0810=$("#f0810").val();
	 f0811=$("#f0811").val();
	 f0812=$("#f0812").val();
	 f0819=$("#f0819").val();
	 f0820=$("#f0820").val();
	 
	 
	 if(f0809=="不限时间"){
		 
		 f0812="9999-12-30";
		 f0810=9999999;
	 }
	
	
	
	
	
	return true;
}

function addHideDanger(){
	
	var obj = {
			f0101:f0101,
			f0802:f0802,
			f0803:f0803,
			f0804:f0804,
			f0805:f0805,
			f0806:f0806,
			f0807:f0807,
			f0808:f0808,
			f0809:f0809,
			f0810:f0810,
			f0811:f0811,
			f0812:f0812,
			f0819:f0819,
			f0820:f0820,
			imgCount:count
		};
	
	
		//设置某个特定的配置参数
		uploader.setOption('multipart_params', obj);
		//开始上传
		uploader.start();
		
}




 
