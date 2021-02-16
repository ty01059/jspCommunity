package com.sbs.example.jspCommunity.service;

import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ReplyDao;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.dto.Reply;

public class ReplyService {

	private ReplyDao replyDao;
	private MemberService memberService;
	private LikeService likeService;
	
	public ReplyService() {
		replyDao = Container.replyDao;
		memberService = Container.memberService;
		likeService = Container.likeService;
	}

	public int write(Map<String, Object> args) {
		return replyDao.write(args);
	}

	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {
		return replyDao.getForPrintReplies(relTypeCode, relId);
	}
	
	public Reply getForPrintReplyById(int id) {
		return getForPrintReplyById(id, null);
	}
	
	public Reply getForPrintReplyById(int id, Member actor) {
		Reply reply = replyDao.getForPrintReplyById(id);

		if (reply == null) {
			return null;
		}

		if (actor != null) {
			updateInfoForPrint(reply, actor);
		}

		return reply;
	}
	
	private void updateInfoForPrint(Reply reply, Member actor) {
		boolean actorCanLike = likeService.actorCanLike(reply, actor);
		boolean actorCanCancelLike = likeService.actorCanCancelLike(reply, actor);
		boolean actorCanDislike = likeService.actorCanDislike(reply, actor);
		boolean actorCanCancelDislike = likeService.actorCanCancelDislike(reply, actor);
		
		reply.getExtra().put("actorCanLike", actorCanLike);
		reply.getExtra().put("actorCanCancelLike", actorCanCancelLike);
		reply.getExtra().put("actorCanDislike", actorCanDislike);
		reply.getExtra().put("actorCanCancelDislike", actorCanCancelDislike);
	}

	public Reply getReply(int id) {
		return replyDao.getReply(id);
	}

	public boolean actorCanDelete(Reply reply, int actorId) {
		if (memberService.isAdmin(actorId)) {
			return true;
		}

		return reply.getMemberId() == actorId;
	}

	public int delete(int id) {
		return replyDao.delete(id);
	}

	public int modify(String body, int id) {
		return replyDao.modify(body, id);
	}
}
