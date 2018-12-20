<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${article.title }</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

  </head>
  
  <body>
  <div class="wrapper wrapper-content  animated fadeInRight article">
  <div class="row">
  <div class="col-lg-10 col-lg-offset-1">
  <div class="ibox">
  <div class="ibox-content">
	<div class="text-center article-title"><h1>${article.title }</h1></div>
	<div id="from">新闻来源：${article.from }  编辑：${article.writer }  发布时间：${article.releasetime }</div>
	<div id="content">${article.content }</div>
  </div>
  </div>
  </div>
  </div>
  </div>
  </body>
</html>
