var ctx = $('base').attr('href');
$(function(){

	//左边分组树
	$('#group_table').treegrid({  
		url: 			ctx +'/group/list.json', 
	    height: 		'auto', 
	    fit: 			true, 
	    fitColumns: 	true,
	    nowrap: 		false, 
	    striped: 		true, 
	    border: 		false, 
	    collapsible:	false, 
	    idField:		'groupId', 
	    treeField:		'groupName',
	    onContextMenu: function(e, row) {
						e.preventDefault();
						$(this).treegrid('select', row.groupId);
						$('#group_menu').menu('show', {
							left : e.pageX,
							top : e.pageY
						});
		}
	}); 
	
	//右边用户列表
	$('#userGroup_table').datagrid({  
		url: 			ctx +'/user/list.json', 
		idField:		'userId', 
	    height: 		'auto', 
	    fitColumns: 	true,
	    fit: 			true,//自动大小 
	    nowrap: 		false, 
	    striped: 		true, 
	    border: 		false, 
	    collapsible:	false,
	    processing: 	true,
        pageLength: 	15,
	    singleSelect:	true,//是否单选 
	    pagination:		true,//分页控件 
	    rownumbers:		true
	}); 
	
	//设置分页控件 
	var p = $('#userGroup_table').datagrid('getPager'); 
	$(p).pagination({ 
		pageSize: 10,
	    pageList: [10,15,30,50,100], 
	    beforePageText: '第',
	    afterPageText: '页    共 {pages} 页', 
	    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	});  
	
});

$(function(){//清除模态窗体的数据,每次打重新加载
	$("#editGroup").on("hidden", function() {
	    $(this).removeData("modal");
	});
});

/**
 * 组事件操作
 */
var GroupHandler = {
	
	/**
	 * 打开编辑页面
	 * @param flag 1标识添加;2标识修改
	 * @param groupId  模块ID
	 */
	beforeEditGroup: function (flag){//加载编辑页面
		var rowsChecked = $('#group_table').treegrid('getSelected');//选中的行
		var groupId = rowsChecked.groupId;
		var pname = rowsChecked.groupName;
		var pid = rowsChecked.groupId;
		var url =  ctx + "/group/beforeEditGroup.html?_time=" + new Date().getTime();
		 
		if(flag == 2){//修改
			var tree = $('#group_table').treegrid('getParent',groupId);
			pname = tree.groupName;
			pid = tree.groupId;
			url +="&groupId=" + groupId;
			url +="&pid=" + pid;
		}else{
			url +="&pid=" + groupId;
		}
		 
		url +="&flag=" + flag;
    	url +="&pname=" + encodeURI(encodeURI(pname));

    	  $("#editGroup").modal({
    	    	 backdrop: false,
    	    	 remote: url
    	    }); 
//    	showModal("editGroup", url); //弹出窗体
    },
	
	editGroup: function(){//提交编辑用户
		if(!this.checkGroup("groupName")){
			return false;
		}
		 
		var url =  ctx + "/group/editGroup.json";
        $.ajax({
        	url: 	 	url,
        	type: 		"POST",
        	dataType: 	"json",
        	data:  		$("#groupForm").serialize(),
        	success: 	function(data){
	        		if(data && data.status != 0){ 
	           			$("#cancleEditGroup").click(); //隐藏提示窗体
	           			$('#group_table').treegrid('reload');//刷新
	        		}
        	}
        });
		
    },
    
    hideTip: function(obj){//隐藏验证提示
    	var id = $(obj).attr("id");
    	$("#" + id + "Tip").removeClass("in");
    },
    
    deleteGroup: function(){//确认删除
    	var rowsChecked = $('#group_table').treegrid('getSelected');//选中的行
    	var groupId = null;
    	var parentId = null;
    	 
    	if(rowsChecked){
    		groupId = rowsChecked.groupId;
    		//当前选中的节点存在子节点则说明本身是父节点，则删除其下的所有子节点与自身
    		var childs = $('#group_table').treegrid('getChildren',groupId);
    		parentId = (childs.length > 0 ? groupId : null);
    	}
    	
    	var url =  ctx + "/group/deleteGroup.json";
    	$.post(url,{
    		groupId: 			groupId, 
    		parentId: 			parentId,
    		_time: 				new Date().getTime()
    	},function(data){
    		if(data && data.status != 0){
    			$("#cancleDelGroup").click(); //隐藏提示窗体
    			tipMsg("beforeDeleteGroup","删除成功");
    			
    			if (rowsChecked){//无刷新删除节点
    	    		$('#group_table').treegrid('remove', groupId);
    	    	}
    		}else{
    			alert(data.msg);
    		}
    	});
    },
    
    beforeDeleteGroup: function(){//删除之前提示
    	 $("#isDeleteGroupTip").modal({//确认是否删除
    		 backdrop: 'static',
    		 keyboard: false
    	 });  
    	return false;
    },
    
    checkGroup: function (id){//验证 
    	var val= $.trim($("#" + id).val());
        if(!val){
        	 $("#" + id + "Tip").addClass("in");
        	 //return false;
        }
        return true;
    },
    
    choiceGroupTree: function(){//修改时弹出树
    	$('#group_treegrid').treegrid('unselectAll');
    	var url =  ctx + "/group/choiceGroupTree.html?_time=" + new Date().getTime();
    	showModal("choiceGroupTree", url);
    },
    
    selectedTreeNode: function(){//选中树节点，回选数据到表单
        var row = $('#group_treegrid').treegrid('getSelected');//选中的行
    	$("#parentId").val(row.groupId);
    	$("#parentName").val(row.groupName);
    	
    	this.closeModalTree();
    },
    
    closeModalTree: function(){ //close窗体
    	$("#choiceGroupTree").modal('hide');
    },
    
    reloadTree: function(){
    	$('#group_treegrid').treegrid('reload');
    	$('#group_treegrid').treegrid('unselectAll');
    }
    
};