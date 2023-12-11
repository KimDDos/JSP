<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
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
		      <h1>my Second JSP Project, Board List Page!</h1>
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
							<div class="Password">PW : <input type="password" name="pwd" placeholder="Password를 입력해주세요."></div>
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
						</div>
					</c:if>
			    </div>
			  </div>
		  </div>
		</div>
	</header>
	<hr> 
	<c:if test="${ses.id ne null}">
		<div class="container text-center">
			<div class="row row-cols-4">
			    <div class="col"><a href="/brd/myboard?id=${ses.id }"><button>내가 쓴 글</button></a></div>
			    <div class="col"><a href="/memb/detail?id=${ses.id }"><button>회원정보수정</button></a></div>
			    <div class="col"><a href="/memb/list"><button>회원리스트</button></a></div>
			    <div class="col"><a href="/memb/logout?id=${ses.id }"><button>로그아웃</button></a></div>
		    </div>
		</div>
	<hr> 
	</c:if>

	<div class="container">
		<!-- Paging 작업 이후 밑으로 이동 -->
		<div class="leftside">
			<a href="/brd/list"><button>게시글 보기 <span class="badge text-bg-info">${ph.totalCount }</span></button></a>
			<a href="/brd/register"><button>게시글 작성</button></a>
		</div>
		<hr>
		<div class="rightside">
			<table class="table table-hover table-primary">
				<tr class="table-info">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				<c:forEach items="${list }" var="bvo">
					<tr>
						<td class="table-light"><a href="/brd/detail?bno=${bvo.bno }">${bvo.bno }</a></td>
						<td class="table-light"><a href="/brd/detail?bno=${bvo.bno }">
						<img alt="" src="/_fileUpload/_th_${bvo.imageFile }">
						${bvo.title }</a></td>
						<td class="table-light">${bvo.writer }</td>
						<td class="table-light">${bvo.moddate }</td>
						<td class="table-light">${bvo.readCount}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- 페이지네이션 표시 구역 -->
		<div class="container text-center">
		  <div class="row justify-content-md-center">
		    <div class="col col-lg-2">
		    	<!-- prev -->
		        <c:if test="${ph.prev }">
				  <a href="/brd/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> <i class="bi bi-chevron-left"></i> </a>
			    </c:if>
		    </div>
		    <div class="col-md-auto">
		    	<!-- paging -->
				<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
					<a href="/brd/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> ${ i }</a>
				</c:forEach>
		    </div>
		    <div class="col col-lg-2">
			    <!-- next -->
				<c:if test="${ph.next }">
					<a href="/brd/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> <i class="bi bi-chevron-right"></i> </a>
				</c:if>
		    </div>
		  </div>
		</div>
		<hr>
		<div class="container text-center">
			<form action="/brd/list" method="get">
				<div class="row">
				    <div class="col-sm-8">
				    	<input type="text" name="keyword" placeholder="검색할 단어를 입력해주세요." value="${ph.pgvo.keyword }">
				    	<input type="hidden" name="pageNo" value="1">
				    	<input type="hidden" name="qty" value="${ph.pgvo.qty}">
				    	<button type="submit"> 검 색 </button>
				    </div>
			    	<div class="col-sm-4">
						<c:set value="${ph.pgvo.type }" var="typed"/>
						<select class="form-select form-select-sm" aria-label="Small select example" name="type">
						  <option ${ typed == null ? 'selected' : ''}>전체글 보기</option>
						  <option value="t" ${ typed eq 't' ? 'selected' : ''}>제목만</option>
						  <option value="w" ${ typed eq 'w' ? 'selected' : ''}>작성자만</option>
						  <option value="c" ${ typed eq 'c' ? 'selected' : ''}>내용만</option>
						  <option value="tc" ${ typed eq 'tc' ? 'selected' : ''}>제목 + 내용</option>
						  <option value="tw" ${ typed eq 'tw' ? 'selected' : ''}>제목 + 작성자</option>
						  <option value="wc" ${ typed eq 'wc' ? 'selected' : ''}>작성자 + 내용</option>
						  <option value="twc" ${ typed eq 'twc' ? 'selected' : ''}>제목 + 작성자 + 내용</option>
						</select>
			    	</div>
			    </div>
			</form>
		</div>
	</div>	
	
</body>
</html>