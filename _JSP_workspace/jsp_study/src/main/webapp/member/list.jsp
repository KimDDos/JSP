<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List Page (관리자용)</title>
</head>
<body>
	<h1>Member List Page (관리자용)</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>PW</th>
			<th>E-mail</th>
			<th>age</th>
			<th>reg-date</th>
			<th>Last-login</th>
		</tr>
		<c:forEach items="${list }" var="mvo">
		<tr>
			<th>${mvo.id }</th>
			<th>${mvo.pwd }</th>
			<th>${mvo.email }</th>
			<th>${mvo.age }</th>
			<th>${mvo.regdate }</th>
			<th>${mvo.lastlogin }</th>
		</tr>
		</c:forEach>
	</table>
</body>
</html>