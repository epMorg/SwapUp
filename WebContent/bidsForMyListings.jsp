<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bids for your Stuff!</title>
</head>
<body>
<form action = "AcceptBidController" method ="post">
<table border="1">
            <thead>
                <tr>
                	<th>Select</th>
                    <th>Bidder</th>
                    <th>Bidding for Listing ID</th>
                    <th>Details of Bid</th>
                    <th>Posted since</th>
                   
                </tr>
            </thead>
            <tbody>
               <c:forEach var="bid" items="${bidsToMeList}">
                <tr>
                	<td><input type = "checkbox" name="acceptBid" value = "${bid.associatedListingID}"/></td>
                    <td><c:out value="${bid.userName}" /></td>
                     <td><c:out value="${bid.associatedListingID}" /></td>
                     <td><c:out value="${bid.bidText}" /></td>
                      <td><c:out value="${bid.dateOfBid}" /></td>
                </tr>
                 </c:forEach>
            </tbody>
        </table>
        <input type ="submit" name = "submit"  value = "Accept This Bid!" />
        </form>
</body>
</html>