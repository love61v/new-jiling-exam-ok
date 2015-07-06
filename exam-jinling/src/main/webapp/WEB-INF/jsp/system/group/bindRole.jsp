<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<table id="bindRole_table" cellspacing="0" cellpadding="0">
	<thead>
		<tr>
			<th data-options="field:'roleName',width:50,align:'center'">名称</th>
			<th data-options="field:'remark',width:80,align:'center'">备注</th>
		</tr>
	</thead>
</table>

<script>
	$(function() {
		$('#bindRole_table').datagrid({  
		    idField:'roleId', 
			url: ctx +'/role/list.json', 
		    height: '300px', 
		    fitColumns: true,
		    nowrap: false, 
		    striped: false, 
		    border: true, 
		    collapsible:false, 
		    singleSelect:false, 
		    rownumbers:true,//行号 
		    frozenColumns:[[ 
		        {field:'ck',checkbox:true} 
		    ]]
		}); 

	});
</script>