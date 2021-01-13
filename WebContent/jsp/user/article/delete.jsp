<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int memberId = (Integer) request.getAttribute("memberId");
int id = (Integer) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 삭제</title>
</head>
<body>
	<h1>게시물 삭제</h1>
	<div>
		<form action="doDelete" method="POST">
			<input type="hidden" name="memberId" value="<%=memberId%>" />
			<input type="hidden" name="id" value="<%=id%>" />

			<hr />
			<div>
				<input type="submit" value="삭제" />
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</form>
	</div>
</body>
</html>