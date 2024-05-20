<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>NewsFeed | Pages | Single Page</title>
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
						<div class="header_top_left">
							<ul class="top_nav">
								<li><a href="home">Home</a></li>
								<li><a href="#">About</a></li>
								<li><a href="contact.html">Contact</a></li>
							</ul>
						</div>
						<div class="header_top_right">
							<p>Friday, December 05, 2045</p>
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
						<li class="active"><a href="home"><span
								class="fa fa-home desktop-home"></span><span class="mobile-show">Home</span></a></li>
						<c:forEach var="category" items="${categories}">
							<c:choose>
								<c:when test="${not empty category.children}">
									<li class="dropdown"><a
										href="article?action=get-article-by-category&categoryId=${category.categoryId}"
										class="dropdown-toggle" data-toggle="dropdown" role="button"
										aria-expanded="false"> ${category.description} </a>
										<ul class="dropdown-menu" role="menu">
											<c:forEach var="child" items="${category.children}">
												<li><a
													href="article?action=get-article-by-category&categoryId=${child.categoryId}">${child.description}</a></li>
											</c:forEach>
										</ul></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="article?action=get-article-by-category&categoryId=${category.categoryId}">${category.description}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li><a href="contact.html">Contact Us</a></li>
						<li><a href="404.html">404 Page</a></li>
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
		<section id="contentSection">
			<div class="row">
				<div class="col-lg-8 col-md-8 col-sm-8">
					<div class="left_content">
						<div class="single_page">
							<ol class="breadcrumb">
								<li><a href="home">Home</a></li>
								<c:choose>
									<c:when test="${not empty article.category.parent}">
										<li><a
											href="category?categoryId=${article.category.parent.categoryId}">${article.category.parent.description}</a></li>
									</c:when>
								</c:choose>
								<li class="active">${article.category.description}</li>
							</ol>

							<h1>${article.title}</h1>
							<div class="post_commentbox">
								<a href="#"><i class="fa fa-user"></i>${article.user.getUsername()}</a>
								<span><i class="fa fa-calendar"></i>${article.publishTime}</span>
								<a href="#"><i class="fa fa-tags"></i>${article.category.description}</a>
							</div>
							<div class="single_page_content">
								<div style="max-width: 600px">${article.content}</div>
							</div>
							<div class="social_link">
								<ul class="sociallink_nav">
									<li><c:choose>
											<c:when test="${isFavourite}">
												<a
													href="article?action=remove-favourite&articleId=${article.articleId}"
													class="favourite-link" data-favourite="true"> <i
													class="fa fa-heart"></i>
												</a> Unlike
			                </c:when>
											<c:otherwise>
												<a
													href="article?action=add-to-favourite&articleId=${article.articleId}"
													class="favourite-link" data-favourite="false"> <i
													class="fa fa-heart-o"></i>
												</a> Like
			                </c:otherwise>
										</c:choose></li>
								</ul>
							</div>
							<div id="comment-form">
								<h4>Add a Comment</h4>
								<form action="comment?action=add-comment" method="post">
									<input type="hidden" name="articleId"
										value="${article.articleId}" />
									<div>
										<textarea name="content" rows="5" cols="105"
											placeholder="Write your comment here..."></textarea>
									</div>
									<div>
										<button type="submit">Submit</button>
									</div>
								</form>
							</div>
							<h3>Comments</h3>
							<c:forEach var="comment" items="${comments}">
								<div>
									<p>
										<strong>${comment.commentor.username}</strong>:
										${comment.content}
									</p>
									<p>
										<em>${comment.commentTime}</em>
									</p>

									<c:if test="${comment.commentor.userId == loggedInUser.userId}">
										<div style="display: flex; flex-direction: row;">
											<form action="comment?action=edit-comment" method="post">
												<input type="hidden" name="commentId"
													value="${comment.commentId}" />
												<textarea id="textarea-${comment.commentId}" name="content"
													rows="3" placeholder="Write your comment here..."
													style="display: none">${comment.content}</textarea>
												<button type="button" id="editBtn"
													onclick="editComment(${comment.commentId})">Edit</button>
												<button type="submit" id="submitBtn" style="display: none">Submit</button>
											</form>
											<form action="comment?action=delete-comment" method="post"
												onsubmit="return deleteComment(${comment.commentId})">
												<input type="hidden" name="commentId"
													value="${comment.commentId}" />
												<button type="submit" id="submitBtn">Delete</button>
											</form>
										</div>
									</c:if>

								</div>
							</c:forEach>

							<div class="related_post">
								<h2>
									Related Post <i class="fa fa-thumbs-o-up"></i>
								</h2>
								<ul class="spost_nav wow fadeInDown animated">
									<c:forEach var="relatedArticle" items="${relatedArticles}">
										<li>
											<div class="media">
												<a class="media-left"
													href="article?action=view-article&articleId=${relatedArticle.articleId}">
													<img src="${relatedArticle.coverImage.imagePath}" alt="">
												</a>
												<div class="media-body">
													<a class="catg_title"
														href="article?action=view-article&articleId=${relatedArticle.articleId}">${relatedArticle.title}</a>
												</div>
											</div>
										</li>
									</c:forEach>
									<li>
										<div class="media">
											<a class="media-left" href="single_page.html"> <img
												src="images/post_img2.jpg" alt="">
											</a>
											<div class="media-body">
												<a class="catg_title" href="single_page.html"> Aliquam
													malesuada diam eget turpis varius</a>
											</div>
										</div>
									</li>
									<li>
										<div class="media">
											<a class="media-left" href="single_page.html"> <img
												src="images/post_img1.jpg" alt="">
											</a>
											<div class="media-body">
												<a class="catg_title" href="single_page.html"> Aliquam
													malesuada diam eget turpis varius</a>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<nav class="nav-slit">
					<a class="prev" href="#"> <span class="icon-wrap"><i
							class="fa fa-angle-left"></i></span>
						<div>
							<h3>City Lights</h3>
							<img src="images/post_img1.jpg" alt="" />
						</div>
					</a> <a class="next" href="#"> <span class="icon-wrap"><i
							class="fa fa-angle-right"></i></span>
						<div>
							<h3>Street Hills</h3>
							<img src="images/post_img1.jpg" alt="" />
						</div>
					</a>
				</nav>
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
										<a href="single_page.html" class="media-left"> <img alt=""
											src="images/post_img2.jpg">
										</a>
										<div class="media-body">
											<a href="single_page.html" class="catg_title"> Aliquam
												malesuada diam eget turpis varius 2</a>
										</div>
									</div>
								</li>
								<li>
									<div class="media wow fadeInDown">
										<a href="single_page.html" class="media-left"> <img alt=""
											src="images/post_img1.jpg">
										</a>
										<div class="media-body">
											<a href="single_page.html" class="catg_title"> Aliquam
												malesuada diam eget turpis varius 3</a>
										</div>
									</div>
								</li>
								<li>
									<div class="media wow fadeInDown">
										<a href="single_page.html" class="media-left"> <img alt=""
											src="images/post_img2.jpg">
										</a>
										<div class="media-body">
											<a href="single_page.html" class="catg_title"> Aliquam
												malesuada diam eget turpis varius 4</a>
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
												<a href="single_page.html" class="media-left"> <img
													alt="" src="images/post_img1.jpg">
												</a>
												<div class="media-body">
													<a href="single_page.html" class="catg_title"> Aliquam
														malesuada diam eget turpis varius 1</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="single_page.html" class="media-left"> <img
													alt="" src="images/post_img2.jpg">
												</a>
												<div class="media-body">
													<a href="single_page.html" class="catg_title"> Aliquam
														malesuada diam eget turpis varius 2</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="single_page.html" class="media-left"> <img
													alt="" src="images/post_img1.jpg">
												</a>
												<div class="media-body">
													<a href="single_page.html" class="catg_title"> Aliquam
														malesuada diam eget turpis varius 3</a>
												</div>
											</div>
										</li>
										<li>
											<div class="media wow fadeInDown">
												<a href="single_page.html" class="media-left"> <img
													alt="" src="images/post_img2.jpg">
												</a>
												<div class="media-body">
													<a href="single_page.html" class="catg_title"> Aliquam
														malesuada diam eget turpis varius 4</a>
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
	<script src="assets/js/slick.min.js"></script>
	<script src="assets/js/jquery.li-scroller.1.0.js"></script>
	<script src="assets/js/jquery.newsTicker.min.js"></script>
	<script src="assets/js/jquery.fancybox.pack.js"></script>
	<script src="assets/js/custom.js"></script>
	<script>
