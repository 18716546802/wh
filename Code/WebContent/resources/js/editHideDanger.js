var f0801;
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
var count;//图片数量
var todayHideDanger;
 

$(document).ready(function() {


		 
	todayHideDanger=JSON.parse(localStorage.getItem("todayHideDanger"));
	 

	count = 0;
	f0101 = JSON.parse(localStorage.getItem("companyID"));
	var companyName = JSON.parse(localStorage.getItem("companyName"));
	var rowData=JSON.parse(localStorage.getItem("rowData"));
	
	

	
	 $("#f0802").val(rowData.f0802);
	 $("#f0803").val(rowData.f0803);
	 $("#f0804").val(rowData.f0804);
	 $("#f0805").val(rowData.f0805);
	 $("#f0806").val(rowData.f0806);
	 $("#f0807").val(rowData.f0807);
	 $("#f0808").val(rowData.f0808);
	 $("#f0809").val(rowData.f0809);
	 $("#f0810").val(rowData.f0810);
	 $("#f0811").val(rowData.f0811);
	 $("#f0812").val(rowData.f0812);
	 $("#f0819").val(rowData.f0819);
	 $("#f0820").val(rowData.f0820);
		
 
	 f0801=rowData.f0801;
	 
 
	 
	 if(rowData.f0813!="")
	 $("#upPicOne").attr('src',rowData.f0813);
	 if(rowData.f0814!="")
	 $("#upPicTwo").attr('src',rowData.f0814);
	 if(rowData.f0815!="")
	 $("#upPicThree").attr('src',rowData.f0815);
		
	 
	 f0809=$("#f0809").val();
	 if(f0809=="不限时间"){
			$("#f0810").val("时间为不限时间");
			$("#f0812").val("时间为不限时间");
			$("#f0810").attr("disabled",true);
			$("#f0812").attr("disabled",true);
	 }
	
	
	$("#companyName").text(companyName);

	$("#submitEditHideDanger").click(function() {

		if (checkValue()) {
			
			if(count==0){
				
				$.ajax({
					type:"post",
					url:"/WH/job/updatePurkingPeril",
					data:{
						f0101:f0101,
						f0801:f0801,
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
					

						
						console.log(todayHideDanger);
						if(todayHideDanger==-1){
							localStorage.setItem("companyID",JSON.stringify(f0101));
						   	
							showPage("/WH/jsp/companyHideDanger.jsp");
							
							//window.location.href="/WH/jsp/companyHideDanger.jsp";				
						}else{
							
							//window.location.href="/WH/jsp/companyTodayHideDanger.jsp";	
		
							showPage("/WH/jsp/companyTodayHideDanger.jsp");
						
						}
				 				
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
		 
		 alert("修改失败,限制天数必须为整数");
		 return false;
	 }
	 	 
	 
	 var clt = f0811;
	 var clt1=[];
	 for(var i=0;i<clt.length;i++)
	{
		 if(clt[i]=='/')
			 clt1+='-';
		 else
			 clt1+=clt[i];
	} 
	 f0811=clt1;

	 
	 var str = f0812;
	 var str1=[];
	 for(var i=0;i<str.length;i++)
	{
		 if(str[i]=='/')
			 str1+='-';
		 else
			 str1+=str[i];
	} 
	 f0812=str1;

	 
	 
	if (f0803==""||f0804==""||f0805==""||f0806==""||f0808==""||
	f0809==""||f0810==""||f0811==""||f0812==""||f0819=="") {
		alert("添加失败,请您完善信息");
		return false;
	}  else {
		return true;
	}

}
// 判断时间格式
function checkTimeFormat() {

	var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
	if (!reg.test(f0811) || !reg.test(f0812)) {
		return false;
	} else {
		return true;
	}
}

// 判断时间大小
function RQcheck(RQ) {
	var date = RQ;
	var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (result == null)
		return false;
	var d = new Date(result[1], result[3] - 1, result[4]);
	return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d
			.getDate() == result[4]);
}
function checkTimeCorrect() {
	var ret = true;

	if (!RQcheck(f0811) || !RQcheck(f0812)) {
		return false;
	} else {
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
	url : '/WH/job/updatePurkingPeril', // 服务器端的上传页面地址
	flash_swf_url : 'js/Moxie.swf', // swf文件，当需要使用swf方式进行上传时需要配置该参数
	silverlight_xap_url : 'js/Moxie.xap', // silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
	multipart : true,
	filters : {
		mime_types : [ // 只允许上传图片和zip文件
		{
			title : "img",
			extensions : "jpg,gif,png,jpeg"
		} ],
		max_file_size : '9640kb', // 最大只能上传400kb的文件
		prevent_duplicates : true
	// 不允许选取重复文件
	}
});

// 在实例对象上调用init()方法进行初始化
uploader.init();
//上传完成后执行的方法
uploader.bind('FileUploaded',function(uploader, file, res){
	alert("修改成功");
	if(todayHideDanger==-1){
		localStorage.setItem("companyID",JSON.stringify(f0101));
	   	window.location.href="/WH/jsp/companyHideDanger.jsp";				
	}else{
		window.location.href="/WH/jsp/companyTodayHideDanger.jsp";	
	}
	
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




function addHideDanger(){
	
	var obj = {
			f0101:f0101,
			f0801:f0801,
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
	
	
		console.log(obj);
	
		//设置某个特定的配置参数
		uploader.setOption('multipart_params', obj);
		//开始上传
		uploader.start();

}




 
