<!DOCTYPE html>
<%@ include file="/WEB-INF/view/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<title>Welcome 222</title>
<style>
table,th,td
{
border:1px solid black;
}
</style>
</head>
<body>
	<h3>Album details</h3>
	<p>
			<br /> <a class="logout"
				href="<c:url value="/j_spring_security_logout"/>">Logout</a> (
			<security:authentication property="principal.username" />
			)
		</p>
	<table style="width: 550px">
		<tr>
			<th>Album Title</th>
			<th>Song Name</th>
			<th>Duration</th>
		</tr>
		<c:forEach items="${album.songs}" var="song">
			<tr>
				<td><c:out value="${album.title}" /></td>
				<td><c:out value="${song.name}" /></td>
				<td><c:out value="${song.duration}" /></td>
			</tr>

		</c:forEach>
	</table>
	<a href="<c:url value='/showalbums'/>">return to albums</a>
	<br>
	<a href="<c:url value='/'/>">return home</a>
</body>
</html>
