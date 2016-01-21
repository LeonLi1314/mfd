package com.rtmap.traffic.mfd.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.mfd.domain.ArrdepFlag;
import com.rtmap.traffic.mfd.domain.OpRst;
import com.rtmap.traffic.mfd.domain.dto.FollowFltDto;
import com.rtmap.traffic.mfd.service.ISubscribeService;

/**
 * 订阅模块控制器
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Controller
@RequestMapping("sbc")
public class SubscribeController extends UniformController {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	ISubscribeService subscribeService;

	/**
	 * 关注航班
	 * 
	 * @return 操作结果
	 */
	@ResponseBody
	@RequestMapping(value = "/followFlight.do")
	public OpRst followFlight(@RequestBody FollowFltDto cond, HttpServletRequest request) {
//		FollowFltDto cond = ConvertRequestParam(request);

		String currAirportCode = request.getParameter("currAirportCode");
		String openId = request.getParameter("openId");
		return subscribeService.followFlight(cond);
	}

	/**
	 * 取消关注航班
	 * 
	 * @return 操作结果
	 */
	@ResponseBody
	@RequestMapping(value = "/unfollowFlight.do")
	public OpRst unfollowFlight(@RequestBody FollowFltDto cond, HttpServletRequest request) {
//		String contractIdStr = request.getParameter("contractId");
//
//		if (StringUtils.isNullOrEmpty(contractIdStr)) {
//			OpRst rst = new OpRst();
//			rst.setMsg("契约Id为空");
//			return rst;
//		}
//
//		FollowFltDto cond = ConvertRequestParam(request);
//		cond.setContractId(Integer.parseInt(contractIdStr));

		String currAirportCode = request.getParameter("currAirportCode");
		String openId = request.getParameter("openId");
		return subscribeService.unfollowFlight(cond);
	}

	private FollowFltDto ConvertRequestParam(HttpServletRequest request) {
		String fltId = request.getParameter("fltId");
		String arrdepStr = request.getParameter("arrdep");
		int subscribeId = Integer.parseInt(request.getParameter("subscribeId"));
		String subscriberId = request.getParameter("openId");
		String currentAirport = request.getParameter("currAirportCode");
		String sourceCode = request.getParameter("sourceCode");
		String subscribeModule = request.getParameter("subscribeModule");
		String subscribeEvent = request.getParameter("subscribeEvent");

		FollowFltDto cond = new FollowFltDto();
		cond.setFltId(fltId);
		cond.setFollow(true);
		cond.setArrdep(ArrdepFlag.valueOf(arrdepStr.toUpperCase(Locale.ENGLISH)));
		cond.setSubscribeId(subscribeId);
		cond.setSubscriberId(subscriberId);
		cond.setCurrentAirport(currentAirport);
		cond.setSourceCode(sourceCode);
		cond.setSubscribeModule(subscribeModule);
		cond.setSubscribeEvent(subscribeEvent);
		return cond;
	}

}
