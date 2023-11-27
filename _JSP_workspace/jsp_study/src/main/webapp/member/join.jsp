<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join page</title>
</head>
<body>
	<h1>Join Page</h1>
	<br>
	
	<form action="/memb/register" method="post">
		ID : <input type="text" placeholder="ID를 입력해주세요" name="id"> <br>
		Password : <input type="password" placeholder="password를 입력해주세요" name="pwd"><br>
		E-mail : <input type="text" name="email" placeholder="E-mail을 입력해주세요."><br>
		Age : <input type="text" name="age" placeholder="나이를 입력해주세요."><br>
		<button type="submit">join</button>
	</form>
</body>
</html>