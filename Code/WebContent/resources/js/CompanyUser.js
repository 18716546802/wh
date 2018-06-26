var grid_data;

// 得到当前数据
function getRowData() {
	var id = $("#grid-table").jqGrid("getGridParam", "selrow"); // 获得选中行的ID
	var rowData = $("#grid-table").jqGrid("getRowData", id); // 当前行数据
	return rowData;
}

// 修改
var updateCompany = function() {

	var rowData = getRowData();
	localStorage.setItem("rowData", JSON.stringify(rowData));
	window.location.href = "/WH/jsp/editCompanyUser.jsp";
};

// 添加
var addCompany = function() {

	window.location.href = "/WH/jsp/addCompany.jsp";
};

// 删除
var deleteCompany = function() {

	if (confirm("您确定要删除嘛？")) {

		// 删除数据
		var rowData = getRowData();
		var f0201 = rowData.f0201;
		$.ajax({
			type : "get",
			url : "/WH/user/removeUser",
			data : {
				f0201 : f0201
			},
			success : function(data) {
				if (data.success) {
					window.location.href = "/WH/jsp/company.jsp";
				} else {
					alert("删除失败");
				}
			}
		});

	} else {

	}

}

$(document)
		.ready(
				function() {

					// 从后台获得数据
					$
							.ajax({
								type : "GET",
								url : "/WH/user/findEnterpriseUsers?f0205=企业",// 接口路径
								success : function(data) {
									grid_data = data.data;
									console.log(data)
									var grid_selector = "#grid-table";
									var pager_selector = "#grid-pager";
									jQuery(grid_selector)
											.jqGrid(
													{
														data : grid_data,
														datatype : "local",
														height : 250,
														colNames : [ 'ID',
																'企业名称', '登陆账号',
																'用户登陆密码',
																'用户真实姓名',
																'用户类型', '管理员',
																'联系电话1',
																'联系电话2' ],
														colModel : [
																{
																	name : 'f0201',
																	index : 'f0201',
																	width : '12%',
																	align : 'center',
																	hidden : true
																},
																{
																	name : 't01.f0102',
																	index : 't01.f0102',
																	width : '12%',
																	align : "center"
																},
																{
																	name : 'f0202',
																	index : 'f0202',
																	width : '12%',
																	align : "center"
																},
																{
																	name : 'f0203',
																	index : 'f0203',
																	width : '12%',
																	align : "center"
																},
																{
																	name : 'f0204',
																	index : 'f0204',
																	width : '12%',
																	align : "center"
																},
																{
																	name : 'f0205',
																	index : 'f0205',
																	width : '12%',
																	align : "center"
																},
																{
																	name : 'f0206',
																	index : 'f0206',
																	width : '12%',
																	align : 'center'
																},
																{
																	name : 'f0207',
																	index : 'f0207',
																	width : '12%',
																	align : "center"
																},
																{
																	name : 'f0208',
																	index : 'f0208',
																	width : '12%',
																	align : "center"
																} ],
														viewrecords : true,
														rowNum : 10,
														rowList : [ 10, 20, 30 ],
														pager : pager_selector,
														altRows : true,
														// toppager: true,
														multiselect : true, /* 定义是否可以多选 */
														// multikey: "ctrlKey",
														multiboxonly : true, /*
																				 * 只有当multiselect =
																				 * true.起作用，当multiboxonly
																				 * 为ture时只有选择checkbox才会起作用
																				 */
														loadComplete : function() {
															var table = this;
															setTimeout(
																	function() {
																		var icons = $(".ui-icon-refresh");// 隐藏刷新
																		icons
																				.hide();// 隐藏刷新
																		styleCheckbox(table);
																		updateActionIcons(table);
																		updatePagerIcons(table);
																		// enableTooltips(table);
																	}, 0);
														},

														caption : "企业管理",
														autowidth : true
													// autowidth 是否自动适应宽度

													});

									// 增删改查
									jQuery(grid_selector)
											.jqGrid(
													'navGrid',
													pager_selector,
													{ // navbar options
														// 表格左下角的各个功能按钮
														edit : true,
														editicon : 'icon-pencil blue',
														editfunc : updateCompany,
														add : true,
														addicon : 'icon-plus-sign purple',
														addfunc : addCompany,
														del : true,
														delicon : 'icon-trash red',
														delfunc : deleteCompany,
														search : true,
														searchicon : 'icon-search orange',
														refresh : false,
														refreshicon : 'icon-refresh green',
														view : false,
														viewicon : 'icon-zoom-in grey',
													},

													{
														// search form
														recreateForm : true,
														afterShowSearch : function(
																e) {
															var form = $(e[0]);
															form
																	.closest(
																			'.ui-jqdialog')
																	.find(
																			'.ui-jqdialog-title')
																	.wrap(
																			'<div class="widget-header" />')
															style_search_form(form);
														},
														afterRedraw : function() {
															style_search_filters($(this));
														},
														multipleSearch : true,
													/**
													 * multipleGroup:true,
													 * showQuery: true
													 */
													})

									function style_search_filters(form) {

										form.find('.delete-rule').val('X');
										form.find('.add-rule').addClass(
												'btn btn-xs btn-primary');
										form.find('.add-group').addClass(
												'btn btn-xs btn-success');
										form.find('.delete-group').addClass(
												'btn btn-xs btn-danger');
									}
									function style_search_form(form) {

										var dialog = form
												.closest('.ui-jqdialog');
										var buttons = dialog.find('.EditTable')
										buttons
												.find(
														'.EditButton a[id*="_reset"]')
												.addClass('btn btn-sm btn-info')
												.find('.ui-icon').attr('class',
														'icon-retweet');
										buttons
												.find(
														'.EditButton a[id*="_query"]')
												.addClass(
														'btn btn-sm btn-inverse')
												.find('.ui-icon').attr('class',
														'icon-comment-alt');
										buttons
												.find(
														'.EditButton a[id*="_search"]')
												.addClass(
														'btn btn-sm btn-purple')
												.find('.ui-icon').attr('class',
														'icon-search');

									}

									function styleCheckbox(table) {

									}

									// unlike navButtons icons, action icons in
									// rows seem to be hard-coded
									// you can change them like this in here if
									// you want
									function updateActionIcons(table) {
										/**
										 * var replacement = { 'ui-icon-pencil' :
										 * 'icon-pencil blue', 'ui-icon-trash' :
										 * 'icon-trash red', 'ui-icon-disk' :
										 * 'icon-ok green', 'ui-icon-cancel' :
										 * 'icon-remove red' };
										 * $(table).find('.ui-pg-div
										 * span.ui-icon').each(function(){ var
										 * icon = $(this); var $class =
										 * $.trim(icon.attr('class').replace('ui-icon',
										 * '')); if($class in replacement)
										 * icon.attr('class', 'ui-icon
										 * '+replacement[$class]); })
										 */
									}

									// replace icons with FontAwesome icons like
									// above
									function updatePagerIcons(table) {
										var replacement = {
											'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
											'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
											'ui-icon-seek-next' : 'icon-angle-right bigger-140',
											'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
										};
										$(
												'.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
												.each(
														function() {
															var icon = $(this);
															var $class = $
																	.trim(icon
																			.attr(
																					'class')
																			.replace(
																					'ui-icon',
																					''));

															if ($class in replacement)
																icon
																		.attr(
																				'class',
																				'ui-icon '
																						+ replacement[$class]);
														})
									}

									function enableTooltips(table) {
										$('.navtable .ui-pg-button').tooltip({
											container : 'body'
										});
										$(table).find('.ui-pg-div').tooltip({
											container : 'body'
										});
									}
								}
							});

				});