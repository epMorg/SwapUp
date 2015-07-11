<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page listing user's active listings</title>
</head>
<body>
<br><b><c:out value="${sessionScope.activeListingsMessage}"/></b>
<table style="width:100%" border="1">
<tr>
	<th><b>Listing ID</b></th>
	<th><b>Listing Description</b></th>
	<th><b>Start Time</b></th>
	<th><b>Listing Keywords</b></th>
</tr>
<c:forEach items="${sessionScope.activeListings}" var="listing">
<tr>
	<td>${listing.listingID}</td>
	<td>${listing.listingDescription}</td>
	<td>${listing.startTime}</td>
	<td>${listing.listingKeywords}</td>
</tr>
</c:forEach>
</table>
</body>
</html>