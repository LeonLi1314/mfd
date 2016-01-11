package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.HotAirline;

/**
 * 热门航空公司基础数据访问接口
 * 
 * @author liqingshan 2016-01-11
 *
 */
public interface IHotAirlineDao {
	/**
	 * 根据当前航站查询热门航空公司
	 * 
	 * @param airportCode
	 *            航站编码
	 * @return 航空公司集合
	 */
	List<HotAirline> selectByCurrentAirport(String airportCode);

	/**
	 * 根据当前航站查询国内热门航空公司
	 * 
	 * @param airportCode
	 *            航站编码
	 * @return 航空公司集合
	 */
	List<HotAirline> selectDomesticByCurrAirport(String airportCode);

	/**
	 * 根据当前航站查询国际热门航空公司
	 * 
	 * @param airportCode
	 *            航站编码
	 * @return 航空公司集合
	 */
	List<HotAirline> selectInternationalByCurrAirport(String airportCode);
}
