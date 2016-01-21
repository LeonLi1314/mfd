package com.rtmap.traffic.mfd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rtmap.traffic.mfd.common.AirportViewConst;
import com.rtmap.traffic.mfd.service.IFlightFactory;
import com.rtmap.traffic.mfd.service.IFlightService;

/**
 * 航班动态工厂类，用于创建各个机场航班动态实现类的工厂
 * 
 * @author liqingshan 2016-01-20
 *
 */
@Component
public class FlightFactory implements IFlightFactory {
	@Resource(name="pekService")
	IFlightService pekService;

	@Override
	public IFlightService getService(String airportCode) {
		switch (airportCode) {
		case AirportViewConst.PEK:
			return pekService;
		default:
			return pekService;
		}
	}
}
