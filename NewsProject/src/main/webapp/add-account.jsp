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
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Site wrapper -->
	<div class="wrapper">

		<header class="main-header">
			<!-- Logo -->
			<a href="home?action=admin" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
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
								<!-- Menu Body -->
								<li class="user-body">
									<div class="row">
										<div class="col-xs-4 text-center">
											<a href="#">Followers</a>
										</div>
										<div class="col-xs-4 text-center">
											<a href="#">Sales</a>
										</div>
										<div class="col-xs-4 text-center">
											<a href="#">Friends</a>
										</div>
									</div> <!-- /.row -->
								</li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="home?action=profile" class="btn btn-default btn-flat">Profile</a>
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

					<li><a href="admin?action=get-page-manage-comment"> <i class="fa fa-th"></i> <span>Comment
								Manager </span>

					</a></li>
					<li><a href=""> <i class="fa fa-th"></i> <span>Category
								Assign </span>

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
				<h1>Account Manage Page</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li><a href="#">Examples</a></li>
					<li class="active">Blank page</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="container">

				<!-- Default box -->
				<div class="col-md-8">
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">Cập nhật tài khoản</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form action="admin?action=get-page-manage-account" method="post" accept-charset="UTF-8">
							<input type="hidden" name="action" value="add-account" /> 
							<div class="box-body">
								<div class="form-group">
									<label for="name" class="form-label">User name </label> <input
										type="text" class="form-control" id="username" name="username"
										required>
								</div>

								<div class="form-group">
									<label for="email" class="form-label">Email address</label> <input
										class="form-control" type="email" name="email" id="emailReg"
										required>
								</div>

								<div class="form-group">
									<label for="fullname" class="form-label">Full name</label> <input
										class="form-control" type="text" name="fullname" id="fullname"
										required>
								</div>

								<div class="form-group">
									<label for="id" class="form-label">Identification Id </label> <input
										class="form-control" type="text" name="idenId" required>
								</div>


								<div class="form-group">
									<label for="age" class="form-label">Age </label> <input
										class="form-control" type="number" name="age" id="age"
										required>
								</div>

								<div class="form-group">
									<label for="address" class="form-label">Address </label> <input
										class="form-control" type="text" name="address" id="address"
										required>
								</div>

								<div class="form-group">
									<label for="phone" class="form-label">Phone number </label> <input
										class="form-control" type="text" name="phone" id="phone"
										required>
								</div>

								<div class="form-group">
									<label for="dob" class="form-label">Date of birth </label> <input
										class="form-control" type="date" name="dob" id="dob" required>
								</div>

								<div class="form-group">
									<label for="role" class="form-label">Role:</label> <select
										class="form-select" id="role" name="roleId">
										<option value="1" selected>NOT ACTIVATED</option>
										<option value="2">USER</option>
										<option value="3">SUBSCRIBER</option>
										<option value="4">WRITER</option>
										<option value="5">EDITOR</option>
										<option value="6">ADMIN</option>
										
									</select>
								</div>

							</div>

							<!-- /.box-body -->

							<div class="box-footer">
								<button type="submit" class="btn btn-primary">Create</button>
							</div>
						</form>
					</div>

					<!-- /.box -->

				</div>
				<!-- /.box -->

			</section>
			<!-- /.content -->
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

