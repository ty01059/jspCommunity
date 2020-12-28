package com.sbs.example.jspCommunity.servlet.gugudan;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan/*")
public class One extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		String lang = request.getParameter("lang") != null ? request.getParameter("lang") : "9";
		int num = Integer.parseInt(lang);
		
		int dan = 1;
		if(!pathParts[1].equals("*")) {
			dan = Integer.parseInt(pathParts[1]);
			for(int i = 1; i <= num; i++) {
				response.getWriter().append(dan + " * " + i + " = " + dan*i + "<br>");				
			}
		} else {
			response.getWriter().append("숫자를 입력해주세요.<br>");
		}
	}
}
