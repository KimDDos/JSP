<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 페이지</title>
</head>
<body>
	<h1>Board Register Page</h1>
	<form action="/brd/insert" method="post">
		제   목 : <br><input type="text" name="title"> <br>
		작 성 자 : <br><input type="text" name="writer"> <br>
		내   용 : <br><textarea rows="10" cols="30" name="content"></textarea><br>
		<button type="submit">전송</button><br>
	</form>
		<button type="button">리스트로</button>
</body>
</html>