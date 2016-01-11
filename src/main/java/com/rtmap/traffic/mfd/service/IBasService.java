package com.rtmap.traffic.mfd.service;

import java.util.List;
import java.util.Map;

import com.rtmap.traffic.mfd.domain.dto.StartingOrDestinationDto;
import com.rtmap.traffic.mfd.domain.entity.Airline;
import com.rtmap.traffic.mfd.domain.entity.Country;

/**
 * 基础数据服务层接口
 * 
 * @author liqingshan 2016-01-06
 *
 */
public interface IBasService {
	/**
	 * 获取国家基础信息
	 * 
	 * @return 国家基础信息集合
	 */
	List<Country> getCountries();

	/**
	 * 获取国内分组城市集合
	 * 
	 * @param currAirport
	 *            当前视角机场编码
	 * @return 城市集合
	 */
	Map<String, List<StartingOrDestinationDto>> getDomesticGroupedCities(String currAirportCode);

	/**
	 * 获取国际分组城市集合
	 * 
	 * @param currAirport
	 *            当前视角机场编码
	 * 
	 * @return 城市集合
	 */
	Map<String, List<StartingOrDestinationDto>> getInternationalGroupedCities(String currAirportCode);

	/**
	 * 获取国内分组航线的航空公司集合
	 * 
	 * @param currAirport
	 *            当前视角机场编码
	 * 
	 * @return 航空公司集合
	 */
	Map<String, List<Airline>> getDomesticGroupedAirlines(String currAirportCode);

	/**
	 * 获取国际分组航线的航空公司集合
	 * 
	 * @param currAirport
	 *            当前视角机场编码
	 * 
	 * @return 航空公司集合
	 */
	Map<String, List<Airline>> getInternationalGroupedAirlines(String currAirportCode);
}
