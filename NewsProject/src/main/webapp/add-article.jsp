<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
<title>NewsFeed | Writer | Add Article</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/richtext.min.css">
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
<script src="../assets/js/html5shiv.min.js"></script>
<script src="../assets/js/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
	<div class="container">
		<header id="header">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="header_top">
						<div class="header_top_left">
							<ul class="top_nav">
								<li><a href="../index.html">Home</a></li>
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
							<a href="../index.html" class="logo"><img
								src="../images/logo.jpg" alt=""></a>
						</div>
						<div class="add_banner">
							<a href="#"><img src="../images/addbanner_728x90_V1.jpg"
								alt=""></a>
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
						<li class="active"><a href="../index.html"><span
								class="fa fa-home desktop-home"></span><span class="mobile-show">Home</span></a></li>
						<li><a href="#">Technology</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">Mobile</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">Android</a></li>
								<li><a href="#">Samsung</a></li>
								<li><a href="#">Nokia</a></li>
								<li><a href="#">Walton Mobile</a></li>
								<li><a href="#">Sympony</a></li>
							</ul></li>
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
							<li><a href="#"><img src="../images/news_thumbnail3.jpg"
									alt="">My First News Item</a></li>
							<li><a href="#"><img src="../images/news_thumbnail3.jpg"
									alt="">My Second News Item</a></li>
							<li><a href="#"><img src="../images/news_thumbnail3.jpg"
									alt="">My Third News Item</a></li>
							<li><a href="#"><img src="../images/news_thumbnail3.jpg"
									alt="">My Four News Item</a></li>
							<li><a href="#"><img src="../images/news_thumbnail3.jpg"
									alt="">My Five News Item</a></li>
							<li><a href="#"><img src="../images/news_thumbnail3.jpg"
									alt="">My Six News Item</a></li>
							<li><a href="#"><img src="../images/news_thumbnail3.jpg"
									alt="">My Seven News Item</a></li>
							<li><a href="#"><img src="../images/news_thumbnail3.jpg"
									alt="">My Eight News Item</a></li>
							<li><a href="#"><img src="../images/news_thumbnail2.jpg"
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
		<section id="contentSection">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="left_content">
						<div class="contact_area">
							<h2>Add Article</h2>
							<form action="manage-articles" method="post" class="contact_form"
								enctype="multipart/form-data" accept-charset="UTF-8">
								<input type="hidden" name="action" value="add-article" /> <input
									type="hidden" name="writerId" value="${writerId}" /> <label
									for="name" class="form-label">Title of this article:</label> <input
									type="text" class="form-control" id="title" name="title"
									placeholder="Title*"> <label for="image"
									class="form-label">Add image(s):</label> <input type="file"
									class="form-control" id="image" name="image" accept="image/*"
									multiple> <label for="content" class="form-label">Content:</label>
								<textarea class="content" cols="30" rows="10" name="content"></textarea>

								<label for="translator" class="form-label">Category:</label> <select
									class="form-select" id="category" name="categoryId">
									<c:forEach var="category" items="${categories}">
										<option value="${category.categoryId}">
											${category.description}</option>
									</c:forEach>
								</select> <select class="form-select" id="tag" name="tagId" multiple>
									<c:forEach var="tag" items="${tags}">
										<option value="${tag.tagId}">${tag.name}</option>
									</c:forEach>
								</select>

								<button type="submit" class="btn btn-primary">Submit
									Article</button>
							</form>
						</div>
					</div>
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
					Copyright &copy; 2045 <a href="../index.html">NewsFeed</a>
				</p>
				<p class="developer">Developed By Wpfreeware</p>
			</div>
		</footer>
	</div>

	<script type="text/javascript"
		src="./assets/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="./assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="./assets/js/jquery.richtext.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/uuid/8.3.2/uuid.min.js"></script>
	<script src="assets/js/wow.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/slick.min.js"></script>
	<script src="assets/js/jquery.li-scroller.1.0.js"></script>
	<script src="assets/js/jquery.newsTicker.min.js"></script>
	<script src="assets/js/jquery.fancybox.pack.js"></script>
	<script src="assets/js/custom.js"></script>



	<script>
		$(document)
				.ready(
						function() {
							// Initialize the rich text editor
							var editor = $('.content').richText({
								fileUpload : false,
								videoEmbed : false,
								table : false
							});

							$('#image')
									.change(
											function() {
												var uuid = window.uuid.v4();
												var files = this.files;
												var baseUrl = window.location.origin;
												var contextPath = window.location.pathname
														.split('/')[1];

												var promises = $
														.map(
																files,
																function(file) {
																	var formData = new FormData();
																	formData
																			.append(
																					'image',
																					file);

																	return $
																			.ajax({
																				url : '/NewsProject/manage-articles?action=upload-image&uuid='
																						+ uuid,
																				type : 'POST',
																				data : formData,
																				contentType : false,
																				processData : false
																			});
																});

												Promise
														.all(promises)
														.then(
																function(
																		results) {
																	$
																			.each(
																					results,
																					function(
																							i,
																							data) {
																						console
																								.log(data);
																						console
																								.log(data.url);
																						var imageUrl = baseUrl
																								+ '/'
																								+ contextPath
																								+ data.url;
																						var imgTag = '<img src="' + imageUrl + '" style="object-fit:contain;width:1000px;height:1000px;" class="container d-flex align-items-center my-5">';

																						var selection = window
																								.getSelection();
																						var range;
																						if (selection.rangeCount > 0) {
																							// Use the existing range if it exists
																							range = selection
																									.getRangeAt(0);
																						} else {
																							// Otherwise, create a new range that selects the textarea
																							range = document
																									.createRange();
																							range
																									.selectNode($('.content')[0]);
																							selection
																									.addRange(range);
																						}
																						var tempDiv = document
																								.createElement('div');
																						tempDiv.innerHTML = imgTag;
																						tempDiv.classList
																								.add(
																										"container-xl",
																										"pt-3");

																						range
																								.deleteContents();
																						range
																								.insertNode(tempDiv);
																						range
																								.setStartAfter(tempDiv);
																						range
																								.setEndAfter(tempDiv);
																						selection
																								.removeAllRanges();
																						selection
																								.addRange(range);
																					});
																});
											});
						});
	</script>
</body>

</html>