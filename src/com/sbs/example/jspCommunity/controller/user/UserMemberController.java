package com.sbs.example.jspCommunity.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		return "user/member/join";
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
		Member member = new Member();
		member.setLoginId(req.getParameter("loginId"));
		member.setLoginPw(req.getParameter("loginPw"));
		member.setName(req.getParameter("name"));
		member.setNickname(req.getParameter("nickname"));
		member.setEmail(req.getParameter("email"));
		member.setCellphoneNo(req.getParameter("cellphoneNo"));
		
		if (memberService.getForPrintMember(member.getLoginId()) != null) {
			req.setAttribute("alertMsg", member.getLoginId() + "는 이미 존재하는 로그인 아이디입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int newArticleId = memberService.join(member);

		req.setAttribute("alertMsg", newArticleId + "번 회원이 생성되었습니다.");
		req.setAttribute("replaceUrl", "join");
		return "common/redirect";
	}
}