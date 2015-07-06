<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form class="form-horizontal" role="form" id="userForm">
 <div class="form-group" style="display:none;">
   <label for="userId" class="col-sm-1 control-label">用户ID</label>
   <div class="col-sm-2 controls">
      <input type="text" class="input-large span3" name="userId" id="userId" value="${user.userId }">
   </div>
</div>

<div class="form-group">
   <div class="col-sm-2 controls" style="color:red;display:none;" id="loginNameTip">
   		<i class="icon-exclamation-sign"></i>请输入登陆账号
   	</div>
   <label for="loginName" class="control-label">登陆账号</label>
   <div class="col-sm-2 controls">
      <input type="text" class="input-large span3" name="loginName" id="loginName" value="${user.loginName }"
	            placeholder="登陆账号" onfocus="doHideTip(this);">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <div class="col-sm-2 controls" style="color:red;display:none;" id="userNameTip">
   	<i class="icon-exclamation-sign"></i>请输入用户名</div>
   <label for="userId" class="control-label">用户名</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="userName" id="userName" value="${user.userName }"
	            placeholder="用户名称" onfocus="doHideTip(this);">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <label for="phoneNumber" class="col-sm-2 control-label">电话号码</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="phoneNumber" id="phoneNumber" value="${user.phoneNumber }"
	            placeholder="电话号码">
   </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <label for="email" class="col-sm-2 control-label">邮箱</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="email" id="email" value="${user.email }"
	            placeholder="邮箱">
   </div>
</div>

 <div class="form-group" style="margin-top: 20px;">
	 <div class="col-sm-2 controls" style="color:red;display:none;" id="deptIdTip">
	 	<i class="icon-exclamation-sign"></i>请选择部门
	 </div>
	 
   	<label for="deptId" class="col-sm-2 control-label">部门 </label>
   	<div class="col-sm-3 controls">
     <select class="input-large span3" name="deptId" id="deptId" onchange="doHideTip(this);">
     	 <option value="0">-请选择部门-</option>
		<c:forEach items="${deptList }" var="el">
	        <option value="${el.deptId }" <c:if test="${user.deptId == el.deptId  }">selected="selected"</c:if> >${el.deptName }</option>
		</c:forEach>
     </select>
	      
	    </div>
</div>

<div class="form-group" style="margin-top: 30px;">
   <label for="sex" class="col-sm-2 control-label">性别 </label>
   <div class="col-sm-6 controls">
        <span>
        	<input type="radio" name="sex" id="sex" value="1" checked="checked"> 男
        </span>
		<span style="margin-left:30px;">
			<input type="radio"  name="sex" id="sex" value="2"  <c:if test="${user.sex== 2  }">checked="checked"</c:if> > 女
		</span>
   </div>
</div>
 
</form>