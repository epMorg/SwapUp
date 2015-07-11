<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Individual Listing Page</title>
</head>
<body>
<u>This is the page for listing ID number</u>: <c:out value="${listing.listingID}" />
<br>
<br>
<u>The description for this listing</u>: <c:out value="${listing.listingDescription}" />

<br>
<br>
<u>The state of this listing is</u>: <c:out value="${listing.listingState}"></c:out>
<br>
<br>
<u>The offerer's username is</u>: <c:out value="${listing.offererUsername}"></c:out>
<br>
<br>
<u>The winning bidder's username is</u>: <c:out value="${listing.bidderUsername}"></c:out>
<br>
</body>
</html>