var ctx = $('base').attr('href');
 
$(function(){

	$('#role_table').datagrid({  
		url: ctx +'/role/list.json', 
	    height: 'auto', 
	    fitColumns: true,
	    nowrap: false, 
	    striped: true, 
	    border: false, 
	    collapsible:false,//是否可折叠的 
	    fit: true,//自动大小 
	    idField:'roleId', 
	    singleSelect:false,//是否单选 
	    pagination:true,//分页控件 
	    rownumbers:true,//行号 
	    frozenColumns:[[ 
	        {field:'ck',checkbox:true} 
	    ]],
	    
	}); 
	
	
	//设置分页控件 
	var p = $('#role_table').datagrid('getPager'); 
	$(p).pagination({ 
		pageSize: 10,//每页显示的记录条数，默认为15 
	    pageList: [10,15,30,50,100],//可以设置每页记录条数的列表 
	    beforePageText: '第',//页数文本框前显示的汉字 
	    afterPageText: '页    共 {pages} 页', 
	    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	});  
	
	$(function(){
		$("#editRole").on("hidden", function() {
		    $(this).removeData("modal");
		});
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
        $("#editRole").modal({
        	 backdrop: 'static',
    		 keyboard: false,
        	 remote: url
        }); 
    },
    
    authzResource: function(){//分配资源
    	var url =  ctx + "/role/authzRole.html";
    	
        $("#authzRole").modal({//弹出窗体
        	 backdrop: 'static',
    		 keyboard: false,
        	 remote: url
        }); 
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
    
    hideTip: function(obj){//隐藏验证提示
    	var id = $(obj).attr("id");
    	$("#" + id + "Tip").removeClass("in");
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
    
    checkRole: function (id){//验证
    	alert($("#" + id).val());
    	debugger;
    	var val= $.trim($("#" + id).val());
        if(!val){
        	 $("#" + id + "Tip").addClass("in");
        	 //return false;
        }
        return true;
    }
    
};


