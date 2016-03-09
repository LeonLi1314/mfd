package com.rtmap.traffic.mfd.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.mfd.domain.PageRst;
import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.dto.WechatMsgDto;
import com.rtmap.traffic.mfd.service.IWechatMsgService;

/**
 * 消息推送模块控制器
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Controller
@RequestMapping("msg")
public class WechatMsgController {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private IWechatMsgService  wechatMsgService;

	@ResponseBody
	@RequestMapping(value = "/getUnreadWechatMsgsCount.do")
	public int getUnreadWechatMsgsCount(HttpServletRequest request) {
		String openId = request.getParameter("openId");
		return wechatMsgService.getUnreadWechatMsgsCount(openId);
	}

	@ResponseBody
	@RequestMapping(value = "/getWechatMsgs.do")
	public PageRst<WechatMsgDto> getWechatMsgs(@RequestBody  PageCond<String> cond ,HttpServletRequest request) {
		String openId = request.getParameter("openId");
		cond.setCond(openId);
		return wechatMsgService.getWechatMsgs(cond);
	}

	@ResponseBody
	@RequestMapping(value = "/getUnreadWechatMsgs.do")
	public PageRst<WechatMsgDto> getUnreadWechatMsgs(@RequestBody  PageCond<String> cond ,HttpServletRequest request) {
		String openId = request.getParameter("openId");
		cond.setCond(openId);
		return wechatMsgService.getUnreadWechatMsgs(cond);
	}

	@ResponseBody
	@RequestMapping(value = "/modifyWechatMsgReadFlag.do")
	public void modifyWechatMsgReadFlag(@RequestBody  int msgId, HttpServletRequest request) {
		wechatMsgService.modifyWechatMsgReadFlag(msgId);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteWechatMsgById.do")
	public void deleteWechatMsgById(@RequestBody  int msgId, HttpServletRequest request) {
		wechatMsgService.deleteWechatMsgById(msgId);
	}
}
