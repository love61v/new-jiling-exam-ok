<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src='${ctx}/js/system/role/authz.js' type='text/javascript'></script>
 
<table id="authzRole_table" cellspacing="0" cellpadding="0">
     <thead>
		<tr>
			<th data-options="field:'resourceName',width:180,align:'center',formatter: formatResourceName"  rowspan="2">名称</th>
			<th colspan="${fn:length(operatelist) }">操作</th>
		</tr>
		<tr>
			<c:forEach items="${operatelist}" var="el">
				<th data-options="field:'id',width:60,align:'center', formatter:formatOperate">${el.handleName }</th>
			</c:forEach>
		</tr>
	</thead>
</table>