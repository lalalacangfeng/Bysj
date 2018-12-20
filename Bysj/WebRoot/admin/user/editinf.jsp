<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改个人信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
	
  </head>
  
  <body>
  <center>
    <div class="wrapper wrapper-content animated fadeInRight">
    <form id="editinf" action="user" method="post">
    	<table>
    		<tbody>
    			<tr>
    				<td><label >用户名：</label></td>
    				<td><input name="username" class="form-control" required="required" type="text" value="${username }"><p></td>
    			</tr>
				<tr>
					<td><label>用户邮箱：</label></td>
					<td><input name="email" class="form-control" required="required" type="text" value="${email}"><p></td>
    			</tr>	    			
    		</tbody>
    	</table>
    	<input type="hidden" name="action" value="editinf">
    	<input class="btn btn-w-m btn-success" type="submit" value="修&nbsp; 改">   
    	<a href="user?action=show"><button type="button" class="btn btn-w-m btn-default">返&nbsp; 回</button></a>
    </form>
	</div>
    </center>
    
  </body>
</html>
