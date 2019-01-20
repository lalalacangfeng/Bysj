<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date date = new Date();
	String nowDate = sdf.format(date);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>留言评论</title>

<link rel="stylesheet" href="js/plugins/layui/css/layui.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<style>
.modal-backdrop {
	z-index: 0;
}
</style>
</head>

<body>
	<br />
	<br />
	<br />
	<div style="margin:0 4% 0 4%;">
		<br />
		<!-- 留言的表单 -->
		<form class="layui-form" action="<%=basePath%>/fm/article/saveWords.do"
			method="post">
			<input name="lw_name" value="${sessionScope.username}" hidden="hidden" />
			<input name="lw_date" value="<%=nowDate%>" hidden="hidden" /> <input
				name="lw_for_article_id" value="${fm_article.nid}" hidden="hidden" />
			<div class="layui-input-block" style="margin-left: 0;">
				<textarea id="lw_content" name="lw_content" placeholder="请输入你的留言" required="required"
					class="layui-textarea" style="height: 150px;"></textarea>
			</div>
			<br />
			<div class="layui-input-block"
				style="text-align: left;margin-left: 0;">
				<c:choose>
					<c:when test="${username != null }">
						<!-- 用户存在 -->
						<input type="submit" class="layui-btn" value="留言">
					</c:when>
					<c:otherwise>
						请先登录
					</c:otherwise>
				</c:choose>
			</div>
		</form>
		<br />
		<!-- 留言信息列表展示 -->
		<div>
			<ul>
				<!-- 先遍历留言信息（一条留言信息，下面的全是回复信息） -->
				<c:forEach items="${fm_words}" var="words">
					<!-- 如果留言信息是在本文章下的才显示 -->
					<c:if test="${words.lw_for_article_id eq fm_article.nid}">
						<li style="border-top: 1px dotted #01AAED"><br />
							<div style="text-align: left;color:#444">
								<div>
									<span style="color:#01AAED">${words.lw_name}</span>
								</div>
								<div>${words.lw_content}</div>
							</div>
							<div>
								<div class="comment-parent"
									style="text-align:left;margin-top:7px;color:#444">
									<span>${words.lw_date}</span> &nbsp;&nbsp;&nbsp;&nbsp;
									<p>
										<a href="javascript:;" style="text-decoration: none;"
											onclick="btnReplyClick(this)">回复</a>
									</p>
									<hr style="margin-top: 7px;" />
								</div>
								<!-- 回复表单默认隐藏 -->
								<div class="replycontainer layui-hide"
									style="margin-left: 61px;">
									<form action="<%=basePath%>/fm/article/saveReply.do" method="post"
										class="layui-form">
										<input name="lr_for_article_id" id="lr_for_article_id"
											value="${fm_article.nid}" hidden="hidden" /> <input
											name="lr_name" id="lr_name" value="${sessionScope.username}"
											hidden="hidden" /> <input name="lr_date" id="lr_date"
											value="<%=nowDate%>" hidden="hidden" /> <input
											name="lr_for_name" id="lr_for_name" value="${words.lw_name}"
											hidden="hidden" /> <input name="lr_for_words"
											id="lr_for_words" value="${words.lw_id}" hidden="hidden" />
										<input name="lr_for_reply" id="lr_for_reply"
											value="${reply.lr_id}" hidden="hidden" />
										<div class="layui-form-item">
											<textarea name="lr_content" id="lr_content"
												lay-verify="replyContent" placeholder="请输入回复内容" required="required"
												class="layui-textarea" style="min-height:80px;"></textarea>
										</div>
										<div class="layui-form-item">
											<button id="replyBtn" class="layui-btn layui-btn-mini"
												lay-submit="formReply" lay-filter="formReply">提交</button>
										</div>
									</form>
								</div>
							</div> <!-- 以下是回复信息 --> <c:forEach items="${fm_replies}"
								var="reply">
								<!-- 每次遍历出来的留言下存在回复信息才展示（本条回复信息是本条留言下的就显示在当前留言下） -->
								<c:if
									test="${reply.lr_for_article_id eq fm_article.nid && reply.lr_for_words eq words.lw_id}">
									<div style="text-align: left;margin-left:61px;color: #444">
										<div>
											<span style="color:#5FB878">${reply.lr_name}&nbsp;&nbsp;</span>
										</div>
										<div>@${reply.lr_for_name}:&nbsp;&nbsp;
											${reply.lr_content}</div>
									</div>
									<div>
										<div class="comment-parent"
											style="text-align:left;margin-top:7px;margin-left:61px;color:#444">
											<span>${reply.lr_date}</span> &nbsp;&nbsp;&nbsp;&nbsp;
											<p>
												<a href="javascript:;" style="text-decoration: none;"
													onclick="btnReplyClick(this)">回复</a>
											</p>
											<hr style="margin-top: 7px;" />
										</div>
										<!-- 回复表单默认隐藏 -->
										<div class="replycontainer layui-hide"
											style="margin-left: 61px;">
											<form action="<%=basePath%>/fm/article/saveReply.do"
												method="post" class="layui-form">
												<input name="lr_for_article_id" id="lr_for_article_id"
													value="${fm_article.nid}" hidden="hidden" /> <input
													name="lr_name" id="lr_name" value="${sessionScope.name}"
													hidden="hidden" /> <input name="lr_date" id="lr_date"
													value="<%=nowDate%>" hidden="hidden" /> <input
													name="lr_for_name" id="lr_for_name"
													value="${words.lw_name}" hidden="hidden" /> <input
													name="lr_for_words" id="lr_for_words"
													value="${words.lw_id}" hidden="hidden" /> <input
													name="lr_for_reply" id="lr_for_reply"
													value="${reply.lr_id}" hidden="hidden" />
												<div class="layui-form-item">
													<textarea name="lr_content" id="lr_content" required="required" 
														lay-verify="replyContent" placeholder="请输入回复内容"
														class="layui-textarea" style="min-height:80px;">
                                                      @${words.lw_name}:&nbsp;&nbsp;
                                                  </textarea>
												</div>
												<div class="layui-form-item">
													<button id="replyBtn" class="layui-btn layui-btn-mini"
														lay-submit="formReply" lay-filter="formReply">提交</button>
												</div>
											</form>
										</div>
									</div>
								</c:if>
							</c:forEach></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
	<br />
	<br />

	<script src="<%=basePath%>/js/jquery.min.js"></script>
	<script src="<%=basePath%>/js/plugins//layui/layui.js"></script>
	<script type="text/javascript">
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
	<script type="text/javascript">
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
    }
    $("#replyBtn").click(function(){
        var lr_for_article_id = $("#lr_for_article_id").val();
        var lr_name = $("#lr_name").val();
        var lr_date = $("#lr_date").val();
        var lr_for_name = $("#lr_for_name").val();
        var lr_content = $("#lr_content").val();
        var lr_for_words = $("#lr_for_words").val();
        $.ajax({
            url: '<%=basePath%>/fm/article/saveReply.do',
				type : 'POST',
				data : [ {
					lr_for_article_id : lr_for_article_id,
					lr_name : lr_name,
					lr_date : lr_date,
					lr_for_name : lr_for_name,
					lr_content : lr_content,
					lr_for_words : lr_for_words
				} ],
				success : function(data) {
					layer.open({
						title : '提示信息',
						content : '留言成功',
						btn : [ '确定' ],
						btn1 : function(index) {
							$("body").html(data);
						}
					});
				},
				error : function() {
					layer.open({
						title : '提示信息',
						content : '出现未知错误'
					});
				}
			});
		});
	</script>

</body>
</html>
