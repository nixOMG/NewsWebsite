<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>NewsFeed</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
<!--[if lt IE 9]>
<script src="assets/js/html5shiv.min.js"></script>
<script src="assets/js/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div id="preloader">
		<div id="status">&nbsp;</div>
	</div>
	<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
	<div class="container">
		<header id="header">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="header_top">


						<div class="header_top_right">
							<form action="article" method="get"
								class="form-inline my-2 my-lg-0 px-4 align-self-center d-flex">
								<input type="hidden" name="action" value="search-article" /> <input
									class="form-control mr-sm-2" name="searchTerm" type="search"
									placeholder="Search" aria-label="Search">
								<button class="btn btn-outline-light my-2 my-sm-0 mx-3"
									type="submit">Search</button>
							</form>
							<p>Friday, December 05, 2045</p>
							<c:choose>
								<c:when test="${loggedInUser ne null}">
									<div class="dropdown bg-dark text-light">
										<a
											style="background-color: gray; border-radius: 5px; color: white; width: 130px; height: 33px; display: inline-block;"
											class="btn btn-secondary dropdown-toggle mx-3"
											data-bs-toggle="dropdown" aria-expanded="false"> <span
											style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
												Hello, ${loggedInUser.getUsername()}! </span> <i
											class="fa fa-caret-down"></i>
										</a>

										<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
											<li><a class="dropdown-item" href="manage-user-info">Account</a>
											</li>
											<li><a class="dropdown-item"
												href="manage-user-info?action=get-user-favourite-articles">Favourite</a>
											</li>
											<c:if test="${loggedInUser.getRole().getRoleId()==4}">
												<li><a class="dropdown-item"
													href="writer-manage-articles">Writer's Page</a></li>
											</c:if>
											<c:if test="${loggedInUser.getRole().getRoleId()==5}">
												<li><a class="dropdown-item"
													href="editor-manage-articles">Editor's Page</a></li>
												<li><a class="dropdown-item" href="manage-category">Manage
														Category</a></li>
											</c:if>
											<c:if test="${loggedInUser.getRole().getRoleId()==6}">
												<li><a class="dropdown-item" href="manage-users">Admin's
														Page</a></li>
												<li><a class="dropdown-item" href="manage-category">Manage
														Category</a></li>
											</c:if>
											<li>
												<hr class="dropdown-divider">
											</li>
											<li><a class="dropdown-item" href="home?action=sign-out">Sign
													out</a></li>
											<li style="display: none;">
												    <a class="dropdown-item" href="manage-user-role"></a>
												</li>
										</ul>
									</div>
								</c:when>
								<c:otherwise>
									<a href="home?action=sign-in"
										class="nav-icon position-relative text-decoration-none"> <i
										class="fa fa-fw fa-user text-light mr-3"></i> <span
										class="position-absolute top-0 left-100 translate-middle">Login/Sign
											up</span>
									</a>
								</c:otherwise>
							</c:choose>

						</div>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="header_bottom">
						<div class="logo_area">
							<a href="home" class="logo"><img src="images/logo.jpg" alt=""></a>
						</div>
						<div class="add_banner">
							<a href="#"><img src="images/addbanner_728x90_V1.jpg" alt=""></a>
						</div>
					</div>
				</div>
			</div>
		</header>
		<section id="navArea">
			<nav class="navbar navbar-inverse" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav main_nav">
					    <li class="active"><a href="home"><span class="fa fa-home desktop-home"></span><span class="mobile-show">Home</span></a></li>
					    <c:forEach var="category" items="${categories}">
					        <c:choose>
					            <c:when test="${not empty category.children}">
					                <li class="dropdown">
					                    <a href="article?action=get-article-by-category&categoryId=${category.categoryId}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
					                        ${category.description}
					                    </a>
					                    <ul class="dropdown-menu" role="menu">
					                        <c:forEach var="child" items="${category.children}">
					                            <li><a href="article?action=get-article-by-category&categoryId=${child.categoryId}">${child.description}</a></li>
					                        </c:forEach>
					                    </ul>
					                </li>
					            </c:when>
					            <c:otherwise>
					                <li><a href="article?action=get-article-by-category&categoryId=${category.categoryId}">${category.description}</a></li>
					            </c:otherwise>
					        </c:choose>
					    </c:forEach>
					    <li><a href="pages/contact.html">Contact Us</a></li>
					    <li><a href="pages/404.html">404 Page</a></li>
					</ul>

				</div>
			</nav>
		</section>
		<section id="newsSection">
			<div class="row">
				<div class="col-lg-12 col-md-12">
					<div class="latest_newsarea">
						<span>Latest News</span>
						<ul id="ticker01" class="news_sticker">
							<c:forEach var="lastestArticle" items="${lastestArticles}">
								<li><a
									href="article?action=view-article&articleId=${lastestArticle.articleId}">
										<img alt="" src="${lastestArticle.coverImage.imagePath}">${lastestArticle.title}
								</a></li>
							</c:forEach>
							<li><a href="#"><img src="images/news_thumbnail3.jpg"
									alt="">My First News Item</a></li>
							<li><a href="#"><img src="images/news_thumbnail3.jpg"
									alt="">My Second News Item</a></li>
						</ul>
						<div class="social_area">
							<ul class="social_nav">
								<li class="facebook"><a href="#"></a></li>
								<li class="twitter"><a href="#"></a></li>
								<li class="flickr"><a href="#"></a></li>
								<li class="pinterest"><a href="#"></a></li>
								<li class="googleplus"><a href="#"></a></li>
								<li class="vimeo"><a href="#"></a></li>
								<li class="youtube"><a href="#"></a></li>
								<li class="mail"><a href="#"></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section id="sliderSection">
			<div class="row">
				<div class="col-lg-8 col-md-8 col-sm-8">
					<div class="slick_slider">
						<c:forEach var="article" items="${articles}">
							<div class="single_iteam">
								<a
									href="article?action=view-article&articleId=${article.articleId}">
									<img src="${article.coverImage.imagePath}" alt="">
								</a>
								<div class="slider_article">
									<h2>
										<a class="slider_tittle"
											href="article?action=view-article&articleId=${article.articleId}">${article.title}</a>
									</h2>
									<p>${article.content}...</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-4">
					<div class="latest_post">
						<h2>
							<span>Latest post</span>
						</h2>
						<div class="latest_post_container">
							<div id="prev-button">
								<i class="fa fa-chevron-up"></i>
							</div>
							<ul class="latest_postnav">
								<c:forEach var="lastestArticle" items="${lastestArticles}">
									<li>
										<div class="media">
											<a
												href="article?action=view-article&articleId=${lastestArticle.articleId}"
												class="media-left"> <img alt=""
												src="${lastestArticle.coverImage.imagePath}">
											</a>
											<div class="media-body">
												<a
													href="article?action=view-article&articleId=${lastestArticle.articleId}"
													class="catg_title"> ${lastestArticle.title}</a>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
							<div id="next-button">
								<i class="fa  fa-chevron-down"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section id="contentSection">
			<div class="row">
				<div class="col-lg-8 col-md-8 col-sm-8">
					<div class="left_content">
						<div class="single_post_content">
							<h2>
								<span>Business</span>
							</h2>
							<div class="single_post_content_left">
								<ul class="business_catgnav  wow fadeInDown">
									<li>
										<figure class="bsbig_fig">
											<a href="pages/single_page.html" class="featured_img"> <img
												alt="" src="images/featured_img1.jpg"> <span
												class="overlay"></span>
											</a>
											<figcaption>
												<a href="pages/single_page.html">Proin rhoncus consequat
													nisl eu ornare mauris</a>
											</figcaption>
											<p>Nunc tincidunt, elit non cursus euismod, lacus augue
												ornare metus, egestas imperdiet nulla nisl quis mauris.
												Suspendisse a phare...</p>
										</figure>
									</li>
								</ul>
							</div>
							<div class="single_post_content_right">
								<ul class="spost_nav">
									<li>
										<div class="media wow fadeInDown">
											<a href="pages/single_page.html" class="media-left"> <img
												alt="" src="images/post_img1.jpg">
											</a>
											<div class="media-body">
												<a href="pages/single_page.html" class="catg_title">
													Aliquam malesuada diam eget turpis varius 1</a>
											</div>
										</div>
									</li>
									<li>
										<div class="media wow fadeInDown">
											<a href="pages/single_page.html" class="media-left"> <img
												alt="" src="images/post_img2.jpg">
											</a>
											<div class="media-body">
												<a href="pages/single_page.html" class="catg_title">
													Aliquam malesuada diam eget turpis varius 2</a>
											</div>
										</div>
									</li>
									<li>
										<div class="media wow fadeInDown">
											<a href="pages/single_page.html" class="media-left"> <img
												alt="" src="images/post_img1.jpg">
											</a>
											<div class="media-body">
												<a href="pages/single_page.html" class="catg_title">
													Aliquam malesuada diam eget turpis varius 3</a>
											</div>
										</div>
									</li>
									<li>
										<div class="media wow fadeInDown">
											<a href="pages/single_page.html" class="media-left"> <img
												alt="" src="images/post_img2.jpg">
											</a>
											<div class="media-body">
												<a href="pages/single_page.html" class="catg_title">
													Aliquam malesuada diam eget turpis varius 4</a>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
						<div class="fashion_technology_area">
							<div class="fashion">
								<div class="single_post_content">
									<h2>
										<span>Fashion</span>
									</h2>
									<ul class="business_catgnav wow fadeInDown">
										<li>
											<figure class="bsbig_fig">
												<a href="pages/single_page.html" class="featured_img"> <img
													alt="" src="images/featured_img2.jpg"> <span
													class="overlay"></span>
												</a>
												<figcaption>
													<a href="pages/single_page.html">Proin rhoncus
														consequat nisl eu ornare mauris</a>
												</figcaption>
												<p>Nunc tincidunt, elit non cursus euismod, lacus augue
													ornare metus, egestas imperdiet nulla nisl quis mauris.
													Suspendisse a phare...</p>
											</figure>
										</li>
									</ul>
									<ul class="spost_nav">
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img1.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 1</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img2.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 2</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img1.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 3</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img2.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 4</a>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="technology">
								<div class="single_post_content">
									<h2>
										<span>Technology</span>
									</h2>
									<ul class="business_catgnav">
										<li>
											<figure class="bsbig_fig wow fadeInDown">
												<a href="pages/single_page.html" class="featured_img"> <img
													alt="" src="images/featured_img3.jpg"> <span
													class="overlay"></span>
												</a>
												<figcaption>
													<a href="pages/single_page.html">Proin rhoncus
														consequat nisl eu ornare mauris</a>
												</figcaption>
												<p>Nunc tincidunt, elit non cursus euismod, lacus augue
													ornare metus, egestas imperdiet nulla nisl quis mauris.
													Suspendisse a phare...</p>
											</figure>
										</li>
									</ul>
									<ul class="spost_nav">
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img1.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 1</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img2.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 2</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img1.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 3</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img2.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 4</a>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="single_post_content">
							<h2>
								<span>Photography</span>
							</h2>
							<ul class="photograph_nav  wow fadeInDown">
								<li>
									<div class="photo_grid">
										<figure class="effect-layla">
											<a class="fancybox-buttons" data-fancybox-group="button"
												href="images/photograph_img2.jpg"
												title="Photography Ttile 1"> <img
												src="images/photograph_img2.jpg" alt="" /></a>
										</figure>
									</div>
								</li>
								<li>
									<div class="photo_grid">
										<figure class="effect-layla">
											<a class="fancybox-buttons" data-fancybox-group="button"
												href="images/photograph_img3.jpg"
												title="Photography Ttile 2"> <img
												src="images/photograph_img3.jpg" alt="" />
											</a>
										</figure>
									</div>
								</li>
								<li>
									<div class="photo_grid">
										<figure class="effect-layla">
											<a class="fancybox-buttons" data-fancybox-group="button"
												href="images/photograph_img4.jpg"
												title="Photography Ttile 3"> <img
												src="images/photograph_img4.jpg" alt="" />
											</a>
										</figure>
									</div>
								</li>
								<li>
									<div class="photo_grid">
										<figure class="effect-layla">
											<a class="fancybox-buttons" data-fancybox-group="button"
												href="images/photograph_img4.jpg"
												title="Photography Ttile 4"> <img
												src="images/photograph_img4.jpg" alt="" />
											</a>
										</figure>
									</div>
								</li>
								<li>
									<div class="photo_grid">
										<figure class="effect-layla">
											<a class="fancybox-buttons" data-fancybox-group="button"
												href="images/photograph_img2.jpg"
												title="Photography Ttile 5"> <img
												src="images/photograph_img2.jpg" alt="" />
											</a>
										</figure>
									</div>
								</li>
								<li>
									<div class="photo_grid">
										<figure class="effect-layla">
											<a class="fancybox-buttons" data-fancybox-group="button"
												href="images/photograph_img3.jpg"
												title="Photography Ttile 6"> <img
												src="images/photograph_img3.jpg" alt="" />
											</a>
										</figure>
									</div>
								</li>
							</ul>
						</div>
						<div class="single_post_content">
							<h2>
								<span>Games</span>
							</h2>
							<div class="single_post_content_left">
								<ul class="business_catgnav">
									<li>
										<figure class="bsbig_fig  wow fadeInDown">
											<a class="featured_img" href="pages/single_page.html"> <img
												src="images/featured_img1.jpg" alt=""> <span
												class="overlay"></span>
											</a>
											<figcaption>
												<a href="pages/single_page.html">Proin rhoncus consequat
													nisl eu ornare mauris</a>
											</figcaption>
											<p>Nunc tincidunt, elit non cursus euismod, lacus augue
												ornare metus, egestas imperdiet nulla nisl quis mauris.
												Suspendisse a phare...</p>
										</figure>
									</li>
								</ul>
							</div>
							<div class="single_post_content_right">
								<ul class="spost_nav">
									<li>
										<div class="media wow fadeInDown">
											<a href="pages/single_page.html" class="media-left"> <img
												alt="" src="images/post_img1.jpg">
											</a>
											<div class="media-body">
												<a href="pages/single_page.html" class="catg_title">
													Aliquam malesuada diam eget turpis varius 1</a>
											</div>
										</div>
									</li>
									<li>
										<div class="media wow fadeInDown">
											<a href="pages/single_page.html" class="media-left"> <img
												alt="" src="images/post_img2.jpg">
											</a>
											<div class="media-body">
												<a href="pages/single_page.html" class="catg_title">
													Aliquam malesuada diam eget turpis varius 2</a>
											</div>
										</div>
									</li>
									<li>
										<div class="media wow fadeInDown">
											<a href="pages/single_page.html" class="media-left"> <img
												alt="" src="images/post_img1.jpg">
											</a>
											<div class="media-body">
												<a href="pages/single_page.html" class="catg_title">
													Aliquam malesuada diam eget turpis varius 3</a>
											</div>
										</div>
									</li>
									<li>
										<div class="media wow fadeInDown">
											<a href="pages/single_page.html" class="media-left"> <img
												alt="" src="images/post_img2.jpg">
											</a>
											<div class="media-body">
												<a href="pages/single_page.html" class="catg_title">
													Aliquam malesuada diam eget turpis varius 4</a>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-4">
					<aside class="right_content">
						<div class="single_sidebar">
							<h2>
								<span>Popular Post</span>
							</h2>
							<ul class="spost_nav">
								<c:forEach var="sortedArticleByView"
									items="${sortedArticlesByView}">
									<li>
										<div class="media wow fadeInDown">
											<a
												href="article?action=view-article&articleId=${sortedArticleByView.articleId}"
												class="media-left"> <img alt=""
												src="${sortedArticleByView.coverImage.imagePath}">
											</a>
											<div class="media-body">
												<a
													href="article?action=view-article&articleId=${sortedArticleByView.articleId}"
													class="catg_title">${sortedArticleByView.title}</a>
											</div>
										</div>
									</li>
								</c:forEach>
								<li>
									<div class="media wow fadeInDown">
										<a href="pages/single_page.html" class="media-left"> <img
											alt="" src="images/post_img2.jpg">
										</a>
										<div class="media-body">
											<a href="pages/single_page.html" class="catg_title">
												Aliquam malesuada diam eget turpis varius 2</a>
										</div>
									</div>
								</li>
								<li>
									<div class="media wow fadeInDown">
										<a href="pages/single_page.html" class="media-left"> <img
											alt="" src="images/post_img1.jpg">
										</a>
										<div class="media-body">
											<a href="pages/single_page.html" class="catg_title">
												Aliquam malesuada diam eget turpis varius 3</a>
										</div>
									</div>
								</li>
								<li>
									<div class="media wow fadeInDown">
										<a href="pages/single_page.html" class="media-left"> <img
											alt="" src="images/post_img2.jpg">
										</a>
										<div class="media-body">
											<a href="pages/single_page.html" class="catg_title">
												Aliquam malesuada diam eget turpis varius 4</a>
										</div>
									</div>
								</li>
							</ul>
						</div>
						<div class="single_sidebar">
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"><a href="#category"
									aria-controls="home" role="tab" data-toggle="tab">Category</a></li>
								<li role="presentation"><a href="#video"
									aria-controls="profile" role="tab" data-toggle="tab">Video</a></li>
								<li role="presentation"><a href="#comments"
									aria-controls="messages" role="tab" data-toggle="tab">Comments</a></li>
							</ul>
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="category">
									<ul>
										<li class="cat-item"><a href="#">Sports</a></li>
										<li class="cat-item"><a href="#">Fashion</a></li>
										<li class="cat-item"><a href="#">Business</a></li>
										<li class="cat-item"><a href="#">Technology</a></li>
										<li class="cat-item"><a href="#">Games</a></li>
										<li class="cat-item"><a href="#">Life &amp; Style</a></li>
										<li class="cat-item"><a href="#">Photography</a></li>
									</ul>
								</div>
								<div role="tabpanel" class="tab-pane" id="video">
									<div class="vide_area">
										<iframe width="100%" height="250"
											src="http://www.youtube.com/embed/h5QWbURNEpA?feature=player_detailpage"
											frameborder="0" allowfullscreen></iframe>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane" id="comments">
									<ul class="spost_nav">
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img1.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 1</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img2.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 2</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img1.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 3</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="pages/single_page.html" class="media-left"> <img
													alt="" src="images/post_img2.jpg">
												</a>
												<div class="media-body">
													<a href="pages/single_page.html" class="catg_title">
														Aliquam malesuada diam eget turpis varius 4</a>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="single_sidebar wow fadeInDown">
							<h2>
								<span>Sponsor</span>
							</h2>
							<a class="sideAdd" href="#"><img src="images/add_img.jpg"
								alt=""></a>
						</div>
						<div class="single_sidebar wow fadeInDown">
							<h2>
								<span>Category Archive</span>
							</h2>
							<select class="catgArchive">
								<option>Select Category</option>
								<option>Life styles</option>
								<option>Sports</option>
								<option>Technology</option>
								<option>Treads</option>
							</select>
						</div>
						<div class="single_sidebar wow fadeInDown">
							<h2>
								<span>Links</span>
							</h2>
							<ul>
								<li><a href="#">Blog</a></li>
								<li><a href="#">Rss Feed</a></li>
								<li><a href="#">Login</a></li>
								<li><a href="#">Life &amp; Style</a></li>
							</ul>
						</div>
					</aside>
				</div>
			</div>
		</section>
		<footer id="footer">
			<div class="footer_top">
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="footer_widget wow fadeInLeftBig">
							<h2>Flickr Images</h2>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="footer_widget wow fadeInDown">
							<h2>Tag</h2>
							<ul class="tag_nav">
								<li><a href="#">Games</a></li>
								<li><a href="#">Sports</a></li>
								<li><a href="#">Fashion</a></li>
								<li><a href="#">Business</a></li>
								<li><a href="#">Life &amp; Style</a></li>
								<li><a href="#">Technology</a></li>
								<li><a href="#">Photo</a></li>
								<li><a href="#">Slider</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="footer_widget wow fadeInRightBig">
							<h2>Contact</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
							<address>Perfect News,1238 S . 123 St.Suite 25 Town
								City 3333,USA Phone: 123-326-789 Fax: 123-546-567</address>
						</div>
					</div>
				</div>
			</div>
			<div class="footer_bottom">
				<p class="copyright">
					Copyright &copy; 2045 <a href="home">NewsFeed</a>
				</p>
				<p class="developer">Developed By Wpfreeware</p>
			</div>
		</footer>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/wow.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="./assets/js/bootstrap.bundle.min.js"></script>
	<script src="https://kit.fontawesome.com/72fe31d212.js"
		crossorigin="anonymous"></script>
	<script src="assets/js/slick.min.js"></script>
	<script src="assets/js/jquery.li-scroller.1.0.js"></script>
	<script src="assets/js/jquery.newsTicker.min.js"></script>
	<script src="assets/js/jquery.fancybox.pack.js"></script>
	<script src="assets/js/custom.js"></script>
</body>
</html>
