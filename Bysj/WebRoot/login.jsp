<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>css/animate.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">
    <link href="<%=basePath%>css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

  </head>
  
  <body class="signin">
	<div class="signinpanel">
		<div class="col-sm-12">
			<form action="dologin" method="post" id="dologin">
				<h4 class="no-margins">登录：</h4>
				<p class="m-t-md">登录到D+后台系统</p>
				<input input="text" id="username" name="username" 
				class="form-control uname" placeholder="用户名" />
				<input type="password" id="password" name="password"
				class="form-control pword m-b" placeholder="密码" />
				<input type="hidden" name="action" value="dologin">
				<button class="btn btn-success btn-block" onclick="dologin()">登录</button>
			</form>
		</div>
	</div>
	<div class="signup-footer">
		<div class="pull-left">
			&copy; DAdmin
		</div>
	</div>
  </body>
  
   	<!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript" src="js/user/dologin.js"></script>  
</html>
