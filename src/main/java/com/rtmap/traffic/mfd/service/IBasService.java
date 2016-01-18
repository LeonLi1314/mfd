package com.rtmap.traffic.mfd.service;

import java.util.List;
import java.util.Map;

import com.rtmap.traffic.mfd.domain.dto.StartingOrDestinationDto;
import com.rtmap.traffic.mfd.domain.entity.Airline;
import com.rtmap.traffic.mfd.domain.entity.Airport;
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

	/**
	 * 获取机场Map
	 * 
	 * @return 机场Map
	 */
	Map<String, Airport> getAirportMap();

	/**
	 * 获取城市对应机场的Map
	 * 
	 * @return 城市对应机场的Map
	 */
	Map<String, List<Airport>> getCityAirportMap();

	/**
	 * 获取航空公司Map
	 * 
	 * @return 航空公司Map
	 */
	Map<String, Airline> getAirlineMap();

	/**
	 * 根据航站编码获取机场中文名称
	 * 
	 * @param airportCode
	 *            机场编码
	 * @return 机场中文名称
	 */
	String getAirportNameCnByCode(String airportCode);

	/**
	 * 根据航空公司编码获取机场中文名称
	 * 
	 * @param airportCode
	 *            航空公司编码
	 * @return 航空公司中文名称
	 */
	String getAirlineNameCnByCode(String airlineCode);

	/**
	 * 根据城市编码获取其下的机场集合
	 * @param cityCode 城市编码
	 * @return 机场集合
	 */
	List<Airport> getAirportsByCityCode(String cityCode);
}
