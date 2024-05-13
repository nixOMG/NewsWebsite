<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Verify!</title>
</head>
<body>

    <div>
        <img src="./assets/img/verify.jpg" alt="Verification Image" />
        <p>Please click the button below to go to your mailbox and complete the verification process:</p>
        
        <form id="emailForm" method="GET" action="https://mail.google.com/mail/">
            <input type="hidden" id="email" name="to" value="" />
            <input type="hidden" name="subject" value="Please verify your email address" />
        </form>
        <button id="goToMailboxBtn">Go to Mailbox</button>
        
    </div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("goToMailboxBtn").addEventListener("click", function() {
        // Chuyển hướng trực tiếp đến Gmail trong trình duyệt
        window.location.href = "https://mail.google.com/mail/";
    });
});

</script>
</body>
</html>
