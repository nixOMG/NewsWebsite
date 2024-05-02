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
                    <form class="form" id="a-form" method="post" action="home">
                        <input type="hidden" name="action" value="handle-forgot-pass">
                        <a href="login.jsp" class="homelink">←Back</a>
                        <h2 class="form_title title">Please enter your email:</h2>
                        <span class="form__span">Don't worry, we will get it back!</span>
                        <input class="form__input" type="email" name="email" id="emailLog" placeholder="Your email" required>
                        <c:if test="${not empty errorMessage}">
			            	<p style="color: red;">${errorMessage}</p>
			        	</c:if>
                        <button type="submit" class="button bn5">SEND</button>
                    </form>
                </div>
            </div>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script>
				$(document).ready(function() {
				    $('#emailLog').blur(function() {
				        var email = $(this).val();
				        var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;  // Regular expression for email
				        if (!emailReg.test(email) || email == '') {
				            // Invalid email, show error message
				            $(this).after('<span class="error">Please enter a valid email.</span>');
				        }
				    });
				
				    // Remove error message when user starts typing again
				    $('#emailLog').focus(function() {
				        $('.error').remove();
				    });
				});
				$(document).ready(function() {
				    // Prevent form submission if there are any error messages
				    $('form').submit(function(e) {
				        if ($('.error').length > 0) {
				            e.preventDefault();
				            alert('Please correct the errors before submitting.');
				        }
				    });
				});
			</script>
			<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	        crossorigin="anonymous"></script>
            <script type="text/javascript" src="./assets/js/script.js"></script>
        </body>

        </html>