<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if(session.getAttribute("uemail")==null)
{
	response.sendRedirect("index.jsp");
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome provider</title>
<link rel="stylesheet" href="css/user_provider_style.css">

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	
</head>
<body style ="text-align: center">



<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
<div style ="text-align: center">
  <h3 class="form-title" STYLE="font-size:30px"> Welcome ${name}! </h3>

	
	    <form method="get" action="Request_list" >
    
	     <div class="p_but">		
			<input type="submit" value="request list" name="bt" id="provider_button">
	</div>
	</form>
	 <br>
	 <form method="get" action="provider_services" >
   
	     <div>		
			<input type="submit" value="my services" name="bt"  id="provider_button">
	</div>
		    </form>
		    <br>
		      <form method="post" action="logout" class="user-form"	id="user-form">
    <div>		
			<input type="submit" value="logout" name="bt">
	</div>
	</form>
		    
		    	
	<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status =="No requests")
		swal("No requests!","You have no requests now! ","info");
	else if(status =="No services")
		swal("No services!","You have no services till now! ","info");
	else if(status =="Check in")
		swal("Success!","You have successfully checked in! ","success");
	else if(status =="Check out")
		swal("Success!","You have successfully checked out! ","success");
	
	</script>
	
</div>
</body>
</html>