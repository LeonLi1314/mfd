package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.Airline;

/**
 * 航空公司基础数据访问接口
 * 
 * @author liqingshan 2016-01-11
 *
 */
public interface IBscAirlineDao {
	/**
	 * 查询所有航空公司
	 * @return 航空公司集合
	 */
	List<Airline> selectAll();

	/**
	 * 查询国内航空公司
	 * @return 航空公司集合
	 */
	List<Airline> selectDomestic();

	/**
	 * 查询国际航空公司
	 * @return 航空公司集合
	 */
	List<Airline> selectInternational();
	
}
