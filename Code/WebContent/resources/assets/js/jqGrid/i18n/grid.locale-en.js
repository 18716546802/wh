;(function($){
/**
 * jqGrid English Translation
 * Tony Tomov tony@trirand.com
 * http://trirand.com/blog/ 
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
**/
$.jgrid = $.jgrid || {};
$.extend($.jgrid,{
	defaults : {
		recordtext: " ",
		emptyrecords: "没有记录",
		loadtext: "加载重...",
		pgtext : "第 {0} 页 "
	},
	search : {
		caption: "查找...",
		Find: "搜索",
		Reset: "重置",
		odata: [{ oper:'eq', text:'等于'},
			/*{ oper:'ne', text:'不等于'},
		{ oper:'lt', text:'小于'},
		{ oper:'le', text:'小于或等于'},
		{ oper:'gt', text:'大于'},
		{ oper:'ge', text:'大于或等于'},
		{ oper:'bw', text:'开始于'},
		{ oper:'bn', text:' 不开始于'},
		{ oper:'in', text:'是在'},
		{ oper:'ni', text:'不在'},
		{ oper:'ew', text:'结束于'},
		{ oper:'en', text:'不结束于'},*/
		{ oper:'cn', text:'包含'}],
		/*{ oper:'nc', text:'不包含'}],*/
		groupOps: [	{ op: "AND", text: "全部记录" }/*,	{ op: "OR",  text: "任意记录" }	*/]
	},
	edit : {
		addCaption: "增加",
		editCaption: "修改",
		bSubmit: "提交",
		bCancel: "退出",
		bClose: "关闭",
		saveData: "数据发生了改变! 要保存改变后的数据吗？",
		bYes : "保存",
		bNo : "退出",
		bExit : "退出",
		msg: {
			required:"字段不能为空！",
			number:"请输入有效数字",
			minValue:"值必须大于或等于",
			maxValue:"值必须小于或等于",
			email: "电子邮件无效",
			integer: "请输入有效的值",
			date: "请输入有效日期值",
			url: "不是一个有效的URL。前缀需要 ('http://' or 'https://')",
			nodefined : " 没有定义!",
			novalue : " 没有返回值！",
			customarray : "自定义函数应该返回数组!",
			customfcheck : "Custom function should be present in case of custom checking!"
			
		}
	},
	view : {
		caption: "显示记录",
		bClose: "关闭"
	},
	del : {
		caption: "删除",
		msg: "是否要删除选中的记录?",
		bSubmit: "删除",
		bCancel: "退出"
	},
	nav : {
		edittext: "",
		edittitle: "编辑",
		addtext:"",
		addtitle: "添加",
		deltext: "",
		deltitle: "删除",
		searchtext: "",
		searchtitle: "查询",
		refreshtext: "",
		refreshtitle: "刷新",
		alertcap: "警告",
		alerttext: "请选择一行记录",
		viewtext: "",
		viewtitle: "查看选中的行"
	},
	col : {
		caption: "选中列",
		bSubmit: "确定",
		bCancel: "退出"
	},
	errors : {
		errcap : "错误",
		nourl : "没有url设置",
		norecords: "没有记录过程",
		model : "Length of colNames <> colModel!"
	},
	formatter : {
		integer : {thousandsSeparator: ",", defaultValue: '0'},
		number : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0.00'},
		currency : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
		date : {
			dayNames:   [
				"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat",
				"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
			],
			monthNames: [
				"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
				"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
			],
			AmPm : ["am","pm","AM","PM"],
			S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th';},
			srcformat: 'Y-m-d',
			newformat: 'n/j/Y',
			parseRe : /[Tt\\\/:_;.,\t\s-]/,
			masks : {
				// see http://php.net/manual/en/function.date.php for PHP format used in jqGrid
				// and see http://docs.jquery.com/UI/Datepicker/formatDate
				// and https://github.com/jquery/globalize#dates for alternative formats used frequently
				// one can find on https://github.com/jquery/globalize/tree/master/lib/cultures many
				// information about date, time, numbers and currency formats used in different countries
				// one should just convert the information in PHP format
				ISO8601Long:"Y-m-d H:i:s",
				ISO8601Short:"Y-m-d",
				// short date:
				//    n - Numeric representation of a month, without leading zeros
				//    j - Day of the month without leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				// example: 3/1/2012 which means 1 March 2012
				ShortDate: "n/j/Y", // in jQuery UI Datepicker: "M/d/yyyy"
				// long date:
				//    l - A full textual representation of the day of the week
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				LongDate: "l, F d, Y", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy"
				// long date with long time:
				//    l - A full textual representation of the day of the week
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    s - Seconds, with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				FullDateTime: "l, F d, Y g:i:s A", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy h:mm:ss tt"
				// month day:
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				MonthDay: "F d", // in jQuery UI Datepicker: "MMMM dd"
				// short time (without seconds)
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				ShortTime: "g:i A", // in jQuery UI Datepicker: "h:mm tt"
				// long time (with seconds)
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    s - Seconds, with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				LongTime: "g:i:s A", // in jQuery UI Datepicker: "h:mm:ss tt"
				SortableDateTime: "Y-m-d\\TH:i:s",
				UniversalSortableDateTime: "Y-m-d H:i:sO",
				// month with year
				//    Y - A full numeric representation of a year, 4 digits
				//    F - A full textual representation of a month
				YearMonth: "F, Y" // in jQuery UI Datepicker: "MMMM, yyyy"
			},
			reformatAfterEdit : false
		},
		baseLinkUrl: '',
		showAction: '',
		target: '',
		checkbox : {disabled:true},
		idName : 'id'
	}
});
})(jQuery);