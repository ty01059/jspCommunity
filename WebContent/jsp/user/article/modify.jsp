<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Board"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
int memberId = (Integer) request.getAttribute("memberId");
int id = (Integer) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<h1>게시물 수정</h1>

	<div>
		<form action="doWrite" method="POST">
			<input type="hidden" name="memberId" value="<%=memberId%>" />

			<hr />
			<div>
				<div>제목</div>
				<div><input name="title" type="text" maxlength="50" placeholder="제목을 입력해주세요." /></div>
			</div>

			<hr />

			<div>
				<div>내용</div>
				<div>
					<textarea placeholder="내용을 입력해주세요." name="body" maxlength="5000"></textarea>
				</div>
			</div>
			<hr />
			<div>
				<div>작성</div>
				<div>
					<input type="submit" value="수정" />
					<button type="button" onclick="history.back();">뒤로가기</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>