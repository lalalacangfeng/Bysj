<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>container.jsp</title>
<link href="js/plugins/fancybox/jquery.fancybox.css"
	rel="stylesheet">

</head>

<body>
	<div class="container">
		<!--前5个内容-->
		<c:forEach items="${menus }" var="menu">
			<c:choose>
				<c:when test="${menu.id != 0 && not empty menu.childMenus }">
					<div class="container ${menu.name }Entries">
						<div class="navbar navbar-default" style="width: 86rem;">
							<ul class="nav nav-pills">
								<li role="presentation" style="top: -1rem"><h1>${menu.name }</h1></li>
								<li class="navbar-right"></li>
								<c:forEach items="${menu.childMenus }" var="secondChild">
									<li role="presentation" class="navbar-right"
										style="top: 1.6rem"><a href="#${secondChild.id }"
										data-toggle="tab"> ${secondChild.name }</a></li>
								</c:forEach>
								<li role="presentation" style="top: 1.6rem"
									class="active navbar-right"><a data-toggle="tab"
									href="#${menu.id }"> 全部</a></li>
							</ul>
						</div>

						<div class="tab-content">
							<div id="${menu.id }" class="tab-pane active">
								<c:set var="isDone" value="3" scope="page"></c:set>
								<c:set var="chance" value="0" scope="page"></c:set>
								<c:forEach items="${menu.childMenus }" var="secondChild">
									<c:forEach items="${secondChild.childArticles }" var="article">
										<c:if test="${chance < isDone }">
											<div class="row">
												<div class="news-item">
													<c:if test="${article.src != null }">
														<img class="news-item-image" src="${article.src }">
													</c:if>
													<div class="item-text">
														<h4 class="new-item-title">${article.title }</h4>
														<p class="new-item-intro">${article.profile }</p>
														<a href="fm/article?action=show&nid=${article.nid}"
															class="button button-pill button-tiny">查看全文</a>
														<c:set var="chance" value="${chance+1 }" scope="page"></c:set>
														<c:out value="${chance }"></c:out>
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</c:forEach>

							</div>
							<c:forEach items="${menu.childMenus }" var="secondChild">
								<div id="${secondChild.id }" class="tab-pane">
									<c:set var="isDone" value="3" scope="page"></c:set>
									<c:set var="chance" value="0" scope="page"></c:set>
									<c:forEach items="${secondChild.childArticles }" var="article">
										<c:if test="${chance < isDone }">
											<div class="row">
												<div class="news-item">
													<c:if test="${article.src != null }">
														<img class="news-item-image" src="${article.src }">
													</c:if>
													<div class="item-text">
														<h4 class="new-item-title">${article.title }</h4>
														<p class="new-item-intro">${article.profile }</p>
														<a href="article?action=show&nid=${article.nid}"
															class="button button-pill button-tiny">查看全文</a>
														<c:set var="chance" value="${chance+1 }" scope="page"></c:set>
														<c:out value="${chance }"></c:out>
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</c:forEach>
						</div>

					</div>
				</c:when>
				<c:when test="${menu.id != 0 && not empty menu.childPicts }">
					<div class="container ${menu.name }Entries">
						<div class="navbar navbar-default" style="width: 86rem;">
							<ul class="nav nav-pills" id="infoTab">
								<li role="presentation" style="top: -1rem"><h1>${menu.name }</h1></li>
								<li class="navbar-right"></li>
								<li style="top: 1.7rem" class="navbar-right"><a href="${menu.id }.fm"
									role="presentation"> 更多...</a></li>
							</ul>
						</div>
						<div class="row">
							<c:set var="isDone" value="3" scope="page"></c:set>
							<c:set var="chance" value="0" scope="page"></c:set>
							<c:forEach items="${menu.childPicts }" var="pict">
								<c:if test="${chance == 0 }">
									<div class="col-sm-12 col-md-6 col-lg-6">
										<a href="/Dmjd/${pict.src }" class="thumbnail fancybox" style="height: 23.9rem;"> <img
											src="/Dmjd/${pict.src }" alt="#" style="height: 23rem">
										</a>
										<c:set var="chance" value="${chance+1 }" scope="page"></c:set>
									</div>
								</c:if>
								<c:if test="${chance < isDone && chance > 0 }">
									<div class="col-sm-6 col-md-3 col-lg-3">
										<div class="thumbnail">
											<a href="/Dmjd/${pict.src }" class="fancybox"><img src="/Dmjd/${pict.src }" alt="${pict.name }"></a>
											<div class="caption">
												<h3>${pict.name }<br></h3>
												<p>${pict.descript }<br></p>
												<c:set var="chance" value="${chance+1 }" scope="page"></c:set>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</c:when>
				<c:when test="${menu.id != 0 && not empty menu.childVedios }">
					<div class="container ${menu.name }Entries">
						<div class="navbar navbar-default" style="width: 86rem;">
							<ul class="nav nav-pills" id="delicateTab">
								<li role="presentation" style="top: -1rem"><h1>${menu.name }</h1></li>
								<li class="navbar-right"></li>
								<li style="top: 1.7rem" class="navbar-right"><a href="${menu.id }.fm"
									role="presentation"> 更多...</a></li>
							</ul>
						</div>

						<div class="row">
							<c:set var="isDone" value="3" scope="page"></c:set>
							<c:set var="chance" value="0" scope="page"></c:set>
							<c:forEach items="${menu.childVedios }" var="vedio">
								<c:if test="${chance < isDone }">
									<div class="col-sm-6 col-md-3 col-lg-3">
										<a href="fm/vedio?action=show&id=${vedio.id }" class="thumbnail" style="height: 23.9rem;"><img
											src="/Dmjd/${vedio.picture }" alt="#"></a>
		
										<div class="caption">
											<h3 style="text-align: center">${vedio.name }</h3>
										</div>
									</div>
								</c:if>
							</c:forEach>

						</div>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>

		<!--END 前5个内容-->
	</div>



	<script type="text/javascript">
		$(document).ready(function() {
			$('.fancybox').fancybox({
				openEffect : 'none',
				closeEffect : 'none'
			});
		});
	</script>
	<script type="text/javascript">
		$("#containerTab a").click(function(e) {
			$(this).tab("show");
		})
	</script>
</body>
</html>
