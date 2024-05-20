<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 	

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Blank Page</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="assets/admin/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/admin/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/admin/css/AdminLTE.css">
<link rel="stylesheet" href="assets/admin/css/_all-skins.min.css">
<link rel="stylesheet" href="assets/admin/css/jquery-ui.css">
<link rel="stylesheet" href="assets/admin/css/style.css" />
<script src="assets/admin/js/angular.min.js"></script>
<script src="assets/admin/js/app.js"></script>

<style>
.form-container {
	max-width: 600px;
	margin: 50px auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #f9f9f9;
}

.form-title {
	text-align: center;
	margin-bottom: 20px;
}

.form-group label {
	font-weight: bold;
}

.form-group input, .form-group select {
	width: 100%;
}
</style>


</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Site wrapper -->
	<div class="wrapper">

		<header class="main-header">
			<!-- Logo -->
			<a href="admin?action=admin" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>A</b>LT</span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg">Admin</span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="push-menu"
					role="button"> <span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a>

				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">


						<!-- User Account: style can be found in dropdown.less -->
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <img
								src="assets/admin/images/adminIcon.jpg" class="user-image" alt="User Image">
								<span class="hidden-xs">Hi Admin</span>
						</a>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><img src="assets/admin/images/adminIcon.jpg"
									class="img-circle" alt="User Image">

									<p>
										Admin for web <small>since 2024</small>
									</p></li>
								
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="manage-user-info" class="btn btn-default btn-flat">Profile</a>
									</div>
									<div class="pull-right">
										<a href="home?action=sign-out"
											class="btn btn-default btn-flat">Sign out</a>
									</div>
								</li>
							</ul></li>

					</ul>
				</div>
			</nav>
		</header>

		<!-- =============================================== -->

		<!-- Left side column. contains the sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="assets/admin/images/adminIcon.jpg" class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<a>${loggedInUser.getUsername()}</a> <a href="#"><i
							class="fa fa-circle text-success"></i> Online</a>
					</div>
				</div>
				<!-- search form -->
				<form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control"
							placeholder="Search..."> <span class="input-group-btn">
							<button type="submit" name="search" id="search-btn"
								class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
				<!-- /.search form -->
				<!-- sidebar menu: : style can be found in sidebar.less -->

				<ul class="sidebar-menu" data-widget="tree">

					<li><a href="admin?action=get-page-manage-account"> <i
							class="fa fa-th"></i> <span>Account Manager</span>
					</a></li>

					<li><a href="admin?action=get-page-manage-comment"> <i class="fa fa-th"></i> <span>Comment
								Manager </span>

					</a></li>
					


				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>
		<!-- =============================================== -->

		

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Quản lý tài khoản
				</h1>
				<ol class="breadcrumb">
					<li><a href="home?action=home"><i class="fa fa-dashboard"></i>
							Home</a></li>
				</ol>
			</section>

			<section class="container">
				<div class="container form-container" id="b-container">
					<form class="form" id="b-form" method="post" action="admin">
						<input type="hidden" name="action" value="add-account">
						<h2 class="form-title">Create Account</h2>

						<div class="form-group">
							<label for="username" class="form-label">Email address:</label> <input
								class="form-control" type="email" name="email" id="emailReg"
								placeholder="Your email" required>
						</div>

						<div class="form-group">
							<label for="email" class="form-label">User name:</label> <input
								class="form-control" type="text" name="username"
								placeholder="Your username" required>
						</div>

						<div class="form-group">
							<label for="password" class="form-label">Password:</label> <input
								class="form-control" type="password" name="password"
								id="passReg" placeholder="Password" required>
						</div>

						<div class="form-group">
							<label for="fullname" class="form-label">Full name:</label> <input
								class="form-control" type="text" name="fullname" id="fullname"
								placeholder="Your full name" required>
						</div>

						<div class="form-group">
							<label for="idenId" class="form-label">Identification Id:</label>
							<input class="form-control" type="text" name="idenId" id="idenId"
								placeholder="Your identification Id" required>
						</div>

						<div class="form-group">
							<label for="age" class="form-label">Age:</label> <input
								class="form-control" type="number" name="age" id="age"
								placeholder="Your age" required>
						</div>

						<div class="form-group">
							<label for="address" class="form-label">Address:</label> <input
								class="form-control" type="text" name="address" id="address"
								placeholder="Your address" required>
						</div>

						<div class="form-group">
							<label for="phone" class="form-label">Phone number:</label> <input
								class="form-control" type="text" name="phone" id="phone"
								placeholder="Your phone number" required>
						</div>

						<div class="form-group">
							<label for="dob" class="form-label">Date of birth:</label> <input
								class="form-control" type="date" name="dob" id="dob"
								placeholder="Your date of birth" required>
						</div>

						<div class="form-group">
							<label for="role" class="form-label">Role:</label> <select
								class="form-control" id="role" name="roleId">
								<option value="1" selected>NOT ACTIVATED</option>
								<option value="2">USER</option>
								<option value="3">SUBSCRIBER</option>
								<option value="4">WRITER</option>
								<option value="5">EDITOR</option>
								<option value="6">ADMIN</option>
							</select>
						</div>

						<c:if test="${not empty errorMessage2}">
							<div class="alert alert-danger" role="alert">
								${errorMessage2}</div>
						</c:if>
						<%
						session.removeAttribute("errorMessage2");
						%>

						<button type="submit" class="btn btn-primary btn-block">Create</button>
					</form>
				</div>
			</section>
		</div>
		<!-- /.content-wrapper -->


		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Admin</b>
			</div>
			<strong>Copyright &copy; 2024 Article Website.</strong>
		</footer>

	</div>
	<!-- ./wrapper -->

	<!-- jQuery 3 -->

	<script src="assets/admin/js/jquery.min.js"></script>
	<script src="assets/admin/js/jquery-ui.js"></script>
	<script src="assets/admin/js/bootstrap.min.js"></script>
	<script src="assets/admin/js/adminlte.min.js"></script>
	<script src="assets/admin/js/dashboard.js"></script>
	<script src="assets/admin/tinymce/tinymce.min.js"></script>
	<script src="assets/admin/tinymce/config.js"></script>
	<script src="assets/admin/js/function.js"></script>
</body>
</html>