package com.sbs.example.jspCommunity.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.Controller;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.dto.ResultData;
import com.sbs.example.jspCommunity.service.EmailService;
import com.sbs.example.jspCommunity.service.MemberService;
import com.sbs.example.util.Util;

public class UserMemberController extends Controller {
	private MemberService memberService;
	private EmailService emailService;

	public UserMemberController() {
		memberService = Container.memberService;
		emailService = Container.emailService;
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
		String loginId = req.getParameter("loginId");

		if (Util.isEmpty(loginId)) {
			return msgAndBack(req, "로그인아이디를 입력해주세요.");
		}

		String loginPw = req.getParameter("loginPwReal");

		if (Util.isEmpty(loginPw)) {
			return msgAndBack(req, "로그인비번을 입력해주세요.");
		}

		String name = req.getParameter("name");

		if (Util.isEmpty(name)) {
			return msgAndBack(req, "이름을 입력해주세요.");
		}

		String nickname = req.getParameter("nickname");

		if (Util.isEmpty(nickname)) {
			return msgAndBack(req, "별명을 입력해주세요.");
		}

		String email = req.getParameter("email");

		if (Util.isEmpty(email)) {
			return msgAndBack(req, "이메일을 입력해주세요.");
		}

		String cellphoneNo = req.getParameter("cellphoneNo");

		if (Util.isEmpty(cellphoneNo)) {
			return msgAndBack(req, "휴대전화번호를 입력해주세요.");
		}

		Member oldMember = memberService.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return msgAndBack(req, "해당 로그인 아이디는 이미 사용중입니다.");
		}

		Member member = new Member();
		member.setLoginId(loginId);
		member.setLoginPw(req.getParameter("loginPwReal"));
		member.setName(req.getParameter("name"));
		member.setNickname(req.getParameter("nickname"));
		member.setEmail(req.getParameter("email"));
		member.setCellphoneNo(req.getParameter("cellphoneNo"));

		int newArticleId = memberService.join(member);

		String title = "회원가입 축하 메일";
		String body = member.getLoginId() + "님 회원가입을 축하합니다.";

		emailService.send(member.getEmail(), title, body);

		return msgAndReplace(req, newArticleId + "번 회원이 생성되었습니다.", "../home/main");
	}

	public String showLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "user/member/login";

	}

	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.removeAttribute("loginedMemberId");

		return msgAndReplace(req, "로그아웃 되었습니다.", "../home/main");
	}

	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();

		String loginId = req.getParameter("loginId");

		if (Util.isEmpty(loginId)) {
			return msgAndBack(req, "로그인아이디를 입력해주세요.");
		}

		String loginPw = req.getParameter("loginPwReal");

		if (Util.isEmpty(loginPw)) {
			return msgAndBack(req, "로그인비밀번호를 입력해주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return msgAndBack(req, "비밀번호가 일치하지 않습니다.");
		}

		session.setAttribute("loginedMemberId", member.getId());

		String alertMsg = String.format("%s님 환영합니다.", member.getNickname());
		String replaceUrl = "../home/main";

		if (Util.isEmpty(req.getParameter("afterLoginUrl")) == false) {
			replaceUrl = req.getParameter("afterLoginUrl");
		}
		
		boolean isUsingTempPassword = memberService.isUsingTempPassword(member.getId());
		
		if (isUsingTempPassword) {
			alertMsg = String.format("%s님은 현재 임시 비밀번호를 사용중입니다. 변경 후 이용해주세요.", member.getNickname());
			replaceUrl = "../member/modify";
		}
		
		boolean isNeedToModifyOldLoginPw = memberService.isNeedToModifyOldLoginPw(member.getId());

		if (isNeedToModifyOldLoginPw) {
			int oldPasswordDays = memberService.getOldPasswordDays();
			alertMsg = String.format("가장 마지막 비밀번호 변경일로부터 " + oldPasswordDays + "일이 경과하였습니다. 비밀번호를 변경해주세요.", member.getNickname());
			replaceUrl = "../member/modify";
		}

		return msgAndReplace(req, alertMsg, replaceUrl);
	}

	public String getLoginIdDup(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");

		Member member = memberService.getMemberByLoginId(loginId);

		String resultCode = null;
		String msg = null;

		if (member != null) {
			resultCode = "F-1";
			msg = "이미 사용중인 로그인아이디 입니다.";
		} else {
			resultCode = "S-1";
			msg = "사용가능한 로그인아이디 입니다.";
		}

		return json(req, new ResultData(resultCode, msg, "loginId", loginId));
	}

	public String showFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		return "user/member/findLoginId";
	}

	public String doFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("name");

		if (Util.isEmpty(name)) {
			return msgAndBack(req, "이름을 입력해주세요.");
		}

		String email = req.getParameter("email");

		if (Util.isEmpty(email)) {
			return msgAndBack(req, "이메일을 입력해주세요.");
		}

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			return msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}

		return msgAndReplace(req, String.format("로그인아이디는 %s 입니다.", member.getLoginId()), "../member/login");
	}

	public String showFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		return "user/member/findLoginPw";
	}

	public String doFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");

		if (Util.isEmpty(loginId)) {
			return msgAndBack(req, "로그인아이디를 입력해주세요.");
		}

		String email = req.getParameter("email");

		if (Util.isEmpty(email)) {
			return msgAndBack(req, "이메일을 입력해주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}

		if (member.getEmail().equals(email) == false) {
			return msgAndBack(req, "회원이 이메일주소를 정확히 입력해주세요.");
		}

		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

		if (sendTempLoginPwToEmailRs.isFail()) {
			return msgAndBack(req, sendTempLoginPwToEmailRs.getMsg());
		}

		return msgAndReplace(req, sendTempLoginPwToEmailRs.getMsg(), "../member/login");
	}

	public String showModify(HttpServletRequest req, HttpServletResponse resp) {
		return "user/member/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		String loginPw = req.getParameter("loginPwReal");

		if (loginPw != null && loginPw.length() == 0) {
			loginPw = null;
		}

		String name = req.getParameter("name");

		if (Util.isEmpty(name)) {
			return msgAndBack(req, "이름을 입력해주세요.");
		}

		String nickname = req.getParameter("nickname");

		if (Util.isEmpty(nickname)) {
			return msgAndBack(req, "별명을 입력해주세요.");
		}

		String email = req.getParameter("email");

		if (Util.isEmpty(email)) {
			return msgAndBack(req, "이메일을 입력해주세요.");
		}

		String cellphoneNo = req.getParameter("cellphoneNo");

		if (Util.isEmpty(cellphoneNo)) {
			return msgAndBack(req, "휴대전화번호를 입력해주세요.");
		}

		Map<String, Object> modifyParam = new HashMap<>();
		modifyParam.put("loginPw", loginPw);
		modifyParam.put("name", name);
		modifyParam.put("nickname", nickname);
		modifyParam.put("email", email);
		modifyParam.put("cellphoneNo", cellphoneNo);
		modifyParam.put("id", loginedMemberId);

		memberService.modify(modifyParam);

		if (loginPw != null) {
			memberService.setIsUsingTempPassword(loginedMemberId, false);
		}

		return msgAndReplace(req, "회원정보가 수정되었습니다.", "../home/main");
	}
}