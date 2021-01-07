package com.sbs.example.jspCommunity.servlet.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

@WebServlet("/user/article")
public class ArticleListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		MysqlUtil.setDBInfo("127.0.0.1", "sbsblog", "sbs123", "jspCommunity");

		list(req, resp);
		add(req, resp);
		modify(req, resp);
		delete(req, resp);
		detail(req, resp);
	}

//	@WebServlet("/list")
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String boardCode = req.getParameter("boardCode");
		int boardId = 0;

		if (boardCode.equals("notice")) {
			boardId = 1;
		} else if (boardCode.equals("geustBook")) {
			boardId = 2;
		} else if (boardCode.equals("free")) {
			boardId = 3;
		}

		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(
				new SecSql().append("SELECT * FROM article WHERE boardId = " + boardId + " ORDER BY id DESC"));
		MysqlUtil.closeConnection();

		req.setAttribute("articleMapList", articleMapList);

		req.getRequestDispatcher("/jsp/user/article/list.jsp").forward(req, resp);
	}

//	@WebServlet("/doWrite")
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String body = req.getParameter("body");

		int articleAdd = MysqlUtil.insert(new SecSql().append(
				"INSERT INTO article SET regDate = NOW(), updateDate = NOW(), memberId = 2, boardId = 1, title = "
						+ title + ", body = " + body + ""));
		MysqlUtil.closeConnection();

		req.setAttribute("articleAdd", articleAdd);

		req.getRequestDispatcher("/jsp/user/article/add.jsp").forward(req, resp);
	}

//	@WebServlet("/doModify")
	private void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String body = req.getParameter("body");

		int articleModify = MysqlUtil.update(new SecSql().append(
				"UPDATE article SET updateDate = NOW(), title = " + title + ", body = " + body + "WHERE id = " + id));
		MysqlUtil.closeConnection();

		req.setAttribute("articleModify", articleModify);

		req.getRequestDispatcher("/jsp/user/article/modify.jsp").forward(req, resp);
	}

//	@WebServlet("/doDelete")
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		int articleDelete = MysqlUtil.delete(new SecSql().append("DELETE FROM article WHERE id = " + id));
		MysqlUtil.closeConnection();

		req.setAttribute("articleDelete", articleDelete);

		req.getRequestDispatcher("/jsp/user/article/delete.jsp").forward(req, resp);
	}

//	@WebServlet("/detail")
	private void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		Map<String, Object> articleMapDetail = MysqlUtil
				.selectRow(new SecSql().append("SELECT * FROM article WHERE id = " + id));
		MysqlUtil.closeConnection();

		req.setAttribute("articleMapDetail", articleMapDetail);

		req.getRequestDispatcher("/jsp/user/article/detail.jsp").forward(req, resp);
	}
}
