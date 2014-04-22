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
	<h3>Albums</h3>
	<p>
			<br /> <a class="logout"
				href="<c:url value="/j_spring_security_logout"/>">Logout</a> (
			<security:authentication property="principal.username" />
			)
		</p>
	<table style="width: 400px">
		<tr>
			<th>Artist</th>
			<th>Title</th>
			<th>Delete?</th>
		</tr>
		<c:forEach items="${albums}" var="album">
			<tr>
				<td><c:out value="${album.artist}" /></td>
				<td><a href="<c:url value='/albumdetails/${album.albumID}'/>">${album.title}</a></td>
				<td><a href="<c:url value='/deletealbum/${album.albumID}'/>">Delete</a></td>
			</tr>

		</c:forEach>
	</table>
	<a href="<c:url value='/'/>">return home</a>
</body>
</html>
