package com.sbs.example.jspCommunity.gugudan;

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
		int num = Integer.parseInt(pathParts[1]);
		String lang = request.getParameter("lang") != null ? request.getParameter("lang") : "9";
		int num2 = Integer.parseInt(lang);
		
		if(num != 0) {
			for(int i = 1; i <= num2; i++) {
				response.getWriter().append(num + " * " + i + " = " + num*i + "<br>");				
			}
		} else {
			response.getWriter().append("구구단 없음.<br>");
		}
	}
}
