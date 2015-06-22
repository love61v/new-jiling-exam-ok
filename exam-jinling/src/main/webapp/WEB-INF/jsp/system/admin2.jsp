<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html PUBLIC>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>后台管理</title>

<link rel="stylesheet" href="${ctx}/css/ace/assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${ctx}/css/ace/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace/assets/css/ace.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace/assets/css/ace-skins.min.css" />

<!--[if IE 7]>
  <link rel="stylesheet" href="${ctx}/css/ace/assets/css/font-awesome-ie7.min.css" />
<![endif]-->
<!--[if lte IE 8]>
  <link rel="stylesheet" href="${ctx}/css/ace/assets/css/ace-ie.min.css" />
<![endif]-->

<script src="${ctx}/js/ace/js/ace.min.js"></script>

<!--[if lt IE 9]>
<script src="${ctx}/js/ace/js/html5shiv.js"></script>
<script src="${ctx}/js/ace/js/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try{ace.settings.check('navbar' , 'fixed')}catch(e){}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
					<small>
						<i class="icon-leaf"></i>
						吉林气象在线考试平台
					</small>
				</a><!-- /.brand -->
			</div><!-- /.navbar-header -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="grey">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
							<i class="icon-tasks"></i>
							<span class="badge badge-grey">4</span>
						</a>

						<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header">
								<i class="icon-ok"></i>
								还有4个任务完成
							</li>

							<li>
								<a href="#">
									<div class="clearfix">
										<span class="pull-left">软件更新</span>
										<span class="pull-right">65%</span>
									</div>

									<div class="progress progress-mini ">
										<div style="width:65%" class="progress-bar "></div>
									</div>
								</a>
							</li>

							<li>
								<a href="#">
									<div class="clearfix">
										<span class="pull-left">硬件更新</span>
										<span class="pull-right">35%</span>
									</div>

									<div class="progress progress-mini ">
										<div style="width:35%" class="progress-bar progress-bar-danger"></div>
									</div>
								</a>
							</li>

							<li>
								<a href="#">
									<div class="clearfix">
										<span class="pull-left">单元测试</span>
										<span class="pull-right">15%</span>
									</div>

									<div class="progress progress-mini ">
										<div style="width:15%" class="progress-bar progress-bar-warning"></div>
									</div>
								</a>
							</li>

							<li>
								<a href="#">
									<div class="clearfix">
										<span class="pull-left">错误修复</span>
										<span class="pull-right">90%</span>
									</div>

									<div class="progress progress-mini progress-striped active">
										<div style="width:90%" class="progress-bar progress-bar-success"></div>
									</div>
								</a>
							</li>

							<li>
								<a href="#">
									查看任务详情
									<i class="icon-arrow-right"></i>
								</a>
							</li>
						</ul>
					</li>

					<li class="purple">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
							<i class="icon-bell-alt icon-animated-bell"></i>
							<span class="badge badge-important">8</span>
						</a>

						<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header">
								<i class="icon-warning-sign"></i>
								8条通知
							</li>

							<li>
								<a href="#">
									<div class="clearfix">
										<span class="pull-left">
											<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
											新闻评论
										</span>
										<span class="pull-right badge badge-info">+12</span>
									</div>
								</a>
							</li>

							<li>
								<a href="#">
									<i class="btn btn-xs btn-primary icon-user"></i>
									切换为编辑登录..
								</a>
							</li>

							<li>
								<a href="#">
									<div class="clearfix">
										<span class="pull-left">
											<i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
											新订单
										</span>
										<span class="pull-right badge badge-success">+8</span>
									</div>
								</a>
							</li>

							<li>
								<a href="#">
									<div class="clearfix">
										<span class="pull-left">
											<i class="btn btn-xs no-hover btn-info icon-twitter"></i>
											粉丝
										</span>
										<span class="pull-right badge badge-info">+11</span>
									</div>
								</a>
							</li>

							<li>
								<a href="#">
									查看所有通知
									<i class="icon-arrow-right"></i>
								</a>
							</li>
						</ul>
					</li>
						 
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle">
							<span class="user-info">
								<small>欢迎:</small>
								<shiro:principal property="userName"/>
							</span>

							<i class="icon-caret-down"></i>
						</a>

						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li>
								<a href="#">
									<i class="icon-user"></i>
									个人资料
								</a>
							</li>

							<li class="divider"></li>

							<li>
								<a href="${ctx }/logout.html">
									<i class="icon-off"></i>
									退出
								</a>
							</li>
						</ul>
					</li>
				</ul><!-- /.ace-nav -->
			</div><!-- /.navbar-header -->
		</div><!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="icon-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="icon-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="icon-group"></i>
						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>
				</div><!-- #sidebar-shortcuts -->

				<ul class="nav nav-list">
					<li class="active">
						<a href="javascript:void(0);">
							<i class="icon-dashboard"></i>
							<span class="menu-text"> 控制台 </span>
						</a>
					</li>

					<li>
						<a href="javascript:void(0);" onclick="toMain(this);">
							<i class="icon-text-width"></i>
							<span class="menu-text"> 在线考试 </span>
						</a>
					</li>

					<li>
						<a href="javascript:void(0);" onclick="toMain(this);" class="dropdown-toggle">
							<i class="icon-desktop"></i>
							<span class="menu-text"> 考生管理 </span>

							<b class="arrow icon-angle-down"></b>
						</a>

						<ul class="submenu">
							<li>
								<a href="javascript:void(0);" onclick="toMain(this);">
									<i class="icon-double-angle-right"></i>
									添加考生
								</a>
							</li>

							<li>
								<a href="javascript:void(0);" onclick="toMain(this);">
									<i class="icon-double-angle-right"></i>
									删除考生
								</a>
							</li>

							<li>
								<a href="javascript:void(0);" onclick="toMain(this);">
									<i class="icon-double-angle-right"></i>
									猫猫管理
								</a>
							</li>

							<li>
								<a href="javascript:void(0);" onclick="toMain(this);" class="dropdown-toggle">
									<i class="icon-double-angle-right"></i>

									成绩管理
									<b class="arrow icon-angle-down"></b>
								</a>

								<ul class="submenu">
									<li>
										<a href="javascript:void(0);" onclick="toMain(this);">
											<i class="icon-leaf"></i>
											第一级
										</a>
									</li>

									<li>
										<a href="javascript:void(0);" onclick="toMain(this);" class="dropdown-toggle">
											<i class="icon-pencil"></i>

											第四级
											<b class="arrow icon-angle-down"></b>
										</a>

										<ul class="submenu">
											<li>
												<a href="#">
													<i class="icon-plus"></i>
													添加产品
												</a>
											</li>

											<li>
												<a href="#">
													<i class="icon-eye-open"></i>
													查看商品
												</a>
											</li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>

					<li>
						<a href="javascript:void(0);" onclick="toMain(this);" class="dropdown-toggle">
							<i class="icon-list"></i>
							<span class="menu-text"> 题库管理 </span>

							<b class="arrow icon-angle-down"></b>
						</a>

						<ul class="submenu">
							<li>
								<a href="javascript:void(0);" onclick="toMain(this);">
									<i class="icon-double-angle-right"></i>
									添加试题
								</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="toMain(this);">
									<i class="icon-double-angle-right"></i>
									在线出题
								</a>
							</li>
							<li>
								<a href="jqgrid.html">
									<i class="icon-double-angle-right"></i>
									删除试题
								</a>
							</li>
						</ul>
					</li>

					<li>
						<a href="#" class="dropdown-toggle">
							<i class="icon-edit"></i>
							<span class="menu-text"> 我的功能 </span>

							<b class="arrow icon-angle-down"></b>
						</a>

						<ul class="submenu">
							<li>
								<a href="form-elements.html">
									<i class="icon-double-angle-right"></i>
									我的资料
								</a>
							</li>

							<li>
								<a href="form-wizard.html">
									<i class="icon-double-angle-right"></i>
									我的收藏
								</a>
							</li>

							<li>
								<a href="wysiwyg.html">
									<i class="icon-double-angle-right"></i>
									我的纠错
								</a>
							</li>

							<li>
								<a href="dropzone.html">
									<i class="icon-double-angle-right"></i>
									修改密码
								</a>
							</li>
						</ul>
					</li>

					<li>
						<a href="calendar.html">
							<i class="icon-calendar"></i>

							<span class="menu-text">
								参加模考
								<span class="badge badge-transparent tooltip-error" title="2&nbsp;Important&nbsp;Events">
									<i class="icon-warning-sign red bigger-130"></i>
								</span>
							</span>
						</a>
					</li>

					<li>
						<a href="#" class="dropdown-toggle">
							<i class="icon-tag"></i>
							<span class="menu-text"> 系统管理 </span>

							<b class="arrow icon-angle-down"></b>
						</a>

						<ul class="submenu">
							<li>
								<a href="profile.html">
									<i class="icon-double-angle-right"></i>
									用户信息
								</a>
							</li>

							<li>
								<a href="inbox.html">
									<i class="icon-double-angle-right"></i>
									收件箱
								</a>
							</li>

							<li>
								<a href="pricing.html">
									<i class="icon-double-angle-right"></i>
									日志管理
								</a>
							</li>

							<li>
								<a href="invoice.html">
									<i class="icon-double-angle-right"></i>
									字典表管理
								</a>
							</li>

							<li>
								<a href="timeline.html">
									<i class="icon-double-angle-right"></i>
									权限管理
								</a>
							</li>
						</ul>
					</li>

					<li>
						<a href="#" class="dropdown-toggle">
							<i class="icon-file-alt"></i>

							<span class="menu-text">
								其他页面
								<span class="badge badge-primary ">5</span>
							</span>

							<b class="arrow icon-angle-down"></b>
						</a>

						<ul class="submenu">
							<li>
								<a href="faq.html">
									<i class="icon-double-angle-right"></i>
									帮助
								</a>
							</li>

							<li>
								<a href="error-404.html">
									<i class="icon-double-angle-right"></i>
									404错误页面
								</a>
							</li>

							<li>
								<a href="error-500.html">
									<i class="icon-double-angle-right"></i>
									500错误页面
								</a>
							</li>

							<li>
								<a href="grid.html">
									<i class="icon-double-angle-right"></i>
									网格
								</a>
							</li>

							<li>
								<a href="blank.html">
									<i class="icon-double-angle-right"></i>
									空白页面
								</a>
							</li>
						</ul>
					</li>
				</ul><!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li>
							<i class="icon-home home-icon"></i>
							<a href="javascript:void(0);">首页</a>
						</li>
						<li class="active">控制台</li>
					</ul><!-- .breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div><!-- #nav-search -->
				</div>

				<div class="page-content">
					<!-- <div class="page-header">
						<h1>
							控制台
							<small>
								<i class="icon-double-angle-right"></i>
							</small>
						</h1>
					</div> --><!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<!-- iframe主页面 -->
							<div>
							 <iframe class="span12"  id="iframe" name="iframe" src="" width="100%" height="100%" 
								style="overflow-x:hidden;" frameborder="0"></iframe>
							</div>

							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

			 
		<!-- 回到顶部  
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="icon-double-angle-up icon-only bigger-110"></i>
		</a> -->
	</div><!-- /.main-container -->

<script src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
<script src="${ctx}/js/ace/js/bootstrap.min.js"></script>
<script src="${ctx}/js/ace/js/typeahead-bs2.min.js"></script>
<!--[if lte IE 8]>
  <script src="${ctx}/js/ace/js/excanvas.min.js"></script>
<![endif]-->

<script src="${ctx}/js/ace/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${ctx}/js/ace/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/ace/js/jquery.slimscroll.min.js"></script>

<!-- ace scripts -->
<script src="${ctx}/js/ace/js/ace-elements.min.js"></script>
<script src="${ctx}/js/ace/js/ace.min.js"></script>
<script>

$(function(){
	$("#iframe").load(function(){
		var mainheight = $(this).contents().find("body").height()+30;
		$(this).height(mainheight);
	});
	
});

/*根据模块导航到主窗体*/
function toMain(obj){
	var txt = $.trim($("span",obj).text()); //当前模块
	if(!txt){
		txt = $(obj).text();
	}
	$(".active").text($.trim(txt));
	
	$("iframe").attr("src","${ctx}/hello/list.html");
}

/*弹出修改密码窗口*/
function updatePassword(){
	$("#passwordModal").modal({
		show: true
	});
}
</script>
</body>
</html>

