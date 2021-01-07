<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%
	Map<String, Object> articleMapDetail = (Map<String, Object>) request.getAttribute("articleMapDetail");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세정보</title>
</head>
<body>
	<h1>게시물 상세</h1>
	<div>
		번호 :
		<%=articleMapDetail.get("id")%>
		<br />
		작성시간 :
		<%=articleMapDetail.get("regDate")%>
		<br />
		제목 :
		<%=articleMapDetail.get("title")%>
		<br />
		내용 :
		<%=articleMapDetail.get("body")%>
		<br />
		사용자 아이디 :
		<%=articleMapDetail.get("articleId")%>
		<br />
		게시판 번호 :
		<%=articleMapDetail.get("boardId")%>
		<hr />
	</div>
</body>
</html>