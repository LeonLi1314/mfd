package com.rtmap.traffic.mfd.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class SubscribeController extends UniformController{
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	ISubscribeService subscribeService;
	
	/**
	 * 关注航班
	 * @return 操作结果
	 */
	@ResponseBody
	@RequestMapping(value = "/followFlight.do", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8"})
	public OpRst followFlight(HttpServletRequest request) {
		String fltId = request.getParameter("fltId");
		String arrdepStr = request.getParameter("arrdep");
		FollowFltDto cond = new FollowFltDto();
		cond.setFltId(fltId);
		cond.setFollow(true);
		if(arrdepStr.equals("A")){
			cond.setArrdep(ArrdepFlag.A);
		}else{
			cond.setArrdep(ArrdepFlag.D);
		}
		
		return subscribeService.followFlight(cond);
	}
	
	/**
	 * 取消关注航班
	 * @return 操作结果
	 */
	@ResponseBody
	@RequestMapping(value = "/unfollowFlight.do", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8"})
	public OpRst unfollowFlight(HttpServletRequest request) {
		String fltId = request.getParameter("fltId");
		String arrdepStr = request.getParameter("arrdep");
		String contractIdStr = request.getParameter("contractId");
		FollowFltDto cond = new FollowFltDto();
		cond.setFltId(fltId);
		cond.setFollow(false);
		cond.setContractId(Integer.parseInt(contractIdStr));
		if(arrdepStr.equals("A")){
			cond.setArrdep(ArrdepFlag.A);
		}else{
			cond.setArrdep(ArrdepFlag.D);
		}

		return subscribeService.unfollowFlight(cond);
	}
}
