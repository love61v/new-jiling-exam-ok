var ctx = $('base').attr('href');
$(function(){
	
	clearModal("editMultiChoice");
	
	//左边分组树
	$('#multichoice_examtype_table').treegrid({  
		url: 			ctx +'/examtype/list.json', 
	    height: 		'auto', 
	    fit: 			true, 
	    fitColumns: 	true,
	    nowrap: 		false, 
	    striped: 		false, 
	    border: 		false, 
	    collapsible:	false, 
	    idField:		'id', 
	    treeField:		'typeName',
	    onClickRow:      function(row){//单击树的行，加载右边表格加载数据
	    	var typeId = row.id;
	    	var pnode = $('#multichoice_examtype_table').treegrid('getChildren',typeId);
	    	if(pnode.length > 0){//父节点禁用修改，绑定用户、角色
	    		 $('#multichoice_examtype_table').treegrid('unselect',typeId);
	    		return false;
	    	}
	    	MultiChoiceHandler.reloadMultiChoice(typeId); //加载右边选中的tab下的表格数据
	    },
	    onContextMenu:  onContextMenuFun
	}); 
	
	//右边用户列表
	$('#multichoice_table').datagrid({  
		url: 			ctx +'multichoice/list.json', 
		idField:		'id', 
	    height: 		'auto', 
	    fitColumns: 	true,
	    fit: 			true,//自动大小 
	    nowrap: 		false, 
	    striped: 		true, 
	    border: 		false, 
	    collapsible:	false,
	    processing: 	true,
	    singleSelect:	false,//是否单选 
	    pagination:true,//分页控件
	    rownumbers:		true,
	    frozenColumns:[[ 
	        	        {field:'ck',checkbox:true} 
	        	    ]]
	}); 
	
	$("#multichoice_tab").tabs({//右边tab页
		fit:true,
		border:false,
		pill:false,
		onSelect: function(title,index){
			var rowsChecked = $('#multichoice_examtype_table').treegrid('getSelected');//选中的行
			if(rowsChecked){
				MultiChoiceHandler.reloadMultiChoice(rowsChecked.id);
			}
		}
	});
	
});
 

function groutRoleFmt(val,row,index){
	return "<a href='javascript:void(0);' onclick=MultiChoiceHandler.removeGroupRole('"+row.roleId+"');>删除</a>";
}

function onContextMenuFun(e, row) {//右键菜单
		e.preventDefault();
		var groupId = row.groupId;
		$(this).treegrid('select', groupId);
		$('#group_menu').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
		 
		var node = $('#multichoice_examtype_table').treegrid('getParent', row.groupId);
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
		var node = $('#multichoice_examtype_table').treegrid('getParent',groupId);
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
    	
    	var pnode = $('#multichoice_examtype_table').treegrid('getChildren',groupId);
    	if(pnode.length > 0){//父节点禁用修改，绑定用户、角色
    		isHide('disableItem','.pGroupHide');
    	}else{
    		isHide('enableItem','.pGroupHide');
    	}
    	
};

