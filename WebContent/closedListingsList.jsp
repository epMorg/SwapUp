<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Listings</title>
</head>
<body>
<!--<c:set var="listingsList" value="session" scope="session"></c:set>-->
<br><b><c:out value="${sessionScope.closedListingsMessage}"/></b>
<table style="width:100%" border="1">
<tr>
	<th><b>Listing ID</b></th>
	<th><b>Listing Keywords</b></th>
	<th><b>Username of Offerer</b></th>
	<th><b>Username of the Winning Bidder</b>
	<th><b>Feedback</b>
</tr>
<c:forEach items="${closedListings}" var="listing">
<tr>
	<td> 
		<a href="ListingServlet?action=displayListing&id=${listing.listingID}">
			${listing.listingID}
		</a>
	</td>
	<td>${listing.listingKeywords}</td>
	<td>${listing.offererUsername}</td>
	<td>${listing.bidderUsername}</td>
	<c:choose>
		<c:when test="${listing.feedbackQuantity eq 0}">
		<td>
		<a href="FeedbackServlet?action=leaveFeedback&listingId=${listing.listingID}&offererUname=${listing.offererUsername}&bidderUname=${listing.bidderUsername}">Submit Feedback</a>
		</td>
		</c:when>
		<c:otherwise>
		<td>
			<a href="FeedbackServlet?action=seeFeedback&listingId=${listing.listingID}&offererUname=${listing.offererUsername}&bidderUname=${listing.bidderUsername}">See Feedback</a>
			</td>
		</c:otherwise>
	</c:choose>
</tr>
</c:forEach>
</table>
</body>
</html>