<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>NewsFeed | Editor | Manage Article</title>
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


<style type="text/css">
.pagination-container {
    margin: 40px auto;
}
.pagination {
    display: flex;
    list-style: none;
    padding: 0;
    justify-content: center;
}

.pagination li {
    margin: 0 5px;
}

.pagination a {
    display: block;
    padding: 8px 12px;
    color: #007bff;
    text-decoration: none;
    border: 1px solid #007bff;
    border-radius: 4px;
}

.pagination a:hover,
.pagination li.active a {
    background-color: #6c757d;
    color: #fff;
}

/* css giới hạn độ rộng của cột trong bảng */
.w3-table-all td:nth-child(3),
    .w3-table-all td:nth-child(4) {
        max-width: 120px; /* Giới hạn chiều rộng của cột "Name" và "Description" */
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
}
.w3-table-all th,
    .w3-table-all td {
        font-size: 13px; /* Đặt kích thước chữ cho cả th và td */
    }

.btn-add-article {
    display: inline-block;
    width: 110px;
    height: 40px;
    text-decoration: none;
    background-color: #6c757d;
    color: white;
    box-sizing: border-box;
    padding: 10px;
    border: none;
    border-radius: 5px; /* Đặt giá trị bán kính bo tròn góc */
}

.btn-add-article:hover {
    background-color: rgba(108, 117, 125, 0.5); /* Màu nền trong suốt khi di chuột vào */
    opacity: 1; /* Không trong suốt */
    cursor: pointer;
}

</style>
</head>

<body>
	<div id="preloader">
		<div id="status">&nbsp;</div>
	</div>
	<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
	<div class="container">
		<!-- Changed container to fluid -->
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
						<li><a href="#">Technology</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">Mobile</a>
