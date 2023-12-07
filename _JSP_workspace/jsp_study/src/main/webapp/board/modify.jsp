<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Page</title>
</head>
<body>
	<h1>detail page</h1>
	<form action="/brd/edit" method="post" enctype="multipart/form-data">
		<table border="1">
			<!-- 화면에 보여주지는 않을거지만 value를 DB로 가져가야 할 때는 hidden 속성을 많이 사용함 -->
			<%-- <input type="hidden" name="bno" value="${bvo.bno }"> --%>
			<tr>
				<th>게시글번호</th>
				<th><input type="text" name="bno" value="${bvo.bno }" readonly="readonly"></th>
			</tr>
			<tr>
				<th>제목</th>
				<th><input type="text" name="title" value="${bvo.title }"></th>
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
				<th><textarea title="content" name="content" rows="10" cols="30">${bvo.content }</textarea></th>
			</tr>
			<tr>
				<th>사진</th>
				<td>
					<input type="hidden" name="image_file" value="${bvo.imageFile }">
					<input type="file" name="new_file" accept="image/png, image/jpg, image/gif, image/jpeg">
				</td>
			</tr>
		</table>
		<button type="submit">Modify</button>
	</form>
	<a href="/brd/remove?bno=${bvo.bno}"><button>remove</button></a>
	<a href="/brd/list"><button>list</button></a>
</body>
</html>