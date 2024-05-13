
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Warning</title>
</head>
<body>

<script>
function confirmDelete(userId) {
	
    var result = confirm("Bạn có chắc chắn muốn xóa tài khoản này không?");
    if (result) {
        // Nếu người dùng nhấn "Yes", tiến hành gửi yêu cầu xóa đến máy chủ
        window.location.href = "admin?action=delete-account?userId=" + userId;
    } else {
        // Nếu người dùng nhấn "No", không làm gì cả
    }
}
</script>

</body>
</html>
