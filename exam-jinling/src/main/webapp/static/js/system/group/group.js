var ctx = $('base').attr('href');
$(function(){

	//左边分组树
	$('#group_table').treegrid({  
		url: 			ctx +'/group/list.json', 
	    height: 		'auto', 
	    fit: 			true, 
	    fitColumns: 	true,
	    nowrap: 		false, 
	    striped: 		false, 
	    border: 		false, 
	    collapsible:	false, 
	    idField:		'groupId', 
	    treeField:		'groupName',
	    onClickRow:      function(row){//单击树的行，加载右边表格加载数据
	    	var groupId = row.groupId;
	    	var pnode = $('#group_table').treegrid('getChildren',groupId);
	    	if(pnode.length > 0){//父节点禁用修改，绑定用户、角色
	    		 $('#group_table').treegrid('unselect',groupId);
	    		return false;
	    	}
	    	GroupHandler.reloadUserGroup(groupId); //加载右边选中的tab下的表格数据
	    },
	    onContextMenu:  onContextMenuFun
	}); 
	
	//右边用户列表
	$('#userGroup_table').datagrid({  
		url: 			ctx +'user/findUserByGroupId.json', 
		idField:		'userId', 
	    height: 		'auto', 
	    fitColumns: 	true,
	    fit: 			true,//自动大小 
	    nowrap: 		false, 
	    striped: 		true, 
	    border: 		false, 
	    collapsible:	false,
	    processing: 	true,
	    singleSelect:	false,//是否单选 
	    rownumbers:		true,
	    frozenColumns:[[ 
	        	        {field:'ck',checkbox:true} 
	        	    ]]
	}); 
	
	$('#groupRole_table').datagrid({  
		idField:'roleId', 
		url: ctx +'group/findRoleByGroupId.json', 
	    height: 'auto', 
	    fitColumns: true,
	    fit: true,//自动大小 
	    nowrap: false, 
	    striped: true, 
	    border: false, 
	    collapsible:false,//是否可折叠的 
	    singleSelect:true,//是否单选 
	    rownumbers:true//行号 
	}); 
	
	$("#group_tab").tabs({//右边tab页
		fit:true,
		border:false,
		pill:false,
		onSelect: function(title,index){
			var rowsChecked = $('#group_table').treegrid('getSelected');//选中的行
			if(rowsChecked){
				GroupHandler.reloadUserGroup(rowsChecked.groupId);
			}
		}
	});
	
});

function groutRoleFmt(val,row,index){
	return "<a href='javascript:void(0);' onclick=GroupHandler.removeGroupRole('"+row.roleId+"');>删除</a>";
}

function onContextMenuFun(e, row) {//右键菜单
		e.preventDefault();
		var groupId = row.groupId;
		$(this).treegrid('select', groupId);
		$('#group_menu').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
		 
		var node = $('#group_table').treegrid('getParent', row.groupId);
		var isHide = function(method){//组的根节点只有添加操作
			for(var i=0,len = $(".menuGrouphide").size();i < len;i++){
    			var item = $('#group_menu').menu('getItem',$(".menuGrouphide")[i]);
    			$('#group_menu').menu(method, item.target);
    		}
		};
    	if(!node){
    		isHide('disableItem');
		}else{
			isHide('enableItem');
		}
		var node = $('#group_table').treegrid('getParent',groupId);
		var isHide = function(method,className){//组的根节点只有添加操作
			for(var i=0,len = $(className).size();i < len;i++){
    			var item = $('#group_menu').menu('getItem',$(className)[i]);
    			$('#group_menu').menu(method, item.target);
    		}
		};
    	if(!node){//根节点
    		isHide('disableItem','.menuGrouphide');
		}else{
			isHide('enableItem','.menuGrouphide');
		}
    	
    	var pnode = $('#group_table').treegrid('getChildren',groupId);
    	if(pnode.length > 0){//父节点禁用修改，绑定用户、角色
    		isHide('disableItem','.pGroupHide');
    	}else{
    		isHide('enableItem','.pGroupHide');
    	}
    	
};

$(function(){//清除模态窗体的数据,每次打重新加载
	$("#editGroup").on("hidden", function() {
	    $(this).removeData("modal");
	});
	 
	$('#bindUserModal').on('hidden.bs.modal', function () {
		$("#bindUser_table").datagrid("clearSelections");
	});
	
	$('#bindRoleModal').on('hidden.bs.modal', function () {
		$("#bindRole_table").datagrid("clearSelections");
	});
	
});

/**
 * 组事件操作
 */
