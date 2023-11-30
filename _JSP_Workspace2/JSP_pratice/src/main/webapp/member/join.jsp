<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Page</title>
</head>
<body>
	<header>
		<div class="topheader">
			<h1>Hello my second dynamic Web Project</h1>
		</div>
		<c:if test="${ses.id eq null}">
			<div class="header-login">
				<form action="/memb/login">
					<div class="login">ID : <input type="text" name="id" placeholder="ID를 입력해주세요."></div>
					<div class="Password">PW : <input type="text" name="pwd" placeholder="Password를 입력해주세요."></div>
					<button type="submit">Login</button>
				</form>
				<a href="/memb/join"><button>회원가입</button></a>
			</div>
		</c:if>
		<c:if test="${ses.id ne null}">
			<div class="header-login">
				${ses.id }님이 login 하셧습니다. <br>
				계정생성일 : ${ses.regdate } <br>
				마지막 접속 : ${ses.lastlogin } <br>
				<a href="/memb/detail?id=${ses.id }"><button>회원정보수정</button></a>
				<a href="/memb/list"><button>회원리스트</button></a>
				<a href="/memb/logout?id=${ses.id }"><button>로그아웃</button></a><br>
			</div>
		</c:if>
	</header>
	<hr>

	<div class="container">
		<form action="/memb/signup">
			ID : <input type="text" name="id" placeholder="ID를 입력해주세요!"> <br>
			Password : <input type="text" name="pwd" placeholder="Password를 입력해주세요!"><br>
			E-Mail :  <input type="text" name="email" placeholder="E-Mail을 입력해주세요!">		<br>
			age :  <input type="text" name="age" placeholder="나이를 입력해주세요!"><br>
			<button type="submit">회원가입</button>
		</form>		
	</div>	
</body>
</html>