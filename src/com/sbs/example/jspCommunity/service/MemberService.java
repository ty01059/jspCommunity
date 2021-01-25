package com.sbs.example.jspCommunity.service;

import java.util.List;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.dto.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}

	public List<Member> getForPrintMembers() {
		return memberDao.getForPrintMembers();
	}
	
	public int join(Member member) {
		return memberDao.join(member);
	}
	
	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int loginedMemberId) {
		return memberDao.getMemberById(loginedMemberId);
	}
}