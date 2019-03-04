<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println("path:"+path);
System.out.println("basePath:"+basePath);
%>
<%

	String imgBasePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+"/Dmjd/vedios";
			
			System.out.println("imgBasePath:"+imgBasePath);
%>
<%String src = request.getParameter("src");
String id = request.getParameter("id");
String pic = request.getParameter("pict");
System.out.println("src:"+src);

 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'vedio.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body>
    <link rel="stylesheet" href="css/DPlayer.min.css">
	<div id="dplayer"></div>
	<script src="js/flv1.js"></script>
	<script src="js/DPlayer.min.js"></script>
		<script>
const dp = new DPlayer({
    container: document.getElementById('dplayer'),
    video: {
        url: '<%=basePath %><%=src %>',
        pic: '<%=basePath %><%=pic %>',
        type: 'auto'
    }
});
		</script>

  </body>
</html>
