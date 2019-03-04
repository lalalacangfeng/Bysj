<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>carousel</title>

<style>
#MyCarousel {
	top: 1.7rem;
}
</style>
</head>

<body>
	<!--Carousel 图片轮播 -->
	<c:forEach items="${menus }" var="menu">
		<c:choose>
			<c:when test="${menu.id != 0 && not empty menu.childPicts }">
				<div id="MyCarousel" class="carousel slide">
					<ol class="carousel-indicators">
						<li data-target="#MyCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#MyCarousel" data-slide-to="1"></li>
						<li data-target="#MyCarousel" data-slide-to="2"></li>
						<li data-target="#MyCarousel" data-slide-to="3"></li>
						<li data-target="#MyCarousel" data-slide-to="4"></li>
					</ol>
					<!-- Carousel items -->
					<div class="carousel-inner">
	
						<div class="active item">
							<img src="images/30.jpg" alt="30" />

							<div class="container">
								<div class="carousel-caption">
									<h2>This is a test</h2>

									<p class="lead">ohoohohooohohohasdfhaslfnkksla,asdjfl;fjsdal
										asdklfjalsfjla;skdjksal;fjlsdakfj.</p>
								</div>
							</div>
						</div>

						<div class="item">
							<img src="images/30.jpg" alt="30" />

							<div class="container">
								<div class="carousel-caption">
									<h2>This is a test</h2>

									<p class="lead">ohoohohooohohohasdfhaslfnkksla,asdjfl;fjsdal
										asdklfjalsfjla;skdjksal;fjlsdakfj.</p>
								</div>
							</div>
						</div>

					</div>

					<!-- Carousel nav -->
					<a class="carousel-control left" href="#MyCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span>‹
					</a> <a class="carousel-control right" href="#MyCarousel"
						data-slide="next">› <span
						class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</c:when>
		</c:choose>
	</c:forEach>
	<!--END CAROUSEL-->

	

</body>
</html>
