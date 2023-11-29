<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			<a href="/brd/modify?bno=${bvo.bno }"><button>게시글 수정</button></a>
			<a href="/brd/remove?bno=${bvo.bno }"><button>게시글 삭제</button></a>
		</div>
		<hr>
		<div class="rightside">
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
		</div>
	</div>	
</body>
</html>