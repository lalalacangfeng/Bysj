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
    
    <title>发表新闻</title>
    
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
	<form id="addarticle" action="admin/article" method="post">
		<div class="wrapper wrapper-content animated fadeInRight">			
			<div class="div-l">
				<label class="col-sm-3 control-label">文章标题：</label>
				<input type="text" id="title" name="title" required="required" 
					    class="form-control" value="${article.title }" placeholder="请输入文章标题" >
				<br>
				<label class="col-sm-3 control-label">作 者：</label>
				<input type="text" id="writer" name="writer" required="required"
					     class="form-control" value="${article.writer }" placeholder="请输入作者" >
				<br>
				<label class="col-sm-3 control-label">文章顺序：</label>
				<input type="text" name="layers" class="form-control" required="required"
						     value="${article.layers }" placeholder="数字越大优先级越高,最小为1，最大为999" >
				<br>
				
			</div>
				
			<div class="div-r">
				<label class="col-sm-3 control-label">文章类型：</label>
				    <select class="form-control" name="columnname" >
				    <c:forEach items="${columns }" var="column">
					    <c:if test="${article.cid == column.cid }">
					        <option name="${column.cid }" value="${column.cid }">${column.columnname }</option>
			    		</c:if>
		    		</c:forEach>
				    	<c:forEach items="${columns }" var="column">
				    		<c:if test="${column.pid == 2 }">
						        <option name="${column.cid }" value="${column.cid }">${column.columnname }</option>
				    		</c:if>
				    	</c:forEach>
				    </select>
				<br>
				<label class="col-sm-3 control-label">来源：</label>
				<input type="text" id="from" name="from" class="form-control" required="required"
						     value="${article.from }" placeholder="原创或转载自：" >
				<br>
				<label class="col-sm-3 control-label">发布时间：</label>
				<input name="releasetime" class="form-control" required="required" value="${article.releasetime }"
							placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" >
			</div>
			
			<div class="div-c">

				<!-- 加载编辑器的容器 -->			
				<jsp:include page="/admin/ueditor.jsp"></jsp:include>
	
				<!-- 判断是否存在新闻 -->
				<c:set var="nid" scope="page" value="${nid }"/>
				<input type="hidden" name="nid" value="${nid }"></input>
				<c:choose>
				    <c:when test="${nid >= 0}">
		            	<input type="submit" name="action" class="btn btn-w-m btn-success" value="修改">
				    </c:when>
				    <c:otherwise>
		            	<input type="submit" name="action" class="btn btn-w-m btn-primary" value="添加" >
				    </c:otherwise>
				</c:choose>
				
			</div>
								
		</div>

	</form> 
	
	
	<!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <!-- layerDate plugin javascript -->
    <script src="js/plugins/layer/laydate/laydate.js"></script>
    
  </body>
</html>
