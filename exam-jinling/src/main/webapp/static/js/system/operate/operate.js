var ctx = $('base').attr('href');
 
$(function(){

	$('#operate_table').datagrid({  
		url: ctx +'/operate/list.json', 
	    height: 'auto', 
	    fitColumns: true,
	    nowrap: false, 
	    striped: true, 
	    border: false, 
	    collapsible:false,//是否可折叠的 
	    fit: true,//自动大小 
	    idField:'operateId', 
	    singleSelect:false,//是否单选 
	    pagination:true,//分页控件 
	    rownumbers:true,//行号 
	    frozenColumns:[[ 
	        {field:'ck',checkbox:true} 
	    ]],
	    
	}); 
	
	$("#editOperate").on("hidden", function() {
		$(this).removeData("modal");
	});

});

/**
 * 角色事件操作
 */
var OperateHandler = {
	search: function (){//查询用户
		 var params = {_time: new Date().getTime() };
		 params.operateName = $.trim($("#operateName").val());
		  
		 $('#operate_table').datagrid('load',params); 
	},
	
	beforeEditOperate: function (flag){//加载编辑页面
    	var url =  ctx + "/operate/beforeEditOperate.html";
		if(flag == 2){//修改
			var rowsChecked = $('#operate_table').datagrid('getChecked');
			var len = rowsChecked.length;
			if(len > 1){
				tipMsg("update","请选择单行记录编辑");
				return false;
			}
		    if(null != rowsChecked && len == 1){
		    	url +="?operateId=" + rowsChecked[0].operateId;
		    	url +="&flag=2";
		    }else{
		    	return false;
		    }
		}
		
		//弹出窗体
        $("#editOperate").modal({
        	 backdrop: 'static',
    		 keyboard: false,
        	 remote: url
        }); 
    },
	
	editOperate: function(){//提交编辑用户
		if(!this.checkOperate("operateName")){
			return false;
		}
		
		var me = this;
		var url =  ctx + "/operate/editOperate.json";
        $.ajax({
        	type: "POST",
        	url:  url,
        	dataType: "json",
        	data:  $("#operateForm").serialize(),
        	success: function(data){
        		if(data && data.status != 0){ 
           			$("#cancleEditOperate").click(); //隐藏提示窗体
           			me.search();  //表格重新加载
           			$('#operate_table').datagrid('clearSelections');//清除缓存之前选中的行
           			
           			var content = (data.flag ? "修改成功" : "添加成功");
           			var id = (data.flag ? "updateOperate": "saveOperate");
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
    	 $("#isDeleteOperateTip").modal({
    		 backdrop: 'static',
    		 keyboard: false
    	 }); 
    },
    
    deleteOperate: function(){//确认删除
    	var me = this;
    	var ids = "";
    	var rowsChecked = $('#operate_table').datagrid('getChecked');
    	for(var i=0,len = rowsChecked.length; i < len; i++ ){
    		ids += (i > 0 ? "," : "");
    		ids += rowsChecked[i].operateId;
    	}
    	
    	var url =  ctx + "/operate/deleteOperate.json";
    	$.post(url,{
    		ids: ids,
    		_time: new Date().getTime()
    	},function(data){
    		if(data && data.status != 0){
    			$("#cancleDel").click(); //隐藏提示窗体
    			me.search();  //表格重新加载
    			$('#operate_table').datagrid('clearSelections');//清除缓存之前选中的行
    			
    			tipMsg("beforeDeleteOperate","删除成功");
    		}
    	});
    },
    
    beforeDeleteOperate: function(){//删除之前提示
	    var me = this;
    	var rowsChecked = $('#operate_table').datagrid('getChecked');
	    if(null != rowsChecked && rowsChecked.length > 0){
	    	me.isDeleteTip(); //确认是否删除
	    	return false;
	    }
    },
    
    checkOperate: function (id){//验证
    	var val= $.trim($("#" + id,".modal-body").val());
        if(!val){
        	 $("#" + id + "Tip").show();
        	 return false;
        }
        return true;
    }
    
};


