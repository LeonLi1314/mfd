package com.rtmap.traffic.mfd.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.mfd.domain.ArrdepFlag;
import com.rtmap.traffic.mfd.domain.PageRst;
import com.rtmap.traffic.mfd.domain.cond.ArrdepPlaceCond;
import com.rtmap.traffic.mfd.domain.cond.FltIdCond;
import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.cond.SubscriberCond;
import com.rtmap.traffic.mfd.domain.dto.FltDetailDto;
import com.rtmap.traffic.mfd.domain.dto.FltInfoDto;
import com.rtmap.traffic.mfd.service.IFlightService;

import lqs.frame.util.StringUtils;

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
	private IFlightService flightService;

	/**
	 * 根据航班号模糊匹配航班列表
	 * 
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getByFltNoCond.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public PageRst<FltInfoDto> getFlightsByFltNoCond(HttpServletRequest request) {
		PageCond<FltNoCond> pageCond = new PageCond<FltNoCond>();

		// mytest
		// pageCond.setPageNo(Integer.parseInt(pageNoStr));
		// pageCond.setCond(new FltNoCond());
		// pageCond.getCond().setArrdep(ArrdepFlag.A);
		// pageCond.getCond().setFltNo("8210");
		// pageCond.getCond().setQueryDate(DateUtils.parseDate("2016-01-11"));
		String pageNoStr = request.getParameter("pageNo");
		String fltNo = request.getParameter("fltNo");
		String arrdepStr = request.getParameter("arrdep");
		String queryDateStr = request.getParameter("queryDate");
		pageCond.setPageNo(Integer.parseInt(pageNoStr));
		pageCond.setCond(new FltNoCond());
		pageCond.getCond().setArrdep(ArrdepFlag.values()[Integer.parseInt(arrdepStr)]);
		pageCond.getCond().setFltNo(fltNo);
		pageCond.getCond().setQueryDate(new Date(Long.parseLong(queryDateStr)));

		return flightService.getFlightsByFltNoCond(pageCond);
	}

	/**
	 * 根据起降地条件获取航班列表
	 * 
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getByPlaceCond.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public PageRst<FltInfoDto> getFlightsByPlaceCond(HttpServletRequest request) {
		PageCond<ArrdepPlaceCond> pageCond = new PageCond<ArrdepPlaceCond>();
		// mytest
		// pageCond.setPageNo(1);
		// pageCond.setCond(new ArrdepPlaceCond());
		// pageCond.getCond().setAirlineCode("CA");
		// pageCond.getCond().setArrdep(ArrdepFlag.D);
		// pageCond.getCond().setCode("SHA");
		// pageCond.getCond().setQueryDate(DateUtils.parseDate("2016-01-11"));
		// pageCond.getCond().setType(1);

		String pageNoStr = request.getParameter("pageNo");
		String code = request.getParameter("code");
		String typeStr = request.getParameter("type");
		String arrdepStr = request.getParameter("arrdep");
		String airlineCode = request.getParameter("airlineCode");
		String queryDateStr = request.getParameter("queryDate");
		pageCond.setPageNo(Integer.parseInt(pageNoStr));
		pageCond.setCond(new ArrdepPlaceCond());
		pageCond.getCond().setAirlineCode(airlineCode);
		pageCond.getCond().setArrdep(ArrdepFlag.values()[Integer.parseInt(arrdepStr)]);
		pageCond.getCond().setCode(code);
		pageCond.getCond().setQueryDate(new Date(Long.parseLong(queryDateStr)));
		pageCond.getCond().setType(Integer.parseInt(typeStr));

		return flightService.getFlightsByPlaceCond(pageCond);
	}

	/**
	 * 根据航班Id查询条件获取航班详情
	 * 
	 * @return 航班详情
	 */
	@ResponseBody
	@RequestMapping(value = "/getDetailsByFltIdCond.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public FltDetailDto getFlightDetailByFltIdCond(HttpServletRequest request) {
		FltIdCond cond = new FltIdCond();
		// mytest
		// cond.setFltId("BS-NSJ572-A-11JAN162130-D");
		// cond.setArrdep(ArrdepFlag.A);

		String fltId = request.getParameter("fltId");
		String arrdepStr = request.getParameter("arrdep");
		String openId = request.getParameter("openId");
		cond.setFltId(fltId);
		if (!StringUtils.isNullOrEmpty(openId)) {
			cond.setSubscriberId(openId);
		}
		// cond.setArrdep(ArrdepFlag.values()[Integer.parseInt(arrdepStr)]);
		if (arrdepStr.equals("A")) {
			cond.setArrdep(ArrdepFlag.A);
		} else {
			cond.setArrdep(ArrdepFlag.D);
		}

		return flightService.getFlightDetailByFltIdCond(cond);
	}

	/**
	 * 获取当前用户关注的航班列表
	 * 
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getFollowedFlights.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public PageRst<FltInfoDto> getFollowedFlights(HttpServletRequest request) {
		String openId = request.getParameter("openId");
		if (StringUtils.isNullOrEmpty(openId))
			return null;

		SubscriberCond cond = new SubscriberCond();
		cond.setSubscriberId("openId");

		return flightService.getFollowedFlights(cond);
	}
}
