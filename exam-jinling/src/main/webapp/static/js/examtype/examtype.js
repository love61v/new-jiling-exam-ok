var ctx = $('base').attr('href');
$(function(){

	$('#examtype_table').treegrid({  
			url: ctx +'/examtype/list.json', 
		    height: 'auto', 
		    fitColumns: true,
		    nowrap: false, 
		    striped: true, 
		    border: false, 
		    collapsible:false,//是否可折叠的 
		    fit: true,//自动大小 
		    idField:'id', 
		    //pagination:true,//分页控件 
		    treeField:'typeName',
		    onContextMenu: function(e, row) {
							e.preventDefault();
							$(this).treegrid('select', row.id);
							$('#examtype_menu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
			}
	}); 

});

$(function(){
	$("#editexamtype").on("hidden", function() {
	    $(this).removeData("modal");
	});
});

/**
 * 角色事件操作
 */
var examtypeHandler = {
	
	/**
	 * 打开编辑页面
	 * @param flag 1标识添加;2标识修改
	 * @param examtypeId  模块ID
	 */
	beforeEditexamtype: function (flag){//加载编辑页面
		var rowsChecked = $('#examtype_table').treegrid('getSelected');//选中的行
		var id = rowsChecked.id;
		var pname = rowsChecked.typeName;
		var pid = rowsChecked.id;
		var url =  ctx + "/examtype/beforeEditExamType.html?_time=" + new Date().getTime();
		 
		if(flag == 2){//修改
			var tree = $('#examtype_table').treegrid('getParent',id);
			pname = tree.typeName;
			pid = tree.id;
			url +="&id=" + id;
			url +="&parentId=" + pid;
		}else{
			url +="&parentId=" + id;
		}
		 
		url +="&flag=" + flag;
    	url +="&pname=" + encodeURI(encodeURI(pname));
		//弹出窗体
        $("#editexamtype").modal({
        	 backdrop: 'static',
    		 keyboard: false,
        	 remote: url
        }); 
    },
	
	editexamtype: function(){//提交编辑用户
		if(!this.checkexamtype("typeName")){
			return false;
		}
		 
		var url =  ctx + "/examtype/editExamtype.json";
        $.ajax({
        	type: "POST",
        	url:  url,
        	dataType: "json",
        	data:  $("#examtypeForm").serialize(),
        	success: function(data){
        		if(data && data.status != 0){ 
           			$("#cancleEditexamtype").click(); //隐藏提示窗体
           			$('#examtype_table').treegrid('reload');//刷新
        		}
        	}
        });
		
    },
    
    hideTip: function(obj){//隐藏验证提示
    	var id = $(obj).attr("id");
    	$("#" + id + "Tip").removeClass("in");
    },
    
    deleteExamtype: function(){//确认删除
    	var rowsChecked = $('#examtype_table').treegrid('getSelected');//选中的行
    	var id = null;
    	var parentId = null;
    	if(rowsChecked){
    		id = rowsChecked.id;
    		//当前选中的节点存在子节点则说明本身是父节点，则删除其下的所有子节点与自身
    		var childs = $('#examtype_table').treegrid('getChildren',id);
    		parentId = (childs.length > 0 ? id : null);
    	}
    	
    	var url =  ctx + "/examtype/deleteExamtype.json";
    	$.post(url,{
    		id: 		id, 
    		parentId: 			parentId,
    		_time: 				new Date().getTime()
    	},function(data){
    		if(data && data.status != 0){
    			$("#cancleDelexamtype").click(); //隐藏提示窗体
    			tipMsg("beforeDeleteexamtype","删除成功");
    			
    			if (rowsChecked){//无刷新删除节点
    	    		$('#examtype_table').treegrid('remove', id);
    	    	}
    		}
    	});
    },
    
    beforeDeleteExamtype: function(){//删除之前提示
    	 $("#isDeleteexamtypeTip").modal({//确认是否删除
    		 backdrop: 'static',
    		 keyboard: false
    	 });  
    	return false;
    },
    
    checkexamtype: function (id){//验证
    	var val= $.trim($("#" + id).val());
        if(!val){
        	 $("#" + id + "Tip").addClass("in");
        	 //return false;
        }
        return true;
    },
    
    choiceExamTypeTree: function(){//修改时弹出树
    	$('#examtype_treegrid').treegrid('unselectAll');
    	var url =  ctx + "/examtype/choiceExamTypeTree.html?_time=" + new Date().getTime();
    	showModal("choiceExamTypeTree", url);
    },
    
    selectedTreeNode: function(){//选中树节点，回选数据到表单
        var row = $('#examtype_treegrid').treegrid('getSelected');//选中的行
    	$("#parentId").val( row.id);
    	$("#parentName").val(row.typeName);
    	
    	this.closeModalTree();
    },
    
    closeModalTree: function(){ //close窗体
    	$("#choiceExamTypeTree").modal('hide');
    },
    
    reloadTree: function(){
    	$('#examtype_treegrid').treegrid('reload');
    	$('#examtype_treegrid').treegrid('unselectAll');
    }
    
};