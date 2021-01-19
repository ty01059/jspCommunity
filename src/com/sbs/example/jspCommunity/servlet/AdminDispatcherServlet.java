package com.sbs.example.jspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.admin.AdminMemberController;

@WebServlet("/adm/*")
public class AdminDispatcherServlet extends DispatcherServlet {
	protected String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName,
			String actionMethodName) {
		String jspPath = null;

		if (controllerName.equals("member")) {
			AdminMemberController memberController = Container.adminMemberController;

			if (actionMethodName.equals("list")) {
				jspPath = memberController.showList(req, resp);
			}
		}

		return jspPath;
	}
}
