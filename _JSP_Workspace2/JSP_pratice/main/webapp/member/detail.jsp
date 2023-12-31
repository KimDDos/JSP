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
		<div class="container text-center">
		  <div class="row">
		    <div class="col">
		    
		    </div>
		    <div class="col-6">
		      <h1>Hello my Second JSP Project!</h1>
		    </div>
		    <div class="col">
		    
		    </div>
		  </div>
		  <div class="container text-center">
			  <div class="row">
			    <div class="col">
			      
			    </div>
			    <div class="col">
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
							${ses.id }님 환영합니다. <br>
							계정생성일 : ${ses.regdate } <br>
							최근접속일 : ${ses.lastlogin } <br>
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
			    </div>
			  </div>
		  </div>
		</div>
	</header>
	<hr> 
	<div class="container">
		<div class="leftside">
			<c:if test="${ses.id ne null}">
				<a href="/brd/list"><button>게시글 보기</button></a>
				<a href="/brd/register"><button>게시글 작성</button></a>
			</c:if>
			<c:if test="${ses.id eq null}">
				<a href="/brd/list"><button>게시글 보기</button></a>
			</c:if>
		</div>
		<hr>
		<div class="rightside">
			<p>회원정보를 수정해주세요.</p>
			<form action="/memb/modify">
				ID : <input type="text" name="id" value="${ses.id }" readonly="readonly"> <br>
				PW : <input type="text" name="pwd" value="${ses.pwd }"> <br>
				E-Mail : <input type="text" name="email" value="${ses.email }"><br>
				나이 : <input type="text" name="age" value="${ses.age }"><br>
				가입일 : <input type="text" name="regdate" value="${ses.regdate }" readonly="readonly"><br>
				마지막 접속일 : <input type="text" name="lastlogin" value="${ses.lastlogin }" readonly="readonly"><br>
				<button type="submit">수정</button>
			</form>
				<a href="/memb/signout?id=${ses.id }"><button>회원탈퇴하기</button></a>
		</div>
	</div>	
</body>
</html>