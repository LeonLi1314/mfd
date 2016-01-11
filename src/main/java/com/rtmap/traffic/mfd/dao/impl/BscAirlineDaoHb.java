package com.rtmap.traffic.mfd.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.rtmap.traffic.mfd.dao.IBscAirlineDao;
import com.rtmap.traffic.mfd.domain.entity.Airline;

/**
 * 航站基础表数据访问层实现
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Repository
public class BscAirlineDaoHb implements IBscAirlineDao {
	@Resource
	HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<Airline> selectAll() {
		String hsql = "from Airline as airline order by airline.fullPinyin";

		return (List<Airline>) hibernateTemplate.find(hsql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Airline> selectDomestic() {
		String hsql = "from Airline as airline where (airline.domint = 'D' or airline.domint = 'M') order by airline.fullPinyin";

		return (List<Airline>) hibernateTemplate.find(hsql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Airline> selectInternational() {
		String hsql = "from Airline as airline where (airline.domint = 'I' or airline.domint = 'M') order by airline.fullPinyin";

		return (List<Airline>) hibernateTemplate.find(hsql);
	}

}
