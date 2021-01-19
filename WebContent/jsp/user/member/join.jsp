<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../part/head.jspf"%>
<c:set var="pageTitle" value="회원가입" />
<h1>${pageTitle}</h1>

<div>
	<form action="doJoin" method="POST" name="joinForm">
		<hr />
		<div>
			<div>로그인 아이디</div>
			<div>
				<input name="loginId" type="text" maxlength="50"
					placeholder="로그인 아이디를 입력해주세요." required
					oninvalid="this.setCustomValidity('로그인 아이디를 입력해주세요.')"
					oninput="this.setCustomValidity('')" />
			</div>
		</div>

		<hr />

		<div>
			<div>로그인 비번</div>
			<div>
				<input name="loginPw" type="password" maxlength="50"
					placeholder="로그인 비번을 입력해주세요." required />
			</div>
		</div>

		<hr />

		<div>
			<div>이름</div>
			<div>
				<input name="name" type="text" maxlength="50"
					placeholder="이름을 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>별명</div>
			<div>
				<input name="nickname" type="text" maxlength="50"
					placeholder="별명을 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>이메일</div>
			<div>
				<input name="email" type="email" maxlength="100"
					placeholder="이메일을 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>전화번호</div>
			<div>
				<input name="cellphoneNo" type="number" maxlength="100"
					placeholder="전화번호를 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>가입</div>
			<div>
				<input type="submit" value="가입" />
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>