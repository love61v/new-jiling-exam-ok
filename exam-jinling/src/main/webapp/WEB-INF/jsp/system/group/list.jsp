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

<title>组管理</title>
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
	<table id="group_table" toolbar="#group_toolbar" cellspacing="0" cellpadding="0">
        <thead>
			<tr>
				<th data-options="field:'groupName',width:80">名称</th>
			</tr>
		</thead>
   	</table>
   	
   	<!-- 右键菜单 -->
   	<div id="group_menu" class="easyui-menu" style="width:120px;">
		<div onclick="GroupHandler.beforeEditGroup(1);"><i class="icon-plus"></i>添加</div>
		<div class="menu-sep"></div>
		<div onclick="GroupHandler.beforeEditGroup(2);"><i class="icon-edit"></i>修改</div>
		<div class="menu-sep"></div>
		<div onclick="GroupHandler.beforeDeleteGroup();"><i class="icon-remove"></i>删除</div>
		<div class="menu-sep"></div>
		<div onclick="collapse('group_table')"> <i class="icon-folder-close"></i>收缩</div>
		<div class="menu-sep"></div>
		<div onclick="expand('group_table')"><i class="icon-folder-open"></i>展开</div>
	</div>
	
	<div id="group_toolbar">
   		<div class="form-inline" >
		   		<input type="text" class="form-control" id="groupName" placeholder="请输入名称">
		    	<a class="btn btn-success" href="javascript:void(0)"   onclick="GroupHandler.search();">
					<i class="icon-search icon-white"></i>查询
				</a>
			 
			<span class="pull-right">
		    	<a class="btn btn-success" id="saveGroup" href="javascript:void(0)"   onclick="GroupHandler.beforeEditGroup(1);">
					<i class="icon-plus icon-white"></i>添加用户
				</a>
		    	<a class="btn btn-success" id="updateGroup" href="javascript:void(0)" onclick="GroupHandler.beforeEditGroup(2);">
					<i class="icon-edit icon-white"></i>添加角色
				</a>
				<a class="btn btn-info" id="beforeDeleteGroup" href="javascript:void(0)"  onclick="GroupHandler.beforeDeleteGroup();">
					<i class="icon-remove icon-white"></i>删除用户
				</a>
		    	<a class="btn btn-info" id="updateGroup" href="javascript:void(0)" onclick="GroupHandler.beforeEditGroup(2);">
					<i class="icon-remove icon-white"></i>删除角色
				</a>
				 
			</span>
		</div>
	</div>
    
    
    
    <!--模块编辑 -->
 <div class="modal fade" id="editGroup">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleEditGroup"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">编辑模块</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="GroupHandler.editGroup();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


 <!-- 提示是否删除 -->
 <div class="modal fade" id="isDeleteGroupTip">
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
        <button type="button" class="btn btn-danger" id="cancleDelGroup" data-dismiss="modal">
        	<i class="icon-remove icon-white"></i>取消
        </button>
        <button type="button" class="btn btn-success" onclick="GroupHandler.deleteGroup()">
        	<i class="icon-ok icon-white"></i>&nbsp;确&nbsp;&nbsp;定
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src='${ctx}/js/common.js' type='text/javascript'></script>
<script src='${ctx}/js/system/group/group.js' type='text/javascript'></script> 
</body>
</html>