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
<p align ="center" ><a href="indexPage.jsp">Home Page</a></p>
<u><b>This is the page for Listing ID number</b></u>: <c:out value="${listing.listingID}" />
<br>
<br>
<u><b>Description</b></u>: <c:out value="${listing.listingDescription}" />
<br>
<br>
<u><b>Status</b></u>: <c:out value="${listing.listingState}"></c:out>
<br>
<br>
<u><b>Offered by</b></u>: <c:out value="${listing.offererUsername}"></c:out>
<br>
<br>
<br>
<br>
<form method ="post" action ="BiddingController?action=grabID&id=${listing.listingID}">
<input type="submit" name = "submit" value = "Make A Bid!" />
</form>
<h2>Bidding on this Listing so far:</h2>
 <table border="1">
            <thead>
                <tr>
                    <th>Bidder</th>
                    <th>Details of Bid</th>
                    <th>Posted since</th>
                   
                </tr>
            </thead>
            <tbody>
               <c:forEach var="bid" items="${bidList}">
                <tr>
                    <td><c:out value="${bid.userName}" /></td>
                     <td><c:out value="${bid.bidText}" /></td>
                      <td><c:out value="${bid.dateOfBid}" /></td>
                </tr>
                 </c:forEach>
            </tbody>
        </table>
</body>
</html>