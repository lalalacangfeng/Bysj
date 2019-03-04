<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加栏目或标签</title>
    
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
		.div-l{ float:left;width:49% } 
		.div-r{ float:right;width:49% } 
		.div-c{ float:left;width:98% }
	</style>
	
  </head>
  
  <body>
    <center>
    <div class="wrapper wrapper-content animated fadeInRight">
    <form id="editgonggao" action="admin/upload/gonggao" method="post">
    	<table>
    		<tbody>
    			<tr>
    				<td><label >公告标题：</label></td>
    				<td><input name="title" class="form-control"  type="text" value="${gonggao.title }"><p></td>
    			</tr>
				<tr>
					<td><label>公告内容：</label></td>
					<td><input name="content" class="form-control"  type="text" value="${gonggao.content }"><p></td>
    			</tr>	    			
    		</tbody>
    	</table>
    	<input type="hidden" name="action" value="update">
    	<input class="btn btn-w-m btn-success" type="submit" value="修&nbsp; 改">   
    	<a href="admin/upload/gonggao?action=show"><button type="button" class="btn btn-w-m btn-default">返&nbsp; 回</button></a>
    </form>
	</div>
    </center>
  </body>
</html>
