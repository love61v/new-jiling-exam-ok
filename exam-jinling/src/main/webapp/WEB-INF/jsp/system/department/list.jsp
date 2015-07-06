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

<title>部门管理</title>

</head>
<body>
	<table id="department_table" cellspacing="0" cellpadding="0">
        <thead>
			<tr>
				<th data-options="field:'deptName',width:80">名称</th>
				<th data-options="field:'areaCode',width:60">区域编码</th>
 			</tr>
		</thead>
   	</table>
   	
   	<!-- 右键菜单 -->
   	<div id="department_menu" class="easyui-menu" style="width:120px;">
		<div onclick="DepartmentHandler.beforeEditDepartment(1);"><i class="icon-plus"></i>添加</div>
		<div class="menu-sep"></div>
		<div onclick="DepartmentHandler.beforeEditDepartment(2);"><i class="icon-edit"></i>修改</div>
		<div class="menu-sep"></div>
		<div onclick="DepartmentHandler.beforeDeleteDepartment();"><i class="icon-trash"></i>删除</div>
		<div class="menu-sep"></div>
		<div onclick="collapse('department_table','deptId')"> <i class="icon-folder-close"></i>收缩</div>
		<div class="menu-sep"></div>
		<div onclick="expand('department_table', 'deptId')"><i class="icon-folder-open"></i>展开</div>
	</div>
    
    
    <!--模块编辑 -->
 <div class="modal fade" id="editDepartment">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleEditDepartment"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">编辑模块</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="DepartmentHandler.editDepartment();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


 <!-- 提示是否删除 -->
 <div class="modal fade" id="isDeleteDepartmentTip">
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
        <button type="button" class="btn btn-danger" id="cancleDelDepartment" data-dismiss="modal">
        	<i class="icon-remove icon-white"></i>取消
        </button>
        <button type="button" class="btn btn-success" onclick="DepartmentHandler.deleteDepartment()">
        	<i class="icon-ok icon-white"></i>&nbsp;确&nbsp;&nbsp;定
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script src='${ctx}/js/system/department/department.js' type='text/javascript'></script> 
</body>
</html>