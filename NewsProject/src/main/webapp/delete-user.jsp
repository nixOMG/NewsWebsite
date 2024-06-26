<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Delete User</title>
    <!-- Thêm thư viện CSS Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
        <h2 class="mb-4">Delete User</h2>
        
        <form action="manage-users" method="post" accept-charset="UTF-8">
        	<input type="hidden" name="action" value="delete-user"/>
        	<input type="hidden" name="userId" value="${user.userId}"/>
            <div class="row mb-3">
                <div class="col-6">
                    <label for="name" class="form-label">User's name:</label>
                    <input type="text" class="form-control" id="username" name="username" value="${user.username}"disabled required>
                    <input class="form__input" type="email" name="email" id="emailReg"  value="${user.email}" disabled required>                    
					<input class="form__input" type="text" name="fullname" id="fullname"  value="${user.fullname}" disabled required>
                    <input class="form__input" type="text" name="idenId" id="idenId"  value="${user.identificationId}" disabled required>
                    <input class="form__input" type="number" name="age" id="age"  value="${user.age}" disabled required>
                    <input class="form__input" type="text" name="address" id="address"  value="${user.address}" disabled required>
                    <input class="form__input" type="text" name="phone" id="phone"  value="${user.phone}" disabled required>
                    <input class="form__input" type="date" name="dob" id="dob"  value="${user.dob}" disabled required>
                    <input type="text" class="form-control" id="role" name="role" value="${user.role.description}" disabled required>
				</div>
            </div>
			<button type="submit" class="btn btn-primary">Delete</button>
        </form>
    </div>

    <script type="text/javascript" src="./assets/js/bootstrap.bundle.min.js"></script>

</body>
</html>
