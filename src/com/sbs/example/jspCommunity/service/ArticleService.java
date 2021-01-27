package com.sbs.example.jspCommunity.service;

import java.util.List;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public Article getForPrintArticleById(int id) {
		return articleDao.getForPrintArticleById(id);
	}
	
	public Board getBoardById(int id) {
		return articleDao.getBoardById(id);
	}

	public int write(Article article) {
		return articleDao.write(article);
	}
	
	public int modify(Article article) {
		return articleDao.modify(article);
	}
	
	public int delete(int id) {
		return articleDao.delete(id);
	}
	
	public int getArticlesCountByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);
	}

	public List<Article> getForPrintArticlesByBoardId(int boardId, int limitStart, int limitCount, String searchKeywordType, String searchKeyword) {
		return articleDao.getForPrintArticlesByBoardId(boardId, limitStart, limitCount, searchKeywordType, searchKeyword);
	}
}