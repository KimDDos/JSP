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
	<hr>

	<div class="container">
		<div class="leftside">
			<a href="/brd/list"><button>게시글 보기</button></a>
			<a href="/brd/register"><button>게시글 작성</button></a>
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
		<hr>
		<div>
			<a href="/brd/modify?bno=${bvo.bno }"><button>게시글 수정</button></a>
			<a href="/brd/remove?bno=${bvo.bno }"><button>게시글 삭제</button></a>
		</div>
		<!-- commentLine -->
		<hr>
		<div id="commentLine">
			<div>
				<div>cno, bno, writer, regdate</div>
				<div>
					<input name="content">
					<button>수 정</button><button>삭 제</button>
				</div>
			</div>
		</div>
		<hr>
		<div>
			Comment Line <br>
			<input type="text" id="cmtWriter" value="${ses.id }" readonly="readonly"><br>
			<input type="text" id="cmtText" placeholder="Add comment..." > <br>
			<button type="button" id="cmtAddBtn">댓글 등록</button>
		</div>
	</div>
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}"/>`;
		console.log(bnoVal);
	</script>
	<script src="/js/Board_detail.js"></script>	
	<script type="text/javascript">
		printCommentList(bnoVal);
	</script>
</body>
</html>