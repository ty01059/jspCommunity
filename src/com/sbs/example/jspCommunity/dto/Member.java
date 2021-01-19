package com.sbs.example.jspCommunity.dto;

import java.util.Map;

import lombok.Data;

public @Data class Member {
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String email;
	private String cellphoneNo;
	private int authLevel;

	public Member() {
	}

	public Member(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		this.loginId = (String) map.get("loginId");
		this.loginPw = (String) map.get("loginPw");
		this.name = (String) map.get("name");
		this.nickname = (String) map.get("nickname");
		
		if (map.containsKey("email")) {
			this.email = (String) map.get("email");			
		}
		if (map.containsKey("cellphoneNo")) {
			this.cellphoneNo = (String) map.get("cellphoneNo");			
		}
		if (map.containsKey("authLevel")) {
			this.authLevel = (int) map.get("authLevel");			
		}
	}
}