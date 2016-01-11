package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IHotAirlineDao;
import com.rtmap.traffic.mfd.domain.entity.HotAirline;

/**
 * 热门航空公司数据访问层实现
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Repository
public class HotAirlineDaoHq implements IHotAirlineDao {
	@Resource
	HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<HotAirline> selectByCurrentAirport(String airportCode) {
		String hsql = "from HotAirline order by priority";

		return (List<HotAirline>) hibernateTemplate.find(hsql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HotAirline> selectDomesticByCurrAirport(String airportCode) {
		String hsql = "from HotAirline where (domint = 'D' or domint = 'M') order by priority";

		return (List<HotAirline>) hibernateTemplate.find(hsql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HotAirline> selectInternationalByCurrAirport(String airportCode) {
		String hsql = "from HotAirline where (domint = 'I' or domint = 'M') order by priority";

		return (List<HotAirline>) hibernateTemplate.find(hsql);
	}

}
