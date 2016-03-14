package com.rtmap.traffic.mfd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rtmap.traffic.mfd.dao.ICityWeatherDao;
import com.rtmap.traffic.mfd.domain.CityWeatherEle;
import com.rtmap.traffic.mfd.domain.WeatherEle;
import com.rtmap.traffic.mfd.domain.entity.CityWeather;
import com.rtmap.traffic.mfd.service.ICityWeatherService;

import rtmap.frame.util.JaxbUtils;

/**
 * 城市天气服务层实现
 * 
 * @author liqingshan
 *
 */
@Service
public class CityWeatherServiceImpl implements ICityWeatherService {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ICityWeatherDao cityWeatherDao;

	@Override
	@SuppressWarnings("rawtypes")
	public void execute(Element weathersEle) {
		try {
			JaxbUtils cwJaxb = new JaxbUtils(CityWeatherEle.class);
			Iterator iterator = weathersEle.elementIterator();
			List<CityWeather> cws = new ArrayList<>(128);

			while (iterator.hasNext()) {
				Element e = (Element) iterator.next();
				CityWeatherEle cwe = cwJaxb.<CityWeatherEle> fromXml(e.asXML());
				// System.out.println(String.format("CityWeather[%s]解析成功!",
				// cw.getCityCode()));

				cws.addAll(convertToCityWeather(cwe));
			}

			if (cws.size() > 0) {
				System.out.println(String.format("共解析[%s]条记录!", cws.size()));
				cityWeatherDao.BatchInsert(cws);
			}
		} catch (Exception e2) {
			logger.error(e2.toString());
		}
	}

	private List<CityWeather> convertToCityWeather(CityWeatherEle cwe) {
		List<CityWeather> list = new ArrayList<>();

		if (cwe.getWeathers() == null || cwe.getWeathers().size() == 0)
			return list;

		for (WeatherEle we : cwe.getWeathers()) {
			CityWeather cw = new CityWeather();
			cw.setAirportCode(cwe.getAirportCode());
			cw.setAirportNameCn(cwe.getAirportNameCn());
			cw.setAirportNameEn(cwe.getAirportNameEn());
			cw.setAreaId(cwe.getAreaId());
			cw.setCityCode(cwe.getCityCode());
			cw.setCityNameCn(cwe.getCityNameCn());
			cw.setCityNameEn(cwe.getCityNameEn());
			cw.setDayTemp(we.getDayTemp());
			cw.setDwdirCode(we.getDwdirCode());
			cw.setDwdirSpecCn(we.getDwdirSpecCn());
			cw.setDwdirSpecEn(we.getDwdirSpecEn());
			cw.setDwphCode(we.getDwphCode());
			cw.setDwphImageUrl(we.getDwphImageUrl());
			cw.setDwphSpecCn(we.getDwphSpecCn());
			cw.setDwphSpecEn(we.getDwphSpecEn());
			cw.setDwphSpecEn(we.getDwpwSpecEn());
			cw.setDwpwCode(we.getDwpwCode());
			cw.setDwpwSpecCn(we.getDwpwSpecCn());
			cw.setDwpwSpecEn(we.getDwpwSpecEn());
			// 组装指数信息
			// cw.setIndexDisp(we.getWeatherIndexes());
			cw.setNightTemp(we.getNightTemp());
			cw.setNwdirCode(we.getNwdirCode());
			cw.setNwdirSpecCn(we.getNwdirSpecCn());
			cw.setNwdirSpecEn(we.getNwdirSpecEn());
			cw.setNwphCode(we.getNwphCode());
			cw.setNwphCode(we.getNwphCode());
			cw.setNwphImageUrl(we.getNwphImageUrl());
			cw.setNwphSpecCn(we.getNwphSpecCn());
			cw.setNwphSpecEn(we.getNwphSpecEn());
			cw.setNwpwCode(we.getNwpwCode());
			cw.setNwpwSpecCn(we.getNwpwSpecCn());
			cw.setNwpwSpecEn(we.getNwpwSpecEn());
			cw.setReleaseTime(we.getReleaseTime());
			cw.setStatDate(we.getStatDate());
			list.add(cw);
		}

		return list;
	}

	@Override
	public CityWeather getCityWeather(String airportCode, Date statDate) {
		return cityWeatherDao.select(airportCode, statDate);
	}
}
