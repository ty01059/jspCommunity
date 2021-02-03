package com.sbs.example.jspCommunity.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.controller.Controller;

public class UserHomeController extends Controller {
	public String showMain(HttpServletRequest req, HttpServletResponse resp) {
		return "user/home/main";
	}
}
