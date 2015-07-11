<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Feedback</title>
</head>
<body>
<center>
<h3>You've requested to see feedback information about listing IDno. ${sessionScope.listingID} 
that involved <c:choose><c:when test="${sessionScope.username eq sessionScope.offererUsername}">
you as the offerer and ${sessionScope.bidderUsername} as the bidder.</c:when>
<c:otherwise>you as the bidder and ${sessionScope.offererUsername} as the offerer.
</c:otherwise></c:choose></h3></center>
<table style="width:100%" border="1">
<tr>
	<th><b>Date and Time</b></th>
	<th><b>Author</b></th>
	<th><b>Feedback</b></th>
	<th><b>Comment</b></th>
</tr>
<c:set var="feedback" value="${sessionScope.feedbacks}" scope="session"></c:set>
<c:forEach items="${feedbacks}" var="feedback">
<tr>
	<td>${feedback.date}</td>
	<td>${feedback.author}</td>
	<td>${feedback.feedbackValue}</td>
	<td>${feedback.feedbackComment}</td>
</tr>
</c:forEach>
</table>
</body>
</html>