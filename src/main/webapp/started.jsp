<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
<meta charset="ISO-8859-1">
<title>SERVICE STARTED Page</title>
</head>
<body >

<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
<form action="back" method="get">
<input type="submit" value="Back to My Orders" class="back" name="back" id="back">
</form>
<form action="Started" method="post">
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
Order id  </td><td>: ${order_id}
</td></tr>
<tr><td>
Service  </td><td>:  ${urole}
</td></tr>
<tr><td>
Description  </td><td>: ${udesc}
</td></tr>
<tr><td>
Address  </td><td>: ${ulocation}
</td></tr>
<tr><td>
Provider </td><td>: ${uprovider}
</td></tr>
<tr><td>
Started TimeStamp</td><td>: ${ustart}
</td></tr>
</table>

<input type="checkbox" name="finished" value="finished" required>The service provider finished the service that I needed<br>
<input type="checkbox" name="work" value="work" required>I know that the service provider will not work anymore after I click the end button<br>
<input type="checkbox" name="known" value="known" required>I know that tthe end time is noted for billing when I click end button<br>
<input type="submit" id="end" name="end" class="end" value="end"> 
<!--  <input type="submit" id="end" name="start" class="end" value="END">-->

</form>
</div>
<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status =="started")
		swal("Success!","The work is started and the time is noted!","success");
	</script>
</body>
</html>