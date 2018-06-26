$.ajax({
	url:"http://localhost:8080/WH/user/tree",
	async:true,
	success:function(result){
	//console.log(result.tree);
	/*			var j = JSON.parse(result);*/
		$(".myTree").append(result.tree);
	}
});