package com.rtmap.traffic.mfd.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.mfd.domain.PageRst;
import com.rtmap.traffic.mfd.domain.cond.ArrdepPlaceCond;
import com.rtmap.traffic.mfd.domain.cond.FltIdCond;
import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.dto.FltDetailDto;
import com.rtmap.traffic.mfd.domain.dto.FltInfoDto;
import com.rtmap.traffic.mfd.service.IFlightService;

/**
 * 航班模块控制器
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Controller
@RequestMapping("flt")
public class FlightController {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private IFlightService flightService;

	/**
	 * 根据航班号模糊匹配航班列表
	 * 
	 * @param cond
	 *            查询条件
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getByFltNoCond.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public PageRst<FltInfoDto> getFlightsByFltNoCond(@RequestBody PageCond<FltNoCond> cond) {
		return flightService.getFlightsByFltNoCond(cond);
	}

	/**
	 * 根据起降地条件获取航班列表
	 * 
	 * @param cond
	 *            查询条件
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getByPlaceCond.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public PageRst<FltInfoDto> getFlightsByPlaceCond(@RequestBody PageCond<ArrdepPlaceCond> cond) {
		return flightService.getFlightsByPlaceCond(cond);
	}

	/**
	 * 根据航班Id查询条件获取航班详情
	 * 
	 * @param cond
	 *            查询条件
	 * @return 航班详情
	 */
	@ResponseBody
	@RequestMapping(value = "/getDetailsByFltIdCond.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public FltDetailDto getFlightDetailByFltIdCond(@RequestBody FltIdCond cond) {
		return flightService.getFlightDetailByFltIdCond(cond);
	}

	/**
	 * 获取当前用户关注的航班列表
	 * 
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getFollowedFlights.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public PageRst<FltInfoDto> getFollowedFlights() {
		String openId = "";
		return flightService.getFollowedFlights(openId);
	}
}
