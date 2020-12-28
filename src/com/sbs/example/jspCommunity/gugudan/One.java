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
		
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		int num = Integer.parseInt(pathParts[1]);
		
		if(num == 1) {
			for(int i = 1; i < 10; i++) {
				response.getWriter().append("1 * " + i + " = " + 1*i + "<br>");				
			}
		} else if(num == 2) {
			for(int i = 1; i < 10; i++) {
				response.getWriter().append("2 * " + i + " = " + 2*i + "<br>");				
			}
		} else if(num == 3) {
			for(int i = 1; i < 10; i++) {
				response.getWriter().append("3 * " + i + " = " + 3*i + "<br>");				
			}
		} else if(num == 4) {
			for(int i = 1; i < 10; i++) {
				response.getWriter().append("4 * " + i + " = " + 4*i + "<br>");				
			}
		} else if(num == 5) {
			for(int i = 1; i < 10; i++) {
				response.getWriter().append("5 * " + i + " = " + 5*i + "<br>");				
			}
		} else if(num == 6) {
			for(int i = 1; i < 10; i++) {
				response.getWriter().append("6 * " + i + " = " + 6*i + "<br>");				
			}
		} else if(num == 7) {
			for(int i = 1; i < 10; i++) {
				response.getWriter().append("7 * " + i + " = " + 7*i + "<br>");				
			}
		} else if(num == 8) {
			for(int i = 1; i < 10; i++) {
				response.getWriter().append("8 * " + i + " = " + 8*i + "<br>");				
			}
		} else if(num == 9) {
			for(int i = 1; i < 10; i++) {
				response.getWriter().append("9 * " + i + " = " + 9*i + "<br>");				
			}
		} else {
			response.getWriter().append("없는 구구단입니다.<br>");
		}
	}
}
