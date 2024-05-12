<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Article</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

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
	<!-- Navbar -->
	<ul>
  <li><a class="active" href="home">Home page</a></li>
  <li><a href="manage-book">Books</a></li>
  <li><a href="manage-authors">Authors</a></li>
  <li><a href="manage-artists">Artists</a></li>
  <li><a href="manage-translators">Translators</a></li>  
  <li><a href="manage-category">Categories</a></li>
</ul>
	<!-- End Navbar -->
	
	
	
	<div class="w3-container">
  <h2 style="text-align: center; margin: 20px 0;">Manage Article</h2>
  	<a class="btn-add-article" href="manage-articles?action=get-page-add-article">Add new</a>
  <table class="w3-table-all w3-hoverable" style="margin: 20px auto;">
    <thead>
        <tr class="w3-light-grey">
            <th>ID</th>
            <th>Title</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="article" items="${articles}" varStatus="loop">
            <tr>
                <td><c:out value="${(pageNumber - 1) * pageSize + loop.index + 1}" /></td>
                <td><c:out value="${article.articleId}" /></td>
                <td><c:out value="${article.title}" /></td>
                <td><c:out value="${article.content}" /></td>
                <td><c:out value="${article.status}" /></td>
                <td>
					  	<a style="margin-right: 10px" href="manage-articles?action=get-page-edit-article&articleId=${article.articleId}"><i class="fa-solid fa-pencil">Edit</i></a>
					  	<a href="manage-articles?action=get-page-delete-article&articleId=${article.articleId}"><i class="fa-solid fa-trash">Delete</i></a>

                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="pagination-container">
    <c:if test="${totalPages > 1}">
        <ul class="pagination">
            <c:forEach begin="1" end="${totalPages}" var="page">
                <li class="<c:if test="${page == pageNumber}">active</c:if>">
                    <a href="manage-article?page=${page}">${page}</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</div>
</div>
</body>
</html>




