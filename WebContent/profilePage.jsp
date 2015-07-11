<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${sessionScope.username}"/>'s Profile Page</title>
</head>
<body>
<p align ="center" ><a href="indexPage.jsp">Home Page</a></p>
<c:set var="profile" value="session" scope="session"></c:set>
<b>Welcome <c:out value="${sessionScope.username}"/></b>
<br>
Your Profile Photo: 
<img src="PhotoServlet?action=profilePage" alt="users' profile pic" style="width:180px;height:200px" />
<form action="/MVCRegistrationAndLogin/uploadPhoto.html">
<input type="submit" value="Upload Profile Photo">
</form>
<br>
Your Student ID: <c:out value="${sessionScope.studentID}"/>
<br>
Your email address: <c:out value="${sessionScope.email}"/>
<br>
Your gender: <c:out value="${sessionScope.gender}"/>
<br>
About Me: <c:out value="${sessionScope.profileBlurb}"/>
<form action="/MVCRegistrationAndLogin/editProfile.html">
<input type="submit" value="Edit Profile" />
</form>
<br>
<table style="width:100%" border="1">
<tr>
<th><h5>Approval Points</h5></th>
<th><h5>Positive Approval Points</h5></th>
<th><h5>Neutral Approval Points</h5></th>
<th><h5>Negative Approval Points</h5></th>
</tr>
<tr>
<td><h4><c:out value="${sessionScope.approvalPoints}" /></h4></td>
<td><h4><c:out value="${sessionScope.positiveAP}" /></h4></td>
<td><h4><c:out value="${sessionScope.neutralAP}" /></h4></td>
<td><h4><c:out value="${sessionScope.negativeAP}" /></h4></td>
</tr>
</table>
<br>
Your social role: <c:out value="${sessionScope.socialRole}"/>
<br>
See my listings:
<br>
<form action="TransactionController" method="get">
<table>
<tr>
<td>
<input type="submit" name="chooseTransaction" value="active">
</td>
<td>
<input type="submit" name="chooseTransaction" value="closed">
</td>
</tr>
</table>
</form>
<form action="BidsToMeController" method="post">
<input type="submit" name="chooseTransaction" value="View Bids For My Stuff">
</form>
<br>
Make a listing:
<form action="/MVCRegistrationAndLogin/createListing.jsp">
<input type="submit" value="Create New Listing!" />
</form>
<br>
My Web of Trust:
<br>
<form action="TrustServlet" method="get">
<table>
<tr>
<td>
<input type="submit" name="action" value="Enter">
</td>
</tr>
</table>
</form>
<br>
<form action="LogoutServlet" method="get">
<input type="submit" value="Log Out">
</form>
</body>
</html>