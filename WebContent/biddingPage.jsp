<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bidding Page</title>
</head>
<body>
Bidding for : Listing ID no. <c:out value="${soughtListingID}"/>
<h2> Outline your bid and hit post! </h2>
<form action = "BiddingController?action=dbInsert&listingID=${soughtListingID}" method ="post"> 
<textarea rows="10" cols="30" name = "bid"></textarea>
<input type = "submit" name = "submit" value = "Post Bid!" />
</form>
</body>
</html>