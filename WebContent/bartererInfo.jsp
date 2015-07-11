<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${sessionScope.bartererUname}" />'s Info Page</title></head>
<body>
<h3><u>This is <c:out value="${user.userName}" />'s Info Page</u></h3>
<h3>Profile Photo: </h3>
<img src="PhotoServlet?action=bartererInfo&bartererName=${sessionScope.bartererUname}" alt="users' profile pic" style="width:180px;height:200px" />
<h3>UserID: <c:out value="${user.userStudentID}" /></h3>
<h3>Joined on: <c:out value="${user.date}" /></h3>
<h3>User's gender: <c:out value="${user.userGender}" /></h3>
<h3>User's About Me: "<c:out value="${user.profileBlurb}" />"</h3>
<h3>User's social role: <c:out value="${user.socialRole}" /></h3>
<table style="width:100%" border="1">
<tr>
<th><h3>Approval Points</h3></th>
<th><h3>Positive Approval Points</h3></th>
<th><h3>Neutral Approval Points</h3></th>
<th><h3>Negative Approval Points</h3></th>
</tr>
<tr>
<td><h2><c:out value="${user.approvalPoints}" /></h2></td>
<td><h2><c:out value="${user.positiveAP}" /></h2></td>
<td><h2><c:out value="${user.neutralAP}" /></h2></td>
<td><h2><c:out value="${user.negativeAP}" /></h2></td>
</tr>
</table>
<h3>Users that <c:out value="${sessionScope.bartererUname}" /> trusts: <c:choose>
<c:when test="${empty connectedTos}">none</c:when><c:otherwise><c:forEach items="${connectedTos}" var="node">${node.connectTo}, 
</c:forEach></c:otherwise></c:choose></h3>
<h3>Users that trust <c:out value="${sessionScope.bartererUname}" />: 
<c:choose>
<c:when test="${empty vertices}">none</c:when><c:otherwise><c:forEach items="${vertices}" var="node">${node.vertex}, 
</c:forEach></c:otherwise></c:choose></h3>
<h3>Establish a Bond of Trust with this user by clicking on the below button:</h3>
<form method="post" action="TrustServlet?action=establishABond&username=${sessionScope.username}&bartererName=${user.userName}">
<input type="submit" value="Establish a Bond Of Trust"></form>
</body>
</html>