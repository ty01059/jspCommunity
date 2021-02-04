<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${article.extra__boardName} 게시물 상세페이지" />

<%@ include file="../../part/head.jspf"%>
<h1>${pageTitle}</h1>

<div>
	번호 : ${article.id} <br /> 작성날짜 : ${article.regDate} <br /> 갱신날짜 :
	${article.updateDate} <br /> 작성자 : ${article.extra__writer} <br /> 제목
	: ${article.title} <br />
	<script type="text/x-template">${article.body}</script>
	<div class="toast-ui-viewer"></div>
</div>

<hr />

<div>
	<c:if test="${article.extra.actorCanLike}">
		<a class="btn btn-primary"
			href="../like/doLike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}"
			onclick="if ( !confirm('`좋아요` 처리 하시겠습니까?') ) return false;"> <span>
				<i class="fas fa-thumbs-up"></i>
		</span> <span>좋아요</span>
		</a>
	</c:if>

	<c:if test="${article.extra.actorCanCancelLike}">
		<a class="btn btn-info"
			href="../like/doCancelLike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}"
			onclick="if ( !confirm('`좋아요`를 취소 처리 하시겠습니까?') ) return false;">
			<span><i class="fas fa-slash"></i></span> <span>좋아요 취소</span>
		</a>
	</c:if>

	<c:if test="${article.extra.actorCanDislike}">
		<a class="btn btn-danger"
			href="../like/doDislike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}"
			onclick="if ( !confirm('`싫어요` 처리 하시겠습니까?') ) return false;"> <span><i
				class="fas fa-thumbs-down"></i></span> <span>싫어요</span>
		</a>
	</c:if>

	<c:if test="${article.extra.actorCanCancelDislike}">
		<a class="btn btn-info"
			href="../like/doCancelDislike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}"
			onclick="if ( !confirm('`싫어요`를 취소 처리 하시겠습니까?') ) return false;">
			<span><span><i class="fas fa-slash"></i></span></span> <span>싫어요
				취소</span>
		</a>
	</c:if>

	<a href="list?boardId=${article.boardId}">리스트로 이동</a> <a
		href="modify?memberId=${article.memberId}&id=${article.id}">게시물 수정</a>
	<a onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) { return false; }"
		href="doDelete?id=${article.id}&memberId=${article.memberId}">게시물
		삭제</a> <a href="list?boardId=${article.boardId}">리스트로 이동</a>
</div>

<%@ include file="../../part/foot.jspf"%>