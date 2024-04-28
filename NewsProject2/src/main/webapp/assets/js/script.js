//form js

let switchCtn = document.querySelector("#switch-cnt");
let switchC1 = document.querySelector("#switch-c1");
let switchC2 = document.querySelector("#switch-c2");
let switchCircle = document.querySelectorAll(".switch__circle");
let switchBtn = document.querySelectorAll(".switch-btn");
let aContainer = document.querySelector("#a-container");
let bContainer = document.querySelector("#b-container");
let allButtons = document.querySelectorAll(".submit");

let getButtons = (e) => e.preventDefault();

let changeForm = (e) => {
  switchCtn.classList.add("is-gx");
  setTimeout(function () {
    switchCtn.classList.remove("is-gx");

    // Save the current form to localStorage after the switch animation has completed
    var currentForm = document.querySelector('.container:not(.is-hidden)').id;
    localStorage.setItem('currentForm', currentForm);
  }, 1500);

  switchCtn.classList.toggle("is-txr");
  switchCircle[0].classList.toggle("is-txr");
  switchCircle[1].classList.toggle("is-txr");

  switchC1.classList.toggle("is-hidden");
  switchC2.classList.toggle("is-hidden");
  aContainer.classList.toggle("is-txl");
  bContainer.classList.toggle("is-txl");
  bContainer.classList.toggle("is-z200");
};

let mainF = (e) => {
  for (var i = 0; i < allButtons.length; i++) allButtons[i].addEventListener("click", getButtons);
  for (var i = 0; i < switchBtn.length; i++) switchBtn[i].addEventListener("click", changeForm);
  
};

window.addEventListener("load", mainF);

$(document).ready(function () {
  $("#emailLog").blur(function () {
    var emailLog = $(this).val();
    var emailLogEx = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/; // Regular expression for email
    if (!emailLogEx.test(emailLog) || emailLog == "") {
      // Invalid email, show error message
      $(this).after('<span class="error">Please enter a valid email.</span>');
    }
  });

  // Remove error message when user starts typing again
  $("#emailLog").focus(function () {
    $(".error").remove();
  });

  $("#emailReg").blur(function () {
    var emailReg = $(this).val();
    var emailRegEx = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/; // Regular expression for email
    if (!emailRegEx.test(emailReg) || emailReg == "") {
      // Invalid email, show error message
      $(this).after('<span class="errorReg">Please enter a valid email.</span>');
    }
  });

  // Remove error message when user starts typing again
  $("#emailReg").focus(function () {
    $(".errorReg").remove();
  });

  $("#passReg").blur(function () {
    var passReg = $(this).val();
    var passRegEx = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/; // Regular expression for password
    if (!passRegEx.test(passReg) || passReg == "") {
      // Invalid email, show error message
      $(this).after(
        '<span class="errorReg">Password must contain at least eight characters, at least one uppercase letter, one lowercase letter and one number.</span>'
      );
    }
  });

  // Remove error message when user starts typing again
  $("#passReg").focus(function () {
    $(".errorReg").remove();
  });
});
