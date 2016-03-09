package com.rtmap.traffic.mfd.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.mfd.dao.IMsgWechatCenterDao;
import com.rtmap.traffic.mfd.domain.PageRst;
import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.dto.WechatMsgDto;
import com.rtmap.traffic.mfd.domain.entity.MsgWechatCenter;
import com.rtmap.traffic.mfd.service.IWechatMsgService;

import rtmap.frame.util.DatePatterns;
import rtmap.frame.util.DateUtils;

@Service
public class WechatMsgServiceImpl implements IWechatMsgService {
	@Resource
	private IMsgWechatCenterDao wechatMsgDao;

	@Override
	public int getUnreadWechatMsgsCount(String openId) {
		return wechatMsgDao.selectUnreadCount(openId);
	}

	@Override
	public PageRst<WechatMsgDto> getWechatMsgs(PageCond<String> cond) {
		return getWechatMsgs(cond, true);
	}

	private PageRst<WechatMsgDto> getWechatMsgs(PageCond<String> cond, boolean isAll) {
		PageRst<WechatMsgDto> pageRst = new PageRst<>();
		pageRst.setTotalCount(wechatMsgDao.selectMessagesTotalCount(cond.getCond(), isAll));

		if (pageRst.getTotalCount() == 0)
			return pageRst;

		List<MsgWechatCenter> list = wechatMsgDao.selectPageMessages(cond, isAll);

		if (list == null || list.size() == 0)
			return pageRst;

		pageRst.setRst(new ArrayList<WechatMsgDto>());

		for (MsgWechatCenter msgWechat : list) {
			WechatMsgDto dto = new WechatMsgDto();
			dto.setMsgContent(msgWechat.getMsgContent());
			dto.setMsgId(msgWechat.getMsgId());
			dto.setMsgTime(DateUtils.formatDate(msgWechat.getMsgTime(), DatePatterns.POPULAR_DATE24TIME));
			dto.setMsgTitle(msgWechat.getMsgTitle());
			dto.setReadFlag(msgWechat.getReadFlag());

			pageRst.getRst().add(dto);
		}

		int remainder = pageRst.getTotalCount() % cond.getPageSize();
		int totalPage = pageRst.getTotalCount() / cond.getPageSize();
		if (remainder != 0) {
			totalPage++;
		}

		pageRst.setTotalPage(totalPage);
		pageRst.setLastPage(cond.getPageNo() == totalPage);
		return pageRst;
	}

	@Override
	public PageRst<WechatMsgDto> getUnreadWechatMsgs(PageCond<String> cond) {
		return getWechatMsgs(cond, false);
	}

	@Override
	public void modifyWechatMsgReadFlag(int msgId) {
		wechatMsgDao.updateReadFlag(msgId);
	}

	@Override
	public void deleteWechatMsgById(int msgId) {
		wechatMsgDao.updateDeleteFlag(msgId);
	}
}
