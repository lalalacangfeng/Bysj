<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.modal-backdrop {
	z-index: 0;
}
</style>

<!--NAVBAR 导航栏-->
<header id="mynav"
	class="navbar navbar-default navbar-static-top bs-docs-nav navbar-fixed-top"
	role="banner">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="index.jsp" class="navbar-brand">Dm-FM</a>
		</div>
		<!--navbar header-->

		<nav class="navbar-collapse bs-navbar-collapse collapse"
			aria-expanded="false" style="height: 1px;">
			<ul class="nav navbar-nav" id="navtab">
				<li class="active"><a href="">首页</a></li>

				<c:forEach items="${menus }" var="menu">
					<c:choose>
						<c:when test="${menu.id != 0 && not empty menu.childMenus }">
							<li class="dropdown"><a href="fm/${menu.id }"
								class="dropdown-toggle" data-toggle="dropdown" role="button">${menu.name }
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<c:forEach items="${menu.childMenus }" var="secondChild">
										<li><a href="${secondChild.id }.fm">${secondChild.name }</a></li>
									</c:forEach>
									<li><a href="${menu.id }.fm">全部</a></li>
								</ul></li>
						</c:when>
						<c:when test="${menu.id != 0 && not empty menu.childPicts }">
							<li class="dropdown"><a href="${menu.id }.fm">${menu.name }</a>
							</li>
						</c:when>
						<c:when test="${menu.id != 0 && not empty menu.childVedios }">
							<li class="dropdown"><a href="${menu.id }.fm">${menu.name }</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="dropdown"><a href="${menu.id }.fm">${menu.name }</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<form action="fm/query" method="get" class="navbar-form navbar-right"
					role="search">
					<div class="form-group">
						<input name="title" type="text" class="form-control" autocomplete="on"
							placeholder="小黄人">
					</div>
					<input type="hidden" name="action" value="query"/>
					<button type="submit" class="btn btn-default">
						<span aria-hidden="true"><img src="img/btn_search.png"
							alt="btn_search" style="width: 25px;" /></span>
					</button>
				</form>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<!-- 待定
				<li role="presentation"><a>
				<button type="button" data-toggle="modal" data-target="#gonggao"
									style="background-color: transparent; border: 0;">公告</button>
									</a></li>
				 -->
				<c:choose>
					<c:when test="${username != null }">
						<!-- 用户存在 -->
						<li role="presentation"><a>
						<button type="button" data-toggle="modal" data-target="#${username }"
									style="background-color: transparent; border: 0;">${username }</button>
						  </a></li>
						<li role="presentation"><a href="fm/logout"> 注销 </a></li>
					</c:when>
					<c:otherwise>
						<li role="presentation"><a>
								<button type="button" data-toggle="modal" data-target="#login"
									style="background-color: transparent; border: 0;">登陆</button>
						</a></li>
						<li role="presentation"><a>
								<button type="button" data-toggle="modal"
									data-target="#register"
									style="background-color: transparent; border: 0;">注册</button>
						</a></li>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${username == null }">
					<!-- 注册窗口 -->
				<div id="register" class="modal fade" tabindex="-1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-body">
								<button class="close" data-dismiss="modal">
									<span>&times;</span>
								</button>
							</div>
							<div class="modal-title">
								<h1 class="text-center">注册</h1>
							</div>
							<div class="modal-body">
								<form class="form-group" action="fm/register">
									<div class="form-group">
										<label for="">用户名</label> <input class="form-control" id="uname"
											required="required" name="uname" type="text" oninput="checkName()"
											placeholder="6-15位字母或数字">
											<label class="col-sm-3 control-label" id="userstatus"
											style="color: #FF0000; ">用户名状态：</label>
									</div>
									<div class="form-group">
										<label for="">密码</label> <input class="form-control"
											required="required" id="passwd1" name="passwd"
											type="password" placeholder="至少6位字母或数字">
									</div>
									<div class="form-group">
										<label for="">再次输入密码</label> <input class="form-control"
											required="required" id="secpasswd" name="secpasswd"
											type="password" placeholder="至少6位字母或数字" oninput="checkPass()">
										<label class="col-sm-3 control-label" id="passwdstatus"
											style="color: #FF0000; "></label>
									</div>
									<div class="form-group">
										<label for="">邮箱</label> <input class="form-control"
											required="required" id="email" oninput="checkEmail()"
											name="email" type="email" placeholder="例如:123@123.com">
											<label class="col-sm-3 control-label" id="emailstatus"
											style="color: #FF0000; ">邮箱状态：</label>
									</div>
									<div class="text-right">
										<input class="btn btn-primary" type="button" value="提交" onclick="checkregister()"/>
										<button class="btn btn-danger" data-dismiss="modal">取消</button>
									</div>
									<a href="" data-toggle="modal" data-dismiss="modal"
										data-target="#login">已有账号？点我登录</a>
								</form>
							</div>
						</div>
					</div>
				</div>

				<!-- 登录窗口 -->
				<div id="login" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-body">
								<button class="close" data-dismiss="modal">
									<span>&times;</span>
								</button>
							</div>
							<div class="modal-title">
								<h1 class="text-center">登录</h1>
							</div>
							<div class="modal-body">
								<form class="form-group" action="fm/login" method="post">
									<div class="form-group">
										<label for="">用户名</label> <input name="uname" id="loginuname"
											class="form-control" type="text" placeholder="">
									</div>
									<div class="form-group">
										<label for="">密码</label> <input name="passwd" id="passwd"
											class="form-control" type="password" placeholder="">
									</div>
									<label class="col-sm-3 control-label" id="loginstatus" name="loginstatus" style="color: #FF0000; ">${status }</label>
									<div class="text-right">
										<input class="btn btn-primary" type="button" value="登录" onclick="checklogin()"/>
										<button class="btn btn-danger" data-dismiss="modal">取消</button>
									</div>
									<a href="" data-toggle="modal" data-dismiss="modal"
										data-target="#register">还没有账号？点我注册</a>
								</form>
							</div>
						</div>
					</div>
				</div>
					</c:when></c:choose>

				
				<c:if test="${username!=null }">
					<!-- 用户信息 -->
					<div id="${username }" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-body">
									<button class="close" data-dismiss="modal">
										<span>&times;</span>
									</button>
								</div>
								<div class="modal-title">
									<h1 class="text-center">${username }用户信息</h1>
								</div>
								<div class="modal-body">
									<table>
										<tbody>
											<tr>
												<td><label>用户名：</label></td>
												<td><label class="form-control" style="width:100%">${username}</label></td>
											</tr>
											<tr>
												<td><label>用户邮箱：</label></td>
												<td><label class="form-control" style="width:100%">${email}</label></td>
											</tr>
										</tbody>
									</table>
									<a><button type="button" class="btn btn-w-m btn-success" data-toggle="modal" data-target="#editinf"
									>修改信息</button></a>
          						  	<a><button type="button" class="btn btn-w-m btn-default" data-toggle="modal" data-target="#editpass"
									>修改密码</button></a>
								</div>
							</div>
						</div>
					</div>
					<!-- 修改用户信息 -->
					<div id="editinf" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-body">
									<button class="close" data-dismiss="modal">
										<span>&times;</span>
									</button>
								</div>
								<div class="modal-title">
									<h1 class="text-center">${username }用户信息</h1>
								</div>
								<div class="modal-body">
									<form id="editinf" action="fm/user" method="post">
										<table>
											<tbody>
												<tr>
													<td><label>用户名：</label></td>
													<td><input name="username" class="form-control" oninput="checkName()" id="uname"
														required="required" type="text" value="${username }">
														<label class="col-sm-3 control-label" id="userstatus"
											style="color: #FF0000; "></label>
														<p></td>
												</tr>
												<tr>
													<td><label>用户邮箱：</label></td>
													<td><input name="email" class="form-control" oninput="checkEmail()" id="email"s
														required="required" type="text" value="${email}">
											<label class="col-sm-3 control-label" id="emailstatus"
											style="color: #FF0000; "></label>
														<p></td>
												</tr>
											</tbody>
										</table>
										
										<input class="btn btn-w-m btn-success" type="button"
											value="修&nbsp; 改" onclick="edituserinf()"/>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- 修改用户密码 -->
					<div id="editpass" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-body">
									<button class="close" data-dismiss="modal">
										<span>&times;</span>
									</button>
								</div>
								<div class="modal-title">
									<h1 class="text-center">${username }用户信息</h1>
								</div>
								<div class="modal-body">
									<form id="editpasswd" action="fm/user" method="post">
										<table>
											<tbody>
												<tr>
													<td><label>原密码：</label></td>
													<td><input name="oldPasswd" class="form-control" id="oldPasswd"
														required="required" type="password" value="${oldPasswd }">
													<p></td>
												</tr>
												<tr>
													<td><label>新密码：</label></td>
													<td><input name="passwd" class="form-control" id="passwd1"
														required="required" type="password" value="${password}">
													<p></td>
												</tr>
												<tr>
													<td><label>确认密码：</label></td>
													<td><input name="secpasswd" class="form-control" oninput="checkPass()"
														required="required" type="password"  id="secpasswd"
														value="${confirepasswd}">
														<label class="col-sm-3 control-label" id="passwdstatus"
											style="color: #FF0000; "></label>
													<p></td>
												</tr>
											</tbody>
										</table>
										<input type="hidden" name="action" value="editpasswd">
										<input class="btn btn-w-m btn-success" type="button"
											value="修&nbsp; 改" onclick="editpass()">
									</form>
								</div>
							</div>
						</div>
					</div>
				</c:if>
					
				<!-- 公告-->
				<div id="gonggao" class="modal fade">
					<div class="modal-dialog">
						<c:choose>
							<c:when test="${not empty gonggao }">
								<div class="modal-content">
									<div class="modal-body">
										<button class="close" data-dismiss="modal">
											<span>&times;</span>
										</button>
									</div>
									<div class="modal-title">
										<h1 class="text-center">${gonggao.title }</h1>
									</div>
									<div class="modal-body">
										<label for="">${gonggao.content }</label>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="modal-content">
									<div class="modal-body">
										<button class="close" data-dismiss="modal">
											<span>&times;</span>
										</button>
									</div>
									<div class="modal-title">
										<h1 class="text-center">${gonggao.title }</h1>
									</div>
									<div class="modal-body">
										<label for="">暂无新公告</label>
									</div>
								</div>
								
							</c:otherwise>
						</c:choose>
					</div>
				</div>
					
			</ul>
		</nav>
	</div>
	<script src="js/head.js"></script>
</header>
<!--END NAVBAR-->


