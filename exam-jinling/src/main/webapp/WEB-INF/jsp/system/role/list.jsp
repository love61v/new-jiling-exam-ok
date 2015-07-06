<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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

<title>角色管理</title>
</head>
<body>
      <table id="role_table" cellspacing="0" cellpadding="0" toolbar="#role_toolbar" style="margin-top:3px;">
        <thead>
            <tr>
                <th data-options="field:'roleName',width:50,align:'center'">名称</th>
                <th data-options="field:'engName',width:50,align:'center'">英文名</th>
                <th data-options="field:'status',width:30, formatter: formatStatus,align:'center'">状态</th>
                <th data-options="field:'createTime',width:80, formatter:formatTime,align:'center'">创建时间</th>
                <th data-options="field:'remark',width:120,align:'center'">备注</th>
                <th data-options="field:'x',width:50,formatter:formatAction,align:'center'">操作</th>
            </tr>
        </thead>
   	</table>
   	
   	<div id="role_toolbar">
   		<div class="form-inline" >
			<form id="role_form" style="margin-top:20px;">
		   		<input type="text" class="form-control" name="roleName" id="roleName" placeholder="名称">
		    	<a class="btn btn-success" href="javascript:void(0)"   onclick="RoleHandler.search();">
					<i class="icon-search icon-white"></i>查询
				</a>
				 
		   	  	 <shiro:hasAnyRoles name="system_admin,super_admin">
					<div class="pull-right"> 
					  <div class="btn-group" data-toggle="buttons-checkbox">
				    	<a class="btn btn-success" id="saveRole" href="javascript:void(0)"   onclick="RoleHandler.beforeEditRole(1);">
							<i class="icon-plus icon-white"></i>添加
						</a>
				    	<a class="btn btn-success" id="updateRole" href="javascript:void(0)" onclick="RoleHandler.beforeEditRole(2);">
							<i class="icon-edit icon-white"></i>修改
						</a>
						<a class="btn btn-success" id="beforeDeleteRole" href="javascript:void(0)"  onclick="RoleHandler.beforeDeleteRole();">
							<i class="icon-trash icon-white"></i>删除 
						</a>
						</div>
				   </div>
			    </shiro:hasAnyRoles>
			</form>
		</div>
	</div>
    
 <!-- 用户编辑 -->
 <div class="modal fade" id="editRole">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleEditRole"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">编辑用户</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body"></div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success" onclick="RoleHandler.editRole();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 分配角色资源 -->
 <div class="modal fade" id="authzRole">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="cancleauthzRole"  data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title"><span style="color:blue;">绑定用户</span></h4>
      </div>
      <!-- remote加载的页面渲染到此容器中 -->
       <div class="modal-body" id="authzBody"></div>
      
      <div class="modal-footer">
      	<span class="pull-left"><input type="checkbox" name="allChk" onchange="RoleHandler.allIsCheck(this);" />全选 / 取消</span> 
      	<span id="acceptAuthz" style="margin-right: 10px;">&nbsp;</span>
        <a class="btn btn-success" href="javascript:void(0)"   onclick="RoleHandler.reloadTree();">
			<i class="icon-refresh icon-white"></i>刷新
		</a>
        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="icon-remove icon-white"></i>取消</button>
        <button type="button" class="btn btn-success"  onclick="RoleHandler.authzRole();"><i class="icon-ok icon-white"></i>&nbsp;提&nbsp;&nbsp;交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

 <!-- 提示是否删除 -->
 <div class="modal fade" id="isDeleteRoleTip">
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
        <button type="button" class="btn btn-danger" id="cancleDel" data-dismiss="modal">
        	<i class="icon-remove icon-white"></i>取消
        </button>
        <button type="button" class="btn btn-success" onclick="RoleHandler.deleteRole()">
        	<i class="icon-ok icon-white"></i>&nbsp;确&nbsp;&nbsp;定
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="${ctx}/js/bootstrap/bootstrap.min.js"></script>
<script src='${ctx}/js/plugins/validate/jquery.validate.min.js' type='text/javascript'></script>
<script src='${ctx}/js/plugins/validate/additional-methods.js' type='text/javascript'></script>
<script src='${ctx}/js/common.js' type='text/javascript'></script>
<script src='${ctx}/js/system/role/role.js' type='text/javascript'></script>

</body>
</html>