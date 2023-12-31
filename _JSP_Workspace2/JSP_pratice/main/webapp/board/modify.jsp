<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
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
				<div class="container text-center">
					<div class="row row-cols-4">
					    <div class="col"><a href="/brd/myboard?id=${ses.id }"><button>내가 쓴 글</button></a></div>
					    <div class="col"><a href="/memb/detail?id=${ses.id }"><button>회원정보수정</button></a></div>
					    <div class="col"><a href="/memb/list"><button>회원리스트</button></a></div>
					    <div class="col"><a href="/memb/logout?id=${ses.id }"><button>로그아웃</button></a></div>
				    </div>
				</div>
			</div>
		</c:if>
	</header>
	<hr>

	<div class="container">
		<div class="leftside">
			<a href="/brd/list"><button>게시글 보기</button></a>
			<a href="/brd/register"><button>게시글 작성</button></a>
		</div>
		<hr>
		<div class="rightside">
			<form action="/brd/modifyDetail" method="post" enctype="multipart/form-data">
				<table class="table-primary">
					<tr class="table-active">
						<th>게시글번호</th>
						<td><input type="text" name="bno" value="${bvo.bno }" readonly="readonly"></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" value="${bvo.title }"></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="writer" value="${bvo.writer }" readonly="readonly"></td>
					</tr>
					<tr>
						<th>작성일</th>
						<td><input type="text" name="regdate" value="${bvo.regdate }" readonly="readonly"></td>
					</tr>
					<tr>
						<th>수정일</th>
						<td><input type="text" name="moddate" value="${bvo.moddate }" readonly="readonly"></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td><input type="text" name="readCount" value="${bvo.readCount }" readonly="readonly"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td> <textarea name="content" rows="10" cols="20">${bvo.content }</textarea></td>
					</tr>
					<tr>
						<th>사진</th>
						<td>
							<input type="hidden" name="imageFile" value="${bvo.imageFile }">
							<input type="file" name="new_file" accept="imgae/png, imgae/jpg, imgae/gif, imgae/jpeg">
						</td>
					</tr>
				</table>
				<button type="submit">수정하기</button>
			</form>
		</div>
	</div>
</body>
</html>