<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${sessionScope.username}"/>'s Web of Trust</title>
</head>
<body>
<!--<c:set var="webOfTrust" value="session" scope="session"></c:set>-->
<center>
<b>This is your Web of Trust <c:out value="${sessionScope.username}"/></b>
</center>
<br>
<h4><c:out value="${sessionScope.message}"/></h4>
<h4>
<c:forEach var="node" items="${nodes}">${node.connectTo} <form method="post" action="TrustServlet?action=severABond&username=${sessionScope.username}&bartererName=${node.connectTo}">
<input type="submit" value="Sever the Bond Of Trust"></form></c:forEach>
</h4>
<h4>Maybe consider establishing a Bond of Trust with some of your bartering partners: </h4>
<h4><c:out value="${sessionScope.noTransactions}"/></h4>
<c:set var="user" value="${sessionScope.users}" scope="session"></c:set>
<c:forEach items="${users}" var="user">
<a href="TrustServlet?action=seeBartererPage&barterername=${user.userName}" title="See Data About the User">${user.userName},</a>
<form method="post" action="TrustServlet?action=establishABond&username=${sessionScope.username}&bartererName=${user.userName}">
<input type="submit" value="Establish a Bond Of Trust"></form>
</c:forEach>
</body>
</html>