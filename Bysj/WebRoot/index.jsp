<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>DAdmin-主页</title>
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
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
  </head>
   <body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
	<div id="wrapper">
		<!-- 左侧导航栏 -->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">DAdmin</strong>
                                    </span>
                                </span>								
							</a>
						</div>
					</li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">BEGIN</span>
                    </li>
                    <li>
                        <a class="J_menuItem" href="">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">主页</span>
                        </a>
                    </li>
                    <li class="line dk"></li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">分类</span>
                    </li>
                    <!-- 邮箱功能 
                    <li>
                        <a href="mailbox.jsp"><i class="fa fa-envelope"></i> 
                        <span class="nav-label">信箱 </span>
                        <span class="label label-warning pull-right">16</span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="">收件箱</a>
                            </li>
                            <li><a class="J_menuItem" href="">查看邮件</a>
                            </li>
                            <li><a class="J_menuItem" href="">写信</a>
                            </li>
                        </ul>
                    </li> -->    
					<!-- 信息功能 -->
					<li>
                        <a href="#"><i class="fa fa-desktop"></i> 
                        <span class="nav-label">用户管理</span>
                        <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="user?action=show">个人信息</a>
                            </li>
                            <li><a class="J_menuItem" href="">用户列表</a>
                            </li>
                            <li><a class="J_menuItem" href="">添加用户</a>
                            </li>
                        </ul>
                    </li>                    
                    <li>
                        <a href="#"><i class="fa fa-edit"></i> 
                        <span class="nav-label">文章管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="article?action=init">写文章</a>
                            </li>
                            <li><a class="J_menuItem" href="article?action=show">文章列表</a>
                            </li>

                        </ul>
                    </li>                               
					<li>
                        <a href="#"><i class="fa fa-picture-o"></i> 
                        <span class="nav-label">图库管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="">图片上传</a>
                            </li>
                            <li><a class="J_menuItem" href="">相册</a>
                            </li>
                            </li>
                        </ul>
                    </li>  
                    <li>
                        <a href="#"><i class="fa fa-magic"></i> 
                        <span class="nav-label">影音管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="">视频发布</a>
                            </li>
                            <li><a class="J_menuItem" href="">视频</a>
                            </li>
                        </ul>
                    </li>                   
                    <li>
                        <a href="#"><i class="fa fa-cutlery"></i> 
                        <span class="nav-label">栏目管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="column?action=init">添加栏目</a>
                            </li>
                            <li><a class="J_menuItem" href="column?action=findall">栏目列表</a>
                            </li>
                        </ul>
                    </li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">END</span>
                    </li>
                         
				</ul>
			</div>
		</nav>
        <!--左侧导航结束-->
        
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <ul class="nav navbar-top-links navbar-right">
	                    <%
	                    	if(session.getAttribute("username")==null||session.getAttribute("username").equals("")){
	                     %>
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" href="login.jsp">
                                <i class="fa fa-user">【请登录】</i>
                            </a>
                        </li>
                        <%
                        	}else{
                         %>
                         <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                                <i class="fa fa-user"><font><%=session.getAttribute("username")%></font></i>
                            </a>
                        </li>
                        <li class="dropdown">
                            <a href="dologin?action=logout" class="fa fa-close">
                                	注销
                            </a>
                        </li>
                        <% } %>
                    </ul>
                </nav>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" width="100%" height="100%" src="" frameborder="0" data-id="" seamless></iframe>
            </div>
        </div>        
     	<!--右侧部分结束-->        

	</div>
    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="js/plugins/pace/pace.min.js"></script>

  </body>
</html>
