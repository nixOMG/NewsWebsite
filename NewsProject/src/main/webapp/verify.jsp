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
            <input type="hidden" id="email" name="to" value="<%= request.getParameter("email") %>" />
            <input type="hidden" name="subject" value="Please verify your email address" />
        </form>
        <button onclick="submitForm()">Go to Mailbox</button>

        <script>
            function submitForm() {
                document.getElementById("emailForm").submit();
            }
        </script>
    </div>
</body>
</html>
