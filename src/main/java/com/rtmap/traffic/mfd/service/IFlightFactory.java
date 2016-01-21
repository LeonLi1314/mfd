package com.rtmap.traffic.mfd.service;

/**
 * 航班动态工厂接口，用于创建各个机场航班动态实现类的工厂
 * @author liqingshan 2016-01-20
 *
 */
public interface IFlightFactory {
	IFlightService getService(String airportCode);
}
