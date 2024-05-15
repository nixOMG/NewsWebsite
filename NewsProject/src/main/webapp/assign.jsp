<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Category Assignment</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
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
	<div class="wrapper">
		<header class="main-header">
			<a href="home?action=admin" class="logo"> <span class="logo-mini"><b>A</b>LT</span>
				<span class="logo-lg">Admin</span>
			</a>
			<nav class="navbar navbar-static-top">
				<a href="#" class="sidebar-toggle" data-toggle="push-menu"
					role="button"> <span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <img
								src="images/logo.jpg" class="user-image" alt="User Image">
								<span class="hidden-xs">Hi Admin</span>
						</a>
							<ul class="dropdown-menu">
								<li class="user-header"><img src="images/logo.jpg"
									class="img-circle" alt="User Image">
									<p>
										Admin for web <small>since 2024</small>
									</p></li>
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
		<aside class="main-sidebar">
			<section class="sidebar">
				<div class="user-panel">
					<div class="pull-left image">
						<img src="images/logo.jpg" class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<a>${loggedInUser.getUsername()}</a> <a href="#"><i
							class="fa fa-circle text-success"></i> Online</a>
					</div>
				</div>
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
				<ul class="sidebar-menu" data-widget="tree">
					<li><a href="admin?action=get-page-manage-account"><i
							class="fa fa-th"></i> <span>Account Manager</span></a></li>
					<li><a href="admin?action=get-page-manage-comment"><i
							class="fa fa-th"></i> <span>Comment Manager</span></a></li>
					<li><a href="admin?action=get-page-assign-category"><i
							class="fa fa-th"></i> <span>Category Assign</span></a></li>
				</ul>
			</section>
		</aside>

		<div class="content-wrapper">
			<section class="content-header">
				<h1>Category Assign Page</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Category Assign</li>
				</ol>
			</section>

			<section class="content">
    <div class="col-md-8">
        <!-- general form elements -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">Assign Category to User</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form action="admin?action=assign-category" method="post" accept-charset="UTF-8">
                <div class="box-body">
                    <!-- Filter to get the user by userId -->
                    <c:forEach var="user" items="${users}" varStatus="loop">
                        <c:if test="${user.userId == param.userId}">
                            <div class="form-group">
                                <label for="userId_${user.userId}" class="form-label">User ID</label>
                                <input type="text" class="form-control" id="userId_${user.userId}" name="userId" value="${user.userId}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="username_${user.userId}" class="form-label">User Name</label>
                                <input type="text" class="form-control" id="username_${user.userId}" name="username" value="${user.username}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="email_${user.userId}" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email_${user.userId}" name="email" value="${user.email}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="role_${user.userId}" class="form-label">Role</label>
                                <input type="text" class="form-control" id="role_${user.userId}" name="role" value="${user.role.description}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="categories_${user.userId}" class="form-label">Categories</label>
                                <c:forEach var="category" items="${categories}">
                                    <label>
                                        <input type="radio" name="category_${user.userId}" value="${category.categoryId}" />
                                        <c:out value="${category.description}" />
                                    </label><br>
                                </c:forEach>
                            </div>

                            <div class="form-group">
                                <button type="submit" name="assign" value="${user.userId}" class="btn btn-success">Assign</button>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </form>
        </div>
        <!-- /.box -->
    </div>
</section>


		</div>
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
