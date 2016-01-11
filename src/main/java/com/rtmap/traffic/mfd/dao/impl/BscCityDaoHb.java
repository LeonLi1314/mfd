package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IBscCityDao;
import com.rtmap.traffic.mfd.domain.entity.City;

/**
 * 城市基础表数据访问层实现
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Repository
public class BscCityDaoHb implements IBscCityDao{
	@Resource
	HibernateTemplate hibernateTemplate;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<City> selectAll() {
		String hsql = "from City as city order by city.fullPinyin";

		return (List<City>) hibernateTemplate.find(hsql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<City> selectDomestic() {
		String hsql = "from City as city where domint = 'D' order by city.fullPinyin";

		return (List<City>) hibernateTemplate.find(hsql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<City> selectInternational() {
		String hsql = "from City as city where domint = 'I' order by city.fullPinyin";

		return (List<City>) hibernateTemplate.find(hsql);
	}
}
