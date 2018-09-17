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
    <title>ueditor 富文本框</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <!-- 配置文件 -->
    <script type="text/javascript" src="ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="ueditor/ueditor.all.js"></script>

  </head>
  
  <body>
	<!-- 加载编辑器的容器 -->
    <script id="container" name="content" type="text/plain">
        	
	<c:out value="${article.articlecontent }" escapeXml="false" default="初始化内容"></c:out>
    </script>

    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container',{
			toolbars: [
    		['fullscreen','source','undo','redo','fontfamily','fontsize','paragraph','link','unlink','date','time','simpleupload','insertimage','insertvideo','preview','pagebreak','emotion','spechars','searchreplace'],
    		['bold','italic','underline','fontborder','strikethrough','superscript','subscript','removeformat','formatmatch','autotypeset','blockquote','pasteplain','|', 'forecolor','backcolor','justifyleft','justifyright','justifycenter','justifyjustify','insertorderedlist','insertunorderedlist','selectall', 'cleardoc']]
        });
		 
    </script>
  </body>
</html>