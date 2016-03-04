package com.rtmap.traffic.mfd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.mfd.domain.dto.WechatMsgDto;

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

	@ResponseBody
	@RequestMapping(value = "/getUnreadMessagesCount.do")
	public int getUnreadMessagesCount(HttpServletRequest request) {
		return 0;
	}

	@ResponseBody
	@RequestMapping(value = "/getMessages.do")
	public List<WechatMsgDto> getMessages(HttpServletRequest request) {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/.do")
	public void modifyReadFlag(int msgId, HttpServletRequest request) {

	}

	@ResponseBody
	@RequestMapping(value = "/.do")
	public void deleteMessage(int msgId, HttpServletRequest request) {

	}
}
