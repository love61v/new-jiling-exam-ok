var ctx = $('base').attr('href');
$(function(){

	$('#module_table').treegrid({  
			url: ctx +'/module/list.json', 
		    height: 'auto', 
		    fitColumns: true,
		    nowrap: false, 
		    striped: false, 
		    border: false, 
		    collapsible: false, 
		    singleSelect: true, 
		    fit: true,  
		    idField: 'resourceId', 
		    treeField:	'resourceName',
		    onContextMenu: onModuleMenuFun //右键菜单
	}); 
	
});

function onModuleMenuFun(e, row) {//右键菜单
	e.preventDefault();
	$(this).treegrid('select', row.resourceId);
	$('#module_menu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
	 
	var node = $('#module_table').treegrid('getParent', row.resourceId);
	var isHide = function(method){//组的根节点只有添加操作
		for(var i=0,len = $(".menuModulehide").size();i < len;i++){
			var item = $('#module_menu').menu('getItem',$(".menuModulehide")[i]);
			$('#module_menu').menu(method, item.target);
		}
	};
	if(!node){
		isHide('disableItem');
	}else{
		isHide('enableItem');
	}
};


$(function(){
	$("#editModule").on("hidden", function() {
	    $(this).removeData("modal");
	});
});

/**
 * 角色事件操作
 */
var ModuleHandler = {
	
	/**
	 * 打开编辑页面
	 * @param flag 1标识添加;2标识修改
	 * @param moduleId  模块ID
	 */
	beforeEditModule: function (flag){//加载编辑页面
	 
		var rowsChecked = $('#module_table').treegrid('getSelected');//选中的行
		var moduleId = rowsChecked.resourceId;
		var pname = rowsChecked.resourceName;
		var pid = rowsChecked.moduleId;
		var url =  ctx + "/module/beforeEditModule.html?_time=" + new Date().getTime();
		 
		if(flag == 2){//修改
			var tree = $('#module_table').treegrid('getParent',moduleId);
			pname = tree.resourceName;
			pid = tree.resourceId;
			url +="&moduleId=" + moduleId;
			url +="&pid=" + pid;
		}else{
			url +="&pid=" + moduleId;
		}
		 
		url +="&flag=" + flag;
    	url +="&pname=" + encodeURI(encodeURI(pname));
    	
    	showModal("editModule", url); //弹出窗体
     },
	
	editModule: function(){//提交编辑用户
		if(!this.checkModule("resourceName")){
			return false;
		}
		 
		var url =  ctx + "/module/editModule.json";
        $.ajax({
        	type: "POST",
        	url:  url,
        	dataType: "json",
        	data:  $("#moduleForm").serialize(),
        	success: function(data){
        		if(data && data.status != 0){ 
           			$("#cancleEditModule").click(); //隐藏提示窗体
           			$('#module_table').treegrid('reload');//刷新
        		}
        	}
        });
		
    },
    
    hideTip: function(obj){//隐藏验证提示
    	var id = $(obj).attr("id");
    	$("#" + id + "Tip").removeClass("in");
    },
    
    deleteModule: function(){//确认删除
    	var rowsChecked = $('#module_table').treegrid('getSelected');//选中的行
    	var id = null;
    	var parentId = null;
    	 
    	if(rowsChecked){
    		id = rowsChecked.resourceId;
    		//当前选中的节点存在子节点则说明本身是父节点，则删除其下的所有子节点与自身
    		var childs = $('#module_table').treegrid('getChildren',id);
    		parentId = (childs.length > 0 ? id : null);
    	}
    	
    	var url =  ctx + "/module/deleteModule.json";
    	$.post(url,{
    		resourceId: 		id, 
    		parentId: 			parentId,
    		_time: 				new Date().getTime()
    	},function(data){
    		if(data && data.status != 0){
    			$("#cancleDelModule").click(); //隐藏提示窗体
    			tipMsg("beforeDeleteModule","删除成功");
    			
    			if (rowsChecked){//无刷新删除节点
    	    		$('#module_table').treegrid('remove', id);
    	    	}
    		}
    	});
    },
    
    beforeDeleteModule: function(){//删除之前提示
    	 $("#isDeleteModuleTip").modal({//确认是否删除
    		 backdrop: 'static',
    		 keyboard: false
    	 });  
    	return false;
    },
    
    checkModule: function (id){//验证
    	var val= $.trim($("#" + id,".modal-body").val());
        if(!val){
        	 $("#" + id + "Tip").show();
        	 return false;
        }
        return true;
    },
    
    choiceModuleTree: function(){//修改时弹出树
    	$('#module_treegrid').treegrid('unselectAll');
    	var url =  ctx + "/module/choiceModuleTree.html?_time=" + new Date().getTime();
    	showModal("choiceModuleTree", url);
    },
    
    selectedTreeNode: function(){//选中树节点，回选数据到表单
        var row = $('#module_treegrid').treegrid('getSelected');//选中的行
    	$("#module_parentId").val(row.resourceId);
    	$("#module_parentName").val(row.resourceName);
    	
    	this.closeModalTree();
    },
    
    closeModalTree: function(){ //close窗体
    	$("#choiceModuleTree").modal('hide');
    },
    
    reloadTree: function(){
    	$('#module_treegrid').treegrid('reload');
    	$('#module_treegrid').treegrid('unselectAll');
    }
    
};