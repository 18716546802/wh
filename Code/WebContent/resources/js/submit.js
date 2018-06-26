$(document).ready(function(){
	$(".eid").bind('click',function(){
		console.log($(this).find("input").val());
	})
});