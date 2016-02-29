package com.rtmap.traffic.mfd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.mfd.dao.IBscAirlineDao;
import com.rtmap.traffic.mfd.dao.IBscAirportDao;
import com.rtmap.traffic.mfd.dao.IBscCityDao;
import com.rtmap.traffic.mfd.dao.IBscCountryDao;
import com.rtmap.traffic.mfd.dao.IHotAirlineDao;
import com.rtmap.traffic.mfd.dao.IHotCityDao;
import com.rtmap.traffic.mfd.domain.DomintFlag;
import com.rtmap.traffic.mfd.domain.dto.StartingOrDestinationDto;
import com.rtmap.traffic.mfd.domain.entity.Airline;
import com.rtmap.traffic.mfd.domain.entity.Airport;
import com.rtmap.traffic.mfd.domain.entity.City;
import com.rtmap.traffic.mfd.domain.entity.Country;
import com.rtmap.traffic.mfd.domain.entity.HotAirline;
import com.rtmap.traffic.mfd.domain.entity.HotCity;
import com.rtmap.traffic.mfd.service.IBasService;

import rtmap.frame.util.StringUtils;

/**
 * 基础数据服务层实现
 * 
 * @author liqingshan 2016-01-06
 *
 */
@Service
public class BscServiceImpl implements IBasService {
	@Resource
	private IBscCountryDao basCountryDao;
	@Resource
	private IBscCityDao cityDao;
	@Resource
	private IHotCityDao hotCityDao;
	@Resource
	private IBscAirportDao airportDao;
	@Resource
	private IBscAirlineDao airlineDao;
	@Resource
	private IHotAirlineDao hotAirlineDao;
	private final String hotKey = "HOT";

	@Override
	public List<Country> getCountries() {
		return basCountryDao.selectAll();
	}

	/**
	 * 获取所有城市
	 * 
	 * @return 城市列表
	 */
	private List<City> getCities() {
		return cityDao.selectAll();
	}

	/**
	 * 获取国内城市
	 * 
	 * @return 国内城市列表
	 */
	private List<City> getDomesticCities() {
		return cityDao.selectDomestic();
	}

	/**
	 * 获取国际城市
	 * 
	 * @return 国际城市列表
	 */
	private List<City> getInternationalCities() {
		return cityDao.selectInternational();
	}

	/**
	 * 获取所有航站
	 * 
	 * @return 航站列表
	 */
	private List<Airport> getAirports() {
		return airportDao.selectAll();
	}
	
	/**
	 * 获取所有航空公司
	 * 
	 * @return 航空公司列表
	 */
	private List<Airline> getAirlines() {
		return airlineDao.selectAll();
	}

	/**
	 * 获取国内航空公司
	 * 
	 * @return 国内航空公司列表
	 */
	private List<Airline> getDomesticAirlines() {
		return airlineDao.selectDomestic();
	}

	/**
	 * 获取国际航空公司
	 * 
	 * @return 国际航空公司列表
	 */
	private List<Airline> getInternationalAirlines() {
		return airlineDao.selectInternational();
	}

	/**
	 * 根据航站编码获取所在城市编码
	 * @param airportCode 航站编码
	 * @return 所在城市编码
	 */
	private String getCityCodeByAirportCode(String airportCode) {
		List<Airport> airports = getAirports();
		for (Airport airport : airports) {
			if (airport.getAirportCode().equals(airportCode))
				return airport.getCityCode();
		}

		return null;
	}

	@Override
	public Map<String, List<StartingOrDestinationDto>> getDomesticGroupedCities(String currAirportCode) {
		return getGroupedCities(currAirportCode, DomintFlag.D);
	}

	@Override
	public Map<String, List<StartingOrDestinationDto>> getInternationalGroupedCities(String currAirportCode) {
		return getGroupedCities(currAirportCode, DomintFlag.I);
	}

	@SuppressWarnings("null")
	private Map<String, List<StartingOrDestinationDto>> getGroupedCities(String currAirportCode, DomintFlag domint) {
		Map<String, List<StartingOrDestinationDto>> rst = new HashMap<>();
		List<HotCity> hotCities;
		List<City> cities;

		if (DomintFlag.D == domint) {
			hotCities = hotCityDao.selectDomesticByCurrentAirport(currAirportCode);
			cities = getDomesticCities();
		} else {
			hotCities = hotCityDao.selectInternationalByCurrentAirport(currAirportCode);
			cities = getInternationalCities();
		}

		// 组装热门城市
		if (hotCities != null && hotCities.size() > 0) {
			rst.put(hotKey, new ArrayList<StartingOrDestinationDto>());

			for (HotCity hotCity : hotCities) {
				StartingOrDestinationDto sd = new StartingOrDestinationDto();
				sd.setCode(hotCity.getCityCode());
				sd.setNameCn(hotCity.getNameCn());
				sd.setNameEn(hotCity.getNameEn());
				sd.setType(0);

				rst.get(hotKey).add(sd);
			}
		}

		String cityCode = getCityCodeByAirportCode(currAirportCode);

		// 组装第一字母对应的城市组
		if (cities == null && cities.size() == 0)
			return rst;

		Map<String, List<Airport>> airportMap = getCityAirportMap();

		for (City city : cities) {
			// 排除当前视角城市
			if(city.getCityCode().equals(cityCode))
				continue;
			
			String firstLeter = city.getFirstLetter();

			if (StringUtils.isNullOrEmpty(firstLeter)) {
				continue;
			}

			String headLetter = firstLeter.substring(0, 1);

			if (!rst.containsKey(headLetter)) {
				rst.put(headLetter, new ArrayList<StartingOrDestinationDto>());
			}

			StartingOrDestinationDto sd = new StartingOrDestinationDto();
			sd.setCode(city.getCityCode());
			sd.setFirstLetter(city.getFirstLetter());
			sd.setFullPinyin(city.getFullPinyin());
			sd.setNameCn(city.getNameCn());
			sd.setNameEn(city.getNameCn());
			sd.setType(0);
			rst.get(headLetter).add(sd);

			if (city.getAirportCount() <= 1)
				continue;

			// 一个城市下多个机场，将机场拼接到城市下
			List<Airport> airports = airportMap.get(city.getCityCode());
			for (Airport airport : airports) {
				sd = new StartingOrDestinationDto();
				sd.setCode(airport.getAirportCode());
				sd.setFirstLetter(airport.getFirstLetter());
				sd.setFullPinyin(airport.getFullPinyin());
				sd.setNameCn(airport.getNameCn());
				sd.setNameEn(airport.getNameCn());
				sd.setType(1);
				rst.get(headLetter).add(sd);
			}
		}

		return rst;
	}

