<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Results</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/animate.css">
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<link rel="stylesheet" type="text/css" href="assets/css/li-scroller.css">
<link rel="stylesheet" type="text/css" href="assets/css/slick.css">
<link rel="stylesheet" type="text/css"
	href="assets/css/jquery.fancybox.css">
<link rel="stylesheet" type="text/css" href="assets/css/theme.css">
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<h1>Search Results</h1>
	<section id="contentSection">
		<div class="row">
			<div class="col-lg-8 col-md-8 col-sm-8">
				<div class="left_content">
					<div class="single_post_content">
						<h2>
							<span>Search results</span>
						</h2>
						<div class="single_post_content_left">
							<ul class="business_catgnav  wow fadeInDown">
								<c:forEach var="searchResult" items="${searchResults}">
									<li>
										<figure class="bsbig_fig">
											<a
												href="article?action=view-article&articleId=${searchResult.articleId}"
												class="featured_img"> <img alt=""
												src="${searchResult.coverImage.imagePath}"> <span
												class="overlay"></span>
											</a>
											<figcaption>
												<a
													href="article?action=view-article&articleId=${searchResult.articleId}">${searchResult.title}</a>
											</figcaption>
										</figure>
										
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>