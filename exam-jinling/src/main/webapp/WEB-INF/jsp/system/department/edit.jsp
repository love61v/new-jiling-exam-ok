<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<form class="form-horizontal" department="form" id="departmentForm">
<span style="display:none;">
      <input type="text" name="deptId" id="deptId" value="${department.deptId }"  placeholder="当前ID">
      <input type="text" name="parentId" id="parentId" value="${pid }"  placeholder="父模ID">
   
</span>
<div class="form-group" style="margin-top: 30px;"> 
   <label for="parentName" class="col-sm-2 control-label">上级部门</label>
   <div class="col-sm-3 controls">
   <input type="text" class="input-large span3 easyui-combotree" name="parentName" id="parentName"
	         value="${pname }"   placeholder="上级部门名">
   </div>
</div>

<div class="form-group"  style="margin-top: 30px;">
   <div class="col-sm-2 controls" style="color:red;display:none;" id="deptNameTip">
   	<i class="icon-exclamation-sign"></i>请输入部门名</div>
   <label for="deptName" class="control-label">部门名</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="deptName" id="deptName" value="${department.deptName }"
	            placeholder="部门名" onfocus="doHideTip(this);">
   </div>
</div>
 

<div class="form-group" style="margin-top: 30px;">
   <label for="engName" class="col-sm-2 control-label">区域编码 </label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="areaCode" id="areaCode" value="${department.areaCode }"
	            placeholder="地址">
   </div>
</div>
 
 
 
</form>