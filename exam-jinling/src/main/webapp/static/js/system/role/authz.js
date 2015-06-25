var ctx = $('base').attr('href'); 
var authlist = [];//资源操作权
$(function(){

	$('#authzRole_table').treegrid({  
		url:			ctx +'/module/list.json', 
	    height: 		'400px', 
	    nowrap: 		false, 
	    striped: 		true, 
	    border: 		true, 
	    collapsible:	false,//是否可折叠的 
	    idField:		'resourceId', 
	    treeField:		'resourceName'
	}); 
	
	authlist = getAuth();//资源权
	  
});


$(function(){
	$('#authzRole').on('shown.bs.modal', function () {
		 $('#authzRole_table').treegrid('reload'); 
	});
	$('#authzRole').on('hidden.bs.modal', function () {
		$("#authc_roleId").val("");
		authlist = [];
	});
	
});

/*
 * 根据角色ID查询资源与操作权
 */
function getAuth(){
	var list = [];
	var roleId = $("#authc_roleId").val();//角色ID
	var url =  ctx + "/role/getAuth.json";
	
	 $.ajax({
       	type: "POST",
       	url:  url,
       	dataType: "json",
       	async: false,  //同步
       	data:  {
       		roleId: roleId
       	},
       	success: function(data){
       		if(data){
       			list = data; 
       		}
       	}
       });
	 return list;
};

/**
 * 格式化名称且加checkbox
 * @param val
 * @param row
 * @param index
 */
function formatResourceName(val,row,index){
	var moduleID = row.resourceId;
	var node = $('#authzRole_table').treegrid('getChildren',moduleID);
	if(node.length > 0){
		return val;
	} 
	 
	var checkbox = val + "&nbsp;&nbsp;<input  type='checkbox' name='module_#' id='module_#' value='#' @checked />";
	checkbox = checkbox.replaceAll("#", moduleID);
	
	for(var i=0,len = authlist.length;i < len;i++){
		if(authlist[i].resourceId == moduleID){
			checkbox = checkbox.replace("@checked","checked='checked'");
		}else{
			checkbox = checkbox.replaceAll("@checked", "");
		}
	}
	
	return checkbox;
};


/**
 * 格式化操作名的列
 * @param val
 * @param row
 * @param index
 * @returns
 */
var operateArr = []; //操作列表
function formatOperate(val,row,index){
	 
	if(!row._parentId){
		return '';
	}
	var node = $('#authzRole_table').treegrid('getChildren',row.resourceId);
	if(node.length > 0){
		return '';
	}
	
	var len = operateArr.length;
	if(len == 0){ 
		var url =  ctx + "/operate/findlist.json";
		 $.ajax({
	        	type: "POST",
	        	url:  url,
	        	dataType: "json",
	        	async: false, 
	        	//data:  $("#roleForm").serialize(),
	        	success: function(data){
	        		if(data){
	        			operateArr = data;
	        			len = operateArr.length;
	        		}
	        	}
	        });
	}
	
	var raw = "";
	if(len > 0){debugger;
		var checkbox = "<input style='margin-left:10px;'  type='checkbox' @checked  name='oper_@moduleID' value='#'/>&nbsp;&nbsp;@name";
		for (var i = 0; i < len; i++) {
			var tem = checkbox;
			tem = tem.replaceAll("#", operateArr[i].operateId)
					  .replace("@moduleID", row.resourceId)
					  .replace("@name", operateArr[i].operateName);
			raw += tem;
			if (i > 0 && i % 4 == 0 ){
				raw += "<p><p>";
			}
			 
			for(var k=0,len2 = authlist.length;k < len2;k++){
				if(row.resourceId == authlist[k].resourceId){
					var authOperStr = authlist[k].operateId ; //已授权的操作用逗号分隔的串
					if(null == authOperStr){
						continue;
					}
					var authOperStr = authOperStr.split(",");
					for ( var j = 0, len3 = authOperStr.length; j < len3; j++) {
						if (authOperStr[j] = operateArr[j].operateId) {
							tem = tem.replace("@checked", "checked='checked'");
							 
						}
					}
				}else{
					raw = raw.replaceAll("@checked", "");
				}
			}
		};
	}
	
	return raw;
 };

