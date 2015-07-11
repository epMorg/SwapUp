<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback Form</title>
</head>
<body> 
You are logged in as: ${sessionScope.username}
<center>
<h3>You are about to give feedback to transaction IDNo.: ${sessionScope.listingID}</h3>
<h3>Give approval points to your fellow barterer: ${sessionScope.transactionPartner}</h3>
<form method="post" action="FeedbackServlet?action=giveFeedback&listingId=${sessionScope.listingID}
		&offererUname=${sessionScope.offererUsername}&bidderUname=${sessionScope.bidderUsername}" method="post">
<input type="radio" name="feedback" value="positive" checked>Positive
<br>
<input type="radio" name="feedback" value="neutral">Neutral
<br>
<input type="radio" name="feedback" value="negative">Negative
<br>
<br>
<h3>Leave a short comment:</h3>
<textarea name="feedbackComment" cols="20" rows="5"></textarea>
<br>
<br>
<input type="submit" name="submit" value="Submit"> <input type="reset" name="submit" value="Reset">
</form>
</center>
</body>
</html>