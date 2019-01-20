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

				<form action="" method="post" class="navbar-form navbar-right"
					role="search">
					<div class="form-group">
						<input type="text" class="form-control" autocomplete="on"
							placeholder="小黄人">
					</div>
					<button type="button" class="btn btn-default">
						<span aria-hidden="true"><img src="img/btn_search.png"
							alt="btn_search" style="width: 25px;" /></span>
					</button>
				</form>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${username != null }">
						<!-- 用户存在 -->
						<li role="presentation"><a> ${username } </a></li>
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
				<script src="js/head.js"></script>

			</ul>
		</nav>
	</div>
</header>
<!--END NAVBAR-->


