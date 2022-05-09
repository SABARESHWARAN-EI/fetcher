
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
if(session.getAttribute("uemail")==null)
{
	response.sendRedirect("index.jsp");
}
%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
   <html>
   <head>
 <link href="https://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css" rel="Stylesheet"
    type="text/css" />
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	   <meta http-equiv="Content-Type" 
            content="text/html; charset=windows-1256">
         <title>   User Logged Successfully   </title>
         <link href="css/ind.css" rel="stylesheet" />
   </head>
   <body>		
   <div class="main">
		<!-- Sign up form -->
		<section class="user">
			<div class="container">
				
				<div class="user-content">
					<div class="user-form">
						<form method="get" action="new_service" class="register-form"
							id="register-form">
							<!--  <h3 class="form-title" STYLE="font-size:30px"> Welcome ${name}! </h3>-->
							<div class="form-group" id="role"  name ="role" >
							<h4>SERVICE NEEDED: 
							
					 	   <select name="role" id="role" style="height:20px;width:80px">
								<option value="select">Select</option>
								    <option value="Plumber">Plumber</option>
								  <option value="Electrician">Electrician</option>
								</select>
								</h4>
					 	  <!-- 
					 	  ELECTRICIAN
					 	  <input type="radio"  value ="Electrician"   id="role"  name ="role"  checked  />
					 	  PLUMBER
					 	  <input type="radio"  value ="Plumber"    id="role"  name ="role"   />
					 	  -->
					 	</div>
					 	<div>
						  <textarea rows="5" cols="35" name="desc" id="desc" placeholder="Job Description" required></textarea>
						  </div>
						  <p>
						  </p>
						  <div>
					 	
						  <textarea rows="5" cols="35" name="address" id="address" placeholder="Your address" required></textarea>
						  </div>
						  <div>
							Date of service:
				 			 <input type="date" id="udate" name="udate" required>
				 			 <script language="javascript">
								    var today = new Date();
								    var dd = String(today.getDate()).padStart(2, '0');
								    var mm = String(today.getMonth() + 1).padStart(2, '0');
								    var yyyy = today.getFullYear();
								
								    today = yyyy + '-' + mm + '-' + dd;
								    $('#udate').attr('min',today);
									</script>
				 			Select a time:
							<input type="time" id="utime" name="utime" required>
						<figure>
						</div>
					</div>							
					<div class="user-image">						
					<div>
							<img src="images/service.jpg" alt="sing up image">
						</div>
						</figure>
						<div class="form-group form-button">
								<input type="submit" name="submit" id="submit"
									class="form-submit" value="Submit" />
									
						</div>
						</form>
						<form method="get" action="new_service" class="register-form"
							id="register-form">
						<div class="form-group form-button">
									<input type="submit" name="submit" id="submit"
									class="form-submit" value="back" />
							</div>
							</div>
						  </form>
					</div>
				</div>
			</div>
		</section>
	</div>
      </body>
   </html>