$(document).ready(function() {
    $('.favourite-link').click(function(event) {
        event.preventDefault();
        var link = $(this);
        var href = link.attr('href');
        var isFavourite = link.data('favourite') === true;

        $.ajax({
            url: href,
            type: 'GET',
            success: function() {
                if (isFavourite) {
                    link.find('i').removeClass('fa-heart').addClass('fa-heart-o');
                    link.attr('href', href.replace('remove-favourite', 'add-to-favourite'));
                    link.data('favourite', false);
                    link.parent().html('<a href="' + link.attr('href') + '" class="favourite-link" data-favourite="false"><i class="fa fa-heart-o"></i></a> Like');
                } else {
                    link.find('i').removeClass('fa-heart-o').addClass('fa-heart');
                    link.attr('href', href.replace('add-to-favourite', 'remove-favourite'));
                    link.data('favourite', true);
                    link.parent().html('<a href="' + link.attr('href') + '" class="favourite-link" data-favourite="true"><i class="fa fa-heart"></i></a> Unlike');
                }
            },
            error: function() {
                alert('Error while updating favourite status.');
            }
        });
    });
});


</script>
	<script>
function editComment(commentId) {
    var textarea = document.getElementById('textarea-' + commentId);
    textarea.style.display = 'block';
    
    var submitBtn=document.getElementById('submitBtn');
    submitBtn.style.display='block';
    
    var editBtn=document.getElementById('editBtn');
    editBtn.style.display='none'
}
function deleteComment(commentId){
	var r = confirm("Bạn có chắc là muốn xóa bình luận này không?");
    if (r == true) {
        return true;
    } else {
        return false;
    }
}
</script>
</body>
</html>