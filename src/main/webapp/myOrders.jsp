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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
<meta charset="ISO-8859-1">
<title>My ORDERS</title>
</head>
<body>	
<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
<div>
<form method="get" action="MyOrders" >  
Click here to view your orders:
<input type="submit" value="VIEW MY ORDERS" class="form-submit">  
</form>
</div>
<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status =="Done")
		swal("Success!","Your requested service is completed successfully!Thanks for using FETCHER HOME SERVICE! ","success");
	
	</script>
</body>
</html>