$(function(){//清除模态窗体的数据,每次打重新加载
	$("#editMultiChoice").on("hidden", function() {
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
var MultiChoiceHandler = {
		
	reloadMultiChoice: function(typeId){//组用户列表reload
		$("#examType_id").val(typeId);
		//加载加载当前组下用户
		var params = $("#multichoice_form").serializeJson();
		$("#multichoice_table").datagrid("load",params);
	},
	
	search: function (){//查询用户
		 var params = $("#multichoice_form").serializeJson();
		 params._time = new Date().getTime();
		 
		 $('#multichoice_table').datagrid('load',params); 
	},
	
	exportExcel: function(){//导出excel模板
		window.location.href = ctx + "/template/multichoice.xls";
	},
	
	importExcel: function(){//导入excel
		var typeId=$.trim($("#examType_id").val());
		if(typeId == ''|| typeId == undefined){
			tipMsg("importExcel_multichoice","请在左侧树结构中选择分类!");
			return false;
		}else{
				$("#importExcelModal_multichoice").modal({//弹出窗体
			       	 remote: ctx + 'multichoice/breforeImportExcel.html'
			       }); 
		}
       
	},
	
	uploadExcel: function(){//上传
		//debugger;
		var typeId=$.trim($("#examType_id").val());
		var file=$.trim($("#multichoice_file").val());
		//alert(file);
		if(file == ''|| file == undefined){
			$("#multichoice_fileTip").text("请选择上传的Excel文件！").show();
		}else{
			var url = ctx +  "multichoice/importExcel.json?typeId="+typeId;
			$("#multichoiceForm").attr("action",url).submit();
		}
		
	},
	 
	beforeEditMultiChoice: function (flag){//加载编辑页面
   	var url =  ctx + "/multichoice/beforeEditMultiChoice.html";
		if(flag == 2){//修改
			var rowsChecked = $('#multichoice_table').datagrid('getChecked');
			var len = rowsChecked.length;
			debugger;
			if(len > 1 || len==0){
				tipMsg("update_multichoice","请选择单行记录编辑");
				return false;
			}
		    if(null != rowsChecked && len == 1){
		    	url +="?id=" + rowsChecked[0].id;
		    	url +="&flag=2";
		    }else{
		    	return false;
		    }
		}else{
			var typeId=$.trim($("#examType_id").val());
			if(typeId==''){
				tipMsg("save_multichoice","请在左侧树结构中选择分类");
				return false;
			}
			url +="?typeId=" + typeId;
		}
		
		showModal("editMultiChoice",url);//弹出窗体
   },
	
	editMultiChoice: function(){//提交编辑填空题
		if(!this.check("multichoice_question")){
			return false;
		}
		if(!this.checkoptions("multichoice_options")){
			return false;
		}
		var me = this;
		var url =  ctx + "/multichoice/editMultiChoice.json";
       $.ajax({
       	type: "POST",
       	url:  url,
       	dataType: "json",
       	data:  $("#multichoiceForm").serialize(),
       	success: function(data){
       		if(data && data.status != 0){ 
          			
          			me.search();  //表格重新加载
          			$('#multichoice_table').datagrid('clearSelections');//清除缓存之前选中的行
          			
          			var content = (data.flag ? "修改成功" : "添加成功");
          			var id = (data.flag ? "update_multichoice": "save_multichoice");
          			tipMsg(id,content);
          			
          			closeModal('editMultiChoice');//关闭modal窗口
       		}
       	}
       });
		
   },
   
   hideTip: function(obj){//隐藏验证提示
   	var id = $(obj).attr("id");
   	$("#" + id + "Tip").removeClass("in");
   },
   
   isDeleteTip: function(){//是否删除提示框
   	 $("#isDeleteMultiChoiceTip").modal({
   		 backdrop: 'static',
   		 keyboard: false
   	 }); 
   },
   
   deleteMultiChoice: function(){//确认删除
   	var me = this;
   	var ids = "";
   	var rowsChecked = $('#multichoice_table').datagrid('getChecked');
   	for(var i=0,len = rowsChecked.length; i < len; i++ ){
   		ids += (i > 0 ? "," : "");
   		ids += rowsChecked[i].id;
   	}
   	
   	var url =  ctx + "/multichoice/deleteMultiChoice.json";
   	$.post(url,{
   		ids: ids,
   		_time: new Date().getTime()
   	},function(data){
   		if(data && data.status != 0){
   			$("#cancleMultiChoiceDel").click(); //隐藏提示窗体
   			me.search();  //表格重新加载
   			$('#multichoice_table').datagrid('clearSelections');//清除缓存之前选中的行
   			
   			tipMsg("beforeDelete_multichoice","删除成功");
   		}
   	});
   },
   
   beforeDeleteMultiChoice: function(){//删除之前提示
	   debugger;
	    var me = this;
   	var rowsChecked = $('#multichoice_table').datagrid('getChecked');
	    if(null != rowsChecked && rowsChecked.length > 0){
	    	me.isDeleteTip(); //确认是否删除
	    	return false;
	    }else{
	    	tipMsg("beforeDelete_multichoice","请选择要删除的试题！");
	    }
   },
   
   cancleEdit: function(){//删除之前提示
	   $("#cancleMultiChoiceEdit").click(); //隐藏提示窗体
   },
   closeImportExcel: function(){//删除之前提示importExcelModal_multichoice
	   this.search();  //表格重新加载
	   closeModal('importExcelModal_multichoice');//关闭modal窗口
   },
   
   check: function(id){
	   var content = $.trim($("#" + id).val());
	   content = content.replaceAll("（","(").replaceAll("）",")");
	   var tipDiv = "#" + id + "Tip";
	    if(!content){
	    	 $(tipDiv).text("请输入题目").show();
	    	 return false;
	    }
	    
	    var boor = (content.indexOf("(") > 0 && content.indexOf(")") > 0);
	    if(!boor){
	    	$(tipDiv).text("您输入的题目没有括号，不符合填空题规则，请重新输入！").show();
			return false;
		}
	   
       return true;
   },
   
   checkoptions: function(id){
	   var content = $.trim($("#" + id).val());
	   var tipDiv = "#" + id + "Tip";
	    if(!content){
	    	 $(tipDiv).text("请输入选择题题目！").show();
	    	 return false;
	    }
	   
       return true;
   }
   
};
