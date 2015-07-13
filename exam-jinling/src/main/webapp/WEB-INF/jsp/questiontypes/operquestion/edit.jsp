<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form class="form-horizontal" group="form" id="operquestionForm">
<span style="display:none;">
       <input type="text"   name="typeId" id="typeId" value="${operquestion.typeId }"> 
      <input type="text"   name="id" id="id" value="${operquestion.id }">
   
</span>


<div class="form-group"  style="margin-top: 10px;">
   <div class="col-sm-2 controls" style="color:red;display:none;" id="operquestion_questionTip"></div>
   
   <label for="groupName" class="control-label">题目</label>
   <div class="col-sm-3 controls">
      <textarea cols="20" rows="3" class="input-large span3" name="question" id="operquestion_question"  placeholder="题目" onfocus="doHideTip(this);">${operquestion.question}</textarea>
   </div>
</div>
<div class="form-group"  style="margin-top: 10px;">
   <div class="col-sm-2 controls" style="color:red;display:none;" id="operquestion_optionsTip"></div>
   
   <label for="groupName" class="control-label">参考答案</label>
   <div class="col-sm-3 controls">
      <textarea cols="20" rows="3" class="input-large span3" name="answer" id="operquestion_answer"  placeholder="操作题答案" onfocus="doHideTip(this);">${operquestion.answer}</textarea>
   </div>
</div>


<div class="form-group" style="margin-top: 30px;">
   <label for="remark" class="col-sm-2 control-label">备注</label>
   <div class="col-sm-3 controls">
      <input type="text" class="input-large span3" name="remark" id="remark" value="${operquestion.remark }"
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
			<input type="radio"  name="status" id="status" value="2"  <c:if test="${operquestion.status== 2  }">checked="checked"</c:if> > 禁用
		</span>
   </div>
</div>
</form>
