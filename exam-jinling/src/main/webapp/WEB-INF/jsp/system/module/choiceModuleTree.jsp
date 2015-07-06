<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<table id="module_treegrid" cellspacing="0" cellpadding="0">
       <thead>
		<tr>
			<th data-options="field:'resourceName',width:80">名称</th>
			<th data-options="field:'path',width:60,align:'center'">地址</th>
			<th data-options="field:'status',width:30, formatter: formatStatus,align:'center'">状态</th>
            <th data-options="field:'createTime',width:80, formatter:formatTime,align:'center'">创建时间</th>
		</tr>
	</thead>
</table>
	 

<script>
$(function(){ 
	$('#module_treegrid').treegrid({  
		url: 			ctx +'/module/list.json', 
	    height: 		'400px', 
	    fitColumns: 	true,
	    nowrap: 		false, 
	    striped: 		false, 
	    border: 		true, 
	    collapsible:	false,//是否可折叠的  
	    processing: 	false,
	    idField:		'resourceId', 
	    treeField:		'resourceName'
	}); 

});
</script>