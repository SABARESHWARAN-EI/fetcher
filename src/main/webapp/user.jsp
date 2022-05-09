<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
if(session.getAttribute("uemail")==null)
{
	response.sendRedirect("index.jsp");
}

%>
<!DOCTYPE html>
<html>
<head>
<%


response.setHeader("Pragma","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Expires","0");
response.setDateHeader("Expires",-1);

%>
<meta charset="ISO-8859-1">
<title>user</title>

<link rel="stylesheet" href="css/user_provider_style.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
</head>
<body>

<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
<center>
  <h3 class="form-title" STYLE="font-size:30px"> Welcome ${name}! </h3>

	
	    <br>
	    <form  method="post" action="user" class="user-form"	id="user-form">
	     <div>		
			<input type="submit" value="Add a Service" name="bt">
	</div>
	</form>
	<br>
	<div>	
	</form>	
	<form method="get" action="MyOrders" class="user-form"	id="user-form">
   
			<input type="submit" value="My Services" name="bt">
	</div>
		    </form>
		    <br>
		    <form method="post" action="logout" class="user-form"	id="user-form">
    <div>		
			<input type="submit" value="logout" name="bt">
	</div>
	</form>
  </center>
  
  <script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status =="Done")
		swal("Success!","Your requested service is completed successfully!Thanks for using FETCHER HOME SERVICE! ","success");
	else if(status =="No Orders")
		swal("No orders!","You have no orders now! ","info");
	</script>
</body>
</html>