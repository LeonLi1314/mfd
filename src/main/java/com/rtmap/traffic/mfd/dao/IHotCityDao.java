package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.HotCity;

/**
 * 热门城市基础数据访问接口
 * @author liqingshan 2016-01-11
 *
 */
public interface IHotCityDao {
	/**
	 * 根据当前航站查询热门城市
	 * @param airportCode 航站编码
	 * @return 热门城市集合
	 */
	List<HotCity> selectByCurrentAirport(String airportCode);

	/**
	 * 根据当前航站查询国内热门城市
	 * @param airportCode 航站编码
	 * @return 热门城市集合
	 */
	List<HotCity> selectDomesticByCurrentAirport(String airportCode);

	/**
	 * 根据当前航站查询国际热门城市
	 * @param airportCode 航站编码
	 * @return 热门城市集合
	 */
	List<HotCity> selectInternationalByCurrentAirport(String airportCode);
}
