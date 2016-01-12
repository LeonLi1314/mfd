package com.rtmap.traffic.mfd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import lqs.frame.util.StringUtils;

/**
 * 基础数据服务层实现
 * 
 * @author liqingshan 2016-01-06
 *
 */
@Service
public class BscServiceImpl implements IBasService {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	IBscCountryDao basCountryDao;
	@Resource
	IBscCityDao cityDao;
	@Resource
	IHotCityDao hotCityDao;
	@Resource
	IBscAirportDao airportDao;
	@Resource
	IBscAirlineDao airlineDao;
	@Resource
	IHotAirlineDao hotAirlineDao;
	private final String hotKey = "HOT";

	@Override
	public List<Country> getCountries() {
		return basCountryDao.selectAll();
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
			cities = cityDao.selectDomestic();
		} else {
			hotCities = hotCityDao.selectInternationalByCurrentAirport(currAirportCode);
			cities = cityDao.selectInternational();
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

		// 组装第一字母对应的城市组
		if (cities == null && cities.size() == 0)
			return rst;
		
		Map<String, List<Airport>> airportMap = getAirports();

		for (City city : cities) {
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
			
			if(city.getAirportCount() <= 1)
				continue;

			//一个城市下多个机场，将机场拼接到城市下
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
			airlines = airlineDao.selectDomestic();
		} else {
			hotAirlines = hotAirlineDao.selectInternationalByCurrAirport(currAirportCode);
			airlines = airlineDao.selectInternational();
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
	 * @return 城市-机场集合
	 */
	private Map<String, List<Airport>> getAirports(){
		List<Airport> airports = airportDao.selectAll();
		Map<String, List<Airport>> airportMap = new HashMap<>();
		
		if(airports != null && airports.size() > 0){
			for (Airport airport : airports) {
				if(!airportMap.containsKey(airport.getCityCode())){
					airportMap.put(airport.getCityCode(), new ArrayList<Airport>());
				}
				
				airportMap.get(airport.getCityCode()).add(airport);
			}
		}
		
		return airportMap;
	}
}
