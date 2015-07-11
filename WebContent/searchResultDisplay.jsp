<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result Display</title>
</head>
<body>
<p align ="center" ><a href="indexPage.jsp">Home Page</a></p>
<br><br>
<c:out value = "${noMessage}"/>
<br><br>
<h2><c:out value = "${yesMessage}"/></h2>
 <table border="1">
            <thead>
                <tr>
                    <th>Listing ID</th>
                    <th>Offered by</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Posted since</th>
                   
                </tr>
            </thead>
            <tbody>
               <c:forEach var="listing" items="${searchedListings}">
                <tr>
                    <td><a href = "IndividualListingController?action=displayIndPage&id=${listing.listingID}"><c:out value="${listing.listingID}"/></a></td>
                    <td><c:out value="${listing.offererUsername}" /></td>
                     <td><c:out value="${listing.listingDescription}" /></td>
                      <td><c:out value="${listing.listingState}" /></td>
                        <td><c:out value="${listing.startTime}" /></td>
                </tr>
                 </c:forEach>
            </tbody>
        </table>
    </body>
</html>