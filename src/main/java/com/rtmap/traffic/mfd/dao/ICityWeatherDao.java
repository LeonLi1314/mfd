package com.rtmap.traffic.mfd.dao;

import java.util.Date;
import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.CityWeather;

/**
 * 城市天气数据访问接口
 * @author  liqingshan
 *
 */
public interface ICityWeatherDao {
	CityWeather select(String airportCode, Date statDate);
	
	void BatchInsert(List<CityWeather> list);
}
