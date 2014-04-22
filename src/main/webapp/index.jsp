<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<title>Welcome to Challenge 2</title>
</head>
<body>
	<H1>Welcome to Challenge 2</H1>
	<p>Two users:<br> ROLE_USER is user/user<br> ROLE_API is api/api<br></p>
	<a href="<c:url value='/showalbums'/>">List Albums</a>
	<br>
	<a href="<c:url value='/createalbum'/>">Add Album</a>
</body>
</html>
