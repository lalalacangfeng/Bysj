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

<title>fm.jsp</title>

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
		<div id="content_header" class="row" style="margin-top: 20px;">
			<div class="ourlogo">
				<!-- 
				<img src="images/logo.png" alt="our logo">
			 -->
			</div>
		</div>

		<div id="content">
			<div class="panel panel-default">
				<div class="panel-heading">
					<c:forEach items="${fm_Menu }" var="tt">
						/${tt }
					</c:forEach>
				</div>
				<div class="content_panel">
					<c:choose>
						<c:when test="${not empty fm_articles }">
							<c:forEach items="${fm_articles }" var="article">
								<div class="row">
									<!---->
									<div class="news-item">
										<c:if test="${article.src != null }">
											<img class="news-item-image" src="${article.src }">
										</c:if>
										<div class="item-text">
											<h4 class="new-item-title">${article.title }</h4>
											<p class="new-item-intro">${article.profile }</p>
											<a href="fm/article?action=show&nid=${article.nid}"
												class="button button-pill button-tiny">查看全文</a>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:when test="${not empty fm_picts }">
							<div class="ibox-content">
								<c:forEach items="${fm_picts }" var="pict" varStatus="status">
									<a class="fancybox" href="/Dmjd/${pict.src }"
										title="${pict.name }"><img src="/Dmjd/${pict.src }"
										alt="${pict.name }" /></a>
								</c:forEach>
							</div>
						</c:when>
						<c:when test="${not empty fm_vedios }">
							<c:forEach items="${fm_vedios }" var="vedio" varStatus="status">
								<div class="item">
									<a href="fm/vedio?action=show&id=${vedio.id }" class="thumbnail">
										<img alt="${vedio.name }" src="/Dmjd/${vedio.picture }">
									</a>
									<div class="item-intro" style=" text-align:center">
										<h5 class="item-title">
											<strong>${vedio.name }</strong>
										</h5>
										<p class="item-source">来源：DmFM</p>
										<c:if test="${vedio.time != null }">
											<p class="item-time">
												<span class="glyphicon glyphicon-time"></span> ${vedio.time }
											</p>
										</c:if>
									</div>
								</div>
							</c:forEach>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>

	<div id="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>



	<!-- Peity -->
	<script src="/Dmjd/js/plugins/peity/jquery.peity.min.js"></script>

	<!-- Fancy box -->
	<script src="/Dmjd/js/plugins/fancybox/jquery.fancybox.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$('.fancybox').fancybox({
				openEffect : 'none',
				closeEffect : 'none'
			});
		});
	</script>


</body>
</html>
