<%
if(session.getAttribute("order_id")==null)
{
	response.sendRedirect("index.jsp");
}

%>
<%
if(session.getAttribute("uemail")==null)
{
	response.sendRedirect("index.jsp");
}

%>

<html>  
<body>  
<head>

</head>
<form method="get" action="provider_list" >  
<div>  Search for  ${role} </div>
<div>Cost : Rs.100 per hour</div>
<div> Minimum charge: Rs.100</div>
<input type="submit" value="search" class="form-submit">  
</form>  
</body>  
</html>  