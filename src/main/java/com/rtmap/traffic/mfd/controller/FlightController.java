package com.rtmap.traffic.mfd.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 航班模块控制器
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Controller("flt")
public class FlightController {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 根据航班号模糊匹配航班列表
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getByFltNo.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8"})
	public Object getFlightByFltNo(String flightNo){
		return null;
	}
	
	/**
	 * 根据起降地条件获取航班列表
	 * @return 航班列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getByPlaceCond.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8"})
	public Object getFlightByPlaceCond(Object placeCond){
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/getDetailsByFltNo.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8"})
	public List<Object> getFlightDetailsByFltNo(String fltNo){
		return null;
	}
}
