<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<table id="bindUser_table" toolbar="#tbar_bindUser" cellspacing="0" cellpadding="0">
	<thead>
		<tr>
			<th data-options="field:'department',width:50,formatter: formatDept,align:'center'">部门</th>
			<th data-options="field:'loginName',width:50,align:'center'">账号</th>
			<th data-options="field:'userName',width:50,align:'center'">用户名</th>
		</tr>
	</thead>
</table>

<div id="tbar_bindUser">
  		<div class="form-inline" >
  				<form id="bindUser_form" style="margin-top:5px;">
	   		<input type="text" class="form-control" name="userQuery" id="userQuery" placeholder="账号  / 用户名称">
	    	<a class="btn btn-success" href="javascript:void(0)"   onclick="GroupHandler.userSearch();">
				<i class="icon-search icon-white"></i>查询
			</a>
		</form>
	</div>
</div>

<script>
	$(function() {

		$('#bindUser_table').datagrid({
			url : ctx + '/user/list.json',
			height : '400px',
			fitColumns : true,
			nowrap : false,
			striped : false,
			border : true,
			collapsible : false,//是否可折叠的 
			idField : 'userId',
			singleSelect : false,//是否单选 
			pagination : true,//分页控件 
			rownumbers : true,//行号 
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
		});
		pageFmt('bindUser_table');
		
	});
</script>