<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				
				
			</c:choose>
		</c:forEach>

		<!--END 前5个内容-->
	</div>

</body>

