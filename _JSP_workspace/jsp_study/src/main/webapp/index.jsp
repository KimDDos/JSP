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
	<h1> Hello my first JSP world </h1>
	<!-- 
	get : 이동(적은 데이터를 이동) => 주쇼피줄에 데이터를 ? 쿼리스트링으로 달고 이동
	post : 등록(많은 데이터를 이동) 별도의 저장공간에 담아서 이동 
	-->
	
	<form action="/memb/login" method="post">
		ID : <input type="text" name="id" placeholder="ID">
		password : <input type="password" name="pwd" placeholder="password">
		<button type="submit">Login</button>
	</form>
	
	<div>
	<hr>
		<!-- ne : not / eq : equals -->
		<c:if test="${ses.id ne null }">
			${ses.id }님이 login 하셧습니다. <br>
			계정생성일 : ${ses.regdate } <br>
			마지막 접속 : ${ses.lastlogin } <br>
			<a href="/memb/detail"><button>회원정보수정</button></a>
			<a href="/memb/list"><button>회원리스트</button></a>
			<a href="/memb/logout"><button>로그아웃</button></a><br>
			<a href="/brd/register"><button type="button">글쓰기 페이지로 이동</button></a>
		</c:if>
		<c:if test="${msg_modify == 1}">
			<p>로그아웃 성공!!</p>
		</c:if>
		
		
	</div>
	
	<hr>
	<a href="/memb/join"><button>회원가입</button></a>
	<a href="/brd/list"><button type="button">게시판 리스트로 이동</button></a>
	<!-- a태그는 register.jsp로 가는것이 아니라 콘트롤러로 가야함 -->
	
	<script type="text/javascript">
		const msg_login = `<c:out value="${msg_login}"/>`;
		const msg_modify = `<c:out value="${msg_modify}"/>`;
		const msg_remove = `<c:out value="${msg_remove}"/>`;
		console.log(msg_login);
		console.log(msg_modify);
		if(msg_login == '-1'){
			alert('로그인 정보가 일치하지 않습니다.');
		}
		
		if(msg_modify == '1'){ 
			alert("회원정보 수정 성공!");
			alert("로그아웃 성공!");
		} else if(msg_modify == '-1') {
			alert("회원정보 수정 실패!");
			alert("로그아웃 성공!");
		}
		
		if(msg_remove == '1') {
			alert("회원탈퇴 성공!");
		} else if(msg_remove == '-1') {
			alert("회원탈퇴 실패!");
		}
		
		
	</script>
</body>
</html>