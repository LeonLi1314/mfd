package com.rtmap.traffic.mfd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.mfd.domain.OpRst;

/**
 * 订阅模块控制器
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Controller
@RequestMapping("sbc")
public class SubscribeController {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@RequestMapping(value = "/followFlight.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8"})
	public OpRst followFlight(String fltId) {
		return null;
	}
	

	@ResponseBody
	@RequestMapping(value = "/unfollowFlight.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8"})
	public OpRst unfollowFlight(String fltId) {
		return null;
	}
}
