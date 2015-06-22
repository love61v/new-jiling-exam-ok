<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>在线考试管理平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />

<link rel="stylesheet" type="text/css" href="${ctx }/css/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx }/js/jquery-easyui/themes/default/easyui.css">
<script type="text/javascript" src="${ctx }/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-easyui/jquery.easyui.min.js"></script>

<!--[if lt IE 9]>
    <script src='${ctx}js/html5shiv.js' type='text/javascript'></script>
<![endif]-->
<link href='${ctx}/css/bootstrap/bootstrap.css' media='all' rel='stylesheet' type='text/css' />
<link href='${ctx}/css/bootstrap/bootstrap-responsive.css' media='all' rel='stylesheet' type='text/css' />

</head>

<body class="easyui-layout contrast-sea-blue">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
		<span style="color:blue;font-size: 20pt;">
		 	<i class='icon-heart-empty'></i>吉林气象在线考试系统ddd<button onclick="updatePassword();">修改密码</button>
		 </span>
		 
		 <span>
		 	<button onclick="updatePassword();">修改密码</button>
		 </span>
		 <span>
		 
		 	 <!-- 弹出修改密码窗体 begin -->
	    <form class="form-horizontal" role="form" id="pwdForm">
			<div class="modal hide fade" id="passwordModal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3><i class="icon-edit"></i>修改密码</h3>
				</div>
				<div class="modal-body">
					   <div class="form-group" style="margin-top: 20px;">
					      <label for="firstname" class="col-sm-4 control-label">原密码:</label>
					      <div class="col-sm-10 controls">
					         <input type="password" class="input-large span4"  name="oldpwd"  id="oldpwd" 
					            placeholder="请输入原密码">
					      </div>
					   </div>
					   
					   <div class="form-group" style="margin-top: 30px;">
					      <label for="lastname" class="col-sm-2 control-label">新密码:</label>
					      <div class="col-sm-10 controls">
					         <input type="password" class="input-large span4" name="newpwd" id="newpwd" 
					            placeholder="请输入新密码">
					      </div>
					   </div>
					   
					   <div class="form-group" style="margin-top: 30px;margin-bottom: 20px;">
					      <label for="lastname" class="col-sm-2 control-label">确认新密码:</label>
					      <div class="col-sm-10 controls">
					         <input type="password" class="input-large span4" id="surenewpwd" 
					            placeholder="请输入确认新密码">
					      </div>
					   </div>
				 </div>
				
				<div class="modal-footer">
					<a href="#" class="btn" data-dismiss="modal">取消</a>
					<button onclick="acceptPwdForm();" class="btn btn-primary">提交</button>
				</div>
				
			</div>
		</form>
		<!-- 弹出修改密码窗体 begin end -->
		 </span>
	</div>
	
	<!-- 左侧菜单 -->
	<div data-options="region:'west',split:true,title:'菜单导航'" style="width:250px;padding:1px;color:blue;">
		<div class="easyui-accordion" data-options="multiple:false,fit:true" style="width:240px;height:300px;">
		<div title="用户权限管理" style="padding:10px;">
			<ul class='nav nav-stacked'>
				<li><a href='javascript:void(0);' onclick="toMain(this);"> <i class='icon-user'></i>
						用户管理
				</a></li>
				<li><a href='javascript:void(0);' onclick="toMain(this);"> <i class='icon-cog'></i>
						角色管理
				</a></li>
				<li><a href='javascript:void(0);' onclick="toMain(this);" > <i class='icon-signout'></i>
						操作管理
				</a></li>
			</ul>
		</div>
		
		<div title="合同管理" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
			<ul class='nav nav-stacked'>
				<li><a href='javascript:void(0);' onclick="toMain(this);"> <i class='icon-user'></i>
						<span>合同分类管理</span>
				</a></li>
				<li><a href='javascript:void(0);' onclick="toMain(this);"> <i class='icon-cog'></i>
						<span>合同上传管理</span>
				</a></li>
				<li><a href='javascript:void(0);' onclick="toMain(this);" > <i class='icon-signout'></i>
						<span>合同查询管理</span>
				</a></li>
			</ul>
		</div>
		<div title="档案管理" style="padding:10px;">
			<ul class='nav nav-stacked'>
				<li><a href='user_profile.html'> <i class='icon-user'></i>
						我的资料
				</a></li>
				<li><a href='user_profile.html'> <i class='icon-cog'></i>
						修改密码
				</a></li>
				<li><a href='${ctx }/logout.html' > <i class='icon-signout'></i>
						退出
				</a></li>
			</ul>
		</div>
		<div title="行文管理" style="padding:10px;">
			<div style="padding:5px 0;">
				<p><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">合同分类管理</a><p>
				<p><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-large-clipart',size:'large',iconAlign:'top'">合同上传管理</a><p>
				<p><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-large-shapes',size:'large',iconAlign:'top'">Shapes</a><p>
				<p><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-large-smartart',size:'large',iconAlign:'top'">SmartArt</a><p>
				<p><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">Chart</a><p>
			</div>
		</div>
		<div title="查询管理" style="padding:10px;">
			<p>A dynamic, reflective, general-purpose object-oriented programming language.</p>
		</div>
		
		<div title="系统维护" style="padding:10px;">
			<p>Fortran (previously FORTRAN) is a general-purpose, imperative programming language that is especially suited to numeric computation and scientific computing.</p>
		</div>
	</div>	 
	</div>
	
	<!-- 中间主窗体内容 -->
	<div data-options="region:'center',title:''" style="margin: 0 5px 0 2px;">
		<iframe id="iframe" name="iframe" src="" width="99%" height="98%" style="overflow-y:hidden; "  scrolling="no" frameborder="0"></iframe>
	</div>
	
	<script type="text/javascript">
	
	/*根据模块导航到主窗体*/
	function toMain(obj){
		var txt = $("span",obj).text(); //当前模块
		$("iframe").attr("src","${ctx}/user/list.html");
	};
	
	/*弹出修改密码窗口*/
	function updatePassword(){
		$("#passwordModal").modal({
			show: true
		});
	}
</script>
</body>
</html>