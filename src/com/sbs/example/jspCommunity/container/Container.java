package com.sbs.example.jspCommunity.container;

import com.sbs.example.jspCommunity.controller.admin.AdminMemberController;
import com.sbs.example.jspCommunity.controller.user.UserArticleController;
import com.sbs.example.jspCommunity.controller.user.UserHomeController;
import com.sbs.example.jspCommunity.controller.user.UserMemberController;
import com.sbs.example.jspCommunity.controller.user.UserLikeController;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dao.AttrDao;
import com.sbs.example.jspCommunity.dao.LikeDao;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.jspCommunity.service.AttrService;
import com.sbs.example.jspCommunity.service.EmailService;
import com.sbs.example.jspCommunity.service.LikeService;
import com.sbs.example.jspCommunity.service.MemberService;

public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static UserArticleController userArticleController;
	
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static UserLikeController userLikeController;
	public static UserMemberController userMemberController;
	public static AdminMemberController adminMemberController;
	public static UserHomeController userHomeController;
	public static EmailService emailService;
	
	public static LikeService likeService;
	public static LikeDao likeDao;
	
	public static AttrService attrService;
	public static AttrDao attrDao;
	
	static {
		attrDao = new AttrDao();
		likeDao = new LikeDao();
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		attrService = new AttrService();
		likeService = new LikeService();
		emailService = new EmailService();
		memberService = new MemberService();
		articleService = new ArticleService();
		
		userLikeController = new UserLikeController();
		adminMemberController = new AdminMemberController();
		userMemberController = new UserMemberController();
		userArticleController = new UserArticleController();
		userHomeController = new UserHomeController();
	}
}