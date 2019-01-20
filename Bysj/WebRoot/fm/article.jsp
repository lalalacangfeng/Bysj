<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${fm_article.title }</title>

<link rel="stylesheet" href="css/site.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" href="img/comicfm.ico">
<link href="js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
<style>
.fancybox img {
	margin-bottom: 5px;
	width: 24%;
}
</style>
<style>
.modal-backdrop {
	z-index: 0;
}
</style>
</head>

<body>
	<div id="head">
		<jsp:include page="../head.jsp"></jsp:include>
	</div>
	<br>
	<br>
	<br>

	<div class="container" id="news">
		<div class="wrapper wrapper-content  animated fadeInRight article">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1">
					<div class="ibox">
						<div class="ibox-content">
							<div class="text-center article-title">
								<h1 style="text-align: center">${fm_article.title }</h1>
							</div>
							<div id="from" style="text-align: center">新闻来源：${fm_article.from }
								编辑：${fm_article.writer } 发布时间：${fm_article.releasetime }</div>
							<div id="content">${fm_article.content }</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="comments">
		<jsp:include page="../comments.jsp"></jsp:include>
	</div>

	<div id="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
	
	<!-- Peity -->
	<script src="/Dmjd/js/plugins/peity/jquery.peity.min.js"></script>

	<!-- Fancy box -->
	<script src="/Dmjd/js/plugins/fancybox/jquery.fancybox.js"></script>

</body>
</html>
