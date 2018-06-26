$(document).ready(function(){ 
	var userName = JSON.parse(localStorage.getItem("userName"));  
	$("#userName").html(userName);
});