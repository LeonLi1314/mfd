package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IBscAirportDao;
import com.rtmap.traffic.mfd.domain.entity.Airport;

/**
 * 机场基础数据访问层实现
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Repository
public class BscAirportDaoHb extends DaoHbSupport implements IBscAirportDao {
	@Override
	@SuppressWarnings("unchecked")
	public List<Airport> selectAll() {
		String hsql = "from Airport as airport order by airport.fullPinyin";

		return (List<Airport>) getHibernateTemplate().find(hsql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Airport> selectCityMultiAirport() {
		// select * from bas_airport where EXISTS(select 1 from bas_airport group by city_code having count(1) > 1)
		String hsql = "from Airport as airport where exists(select 1 from Airport as a1 group by a1.cityCode having count(1) > 1) order by airport.fullPinyin";

		return (List<Airport>) getHibernateTemplate().find(hsql);
	}
}
