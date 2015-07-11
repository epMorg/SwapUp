<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submit Feedback Form</title>
</head>
<body>
<c:forEach items="${listings}" var="listing">
<c:choose>
<c:when test="${pageScope.listings.listingID == listing.listingID}" >
<form method="post" action="FeedbackServlet?action=displayListing&id=${pageScope.listings.listingID}">
<input type="submit" name="submit feedback" value="Submit Feedback" />
</form>
</c:when>
</c:choose>
</c:forEach>
</body>
</html>