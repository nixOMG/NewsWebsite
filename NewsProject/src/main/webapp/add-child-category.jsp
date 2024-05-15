<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Category</title>
    <!-- Thêm thư viện CSS Bootstrap -->
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="./assets/css/bootstrap-icons.css">
	<script
      src="https://kit.fontawesome.com/72fe31d212.js"
      crossorigin="anonymous"></script>
	
    <!-- Thêm CSS tùy chỉnh -->
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 50px;
        }

        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            font-weight: bold;
        }

        textarea {
            resize: vertical;
        }

        button {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="mb-4">Add Category</h2>
        
        <form action="manage-category" method="post" accept-charset="UTF-8">
		    <input type="hidden" name="action" value="add-child-category"/>
		    <input type="hidden" name="parentCategoryId" value="${category.categoryId}"/>
		    <div class="row mb-3">
		        <div class="col-6">
		            <label for="name" class="form-label">Category's name:</label>
		            <input type="text" class="form-control" id="description" name="description" placeholder="Ex: Khoa học, Thế giới" required>
		        </div>
		    </div>
		
		    <div class="row mb-3">
		        <div class="col-6">
		            <label for="parent" class="form-label">Parent Category:</label>
		            <input type="text" class="form-control" id="description" name="description" value="${category.description}" required>
		        </div>
		    </div>
		
		    <button type="submit" class="btn btn-primary">Add Category</button>
		</form>

    </div>
	
    <script type="text/javascript" src="./assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
