<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div class="header-login">
			<form action="/memb/login">
				<div class="login">ID : <input type="text" name="id" placeholder="ID를 입력해주세요."></div>
				<div class="Password">PW : <input type="text" name="pwd" placeholder="Password를 입력해주세요."></div>
				<button type="submit">Login</button>
			</form>
		</div>
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