var ctx = $('base').attr('href');
$(function(){

	$('#department_table').treegrid({  
			url: ctx +'/department/list.json', 
		    height: 		'auto', 
		    fitColumns: 	true,
		    nowrap: 		false, 
		    striped: 		true, 
		    border: 		false, 
		    collapsible:	false,//是否可折叠的 
		    fit: 			true,//自动大小 
		    lines:			true,
		    idField:		'deptId', 
		    pagination:		true,//分页控件 
		    treeField:		'deptName',
		    onContextMenu: 	function(e, row) {
								e.preventDefault();
								$(this).treegrid('select', row.deptId);
								$('#department_menu').menu('show', {
									left : e.pageX,
									top : e.pageY
							});
			}
	}); 
	
	//设置分页控件 
	var p = $('#department_table').datagrid('getPager'); 
	$(p).pagination({ 
		pageSize: 10,//每页显示的记录条数，默认为15 
	    pageList: [10,15,30,50,100],//可以设置每页记录条数的列表 
	    beforePageText: '第',//页数文本框前显示的汉字 
	    afterPageText: '页    共 {pages} 页', 
	    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	});  

});

$(function(){
	$("#editDepartment").on("hidden", function() {
	    $(this).removeData("modal");
	});
});

/**
 * 角色事件操作
 */
var DepartmentHandler = {
	
	/**
	 * 打开编辑页面
	 * @param flag 1标识添加;2标识修改
	 * @param departmentId  模块ID
	 */
	beforeEditDepartment: function (flag){//加载编辑页面
		var rowsChecked = $('#department_table').treegrid('getSelected');//选中的行
		var departmentId = rowsChecked.deptId; 
		var pname = rowsChecked.deptName;
		var pid = departmentId;
		var url =  ctx + "/department/beforeEditDepartment.html?_time=" + new Date().getTime();
		 
		if(flag == 2){//修改
			var tree = $('#department_table').treegrid('getParent',departmentId);
			pname = tree.deptName;
			pid = tree.deptId;
			url +="&deptId=" + departmentId;
			url +="&pid=" + pid;
		}else{
			url +="&pid=" + departmentId;
		}
		 
		url +="&flag=" + flag;
    	url +="&pname=" + encodeURI(encodeURI(pname));
		
		//弹出窗体
        $("#editDepartment").modal({
        	 backdrop: 'static',
    		 keyboard: false,
        	 remote: url
        }); 
    },
	
	editDepartment: function(){//提交编辑用户
		if(!this.checkDepartment("departmentName")){
			return false;
		}
		 
		var url =  ctx + "/department/editDepartment.json";
        $.ajax({
        	type: "POST",
        	url:  url,
        	dataType: "json",
        	data:  $("#departmentForm").serialize(),
        	success: function(data){
        		if(data && data.status != 0){ 
           			$("#cancleEditDepartment").click(); //隐藏提示窗体
           			$('#department_table').treegrid('reload');//刷新
        		}
        	}
        });
		
    },
    
    hideTip: function(obj){//隐藏验证提示
    	var id = $(obj).attr("id");
    	$("#" + id + "Tip").removeClass("in");
    },
    
    deleteDepartment: function(){//确认删除
    	var rowsChecked = $('#department_table').treegrid('getSelected');//选中的行
    	var id = null;
    	var parentId = null;
    	 
    	if(rowsChecked){
    		id = rowsChecked.deptId;
    		//当前选中的节点存在子节点则说明本身是父节点，则删除其下的所有子节点与自身
    		var childs = $('#department_table').treegrid('getChildren',id);
    		parentId = (childs.length > 0 ? id : null);
    	}
    	
    	var url =  ctx + "/department/deleteDepartment.json";
    	$.post(url,{
    		deptId: 			id, 
    		parentId: 			parentId,
    		_time: 				new Date().getTime()
    	},function(data){
    		if(data && data.status != 0){
    			$("#cancleDelDepartment").click(); //隐藏提示窗体
    			tipMsg("beforeDeleteDepartment","删除成功");
    			
    			if (rowsChecked){//无刷新删除节点
    	    		$('#department_table').treegrid('remove', id);
    	    	}
    		}
    	});
    },
    
    beforeDeleteDepartment: function(){//删除之前提示
    	 $("#isDeleteDepartmentTip").modal({//确认是否删除
    		 backdrop: 'static',
    		 keyboard: false
    	 });  
    	return false;
    },
    
    checkDepartment: function (id){//验证
    	var val= $.trim($("#" + id).val());
        if(!val){
        	 $("#" + id + "Tip").addClass("in");
        	 //return false;
        }
        return true;
    }
    
};