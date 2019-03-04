<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'medias.jsp' starting page</title>

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
    <link href="js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/admin/style.css?v=4.1.0" rel="stylesheet">

	<style>
.div1{
width:660px;
height:400px;
border:1px soild #f00;
}

.div2{
width:660px;
height:200px;
border:1px soild #f00;
}

.wenzi{
width:660px;
height:200px;
border:1px soild #f00;
}
	</style>


</head>

<body>
	<div class="clients-list">
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#tab-1"><i
					class="fa fa-briefcase"></i> 视频</a></li>
			<li class=""><a data-toggle="tab" href="#tab-2"><i
					class="fa fa-briefcase"></i> 图库</a></li>
		</ul>
		<div class="tab-content">
			<div id="tab-1" class="tab-pane active">
				<div class="full-height-scroll">
					<div class="table-responsive">
						<jsp:include page="vedios.jsp"></jsp:include>
					</div>
				</div>
			</div>
			<div id="tab-2" class="tab-pane">
				<div class="full-height-scroll">
					<div class="table-responsive">
						<jsp:include page="picts.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		
	<!-- 全局js -->
	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.6"></script>
	<script src="js/plugins/jeditable/jquery.jeditable.js"></script>
	
	<!-- Peity -->
    <script src="js/plugins/peity/jquery.peity.min.js"></script>

    <!-- Fancy box -->
    <script src="js/plugins/fancybox/jquery.fancybox.js"></script>

	<!-- Data Tables -->
	<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>


	</div>
</body>
</html>
