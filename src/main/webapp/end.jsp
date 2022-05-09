<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
<meta charset="ISO-8859-1">
<title>SERVICE end Page</title>
</head>
<body >

<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
<form action="back" method="get">
<input type="submit" value="Back to My Services" class="back" name="back" id="back">
</form>
<form action="end" method="get">
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
User name</td><td>:  ${username}
</td></tr>
<tr><td>
User email</td><td>:  ${user_email}
</td></tr>
<tr><td>
User mobile</td><td>:  ${user_mobile}
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
Additional charges description</td><td>
						  <textarea rows="2" cols="25" name="add_desc" id="add_desc" placeholder="Additional Charges Description" required></textarea>
						  
</td></tr>
<tr><td>
Additional charges amount</td><td>
						  <textarea rows="1" cols="25" name="add_amount" id="add_amount" placeholder="Additional Charges Amount" required></textarea>
						  
</td></tr>
</table>

<input type="checkbox" name="finished" value="finished" required>The work  is finished and I know that the end time is noted for billing when I click end button<br>
<input type="submit" id="end" name="end" class="end" value="end"> 

</form>
</div>
<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status =="started")
		swal("Success!","The work is started and the time is noted!","success");
	</script>
</body>
</html>