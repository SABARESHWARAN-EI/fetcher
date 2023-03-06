<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>

<% 
response.setHeader("Pragma","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Expires","0");
response.setDateHeader("Expires",-1);
%>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign In Form </title>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	
<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->

<link rel="stylesheet" href="css/style.css">

<script type="text/javascript">
function back_block() {
window.history.foward(-1)
}
</script>

</head>

<body önload="javascript:back_block();">


<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
	<div class="main">

		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
						<div>
							<img src="images/service.jpg" alt="sing up image">
							</div>							
						</figure>
						<a href="registration.jsp" class="signup-image-link">Create an
							account</a>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Sign in</h2>
						<form method="post" action="index" class="register-form"
							id="login-form" >
							<div class="form-group">
								<label for="email">
								<i	class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" required/>
							</div>
							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password"
									placeholder="Password" required />
							</div>
							
							<!--  
							<div class="form-group">
								<input type="checkbox" name="remember-me" id="remember-me"
									class="agree-term" /> <label for="remember-me"
									class="label-agree-term"><span><span></span></span>Remember
									me</label>
							</div>
							
							-->
							<div class="form-group form-button">
								<a href="forgotPassword.jsp">Forgot Password?</a>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	
	<script>
    history.pushState(null, null, null);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, null);
    });
</script>
	
	<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status =="failed")
		swal("Ooops!","Wrong username or password! Try again","error");
	else if(status =="InvalidEmail")
		swal("Ooops!","Enter your email!","error");
	else if(status =="invalidPassword")
		swal("Ooops!","Enter your password!","error");
	else if(status =="resetSuccess")
		swal("Congrats!","Password Reset Successful","success");
	else if(status=="resetFailed")
		swal("Ooops!","Password Reset Failed!","error");

	else if(status =="alreadyRegistered")
		swal("Oops!","Email already Exists! Try login!",'error');

	</script>
	
	
</body>
</html>