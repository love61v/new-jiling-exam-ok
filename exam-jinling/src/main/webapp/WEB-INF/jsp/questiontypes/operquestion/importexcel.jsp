<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form class="form-horizontal" method="post" role="form" id="operquestionForm" target="operquestion_iframe" enctype="multipart/form-data">
	<div class="form-group" style="margin-top: 30px;">
	<div class="col-sm-2 controls" style="color:red;display:none;" id="operquestion_fileTip"></div>
	   <div class="col-sm-6 controls">
	        <span>
	        	<input type="file" name="file" id="operquestion_file"/> 
	        </span>
			 
	   </div>
	</div>
</form>

<!-- 显示上传状态 -->
<iframe id="operquestion_iframe" name="operquestion_iframe" style="height:50px;margin-left:50px;border: 0px solid #fff;"></iframe>