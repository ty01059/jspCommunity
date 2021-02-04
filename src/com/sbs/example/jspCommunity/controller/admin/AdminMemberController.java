package com.sbs.example.jspCommunity.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.Controller;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.service.MemberService;

public class AdminMemberController extends Controller {
	private MemberService memberService;
	
	public AdminMemberController() {
		memberService = Container.memberService;
	}
	
	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		List<Member> members = memberService.getForPrintMembers();
		
		req.setAttribute("members", members);
		
		return "user/member/list";
	}
}