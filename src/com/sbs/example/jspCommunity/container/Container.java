package com.sbs.example.jspCommunity.container;

import com.sbs.example.jspCommunity.controller.admin.AdminMemberController;
import com.sbs.example.jspCommunity.controller.user.UserArticleController;
import com.sbs.example.jspCommunity.controller.user.UserMemberController;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.jspCommunity.service.MemberService;

public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static UserArticleController articleController;
	
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static UserMemberController memberController;
	public static AdminMemberController adminMemberController;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
		
		adminMemberController = new AdminMemberController();
		memberController = new UserMemberController();
		articleController = new UserArticleController();
	}
}