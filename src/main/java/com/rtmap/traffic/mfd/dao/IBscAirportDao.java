package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.Airport;

/**
 * 航站基础数据访问接口
 * 
 * @author liqingshan 2016-01-11
 *
 */
public interface IBscAirportDao {
	/**
	 * 查询所有航站
	 * 
	 * @return 航站集合
	 */
	List<Airport> selectAll();

	/**
	 * 获取城市多航站的集合
	 * 
	 * @return 航站集合
	 */
	List<Airport> selectCityMultiAirport();
}
