<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>在线考试管理平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />

<link rel="stylesheet" type="text/css" href="${ctx }/css/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx }/js/jquery-easyui/themes/default/easyui.css">

<!--[if lt IE 9]>
    <script src='${ctx}js/html5shiv.js' type='text/javascript'></script>
<![endif]-->
<link href='${ctx}/css/bootstrap/bootstrap.css' media='all' rel='stylesheet' type='text/css' />
<link href='${ctx}/css/bootstrap/bootstrap-responsive.css' media='all' rel='stylesheet' type='text/css' />
	
<script type="text/javascript" src="${ctx }/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-easyui/jquery.easyui.min.js"></script>
<script src="${ctx}/js/bootstrap/bootstrap.min.js"></script>
<script src='${ctx}/js/common.js' type='text/javascript'></script>

<!-- zTree -->
<%-- <link rel="stylesheet" href="${ctx }/js/plugins/zTree3/css/demo.css" type="text/css">
<link rel="stylesheet" href="${ctx }/js/plugins/zTree3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/plugins/zTree3/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/js/plugins/zTree3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx }/js/plugins/zTree3/js/jquery.ztree.excheck-3.5.js"></script> --%>
</head>

<body class="easyui-layout contrast-sea-blue">
	<div data-options="region:'north',border:false"
		style="height: 60px; background: #B3DFDA; padding: 10px">
		<span style="color: blue; font-size: 20pt;"> 
<!-- 			<i class='icon-heart-empty'></i>吉林气象在线考试系统 -->
		</span>
	</div>

	<!-- 左侧菜单 -->
	<div data-options="region:'west',split:true,title:'菜单导航'"
		style="width: 250px; padding: 1px; color: blue;">
		<div class="easyui-accordion" data-options="multiple:false,fit:true"
			style="width: 240px; height: 300px;">
			<div title="用户权限管理" style="padding: 10px;">
				<ul class='nav nav-stacked'>
					<li><a href='javascript:void(0);' onclick="toMain(this);">
							<i class='icon-user'></i> <span>分组管理</span>
					</a></li>
					<li><a href='javascript:void(0);' onclick="toMain(this);">
							<i class='icon-user'></i> <span>用户管理</span>
					</a></li>
					<li>
						<a href='javascript:void(0);' onclick="toMain(this);">
							<i class='icon-cog'></i> <span>角色管理</span>
						</a>
					</li>
					<li>
						<a href='javascript:void(0);' onclick="toMain(this);">
							<i class='icon-cog'></i> <span>操作管理</span>
						</a>
					</li>
					<li>
						<a href='javascript:void(0);' onclick="toMain(this);">
							<i class='icon-cog'></i><span>模块管理</span>
						</a>
					</li>
					<li>
						<a href='javascript:void(0);' onclick="toMain(this);">
							<i class='icon-cog'></i><span>部门管理</span>
						</a>
					</li>
				</ul>
			</div>

			<div title="合同管理" style="overflow: auto; padding: 10px;">
				<ul class='nav nav-stacked'>
					<li><a href='javascript:void(0);' onclick="toMain(this);">
							<i class='icon-user'></i> <span>合同分类管理</span>
					</a></li>
					<li><a href='javascript:void(0);' onclick="toMain(this);">
							<i class='icon-cog'></i> <span>合同上传管理</span>
					</a></li>
					<li><a href='javascript:void(0);' onclick="toMain(this);">
							<i class='icon-signout'></i> <span>合同查询管理</span>
					</a></li>
				</ul>
			</div>
			<div title="档案管理" style="padding: 10px;">
				<ul class='nav nav-stacked'>
					<li><a href='user_profile.html'> <i class='icon-user'></i>
							我的资料
					</a></li>
					<li><a href='user_profile.html'> <i class='icon-cog'></i>
							修改密码
					</a></li>
					<li><a href='${ctx }/logout.html'> <i class='icon-signout'></i>
							退出
					</a></li>
				</ul>
			</div>
			<div title="行文管理" style="padding: 10px;">
				<div style="padding: 5px 0;">
					<p>
						<a href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">合同分类管理</a>
					<p>
					<p>
						<a href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-large-clipart',size:'large',iconAlign:'top'">合同上传管理</a>
					<p>
					<p>
						<a href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-large-shapes',size:'large',iconAlign:'top'">Shapes</a>
					<p>
					<p>
						<a href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-large-smartart',size:'large',iconAlign:'top'">SmartArt</a>
					<p>
					<p>
						<a href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">Chart</a>
					<p>
				</div>
			</div>
			<div title="查询管理" style="padding: 10px;">
				<p>A dynamic, reflective, general-purpose object-oriented
					programming language.</p>
			</div>

			<div title="系统维护" style="padding: 10px;">
				<p>Fortran (previously FORTRAN) is a general-purpose, imperative
					programming language that is especially suited to numeric
					computation and scientific computing.</p>
			</div>
		</div>
	</div>

	<!-- 中间主窗体内容 -->
	<div data-options="region:'center',border:false">
		<div id="mainContent_tab" class="easyui-tabs"
			data-options="fit:true,border:false,pill:false"></div>
	</div>
	

	<script type="text/javascript">
		/*根据模块导航到主窗体*/
		function toMain(obj) {
			var module_name = $("span", obj).text(); //当前模块
			
			var exitstTab = $('#mainContent_tab').tabs('getTab', module_name);
			if (exitstTab) {//已打开标签
				$('#mainContent_tab').tabs('select', module_name);
				return false;
			}
			var	url = null;
			 
			if(module_name == "角色管理"){
				url = "${ctx}/role/list.html";
			}else if(module_name == "操作管理"){
				url = "${ctx}/operate/list.html";
			}
			else if(module_name == "分组管理"){
				url = "${ctx}/group/list.html";
			}
			else if(module_name == "模块管理"){
				url = "${ctx}/module/list.html";
			}else if(module_name == "部门管理"){
				url = "${ctx}/department/list.html" + "?_time=" + new Date().getTime();
			}
			else{
			 	url = "${ctx}/user/list.html";
			}
			
			
			$('#mainContent_tab').tabs('add', {
				title : module_name,
				href : url,
				closable : true
			});

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