<!-- 							<ul class="dropdown-menu" role="menu"> -->
<!-- 								<li><a href="#">Android</a></li> -->
<!-- 								<li><a href="#">Samsung</a></li> -->
<!-- 								<li><a href="#">Nokia</a></li> -->
<!-- 								<li><a href="#">Walton Mobile</a></li> -->
<!-- 								<li><a href="#">Sympony</a></li> -->
<!-- 							</ul></li> -->
						<li><a href="#">Laptops</a></li>
						<li><a href="#">Tablets</a></li>
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
							<li><a href="#"><img src="images/news_thumbnail3.jpg"
									alt="">My First News Item</a></li>
							<li><a href="#"><img src="images/news_thumbnail3.jpg"
									alt="">My Second News Item</a></li>
							<li><a href="#"><img src="images/news_thumbnail3.jpg"
									alt="">My Third News Item</a></li>
							<li><a href="#"><img src="images/news_thumbnail3.jpg"
									alt="">My Four News Item</a></li>
							<li><a href="#"><img src="images/news_thumbnail3.jpg"
									alt="">My Five News Item</a></li>
							<li><a href="#"><img src="images/news_thumbnail3.jpg"
									alt="">My Six News Item</a></li>
							<li><a href="#"><img src="images/news_thumbnail3.jpg"
									alt="">My Seven News Item</a></li>
							<li><a href="#"><img src="images/news_thumbnail3.jpg"
									alt="">My Eight News Item</a></li>
							<li><a href="#"><img src="images/news_thumbnail2.jpg"
									alt="">My Nine News Item</a></li>
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


		<div class="container">

			<div class="row justify-content-end mt-3">
        <div class="col-md-4">
            <form id="statusForm" action="editor-manage-articles" method="get" class="form-inline">
                <input type="hidden" name="action" value="get-articles-by-status" />
                <label for="status" class="mr-2">Filter by Status:</label>
                <select id="" name="status" class="form-control">
                    <c:choose>
                        <c:when test="${status == 'pending'}">
                            <option value="pending" selected>Pending</option>
                            <option value="approved">Approved</option>
                            <option value="declined">Declined</option>
                            <option value="all">All</option>
                        </c:when>
                        <c:when test="${status == 'approved'}">
                            <option value="pending">Pending</option>
                            <option value="approved" selected>Approved</option>
                            <option value="declined">Declined</option>
                            <option value="all">All</option>
                        </c:when>
                        <c:when test="${status == 'declined'}">
                            <option value="pending">Pending</option>
                            <option value="approved">Approved</option>
                            <option value="declined" selected>Declined</option>
                            <option value="all">All</option>
                        </c:when>
                        <c:otherwise>
                            <option value="pending">Pending</option>
                            <option value="approved">Approved</option>
                            <option value="declined">Declined</option>
                            <option value="all" selected>All</option>
                        </c:otherwise>
                    </c:choose>
                </select>
                <button type="submit" class="btn btn-primary ml-2">Filter</button>
            </form>
        </div>
    </div>

			<div class="row">
				<div class="col-md-12">
					<h2 class="text-center mt-5 mb-3">Manage Article</h2>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead class="thead-light">
								<tr>
									<th>ID</th>
									<th>Title</th>
									<th>Content</th>
									<th>Status</th>
									<th>Category</th>
									<th>Tags</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="article" items="${articles}" varStatus="loop">
									<tr>
										<td><c:out value="${article.articleId}" /></td>
										<td><c:out value="${article.title}" /></td>
										<td><c:out value="${article.content}" escapeXml="false" /></td>
										<td><c:out value="${article.status}" /></td>
										<td><c:out value="${article.category.description}" /></td>
										<td><c:forEach var="tag" items="${article.tags}">
												<c:out value="${tag.name}" />
												<c:if test="${!loop.last}">, </c:if>
											</c:forEach></td>
										<td><a
											href="editor-manage-articles?action=get-detail-article-page&articleId=${article.articleId}"
											class="btn btn-primary mr-2"> <i
												class="fas fa-pencil-alt"></i> View
										</a>
											<button class="btn btn-danger">
												<i class="fas fa-trash-alt"></i> Delete
											</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div
						class="pagination-container d-flex justify-content-center mt-3">
						<c:if test="${totalPages > 1}">
							<ul class="pagination">
								<c:forEach begin="1" end="${totalPages}" var="page">
									<li
										class="page-item <c:if test='${page == pageNumber}'>active</c:if>">
										<a class="page-link"
										href="editor-manage-articles?page=${page}">${page}</a>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
				</div>
			</div>
		</div>



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
	<script type="text/javascript" src="./assets/js/jquery.min.js"></script>
	<script>
		$(document)
				.ready(
						function() {
							$('.fa-trash')
									.click(
											function(event) {
												event.preventDefault(); // prevent the default action

												var articleId = $(this)
														.closest('tr').find(
																'td:first')
														.text(); // get the article ID from the first td of the row
												var confirmDelete = confirm("Do you want to delete this article?");
												if (confirmDelete) {
													$
															.ajax({
																url : 'editor-manage-articles?action=delete-article-editor&articleId='
																		+ articleId,
																type : 'POST',
																contentType : 'application/json',
																data : {
																	articleId : articleId
																},
																success : function(
																		result) {
																	alert('Article deleted successfully');
																	location
																			.reload(); // reload the page to reflect the changes
																},
																error : function(
																		jqXHR,
																		textStatus,
																		errorThrown) {
																	alert('Error deleting article: '
																			+ textStatus);
																}
															});
												}

											});
						});
		$(document).ready(function() {
			$('#status').change(function() {
				$('#statusForm').submit();
			});
		});
	</script>
	

	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/wow.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/slick.min.js"></script>
	<script src="assets/js/jquery.li-scroller.1.0.js"></script>
	<script src="assets/js/jquery.newsTicker.min.js"></script>
	<script src="assets/js/jquery.fancybox.pack.js"></script>
	<script src="assets/js/custom.js"></script>
</body>

</html>
