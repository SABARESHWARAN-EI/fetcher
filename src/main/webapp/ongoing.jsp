<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
<title>Ongoing Page</title>
</head>
<body >
<form action="back" method="get">
<input type="submit" value="Back to My Orders" class="back" name="back" id="back">
</form>
<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
<!-- 
<form action="Ongoing" method="post">

<button id="back" name="back" value="Back to My orders">Back to My orders</button>
</form>
 -->
<form action="Ongoing" method="post">

<div style ="text-align: center">
<h2>Your current order details:</h2>
<table style="text-align:left" style="margin-left: auto; margin-right: auto;">
<style>
  #test {
    width:100%;
    height:100%;
  }
  table {
    margin: 0 auto; /* or margin: 0 auto 0 auto */
  }
</style>
<tr><td>
Order id  </td><td>:${order_id}
</td></tr>
<tr><td>
Service  </td><td>: ${role}
</td></tr>
<tr><td>
Description  </td><td>:${udesc}
</td></tr>
<tr><td>
Address  </td><td>: ${ulocation}
</td></tr>
<tr><td>
Provider </td><td>: ${uprovider}
</td></tr>
<tr><td>
Date  </td><td>: ${udate}
</td></tr>

</table>

<input type="checkbox" name="arrive" value="arrived" required>The service provider arrived at your location<br>
<input type="checkbox" name="ready" value="ready" required>The service provider is ready to start service<br>
<input type="checkbox" name="known" value="known" required>The start time is noted for billing when you click start button<br>
<input type="submit" id="start" name="start" class="start" value="START"> 
<!--  <input type="submit" id="end" name="start" class="end" value="END">-->

</form>
<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status =="failed")
		swal("Ooops!","Cannot start the work! Try again!","error");
	</script>
</body>

</html>