	@Override
	public Map<String, List<Airline>> getDomesticGroupedAirlines(String currAirportCode) {
		return getGroupedAirlines(currAirportCode, DomintFlag.D);
	}

	@Override
	public Map<String, List<Airline>> getInternationalGroupedAirlines(String currAirportCode) {
		return getGroupedAirlines(currAirportCode, DomintFlag.I);
	}

	/**
	 * 获取分组的航空公司
	 * 
	 * @param currAirportCode
	 *            当前视角机场
	 * @param domint
	 *            国际国内标志
	 * @return 航空公司集合
	 */
	private Map<String, List<Airline>> getGroupedAirlines(String currAirportCode, DomintFlag domint) {
		Map<String, List<Airline>> rst = new HashMap<>();
		List<HotAirline> hotAirlines;
		List<Airline> airlines;

		if (DomintFlag.D == domint) {
			hotAirlines = hotAirlineDao.selectDomesticByCurrAirport(currAirportCode);
			airlines = getDomesticAirlines();
		} else {
			hotAirlines = hotAirlineDao.selectInternationalByCurrAirport(currAirportCode);
			airlines = getInternationalAirlines();
		}

		// 组装热门航空公司
		if (hotAirlines != null && hotAirlines.size() > 0) {
			rst.put(hotKey, new ArrayList<Airline>());

			for (HotAirline hotAirline : hotAirlines) {
				Airline airline = new Airline();
				airline.setAirlineCode(hotAirline.getAirlineCode());
				airline.setDomint(hotAirline.getDomint());
				airline.setNameCn(hotAirline.getNameCn());
				airline.setNameEn(hotAirline.getNameEn());

				rst.get(hotKey).add(airline);
			}
		}

		// 组装第一字母对应的航空公司组;
		if (airlines == null || airlines.size() == 0)
			return rst;

		for (Airline airline : airlines) {
			String firstLeter = airline.getFirstLetter();

			if (StringUtils.isNullOrEmpty(firstLeter)) {
				continue;
			}

			String headLetter = firstLeter.substring(0, 1);

			if (!rst.containsKey(headLetter)) {
				rst.put(headLetter, new ArrayList<Airline>());
			}

			rst.get(headLetter).add(airline);
		}

		return rst;
	}

	/**
	 * 获取城市对应的机场集合
	 * 
	 * @return 城市-机场集合
	 */
	@Override
	public Map<String, List<Airport>> getCityAirportMap() {
		List<Airport> airports = getAirports();
		Map<String, List<Airport>> airportMap = new HashMap<>();

		if (airports != null && airports.size() > 0) {
			for (Airport airport : airports) {
				if (!airportMap.containsKey(airport.getCityCode())) {
					airportMap.put(airport.getCityCode(), new ArrayList<Airport>());
				}

				airportMap.get(airport.getCityCode()).add(airport);
			}
		}

		return airportMap;
	}

	@Override
	public Map<String, Airport> getAirportMap() {
		Map<String, Airport> rst = new HashMap<>();
		List<Airport> airports = getAirports();

		for (Airport airport : airports) {
			rst.put(airport.getAirportCode(), airport);
		}

		return rst;
	}

	@Override
	public Map<String, Airline> getAirlineMap() {
		Map<String, Airline> rst = new HashMap<>();
		List<Airline> airlines = getAirlines();

		for (Airline airline : airlines) {
			rst.put(airline.getAirlineCode(), airline);
		}

		return rst;
	}

	@Override
	public String getAirportNameCnByCode(String airportCode) {
		Map<String, Airport> map = getAirportMap();

		if (map.containsKey(airportCode)) {
			return map.get(airportCode).getNameCn();
		}

		return null;
	}

	@Override
	public String getAirlineNameCnByCode(String airlineCode) {
		Map<String, Airline> map = getAirlineMap();

		if (map.containsKey(airlineCode)) {
			return map.get(airlineCode).getNameCn();
		}

		return null;
	}

	@Override
	public List<Airport> getAirportsByCityCode(String cityCode) {
		Map<String, List<Airport>> map = getCityAirportMap();

		if (map.containsKey(cityCode)) {
			return map.get(cityCode);
		}

		return null;
	}
}
