<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发布新闻</title>
    
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
	<link href="css/style.css?v=4.1.0" rel="stylesheet">
  </head>
  
  <body>
  	<form id="releasearticle" action="article" method="post">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
		<table  style="width: 1096px;cellpadding:0;cellspacing:0;">
			<tbody>
				<tr>
					<td width="33%"><label class="col-sm-3 control-label">文章标题：</label></th>
					<td width="33%"><label class="col-sm-3 control-label">文章类型：</label></th>
					<td rowspan="6" width="33%">
					<label class="col-sm-3 control-label"><p></p></label>
			<div class="col-sm-12">
				<c:set var="nid" scope="session" value="${nid }"/>
				<c:choose>
				    <c:when test="${nid >= 0}">
		            	<input type="submit" name="action" class="btn btn-w-m btn-default" value="修改">
				    </c:when>
				    <c:otherwise>
		            	<input type="submit" name="action" class="btn btn-w-m btn-default" value="添加" >
				    </c:otherwise>
				</c:choose>
						<a href="article?action=show" class="btn btn-w-m btn-default" >取消</a>
			</div>
					</th>
				</tr>			
    			<tr>
    				<td width="100">
    					<div class="col-sm-12">
					    <input type="text" id="articletitle" name="articletitle" required="required" 
					    class="form-control" value="${article.articletitle }" placeholder="请输入文章标题" style="width:100%">
						<span class="help-block m-b-none"></span>
						</div>
    				</td>
    				<td width="100">
						<div class="col-sm-12">
						    <select class="form-control" name="classname" style="width:100%">
						    	<c:forEach items="${columns }" var="column">
							        <option name="${column.cid }" value="${column.cid }">${column.classname }</option>
						    	</c:forEach>
						    </select>
						</div>
    				</td>
    			</tr>
   				<tr>
					<th><label class="col-sm-3 control-label">作 者：</label></th>
					<th><label class="col-sm-3 control-label">来源：</label></th>
				</tr>
   				<tr>
   					<td width="100">
						<div class="col-sm-12">
						    <input type="text" id="articlewriter" name="articlewriter" required="required"
						     class="form-control" value="${article.articlewriter }" placeholder="请输入作者" style="width:100%">
						<span class="help-block m-b-none"></span>
						</div>   					
   					</td>
   					<td width="100">
						<div class="col-sm-12">
						    <input type="text" id="from" name="from" class="form-control" required="required"
						     value="${article.from }" placeholder="原创或转载自：" style="width:100%">
						<span class="help-block m-b-none"></span>
						</div>			
   					</td>
   				</tr>  			
   				<tr>
   					<th><label class="col-sm-3 control-label">文章顺序：</label></th>
   					<th><label class="col-sm-3 control-label">发布时间：</label></th>
   				</tr>
   				<tr>
   					<td width="100">
						<div class="col-sm-12">
						    <input type="text" name="layers" class="form-control" required="required"
						     value="${article.layers }" placeholder="数字越大优先级越高,最小为1，最大为999" style="width:100%">
						<span class="help-block m-b-none"></span>
						</div>
   					</td>
   					<td width="100">
						<div class="col-sm-12">
							<input id="releasetime" name="releasetime" class="form-control layer-date" required="required" value="${article.releasetime }"
							placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" style="width:100%" >
						</div>
   					</td>
   				</tr>
   			</tbody>
		</table>			
			
		</div>
	</div>
	<!-- 
	<div class="wrapper wrapper-content animated fadeInRight">
	<script id="container" name="content" type="text/plain">   	
	<c:out value="${article.articlecontent }" escapeXml="false" default="初始化内容"></c:out>
	</script>
	</div>
	 -->
	<jsp:include page="/ueditor.jsp"></jsp:include>


	</form> 
	
	<!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <!-- layerDate plugin javascript -->
    <script src="js/plugins/layer/laydate/laydate.js"></script>

  </body>
</html>
