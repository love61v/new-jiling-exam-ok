<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理员</title>

<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>

<link id="bootstrap-style" href="${ctx}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/css/bootstrap/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="${ctx}/css/bootstrap/style.css" rel="stylesheet">
<link id="base-style-responsive" href="${ctx}/css/bootstrap/style-responsive.css" rel="stylesheet">

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="${ctx}/css/bootstrap/ie.css" rel="stylesheet">
	<![endif]-->

	<!--[if IE 9]>
		<link id="ie9style" href="${ctx}/css/bootstrap/ie9.css" rel="stylesheet">
	<![endif]-->

<!-- start: Favicon -->
<link rel="shortcut icon" href="${ctx}/images/favicon.ico">
<!-- end: Favicon -->

</head>

<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="javascript:void(0)"><span>吉林气象在线考试系统</span></a>
								
				<!-- start: Header Menu -->
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
						 
						<!-- start: Notifications Dropdown -->
						<li class="dropdown hidden-phone">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="halflings-icon white tasks"></i>
							</a>
							<ul class="dropdown-menu tasks">
								<li class="dropdown-menu-title">
 									<span>You have 17 tasks in progress</span>
									<a href="#refresh"><i class="icon-repeat"></i></a>
								</li>
								<li>
                                    <a href="#">
										<span class="header">
											<span class="title">iOS Development</span>
											<span class="percent"></span>
										</span>
                                        <div class="taskProgress progressSlim red">80</div> 
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="header">
											<span class="title">Android Development</span>
											<span class="percent"></span>
										</span>
                                        <div class="taskProgress progressSlim green">47</div> 
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="header">
											<span class="title">ARM Development</span>
											<span class="percent"></span>
										</span>
                                        <div class="taskProgress progressSlim yellow">32</div> 
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										<span class="header">
											<span class="title">ARM Development</span>
											<span class="percent"></span>
										</span>
                                        <div class="taskProgress progressSlim greenLight">63</div> 
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="header">
											<span class="title">ARM Development</span>
											<span class="percent"></span>
										</span>
                                        <div class="taskProgress progressSlim orange">80</div> 
                                    </a>
                                </li>
								<li>
                            		<a class="dropdown-menu-sub-footer">View all tasks</a>
								</li>	
							</ul>
						</li>
						<!-- end: Notifications Dropdown -->
						<!-- start: Message Dropdown -->
						<li class="dropdown hidden-phone">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="halflings-icon white envelope"></i>
							</a>
							<ul class="dropdown-menu messages">
								<li class="dropdown-menu-title">
 									<span>所有消息</span>
									<a href="#refresh"><i class="icon-repeat"></i></a>
								</li>	
                            	<li>
                                    <a href="#">
										<span class="avatar"><img src="img/avatar.jpg" alt="Avatar"></span>
										<span class="header">
											<span class="from">
										    	Dennis Ji
										     </span>
											<span class="time">
										    	6 min
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="avatar"><img src="img/avatar.jpg" alt="Avatar"></span>
										<span class="header">
											<span class="from">
										    	Dennis Ji
										     </span>
											<span class="time">
										    	56 min
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="avatar"><img src="img/avatar.jpg" alt="Avatar"></span>
										<span class="header">
											<span class="from">
										    	Dennis Ji
										     </span>
											<span class="time">
										    	3 hours
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
								<li>
                                    <a href="#">
										<span class="avatar"><img src="img/avatar.jpg" alt="Avatar"></span>
										<span class="header">
											<span class="from">
										    	Dennis Ji
										     </span>
											<span class="time">
										    	yesterday
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="avatar"><img src="img/avatar.jpg" alt="Avatar"></span>
										<span class="header">
											<span class="from">
										    	Dennis Ji
										     </span>
											<span class="time">
										    	Jul 25, 2012
										    </span>
										</span>
                                        <span class="message">
                                            Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                        </span>  
                                    </a>
                                </li>
								<li>
                            		<a class="dropdown-menu-sub-footer">消息</a>
								</li>	
							</ul>
						</li>
						 
						<!-- 我的账户下拉  begin-->
						<li class="dropdown">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="halflings-icon white user"></i> <shiro:principal property="userName"></shiro:principal>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title">
 									<span>我的账户</span>
								</li>
								<li><a href="javascript:void(0);" onclick="updatePassword();"><i class="halflings-icon user"></i> 修改密码</a></li>
								<li><a href="${ctx }/logout.html"><i class="halflings-icon off"></i> 退出</a></li>
							</ul>
						</li>
						<!-- 我的账户下拉  end-->
					</ul>
				</div>
				<!-- end: Header Menu -->
				
			</div>
		</div>
	</div>
	<!-- start: Header -->
	
		<div class="container-fluid-full">
		<div class="row-fluid">
				
			<!-- 左侧菜单 begin -->
			<div id="sidebar-left" class="span2">
				<div class="nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li>
							<shiro:hasPermission name="user:query">
								<a href="javascript:void(0);" onclick="toMain(this);">
									<i class="icon-bar-chart"></i>
										<span class="hidden-tablet"> 查询用户</span>
								</a>
							</shiro:hasPermission>
							<shiro:lacksPermission name="user:query">
								<a href="javascript:void(0);" onclick="alert('你没有权限操作!');">
								<i class="icon-bar-chart"></i>
									<span class="hidden-tablet"> 修改用户</span>
								</a>
							</shiro:lacksPermission>
						</li>	
						
						<li><a href="javascript:void(0);" onclick="toMain(this);"><i class="icon-envelope"></i><span class="hidden-tablet"> 考生管理</span></a></li>
						<li><a href="javascript:void(0);" onclick="toMain(this);"><i class="icon-tasks"></i><span class="hidden-tablet"> 题库管理</span></a></li>
						<li><a href="javascript:void(0);" onclick="toMain(this);"><i class="icon-eye-open"></i><span class="hidden-tablet"> 成绩管理</span></a></li>
						<li><a href="javascript:void(0);" onclick="toMain(this);"><i class="icon-dashboard"></i><span class="hidden-tablet"> 在线出题</span></a></li>
						
						<li><a href="javascript:void(0);" onclick="toMain(this);"><i class="icon-edit"></i><span class="hidden-tablet"> 参加模考</span></a></li>
						<li><a href="javascript:void(0);" onclick="toMain(this);"><i class="icon-list-alt"></i><span class="hidden-tablet"> 答题记录</span></a></li>
						<li><a href="javascript:void(0);" onclick="toMain(this);"><i class="icon-font"></i><span class="hidden-tablet"> 我的收藏</span></a></li>
						
						<li>
							<a class="dropmenu" href="#">
							<i class="icon-folder-close-alt"></i>
							<span class="hidden-tablet"> 我的资料信息</span>
							<i class="halflings-icon chevron-down"></i>
							</a>
							<ul>
								<li><a class="submenu" href="javascript:void(0);" onclick="toMain(this);"><i class="icon-file-alt"></i><span class="hidden-tablet"> 修改我的资料信息</span></a></li>
								<li><a class="submenu" href="javascript:void(0);" onclick="toMain(this);"><i class="icon-file-alt"></i><span class="hidden-tablet"> 修改密码</span></a></li>
							</ul>	
						</li>
						
						<li>
							<a class="dropmenu" href="#">
								<i class="icon-folder-close-alt"></i>
								<span class="hidden-tablet"> 系统管理</span>
								<i class="halflings-icon chevron-down"></i>
							</a>
							<ul>
								<li><a class="submenu" href="javascript:void(0);" onclick="toMain(this);"><i class="icon-file-alt"></i><span class="hidden-tablet"> 日志管理</span></a></li>
								<li><a class="submenu" href="javascript:void(0);" onclick="toMain(this);"><i class="icon-file-alt"></i><span class="hidden-tablet"> 字典表管理</span></a></li>
								<li><a class="submenu" href="javascript:void(0);" onclick="toMain(this);"><i class="icon-file-alt"></i><span class="hidden-tablet"> 猫猫管理</span></a></li>
							</ul>	
						</li>
					</ul>
				</div>
			</div>
			<!-- 左侧菜单 end -->
			
			<!-- 中间主窗体 begin -->
			<div id="content" class="span10">
				<!-- 模块提示栏  begin -->
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>
						<span id="cur_module">&nbsp;</span> 
						<!--  <i class="icon-angle-right"></i> -->
					</li>
				</ul>
				<!-- 模块提示栏  end -->
						
				<!-- iframe -->
				<div class="row-fluid" style="margin: -10px -15px -20px -15px;">
					<iframe class="span12"  id="iframe" name="iframe" src="" width="100%" height="100%" 
					style="overflow-x:hidden;" frameborder="0"></iframe>
				</div>
	       </div> 
		<!-- 中间主窗体 end -->
			 
		</div><!--/#content.span10-->
		</div><!--/fluid-row-->
		
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
	
