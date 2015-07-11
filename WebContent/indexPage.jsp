<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<div style="margin:0 auto" align=center >
<form method="get" name="recentListingsForm" action="RecentListings" style= "background-color: #d3d3d3;">
<h3>General Listings</h3> 
<p>View the 
<input type="text" name="numberOfListings" value="" style="width: 35px;">
most recent Listings!</p>
<input type="submit" name="submit" value="Let's Go!">
</form>
</div>
<br><br><br>
<form method="get" name="searchForm" action="SearchController">
<table border="0" width="300" align="center" bgcolor="#d3d3d3">
<tr><td colspan=2 style="font-size:12pt;color:#00000;" align="center">
<h3>Looking for something in particular?</h3></td></tr>
<tr><td ><b>Enter keyword</b></td>
<td>: <input type="text" name="searchItem" id="name">
</td></tr>
<tr><td colspan=2 align="center">
<input type="submit" name="submit" value="Search"></td></tr>
</table>
</form>
<br><br><br>
<p align ="center"><a href = "ReturnToProfilePageController">My Profile Page</a></p>
</body>
</html>