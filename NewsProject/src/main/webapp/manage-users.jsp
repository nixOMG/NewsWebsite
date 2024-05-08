<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Users</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<style type="text/css">
.w3-table-all th,
    .w3-table-all td {
        font-size: 13px;
    }
    
.btn-add-user{
    display: inline-block;
    width: 110px;
    height: 40px;
    text-decoration: none;
    background-color: #6c757d;
    color: white;
    box-sizing: border-box;
    padding: 10px;
    border: none;
    border-radius: 5px;
}

.btn-add-userk:hover {
    background-color: rgba(108, 117, 125, 0.5);
    opacity: 1; 
    cursor: pointer;
}

</style>
</head>
<body>
	<!-- Navbar -->
	<ul>
  <li><a class="active" href="home">Home page</a></li>
  <li><a href="manage-book">Books</a></li>
  <li><a href="manage-users">Authors</a></li>
  <li><a href="manage-artists">Artists</a></li>
  <li><a href="manage-translators">Translators</a></li>  
  <li><a href="manage-category">Categories</a></li>
</ul>
	<!-- End Navbar -->
	
	
	
	<div class="w3-container">
  <h2 style="text-align: center; margin: 20px 0;">Manage User</h2>
 

  <table class="w3-table-all w3-hoverable" style="margin: 20px auto;">
    <thead>
        <tr class="w3-light-grey">
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}" varStatus="loop">
            <tr>
                <td><c:out value="${user.userId}" /></td>
                <td><c:out value="${user.username}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td>
					
                    <a style="margin-right: 10px" href="manage-users?action=get-page-edit-user&userId=${user.userId}"><i class="fa-solid fa-pencil"></i>Edit</a>
                    <a style="margin-right: 10px"href="manage-users?action=get-page-delete-user&userId=${user.userId}"><i class="fa-solid fa-trash"></i>Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</div>
</body>
</html>