</body>
</html>

<!-- start: JavaScript-->
	<script src="${ctx}/js/bootstrap/jquery-1.9.1.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery-migrate-1.0.0.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery-ui-1.10.0.custom.min.js"></script>
	<script src="${ctx}/js/bootstrap/bootstrap.min.js"></script>
	<script src='${ctx}/js/bootstrap/fullcalendar.min.js'></script>
	<script src='${ctx}/js/bootstrap/jquery.dataTables.min.js'></script>
	<script src="${ctx}/js/bootstrap/excanvas.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.flot.resize.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.chosen.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.uniform.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.cleditor.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.elfinder.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.raty.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.uploadify-3.1.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.gritter.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.masonry.min.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.knob.modified.js"></script>
	<script src="${ctx}/js/bootstrap/jquery.sparkline.min.js"></script>
	<script src="${ctx}/js/bootstrap/counter.js"></script>
	<script src="${ctx}/js/bootstrap/retina.js"></script>
	<script src="${ctx}/js/bootstrap/custom.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/placeholder.js"></script>
	<!-- end: JavaScript-->
	
	<script>
		$(function(){
			$(window).resize(function(){
				$("iframe").width($("iframe").width() + 30);
				$("iframe").height(document.documentElement.clientHeight + 100);
			});
		});
		
		/*根据模块导航到主窗体*/
		function toMain(obj){
			var txt = $("span",obj).text(); //当前模块
			$("#cur_module").text(txt);
			
			$("iframe").attr("src","${ctx}/hello/list.html");
		}
		
		/*弹出修改密码窗口*/
		function updatePassword(){
			$("#passwordModal").modal({
				show: true
			});
		}
		
		/*提交修改密码表单*/;
		function acceptPwdForm(){
			debugger;
			var url = "${ctx}/user/updatePwd.json";
		 
			$.post(url,{
				oldpwd: $.trim($("oldpwd").val()),
				newpwd: $.trim($("newpwd").val()),
			},function(data){
				alert(data.code);
			});
		}
	</script>
 