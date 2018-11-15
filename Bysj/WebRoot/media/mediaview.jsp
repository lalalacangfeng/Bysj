<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println("==========basePath:"+basePath);
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>视频展示</title>
    
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

	<!-- 嵌入Flash播放器 -->
	<div align="center" width="455">
	    <object classid='clsid:D27CDB6E-AE6D-11cf-96B8-4445535411111'
			codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0'
			width=500 height=400>
			<param name='movie' value='tools/flvplayer.swf' />
			<param name='quality' value='high' />
			<param name='allowFullScreen' value='true' />
			<param name='FlashVars'
			value='vcastr_file=<%=basePath%>videos/1538322642379.flv&IsAutoPlay=1&IsContinue=1' />   此处是你要播放的视频的路径，flv格式
			<embed src='tools/flvplayer.swf' allowfullscreen='true'
			flashvars='vcastr_file=<%=basePath%>videos/1538322642379.flv&IsAutoPlay=1&IsContinue=1'  此处是你要播放的视频的路径，flv格式
			quality='high'
			pluginspage='http://www.macromedia.com/go/getflashplayer'
			type='application/x-shockwave-flash' width=500 height=400 />
			</object>
	</div>
	
  </body>
</html>
