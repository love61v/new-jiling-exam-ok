/**
 * * 对Date的扩展，将 Date 转化为指定格式的String *
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符 * 年(y)可以用 1-4
 * 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) * eg: * (new Date()).format("yyyy-MM-dd
 * hh:mm:ss.S")==> 2006-07-02 08:09:04.423 (new Date()).format("yyyy-MM-dd E
 * HH:mm:ss") ==> 2009-03-10 二 20:09:04 (new Date()).format("yyyy-MM-dd EE
 * hh:mm:ss") ==> 2009-03-10 周二 08:09:04 (new Date()).format("yyyy-MM-dd EEE
 * hh:mm:ss") ==> 2009-03-10 星期二 08:09:04 (new Date()).format("yyyy-M-d
 * h:m:s.S") ==> 2006-7-2 8:9:4.18
 * 
 */
Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
};
/**
 * 日期加减天数
 * 
 * @param d
 *            天数(负数则减) eg: new Date().addDays(2).format('yyyy-MM-dd');
 */
Date.prototype.addDays = function(d) {
	this.setDate(this.getDate() + d);
	return this;
};

/**
 * 日期加周数
 * 
 * @param w
 *            周数(负数则减)
 */
Date.prototype.addWeeks = function(w) {
	;
	this.addDays(w * 7);
	return this;
};

/**
 * 日期加月数
 * 
 * @param m
 *            月数(负数则减)
 */
Date.prototype.addMonths = function(m) {
	var d = this.getDate();
	this.setMonth(this.getMonth() + m);
	if (this.getDate() < d)
		this.setDate(0);

	return this;
};

/**
 * 日期加年数
 * 
 * @param y
 *            月数(负数则减)
 */
Date.prototype.addYears = function(y) {
	var m = this.getMonth();
	this.setFullYear(this.getFullYear() + y);
	if (m < this.getMonth()) {
		this.setDate(0);
	}
	return this;
};

/**
 * 替换所有
 * s1: 要替换的key
 * s2: 替换成的值
 */
String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
};

/**
 * 数组转换为json
 * @param arr
 * @returns 
 */
function ArrToJson(arr) {
    var length = arr.length;
    var json = {};
    for(var i=0;i < length;i++) {
         json.push(arr[i].toString());
    }
    return json;
}

/**
 * 将表单名与值序列化json对象
 * 
 * @param $
 */
(function($) {
	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();

		$(array).each(
				function() {
					if (serializeObj[this.name]) {
						if ($.isArray(serializeObj[this.name])) {
							serializeObj[this.name].push(this.value);
						} else {
							serializeObj[this.name] = [
									serializeObj[this.name], this.value ];
						}
					} else {
						serializeObj[this.name] = this.value;
					}
				});
		return serializeObj;
	};
})(jQuery);

/**
 * datagrid分页格式化
 * id: datagrid表格的id
 */
function pageFmt(id){
	//设置分页控件 
	var p = $('#' + id).datagrid('getPager'); 
	$(p).pagination({ 
		pageSize: 10,//每页显示的记录条数，默认为15 
	    pageList: [10,15,30,50,100],//可以设置每页记录条数的列表 
	    beforePageText: '第',//页数文本框前显示的汉字 
	    afterPageText: '页    共 {pages} 页', 
	    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	});  
}

/**
 * 气泡提示
 * @param id  容器ID
 * @param content 内容
 * @param position 默认bottom位置: top,left,right,bottom
 */
function tipMsg(id, content, position) {
	id = "#" + id;
	position = position || "bottom";
	$(id).popover({
		title : '提示',
		placement : position,
		html : true,
		content : '<font size="+1" style="color:red;">' + content + '</font>'
	}).on('shown.bs.popover', function() {
		setTimeout(function() {
			$(id).popover('destroy'); // 隐藏提示框
		}, 3000);
	});

	$(id).popover('show'); // 显示提示框
};

/**
 * 弹出modal模态窗体
 * @param url
 */
function showModal(id,url){
    $("#" + id).modal({
    	 backdrop: 'static',
		 keyboard: false,
    	 remote: url
    }); 
}

/**
 * 格式化性别
 * @param val
 * @param row
 * @param index
 * @returns
 */
function formatStatus(val, row, index) {
	return val == 1 ? '应用' : '禁用';
};

/**
 * 格式化日期时间
 * @param val 当前行的值
 * @param row  行,可获取所有单元格数据
 * @param index 下标
 * @returns
 */
function formatTime(val, row, index) {
	var time = val || '';
	if (time) {
		return new Date(val).format('yyyy-MM-dd HH:mm:ss');
	}
};

/**
 * 格式化性别
 * @param val
 * @param row
 * @param index
 * @returns
 */
function formatSex(val,row,index){
	return val == 1 ? '男' : '女';
};

function formatDept(val,row,index){//格式化部门
	val = val || "";
	return val.deptName;
};

/**
 * treegrid收缩事件
 * 
 * @param treegrid
 *            树grid的id
 * @param idField
 *            行数据的idField
 */
function collapse(treegrid, idField) {debugger;
	var node = $('#' + treegrid).treegrid('getSelected');
	if (node) {
		$('#' + treegrid).treegrid('collapse', node[idField]);
	}
};

/**
 * treegrid展开事件
 * @param treegrid  树grid的id
 * @param idField   列id的名字
 */
function expand(treegrid, idField) {
	var node = $('#' + treegrid).treegrid('getSelected');
	if (node) {
		$('#' + treegrid).treegrid('expand', node[idField]);
	}
};

/**
 * 隐藏验证提示
 * @param obj 
 */
function doHideTip(obj){
	var id = $(obj).attr("id");
	$("#" + id + "Tip").hide();
};

