<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Page</title>
</head>
<body>
	<h1>detail page</h1>
	<table border="1">
		<tr>
			<th>게시글번호</th>
			<th>${bvo.bno } </th>
		</tr>
		<tr>
			<th>제목</th>
			<th>${bvo.title } </th>
		</tr>
		<tr>
			<th>작성자</th>
			<th> ${bvo.writer } </th>
		</tr>
		<tr>
			<th>작성일</th>
			<th> ${bvo.regdate } </th>
		</tr>
		<tr>
			<th>수정일</th>
			<th> ${bvo.moddate } </th>
		</tr>
		<tr>
			<th>조회수</th>
			<th> ${bvo.readCount } </th>
		</tr>
		<tr>
			<th>내용</th>
			<th> <textarea rows="10" cols="20" readonly="readonly">${bvo.content }</textarea></th>
		</tr>
	</table>
	<c:if test="${bvo.writer eq ses.id }">
	<a href="/brd/modify?bno=${bvo.bno}"><button>Modify</button></a>
	<a href="/brd/remove?bno=${bvo.bno}"><button>remove</button></a>
	</c:if>
	<a href="/brd/list"><button>list</button></a>
</body>
</html>