package com.sbs.example.jspCommunity.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.service.ArticleService;

public class UserArticleController {
	private ArticleService articleService;

	public UserArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));

		List<Article> articles = articleService.getForPrintArticlesByBoardId(boardId);
		Board board = articleService.getBoardById(boardId);

		req.setAttribute("board", board);
		req.setAttribute("articles", articles);

		return "user/article/list";
	}

	public String showDetail(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));

		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		req.setAttribute("article", article);

		return "user/article/detail";
	}

	public String showWrite(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int boardId = Integer.parseInt(req.getParameter("boardId"));
		Board board = articleService.getBoardById(boardId);

		req.setAttribute("board", board);
		return "user/article/write";
	}

	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		Article article = new Article();
		article.setMemberId((int)session.getAttribute("loginedMemberId"));
		article.setBoardId(Integer.parseInt(req.getParameter("boardId")));
		article.setTitle(req.getParameter("title"));
		article.setBody(req.getParameter("body"));

		int newArticleId = articleService.write(article);

		req.setAttribute("alertMsg", newArticleId + "번 게시물이 생성되었습니다.");
		req.setAttribute("replaceUrl", String.format("detail?id=%d", newArticleId));
		return "common/redirect";
	}

	public String showModify(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int id = Integer.parseInt(req.getParameter("id"));
		int loginedMemberId = (int)session.getAttribute("loginedMemberId");

		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		if (article.getMemberId() != loginedMemberId) {
			req.setAttribute("alertMsg", id + "번 게시물에 대한 권한이 없습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		Board board = articleService.getBoardById(article.getBoardId());

		req.setAttribute("board", board);
		req.setAttribute("article", article);
		return "user/article/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int id = Integer.parseInt(req.getParameter("id"));
		int loginedMemberId = (int)session.getAttribute("loginedMemberId");

		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (article.getMemberId() != loginedMemberId) {
			req.setAttribute("alertMsg", id + "번 게시물에 대한 권한이 없습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		article.setTitle(req.getParameter("title"));
		article.setBody(req.getParameter("body"));

		articleService.modify(article);

		int boardId = article.getBoardId();

		req.setAttribute("alertMsg", id + "번 게시물이 수정되었습니다.");
		req.setAttribute("replaceUrl", String.format("list?boardId=%d", boardId));
		return "common/redirect";
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int id = Integer.parseInt(req.getParameter("id"));
		int loginedMemberId = (int)session.getAttribute("loginedMemberId");

		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (article.getMemberId() != loginedMemberId) {
			req.setAttribute("alertMsg", id + "번 게시물에 대한 권한이 없습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		articleService.delete(id);

		int boardId = article.getBoardId();

		req.setAttribute("alertMsg", id + "번 게시물이 삭제되었습니다.");
		req.setAttribute("replaceUrl", String.format("list?boardId=%d", boardId));
		return "common/redirect";
	}
}