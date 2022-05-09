<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
<meta charset="ISO-8859-1">
<title>rating page</title>

</head>
<body>
<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
<form action="back" method="get">
<input type="submit" value="Back to My Orders" class="back" name="back" id="back">
</form>




 <form action="rating" method="post">

<div style ="text-align: center">
 <h3> Provide the ratings:</h3>
            <input type="radio" name="rating" value="1" class="star">1
            <input type="radio" name="rating" value="2" class="star">2
            <input type="radio" name="rating" value="3" class="star">3
            <input type="radio" name="rating" value="4" class="star">4
            <input type="radio" name="rating" value="5" class="star">5
            <input type="submit" name="submit" value="submit">
        </form>
        </div>
<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status =="paid")
		swal("Success!","Payment done successfully! ","success");
	
	</script>
</body>
</html>