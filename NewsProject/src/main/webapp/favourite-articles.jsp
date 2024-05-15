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
	<h1>Favourite Articles</h1>
	<section id="contentSection">
		<div class="row">
			<div class="col-lg-8 col-md-8 col-sm-8">
				<div class="left_content">
					<div class="single_post_content">
						<h2>
							<span>Favourite Articles</span>
						</h2>
						<div class="single_post_content_left">
							<ul class="business_catgnav  wow fadeInDown">
								<c:forEach var="favArticle" items="${favArticles}">
									<li>
										<figure class="bsbig_fig">
											<a
												href="article?action=view-article&articleId=${favArticle.articleId}"
												class="featured_img"> <img alt=""
												src="${favArticle.coverImage.imagePath}"> <span
												class="overlay"></span>
											</a>
											<figcaption>
												<a
													href="article?action=view-article&articleId=${favArticle.articleId}">${favArticle.title}</a>
											</figcaption>
										</figure>
										<button class="deleteBtn"
											onclick="window.location.href='article?action=remove-favourite&articleId=${favArticle.articleId}'">Delete
											from Favourite</button>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript" src="./assets/js/jquery.min.js"></script>
</body>
</html>