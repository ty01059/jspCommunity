package com.sbs.example.jspCommunity.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserHomeController {
	public String showMain(HttpServletRequest req, HttpServletResponse resp) {
		return "user/home/main";
	}
}
