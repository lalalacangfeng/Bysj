<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editpasswd.jsp' starting page</title>
    
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
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

  </head>
  
  <body>
<center>
    <div class="wrapper wrapper-content animated fadeInRight">
    <form id="editpasswd" action="user" method="post">
    	<table>
    		<tbody>
    			<tr>
    				<td><label >原密码：</label></td>
    				<td><input name="oldPasswd" class="form-control" required="required" type="password" value="${oldPasswd }"><p></td>
    			</tr>
				<tr>
					<td><label>新密码：</label></td>
					<td><input name="password" class="form-control" required="required" type="password" value="${password}"><p></td>
    			</tr>
    			<tr>
					<td><label>确认密码：</label></td>
					<td><input name="confirepasswd" class="form-control" required="required" type="password" value="${confirepasswd}"><p></td>
    			</tr>    	    	    			
    		</tbody>
    	</table>
    	<input type="hidden" name="action" value="editpasswd">
    	<input class="btn btn-w-m btn-success" type="submit" value="修&nbsp; 改">   
    	<a href="user?action=show"><button type="button" class="btn btn-w-m btn-default">返&nbsp; 回</button></a>
    </form>
	</div>
    </center>
    
  </body>
</html>
