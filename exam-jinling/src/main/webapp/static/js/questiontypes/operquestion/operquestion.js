var ctx = $('base').attr('href');
$(function(){
	
	clearModal("editOperQuestion");
	
	//左边分组树
	$('#operquestion_examtype_table').treegrid({  
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
	    	var pnode = $('#operquestion_examtype_table').treegrid('getChildren',typeId);
	    	if(pnode.length > 0){//父节点禁用修改，绑定用户、角色
	    		 $('#operquestion_examtype_table').treegrid('unselect',typeId);
	    		return false;
	    	}
	    	OperQuestionHandler.reloadOperQuestion(typeId); //加载右边选中的tab下的表格数据
	    },
	    onContextMenu:  onContextMenuFun
	}); 
	
	//右边用户列表
	$('#operquestion_table').datagrid({  
		url: 			ctx +'operquestion/list.json', 
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
	
	$("#operquestion_tab").tabs({//右边tab页
		fit:true,
		border:false,
		pill:false,
		onSelect: function(title,index){
			var rowsChecked = $('#operquestion_examtype_table').treegrid('getSelected');//选中的行
			if(rowsChecked){
				OperQuestionHandler.reloadOperQuestion(rowsChecked.id);
			}
		}
	});
	
});
 

function groutRoleFmt(val,row,index){
	return "<a href='javascript:void(0);' onclick=OperQuestionHandler.removeGroupRole('"+row.roleId+"');>删除</a>";
}

function onContextMenuFun(e, row) {//右键菜单
		e.preventDefault();
		var groupId = row.groupId;
		$(this).treegrid('select', groupId);
		$('#group_menu').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
		 
		var node = $('#operquestion_examtype_table').treegrid('getParent', row.groupId);
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
		var node = $('#operquestion_examtype_table').treegrid('getParent',groupId);
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
    	
    	var pnode = $('#operquestion_examtype_table').treegrid('getChildren',groupId);
    	if(pnode.length > 0){//父节点禁用修改，绑定用户、角色
    		isHide('disableItem','.pGroupHide');
    	}else{
    		isHide('enableItem','.pGroupHide');
    	}
    	
};

$(function(){//清除模态窗体的数据,每次打重新加载
	$("#editOperQuestion").on("hidden", function() {
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
var OperQuestionHandler = {
		
	reloadOperQuestion: function(typeId){//组用户列表reload
		$("#operquestion_examType_id").val(typeId);
		//加载加载当前组下用户
		var params = $("#operquestion_form").serializeJson();
		$("#operquestion_table").datagrid("load",params);
	},
	
	search: function (){//查询用户
		 var params = $("#operquestion_form").serializeJson();
		 params._time = new Date().getTime();
		 
		 $('#operquestion_table').datagrid('load',params); 
	},
	
	exportExcel: function(){//导出excel模板
		window.location.href = ctx + "/template/operquestion.xls";
	},
	
	importExcel: function(){//导入excel
		var typeId=$.trim($("#operquestion_examType_id").val());
		if(typeId == ''|| typeId == undefined){
			tipMsg("importExcel_operquestion","请在左侧树结构中选择分类!");
			return false;
		}else{
				$("#importExcelModal_operquestion").modal({//弹出窗体
			       	 remote: ctx + 'operquestion/breforeImportExcel.html'
			       }); 
		}
       
	},
	
	uploadExcel: function(){//上传
		var typeId=$.trim($("#operquestion_examType_id").val());
		var file=$.trim($("#operquestion_file").val());
		//alert(file);
		if(file == ''|| file == undefined){
			$("#operquestion_fileTip").text("请选择上传的Excel文件！").show();
		}else{
			var url = ctx +  "operquestion/importExcel.json?typeId="+typeId;
			$("#operquestionForm").attr("action",url).submit();
		}
		
	},
	 
	beforeEditOperQuestion: function (flag){//加载编辑页面
   	var url =  ctx + "/operquestion/beforeEditOperQuestion.html";
		if(flag == 2){//修改
			var rowsChecked = $('#operquestion_table').datagrid('getChecked');
			var len = rowsChecked.length;
			if(len > 1 || len==0){
				tipMsg("update_operquestion","请选择单行记录编辑");
				return false;
			}
		    if(null != rowsChecked && len == 1){
		    	url +="?id=" + rowsChecked[0].id;
		    	url +="&flag=2";
		    }else{
		    	return false;
		    }
		}else{
			var typeId=$.trim($("#operquestion_examType_id").val());
			if(typeId==''){
				tipMsg("save_operquestion","请在左侧树结构中选择分类");
				return false;
			}
			url +="?typeId=" + typeId;
		}
		
		showModal("editOperQuestion",url);//弹出窗体
   },
	
	editOperQuestion: function(){//提交编辑填空题
		if(!this.check("operquestion_question")){
			return false;
		}
		if(!this.checkoptions("operquestion_answer")){
			return false;
		}
		var me = this;
		var url =  ctx + "/operquestion/editOperQuestion.json";
       $.ajax({
       	type: "POST",
       	url:  url,
       	dataType: "json",
       	data:  $("#operquestionForm").serialize(),
       	success: function(data){
       		if(data && data.status != 0){ 
          			
          			me.search();  //表格重新加载
          			$('#operquestion_table').datagrid('clearSelections');//清除缓存之前选中的行
          			
          			var content = (data.flag ? "修改成功" : "添加成功");
          			var id = (data.flag ? "update_operquestion": "save_operquestion");
          			tipMsg(id,content);
          			
          			closeModal('editOperQuestion');//关闭modal窗口
       		}
       	}
       });
		
   },
   
   hideTip: function(obj){//隐藏验证提示
   	var id = $(obj).attr("id");
   	$("#" + id + "Tip").removeClass("in");
   },
   
   isDeleteTip: function(){//是否删除提示框
   	 $("#isDeleteOperQuestionTip").modal({
   		 backdrop: 'static',
   		 keyboard: false
   	 }); 
   },
   
   deleteOperQuestion: function(){//确认删除
   	var me = this;
   	var ids = "";
   	var rowsChecked = $('#operquestion_table').datagrid('getChecked');
   	for(var i=0,len = rowsChecked.length; i < len; i++ ){
   		ids += (i > 0 ? "," : "");
   		ids += rowsChecked[i].id;
   	}
   	
   	var url =  ctx + "/operquestion/deleteOperQuestion.json";
   	$.post(url,{
   		ids: ids,
   		_time: new Date().getTime()
   	},function(data){
   		if(data && data.status != 0){
   			$("#cancleOperQuestionDel").click(); //隐藏提示窗体
   			me.search();  //表格重新加载
   			$('#operquestion_table').datagrid('clearSelections');//清除缓存之前选中的行
   			
   			tipMsg("beforeDelete_operquestion","删除成功");
   		}
   	});
   },
   
   beforeDeleteOperQuestion: function(){//删除之前提示
	    var me = this;
   	var rowsChecked = $('#operquestion_table').datagrid('getChecked');
	    if(null != rowsChecked && rowsChecked.length > 0){
	    	me.isDeleteTip(); //确认是否删除
	    	return false;
	    }else{
	    	tipMsg("beforeDelete_operquestion","请选择要删除的试题！");
	    }
   },
   
   cancleEdit: function(){//删除之前提示
	   $("#cancleOperQuestionEdit").click(); //隐藏提示窗体
   },
   closeImportExcel: function(){//删除之前提示importExcelModal_operquestion
	   this.search();  //表格重新加载
	   closeModal('importExcelModal_operquestion');//关闭modal窗口
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
