<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up Form </title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
      
<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
<script language="JavaScript">
function setVisibility(id, visibility) {
document.getElementById(id).style.display = visibility;
}
</script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

</head>
<body>

<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
					
						<form method="post" action="register" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Your Name" required />
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email"  required />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass" placeholder="Password"  required />
							</div>
							
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-phone"></i>
								</label>
								<input type="text" name="contact" id="contact"
									placeholder="Contact no" />
							</div>
						
					 	  <div class="form-group" id="role"  name ="role" style="white-space:nowrap">
					 	  PROVIDER
					 	  <input type="radio"  value ="service_provider"  onclick="setVisibility('sub3', 'inline');"  id="role"  name ="role"  checked  />
					 	  USER
					 	  <input type="radio"  value ="service_user"   onclick="setVisibility('sub3', 'none');"  id="role"  name ="role"   />
					 	</div>
						
						
						<div id="sub3">
							<div class="form-group">
							SERVICE:
								<select name="service_role" id="service_role">
								<option value="select">Select</option>
								    <option value="Plumber">Plumber</option>
								  <option value="Electrician">Electrician</option>
								</select>
								</div>
								 <div class="form-group">
								<label for="experience"><i class="zmdi zmdi-calendar"></i></label>
								<input type="text" name="experience" id="experience"
									placeholder="Years of experience" />

								</div>
						</div>
					 	 
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
						<div>
							<img src="images/service.jpg" alt="sing up image">
						</div>
						</figure>
						<a href="index.jsp" class="signup-image-link">I am already
							member</a>
					</div>
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	
	
	
	
	<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status =="success")
		swal("Congrats!","Account created successfully","success");
	else if(status=="InvalidName")
		swal("Oops!","Enter your Name!","Error");
	else if(status =="InvalidEmail")
		swal("Oops!","Enter your email!","Error");
	else if(status =="invalidPassword")
		swal("Oops!","Enter your password!","Error");
	else if(status =="invalidMobile")
		swal("Oops!","Enter your mobile number!","Error");
	else if(status =="InvalidMobileLength")
		swal("Oops!","Enter your 10 digit mobile number!",'error');
	<!--
	else if(status =="alreadyRegistered")
		swal("Oops!","Email already Exists! Try login!",'error');
	-->

	</script>


</body>

</html>