<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<body>
	<header>
		<div class="topheader">
			<h1>Hello my second dynamic Web Project</h1>
		</div>
		<c:if test="${ses.id eq null }">
			<div class="header-login">
				<form action="/#">
					<div class="login">ID : <input type="text" name="id" placeholder="ID를 입력해주세요."></div>
					<div class="Password">PW : <input type="text" name="pwd" placeholder="Password를 입력해주세요."></div>
					<button type="submit">Login</button>
				</form>
				<a href="/memb/join"><button>회원가입</button></a>
			</div>
		</c:if>
		<c:if test="${ses.id ne null }">
			<div class="header-login">
				${ses.id }님이 login 하셧습니다. <br>
				계정생성일 : ${ses.regdate } <br>
				마지막 접속 : ${ses.lastlogin } <br>
				<a href="/memb/detail"><button>회원정보수정</button></a>
				<a href="/memb/list"><button>회원리스트</button></a>
				<a href="/memb/logout"><button>로그아웃</button></a><br>
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
			
		</div>
	</div>	
	
	<script type="text/javascript">
		const msg_remove = `<c:out value="${msg_remove}"/>`;
		console.log(msg_remove);
		if(msg_remove=='1'){
			alert("게시글을 삭제 했습니다.");
		} else if(msg_remove=='-1'){
			alert("Error!!! 게시글을 삭제 실패  Error!!!");
		} 
		
		const msg_modify = `<c:out value="${msg_modify}"/>`;
		console.log(msg_modify);
		if(msg_modify=='1'){
			alert("게시글을 수정했습니다.");
		} else if(msg_modify=='-1'){
			alert("Error!!! 게시글을 수정 실패  Error!!!");
		} 
		
		const msg_insert = `<c:out value="${msg_insert}"/>`;
		console.log(msg_modify);
		if(msg_insert=='1'){
			alert("게시글 작성을 완료했습니다.");
		} else if(msg_insert=='-1'){
			alert("Error!!! 게시글을 작성 실패  Error!!!");
		} 
		
		const msg_login = `<c:out value="${msg_login}"/>`;
		if(msg_login == '-1'){alert('로그인 실패, 계정정보가 일치하지 않습니다!')}
	</script>
	
</body>
</html>