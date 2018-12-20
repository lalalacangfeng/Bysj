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
    
    <title>用户列表</title>
    
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
  
  <body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-8">
				<div class="ibox">
					<div class="ibox-content">
						<h2>用户列表</h2>
  						<div class="input-group">

                        </div>
						<div class="clients-list">
                            <ul class="nav nav-tabs">
                                <span class="pull-right small text-muted"> ${uers.size() }个用户</span>
                                <li class="active">
                                	<a data-toggle="tab" >
                                	<i class="fa fa-user"></i> 联系人</a>
                                </li>
                            </ul>
							<div class="tab-content">
								<div id="tab-1" class="tab-pane active">
									<div class="full-height-scroll">
										<div class="table-responsive">
											<table class="table table-striped table-hover">
												<tbody>
													<c:forEach items="${users }" var="user">
													<tr>
														<!-- 头像图标
														<td class="client-avatar"><img alt="image" src=""></td>														
														 -->
														<td> <c:out value="${user.username }"></c:out></td><!-- 用户名 -->
														<td> <c:out value="${user.lasttime }"></c:out></td><!-- 最后登录时间 -->
														<td class="contact-type"><i class="fa fa-envelope"> </i></td><!-- 邮箱图标 -->
														<td> <c:out value="${user.email }"></c:out></td><!-- 邮箱 -->
														<c:choose>
															<c:when test="${user.role == 1 }">
																<td class="client-status"><span class="label label-success">管理员</span></td><!-- 验证状态 -->
															</c:when>
															<c:when test="${user.role == 2 }">
																<td class="client-status"><span class="label label-primary">已验证</span></td><!-- 验证状态 -->
															</c:when>
															<c:when test="${user.role == 3 }">
																<td class="client-status"><span class="label label-warning">等待验证</span></td><!-- 验证状态 -->
															</c:when>
															<c:when test="${user.role == 4 }">
																<td class="client-status"><span class="label label-info">未验证</span></td><!-- 验证状态 -->
															</c:when>
															<c:when test="${user.role == 5 }">
																<td class="client-status"><span class="label label-white">已删除</span></td><!-- 验证状态 -->
															</c:when>
															<c:otherwise>
																<td class="client-status"><span class="label label-danger">超级管理员</span></td><!-- 验证状态 -->															
															</c:otherwise>
														</c:choose>
													</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
