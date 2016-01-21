package com.rtmap.traffic.mfd.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.mfd.domain.PageRst;
import com.rtmap.traffic.mfd.domain.cond.ArrdepPlaceCond;
import com.rtmap.traffic.mfd.domain.cond.FltIdCond;
import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.cond.ShakeCond;
import com.rtmap.traffic.mfd.domain.cond.SubscriberCond;
import com.rtmap.traffic.mfd.domain.dto.FltDetailDto;
import com.rtmap.traffic.mfd.domain.dto.FltInfoDto;
import com.rtmap.traffic.mfd.service.IFlightFactory;

/**
 * 航班模块控制器
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Controller
@RequestMapping("flt")
public class FlightController extends UniformController {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private IFlightFactory factory;

	/**
	 * 根据航班号模糊匹配航班列表
	 * 
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getByFltNoCond.do")
	public PageRst<FltInfoDto> getFlightsByFltNoCond(@RequestBody PageCond<FltNoCond> pageCond, HttpServletRequest request) {
//		PageCond<FltNoCond> pageCond = new PageCond<FltNoCond>();
//		String pageNoStr = request.getParameter("pageNo");
//		String fltNo = request.getParameter("fltNo");
//		String arrdepStr = request.getParameter("arrdep");
//		String queryDateStr = request.getParameter("queryDate");
//		pageCond.setPageNo(Integer.parseInt(pageNoStr));
//		pageCond.setCond(new FltNoCond());
//		pageCond.getCond().setArrdep(ArrdepFlag.valueOf(arrdepStr.toUpperCase(Locale.ENGLISH)));
//		pageCond.getCond().setFltNo(fltNo);
//		pageCond.getCond().setQueryDate(new Date(Long.parseLong(queryDateStr))))
		String currAirportCode = request.getParameter("currAirportCode");
		String openId = request.getParameter("openId");
		return factory.getService(currAirportCode).getFlightsByFltNoCond(pageCond);
	}

	/**
	 * 根据起降地条件获取航班列表
	 * 
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getByPlaceCond.do")
	public PageRst<FltInfoDto> getFlightsByPlaceCond(@RequestBody PageCond<ArrdepPlaceCond> pageCond, HttpServletRequest request) {
//		PageCond<ArrdepPlaceCond> pageCond = new PageCond<ArrdepPlaceCond>();
//		String pageNoStr = request.getParameter("pageNo");
//		String code = request.getParameter("code");
//		String typeStr = request.getParameter("type");
//		String arrdepStr = request.getParameter("arrdep");
//		String airlineCode = request.getParameter("airlineCode");
//		String queryDateStr = request.getParameter("queryDate");
//		pageCond.setPageNo(Integer.parseInt(pageNoStr));
//		pageCond.setCond(new ArrdepPlaceCond());
//		pageCond.getCond().setAirlineCode(airlineCode);
//		pageCond.getCond().setArrdep(ArrdepFlag.valueOf(arrdepStr.toUpperCase(Locale.ENGLISH)));
//		pageCond.getCond().setCode(code);
//		pageCond.getCond().setQueryDate(new Date(Long.parseLong(queryDateStr)));
//		pageCond.getCond().setType(Integer.parseInt(typeStr));

		String currAirportCode = request.getParameter("currAirportCode");
		String openId = request.getParameter("openId");
		return factory.getService(currAirportCode).getFlightsByPlaceCond(pageCond);
	}
	
	/**
	 * 根据航班Id查询条件获取航班详情
	 * 
	 * @return 航班详情
	 */
	@ResponseBody
	@RequestMapping(value = "/getDetailsByFltIdCond.do")
	public FltDetailDto getFlightDetailByFltIdCond(@RequestBody FltIdCond cond, HttpServletRequest request) {
//		FltIdCond cond = new FltIdCond();
//		String fltId = request.getParameter("fltId");
//		String arrdepStr = request.getParameter("arrdep");
//		String openId = request.getParameter("openId");
//		cond.setFltId(fltId);
//		if (!StringUtils.isNullOrEmpty(openId)) {
//			cond.setSubscriberId(openId);
//		}
//		cond.setArrdep(ArrdepFlag.valueOf(arrdepStr.toUpperCase(Locale.ENGLISH)));

		String currAirportCode = request.getParameter("currAirportCode");
		String openId = request.getParameter("openId");
		return factory.getService(currAirportCode).getFlightDetailByFltIdCond(cond);
	}

	/**
	 * 获取当前用户关注的航班列表
	 * 
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getFollowedFlights.do")
	public PageRst<FltInfoDto> getFollowedFlights(@RequestBody SubscriberCond cond,HttpServletRequest request) {
//		String openId = request.getParameter("openId");
//		if (StringUtils.isNullOrEmpty(openId))
//			return null;
//
//		SubscriberCond cond = new SubscriberCond();
//		cond.setSubscriberId("openId");

		String currAirportCode = request.getParameter("currAirportCode");
		String openId = request.getParameter("openId");
		return factory.getService(currAirportCode).getFollowedFlights(cond);
	}

	/**
	 * 根据起降地条件获取航班列表
	 * 
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getLimitByShakeCond.do")
	public List<FltInfoDto> getLimitFlightsByShakeCond(@RequestBody ShakeCond cond, HttpServletRequest request) {
		String currAirportCode = request.getParameter("currAirportCode");
		String openId = request.getParameter("openId");
		return factory.getService(currAirportCode).getLimitFlightsByShakeCond(cond);
	}

	/**
	 * 根据起降地条件获取航班列表
	 * 
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getByShakeCond.do")
	public List<FltInfoDto> getFlightsByShakeCond(@RequestBody ShakeCond cond, HttpServletRequest request) {
		String currAirportCode = request.getParameter("currAirportCode");
		String openId = request.getParameter("openId");
		return factory.getService(currAirportCode).getFlightsByShakeCond(cond);
	}
}
