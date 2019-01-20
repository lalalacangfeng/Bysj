<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/admin/style.css?v=4.1.0" rel="stylesheet">

<style>
.div-l {
	float: left;
	width: 49%
}

.div-r {
	float: right;
	width: 49%
}

.div-c {
	float: left;
	width: 98%
}
</style>

</head>

<body>
	<form id="register" action="admin/user" method="post">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="div-l">
				<div id="username">
					<label class="col-sm-3 control-label">用户名：</label> 
					<input name="username" class="form-control" required="required" type="text" value=""><p>
				</div>
				<br> 
				<div id="password">
					<label class="col-sm-3 control-label">用户密码：</label>
					<input type="password" name="password" class="form-control" required="required" type="text" value=""><p>
				</div>
				<br> <input type="submit"
					name="action" class="btn btn-w-m btn-success" value="添加">

			</div>
		</div>
	</form>
</body>
</html>
