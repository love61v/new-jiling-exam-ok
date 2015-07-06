var ctx = $('base').attr('href');
$(function() {
	$("#main_accordion").accordion({
		width: 		'240px',
		height: 	'300px',
		multiple:	false,
		fit:		true
	});
	 
	$('#userModule_tree').treegrid({//用户模块菜单 
		url: 		ctx + '/module/showUserModules.json', 
		idField: 	'resourceId', 
		treeField:	'resourceName',
	    height: 	'auto', 
	    fitColumns: true,
	    fit: 		true, 
	    nowrap: 	false, 
	    striped: 	false, 
	    border: 	false, 
	    collapsible: true, 
	    singleSelect: true,
	    onClickRow:  function(row){
	    	var node = $('#userModule_tree').treegrid('getChildren', row.resourceId);
	    	if(node.length > 0){
	    		return false;
	    	}
	    	var module_name = row.resourceName;
	    	var exitstTab = $('#mainContent_tab').tabs('getTab', module_name);
			if (exitstTab) {// 已打开标签
				$('#mainContent_tab').tabs('select', module_name);
				return false;
			}
			$('#mainContent_tab').tabs('add', {
				title : module_name,
				href :  ctx + '/' + row.path,
				closable : true
			});
			
	    }
}); 
	
});

var MainHandler = {

	/* 弹出修改密码窗口 */
	updatePassword : function() {
		$("#passwordModal").modal({
			show : true
		});
	}

};
