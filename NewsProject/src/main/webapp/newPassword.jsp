<%@page contentType="text/html" pageEncoding="utf-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>Tenshi fandom</title>
            <link rel="stylesheet" href="./assets/css/LogReg.css" />
            <link rel="icon" href="./assets/img/webicon.png" type="image/x-icon" />
        </head>

        <body>
            <div class="main">
                <div class="container a-container" id="a-container">
                    <form class="form" id="a-form" method="post" action="resetPass">
                    <% String token = (String) session.getAttribute("token"); %>
					<input type="hidden" name="token" value="<%=token%>">
                    <input type="hidden" name="action" value="handle-change-pass">
                        <h2 class="form_title title">Almost there!</h2>
                        <span class="form__span">Only one more step!</span>                        
                        <input class="form__input" type="password" name="password" id="password" placeholder="Your new password" required>
                        <c:if test="${not empty errorMessage}">
			            	<p style="color: red;">${errorMessage}</p>
			        	</c:if>
                        <button type="submit" class="button bn5">CHANGE PASSWORD</button>
                    </form>
                </div>
            </div>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
			<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	        crossorigin="anonymous"></script>
            <script type="text/javascript" src="./assets/js/script.js"></script>
        </body>

        </html>