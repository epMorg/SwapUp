<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Retry</title>
</head>
<body>
<form action="RegistrationController" method=post>
<center>
<table cellpadding=4 cellspacing=2 border=0>
<tr>
	<th colspan=2>
	<font size=5>THERE WAS A PROBLEM WITH YOUR REGISTRATION. PLEASE TRY AGAIN.</font>
	<br>
	<font size=1><sup>*</sup> Required Fields</font>
</tr>
<tr>
	<td valign=top> 
	<b>NUIG Student ID<sup>*</sup></b> 
	<br>
	<input type="text" name="userStudentID" 
	value='<%= request.getAttribute("userStudentID")%>' size=15 maxlength=20 
	>
	<font size=2 color=red>
	<%= request.getAttribute("studentIDErrMsg")%>
	</font>
	</td>
</tr>
<tr>
	<td valign=top>
	<b>User Name<sup>*</sup></b>
	<br>
	<input type="text" name="userName" size=10 
	value='<%= request.getAttribute("userName")%>'  maxlength=10 
	>
	<font size=2 color=red>
	<%= request.getAttribute("userNameErrMsg")%>
	</font>
	</td>
</tr>
<tr>
	<td valign=top>
	<b>Password<sup>*</sup></b> 
	<br>
	<input type="password" name="userPassword1" size=10 
	value='<%= request.getAttribute("userPassword1")%>' maxlength=10 >
	<font size=2 color=red>
	<%= request.getAttribute("userPassword1ErrMsg")%>
	</font>
	</td>
</tr>
<tr>
	<td  valign=top>
	<b>Confirm Password<sup>*</sup></b>
	<br>
	<input type="password" name="userPassword2" size=10 
	value='<%= request.getAttribute("userPassword2")%>' maxlength=10>
	<font size=2 color=red>
	<%= request.getAttribute("userPassword2ErrMsg")%>
	</font>
	</td>
</tr>
<tr>
	<td valign=top>
	<b>E-Mail<sup>*</sup></b> 
	<br>
	<input type="text" name="userEmail" 
	value='<%= request.getAttribute("userEmail")%>' size=25  maxlength=125>
	<font size=2 color=red>
	<%= request.getAttribute("userEmailErrMsg")%>
	</font>
	</td>
</tr>
<tr>
	<td valign=top>
	<b>Gender<sup>*</sup></b>
	<br>
	<input type="radio" name="chooseGender" value=
	'<%= request.getAttribute("userGender") %>'>Male
	<input type="radio" name="chooseGender" value='<%= request.getAttribute("userGender") %>'>Female
	</td>
</tr>
<tr>
<td  align=left colspan=2>
<input type="submit" value="Resubmit"> <input type="reset" value="Reset">
</td>
</tr>
</table>
</center>
</form>
</body>
</html>