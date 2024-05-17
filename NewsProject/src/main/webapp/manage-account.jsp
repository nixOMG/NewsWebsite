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
.form-inline .form-control, .form-inline .btn {
    margin: 0 5px;
}

.dropdown-menu {
    background-color: #f8f9fa; /* Màu nền */
    color: #333; /* Màu chữ */
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
				<h1>Quản lý tài khoản</h1>
				<ol class="breadcrumb">
					<li><a href="home?action=home"><i class="fa fa-dashboard"></i>
							Home</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<div class="row justify-content-end mt-3">
					<div class="col-md-4">
						<form id="roleForm" action="admin" method="get"
							class="form-inline">
							<input type="hidden" name="action" value="get-accounts-by-role" />
							<label for="role" class="mr-2">Filter by Role:</label> <select
								id="role" name="role" class="form-control">
								<c:choose>
									<c:when test="${role == 'admin'}">
										<option value="admin" selected>Admin</option>
										<option value="editor">Editor</option>
										<option value="user">User</option>
										<option value="all">All</option>
									</c:when>
									<c:when test="${role == 'editor'}">
										<option value="admin">Admin</option>
										<option value="editor" selected>Editor</option>
										<option value="user">User</option>
										<option value="all">All</option>
									</c:when>
									<c:when test="${role == 'user'}">
										<option value="admin">Admin</option>
										<option value="editor">Editor</option>
										<option value="user" selected>User</option>
										<option value="all">All</option>
									</c:when>
									<c:otherwise>
										<option value="admin">Admin</option>
										<option value="editor">Editor</option>
										<option value="user">User</option>
										<option value="all" selected>All</option>
									</c:otherwise>
								</c:choose>
							</select>
							<button type="submit" class="btn btn-primary ml-2">Filter</button>
						</form>
					</div>
				</div>

				<!-- Display count of accounts based on role -->
				<div class="row mt-3">
					<div class="col-md-12">
						<c:set var="adminCount" value="0" />
						<c:set var="editorCount" value="0" />
						<c:set var="userCount" value="0" />

						<c:forEach var="account" items="${accounts}">
							<c:if test="${account.role == 'admin'}">
								<c:set var="adminCount" value="${adminCount + 1}" />
							</c:if>
							<c:if test="${account.role == 'editor'}">
								<c:set var="editorCount" value="${editorCount + 1}" />
							</c:if>
							<c:if test="${account.role == 'user'}">
								<c:set var="userCount" value="${userCount + 1}" />
							</c:if>
						</c:forEach>

						<div>
							Admin Accounts:
							<c:out value="${adminCount}" />
						</div>
						<div>
							Editor Accounts:
							<c:out value="${editorCount}" />
						</div>
						<div>
							User Accounts:
							<c:out value="${userCount}" />
						</div>
					</div>
				</div>


				<!-- Default box -->
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<a href="admin?action=get-page-add-account"
								class="btn btn-success">+Add Account</a>

							<div class="box-tools">
								<div class="input-group input-group-sm" style="width: 150px;">
									<input type="text" name="table_search"
										class="form-control pull-right" placeholder="Search">

									<div class="input-group-btn">
										<button type="submit" class="btn btn-default">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover" style="margin: 20px auto;">
								<tbody>
									<tr>
										<th>ID</th>
										<th>Name</th>
										<th>Email</th>
										<th>Role</th>
										<th>Actions</th>
									</tr>
									<c:forEach var="user" items="${users}" varStatus="loop">
										<tr>
											<td><c:out value="${user.userId}" /></td>
											<td><c:out value="${user.username}" /></td>
											<td><c:out value="${user.email}" /></td>
											<td><c:out value="${user.role.description}" /></td>
											<td><a class="btn btn-success"
												href="admin?action=get-page-edit-account&userId=${user.userId}">
													Edit</a> <a class="btn btn-danger"
												href="admin?action=get-page-delete-account&userId=${user.userId}">Delete</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>


						</div>
						<!-- /.box-body -->
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
	<script>
		$(document).ready(function() {
			$('#status').change(function() {
				$('#roleForm').submit();
			});
		});
	</script>
	

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