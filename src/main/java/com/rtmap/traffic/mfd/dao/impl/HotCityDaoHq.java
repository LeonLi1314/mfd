package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IHotCityDao;
import com.rtmap.traffic.mfd.domain.entity.HotCity;

/**
 * 热门城市数据访问层实现
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Repository
public class HotCityDaoHq implements IHotCityDao {
	@Resource
	HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<HotCity> selectByCurrentAirport(String airportCode) {
		String hsql = "from HotCity order by priority";

		return (List<HotCity>) hibernateTemplate.find(hsql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HotCity> selectDomesticByCurrentAirport(String airportCode) {
		String hsql = "from HotCity where domint = 'D' order by priority";

		return (List<HotCity>) hibernateTemplate.find(hsql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HotCity> selectInternationalByCurrentAirport(String airportCode) {
		String hsql = "from HotCity where domint = 'I' order by priority";

		return (List<HotCity>) hibernateTemplate.find(hsql);
	}

}
