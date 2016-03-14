package com.rtmap.traffic.mfd.service;

import java.util.Date;

import org.dom4j.Element;

import com.rtmap.traffic.mfd.domain.entity.CityWeather;

/**
 * 城市天气接口
 * @author liqingshan
 *
 */
public interface ICityWeatherService {
	public void execute(Element weathersEle);
	
	public CityWeather getCityWeather(String cityCode, Date statDate);
}
