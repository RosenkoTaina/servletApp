<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Application</title>

    <!-- CSS Styles -->
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        form {
            border: 3px solid #f1f1f1;
        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            background-color: #04AA6D;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }

        .cancelbutton {
            width: auto;
            padding: 10px 18px;
            background-color: #f44336;
        }

        .container {
            padding: 16px;
        }

        span.psw {
            float: right;
            padding-top: 16px;
        }

        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }

            .cancelbutton {
                width: 100%;
            }
        }
    </style>
</head>
<body>

    <!-- Login Form -->
    <form action="loginServlet" method="post" onsubmit="return validateEmail()">
        <div class="container">
            <label for="emailId"><b>Email</b></label>
            <input type="text" placeholder="Enter your email" name="emailId" id="emailId" required>

            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" id="password" required>

            <button type="submit">Login</button>
            <label>
                <input type="checkbox" checked="checked" name="rememberme"> Remember me
            </label>
        </div>

        <div class="container" style="background-color: #f1f1f1">
            <button type="button" class="cancelbutton">Cancel</button>
            <span class="psw">Forgot <a href="<%=request.getContextPath()%>/forgotpassword.jsp">password?</a></span>
        </div>
    </form>

    <!-- JavaScript for Client-side Validation -->
    <script type="text/javascript">
        function validateEmail() {
            var emailInput = document.getElementById('emailId');
            var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

            if (emailInput.value.match(emailFormat)) {
                return true;
            } else {
                alert("You have entered an invalid email address!");
                emailInput.focus();
                return false;
            }
        }
    </script>
</body>
</html>
