<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method = "post" action = "CreateListingController">
Please describe your listing:<br><br> 
<textarea name="listingDescription" rows=10 cols=30></textarea>
<br><br>
Please provide some keywords for search purposes:
<br><br>
<input type="text" name = "keywords"/>
<br><br>
<input type = "submit"  name = "submit" value = "Post it!"/>
</form>
</body>
</html>