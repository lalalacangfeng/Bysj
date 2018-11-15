<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println("path:"+path+"      basePath:"+basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addmedia.jsp' starting page</title>
    
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
    <form id="uploadmedia" name="uploadmedia" action="media" 
    encType="multipart/form-data" method="post">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="ibox float-e-margins">
				<ul class="forminfo">
					<li>
						<label class="col-sm-3 control-label">视频名称：</label>
						<input type="text" id="name" name="name" required="required" 
					    class="form-control" value="${media.name }" placeholder="请输入视频标题">
					    <br>
					</li>
					<li>
						<label class="col-sm-3 control-label">视频描述：</label>
						<input type="text" id="descript" name="descript" required="required" 
					    class="form-control" value="${media.descript }" placeholder="请输入视频描述">
					    <br>
					</li>
					<li>
						<label class="col-sm-3 control-label">上传资源&nbsp;&nbsp;(暂时只支持asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等格式文件) </label>
						<input type="file" id="file" name="file" 
					    class="form-control">
					    <br>
					</li>
					<li>
						<label>&nbsp;</label>
						<input id="upload" name="upload" onclick="test()"
						type="button" class="btn" value="提交"/>
						<br>
					</li>
               </ul>
        	</div>
    	</div>
   </form>
   
   	<!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    
    
    <script type="text/javascript" src="js/media/addmedia.js"></script>
    

  </body>
</html>
