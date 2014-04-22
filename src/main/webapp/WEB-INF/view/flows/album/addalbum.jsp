<!DOCTYPE html>


<head>
<%@ include file="/WEB-INF/view/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="utf-8">
<title>Add Album details</title>
</head>
<body>
	<h3>Add Album details</h3>
	<form id="step1" action="${flowExecutionUrl}" method="post">
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
		<b>Album Title : </b>
		<input type="text" name="title" />
		<br>
		<b>Artist : </b>
		<input type="text" name="artist" />
		<br>
		<button type="submit" id="next" name="_eventId_next">Next</button>
        <button type="submit" name="_eventId_cancel" >Cancel</button>
        
	</form>
	
</body>
</html>
