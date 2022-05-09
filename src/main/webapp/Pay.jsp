<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
<meta charset="ISO-8859-1">
<title>Pay Page</title>
</head>
<body >

<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
<form action="back" method="get">
<input type="submit" value="Back to My Orders" class="back" name="back" id="back">
</form>
<form action="pay" method="get">
<div style ="text-align: center">
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
Provider </td><td>: ${uprovider}
</td></tr>
<tr><td>
Description  </td><td>: ${udesc}
</td></tr>
<tr><td>
Address  </td><td>: ${ulocation}
</td></tr>
<tr><td>
Started TimeStamp</td><td>: ${ustart}
</td></tr>
<tr><td>
Ended TimeStamp</td><td>: ${uend}
</td></tr>
<tr><td>
Duration</td><td>: ${hrs} Hours ${mins} Minutes
</td></tr>
<tr><td>
Service charge as per duration</td><td>: ${uamount}
</td></tr>
<tr><td>
Additional charges description</td><td>: ${add_desc}
</td></tr>
<tr><td>
Additional charges amount</td><td>: ${add_amt}
</td></tr>
<tr><td>
Amount to be paid</td><td>: ${tamount}
</td></tr>
<tr><td>
Payment mode</td><td>: Cash
</td></tr>

</table>
<input type="checkbox" name="pay" value="pay" required>The service provider completed the service and the payment cash is handed over to the service provider<br>
<input type="submit" id="paid" name="paid" class="paid" value="paid"> 
</form>
</div>
<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status =="started")
		swal("Success!","The work is started and the time is noted!","success");
	</script>
</body>
</html>