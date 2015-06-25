<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form class="form-horizontal" module="form" id="examtypeForm">
	<span style="display:none;">
	      <input type="text"   name="id" id="id" value="${module.id }"  placeholder="当前ID">
	      <input type="text"   name="parentId" id="parentId" value="${pid }"  placeholder="父模ID">
	   
	</span>
	<div class="form-group" style="margin-top: 10px;"> 
	   <label for="parentName" class="col-sm-2 control-label">上级模块</label>
	   <div class="col-sm-3 controls">
	   <input type="text" class="input-large span3 easyui-combotree" name="parentName" id="parentName"
		         value="${pname }" readonly="readonly"  placeholder="上级模块名">
		<!-- 修改时可变任意节点 -->
		<c:if test="${flag == 2 }">
			<a href="javascript:void(0)" onclick="examtypeHandler.choiceExamTypeTree();">选择...</a>
		</c:if>
	   </div>
	</div>
	
	<div class="form-group"  style="margin-top: 10px;">
	   <div class="col-sm-2 controls fade" style="color:red;" id="typeNameTip">
	   	<i class="icon-exclamation-sign"></i>请输入分类名称</div>
	   <label for="typeName" class="control-label">分类名</label>
	   <div class="col-sm-3 controls">
	      <input type="text" class="input-large span3" name="typeName" id="typeName" value="${module.typeName }"
		            placeholder="分类名称" onfocus="examtypeHandler.hideTip(this);">
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

 <!--模块编辑 -->
 <div class="modal fade" id="choiceExamTypeTree">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleChoiceExamType"  onclick="ModuleHandler.closeModalTree();" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">选择模块</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
       <a class="btn btn-success" href="javascript:void(0)"   onclick="examtypeHandler.reloadTree();">
			<i class="icon-refresh icon-white"></i>刷新
		</a>
        <button type="button" class="btn btn-danger" onclick="examtypeHandler.closeModalTree();"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="examtypeHandler.selectedTreeNode();"><i class="icon-ok icon-white"></i>&nbsp;确&nbsp;&nbsp;定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->