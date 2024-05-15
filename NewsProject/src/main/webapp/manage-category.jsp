<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Category</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<style type="text/css">
.w3-table-all th,
    .w3-table-all td {
        font-size: 13px;
    }
    
.btn-add-category{
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

.btn-add-categoryk:hover {
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
  <li><a href="manage-authors">Authors</a></li>
  <li><a href="manage-artists">Artists</a></li>
  <li><a href="manage-translators">Translators</a></li>  
  <li><a href="manage-category">Categories</a></li>
</ul>
	<!-- End Navbar -->
	
	
	
	<div class="w3-container">
  <h2 style="text-align: center; margin: 20px 0;">Manage Category</h2>
  
  <a class="btn-add-category" href="manage-category?action=get-page-add-category">Add new</a>

 <table class="w3-table-all w3-hoverable" style="margin: 20px auto;">
    <thead>
        <tr class="w3-light-grey">
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="category" items="${categories}" varStatus="loop">
    <tr>
        <td><c:out value="${category.categoryId}" /></td>
        <td>
            <c:out value="${category.description}" />
            <a style="margin-left: 10px" href="manage-category?action=get-page-add-child-category&parentCategoryId=${category.categoryId}"><i class="fa-solid fa-plus"></i>Add Child</a>
            <button class="deleteBtn" data-category-id="${category.categoryId}"><i class="fa-solid fa-trash"></i>Delete</button>
            <c:if test="${not empty category.children}">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton${loop.index}" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        View Children
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton${loop.index}">
                        <c:forEach var="child" items="${category.children}">
                            <a class="dropdown-item" href="#">
                                ${child.description}
                                <button class="deleteBtn" data-category-id="${child.categoryId}"><i class="fa-solid fa-trash"></i>Delete</button>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </td>
    </tr>
</c:forEach>



    </tbody>
</table>
<script type="text/javascript" src="./assets/js/jquery.min.js"></script>
<script>
$(document).ready(function() {
    $('.deleteBtn').click(function(event) {
        event.preventDefault(); // prevent the default action

        var categoryId = $(this).data('category-id'); // get the category ID from the data attribute
        var confirmDelete = confirm("Do you want to delete this category?");
        
        if (confirmDelete) {
            $.ajax({
                url: 'manage-category?action=delete-category&categoryId=' + categoryId,
                type: 'POST',
                success: function(result) {
                    alert('Category deleted successfully');
                    location.reload(); // reload the page to reflect the changes
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('Error deleting category: ' + textStatus);
                }
            });
        }
    });
});


</script>
</div>
</body>
</html>




