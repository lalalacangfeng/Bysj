<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${fm_vedio.name }</title>

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

	<div class="container" id="vedio">
		<link rel="stylesheet" href="css/DPlayer.min.css">
		<div id="dplayer"></div>
		<script src="js/DPlayer.min.js"></script>
		<script>
			const
			dp = new DPlayer({
				container : document.getElementById('dplayer'),
				video : {
					url : '${fm_vedio.src}',
					pic : '${fm_vedio.picture}',
					type : 'auto'
				}
			});
		</script>
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
