<%@page contentType="text/html" pageEncoding="utf-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>Tenshi fandom</title>
            <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
            <link rel="stylesheet" href="./assets/css/LogReg.css" />
            <link rel="icon" href="./assets/img/webicon.png" type="image/x-icon" />
        </head>

        <body>
            <div class="main">
                <div class="container a-container" id="a-container">
                    <form class="form" id="a-form" method="post" action="home">
                        <input type="hidden" name="action" value="handle-login">
                        <a href="index.jsp" class="homelink">←Back</a>
                        <h2 class="form_title title">Sign in to Website</h2>
                        <span class="form__span">Glad to see you again!</span>
                        <input class="form__input" type="email" name="email" id="emailLog" placeholder="Your email" required>
                        
                        <input class="form__input" type="password" name="password" placeholder="Password" required>
                        <c:if test="${not empty errorMessage}">
							<div class="alert alert-danger" role="alert">
		                    	${errorMessage}
							</div>
						</c:if>
						<% session.removeAttribute("errorMessage"); %>
                        <a href="resetPass.jsp"
                            class="form__link">Forgot your password?</a>
                        <a class="form__link switch__button switch-btn">Or sign up</a>
                        <button type="submit" class="button bn5">SIGN IN</button>
                    </form>
                </div>

                <div class="container b-container" id="b-container">
                    <form class="form" id="b-form" method="post" action="home">
                        <input type="hidden" name="action" value="handle-register">
                        <h2 class="form_title title">Create Account</h2>
                        <span class="form__span">Let's sign you up!</span>
                        <input class="form__input" type="email" name="email" id="emailReg"  placeholder="Your email" required>
                        <input class="form__input" type="text" name="username" placeholder="Your username" required>                        
                        <input class="form__input" type="password"  name="password" id="passReg" placeholder="Password" required>
                        <input class="form__input" type="text" name="fullname" id="fullname"  placeholder="Your fullname" required>
                        <input class="form__input" type="text" name="idenId" id="idenId"  placeholder="Your identification Id" required>
                        <input class="form__input" type="number" name="age" id="age"  placeholder="Your age" required>
                        <input class="form__input" type="text" name="address" id="address"  placeholder="Your address" required>
                        <input class="form__input" type="text" name="phone" id="phone"  placeholder="Your phone number" required>
                        <input class="form__input" type="date" name="dob" id="dob"  placeholder="Your date of birth" required>
                        <a href="#" class="form__link switch__button switch-btn">Or sign in</a>
                        <c:if test="${not empty errorMessage2}">
							<div class="alert alert-danger" role="alert">
		                    	${errorMessage2}
							</div>
						</c:if>
			        	<% session.removeAttribute("errorMessage2"); %>
                        <button type="submit" class="button bn5">SIGN UP</button>
                        <div>
                        </div>
                    </form>
                </div>



                <div class="switch" id="switch-cnt">
                    <div class="switch__circle"></div>
                    <div class="switch__circle switch__circle--t"></div>

                    <div class="switch__container" id="switch-c1">
                        <h2 class="switch__title title">Don't have an account?</h2>
                        <p class="switch__description description">
                            Just come here and join us!
                        </p>

                        <button class="switch__button button bn5 switch-btn">SIGN UP</button>
                    </div>

                    <div class="switch__container is-hidden" id="switch-c2">
                        <h2 class="switch__title title">Already have an account?</h2>
                        <p class="switch__description description">
                            Let's shake up some old memories!
                        </p>
                        <button class="switch__button button bn5 switch-btn">SIGN IN</button>
                    </div>

                </div>

            </div>
            
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            
            <script>
				
				$(document).ready(function() {
					document.querySelector('.switch-btn').addEventListener('click', function() {
					    var currentForm = document.querySelector('.container:not(.is-hidden)').id;
					    localStorage.setItem('currentForm', currentForm);
					});

					// When the page loads, check if there's a saved form in localStorage
					document.addEventListener('DOMContentLoaded', function() {
					    var savedForm = localStorage.getItem('currentForm');
					    if (savedForm) {
					        // If there's a saved form, show it and hide the other one
					        document.querySelector('#' + savedForm).classList.remove('is-hidden');
					        document.querySelector('.container:not(#' + savedForm + ')').classList.add('is-hidden');
					    }
					});
				    // Prevent form submission if there are any error messages in the login form
				    $('form').submit(function(e) {
				        if ($('.error').length > 0) {
				            e.preventDefault();
				            alert('Please correct the errors before submitting.');
				        }
				    });
				 	// Prevent form submission if there are any error messages
				    $('form').submit(function(e) {
				        if ($('.errorReg').length > 0) {
				            e.preventDefault();
				            alert('Please correct the errors before submitting.');
				        }
				    });
				});
			</script>
	        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	        crossorigin="anonymous"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	        crossorigin="anonymous"></script>
	
	    <script type="text/javascript" src="./assets/js/bootstrap.bundle.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	        crossorigin="anonymous"></script>
	    <script src="https://kit.fontawesome.com/72fe31d212.js" crossorigin="anonymous"></script>
            <script type="text/javascript" src="./assets/js/script.js"></script>
        </body>

        </html>