<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登陆-考试系统</title>

<link href="${ctx}/css/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/css/ace/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace/assets/css/ace.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace/assets/css/ace-rtl.min.css" />

<!--[if IE 7]>
  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
<![endif]-->
<!--[if lte IE 8]>
  <link rel="stylesheet" href="${ctx}/css/ace/assets/css/ace-ie.min.css" />
<![endif]-->

<!--[if lt IE 9]>
<script src="${ctx}/js/ace/js/html5shiv.js"></script>
<script src="${ctx}/js/ace/js/respond.min.js"></script>
<![endif]-->
</head>

<body class="login-layout">

<div class="main-container" style="margin-top:130px;">
	<div class="main-content">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<div class="login-container">
					<div class="center">
						<h1>
							<i class="icon-leaf green"></i>
<!-- 							<span class="white">吉林气象在线考试平台</span> -->
						</h1>
						<h5 class="red">&nbsp;</h5>
					</div>
	
					<div class="space-6"></div>
	
					<div class="position-relative">
						<div id="login-box" class="login-box visible widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header blue lighter bigger">
										<i class="icon-coffee green"></i>
										登陆平台
									</h4>
									<div class="space-5"></div>
									
									<form class="form-horizontal" action="${ctx}/login.html" method="post">
										<fieldset>
											<label class="block clearfix">
												<span id="errorMessage" style="margin-left:80px;color:red;">${errorMessage }</span>
												<span class="block input-icon input-icon-right" style="margin-top:5px;">
													<input type="text" name="username" id="username" class="form-control"
													 placeholder="请输入用户名" onfocus="$('#errorMessage').text('');" value="${userName }"/>
													<i class="icon-user"></i>
												</span>
											</label>
	
											<label class="block clearfix">
												<span class="block input-icon input-icon-right" style="margin-top:20px;">
													<input type="password" name="password" id="password" class="form-control" placeholder="请输入密码" onfocus="$('#errorMessage').text('');"/>
													<i class="icon-lock"></i>
												</span>
											</label>
											
											<label class="block clearfix">
												<span class="block input-prepend" style="margin-top:20px;">
													<input class="" type="text" style="width:80px;" name="captcha" id="captcha" class="form-control" value="${captcha }" placeholder="验证码" />
												 
													<img class="add-on pull-right" style="cursor:pointer;" alt="验证码" src="${ctx}/servlet/captchaCode.html" title="点击更换"   id="img_captcha" onclick="javascript:refreshCaptcha();">
	<!--                     										<a href="javascript:void(0)" class="add-on pull-right"  onclick="javascript:refreshCaptcha()">换一张</a> -->
												</span>
											</label>
	
												
											<button type="submit" class="width-35 pull-right btn btn-sm btn-primary" style="margin-top:20px;">
												<i class="icon-key"></i> 登陆
											</button>
											</div>
	
											<div class="space-4"></div>
										</fieldset>
									</form>
	
									 <div class="space-4"></div>
								</div><!-- /widget-main -->
	
								<div class="toolbar clearfix">
									<div>
										<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
											<i class="icon-arrow-left"></i> 忘记密码
										</a>
									</div>
	
									<div>
										<a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
											<i class="icon-arrow-right"></i>注册
										</a>
									</div>
								</div>
							</div><!-- /widget-body -->
						</div><!-- /login-box -->
	
						<div id="forgot-box" class="forgot-box widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header red lighter bigger">
										<i class="icon-key"></i>
										Retrieve Password
									</h4>
	
									<div class="space-6"></div>
									<p>
										Enter your email and to receive instructions
									</p>
	
									<form>
										<fieldset>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="email" class="form-control" placeholder="Email" />
													<i class="icon-envelope"></i>
												</span>
											</label>
	
											<div class="clearfix">
												<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
													<i class="icon-lightbulb"></i>
													Send Me!
												</button>
											</div>
										</fieldset>
									</form>
								</div><!-- /widget-main -->
	
								<div class="toolbar center">
									<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
										登陆
										<i class="icon-arrow-right"></i>
									</a>
								</div>
							</div><!-- /widget-body -->
						</div><!-- /forgot-box -->
	
						<div id="signup-box" class="signup-box widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header green lighter bigger">
										<i class="icon-group blue"></i>
										New User Registration
									</h4>
	
									<div class="space-6"></div>
									<p> Enter your details to begin: </p>
	
									<form>
										<fieldset>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="email" class="form-control" placeholder="Email" />
													<i class="icon-envelope"></i>
												</span>
											</label>
	
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="text" class="form-control" placeholder="Username" />
													<i class="icon-user"></i>
												</span>
											</label>
	
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="password" class="form-control" placeholder="Password" />
													<i class="icon-lock"></i>
												</span>
											</label>
	
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="password" class="form-control" placeholder="Repeat password" />
													<i class="icon-retweet"></i>
												</span>
											</label>
	
											<label class="block">
												<input type="checkbox" class="ace" />
												<span class="lbl">
													I accept the
													<a href="#">User Agreement</a>
												</span>
											</label>
	
											<div class="space-24"></div>
	
											<div class="clearfix">
												<button type="reset" class="width-30 pull-left btn btn-sm">
													<i class="icon-refresh"></i>
													Reset
												</button>
	
												<button type="button" class="width-65 pull-right btn btn-sm btn-success">
													Register
													<i class="icon-arrow-right icon-on-right"></i>
												</button>
											</div>
										</fieldset>
									</form>
								</div>
	
								<div class="toolbar center">
									<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
										<i class="icon-arrow-left"></i>
										Back to login
									</a>
								</div>
							</div><!-- /widget-body -->
						</div><!-- /signup-box -->
					</div><!-- /position-relative -->
				</div>
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div>
</div><!-- /.main-container -->
	
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/placeholder.js"></script>
<script type="text/javascript">
	window.jQuery || document.write("<script src='${ctx}/js/ace/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${ctx}/js/ace/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
 

<script type="text/javascript">
	if (window.self != window.top) {
		window.top.location = window.self.location;
	};
	 
	function show_box(id) {
		 jQuery('.widget-box.visible').removeClass('visible');
		 jQuery('#'+id).addClass('visible');
	}
	
	function refreshCaptcha(){
	   $("#img_captcha").attr("src","${ctx}/servlet/captchaCode.html?t=" + Math.random());  
	}  
		
</script>

</body>
</html>
