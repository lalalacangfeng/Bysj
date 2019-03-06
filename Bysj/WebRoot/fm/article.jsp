<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.println("path:"+path+"   basePath:"+basePath);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${fm_article.title }</title>
<link rel="stylesheet" href="js/plugins/layui/css/layui.css" />
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
	
	<script src="js/jquery.min.js"></script>
	<script src="<%=basePath%>/js/plugins/layui/layui.js"></script>
	<script src="<%=basePath%>/js/plugins/layer/layer.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
    layui.use('element', function () {
        var element = layui.element;
    });

    function btnReplyClick(elem) {
        var $ = layui.jquery;
        if($(this)){
        }else if(!$(this)){
            $(elem).parent('p').parent('.comment-parent').siblings('.replycontainer').toggleClass('layui-show');
        }
        $(elem).parent('p').parent('.comment-parent').siblings('.replycontainer').toggleClass('layui-hide');
        if ($(elem).text() == '回复') {
            $(elem).text('收起')
        } else {
            $(elem).text('回复')
        }
    };
    
    function wordBtn(){
    	var lw_name = $("#lw_name").val();
    	var lw_date = $("#lw_date").val();
    	var lw_for_article_id = $("#lw_for_article_id").val();
    	var lw_content = $("#lw_content").val();
    	console.log("lw_name:"+lw_name);
	  	console.log("lw_date:"+lw_date);
	  	console.log("lw_for_article_id:"+lw_for_article_id);
	  	console.log("lw_content:"+lw_content);
    	$.ajax({
    		url:'fm/article?action=saveWords',
    		type:'POST',
    		dataType : "json",
    		data : {
					lw_name : lw_name,
					lw_date : lw_date,
					lw_for_article_id : lw_for_article_id,
					lw_content : lw_content,
			},
			success : function(result) {
					if (result.ifExist == 2) {
						alert("留言失败！");
	                } else if (result.ifExist == 1) {
						alert("留言成功！");
	                    parent.location.reload();//parent.location.reload()刷新父亲对象（用于框架）
	                }
				},
				error : function() {
					alert("留言失败！");
				}
    	})
    };
    
    $("#replyBtn").click(function (){});
		
	function replyBtn(){
	alert("erer");
       var lr_for_article_id = $("#lr_for_article_id").val();
       var lr_name = $("#lr_name").val();
       var lr_date = $("#lr_date").val();
       var lr_for_name = $("#lr_for_name").val();
       var lr_content = $("#lr_content").val();
       var lr_for_words = $("#lr_for_words").val();
       console.log("lr_for_article_id:"+lr_for_article_id);
  	console.log("lr_name:"+lr_name);
  	console.log("lr_date:"+lr_date);
  	console.log("lr_content:"+lr_content);
  	console.log("lr_for_words:"+lr_for_words);
       $.ajax({
           url: '<%=basePath%>fm/article?action=saveReply',
			type : 'POST',
			dataType : "json",
			data :{
				lr_for_article_id : lr_for_article_id,
				lr_name : lr_name,
				lr_date : lr_date,
				lr_for_name : lr_for_name,
				lr_content : lr_content,
				lr_for_words : lr_for_words
			},
			success : function(result) {
				if (result.ifExist == 2) {
					alert("回复失败！");
                } else if (result.ifExist == 1) {
					alert("回复成功！");
                    parent.location.reload();//parent.location.reload()刷新父亲对象（用于框架）
                }
			},
			error : function() {
				alert("回复失败！");
			}
		});
	};
	</script>
	
</body>
	
</html>
