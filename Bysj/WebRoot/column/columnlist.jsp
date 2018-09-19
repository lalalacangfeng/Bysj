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
    
    <title>栏目列表</title>
    
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
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

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
		                            <th>栏目编号</th>
		                            <th>栏目名称</th>
		                            <th>栏目等级</th>
		                            <th>所属栏目</th>
		                            <th>操作</th>
	                           	</tr>
	                        </thead>
  							<tbody>
  								<c:forEach items="${columns }" var="column">
                       			<tr class="gradeX">
                        				<td>
	                        				<c:choose>
	                        					<c:when test="${column.cid ==0 }">
	                        						<c:out value="${column.kid}"/>
	                        					</c:when>
	                        					<c:otherwise>
	                        						<c:out value="${column.cid}"/>
	                        					</c:otherwise>
	                        				</c:choose>
                   						</td>
										<td>
											<c:choose>
	                        					<c:when test="${column.cid ==0 }">
	                        						<c:out value="${column.kindname}"/>
	                        					</c:when>
	                        					<c:otherwise>
	                        						<c:out value="${column.classname}"/>
	                        					</c:otherwise>
	                        				</c:choose>
										</td>
										<td>
											<c:choose>
                        					<c:when test="${column.cid ==0 }">
                        						<c:out value="一级栏目"/>
                        					</c:when>
                        					<c:otherwise>
                        						<c:out value="二级栏目"/>
                        					</c:otherwise>
                        				</c:choose>
										</td>
										<td>
											<c:choose>
                        					<c:when test="${column.cid ==0 }">
                        						<c:out value="无"/>
                        					</c:when>
                        					<c:otherwise>
                        						<c:out value="${column.kindname} (${column.kid})"/>
                        					</c:otherwise>
                        				</c:choose>
										</td>
										<td>
										<c:choose>
	                        					<c:when test="${column.cid ==0 }">
	                        					<a href="column?action=edit&cid=0&kid=${column.kid}"><i class="fa fa-circle-o-notch"> 编辑</i></a>
												<a href="column?action=del&cid=0&kid=${column.kid}"><i class="fa fa-close"> 删除</a>
	                        					</c:when>
	                        					<c:otherwise>
		                        					<a href="column?action=edit&cid=${column.cid}&kid=${column.kid}"><i class="fa fa-circle-o-notch"> 编辑</i></a>
													<a href="column?action=del&cid=${column.cid}&kid=${column.kid}"><i class="fa fa-close"> 删除</a>
	                        					</c:otherwise>
                        				</c:choose>
										</td> 
                       			</tr>
		  						</c:forEach>
                   			</tbody>
	  						<tfoot>
                        		<tr>
                        			<th>栏目编号</th>
		                            <th>栏目名称</th>
		                            <th>栏目等级</th>
		                            <th>所属栏目</th>
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
            $('.dataTables-example').dataTable();

        });
        
    </script>
	
</body>
</html>
