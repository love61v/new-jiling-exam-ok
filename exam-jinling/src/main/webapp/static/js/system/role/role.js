var ctx = $('base').attr('href');
 
$(function(){

	$('#role_table').datagrid({  
		url: ctx +'/role/list.json', 
	    height: 'auto', 
	    fitColumns: true,
	    nowrap: false, 
	    striped: false, 
	    border: false, 
	    collapsible:false,//是否可折叠的 
	    fit: true,//自动大小 
	    idField:'roleId', 
	    singleSelect:false,//是否单选 
	    pagination:true,//分页控件 
	    rownumbers:true,//行号 
	    frozenColumns:[[ 
	        {field:'ck',checkbox:true} 
	    ]]
	}); 
	
	/**
	 * 编辑角色信息窗体关闭时触发事件
	 */
	$("#editRole").on("hidden", function() {
	    $(this).removeData("modal");
	});
	
	/**
	 * 角色授权窗体关闭时触发事件
	 */
	$('#authzRole').on('hidden.bs.modal', function () {
		RoleHandler.cancleAllChk();
	});

});

/**
 * 角色事件操作
 */
var RoleHandler = {
	search: function (){//查询用户
		 var params = $("#role_form").serializeJson();
		 params._time = new Date().getTime();
		 $('#role_table').datagrid('load',params); 
	},
	
	beforeEditRole: function (flag){//加载编辑页面
    	var url =  ctx + "/role/beforeEditRole.html";
		if(flag == 2){//修改
			var rowsChecked = $('#role_table').datagrid('getChecked');
			var len = rowsChecked.length;
			if(len > 1){
				tipMsg("update","请选择单行记录编辑");
				return false;
			}
		    if(null != rowsChecked && len == 1){
		    	url +="?roleId=" + rowsChecked[0].roleId;
		    	url +="&flag=2";
		    }else{
		    	return false;
		    }
		}
		
		//弹出窗体
		showModal('editRole',url);
    },
    
    authzResource: function(roleId){//分配资源页面
    	var url =  ctx + "/role/authzRole.html?roleId=" + roleId + "&t" + Math.random(1000);
    	$.get(url, null, function(data){
    	    $('#authzRole .modal-body').html(data); //把页面读取到modal中每次请求新数据
	    });
	    $('#authzRole').modal({show:true,backdrop:false});
    },
    
    authzRole: function(){//保存授权角色资源与操作权
    	var roleId = $("#authc_roleId").val();//角色ID
    	var arr = []; 
    	
    	var moduel_chkedArr = $("input:checkbox[name^='module_']:checked");//所有选中的模块
    	if(moduel_chkedArr.length > 0){
			for (var i = 0, len = moduel_chkedArr.length; i < len; i++) {
				var moduleId = moduel_chkedArr[i].value;  //模块ID
				var result = {}; 
				var operateId = ""; //操作 ID
				var j = 0;
				
				$("input:checkbox[name^='oper_"+moduleId+"']:checked").each(function(){
					operateId += (j > 0 ? ",": "");
					operateId += $(this).val();
					j++;
 				});
				result.roleId = roleId;
				result.resourceId = moduleId;
				result.operateId = operateId;
				
				arr.push(result);
    		}
			 
			var url =  ctx + "/role/saveAuthzRole.json";//保存
			var me = this;
			$.post(url,
					{
						roleId: 	roleId,
						permissons: JSON.stringify(arr)
					},
					function(data){
						if(data && data.status != 0){ 
		           			$("#cancleauthzRole").click(); //关闭窗体
		           			me.cancleAllChk();
		        		}
			});
    	}else{
    		tipMsg("acceptAuthz","请勾选资源与操作","top");
    		return false;
    	}
    },
	
	editRole: function(){//提交编辑用户
		if(!this.checkRole("roleName")){
			return false;
		}
		
		var me = this;
		var url =  ctx + "/role/editRole.json";
        $.ajax({
        	type: "POST",
        	url:  url,
        	dataType: "json",
        	data:  $("#roleForm").serialize(),
        	success: function(data){
        		if(data && data.status != 0){ 
           			$("#cancleEditRole").click(); //隐藏提示窗体
           			me.search();  //表格重新加载
           			$('#role_table').datagrid('clearSelections');//清除缓存之前选中的行
           			
           			var content = (data.flag ? "修改成功" : "添加成功");
           			var id = (data.flag ? "updateRole": "saveRole");
           			tipMsg(id,content);
        		}
        	}
        });
		
    },
    
    isDeleteTip: function(){//是否删除提示框
    	 $("#isDeleteRoleTip").modal({
    		 backdrop: 'static',
    		 keyboard: false
    	 }); 
    },
    
    deleteRole: function(){//确认删除
    	var me = this;
    	var ids = "";
    	var rowsChecked = $('#role_table').datagrid('getChecked');
    	for(var i=0,len = rowsChecked.length; i < len; i++ ){
    		ids += (i > 0 ? "," : "");
    		ids += rowsChecked[i].roleId;
    	}
    	
    	var url =  ctx + "/role/deleteRole.json";
    	$.post(url,{
    		ids: ids,
    		_time: new Date().getTime()
    	},function(data){
    		if(data && data.status != 0){
    			$("#cancleDel").click(); //隐藏提示窗体
    			me.search();  //表格重新加载
    			$('#role_table').datagrid('clearSelections');//清除缓存之前选中的行
    			
    			tipMsg("beforeDeleteRole","删除成功");
    		}
    	});
    },
    
    beforeDeleteRole: function(){//删除之前提示
	    var me = this;
    	var rowsChecked = $('#role_table').datagrid('getChecked');
	    if(null != rowsChecked && rowsChecked.length > 0){
	    	me.isDeleteTip(); //确认是否删除
	    	return false;
	    }
    },

    reloadTree: function(){ //刷新重新加载树
    	$('#authzRole_table').treegrid('reload');
    	$('#authzRole_table').treegrid('unselectAll');
    },

	allIsCheck : function(obj) {// 授权全选反选
		var isChked = obj.checked;
		if(isChked){
			$(".allme_9").attr("checked",  obj.checked);// 模块
		}else{
			this.reloadTree();
		}
	},
	
	cancleAllChk: function(){//全选checkbox勾选中
		$("input:checkbox[name='allChk']").attr("checked",false);
	},
    
    checkRole: function (id){//验证
    	var val= $.trim($("#" + id, ".modal-body").val());
        if(!val){
        	 $("#" + id + "Tip").show();
        	 return false;
        }else{
        	$("#" + id + "Tip").hide();
        }
        return true;
    }
    
};

/*
 * 格式化操作列
 */
function formatAction(val,row,index){
	return "<a href='javascript:void(0);' onclick=RoleHandler.authzResource('"+row.roleId+"');>分配资源</a>";
};



