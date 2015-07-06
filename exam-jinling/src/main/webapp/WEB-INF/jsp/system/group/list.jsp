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

</head>
<body>

<!--分组编辑 -->
 <div class="modal fade" id="editGroup">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleEditGroup"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">编辑分组</span></h4>
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

<!--绑定用户 -->
 <div class="modal fade" id="bindUserModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleEditGroup"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">绑定用户</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="GroupHandler.bindUser();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--绑定角色 -->
 <div class="modal fade" id="bindRoleModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleEditGroup"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">绑定角色</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="GroupHandler.bindRole();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
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

<!-- 提示是否解除 -->
 <div class="modal fade" id="removeFromGroupTip">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">提示</span></h4>
      </div>
      <div class="modal-body">
        <p><h3 style="color:red">你确定解除用户吗?</h3></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" id="cancleDelGroup" data-dismiss="modal">
        	<i class="icon-remove icon-white"></i>取消
        </button>
        <button type="button" class="btn btn-success" onclick="GroupHandler.removeFromGroup()">
        	<i class="icon-ok icon-white"></i>&nbsp;确&nbsp;&nbsp;定
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 左树，右边表格 -->
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west',split:true,border:false" style="width:200px">
    	<table id="group_table" cellspacing="0" cellpadding="0">
        <thead>
			<tr>
				<th data-options="field:'groupName',width:80">权限组 (右键编辑)</th>
			</tr>
		</thead>
   	</table>
   	
   	<!-- 右键菜单 -->
   	<div id="group_menu" class="easyui-menu" style="width:120px;">
		<div onclick="GroupHandler.beforeEditGroup(1);"><i class="icon-plus"></i>&nbsp;&nbsp;添加</div>
		<div class="menu-sep"></div>
		<div onclick="GroupHandler.beforeEditGroup(2);" class="menuGrouphide"><i class="icon-edit"></i>&nbsp;&nbsp;修改</div>
		<div class="menu-sep"></div>
		<div onclick="GroupHandler.beforeDeleteGroup();" class="menuGrouphide pGroupHide"><i class="icon-trash"></i>&nbsp;&nbsp;删除</div>
		<div class="menu-sep"></div>
		<div onclick="GroupHandler.beforeUserBind();" class="menuGrouphide pGroupHide"><i class="icon-fire"></i>&nbsp;&nbsp;绑定用户</div>
		<div class="menu-sep"></div>
		<div onclick="GroupHandler.beforeRoleBind();" class="menuGrouphide pGroupHide"><i class="icon-leaf"></i>&nbsp;&nbsp;授权角色</div>
		<div class="menu-sep"></div>
	</div>
   
    </div>
   
   <!-- 用户列表 -->
    <div data-options="region:'center',border:false">
    <div id="group_tab">
   	 <div  title="用户列表">
   	 	<table id="userGroup_table" cellspacing="0" cellpadding="0" toolbar="#tbar_ug" style="margin-top:3px;">
	        <thead>
	            <tr>
	                <th data-options="field:'deptName',width:50,align:'center'">部门</th>
	                <th data-options="field:'loginName',width:50,align:'center'">账号</th>
	                <th data-options="field:'userName',width:50,align:'center'">用户名</th>
	                <th data-options="field:'phoneNumber',width:50,align:'center'">电话</th>
	            </tr>
	        </thead>
   		</table>
   	
	   	<div id="tbar_ug">
	   		<div class="form-inline" >
			  <form id="usergroup_form" style="margin-top:20px;">
			  		<!-- 组ID -->
					<input type="hidden" name="userGroupId" id="user_groupId"/>
			   		<input type="text" class="form-control" name="findInGroup" id="findInGroup" placeholder="账号   / 用户名称">
			    	<a class="btn btn-success" href="javascript:void(0)"   onclick="GroupHandler.searchUserGroup();">
						<i class="icon-search icon-white"></i>查询
					</a>
				 
					<span class="pull-right">
				    	<a class="btn btn-success" id="removeFromGroup" href="javascript:void(0)"   onclick="GroupHandler.removeFromGroup();">
							<i class="icon-fire icon-white"></i>解除组用户
						</a>
					</span>
				</form>
			</div>
		</div>
	 </div>
	
	 <div  title="角色列表">
	 	 <table id="groupRole_table" cellspacing="0" cellpadding="0">
	        <thead>
	            <tr>
	                <th data-options="field:'roleName',width:50,align:'center'">名称</th>
	                <th data-options="field:'engName',width:50,align:'center'">英文名</th>
	                <th data-options="field:'remark',width:50,align:'center'">备注</th>
	                <th data-options="field:'id',width:50,formatter:groutRoleFmt,align:'center'">操作</th>
	            </tr>
	        </thead>
   		</table>
	 </div>
	 
</div>

<script src='${ctx}/js/system/group/group.js' type='text/javascript'></script> 
</body>
</html>