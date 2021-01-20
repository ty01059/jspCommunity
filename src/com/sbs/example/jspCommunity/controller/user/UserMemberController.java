package com.sbs.example.jspCommunity.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.service.MemberService;

public class UserMemberController {
	private MemberService memberService;

	public UserMemberController() {
		memberService = Container.memberService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		List<Member> members = memberService.getForPrintMembers();

		req.setAttribute("members", members);

		return "user/member/list";
	}

	public String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		return "user/member/join";
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		String loginId = req.getParameter("loginId");

		Member oldMember = memberService.getMemberByLoginId(loginId);

		if (oldMember != null) {
			req.setAttribute("alertMsg", "해당 로그인 아이디는 이미 사용중입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		Member member = new Member();
		member.setLoginId(loginId);
		member.setLoginPw(req.getParameter("loginPw"));
		member.setName(req.getParameter("name"));
		member.setNickname(req.getParameter("nickname"));
		member.setEmail(req.getParameter("email"));
		member.setCellphoneNo(req.getParameter("cellphoneNo"));

		int newArticleId = memberService.join(member);

		req.setAttribute("alertMsg", newArticleId + "번 회원이 생성되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	public String showLogin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		return "user/member/login";

	}

	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "이미 로그아웃 상태입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		session.removeAttribute("loginedMemberId");

		req.setAttribute("alertMsg", "로그아웃 되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			req.setAttribute("alertMsg", "로그아웃 후 진행해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPw");

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			req.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		session.setAttribute("loginedMemberId", member.getId());

		req.setAttribute("alertMsg", String.format("%s님 환영합니다.", member.getNickname()));
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}
}