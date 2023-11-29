<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<div class="topheader">
			<h1>Hello my second dynamic Web Project</h1>
		</div>
		<div class="header-login">
			<form action="/#">
				<div class="login">ID : <input type="text" name="id" placeholder="ID를 입력해주세요."></div>
				<div class="Password">PW : <input type="text" name="pwd" placeholder="Password를 입력해주세요."></div>
				<button type="submit">Login</button>
			</form>
		</div>
	</header>
	<hr>

	<div class="container">
		<div class="leftside">
			<a href="/brd/list"><button>게시글 보기</button></a>
			<a href="/brd/register"><button>게시글 작성</button></a>
		</div>
		<hr>
		<div class="rightside">
			<table border="1">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				<c:forEach items="${list }" var="bvo">
					<tr>
						<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.bno }</a></td>
						<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.title }</a></td>
						<td>${bvo.writer }</td>
						<td>${bvo.moddate }</td>
						<td>${bvo.readCount}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>	
	
</body>
</html>