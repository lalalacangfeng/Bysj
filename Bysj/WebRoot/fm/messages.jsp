<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>'messages.jsp'</title>
    
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

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

<!-- Data Tables -->
<link href="css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

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
td{
	word-wrap:break-word;word-break:break-all;
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
	<div class="container" id="messages">
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
					<jsp:include page="showMessage.jsp" />
	
					<jsp:include page="inputMessage.jsp" />
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- Data Tables -->
	<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- Page-Level Scripts -->
	<script>
		$(document).ready(function() {
			$('.dataTables-example').dataTable({
				/*规定某列不排序
				　　columnDefs:[{
				　　　　'targets' : [0,1],    //除第六，第七两列外，都默认不排序
				　　　　'orderable' : false
				　　}]
				*/
				//禁止排序
				'ordering':false,
			});
		});
	</script>
	<script>
		function inputmessage(){
			var title = document.getElementById("title").value;
		  	var messcontent = document.getElementById("messcontent").value;
		  	var author = document.getElementById("author").value;
		  	console.log("author:"+author);
		  	console.log("title:"+title);
		  	console.log("messcontent:"+messcontent);
		  	if(title==""  || messcontent==""){
		        alert("请输入标题或内容");
		    return false;
		    }
		  	if(author==""){
		        alert("请重新登录");
		    return false;
		    }
		    if (title != null&&messcontent!=null) {
		    	$.ajax({
            type : "post",
            url : "fm/user?action=inputmessage",
            dataType : "json",
            data : {
                "title" : title,"messcontent":messcontent,"author":author
            },
            success : function(result) {
                if (result.ifExist == 2) {
                    alert("留言失败");
                } else if (result.ifExist == 1) {
                    alert("留言成功！");
                    parent.location.reload();//parent.location.reload()刷新父亲对象（用于框架）
                }else if (result.ifExist == 4) {
                    alert("留言出错");
                }
            }
        });
		    }
		}	
	</script>
	
  </body>
</html>
