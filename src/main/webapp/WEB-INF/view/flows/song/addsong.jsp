<!DOCTYPE html>
<%@ include file="/WEB-INF/view/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<title>Add song details</title>
</head>
<body>
	<h3>Add song details</h3>
	<form id="step2" action="${flowExecutionUrl}" method="post">
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
		<b>Song Name : </b>
		<input type="text" name="name" />
		<br>
		<b>Song Duration : </b>
		<input type="text" name="duration" />
		<br>
		<button type="submit" id="add" name="_eventId_add">Add</button>
		<button type="submit" id="finish" name="_eventId_finish">Finish</button>
        <button type="submit" name="_eventId_cancel" >Cancel</button>
	</form>
</body>
</html>
