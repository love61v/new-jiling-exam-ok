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

<title>简答题管理</title>

</head>
<body>

<!-- 编辑简答题目 -->
 <div class="modal fade" id="editShortQuestion">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleShortQuestionEdit"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">编辑简答题目</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="ShortQuestionHandler.editShortQuestion();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 导入excel -->
 <div class="modal fade" id="importExcelModal_shortquestion">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleauthzShortQuestion"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">导入excel</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="ShortQuestionHandler.uploadExcel();"><i class="icon-ok icon-white"></i>&nbsp;上&nbsp;&nbsp;传</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="ShortQuestionHandler.closeImportExcel();"><i class="icon-remove icon-white"></i>关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

 <!-- 提示是否删除 -->
 <div class="modal fade" id="isDeleteShortQuestionTip">
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
        <button type="button" class="btn btn-danger" id="cancleShortQuestionDel" data-dismiss="modal">
        	<i class="icon-remove icon-white"></i>取消
        </button>
        <button type="button" class="btn btn-success" onclick="ShortQuestionHandler.deleteShortQuestion()">
        	<i class="icon-ok icon-white"></i>&nbsp;确&nbsp;&nbsp;定
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

 
<!-- 左树，右边表格 -->
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west',split:true,border:false" style="width:200px">
    	<table id="shortquestion_examtype_table" cellspacing="0" cellpadding="0">
        <thead>
			<tr>
				<th data-options="field:'typeName',width:80">试题分类</th>
			</tr>
		</thead>
   	</table>
    </div>
   
   <!-- 用户列表 -->
    <div data-options="region:'center',border:false">
    <div id="shortquestion_tab">
   	 <div  title="试题列表">
   	 	<table id="shortquestion_table" cellspacing="0" cellpadding="0" toolbar="#shortquestion_tbar_ug" style="margin-top:3px;">
	        <thead>
	            <tr>
	                <th data-options="field:'question',width:150,align:'center'">题目</th>
	                <th data-options="field:'score',width:50,align:'center'">分值</th>
	                <th data-options="field:'status',width:30, formatter: formatStatus,align:'center'">状态</th>
                <th data-options="field:'createTime',width:80, formatter:formatTime,align:'center'">创建时间</th>
	            </tr>
	        </thead>
   		</table>
   	
	   	<div id="shortquestion_tbar_ug">
	   		<div class="form-inline" >
			  <form id="shortquestion_form" style="margin-top:20px;">
			  		<!-- 组ID -->
					<input type="hidden" name="typeId" id="shortquestion_examType_id"/>
			   		<input type="text" class="form-control" name="prototypeQuestion" id="question_shortquestion" placeholder="试题关键字">
			    	<a class="btn btn-success" href="javascript:void(0)"   onclick="ShortQuestionHandler.search();">
						<i class="icon-search icon-white"></i>查询
					</a>
				 <div class="pull-right"> 
				  <div class="btn-group" data-toggle="buttons-checkbox">
			    	<a class="btn btn-success" id="importExcel_shortquestion" href="javascript:void(0)"   onclick="ShortQuestionHandler.importExcel();">
						<i class="icon-tint icon-white"></i>导入excel
					</a>
			    	<a class="btn btn-success" id="exportExcel_shortquestion" href="javascript:void(0)"   onclick="ShortQuestionHandler.exportExcel();">
						<i class="icon-fire icon-white"></i>导出模板
					</a>
			    	<a class="btn btn-success" id="save_shortquestion" href="javascript:void(0)"   onclick="ShortQuestionHandler.beforeEditShortQuestion(1);">
						<i class="icon-plus icon-white"></i>添加
					</a>
			    	<a class="btn btn-success" id="update_shortquestion" href="javascript:void(0)" onclick="ShortQuestionHandler.beforeEditShortQuestion(2);">
						<i class="icon-edit icon-white"></i>修改
					</a>
					<a class="btn btn-success" id="beforeDelete_shortquestion" href="javascript:void(0)"  onclick="ShortQuestionHandler.beforeDeleteShortQuestion();">
						<i class="icon-trash icon-white"></i>删除 
					</a>
					</div>
				 </div>
				</form>
			</div>
		</div>
	 </div>
</div>

 
<script src='${ctx}/js/questiontypes/shortquestion/shortquestion.js' type='text/javascript'></script> 
</body>
</html>