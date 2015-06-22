<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<form class="form-horizontal" module="form" id="moduleForm">
<span style="display:none;">
      <input type="text"   name="resourceId" id="resourceId" value="${module.resourceId }"  placeholder="当前ID">
      <input type="text"   name="parentId" id="parentId" value="${pid }"  placeholder="父模ID">
   
</span>
<div class="form-group" style="margin-top: 10px;"> 
   <label for="parentName" class="col-sm-2 control-label">上级模块</label>
   <div class="col-sm-3 controls">
   <input type="text" class="input-large span3 easyui-combotree" name="parentName" id="parentName"
	         value="${pname }"   placeholder="上级模块名">
   </div>
</div>

<div class="form-group"  style="margin-top: 10px;">
   <div class="col-sm-2 controls fade" style="color:red;" id="moduleNameTip">
   	<i class="icon-exclamation-sign"></i>请输入模块名</div>
   <label for="moduleName" class="control-label">模块名</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="resourceName" id="resourceName" value="${module.resourceName }"
	            placeholder="模块名" onfocus="ModuleHandler.hideTip(this);">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <label for="engName" class="col-sm-2 control-label">模块英文名</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="engName" id="engName" value="${module.engName }"
	            placeholder="模块英文名">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <label for="engName" class="col-sm-2 control-label">地址</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="path" id="path" value="${module.path }"
	            placeholder="地址">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <label for="sort" class="col-sm-2 control-label">排序</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="sort" id="sort" value="${module.sort }"
	            placeholder="排序">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <label for="remark" class="col-sm-2 control-label">备注</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="remark" id="remark" value="${module.remark }"
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
			<input type="radio"  name="status" id="status" value="2"  <c:if test="${module.status== 2  }">checked="checked"</c:if> > 禁用
		</span>
   </div>
</div>
 
</form>