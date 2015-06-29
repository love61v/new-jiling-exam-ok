var ctx = $('base').attr('href');
 
$(function(){

	$('#singlechoice_table').datagrid({  
		url: ctx +'/singlechoice/list.json', 
	    height: 		'auto', 
	    fitColumns: 	true,
	    nowrap: 		false, 
	    striped: 		true, 
	    border: 		false, 
	    collapsible:	false,//是否可折叠的 
	    processing: 	true,	//载入数据的时候是否显示“载入中”
        pageLength: 	15,		//首次加载的数据条数
	    fit: 			true,//自动大小 
	    idField:		'id', 
	    singleSelect:	false,//是否单选 
	    pagination:		true,//分页控件 
	    rownumbers:		true,//行号 
	    frozenColumns:[[ 
	        {field:'ck',checkbox:true} 
	    ]],
	    
	}); 
	
	//设置分页控件 
	var p = $('#singlechoice_table').datagrid('getPager'); 
	$(p).pagination({ 
		pageSize: 10,//每页显示的记录条数，默认为15 
	    pageList: [10,15,30,50,100],//可以设置每页记录条数的列表 
	    beforePageText: '第',//页数文本框前显示的汉字 
	    afterPageText: '页    共 {pages} 页', 
	    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	});  
	
	$(function(){
		$("#editSinglechoice").on("hidden", function() {
		    $(this).removeData("modal");
		});
	});

});

/**
 * 用户事件操作
 */
var SinglechoiceHandler = {
	search: function (){//查询用户
		 var params = $("#singlechoice_form").serializeJson();
		 params._time = new Date().getTime();
		 
		 $('#singlechoice_table').datagrid('load',params); 
	},
	
	exportExcel: function(){//导出excel模板
		window.location.href = ctx + "/template/singlechoice.xls";
	},
	
	importExcel: function(){//导入excel
        $("#importExcelModal").modal({//弹出窗体
        	 remote: ctx + 'singlechoice/breforeImportExcel.html'
        }); 
	},
	
	uploadExcel: function(){//上传
		var url = ctx +  "singlechoice/importExcel.html";
		$("#singleChoiceForm").attr("action",url).submit();
	},
	
	authz: function(){//授权
		var rowsChecked = $('#singlechoice_table').datagrid('getChecked');
		var len = rowsChecked.length;
		if(len > 1){
			tipMsg("authz","请选择单行记录编辑");
			return  false;
		}
		
		//弹出窗体
        $("#authzSinglechoice").modal({
        	 remote: ctx + '/singlechoice/authz.html'
        }); 
		
	},
	
	beforeEditSinglechoice: function (flag){//加载编辑页面
    	var url =  ctx + "/singlechoice/beforeEditSinglechoice.html";
		if(flag == 2){//修改
			var rowsChecked = $('#singlechoice_table').datagrid('getChecked');
			var len = rowsChecked.length;
			if(len > 1){
				tipMsg("update","请选择单行记录编辑");
				return false;
			}
		    if(null != rowsChecked && len == 1){
		    	url +="?singlechoiceId=" + rowsChecked[0].singlechoiceId;
		    	url +="&flag=2";
		    }else{
		    	return false;
		    }
		}
		
		//弹出窗体
        $("#editSinglechoice").modal({
        	 backdrop: 'static',
    		 keyboard: false,
        	 remote: url
        }); 
    },
	
	editSinglechoice: function(){//提交编辑用户
		if(!check("loginName")){
			return false;
		}
		var me = this;
		var url =  ctx + "/singlechoice/editSinglechoice.json";
        $.ajax({
        	type: "POST",
        	url:  url,
        	dataType: "json",
        	data:  $("#singlechoiceForm").serialize(),
        	success: function(data){
        		if(data && data.status != 0){ 
           			$("#cancleEdit").click(); //隐藏提示窗体
           			me.search();  //表格重新加载
           			$('#singlechoice_table').datagrid('clearSelections');//清除缓存之前选中的行
           			
           			var content = (data.flag ? "修改成功" : "添加成功");
           			var id = (data.flag ? "update": "save");
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
    	 $("#isDeleteTip").modal({
    		 backdrop: 'static',
    		 keyboard: false
    	 }); 
    },
    
    deleteSinglechoice: function(){//确认删除
    	var me = this;
    	var ids = "";
    	var rowsChecked = $('#singlechoice_table').datagrid('getChecked');
    	for(var i=0,len = rowsChecked.length; i < len; i++ ){
    		ids += (i > 0 ? "," : "");
    		ids += rowsChecked[i].id;
    	}
    	
    	var url =  ctx + "/singlechoice/deleteSinglechoice.json";
    	$.post(url,{
    		ids: ids,
    		_time: new Date().getTime()
    	},function(data){
    		if(data && data.status != 0){
    			$("#cancleDel").click(); //隐藏提示窗体
    			me.search();  //表格重新加载
    			$('#singlechoice_table').datagrid('clearSelections');//清除缓存之前选中的行
    			
    			tipMsg("beforeDeleteSinglechoice","删除成功");
    		}
    	});
    },
    
    beforeDeleteSinglechoice: function(){//删除之前提示
	    var me = this;
    	var rowsChecked = $('#singlechoice_table').datagrid('getChecked');
	    if(null != rowsChecked && rowsChecked.length > 0){
	    	me.isDeleteTip(); //确认是否删除
	    	return false;
	    }
    }
};

function check(id){
	var loginName = $.trim($("#" + id).val());
    if(!loginName){
    	 $("#" + id + "Tip").addClass("in");
    	 return false;
    }
    return true;
};
 