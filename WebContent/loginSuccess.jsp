<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<table cellpadding=4 cellspacing=2 border=0>
<tr>
	<th colspan=2>
	<font size=5>CONGRATULATIONS, YOUR REGISTRATION WAS A SUCCESS!</font>
	<br>
</tr>
<tr>
	<td valign=top> 
	<b>NUIG Student ID:</b> 
	<br>
	<%= request.getAttribute("userStudentID")%>
	</td>
</tr>
<tr>
	<td valign=top>
	<b>User Name:</b>
	<br>
	<%= request.getAttribute("userName")%>
	</td>
</tr>
<tr>
	<td valign=top>
	<b>E-Mail:</b> 
	<br>
	<%= request.getAttribute("userEmail")%>
	</td>
</tr>
<tr>
	<td valign=top>
	<b>Gender:</b>
	<br>
	<%= request.getAttribute("userGender") %>
	</td>
</tr>
</table>
</center>
<p>Visit the login page<a href="login.html"> here</a>
</body>
</html>