var ctx = $('base').attr('href');
$(function(){

	$('#authzRole_table').treegrid({  
		url: ctx +'/role/authzRole.json', 
	    height: '400px', 
//	    fitColumns: true,
	    nowrap: false, 
	    striped: true, 
	    border: true, 
	    collapsible:false,//是否可折叠的 
//	    fit: true,//自动大小 
	    idField:'id', 
	    treeField:'resourceName'
	}); 

});

/**
 * 格式化名称且加checkbox
 * @param val
 * @param row
 * @param index
 */
function formatResourceName(val,row,index){
	 
	var checkbox = "&nbsp;&nbsp;<input  type='checkbox' ";
	checkbox += " name='module_"+row.id+"'";
	checkbox += " id='module_"+row.id+"'";
	checkbox += " />";
	
	return val + checkbox;
};

/**
 * 格式化操作名的列
 * @param val
 * @param row
 * @param index
 * @returns
 */
function formatOperate(val,row,index){
	if(!row._parentId){
		return '';
	}
	var node = $('#authzRole_table').treegrid('getChildren',row.id);
	if(node.length > 0){
		return '';
	}
	
	var checkbox = "<input  type='checkbox' ";
	checkbox += " name='oper_"+row.id+"'";
	checkbox += " id='oper_"+row.id+"'";
	checkbox += " value='" + row.engName + "'";
	checkbox += " />";
	
	return checkbox;
};

