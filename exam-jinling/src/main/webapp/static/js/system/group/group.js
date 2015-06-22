var ctx = $('base').attr('href');
$(function(){

	$('#group_table').treegrid({  
		url: ctx +'/group/list.json', 
	    height: 'auto', 
	    fitColumns: true,
	    nowrap: false, 
	    striped: true, 
	    border: false, 
	    collapsible:false,//是否可折叠的 
	    fit: true,//自动大小 
	    idField:'groupId', 
	    treeField:'groupName',
	    onContextMenu: function(e, row) {
						e.preventDefault();
						$(this).treegrid('select', row.id);
						$('#group_menu').menu('show', {
							left : e.pageX,
							top : e.pageY
						});
		}
	}); 

});

$(function(){
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
		var groupId = rowsChecked.id;
		var pname = rowsChecked.resourceName;
		var url =  ctx + "/group/beforeEditGroup.html?_time=" + new Date().getTime();
		 
		if(flag == 2){//修改
			var tree = $('#group_table').treegrid('getParent',groupId);
			var pid = tree.id;
			pname = tree.resourceName;
			url +="&pid=" + pid;
			url +="&groupId=" + groupId;
			url +="&flag=2";
		}
		 
    	url +="&pname=" + encodeURI(encodeURI(pname));
		 
		
		//弹出窗体
        $("#editGroup").modal({
        	 backdrop: 'static',
    		 keyboard: false,
        	 remote: url
        }); 
    },
	
	editGroup: function(){//提交编辑用户
		if(!this.checkGroup("groupName")){
			return false;
		}
		 
		var url =  ctx + "/group/editGroup.json";
        $.ajax({
        	type: "POST",
        	url:  url,
        	dataType: "json",
        	data:  $("#groupForm").serialize(),
        	success: function(data){
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
    	var id = null;
    	var parentId = null;
    	 
    	if(rowsChecked){
    		id = rowsChecked.id;
    		//当前选中的节点存在子节点则说明本身是父节点，则删除其下的所有子节点与自身
    		var childs = $('#group_table').treegrid('getChildren',id);
    		parentId = (childs.length > 0 ? id : null);
    	}
    	
    	var url =  ctx + "/group/deleteGroup.json";
    	$.post(url,{
    		resourceId: 		id, 
    		parentId: 			parentId,
    		_time: 				new Date().getTime()
    	},function(data){
    		if(data && data.status != 0){
    			$("#cancleDelGroup").click(); //隐藏提示窗体
    			tipMsg("beforeDeleteGroup","删除成功");
    			
    			if (rowsChecked){//无刷新删除节点
    	    		$('#group_table').treegrid('remove', id);
    	    	}
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
    	alert($("#" + id).val());
    	var val= $.trim($("#" + id).val());
        if(!val){
        	 $("#" + id + "Tip").addClass("in");
        	 //return false;
        }
        return true;
    }
    
};