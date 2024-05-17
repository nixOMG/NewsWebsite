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

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


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
								src="images/logo.jpg" class="user-image" alt="User Image">
								<span class="hidden-xs">Hi Admin</span>
						</a>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><img src="images/logo.jpg"
									class="img-circle" alt="User Image">

									<p>
										Admin for web <small>since 2024</small>
									</p></li>

								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="home?action=manage-user-info"
											class="btn btn-default btn-flat">Profile</a>
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
						<img src="images/logo.jpg" class="img-circle" alt="User Image">
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

					<li><a href="admin?action=get-page-manage-comment"> <i
							class="fa fa-th"></i> <span>Comment Manager </span>

					</a></li>
					<li><a href="admin?action=get-page-assign-category"> <i
							class="fa fa-th"></i> <span>Category Assign </span>

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
				<h1>Phân công chuyên mục</h1>
				<ol class="breadcrumb">
					<li><a href="home?action=home"><i class="fa fa-dashboard"></i>
							Home</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="col-md-8">
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">Assign Category to User</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form action="admin?action=get-page-assign-category" method="post"
							accept-charset="UTF-8">
							<div class="box-body">
								<div class="form-group">
									<input type="hidden" name="action" value="assign-category" />
									
									
									<label for="userId" class="form-label">User ID</label>
									
									 <input
										type="text" class="form-control" id="userId" name="userId"
										value="${user.userId}" readonly>
								</div>

								<div class="form-group">
									<label for="username" class="form-label">User Name</label> <input
										type="text" class="form-control" id="username" name="username"
										value="${user.username}" readonly>
								</div>

								<div class="form-group">
									<label for="email" class="form-label">Email</label> <input
										type="email" class="form-control" id="email" name="email"
										value="${user.email}" readonly>
								</div>

								<div class="form-group">
									<label for="role" class="form-label">Role</label> <input
										type="text" class="form-control" id="role" name="role"
										value="${user.role.description}" readonly>
								</div>

								<div class="form-group" id="categories">
									<label class="form-label">Category</label>
									<c:forEach var="category" items="${categories}">
										<label> <input type="radio" name="categoryId"
											value="${category.categoryId}" /> <c:out
												value="${category.description}" />
										</label>
										<br>
									</c:forEach>
								</div>

								<div class="form-group">
									<button type="submit" class="btn btn-success" href="admin?action=get-page-assign-category">Assign</button>
								</div>
							</div>
						</form>
					</div>
					<!-- /.box -->
				</div>
				<!-- /.box -->
			</section>
			<!-- /.content -->
		</div>


		<!-- Content Wrapper. Contains page content -->

		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Admin</b>
			</div>
			<strong>Copyright &copy; 2024 Article Website.</strong>
		</footer>
	</div>
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