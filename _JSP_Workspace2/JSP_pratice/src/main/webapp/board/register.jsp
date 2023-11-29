<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
	<header>
		<div class="topheader">
			<h1>Board register Page</h1>
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
			<form action="/brd/insert">
				제 목 : <br><input type="text" name="title"> <br>
				작성자 : <br><input type="text" name="writer"> <br>
				내 용 : <br><textarea name="content" rows="10" cols="30"></textarea><br>
				<button type="submit">작성</button>
			</form>
		</div>
	</div>	
</body>
</html>