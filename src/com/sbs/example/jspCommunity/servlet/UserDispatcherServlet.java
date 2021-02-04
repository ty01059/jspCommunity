package com.sbs.example.jspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.user.UserArticleController;
import com.sbs.example.jspCommunity.controller.user.UserHomeController;
import com.sbs.example.jspCommunity.controller.user.UserLikeController;
import com.sbs.example.jspCommunity.controller.user.UserMemberController;

@WebServlet("/user/*")
public class UserDispatcherServlet extends DispatcherServlet {
	@Override
	protected String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName,
			String actionMethodName) {
		String jspPath = null;

		if (controllerName.equals("home")) {
			UserHomeController homeController = Container.userHomeController;

			if (actionMethodName.equals("main")) {
				jspPath = homeController.showMain(req, resp);
			}
		} else if (controllerName.equals("member")) {
			UserMemberController memberController = Container.userMemberController;

			if (actionMethodName.equals("list")) {
				jspPath = memberController.showList(req, resp);
			} else if (actionMethodName.equals("findLoginId")) {
				jspPath = memberController.showFindLoginId(req, resp);
			} else if (actionMethodName.equals("doFindLoginId")) {
				jspPath = memberController.doFindLoginId(req, resp);
			} else if (actionMethodName.equals("findLoginPw")) {
				jspPath = memberController.showFindLoginPw(req, resp);
			} else if (actionMethodName.equals("doFindLoginPw")) {
				jspPath = memberController.doFindLoginPw(req, resp);
			} else if (actionMethodName.equals("modify")) {
				jspPath = memberController.showModify(req, resp);
			} else if (actionMethodName.equals("doModify")) {
				jspPath = memberController.doModify(req, resp);
			} else if (actionMethodName.equals("join")) {
				jspPath = memberController.showJoin(req, resp);
			} else if (actionMethodName.equals("doJoin")) {
				jspPath = memberController.doJoin(req, resp);
			} else if (actionMethodName.equals("login")) {
				jspPath = memberController.showLogin(req, resp);
			} else if (actionMethodName.equals("doLogin")) {
				jspPath = memberController.doLogin(req, resp);
			} else if (actionMethodName.equals("doLogout")) {
				jspPath = memberController.doLogout(req, resp);
			} else if (actionMethodName.equals("getLoginIdDup")) {
				jspPath = memberController.getLoginIdDup(req, resp);
			}
		} else if (controllerName.equals("article")) {
			UserArticleController articleController = Container.userArticleController;

			if (actionMethodName.equals("list")) {
				jspPath = articleController.showList(req, resp);
			} else if (actionMethodName.equals("detail")) {
				jspPath = articleController.showDetail(req, resp);
			} else if (actionMethodName.equals("modify")) {
				jspPath = articleController.showModify(req, resp);
			} else if (actionMethodName.equals("doModify")) {
				jspPath = articleController.doModify(req, resp);
			} else if (actionMethodName.equals("write")) {
				jspPath = articleController.showWrite(req, resp);
			} else if (actionMethodName.equals("doWrite")) {
				jspPath = articleController.doWrite(req, resp);
			} else if (actionMethodName.equals("doDelete")) {
				jspPath = articleController.doDelete(req, resp);
			}
		} else if (controllerName.equals("like")) {
			UserLikeController likeController = Container.userLikeController;

			if (actionMethodName.equals("doLike")) {
				jspPath = likeController.doLike(req, resp);
			} else if (actionMethodName.equals("doCancelLike")) {
				jspPath = likeController.doCancelLike(req, resp);
			} else if (actionMethodName.equals("doDislike")) {
				jspPath = likeController.doDislike(req, resp);
			} else if (actionMethodName.equals("doCancelDislike")) {
				jspPath = likeController.doCancelDislike(req, resp);
			}
		}

		return jspPath;
	}
}