<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<h1> list </h1>
	
	<!-- search line -->
	<div>
		<form action="/brd/list" method="get">
			<c:set value="${ph.pgvo.type }" var="typed"/>
				<select class="form-select" name="type">
					<!-- selected : 화면이 리로드되어도 그 벨류값이 선택된채로 나옴 -->
					<option ${typed == null ? 'selected':''}>choose....</option>
					<option value="t" ${typed eq 't' ? 'selected':''}>title</option>
					<option value="w" ${typed eq 'w' ? 'selected':''}>writer</option>
					<option value="c" ${typed eq 'c' ? 'selected':''}>content</option>
					<option value="tc" ${typed eq 'tc' ? 'selected':''}>title&content</option>
					<option value="tw" ${typed eq 'tw' ? 'selected':''}>title&writer</option>
					<option value="wc" ${typed eq 'wc' ? 'selected':''}>writer&content</option>
					<option value="twc" ${typed eq 'twc' ? 'selected':''}>title&writer&content</option>
				</select>
			<input type="text" name="keyWord" placeholder="search" value="${ph.pgvo.keyword }">
			<input type="hidden" name="pageNo" value="1">
			<input type="hidden" name="qty" value="${ph.pgvo.qty }">
			<button type="submit">Search</button>
			<span>${ph.totalCount}</span>
		</form>
		<br>
	</div>
	
	<table class="table table-hover">
		<tr>
			<th>bno</th>
			<th>title</th>
			<th>writer</th>
			<th>regdate</th>
			<th>readCount</th>
		</tr>
		<!-- DB에서 가져온 리스트를 c:foreach를 통해 반복 -->
		<c:forEach items="${list }" var="bvo">
			<tr>
				<td>
					<a href="/brd/detail?bno=${bvo.bno}">${bvo.bno }</a>
				</td>
				<td>
					<a href="/brd/detail?bno=${bvo.bno}">
					<c:if test="${bvo.imageFile ne null }">
						<img alt="" src="/_fileUpload/_th_${bvo.imageFile}">
					</c:if>
					${bvo.title }</a>
				</td>
				<td>${bvo.writer }</td>
				<td>${bvo.regdate }</td>
				<td>${bvo.readCount }</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 페이지네이션 표시 구역 -->
	<div>
		<!-- prev -->
		<c:if test="${ph.prev }">
			<a href="/brd/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyWord=${ph.pgvo.keyword}"> ◁ | </a>
		</c:if>
		<!-- paging -->
		<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
			<a href="/brd/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyWord=${ph.pgvo.keyword}"> ${i} </a>
		</c:forEach>
		<!-- next -->
		<c:if test="${ph.next }">
			<a href="/brd/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyWord=${ph.pgvo.keyword}"> | ▷ </a>
		</c:if>
	</div>
	
	<br>
	<a href="/brd/register"><button type="button">register</button></a>
	<a href="/index.jsp"><button type="button">index</button></a>
</body>
</html>