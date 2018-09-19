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
	<link href="css/style.css?v=4.1.0" rel="stylesheet">
  </head>
  
  <body>
    <form id="columns" action="column" method="post">
    	<div class="wrapper wrapper-content animated fadeInRight">
			<div class="ibox float-e-margins">
				<div class="col-md-12">    
			        <label class="col-sm-3 control-label">类别：</label>
			        <div class="col-sm-9">
			            <label class="radio-inline">
			                <input type="radio" value="0" checked="checked" id="optionsRadios1" name="optionsRadios">一级栏目</label>
			            <label class="radio-inline">
			                <input type="radio" value="100" id="optionsRadios2" name="optionsRadios">二级栏目</label>
			            <label class="radio-inline">
			                <input type="radio" value="4" id="optionsRadios3" name="optionsRadios">动漫标签</label>
			        </div>
			        <c:set var="type" value="${optionsRadios.value }"/>
			        type:<c:out value="${optionsRadios.value }"/>
			        <label class="col-sm-3 control-label">所属栏目列表：</label>
			        <div class="col-sm-9">
			            <select class="form-control" name="classname">
			            	<c:forEach items="${columns }" var="column">
		            			<option name="${column.kid }" value="${column.kid }">${column.kindname }</option>
			            	</c:forEach>
			            </select>
			        </div>
			        <label class="col-sm-3 control-label">字段名：</label>
			        <div class="col-sm-9">
			            <input type="text" name="inputname" class="form-control" placeholder="请输入文本">
			        </div>
					<input type="submit" name="action" class="btn btn-w-m btn-success" value="添加" >
				</div>
			</div>
		</div>
    </form>
  </body>
</html>
