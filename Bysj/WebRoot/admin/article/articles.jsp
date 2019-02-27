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
    
    <title>新闻列表</title>
    
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

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/admin/style.css?v=4.1.0" rel="stylesheet">	

  </head>
  
<body class="gray-bg">
  <div class="wrapper wrapper-content animated fadeInRight">
  	<div class="row">
  		<div class="col-sm-12">
  			<div class="ibox float-e-margins">
  				<div class="ibox-content">
  					<table class="table table-striped table-bordered table-hover dataTables-example">
  						<thead>
                        	<tr>
	                            <th>文章编号</th>
	                            <th>文章标题</th>
	                            <th>发表时间</th>
	                            <th>作者</th>
	                            <th>所属栏目</th>
	                            <th>状态</th>
	                            <th>操作</th>
                           	</tr>
                        </thead>
                        <tbody>
	                   	<c:forEach items="${articles }" var="article">
                        	<tr class="gradeX">
	                            <td> <c:out value="${article.nid}"/></td>
	                            <td><a href="admin/article?action=show&nid=${article.nid}">
	                            	<c:out value="${article.title}"/></a></td>
	                            <td><c:out value="${article.releasetime}"/></td>
	                            <td class="center"><c:out value="${article.writer}"/></td>
	                            <td class="center"><c:out value="${article.columnname }"></c:out></td>
	                            <td>
	                            <c:if test="${article.status ==0}">
	                            	<a href="admin/article?action=release&nid=${article.nid}"> 发布</a>
	                            </c:if>		                 
	                            <c:if test="${article.status ==1}">       
	                            	<i class="fa fa-check text-navy"> 通过</i>
	                            	<a href="admin/article?action=cancel&nid=${article.nid}"><i class="fa fa-ban"> 撤销</i></a>
                            	</c:if>
	                            </td>
	                            <td><a href="admin/article?action=edit&nid=${article.nid}"><i class="fa fa-circle-o-notch"> 编辑</i></a> 
	                            <a href="admin/article?action=del&nid=${article.nid}"><i class="fa fa-close"> 删除</a></td>
                            </tr>
	                   	</c:forEach>
                        </tbody>
						<tfoot>
                        	<tr>
	                            <th>文章编号</th>
	                            <th>文章标题</th>
	                            <th>发表时间</th>
	                            <th>作者</th>
	                            <th>所属栏目</th>
	                            <th>状态</th>
	                            <th>操作</th>
                         	</tr>
                        </tfoot>                        
  					</table>
  				</div>
  			</div>
  		</div>
  	</div>
  </div>
    
	<!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>

    <!-- Data Tables -->
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

<!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {
            $('.dataTables-example').dataTable({
            
            }
            );
        });

    </script>


  </body>
</html>
