<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">

<title>多项选择题管理</title>

</head>
<body>

<!-- 编辑多项选择题目 -->
 <div class="modal fade" id="editMultiChoice">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleMultiChoiceEdit"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">编辑多项选择题目</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="MultiChoiceHandler.editMultiChoice();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 导入excel -->
 <div class="modal fade" id="importExcelModal_multichoice">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleauthzMultiChoice"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">导入excel</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="MultiChoiceHandler.uploadExcel();"><i class="icon-ok icon-white"></i>&nbsp;上&nbsp;&nbsp;传</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="MultiChoiceHandler.closeImportExcel();"><i class="icon-remove icon-white"></i>关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

 <!-- 提示是否删除 -->
 <div class="modal fade" id="isDeleteMultiChoiceTip">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">提示</span></h4>
      </div>
      <div class="modal-body">
        <p><h3 style="color:red">您确定删除吗?</h3></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" id="cancleMultiChoiceDel" data-dismiss="modal">
        	<i class="icon-remove icon-white"></i>取消
        </button>
        <button type="button" class="btn btn-success" onclick="MultiChoiceHandler.deleteMultiChoice()">
        	<i class="icon-ok icon-white"></i>&nbsp;确&nbsp;&nbsp;定
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

 
<!-- 左树，右边表格 -->
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west',split:true,border:false" style="width:200px">
    	<table id="multichoice_examtype_table" cellspacing="0" cellpadding="0">
        <thead>
			<tr>
				<th data-options="field:'typeName',width:80">试题分类</th>
			</tr>
		</thead>
   	</table>
    </div>
   
   <!-- 用户列表 -->
    <div data-options="region:'center',border:false">
    <div id="multichoice_tab">
   	 <div  title="试题列表">
   	 	<table id="multichoice_table" cellspacing="0" cellpadding="0" toolbar="#tbar_ug" style="margin-top:3px;">
	        <thead>
	            <tr>
	                <th data-options="field:'prototypeQuestion',width:150,align:'center'">题目</th>
	                <th data-options="field:'score',width:50,align:'center'">分值</th>
	                <th data-options="field:'status',width:30, formatter: formatStatus,align:'center'">状态</th>
                <th data-options="field:'createTime',width:80, formatter:formatTime,align:'center'">创建时间</th>
	            </tr>
	        </thead>
   		</table>
   	
	   	<div id="multichoice_tbar_ug">
	   		<div class="form-inline" >
			  <form id="multichoice_form" style="margin-top:20px;">
			  		<!-- 组ID -->
					<input type="hidden" name="typeId" id="examType_id"/>
			   		<input type="text" class="form-control" name="prototypeQuestion" id="question_multichoice" placeholder="试题关键字">
			    	<a class="btn btn-success" href="javascript:void(0)"   onclick="MultiChoiceHandler.search();">
						<i class="icon-search icon-white"></i>查询
					</a>
				 <div class="pull-right"> 
				  <div class="btn-group" data-toggle="buttons-checkbox">
			    	<a class="btn btn-success" id="importExcel_multichoice" href="javascript:void(0)"   onclick="MultiChoiceHandler.importExcel();">
						<i class="icon-tint icon-white"></i>导入excel
					</a>
			    	<a class="btn btn-success" id="exportExcel_multichoice" href="javascript:void(0)"   onclick="MultiChoiceHandler.exportExcel();">
						<i class="icon-fire icon-white"></i>导出模板
					</a>
			    	<a class="btn btn-success" id="save_multichoice" href="javascript:void(0)"   onclick="MultiChoiceHandler.beforeEditMultiChoice(1);">
						<i class="icon-plus icon-white"></i>添加
					</a>
			    	<a class="btn btn-success" id="update_multichoice" href="javascript:void(0)" onclick="MultiChoiceHandler.beforeEditMultiChoice(2);">
						<i class="icon-edit icon-white"></i>修改
					</a>
					<a class="btn btn-success" id="beforeDelete_multichoice" href="javascript:void(0)"  onclick="MultiChoiceHandler.beforeDeleteMultiChoice();">
						<i class="icon-trash icon-white"></i>删除 
					</a>
					</div>
				 </div>
				</form>
			</div>
		</div>
	 </div>
</div>

 
<script src='${ctx}/js/questiontypes/multichoice/multichoice.js' type='text/javascript'></script> 
</body>
</html>