package com.sbs.example.jspCommunity.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.jspCommunity.service.ArticleService;

public class ArticleController {
	private ArticleService articleService;

	public ArticleController() {
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
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		Board board = articleService.getBoardById(boardId);
		
		req.setAttribute("board", board);
		return "user/article/write";
	}
	
	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		String title = req.getParameter("title");
		String body = req.getParameter("body");

		Map<String, Object> writeArgs = new HashMap<>();
		writeArgs.put("memberId", memberId);
		writeArgs.put("boardId", boardId);
		writeArgs.put("title", title);
		writeArgs.put("body", body);

		int newArticleId = articleService.write(writeArgs);

		req.setAttribute("alertMsg", newArticleId + "번 게시물이 생성되었습니다.");
		req.setAttribute("replaceUrl", String.format("detail?id=%d", newArticleId));
		return "common/redirect";
	}
	
	public String showModify(HttpServletRequest req, HttpServletResponse resp) {
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int id = Integer.parseInt(req.getParameter("id"));
		
		req.setAttribute("memberId", memberId);
		req.setAttribute("id", id);
		return "user/article/modify";
	}
	
	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String body = req.getParameter("body");

		Map<String, Object> modifyArgs = new HashMap<>();
		modifyArgs.put("memberId", memberId);
		modifyArgs.put("title", title);
		modifyArgs.put("body", body);
		modifyArgs.put("id", id);

		articleService.modify(modifyArgs);
		
		req.setAttribute("alertMsg", id + "번 게시물이 수정되었습니다.");
		return "common/redirect";
	}

	public String showDelete(HttpServletRequest req, HttpServletResponse resp) {
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int id = Integer.parseInt(req.getParameter("id"));
		
		req.setAttribute("memberId", memberId);
		req.setAttribute("id", id);
		return "user/article/delete";
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		int memberId = Integer.parseInt(req.getParameter("memberId"));
		int id = Integer.parseInt(req.getParameter("id"));

		Map<String, Object> deleteArgs = new HashMap<>();
		deleteArgs.put("memberId", memberId);
		deleteArgs.put("id", id);
		
		articleService.delete(deleteArgs);
		
		req.setAttribute("alertMsg", id + "번 게시물이 삭제되었습니다.");
		return "common/redirect";
	}
}