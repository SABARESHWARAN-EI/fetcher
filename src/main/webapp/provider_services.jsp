<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if(session.getAttribute("uemail")==null)
{
	response.sendRedirect("index.jsp");
}

%>

<html>  
<body>  
<head>
<title>PROVIDER_SERVICE</title>

</head>
<form method="get" action="provider_services" >  
VIEW MY SERVICES:
<input type="submit" value="search" class="form-submit">  
</form>  
</body>  
</html> 