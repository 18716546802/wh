var f0101;
var f0902;
var f0903;
var f0904;
var f0905;
var f0906;
var f0907;

var uploader = new plupload.Uploader({
	    browse_button : 'subBtn', //触发文件选择对话框的按钮，为那个元素id
	    url : '/WH/lowcard/addLow', //服务器端的上传页面地址
	    flash_swf_url : 'js/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
	    silverlight_xap_url : 'js/Moxie.xap', //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
	    multipart:true,
	    chunk_size:0,
	    filters: {
	    	  mime_types : [ //只允许 
	    	    { title : "word", extensions : "doc" },
	    	    { title : "word", extensions : "docx" },
	    	    { title : "word", extensions : "pdf" }
	    	  ],
	    	  max_file_size : '2mb', //最大只能上传2mb的文件
	    	  prevent_duplicates : true //不允许选取重复文件
	    }
	});    	
	//在实例对象上调用init()方法进行初始化
	uploader.init();
	
	//上传完成后执行的方法
	uploader.bind('FileUploaded',function(uploader, file, res){
		window.location.href="/WH/jsp/lawsAndRegulations.jsp";
	});
	
	//绑定各种事件，并在事件监听函数中做你想做的事
	uploader.bind('FilesAdded',function(uploader,files){
		var fne = $("#file_name").text(files[0].name);
	/*	console.log(files[0].name);*/
		document.getElementById('file_name').value = files[0].name;
	});
	uploader.bind('UploadProgress',function(uploader,file){
	    //每个事件监听函数都会传入一些很有用的参数，
	    //我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
	});

	$("#submitFormAddLaws").bind("click",function(e){
        e.preventDefault();
        if($("input[name='file_type']:checked").val()== "企业文件"){
            if(!($(":input[name='lawsTree']").val()>=101000000 && $(":input[name='lawsTree']").val()<=101999999)){
         	   alert("请选择正确的企业");
            }else{
                var obj=
                {   
            		f0101:$(":input[name='lawsTree']").val(),
            		f0905:$("#f0905").val(),
            		f0906:localStorage.getItem("userID"),
            		f0907:$("input[name='file_type']:checked").val()
                };
                console.log(obj);
                uploader.setOption('multipart_params',obj);
                uploader.start();   
            }
        }
        else if(uploader.files.length == 0){
     	   alert("请选择文件");
        }
        else{
           var obj=
           {   
       		f0101:$(":input[name='lawsTree']").val(),
       		f0905:$("#f0905").val(),
       		f0906:localStorage.getItem("userID"),
       		f0907:$("input[name='file_type']:checked").val()
           };
           console.log(obj);
           uploader.setOption('multipart_params',obj);
           uploader.start();   
        }
})
