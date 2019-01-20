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

<title>Dm-FM</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="css/site.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" href="img/comicfm.ico">
<link href="js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">

<style>
.modal-backdrop {
	z-index: 0;
}
</style>

</head>

<body>
	<div id="head">
		<jsp:include page="head.jsp"></jsp:include>
	</div>


	<div id="carousel">
		<jsp:include page="carousel.jsp"></jsp:include>
	</div>

	<br>
	<br>
	<br>
	<br>
	<br>
	<div id="container">
		<jsp:include page="container.jsp"></jsp:include>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$("#navtab a").click(function(e) {
			$(this).tab("show");
		})
	</script>

	<!-- Fancy box -->
	<script src="js/plugins/fancybox/jquery.fancybox.js"></script>

	<script type="text/javascript">
		!function($) {
			$(function() {
				$('#MyCarousel').carousel()
			})
		}(window.jQuery)
	</script>

	<!--返回顶部-->
	<a id="scrollUp" href="#MyCarousel"
		style="position: fixed; z-index: 2147483647; display: block;"> <img
		src="images/btn_uptotop.png" alt="btn_UpToTop" /></a>
	<!--END 返回顶部-->
</body>
</html>
