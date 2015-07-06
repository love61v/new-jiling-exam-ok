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

<title>模块管理</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx }/js/jquery-easyui/themes/bootstrap/easyui.css">

<!--[if lt IE 9]>
    <script src='${ctx}js/html5shiv.js' type='text/javascript'></script>
<![endif]-->
<link href='${ctx}/css/bootstrap/bootstrap.css' media='all' rel='stylesheet' type='text/css' />
<link href='${ctx}/css/bootstrap/bootstrap-responsive.css' media='all' rel='stylesheet' type='text/css' />
<script type="text/javascript" src="${ctx }/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-easyui/jquery.easyui.min.js"></script>
<script src="${ctx}/js/bootstrap/bootstrap.min.js"></script>
<script src='${ctx}/js/common.js' type='text/javascript'></script>

</head>
<body>
	<table id="examtype_table" cellspacing="0" cellpadding="0">
        <thead>
			<tr>
				<th data-options="field:'typeName',width:80">名称</th>
				<th data-options="field:'createUser',width:60,align:'center'">创建人</th>
				<th data-options="field:'status',width:30, formatter: formatStatus,align:'center'">状态</th>
                <th data-options="field:'createTime',width:80, formatter:formatTime,align:'center'">创建时间</th>
				<th data-options="field:'sort',width:30,align:'center'">顺序</th> 
			</tr>
		</thead>
   	</table>
   	
   	<!-- 右键菜单 -->
   	<div id="examtype_menu" class="easyui-menu" style="width:120px;">
		<div onclick="examtypeHandler.beforeEditexamtype(1);"><i class="icon-plus"></i>添加</div>
		<div class="menu-sep"></div>
		<div onclick="examtypeHandler.beforeEditexamtype(2);"><i class="icon-edit"></i>修改</div>
		<div class="menu-sep"></div>
		<div onclick="examtypeHandler.beforeDeleteExamtype();"><i class="icon-trash"></i>删除</div>
		<div class="menu-sep"></div>
		<div onclick="collapse('examtype_table')"> <i class="icon-folder-close"></i>收缩</div>
		<div class="menu-sep"></div>
		<div onclick="expand('examtype_table')"><i class="icon-folder-open"></i>展开</div>
	</div>
    
    
    <!--模块编辑 -->
 <div class="modal fade" id="editexamtype">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleEditexamtype"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">编辑模块</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="examtypeHandler.editexamtype();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


 <!-- 提示是否删除 -->
 <div class="modal fade" id="isDeleteexamtypeTip">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">提示</span></h4>
      </div>
      <div class="modal-body">
        <p><h3 style="color:red">你确定删除吗?</h3></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" id="cancleDelexamtype" data-dismiss="modal">
        	<i class="icon-remove icon-white"></i>取消
        </button>
        <button type="button" class="btn btn-success" onclick="examtypeHandler.deleteExamtype()">
        	<i class="icon-ok icon-white"></i>&nbsp;确&nbsp;&nbsp;定
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script src='${ctx}/js/examtype/examtype.js' type='text/javascript'></script> 
</body>
</html>