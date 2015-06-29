<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form class="form-horizontal" method="post" role="form" id="singleChoiceForm" target="_self" enctype="multipart/form-data">

<div class="form-group" style="margin-top: 30px;">
   <div class="col-sm-6 controls">
        <span>
        	<input type="hidden" name="type" value="1"/>
        	<input type="file" name="file"/> 
        </span>
		 
   </div>
</div>
 
</form>