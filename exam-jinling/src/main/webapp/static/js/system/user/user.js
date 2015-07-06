var ctx = $('base').attr('href');
 
$(function(){

	$('#user_table').datagrid({  
		url: ctx +'/user/list.json', 
	    height: 'auto', 
	    fitColumns: true,
	    nowrap: false, 
	    striped: false, 
	    border: false, 
	    collapsible:false,//是否可折叠的 
	    processing: true,	//载入数据的时候是否显示“载入中”
        pageLength: 15,		//首次加载的数据条数
	    fit: true,//自动大小 
	    idField:'userId', 
	    singleSelect:false,//是否单选 
	    pagination:true,//分页控件 
	    rownumbers:true,//行号 
	    frozenColumns:[[ 
	        {field:'ck',checkbox:true} 
	    ]]
	    
	}); 
	
	$(function(){
		$("#editUser").on("hidden", function() {
		    $(this).removeData("modal");
		});
	});

});

/**
 * 用户事件操作
 */
var UserHandler = {
	search: function (){//查询用户
		 var params = $("#user_form").serializeJson();
		 params._time = new Date().getTime();
		 
		 $('#user_table').datagrid('load',params); 
	},
	
	authz: function(){//授权
		var rowsChecked = $('#user_table').datagrid('getChecked');
		var len = rowsChecked.length;
		if(len > 1){
			tipMsg("authz","请选择单行记录编辑");
			return  false;
		}
		
		//弹出窗体
        $("#authzUser").modal({
        	 remote: ctx + '/user/authz.html'
        }); 
		
	},
	
	beforeEditUser: function (flag){//加载编辑页面
    	var url =  ctx + "/user/beforeEditUser.html";
		if(flag == 2){//修改
			var rowsChecked = $('#user_table').datagrid('getChecked');
			var len = rowsChecked.length;
			if(len > 1){
				tipMsg("update","请选择单行记录编辑");
				return false;
			}
		    if(null != rowsChecked && len == 1){
		    	url +="?userId=" + rowsChecked[0].userId;
		    	url +="&flag=2";
		    }else{
		    	return false;
		    }
		}
		
		//弹出窗体
        $("#editUser").modal({
        	 backdrop: 'static',
    		 keyboard: false,
        	 remote: url
        }); 
    },
	
	editUser: function(){//提交编辑用户
		if(!this.check("loginName")){
			return false;
		}
		if(!this.checkDept("deptId")){
			return false;
		}
		
		var me = this;
		var url =  ctx + "/user/editUser.json";
        $.ajax({
        	type: "POST",
        	url:  url,
        	dataType: "json",
        	data:  $("#userForm").serialize(),
        	success: function(data){
        		if(data && data.status != 0){ 
           			$("#cancleEdit").click(); //隐藏提示窗体
           			me.search();  //表格重新加载
           			$('#user_table').datagrid('clearSelections');//清除缓存之前选中的行
           			
           			var content = (data.flag ? "修改成功" : "添加成功");
           			var id = (data.flag ? "update": "save");
           			tipMsg(id,content);
        		}
        	}
        });
		
    },
    
    isDeleteTip: function(){//是否删除提示框
    	 $("#isDeleteTip").modal({
    		 backdrop: 'static',
    		 keyboard: false
    	 }); 
    },
    
    deleteUser: function(){//确认删除
    	var me = this;
    	var ids = "";
    	var rowsChecked = $('#user_table').datagrid('getChecked');
    	for(var i=0,len = rowsChecked.length; i < len; i++ ){
    		ids += (i > 0 ? "," : "");
    		ids += rowsChecked[i].userId;
    	}
    	
    	var url =  ctx + "/user/deleteUser.json";
    	$.post(url,{
    		ids: ids,
    		_time: new Date().getTime()
    	},function(data){
    		if(data && data.status != 0){
    			$("#cancleDel").click(); //隐藏提示窗体
    			me.search();  //表格重新加载
    			$('#user_table').datagrid('clearSelections');//清除缓存之前选中的行
    			
    			tipMsg("beforeDeleteUser","删除成功");
    		}
    	});
    },
    
    beforeDeleteUser: function(){//删除之前提示
	    var me = this;
    	var rowsChecked = $('#user_table').datagrid('getChecked');
	    if(null != rowsChecked && rowsChecked.length > 0){
	    	me.isDeleteTip(); //确认是否删除
	    	return false;
	    }
    },
    
    /*验证账号*/
    check: function(id){ 
    	var loginName = $.trim($("#" + id,".modal-body").val());
        if(!loginName){
        	 $("#" + id + "Tip").show();
        	 return false;
        }else{
        	 $("#" + id + "Tip").hide();
        }
        return true;
    },
    
    /*验证部门*/
    checkDept: function (id){ 
    	if($("#"+id,".modal-body").val() == 0){
    		 $("#" + id + "Tip").show();
    		return false;
    	}else{
    		 $("#" + id + "Tip").hide();
    	}
    	return true;
    }
};