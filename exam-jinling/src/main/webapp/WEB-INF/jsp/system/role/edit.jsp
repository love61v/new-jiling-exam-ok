<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form class="form-horizontal" role="form" id="roleForm">
 <div class="form-group" style="display:none;">
   <label for="roleId" class="col-sm-1 control-label">用户ID</label>
   <div class="col-sm-2 controls">
      <input type="text" class="input-large span3" name="roleId" id="roleId" value="${role.roleId }">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <div class="col-sm-2 controls" style="color:red;display:none;" id="roleNameTip">
   	<i class="icon-exclamation-sign"></i>请输入角色名</div>
   <label for="roleName" class="control-label">角色名</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="roleName" id="roleName" value="${role.roleName }"
	            placeholder="用户名称" onfocus="doHideTip(this);">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <label for="engName" class="col-sm-2 control-label">英文名称</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="engName" id="engName" value="${role.engName }"
	            placeholder="英文名称">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <label for="remark" class="col-sm-2 control-label">备注</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="remark" id="remark" value="${role.remark }"
	            placeholder="备注">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <label for="status" class="col-sm-2 control-label">状态</label>
   <div class="col-sm-6 controls">
        <span>
        	<input type="radio" name="status" id="status" value="1" checked="checked">应用
        </span>
		<span style="margin-left:30px;">
			<input type="radio"  name="status" id="status" value="2"  <c:if test="${role.status== 2  }">checked="checked"</c:if> > 禁用
		</span>
   </div>
</div>
 
</form>