var GroupHandler = {
		
	reloadUserGroup: function(groupId){//组用户列表reload
		$("#user_groupId").val(groupId);
		
		var tab = $('#group_tab').tabs('getSelected');
		var index = $('#group_tab').tabs('getTabIndex',tab);
		if(index == 0){//加载加载当前组下用户
			$("#userGroup_table").datagrid("uncheckAll");
			var params = $("#usergroup_form").serializeJson();
			$("#userGroup_table").datagrid("load",params);
		}else{//加载当前组下的角色
			var params = {groupId: groupId};
			$("#groupRole_table").datagrid("load",params);
		}
		
	},
	
	userSearch: function(){ 
		var params = $("#bindUser_form").serializeJson();
		params._time = new Date().getTime();
		 
		$('#bindUser_table').datagrid('load',params); 
	},
	
	searchUserGroup: function(){//查询组下的所有用户
		var params = $("#usergroup_form").serializeJson();
		params._time = new Date().getTime();
		 
		$('#userGroup_table').datagrid('load',params); 
	},
	
	removeGroupRole: function(roleId){//删除组的角色
		var rowsChecked = $('#group_table').treegrid('getSelected');//选中的行
		var url = ctx +"group/deleteGroupRole.json";
		
		$.post(url,{
			groupId: rowsChecked.groupId,
			roleId: roleId,
		},function(data){
			$("#groupRole_table").datagrid("load",{groupId: rowsChecked.groupId});
		});
	},
	
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
			if(groupId == 1){
				return false;
			}
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
    	
    	showModal("editGroup", url);
    },
	
    parentNodeAct: function(){//根节点无删除，绑定权限功能
    	var rowsChecked = $('#group_table').treegrid('getSelected');//选中的行
		var node = $('#group_table').treegrid('getParent', rowsChecked.groupId);
    	if(!node){
			return false;
		}
    	return true;
    },
    
    beforeUserBind: function(){//跳绑定用户页面
    	var url =  ctx + "/group/beforeUserBind.html";
    	showModal("bindUserModal", url);
    },
    
    beforeRoleBind: function(){//跳绑定角色页面
    	var url =  ctx + "/group/beforeRoleBind.html";
    	showModal("bindRoleModal", url);
    },
    
    bindUser: function(){//绑定用户
    	var rowsChecked = $("#bindUser_table").datagrid("getChecked");
    	var len = rowsChecked.length;
    	if(len > 0){
    		var groupId = $("#group_table").treegrid("getSelected").groupId;
    		var userGroupArr = [];
    		for(var i=0; i < len; i++ ){
    			var userGrop = {};
    			userGrop.groupId = groupId;
    			userGrop.userId = rowsChecked[i].userId;
    			
    			userGroupArr.push(userGrop);
        	}
    		
    		var url =  ctx + "/group/bindUser.json";
    		var me = this;
        	$.post(url,{
        		userGroups: JSON.stringify(userGroupArr), 
        		_time: new Date().getTime()
        	},function(data){
        		if(data && data.status != 0){
        			me.reloadUserGroup(groupId); //表格重新加载
        			$('#bindUser_table').datagrid('clearSelections');//清除缓存之前选中的行
        			$("#bindUserModal").modal("hide");
        		}
        	});
        	
    	} 
    },
    
    bindRole: function(){//绑定角色
    	var rowsChecked = $("#bindRole_table").datagrid("getChecked");
    	var len = rowsChecked.length;
    	if(len > 0){
    		var groupId = $("#group_table").treegrid("getSelected").groupId;
    		var roleGroupArr = [];
    		for(var i=0; i < len; i++ ){
    			var roleGrop = {};
    			roleGrop.groupId = groupId;
    			roleGrop.roleId = rowsChecked[i].roleId;
    			
    			roleGroupArr.push(roleGrop);
        	}
    		
    		var me = this;
    		var url =  ctx + "/group/bindRole.json";
        	$.post(url,{
        		roleGroups: JSON.stringify(roleGroupArr), 
        		_time: new Date().getTime()
        	},function(data){
        		if(data && data.status != 0){
        			me.reloadUserGroup(groupId);  //表格重新加载
        			$('#bindRole_table').datagrid('clearSelections');//清除缓存之前选中的行
        			$("#bindRoleModal").modal("hide");
        		}
        	});
        	
    	} 
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
    	doHideTip(obj);
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
    
    beforeRemoveFromGroup: function(){//确认是否解除用户
    	$("#removeFromGroupTip").modal({
   		 backdrop: 'static',
   		 keyboard: false
   	 });  
   	return false;
    },
    
    removeFromGroup: function(){//解除用户
    	var groupChecked = $('#group_table').treegrid('getSelected');//选中的行
    	if(!groupChecked){
    		tipMsg("removeFromGroup", "请选择组");
    		return false;
    	}
    	var userChecked = $('#userGroup_table').datagrid('getChecked');
    	var len = userChecked.length;
    	if(len == 0){
    		tipMsg("removeFromGroup", "请选用户");
    		return false;
    	}
    	
		var groupId = groupChecked.groupId;
    	var uerIds = "";
    	for(var i=0; i < len; i++ ){
    		uerIds += (i > 0 ? "," : "");
    		uerIds += userChecked[i].userId;
    	}
    	
    	var me = this;
    	var url =  ctx + "/group/removeFromGroup.json";
    	$.post(url,{
    		groupId: groupId,
    		uerIds: uerIds,
    		_time: new Date().getTime()
    	},function(data){
    		if(data && data.status != 0){
    			$("#removeFromGroupTip").modal("hide"); //隐藏提示窗体
    			me.reloadUserGroup();  //表格重新加载
    		}
    	});
    
    },
    
    checkGroup: function (id){//验证 
    	var val= $.trim($("#" + id, ".modal-body").val());
        if(!val){
        	 $("#" + id + "Tip").show();
        	 return false;
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