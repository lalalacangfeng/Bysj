<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传视频</title>
    
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
    
    <link rel="stylesheet" type="text/css" href="css/plugins/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="css/demo/webuploader-demo.css">
	
	<link href="css/admin/style.css?v=4.1.0" rel="stylesheet">

  </head>
  
  <body>
	<form id="uploadmedia" name="uploadmedia" action="admin/media" 
    encType="multipart/form-data" method="post">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="ibox float-e-margins">
				<ul class="forminfo">
					<li>
						<label class="col-sm-3 control-label">名称：</label>
						<input type="text" id="name" name="name" required="required" 
					    class="form-control" value="${media.name }" placeholder="请输入标题">
					    <br>
					</li>
					<li>
						<label class="col-sm-3 control-label">描述：</label>
						<input type="text" id="descript" name="descript" required="required" 
					    class="form-control" value="${media.descript }" placeholder="请输入描述">
					    <br>
					</li>
				
				<div id="vedio">
					<li>
						<label class="col-sm-3 control-label">上传资源&nbsp;&nbsp;(暂时只支持asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等格式文件) </label>
						<input type="file" id="file" name="file" class="form-control">
					    <br>
					</li>
				</div>
				<div id="progressbar" class="progress progress-striped active" style="display:none">
                           <div style="width: 100%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="100" role="progressbar" class="progress-bar progress-bar-danger">
                               <span class="sr-only">100% Complete (success)</span>
                           </div>
                </div>
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
        
    <script src="js/media/addvedio.js"></script>    

  </body>
</html>
